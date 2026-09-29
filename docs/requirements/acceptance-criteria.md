# Acceptance Criteria

**Team:** Codex Ramblers  
**Project:** CampusConnect  
**Current Phase:** A2 — Planning and Estimation  
**Release Cycle:** Cycle 1

## Acceptance Criteria

| ID | Requirement Reference | Acceptance Criterion | Verification Method | Status |
|---|---|---|---|---|
| AC-REQ-001-01 | REQ-001 | Given a student requester and valid required request information, when the requester submits the support request, then the system accepts the submission for processing. | Automated test / demonstration | Proposed |
| AC-REQ-001-02 | REQ-001 | Given a support request missing required information, when submission is attempted, then the system rejects the submission and identifies that required information is missing. | Automated test / demonstration | Proposed |
| AC-REQ-002-01 | REQ-002 | Given a successfully submitted support request, when the submission completes, then the request is stored and assigned a unique identifier. | Automated integration test / demonstration | Proposed |
| AC-REQ-003-01 | REQ-003 | Given one or more stored support requests, when a support reviewer views available requests, then the submitted requests are available for review. | Automated integration test / demonstration | Proposed |
| AC-REQ-004-01 | REQ-004 | Given a stored support request, when a support reviewer changes the request to a supported Cycle 1 status, then the new status is stored as the request's current status. | Automated integration test / demonstration | Proposed |
| AC-REQ-005-01 | REQ-005 | Given a stored support request, when a support reviewer records a note or resolution, then that information is saved with the request and remains available afterward. | Automated integration test / demonstration | Proposed |
| AC-REQ-006-01 | REQ-006 | Given a student requester with a previously submitted request, when the requester views that request, then the system displays its current status. | Automated end-to-end test / demonstration | Proposed |
| AC-REQ-006-02 | REQ-006 | Given a student request for which resolution information has been recorded, when the requester views the request, then the recorded resolution information is displayed. | Automated end-to-end test / demonstration | Proposed |

## Traceability

| Requirement | Acceptance Criteria |
|---|---|
| REQ-001 | AC-REQ-001-01, AC-REQ-001-02 |
| REQ-002 | AC-REQ-002-01 |
| REQ-003 | AC-REQ-003-01 |
| REQ-004 | AC-REQ-004-01 |
| REQ-005 | AC-REQ-005-01 |
| REQ-006 | AC-REQ-006-01, AC-REQ-006-02 |

## Verification Status

At A2, these acceptance criteria define the intended observable behavior for Cycle 1.

Implementation and verification evidence do not yet exist. Verification references will be strengthened with concrete test and implementation evidence as construction proceeds.

Criteria should not be marked **Verified** until repository-visible evidence demonstrates that the behavior has been satisfied.