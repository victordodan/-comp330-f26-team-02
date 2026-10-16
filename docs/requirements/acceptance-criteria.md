# Acceptance Criteria

**Team:** Codex Ramblers  
**Project:** CampusConnect  
**Current Phase:** A3 — Architecture and Review  
**Release Cycle:** Cycle 1

## Acceptance Criteria

| ID | Requirement Reference | Acceptance Criterion | Verification Method | Status |
|---|---|---|---|---|
| AC-REQ-001-01 | REQ-001 | Given a selected demo student and valid synthetic title, category, and description, when submitted, then the system creates a request with status Submitted and confirms its identifier. | Automated test / demonstration | Proposed |
| AC-REQ-001-02 | REQ-001 | Given missing, whitespace-only, out-of-range, or unsupported input under the stated field rules, when submitted, then the system explains the validation failure and creates no request. | Automated test / demonstration | Proposed |
| AC-REQ-002-01 | REQ-002 | Given a student requester with a previously submitted request, when the requester views it, then the system displays its current stored status, including changes after reopening. | Automated test / demonstration | Proposed |
| AC-REQ-002-02 | REQ-002 | Given saved note or resolution information, when the owning student views the request, then the system displays its current text, including reviewer edits and text preserved on reopening. | Automated test / demonstration | Proposed |
| AC-REQ-002-03 | REQ-002 | Given Student A's request, when Student B lists their own requests or directly attempts to view A's request, then no contents of A's request are returned. Unknown identifiers expose no unrelated records. | Automated test / demonstration | Proposed |
| AC-REQ-003-01 | REQ-003 | Given a successful submission, when it completes, then the request is stored with a unique identifier, selected requester identity, and system-managed timestamps. A failed write must not produce a success confirmation. | Automated test / demonstration | Proposed |
| AC-REQ-003-02 | REQ-003 | Given a request with saved status and note changes, when the application restarts against the same local store, then its identifier, owner, content, status, and note remain available after reselecting the demo user. | Automated test / demonstration | Proposed |
| AC-REQ-004-01 | REQ-004 | Given stored requests from both demo students, when Reviewer lists and inspects requests, then both students' requests are available for review. | Automated test / demonstration | Proposed |
| AC-REQ-004-02 | REQ-004 | Given a student identity, when reviewer listing, status-change, or note-edit operations are attempted directly, then access is rejected without exposing data or changing records. Missing or invalid demo context is also rejected; form values cannot grant reviewer permission. | Automated test / demonstration | Proposed |
| AC-REQ-005-01 | REQ-005 | Given a stored request and Reviewer, when a permitted transition is requested, then the new status is saved and visible to its student. Reopening changes Resolved to In Progress and preserves the request ID and note; resolving requires saved nonblank text. | Automated test / demonstration | Proposed |
| AC-REQ-005-02 | REQ-005 | Given an unsupported status/transition, missing resolution text when resolving, unknown request ID, or failed status write, when an update is attempted, then it is rejected without a false success confirmation or changes to stored records. | Automated test / demonstration | Proposed |
| AC-REQ-006-01 | REQ-006 | Given a stored request and Reviewer, when a valid note or replacement is saved, then its current text is stored with the correct request and visible to its student. Reject text beyond 2,000 trimmed characters, clearing while Resolved, missing requests, and failed writes without false success or changing records. | Automated test / demonstration | Proposed |

## Traceability

| Requirement | Acceptance Criteria |
|---|---|
| REQ-001 | AC-REQ-001-01, AC-REQ-001-02 |
| REQ-002 | AC-REQ-002-01, AC-REQ-002-02, AC-REQ-002-03 |
| REQ-003 | AC-REQ-003-01, AC-REQ-003-02 |
| REQ-004 | AC-REQ-004-01, AC-REQ-004-02 |
| REQ-005 | AC-REQ-005-01, AC-REQ-005-02, AC-REQ-004-02 |
| REQ-006 | AC-REQ-006-01, AC-REQ-004-02 |

## Verification Status

At A3, these 12 proposed criteria define the intended observable behavior for Cycle 1. The original seven IDs are retained, with five additions covering invalid input, requester ownership, restart persistence, permissions, and invalid status changes. Detailed boundary and failure scenarios belong in later test cases; they do not each require another acceptance criterion.

These are planned checks, not executed results. All criteria remain Proposed pending baseline review. Verification references will be strengthened as construction proceeds. Test permissions through application operations as well as the UI. The test strategy from merged PR #44 and the twelve-case catalog from merged PR #56 provide planned traceability; confirm their alignment with central planning during final A3 review.

Criteria should not be marked **Verified** until repository-visible evidence demonstrates that the behavior has been satisfied.

Draft behavior resolutions and remaining architecture questions are tracked in
`docs/requirements/assumptions-open-questions.md`.
