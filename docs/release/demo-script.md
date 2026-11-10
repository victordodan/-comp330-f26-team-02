# CampusConnect — Cycle 1 Release Demo Script

> **Release Baseline Tag:** `a5-cycle1-release`
> **Target Base URL:** `http://localhost:8080`
> **Date:** 2026-11-09

This script documents the step-by-step REST API demonstration sequence for the Cycle 1 release presentation. Each step shows the raw HTTP request and the expected HTTP response.

---

## Part 1: Controlled Main Path (Happy Path)

### Step 1: Student Submits Support Request

**HTTP Request**

```http
POST /api/requests HTTP/1.1
Host: localhost:8080
Content-Type: application/json

{
  "studentID": "student-101",
  "title": "Need help with course registration",
  "category": "Academic",
  "description": "Unable to register for COMP 330 due to prerequisite override requirement."
}
```

**Expected HTTP Response**

```http
HTTP/1.1 201 Created
Content-Type: application/json

{
  "id": "REQ-1001",
  "studentID": "student-101",
  "title": "Need help with course registration",
  "category": "Academic",
  "description": "Unable to register for COMP 330 due to prerequisite override requirement.",
  "status": "Submitted",
  "resolutionNote": null
}
```

> Ticket is assigned ID `REQ-1001` with default status `"Submitted"`.

### Step 2: Student Retrieves Requests (Privacy Isolation — REQ-002)

**HTTP Request**

```http
GET /api/requests HTTP/1.1
Host: localhost:8080
X-User-ID: student-101
X-User-Role: STUDENT
```

**Expected HTTP Response**

```http
HTTP/1.1 200 OK
Content-Type: application/json

[
  {
    "id": "REQ-1001",
    "studentID": "student-101",
    "title": "Need help with course registration",
    "category": "Academic",
    "description": "Unable to register for COMP 330 due to prerequisite override requirement.",
    "status": "Submitted",
    "resolutionNote": null
  }
]
```

> Response contains only tickets belonging to `student-101`.

### Step 3: Support Reviewer Triages & Resolves Ticket

**HTTP Request**

```http
PATCH /api/requests/REQ-1001/status HTTP/1.1
Host: localhost:8080
X-User-ID: reviewer-1
X-User-Role: REVIEWER
Content-Type: application/json

{
  "status": "Resolved",
  "resolutionNote": "Prerequisite override granted in registration system."
}
```

**Expected HTTP Response**

```http
HTTP/1.1 200 OK
Content-Type: application/json

{
  "id": "REQ-1001",
  "studentID": "student-101",
  "title": "Need help with course registration",
  "category": "Academic",
  "description": "Unable to register for COMP 330 due to prerequisite override requirement.",
  "status": "Resolved",
  "resolutionNote": "Prerequisite override granted in registration system."
}
```

> Ticket status is now `"Resolved"` and the resolution note is recorded.

---

## Part 2: Risk & Boundary Paths (Negative Verification)

### Boundary Case 1: Student Tampers with Status (Role Protection)

**HTTP Request**

```http
PATCH /api/requests/REQ-1001/status HTTP/1.1
Host: localhost:8080
X-User-ID: student-101
X-User-Role: STUDENT
Content-Type: application/json

{
  "status": "Resolved",
  "resolutionNote": "Self-resolving"
}
```

**Expected HTTP Response**

```http
HTTP/1.1 403 Forbidden
```

> Non-reviewer role is rejected from status updates (`LIM-002`).

### Boundary Case 2: Resolve with Blank Resolution Note (Audit Rule)

**HTTP Request**

```http
PATCH /api/requests/REQ-1001/status HTTP/1.1
Host: localhost:8080
X-User-ID: reviewer-1
X-User-Role: REVIEWER
Content-Type: application/json

{
  "status": "Resolved",
  "resolutionNote": ""
}
```

**Expected HTTP Response**

```http
HTTP/1.1 400 Bad Request
```

> Resolving a ticket requires a non-blank resolution note (audit rule).

### Boundary Case 3: Update Non-Existent Ticket ID

**HTTP Request**

```http
PATCH /api/requests/REQ-9999/status HTTP/1.1
Host: localhost:8080
X-User-ID: reviewer-1
X-User-Role: REVIEWER
Content-Type: application/json

{
  "status": "Resolved",
  "resolutionNote": "Testing non-existent ticket"
}
```

**Expected HTTP Response**

```http
HTTP/1.1 404 Not Found
```

> Unknown ticket ID returns 404 Not Found.
