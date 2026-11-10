#!/usr/bin/env bash
# Run against a local synthetic-data instance. Python 3 standard library only.
set -euo pipefail
exec python3 - "$@" <<'PY'
import argparse, datetime, hashlib, http.cookiejar, json, pathlib, sys, urllib.error, urllib.parse, urllib.request, uuid
p = argparse.ArgumentParser(description='CampusConnect HTTP rehearsal; exits nonzero on failed expectations')
p.add_argument('--base-url', default='http://127.0.0.1:8080')
p.add_argument('--transcript', type=pathlib.Path, help='New JSONL file; never overwrite an existing transcript')
p.add_argument('--save-state', type=pathlib.Path, help='New synthetic record snapshot for restart verification')
p.add_argument('--check-restart', type=pathlib.Path, help='Verify a previous snapshot after restarting the same database')
a = p.parse_args()
u = urllib.parse.urlparse(a.base_url)
if u.scheme != 'http' or u.hostname not in ('127.0.0.1', 'localhost', '::1') or u.username or u.password or u.path not in ('', '/') or u.query or u.fragment:
    p.error('Use an HTTP loopback origin with no credentials/path/query')
base = a.base_url.rstrip('/')
out = a.transcript.open('x') if a.transcript else None
sessions = {name: urllib.request.build_opener(urllib.request.HTTPCookieProcessor(http.cookiejar.CookieJar())) for name in ('a', 'b', 'r', 'none')}
steps = 0

def emit(event):
    event['time_utc'] = datetime.datetime.now(datetime.timezone.utc).isoformat()
    line = json.dumps(event, sort_keys=True)
    print(line, flush=True)
    if out:
        out.write(line + '\n'); out.flush()

def require(condition, message):
    if not condition:
        raise AssertionError(message)

def request(label, actor, method, path, expected, data=None, etag=None, extra=None):
    global steps
    headers = {'Content-Type': 'application/json'}
    if etag is not None:
        headers['If-Match'] = etag
    headers.update(extra or {})
    req = urllib.request.Request(base + path, data=None if data is None else json.dumps(data).encode(), headers=headers, method=method)
    try:
        res = sessions[actor].open(req, timeout=10)
    except urllib.error.HTTPError as err:
        res = err
    with res:
        raw = res.read().decode(); status = res.code; tag = res.headers.get('ETag')
    body = json.loads(raw) if raw else None
    steps += 1
    emit({'step': steps, 'label': label, 'actor': actor, 'method': method, 'path': path, 'expected': expected, 'actual': status, 'etag': tag, 'response': body})
    require(status == expected, f'{label}: expected HTTP {expected}, got {status}')
    return body, tag

def own(label='Owner reads request'):
    return request(label, 'a', 'GET', '/api/requests/' + rid, 200)

def unchanged(snapshot, label):
    got, _ = own(label + ': state unchanged')
    require(got == snapshot, label + ': rejected operation changed the record')

def select(actor, identity):
    request('Select ' + identity, actor, 'POST', '/api/demo/identity', 200, {'identity': identity})

