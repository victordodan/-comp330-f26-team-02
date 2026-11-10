# CampusConnect — Cycle 1 HTTP Demo and Rehearsal

**Status:** Rehearsed candidate procedure; final release acceptance/tag pending.
**Application source checkpoint:** `714a31bf5c9283e732fcfd895615a3471fef93b4`.
**Scope:** Local synthetic data, request/owner/reviewer workflow and failure paths.

## Artifacts and prerequisites

The single executable is `scripts/demo-requests.sh`, delivered by companion
[PR #97](https://github.com/victordodan/-comp330-f26-team-02/pull/97).
This PR (#96) supplies the procedure and recorded rehearsal evidence. Integrate
#97 before running the command from the completed repository. The shell entry
uses Python 3's standard-library HTTP client, cookie jars and JSON assertions;
Bash and Python 3 are required. No jq or third-party Python packages are needed.
Build/run the application using JDK 17 and Maven. The executable bit is preserved
in #97; `bash scripts/demo-requests.sh --help` also works.

Use a dedicated local H2 store with synthetic data. The demo creates requests
and leaves them in that store; it has no deletion/reset endpoint and must not be
pointed at an existing shared dataset. The script restricts its URL to HTTP
loopback origins and exits nonzero for transport failures, unexpected status
codes or incorrect response contents. It does not interpret expected errors as
success without checking the code and relevant unchanged state.

## Build and start the candidate

From the repository root:

```sh
mvn -B clean verify
mkdir -p work/demo-runtime work/demo-evidence
java -jar target/campusconnect-0.1.0-SNAPSHOT.jar \
  --server.address=127.0.0.1 --server.port=18080 \
  --spring.datasource.url=jdbc:h2:file:./work/demo-runtime/campusconnect
```

Keep the server in its own terminal. Wait for Spring Boot's Started message.
Record the exact source revision (`git rev-parse HEAD`), any local changes,
runtime versions and artifact hash; the rehearsal record explains our actual
local build context. Do not label an untagged checkout `a5-cycle1-release`.

In another terminal, from the same repository root:

```sh
./scripts/demo-requests.sh --base-url http://127.0.0.1:18080 \
  --transcript work/demo-evidence/workflow.jsonl \
  --save-state work/demo-evidence/restart-state.json
```

Use new output paths for each rehearsal: existing transcripts/snapshots are never
overwritten. Output records HTTP status, response JSON and ETag, but not session
cookies. Each JSONL transcript ends with PASS only after all assertions pass;
on failure it emits FAIL and exits nonzero. Preserve failed output and explain
it rather than announcing success from printed expected codes.

## Presentation sequence and proof links

The executable performs the checks in the following order. IDs are read from
actual create responses; they are UUIDs, not `REQ-1001`. Separate cookie jars
represent Student A, Student B, Reviewer and a caller without a selected identity.

| Segment | Demonstration / expected result | Requirement / acceptance context |
|---|---|---|
| 1 — Identity and input boundary | No session and forged role headers return 403. Select `student-a`, `student-b` and `reviewer` through POST `/api/demo/identity`. Blank title returns 400 without changing the owner's listing. | REQ-001/004; TC-002/009 |
| 2 — Submission and isolation | A/B creates return 201, distinct UUIDs, server-assigned owner, Submitted, empty note and initial ETag. A listing contains only A records; B's direct access to A returns 404. Reviewer lists/inspects both. | REQ-001/002/003/004; TC-001/005/006/008 |
| 3 — Reviewer lifecycle | Student status edit returns 403. Direct Submitted → Resolved returns 400. Missing If-Match returns 428. Reviewer moves Submitted → In Progress using the current quoted ETag. Resolving without a saved nonblank note returns 400. | REQ-004/005; TC-009/010/011 |
| 4 — Notes, conflicts and resolution | Unknown UUID returns 404; note over 2,000 characters returns 400. Save trimmed note via separate PATCH `/note`. Stale If-Match returns 412 without mutation. Resolve via PATCH `/status`; clearing note while Resolved returns 400. Reviewer replaces resolution; owner sees latest saved text. | REQ-002/005/006; TC-003/004/011/012 |
| 5 — Reopen | Resolved → In Progress preserves note, ID, owner, creation timestamp and fields. Owner retrieves latest state and ETag. | REQ-002/005; TC-003/004/010 |
| 6 — Process restart | Stop/start the jar with the same H2 URL. Reselect identities and retrieve the saved record; compare every saved JSON field and ETag. Reviewer sees it; new request has a distinct ID. | REQ-003; TC-007 and part of TC-006 |

After denied/invalid/stale edits of the existing record, owner GET assertions
confirm it is unchanged. Creating with a forged body `studentID` also verifies
the owner comes from the session. Note and status writes are separate operations:
there is no direct Submitted → Resolved shortcut or inline-resolution payload.
Every successful response supplies the ETag used for the next mutation.

## Packaged restart and repeatability

After the workflow passes, stop the application normally (Ctrl-C in its terminal),
wait for process exit, and restart the same jar using the **same working directory,
port and datasource URL**. Then run:

```sh
./scripts/demo-requests.sh --base-url http://127.0.0.1:18080 \
  --check-restart work/demo-evidence/restart-state.json \
  --transcript work/demo-evidence/restart.jsonl
./scripts/demo-requests.sh --base-url http://127.0.0.1:18080 \
  --transcript work/demo-evidence/repeat.jsonl
```

The second full run works with existing records because IDs/ETags are captured
and listings are tested for ownership/membership rather than assuming a single
record. Restart evidence covers the actual saved In Progress record and note;
it does not demonstrate every lifecycle state, backup restoration or recovery
from corruption. No automated runtime restart is hidden inside the demo client.

## Rehearsal evidence and limits

See [rehearsal record](../testing/a5-demo-rehearsal.md) and
[raw evidence](../../test-evidence/a5-http-demo/README.md). The record identifies
source scope, tool versions, executable/jar hashes, executed counts and actual
results. This is an automated HTTP rehearsal performed by Codex, not a claim
that a named student independently witnessed it or approved final acceptance.

The procedure supports the twelve-case catalog but does not mark all twelve
acceptance cases complete. Database write-failure injection, rollback/concurrency
races, all field boundaries, all restart states and presentation rehearsal by
students remain separate evidence/disposition work. Existing JUnit tests cover
many related negatives; retain their exact-revision logs alongside this demo.

## Limitations and fallback

- LIM-002: Demo identities are deliberately selectable, including Reviewer.
  Session checks and header-spoofing denial are not institutional authentication.
- LIM-001: H2 retains records in the same local store, but backup/restore and
  multi-instance operation are unverified. Restart persistence is not recovery.
- LIM-003/004/005: No notifications, graphical workflow or production operational
  readiness is claimed. Use the [HTTP API guide](../testing/a4-http-api.md) and
  [limitations](known-limitations.md) to explain these boundaries.
- If the server/build fails, stop the live demo, record the failure and present
  the labeled prior transcript as historical evidence. Do not imply it is live.

**AI disclosure:** Codex corrected the original PR #96 draft against current
source and rehearsed the companion executable. The [AI-use log](../ai/ai-use-log.md)
records assistance and distinguishes automated observations from human approval.
