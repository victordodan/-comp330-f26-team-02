# CampusConnect local HTTP API

This increment exposes the request workflow through a Java 17 Spring Boot API. Use synthetic data only. The demo selector deliberately permits switching among fixed Student A, Student B, and Reviewer identities; it is not institutional authentication or a production privacy control.

## Run and select a demo identity

After the build/application/model/service prerequisites are integrated, run from the repository root:

```sh
mvn -B clean verify
mvn spring-boot:run
```

The application binds to `http://127.0.0.1:8080`. Select an identity and retain its session cookie:

```sh
curl -sS -c /tmp/campusconnect-demo.cookies \
  -H 'Content-Type: application/json' \
  -d '{"identity":"student-a"}' \
  http://127.0.0.1:8080/api/demo/identity
```

The other fixed identifiers are `student-b` and `reviewer`. Only a validated selector writes this identity into the server session. Missing, invalid, or forged context is rejected. `X-User-ID`, `X-User-Role`, and request-body `studentID` cannot establish permission or ownership.

## Routes and permissions

| Method and route | Required context | Behavior |
|---|---|---|
| POST `/api/demo/identity` | Local demo selector | Validate a fixed identity and save it in session |
| POST `/api/requests` | Student | Validate fields, assign the session student as owner, and create a Submitted request |
| GET `/api/requests` | Student | List only that student's records |
| GET `/api/requests/{id}` | Owning student | Return current state and ETag; another student's record is unavailable |
| GET `/api/reviewer/requests` | Reviewer | List both students' requests |
| GET `/api/reviewer/requests/{id}` | Reviewer | Inspect one request and its ETag |
| PATCH `/api/requests/{id}/status` | Reviewer | Apply a permitted status transition with an expected version |
| PATCH `/api/requests/{id}/note` | Reviewer | Save, replace, or validly clear note text with an expected version |

These HTTP paths are implementation choices for the existing logical contracts. Service-side role and ownership checks remain authoritative. Reviewers use reviewer operations rather than student-only retrieval.

Submit only the client-editable creation fields:

```sh
curl -i -b /tmp/campusconnect-demo.cookies \
  -H 'Content-Type: application/json' \
  -d '{"title":"WiFi issue","category":"IT","description":"Campus WiFi is not connecting."}' \
  http://127.0.0.1:8080/api/requests
```

The response contains the generated identifier, server-assigned `studentID`, status, timestamps, and version. The existing create DTO retains its legacy `studentID` component, but this API deliberately ignores that value and assigns the owner from the validated session. Omit `studentID` when submitting. Extra lifecycle/role JSON fields may be rejected or ignored by binding configuration; they never grant permission or replace the session owner.

## Status, notes, and concurrent edits

Create, detail, and update responses expose a strong ETag such as `"0"`. Retrieve the current record, then send that exact quoted tag in `If-Match` on a mutation. A successful mutation returns a new ETag/version. Reload after a stale response before attempting another save.

For example, after selecting Reviewer and replacing `<request-id>` with the returned identifier:

```sh
curl -i -b /tmp/campusconnect-demo.cookies \
  -X PATCH -H 'Content-Type: application/json' -H 'If-Match: "0"' \
  -d '{"status":"In Progress"}' \
  http://127.0.0.1:8080/api/requests/<request-id>/status
```

Only these transitions are permitted:

- Submitted → In Progress.
- In Progress → Resolved, requiring already-saved nonblank resolution text.
- Resolved → In Progress, preserving the existing resolution text.

Save notes separately with `{ "resolutionNote": "Saved resolution text" }` at the note endpoint. Text is trimmed and limited to 2,000 characters. Clearing is permitted in Submitted or In Progress and forbidden while Resolved. Nonblank replacement while Resolved is permitted.

The earlier `UpdateStatusDto` retains its optional `resolutionNote` component for compatibility, but this status endpoint rejects a non-null note payload. It does not silently apply a combined status-and-note change. Save the note through the note endpoint, use the returned ETag, and then resolve. This preserves the saved-note-before-resolution contract.

The service compares the caller-loaded version atomically during both status and note writes. Rejected input, denied access, stale versions, and failed storage must preserve stored records and must not return a success confirmation.

## Responses

| Result | HTTP status |
|---|---:|
| Created request | 201 |
| Successful read or update | 200 |
| Invalid JSON, fields, transition, or precondition syntax | 400 |
| Missing/invalid identity or disallowed role | 403 |
| Unknown request or unavailable non-owned student detail | 404 |
| Stale `If-Match` version | 412 |
| Missing `If-Match` | 428 |
| Storage failure | 503 |

Workflow errors use a `code` and an `errors` array. Unknown identifiers and unauthorized student detail access do not expose another student's contents. Exact HTTP codes implement these contracts; they are not separate assignment-mandated codes.

## Test and assignment evidence

`src/test/java/edu/loyola/campusconnect/controller/RequestControllerTest.java` exercises JSON binding, ownership and role denial, forged headers/body fields, validation with no writes, permitted/invalid transitions, saved-note rules, note limits, reopening preservation, optimistic preconditions, missing records, malformed input, and storage failure without false success. Tests use the real workflow/repository with an isolated H2 database. Passing results must be linked from the final reviewed revision; test source alone is not a passing result.

Traceability: REQ-001/003 creation, REQ-002 own reads, REQ-004 reviewer operations, REQ-005 status changes, REQ-006 notes; TC-001 through TC-012 and the optimistic-update design in ADR-003 provide the planned verification context.

Server-rendered forms, Post/Redirect/Get, institutional login, and the separate backup/recovery exercise are not implemented by this API PR. Final architecture/acceptance approval and human review remain separate evidence gates.