try:
    emit({'event': 'start', 'mode': 'restart' if a.check_restart else 'workflow', 'base_url': base, 'python': sys.version.split()[0]})
    if a.check_restart:
        saved = json.loads(a.check_restart.read_text()); rid = saved['record']['id']
        select('a', 'student-a'); select('r', 'reviewer')
        current, tag = own('Restart: owner reselects identity and reads stored record')
        require(current == saved['record'] and tag == saved['etag'], 'Restart changed stored record or ETag')
        listing, _ = request('Restart: reviewer sees stored record', 'r', 'GET', '/api/reviewer/requests', 200)
        require(any(x['id'] == rid for x in listing), 'Reviewer listing lost the stored record')
        new, _ = request('Restart: create another unique record', 'a', 'POST', '/api/requests', 201, {'title':'Post-restart synthetic request','category':'IT','description':'Synthetic persistence rehearsal after application restart.'})
        require(new['id'] != rid, 'ID reused after restart')
    else:
        valid = {'title':'Synthetic registration request','category':'Academic','description':'Synthetic course registration issue for the local demonstration.'}
        request('Missing identity denied', 'none', 'GET', '/api/requests', 403)
        request('Forged headers cannot select a role', 'none', 'POST', '/api/requests', 403, valid, extra={'X-User-ID':'student-a','X-User-Role':'REVIEWER'})
        select('a','student-a'); select('b','student-b'); select('r','reviewer')
        initial, _ = request('Initial owner listing', 'a', 'GET', '/api/requests', 200)
        request('Invalid title rejected', 'a', 'POST', '/api/requests', 400, dict(valid, title=' '))
        after, _ = request('Invalid submission did not persist', 'a', 'GET', '/api/requests', 200)
        require(after == initial, 'Invalid submission changed listing')
        created, tag = request('Create Student A request', 'a','POST','/api/requests',201,dict(valid, studentID='student-b'))
        rid = created['id']; uuid.UUID(rid)
        require(created['studentID']=='student-a' and created['status']=='Submitted' and created['resolutionNote']=='' and tag=='"0"', 'Creation defaults/owner/ETag incorrect')
        other, _ = request('Create Student B request','b','POST','/api/requests',201,valid)
        require(other['id'] != rid, 'Request IDs are not unique')
        listing, _ = request('Student A lists only owned requests','a','GET','/api/requests',200)
        require(any(x['id']==rid for x in listing) and all(x['studentID']=='student-a' for x in listing), 'Owner listing leaked other data')
        request('Student B cannot read Student A record','b','GET','/api/requests/'+rid,404)
        listing, _ = request('Reviewer lists both students','r','GET','/api/reviewer/requests',200)
        require({rid,other['id']}.issubset({x['id'] for x in listing}), 'Reviewer cannot see both records')
        request('Reviewer inspects request','r','GET','/api/reviewer/requests/'+rid,200)
        request('Student cannot change status','a','PATCH','/api/requests/'+rid+'/status',403,{'status':'In Progress'},tag)
        unchanged(created,'Denied student update')
        request('Direct Submitted to Resolved rejected','r','PATCH','/api/requests/'+rid+'/status',400,{'status':'Resolved'},tag)
        unchanged(created,'Invalid transition')
        request('Missing If-Match rejected','r','PATCH','/api/requests/'+rid+'/status',428,{'status':'In Progress'})
        unchanged(created,'Missing precondition')
        progress, tag = request('Move to In Progress','r','PATCH','/api/requests/'+rid+'/status',200,{'status':'In Progress'},tag)
        request('Resolve without saved nonblank note rejected','r','PATCH','/api/requests/'+rid+'/status',400,{'status':'Resolved'},tag)
        unchanged(progress,'Missing saved note')
        request('Unknown request rejected','r','PATCH','/api/requests/'+str(uuid.uuid4())+'/status',404,{'status':'In Progress'},'"0"')
        request('Oversized note rejected','r','PATCH','/api/requests/'+rid+'/note',400,{'resolutionNote':'n'*2001},tag)
        unchanged(progress,'Oversized note')
        previous = tag
        noted, tag = request('Save resolution separately','r','PATCH','/api/requests/'+rid+'/note',200,{'resolutionNote':'  Synthetic resolution recorded.  '},tag)
        require(noted['resolutionNote']=='Synthetic resolution recorded.', 'Note not trimmed/saved')
        request('Stale If-Match rejected','r','PATCH','/api/requests/'+rid+'/status',412,{'status':'Resolved'},previous)
        unchanged(noted,'Stale update')
        resolved, tag = request('Resolve with saved note','r','PATCH','/api/requests/'+rid+'/status',200,{'status':'Resolved'},tag)
        require(resolved['status']=='Resolved','Resolve did not save status')
        request('Cannot clear note while Resolved','r','PATCH','/api/requests/'+rid+'/note',400,{'resolutionNote':' '},tag)
        unchanged(resolved,'Clearing resolved note')
        edited, tag = request('Reviewer replaces saved resolution','r','PATCH','/api/requests/'+rid+'/note',200,{'resolutionNote':'Synthetic revised resolution.'},tag)
        unchanged(edited,'Owner sees latest saved note')
        reopened, tag = request('Reopen resolved request','r','PATCH','/api/requests/'+rid+'/status',200,{'status':'In Progress'},tag)
        require(reopened['status']=='In Progress' and reopened['resolutionNote']==edited['resolutionNote'],'Reopen lost status/note')
        for field in ('id','studentID','title','category','description','createdAt'):
            require(reopened[field]==created[field], 'Immutable field changed: '+field)
        final, finaltag = own('Owner sees reopened status and preserved note')
        require(final==reopened and finaltag==tag,'Owner read differs from saved state')
        if a.save_state:
            with a.save_state.open('x') as f:
                json.dump({'record': final, 'etag': tag},f,indent=2)
    emit({'event':'PASS','http_checks':steps,'scope':'HTTP rehearsal only; no release approval, vulnerability scan or complete acceptance certification'})
except Exception as err:
    emit({'event':'FAIL','http_checks':steps,'error':str(err)})
    sys.exit(1)
finally:
    if out: out.close()
PY
