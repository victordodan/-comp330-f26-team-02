# Requirements

**Team:** Codex Ramblers  
**Project:** CampusConnect  
**Current Phase:** A2 — Planning and Estimation  
**Release Cycle:** Cycle 1

## Requirements Baseline

CampusConnect Cycle 1 is limited to one end-to-end student support-request workflow.

The system will allow a student requester to submit a support request using synthetic data. The request will be stored and assigned a unique identifier. A support reviewer will be able to view the request, update its status, and record a note or resolution. The student will then be able to view the current status and resolution information for their request.

| ID | Requirement | Rationale | Priority | Acceptance Criteria Reference | Status |
|---|---|---|---|---|---|
| REQ-001 | The system shall allow a student requester to submit a support request containing the information required for Cycle 1 processing. | Students need a controlled way to initiate the support-request workflow. | Must | AC-REQ-001-01 | Proposed |
| REQ-002 | The system shall allow a student requester to view the current status and available resolution information for a request they submitted. | Students need visibility into the progress and outcome of their support requests. | Must | AC-REQ-002-01, AC-REQ-002-02 | Proposed |
| REQ-003 | The system shall store each successfully submitted support request and assign it a unique identifier. | Requests must remain identifiable and available for later review and tracking. | Must | AC-REQ-003-01 | Proposed |
| REQ-004 | The system shall allow a support reviewer to view submitted support requests. | Reviewers must be able to inspect submitted requests before taking workflow actions. | Must | AC-REQ-004-01 | Proposed |
| REQ-005 | The system shall allow a support reviewer to change the current status of a support request. | Request status must reflect the current point in the review workflow. | Must | AC-REQ-005-01 | Proposed |
| REQ-006 | The system shall allow a support reviewer to record a short note or resolution for a support request. | The result of reviewing a request must be preserved and available to the requester. | Must | AC-REQ-006-01 | Proposed |


## Cycle 1 Constraints

The requirements above are subject to the following project constraints:

- Only synthetic or made-up data will be used.
- Student requester and support reviewer behavior must remain distinguishable, even if roles are simulated.
- Cycle 1 will use a small and manageable request-status model.
- CampusConnect will not depend on live Loyola systems or institutional integrations.
- Real Loyola authentication or single sign-on is not required for Cycle 1.
- Real email or text-message integration is outside the required Cycle 1 workflow.
- User-facing AI is optional and, if introduced later, must remain bounded, reviewable, and human-controlled.
- No live institutional integration is required.
- External APIs or services require instructor approval and documented risk review before use.
- The team will prioritize a complete end-to-end workflow over additional features.

## Requirement Quality

The Cycle 1 requirements describe observable system behavior rather than prescribing implementation technologies.

Each requirement:

- represents a capability needed for the required support-request workflow;
- has a stable requirement identifier;
- has corresponding acceptance criteria;
- is intended to be independently reviewable and verifiable; and
- avoids introducing functionality outside the agreed Cycle 1 scope.

Unresolved details that affect these requirements are maintained in `assumptions-open-questions.md`.

## Requirements and Acceptance Criteria

Acceptance criteria for each requirement are maintained in:

`docs/requirements/acceptance-criteria.md`

Requirement and acceptance-criteria identifiers should remain stable once accepted. If an accepted requirement changes, the affected planning, traceability, estimates, risks, tasks, and later verification evidence must be reviewed.