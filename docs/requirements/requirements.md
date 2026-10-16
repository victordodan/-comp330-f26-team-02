# Requirements

**Team:** Codex Ramblers  
**Project:** CampusConnect  
**Current Phase:** A3 — Architecture and Review  
**Release Cycle:** Cycle 1

## Requirements Baseline

CampusConnect Cycle 1 is limited to one end-to-end student support-request workflow.

The system will allow a student requester to submit a support request using synthetic data. The request will be stored and assigned a unique identifier. A support reviewer will be able to view the request, update its status, and record a note or resolution. The student will then be able to view the current status and resolution information for their request.

The following A3 behavior decisions form the documented **proposed Cycle 1 baseline**. They are ready for explicit requirements-owner and team-lead acceptance at the final A3 gate; a merged PR alone does not prove that acceptance or implementation verification occurred.

Requests require a title (5–100 characters), category (Academic, IT, Campus Services, or Other), and description (10–2,000 characters). Trim surrounding whitespace before validation and storage; limits are inclusive. These categories and limits are proposed defaults. The application assigns the request ID, selected student identity, creation/last-updated timestamps, and initial status Submitted. Students cannot edit or delete submitted requests.

Only reviewers change status: Submitted → In Progress, In Progress → Resolved, or Resolved → In Progress. Other transitions and unsupported values are rejected. Resolution requires a saved nonblank note/resolution. Reopening preserves the existing text. The field is student-visible, editable only by reviewers, and limited to 2,000 characters after trimming. It may be empty in Submitted or In Progress, but cannot be cleared while Resolved. Replacing it updates the current value; comment threads and edit history are outside Cycle 1.

A demo selector provides Student A, Student B, and Reviewer, storing the selected identity in the server session. The application checks role and ownership on every operation: only students submit new requests and view their own requests; Reviewer uses the review view to inspect all requests and make reviewer updates. Missing/invalid context is rejected. Form-supplied roles or requester IDs cannot override session context. Switching identity does not change stored ownership. The selector intentionally permits demo impersonation and is not real authentication.

Successful storage must precede success confirmation. Rejected operations leave records unchanged; failed writes must not be reported as successful. A failed request listing must be distinguishable from an empty list. Request data and stable requester identities survive ordinary application restarts. Successful updates preserve request ID, owner, and creation time while updating last-updated time. Unknown identifiers and unauthorized access must not expose another student's request data.

| ID | Requirement | Rationale | Priority | Acceptance Criteria Reference | Status |
|---|---|---|---|---|---|
| REQ-001 | The system shall allow a student requester to submit a support request with a valid title, category, and description. | Students need a controlled way to initiate the support-request workflow. | Must | AC-REQ-001-01, AC-REQ-001-02 | Proposed |
| REQ-002 | The system shall allow a student requester to view only their own requests, including current status and available note or resolution information. | Students need visibility into the progress and outcome of their support requests. | Must | AC-REQ-002-01, AC-REQ-002-02, AC-REQ-002-03 | Proposed |
| REQ-003 | The system shall persist each successfully submitted support request and assign it a unique identifier, retaining its data across application restarts. | Requests must remain identifiable and available for later review and tracking. | Must | AC-REQ-003-01, AC-REQ-003-02 | Proposed |
| REQ-004 | The system shall allow a support reviewer to view submitted support requests and reject student access to reviewer operations. | Reviewers must be able to inspect submitted requests before taking workflow actions. | Must | AC-REQ-004-01, AC-REQ-004-02 | Proposed |
| REQ-005 | The system shall allow only a support reviewer to change status using the permitted transitions, including reopening; resolution requires nonblank resolution text. | Request status must reflect the current point in the review workflow. | Must | AC-REQ-005-01, AC-REQ-005-02, AC-REQ-004-02 | Proposed |
| REQ-006 | The system shall allow only a support reviewer to save or replace one student-visible note or resolution per request under the stated editing rules. | The result of reviewing a request must be preserved and available to the requester. | Must | AC-REQ-006-01, AC-REQ-004-02 | Proposed |


## Cycle 1 Constraints

The requirements above are subject to the following project constraints:

- Only synthetic or made-up data will be used.
- Java with server-rendered pages, run locally for the class demonstration.
- No paid hosting, paid runtime services, or required cloud deployment.
- Student requester and support reviewer behavior must remain distinguishable, even if roles are simulated.
- Cycle 1 will use a small and manageable request-status model.
- CampusConnect will not depend on live Loyola systems or institutional integrations.
- Real Loyola authentication or single sign-on is not required for Cycle 1.
- Real email or text-message integration is outside the required Cycle 1 workflow.
- User-facing AI is excluded from the current baseline; any later addition requires scope and architecture review.
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

Draft resolutions and remaining architecture decisions are maintained in `assumptions-open-questions.md`. PR #43 and PR #44 are merged; the final cross-document review must confirm consistency with their architecture and testing artifacts. Any remaining differences must be recorded before A3 approval.

## Requirements and Acceptance Criteria

Acceptance criteria for each requirement are maintained in:

`docs/requirements/acceptance-criteria.md`

Requirement and acceptance-criteria identifiers should remain stable once accepted. If an accepted requirement changes, the affected planning, traceability, estimates, risks, tasks, and later verification evidence must be reviewed.
