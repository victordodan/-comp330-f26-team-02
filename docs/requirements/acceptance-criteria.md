# Acceptance Criteria

**Team:** Codex Ramblers  
**Project:** CampusConnect  
**Current Phase:** A2 — Planning and Estimation  
**Release Cycle:** Cycle 1

## Acceptance Criteria

| ID | Requirement Reference | Acceptance Criterion | Verification Method | Status |
|---|---|---|---|---|
| AC-REQ-001-01 | REQ-001 | Given a student requester and synthetic request data, when the requester submits a new support request, then the system creates the request for processing. | Automated test / demonstration | Proposed |
| AC-REQ-002-01 | REQ-002 | Given a student requester with a previously submitted request, when the requester views that request, then the system displays its current status. | Automated test / demonstration | Proposed |
| AC-REQ-002-02 | REQ-002 | Given a request for which resolution information has been recorded, when the student requester views that request, then the recorded resolution information is displayed. | Automated test / demonstration | Proposed |
| AC-REQ-003-01 | REQ-003 | Given a successfully submitted support request, when submission completes, then the request is stored and assigned a unique identifier. | Automated test / demonstration | Proposed |
| AC-REQ-004-01 | REQ-004 | Given one or more stored support requests, when a support reviewer views available requests, then the submitted requests are available for review. | Automated test / demonstration | Proposed |
| AC-REQ-005-01 | REQ-005 | Given a stored support request, when a support reviewer changes the request to a supported Cycle 1 status, then the selected status becomes the request's current status. | Automated test / demonstration | Proposed |
| AC-REQ-006-01 | REQ-006 | Given a stored support request, when a support reviewer records a short note or resolution, then that information is saved with the request and remains available for later viewing. | Automated test / demonstration | Proposed |

## Traceability

| Requirement | Acceptance Criteria |
|---|---|
| REQ-001 | AC-REQ-001-01 |
| REQ-002 | AC-REQ-002-01, AC-REQ-002-02 |
| REQ-003 | AC-REQ-003-01 |
| REQ-004 | AC-REQ-004-01 |
| REQ-005 | AC-REQ-005-01 |
| REQ-006 | AC-REQ-006-01 |

## Verification Status

At A2, these acceptance criteria define the intended observable behavior for Cycle 1.

Implementation and verification evidence do not yet exist. Verification references will be strengthened with concrete test and implementation evidence as construction proceeds.

Criteria should not be marked **Verified** until repository-visible evidence demonstrates that the behavior has been satisfied.

Unresolved requirement behavior and open questions are tracked in
`docs/requirements/assumptions-open-questions.md`.