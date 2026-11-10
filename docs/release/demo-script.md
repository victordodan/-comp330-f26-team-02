# CampusConnect — Cycle 1 Release Demo Script

> **Release Baseline Tag:** `a5-cycle1-release`
> **Target Base URL:** `http://localhost:8080`
> **Persistence Architecture:** JDBC + File-Backed H2 Persistence
> **Note on Authentication:** `/api/demo/identity` is a synthetic session endpoint for local test simulation (`LIM-002`), not a production OAuth2/SSO provider.

Each step shows the raw HTTP request and expected response. `{request-uuid}` denotes the UUID captured from the Step 1b response; `{session-cookie}` denotes the cookie set by `/api/demo/identity`.

---

## Part 1: Controlled Main Path (Happy Path)

### Step 1a: Establish Student Session

```http
POST /api/demo/identity HTTP/1.1
Host: localhost:8080
Content-Type: application/json

{
  "identity": "student-a"
}
```

```http
HTTP/1.1 200 OK
Set-Cookie: SESSION=...; Path=/; HttpOnly
```

> Session cookie is stored by the client (cookie jar in the script) and attached to all subsequent requests.

### Step 1b: Student Submits Support Request

```http
POST /api/requests HTTP/1.1
Host: localhost:8080
Cookie: SESSION=...
Content-Type: application/json

{
  "title": "Need help with course registration",
  "category": "Academic",
  "description": "Unable to register for COMP 330 due to prerequisite override requirement."
}
```

```http
HTTP/1.1 201 Created
Content-Type: application/json
ETag: "1"

{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "title": "Need help with course registration",
  "category": "Academic",
  "description": "Unable to register for COMP 330 due to prerequisite override requirement.",
  "status": "Submitted",
  "note": null
}
```

> `id` is a server-generated UUID — capture it for subsequent steps. `ETag: "1"` is the initial version used for the first `If-Match`. Ownership is bound to the `student-a` session; `studentID` is not sent in the body.

### Step 2: Student Retrieves Requests (Privacy Isolation)

```http
GET /api/requests HTTP/1.1
Host: localhost:8080
Cookie: SESSION=...
```

```http
HTTP/1.1 200 OK
Content-Type: application/json

[
  {
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "title": "Need help with course registration",
    "category": "Academic",
    "description": "Unable to register for COMP 330 due to prerequisite override requirement.",
    "status": "Submitted",
    "note": null
  }
]
```

> Response contains only tickets owned by `student-a` (`REQ-002`).

### Step 3a: Switch to Reviewer Session

```http
POST /api/demo/identity HTTP/1.1
Host: localhost:8080
Content-Type: application/json

{
  "identity": "reviewer"
}
```

```http
HTTP/1.1 200 OK
Set-Cookie: SESSION=...; Path=/; HttpOnly
```

> Session cookie is replaced with the `reviewer` identity.

### Step 3b: Reviewer Resolves Ticket (Optimistic Concurrency)

```http
PATCH /api/requests/550e8400-e29b-41d4-a716-446655440000/status HTTP/1.1
Host: localhost:8080
Cookie: SESSION=...
If-Match: "1"
Content-Type: application/json

{
  "status": "Resolved"
}
```

```http
HTTP/1.1 200 OK
Content-Type: application/json
ETag: "2"

{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "status": "Resolved"
}
```

> Status transitions to `"Resolved"`; the server advances the version. The status body carries `status` only — a resolution note in this body is rejected by the controller.

### Step 3c: Record Resolution Note

```http
PATCH /api/requests/550e8400-e29b-41d4-a716-446655440000/note HTTP/1.1
Host: localhost:8080
Cookie: SESSION=...
Content-Type: application/json

{
  "note": "Prerequisite override granted in registration system."
}
```

```http
HTTP/1.1 200 OK
Content-Type: application/json
```

> Notes are authored via the dedicated note endpoint, not the status endpoint.

---

## Part 2: Risk & Boundary Paths (Negative Verification)

### Boundary Case 1: Blank Resolution Note (Validation Rule)

```http
PATCH /api/requests/550e8400-e29b-41d4-a716-446655440000/note HTTP/1.1
Host: localhost:8080
Cookie: SESSION=...
Content-Type: application/json

{
  "note": ""
}
```

```http
HTTP/1.1 400 Bad Request
```

> Blank note is rejected by payload validation.

### Boundary Case 2: Student Status Tampering (Role Protection)

Step 1 — switch back to the student session:

```http
POST /api/demo/identity HTTP/1.1
Host: localhost:8080
Content-Type: application/json

{
  "identity": "student-a"
}
```

```http
HTTP/1.1 200 OK
Set-Cookie: SESSION=...; Path=/; HttpOnly
```

Step 2 — read the current version for this ticket:

```http
GET /api/requests/550e8400-e29b-41d4-a716-446655440000 HTTP/1.1
Host: localhost:8080
Cookie: SESSION=...
```

```http
HTTP/1.1 200 OK
Content-Type: application/json
ETag: "2"
```

> The ETag is read at runtime — the script re-fetches it rather than assuming a version, so the test is unaffected by how many prior mutations bumped it.

Step 3 — attempt a status update as a student:

```http
PATCH /api/requests/550e8400-e29b-41d4-a716-446655440000/status HTTP/1.1
Host: localhost:8080
Cookie: SESSION=...
If-Match: "2"
Content-Type: application/json

{
  "status": "Closed"
}
```

```http
HTTP/1.1 403 Forbidden
```

> Role check rejects the student session despite a current `If-Match` (`LIM-002`).

### Boundary Case 3: Non-Existent Ticket Access

```http
PATCH /api/requests/00000000-0000-0000-0000-000000000000/status HTTP/1.1
Host: localhost:8080
Cookie: SESSION=...
Content-Type: application/json

{
  "status": "Resolved"
}
```

```http
HTTP/1.1 404 Not Found
```

> `If-Match` is deliberately omitted so resource presence is evaluated before the version check; the valid-format UUID does not exist.
