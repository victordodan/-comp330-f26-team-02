
# API and Tool Boundary Contracts

**Project:** CampusConnect  
**Team:** Team 2 — Codex Ramblers  
**Assignment:** A3 — Cycle 1 Architecture and Review  
**Status:** Proposed — aligned with PR #46 draft requirements; pending team architecture review

## 1. Purpose and Scope

This document defines the significant logical interactions between CampusConnect components for the Cycle 1 student support-request workflow.

It covers student submission, student-owned request retrieval, reviewer access, status changes, reviewer notes or resolutions, persistence, and simulated role context.

These contracts describe proposed component boundaries and observable behavior. They do not claim that HTTP endpoints, database schemas, or working implementations have been approved or verified.

Cycle 1 uses synthetic data, Java with server-rendered pages, and a local class demonstration. Real Loyola authentication, institutional integrations, notifications, paid services, and user-facing AI are outside the required workflow.

The detailed behavior reflects the draft resolutions in PR #46. Those decisions remain subject to team review and are not implementation evidence.

## 2. Contract Summary

| ID | Consumer | Provider | Interaction | Requirements | Status |
|---|---|---|---|---|---|
| API-001 | Requester UI | Request Workflow Service | Submit support request | REQ-001, REQ-003 | Proposed |
| API-002 | Requester UI | Request Workflow Service | Retrieve student-owned requests | REQ-002 | Proposed |
| API-003 | Reviewer UI | Request Workflow Service | List and inspect requests | REQ-004 | Proposed |
| API-004 | Reviewer UI | Request Workflow Service | Update request status | REQ-005 | Proposed |
| API-005 | Reviewer UI | Request Workflow Service | Save or replace note or resolution | REQ-006 | Proposed |
| API-006 | Request Workflow Service | Request Repository | Persist and retrieve records | REQ-001 through REQ-006 | Proposed |
| API-007 | Request Workflow Service | Simulated Role Context | Obtain session role and identity | REQ-001 through REQ-006 | Proposed |

The contracts describe logical interfaces. Specific HTTP paths, request/response schemas, framework choices, and database technology remain subject to accepted architecture decisions.

## 3. Shared Contract Rules

### Request data and validation

A valid Cycle 1 request requires:

- Title: 5–100 characters after trimming surrounding whitespace.
- Category: Academic, IT, Campus Services, or Other.
- Description: 10–2,000 characters after trimming surrounding whitespace.

The limits are inclusive. Missing, whitespace-only, out-of-range, or unsupported values must be rejected.

The application assigns:

- A unique request identifier.
- The selected student requester identity.
- Creation and last-updated timestamps.
- Initial status `Submitted`.

Students cannot edit or delete submitted requests.

These field limits and categories are draft defaults established by Q-001.

### Status and transition rules

The proposed status model contains exactly three values:

- `Submitted`
- `In Progress`
- `Resolved`

Permitted reviewer transitions are:

| Current Status | New Status | Condition |
|---|---|---|
| Submitted | In Progress | Reviewer authorization |
| In Progress | Resolved | Reviewer authorization and saved nonblank resolution text |
| Resolved | In Progress | Reviewer authorization; preserve existing note or resolution |

All other status transitions and unsupported status values are rejected.

Only Reviewer may change request status. Reopening preserves the request identifier, owner, and existing note or resolution.

These are the draft rules established by Q-002.

### Reviewer note and resolution rules

Each request has one current student-visible note or resolution field.

- Reviewer may save or replace the field.
- Maximum length is 2,000 characters after trimming.
- The field may be empty while status is `Submitted` or `In Progress`.
- Clearing the field while status is `Resolved` is prohibited.
- Moving a request to `Resolved` requires saved nonblank resolution text.
- Reopening preserves the current text.
- Comment threads and edit history are outside Cycle 1 scope.

These are the draft rules established by Q-003.

### Role and ownership rules

The proposed demo selector provides three identities:

- Student A
- Student B
- Reviewer

The selected identity is stored in the server session.

- Only students may submit requests.
- Students may view only their own requests.
- Reviewer may list and inspect requests from both students.
- Only Reviewer may change statuses or save notes.
- Every operation must enforce role and ownership rules in the application.
- Form-supplied roles or requester identifiers cannot override session context.
- Missing or invalid session context must be rejected.
- Switching the selected demo identity must not change stored request ownership.

The selector intentionally permits demo impersonation. It is not real authentication or institutional identity verification.

Stable stored requester identities allow request ownership to be recovered after application restart and demo-user reselection.

These are the draft rules established by Q-004.

### Persistence and consistency rules

- Successfully submitted requests receive unique identifiers.
- Stored request data must survive ordinary application restarts against the same local store.
- Request identifier, owner, and creation timestamp remain unchanged during updates.
- Successful updates change the last-updated timestamp.
- Successful storage must occur before success confirmation.
- Failed writes must not be reported as successful.
- Rejected operations must leave stored records unchanged.
- Unknown identifiers and unauthorized access must not expose another student's request information.
- Failed retrieval or listing must be distinguishable from an empty result.
- Retry, duplicate-submission, transaction, and concurrent-update policies remain architecture decisions.

### Failure categories

Contracts distinguish:

- Invalid or missing input.
- Unsupported category, status, or transition.
- Missing request identifier or record.
- Unauthorized role or request ownership.
- Missing or invalid simulated session context.
- Failed storage, retrieval, or update.
- Unexpected service failure.

Exact protocol response codes and error schemas will be defined when the implementation architecture is accepted.

### Observability

The implementation should provide enough evidence to investigate request creation, authorized updates, rejected operations, and persistence failures.

Logs should avoid unnecessary request descriptions and other potentially sensitive content. Logging detail and retention remain subject to architecture review.

## 4. Detailed Interface Contracts

### API-001 — Submit Support Request

**Consumer:** Requester UI  
**Provider:** Request Workflow Service  
**Related requirements:** REQ-001, REQ-003  
**Acceptance criteria:** AC-REQ-001-01, AC-REQ-001-02, AC-REQ-003-01, AC-REQ-003-02  
**Status:** Proposed

**Input**

- Simulated student identity from the server session (Student A or Student B).
- Title: 5–100 characters after trimming whitespace.
- Category: Academic, IT, Campus Services, or Other.
- Description: 10–2,000 characters after trimming whitespace.

The application assigns the unique request ID, requester identity, creation and last-updated timestamps, and initial status `Submitted`. Students cannot supply or override these system-managed values. These are draft rules from Q-001 and Q-004, pending team approval.

**Successful output**

- Unique request identifier.
- Confirmation that the request was successfully stored.
- Initial status `Submitted`.
- System-managed creation and last-updated timestamps.

**Failure behavior**

- Reject missing, whitespace-only, too-short, or too-long titles and descriptions according to Q-001.
- Reject missing or unsupported categories.
- Reject submissions without a valid Student A or Student B server-session context, including submissions attempted as Reviewer.
- Do not allow form-supplied requester identities or roles to override server-session context.
- Reject invalid submissions without creating or modifying a request.
- Report persistence failures without claiming successful creation.

**Contract rules**

- Trim surrounding whitespace from title and description before validation and storage.
- Enforce the draft field limits and allowed categories defined in Q-001.
- Use the selected student identity from the server session; never trust a form-supplied requester ID or role.
- Assign a unique request identifier, creation and last-updated timestamps, and initial status `Submitted`.
- Persist the request and its ownership before confirming successful submission.
- Do not allow students to edit or delete submitted requests.
- Do not report success unless storage succeeds.
- Retry and duplicate-submission behavior remain pending architecture decisions.

**Planned verification**

- Valid title, category, and description create a stored request with a unique identifier and initial status `Submitted`.
- Test title lengths of 4, 5, 100, and 101 characters after trimming.
- Test description lengths of 9, 10, 2,000, and 2,001 characters after trimming.
- Reject missing, whitespace-only, and unsupported field values without storing a request.
- Confirm Student A and Student B can submit requests, but Reviewer cannot.
- Confirm form-supplied requester IDs and roles cannot override server-session context.
- Confirm the stored request has the correct owner and system-managed timestamps.
- Confirm a simulated persistence failure does not produce a false success confirmation.
- Confirm the created request remains available after restarting against the same local store and reselecting its owner.

### API-002 — Retrieve Requester-Owned Requests

**Consumer:** Requester UI  
**Provider:** Request Workflow Service  
**Related requirement:** REQ-002  
**Acceptance criteria:** AC-REQ-002-01, AC-REQ-002-02, AC-REQ-002-03  
**Status:** Proposed

**Input**

- Request identifier when viewing a specific request, or a request-listing operation for the selected student.
- Student A or Student B identity obtained from the server session.
- The selected student's identity must be used for ownership checks; form-supplied requester IDs or roles cannot override session context.

**Successful output**

- Only request records belonging to the selected student.
- Request identifier and stored request details.
- Current stored status (`Submitted`, `In Progress`, or `Resolved`).
- Current reviewer note or resolution text, when available.
- Updated status and note information after reviewer changes or reopening.

**Failure behavior**

- Reject access when Student A or Student B attempts to retrieve another student's request.
- Reject retrieval without a valid student identity in the server session, including attempts made as Reviewer through this student-only operation.
- Handle unknown request identifiers without exposing unrelated records or another student's information.
- Do not allow form-supplied roles or requester identities to override server-session context.
- Report storage or retrieval failures without displaying misleading request information.

**Contract rules**

- Obtain Student A or Student B identity from the server session.
- Enforce request ownership in the Request Workflow Service for every retrieval or student request-listing operation.
- Return only records belonging to the selected student; never rely solely on UI filtering.
- Do not allow form-supplied roles or requester IDs to override the session identity.
- Return the latest stored status and current note or resolution text, including reviewer edits and text preserved after reopening.
- Unknown request identifiers and unauthorized access must not expose another student's request information.
- A failed listing must be distinguishable from a successful empty listing.

**Planned verification**

- Student A retrieves their own request.
- Student B retrieves their own request.
- Student B cannot list or directly retrieve Student A's request.
- Unknown identifiers expose no unrelated records.
- Missing or invalid session context is rejected.
- Status changes appear when the owning student retrieves the request.
- Saved and replaced notes are visible to the owning student.
- Reopening preserves the existing note and updates the visible status.
- Request data remains available after an ordinary application restart and user reselection.

### API-003 — View Submitted Requests for Review

**Consumer:** Reviewer UI  
**Provider:** Request Workflow Service  
**Related requirement:** REQ-004  
**Acceptance criteria:** AC-REQ-004-01, AC-REQ-004-02  
**Status:** Proposed

**Input**

- Reviewer identity obtained from the server session.
- Optional request identifier when inspecting a specific stored request.

**Successful output**

- Stored support requests from both Student A and Student B.
- Information needed to identify and inspect requests.
- Current status and note or resolution information.

**Failure behavior**

- Reject student access to reviewer-only listing and inspection operations.
- Reject missing or invalid reviewer session context.
- Do not allow form-supplied roles to grant reviewer permissions.
- Handle missing request identifiers without exposing unrelated records.
- Distinguish an empty request list from a retrieval failure.

**Contract rules**

- Verify the Reviewer role in the Request Workflow Service.
- Use session context rather than trusting UI controls or form values.
- Permit Reviewer to inspect requests from both simulated students.
- Avoid unnecessary exposure of request data.
- Pagination and filtering are not assumed for Cycle 1.

**Planned verification**

- Reviewer lists requests submitted by both Student A and Student B.
- Reviewer can inspect a stored request.
- Students cannot access reviewer-only listing or inspection operations.
- Form-supplied reviewer role values cannot bypass authorization.
- Missing or invalid session context is rejected.
- Empty results and retrieval failures are distinguishable.

### API-004 — Update Request Status

**Consumer:** Reviewer UI  
**Provider:** Request Workflow Service  
**Related requirement:** REQ-005  
**Acceptance criteria:** AC-REQ-005-01, AC-REQ-005-02, AC-REQ-004-02  
**Status:** Proposed

**Input**

- Request identifier.
- Proposed new status.
- Reviewer identity from the server session.

**Successful output**

- Confirmation of the persisted status change.
- Updated current status.
- Updated last-updated timestamp.

**Failure behavior**

- Reject unsupported statuses and transitions.
- Reject status changes attempted by Student A or Student B.
- Reject missing or invalid reviewer session context.
- Reject changes to nonexistent requests.
- Reject transition to `Resolved` without saved nonblank resolution text.
- Report persistence failure without falsely confirming an update.
- Preserve the stored record when an operation is rejected or a write fails.

**Contract rules**

- Only Reviewer may update status.
- Permit `Submitted` to `In Progress`.
- Permit `In Progress` to `Resolved` only when nonblank resolution text has already been saved.
- Permit `Resolved` to `In Progress` for reopening.
- Reject all other transitions.
- Preserve request identifier, owner, and creation timestamp.
- Preserve the current note or resolution when reopening.
- Update last-updated time after a successful status change.
- Validate authorization and transition rules before persisting.
- Concurrent-update handling remains an unresolved architecture decision.

**Planned verification**

- Reviewer changes `Submitted` to `In Progress`.
- Reviewer changes `In Progress` to `Resolved` when saved nonblank resolution text exists.
- Reviewer reopens `Resolved` to `In Progress`.
- Reopening preserves the request ID, owner, and note or resolution.
- Students cannot change status.
- Unsupported transitions and values are rejected.
- Resolving without saved nonblank text is rejected.
- Unknown request identifiers are rejected.
- Failed persistence does not produce false success or change the stored record.
- The owning student sees the latest saved status.

### API-005 — Record or Replace Reviewer Note or Resolution

**Consumer:** Reviewer UI  
**Provider:** Request Workflow Service  
**Related requirement:** REQ-006  
**Acceptance criteria:** AC-REQ-006-01, AC-REQ-004-02  
**Status:** Proposed

**Input**

- Request identifier.
- Proposed note or resolution text.
- Reviewer identity from the server session.

**Successful output**

- Confirmation that the current note or resolution was saved.
- Current stored note or resolution text.
- Updated last-updated timestamp.

**Failure behavior**

- Reject note changes attempted by students.
- Reject missing or invalid reviewer session context.
- Reject text exceeding 2,000 characters after trimming.
- Reject clearing the note or resolution while the request is `Resolved`.
- Reject changes to nonexistent requests.
- Report persistence failures without falsely confirming that text was saved.
- Preserve existing stored content when an operation is rejected or a write fails.

**Contract rules**

- Only Reviewer may save or replace note or resolution text.
- Each request has one current student-visible note or resolution field.
- Trim text before validating and storing it.
- Allow a maximum of 2,000 characters after trimming.
- Permit empty text while status is `Submitted` or `In Progress`.
- Do not permit clearing the field while status is `Resolved`.
- Replacing text updates the current stored value.
- Preserve request identifier, owner, and creation timestamp.
- Update last-updated time after a successful change.
- Keep the note or resolution associated with the correct request.
- No comment thread or edit history is required.
- Reopening a request preserves the current text.

**Planned verification**

- Reviewer saves a valid note or resolution.
- Reviewer replaces an existing note.
- Student sees the current saved text.
- Text at the 2,000-character limit is accepted.
- Text exceeding 2,000 characters after trimming is rejected.
- Clearing is allowed while `Submitted` or `In Progress`.
- Clearing while `Resolved` is rejected.
- Students cannot save or replace reviewer notes.
- Unknown request identifiers are rejected.
- Failed writes preserve existing stored content and do not produce false confirmation.
- Reopening preserves the saved note or resolution.

### API-006 — Request Repository Operations

**Consumer:** Request Workflow Service  
**Provider:** Request Repository  
**Related requirements:** REQ-001 through REQ-006  
**Acceptance criteria:** AC-REQ-003-01, AC-REQ-003-02, plus persistence behavior supporting the other relevant acceptance criteria  
**Status:** Proposed

**Logical operations**

- Create and persist a request with a unique identifier.
- Store the selected student requester identity.
- Store the validated title, category, and description.
- Store creation and last-updated timestamps.
- Store the initial status `Submitted`.
- Retrieve a request by identifier.
- Retrieve requests belonging to a selected student.
- Retrieve requests for reviewer listing and inspection.
- Persist permitted status changes.
- Persist reviewer note or resolution changes.
- Retain records across ordinary application restarts against the same local store.

**Contract rules**

- The Request Workflow Service must enforce role, ownership, and workflow authorization before repository operations.
- Repository operations must not be exposed as an authorization bypass.
- Successfully stored requests must have unique identifiers.
- Request ownership must remain stable when demo identities change.
- Updates preserve request ID, owner, and creation timestamp.
- Successful changes update last-updated time.
- Failed writes must not be reported as successful.
- Rejected operations must not modify stored records.
- Failed retrieval must be distinguishable from an empty result.
- Persistence must survive ordinary application restarts using the same local store.
- The database technology remains subject to an accepted architecture decision.
- Transaction, retry, identifier-generation, and concurrent-update details require team decisions.

**Planned verification**

- Created records can be retrieved by identifier.
- Successfully created records have unique identifiers.
- Student ownership remains associated with the correct record.
- Student-specific retrieval does not expose another student's records.
- Reviewer retrieval can access requests from both students.
- Status and note updates persist.
- Records remain available after restarting the application against the same local store.
- Reopening preserves the existing note.
- Failed writes are reported without false success.
- Rejected operations preserve existing stored records.
- Updated records retain their original ID, owner, and creation timestamp.

### API-007 — Simulated Role Context

**Consumer:** Request Workflow Service  
**Provider:** Simulated Role Context  
**Related requirements:** REQ-001 through REQ-006  
**Acceptance criteria:** AC-REQ-002-03, AC-REQ-004-02, plus role checks supporting other relevant acceptance criteria  
**Status:** Proposed

**Purpose**

Provide the simulated role and identity information needed to distinguish student requester and support reviewer behavior.

**Expected information**

- Selected demo identity: Student A, Student B, or Reviewer.
- Role associated with the selected identity.
- Stable student requester identity when applicable.
- Server-session context used for application authorization checks.

**Contract rules**

- The demo selector stores the selected identity in the server session.
- The Request Workflow Service obtains role and identity from the session context.
- Students may submit requests and view only their own requests.
- Reviewer may list and inspect all requests and perform reviewer-only updates.
- Form-supplied roles or requester IDs cannot override session context.
- Missing or invalid session context must be rejected.
- Switching demo identity does not alter stored request ownership.
- Stable stored requester identities allow ownership to be recovered after application restart and demo-user reselection.
- The demo selector intentionally permits impersonation for classroom testing.
- This mechanism is not real authentication or Loyola single sign-on.

**Planned verification**

- Student A and Student B remain distinguishable.
- Reviewer operations are available only with Reviewer session context.
- Student A cannot access Student B's requests, and vice versa.
- Form-supplied roles or requester IDs cannot bypass authorization.
- Missing or invalid session context is rejected.
- Switching demo users does not change stored ownership.
- Reselecting a demo student after restart restores access to that student's persisted requests.

## 5. AI and External Tool Boundaries

The Cycle 1 support-request workflow does not include user-facing AI, external APIs, or institutional integrations in its current requirements baseline.

Accordingly:

- No AI model is authorized to create, modify, or approve request state.
- No external service contract is assumed.
- Development-time AI assistance is documented separately in the team's AI-use log.
- Real Loyola authentication and institutional systems are excluded.
- Real email and text-message notifications are excluded.
- Any future AI or external integration requires scope review, architecture decisions, appropriate permissions, documented risk review, and applicable instructor approval.

## 6. Open Decisions and Dependencies

Q-001 through Q-004 have draft resolutions in PR #46. They are not treated as implementation-verified decisions.

| Reference | Draft Resolution or Pending Decision | Affected Contracts |
|---|---|---|
| Q-001 | Title, category, and description validation rules; draft defaults pending baseline approval | API-001, API-006 |
| Q-002 | Submitted, In Progress, Resolved, and permitted transitions | API-002, API-004, API-006 |
| Q-003 | One editable note or resolution field with clearing restrictions | API-002, API-005, API-006 |
| Q-004 | Student A, Student B, Reviewer; server-session context and stable ownership | API-001 through API-007 |
| Architecture ADR | Final framework, database, transport, and deployment design | All relevant contracts |
| Retry and concurrency | Duplicate submissions, transaction boundaries, concurrent updates, and recovery | API-001, API-004, API-005, API-006 |
| Logging and recovery | Operational evidence, error handling, and retention | API-001 through API-006 |

The requirements specify Java with server-rendered pages and a local demonstration. Spring Boot, Thymeleaf, Maven, and file-backed H2 are recommendations, not approved implementation choices in this document.

Architecture-critical decisions must be accepted or explicitly deferred with owners and decision points before dependent construction.

## 7. Additional Verification and Boundary Test Scenarios

The following scenarios supplement the 12 proposed acceptance criteria. They are design-time test proposals, not executed results.

| Test ID | Related Contract | Scenario | Expected Behavior |
|---|---|---|---|
| NEG-001 | API-001 | Student submits missing or invalid required information | Reject with validation feedback; store no request |
| NEG-002 | API-001, API-006 | Two valid requests are successfully created | Each receives a distinct identifier |
| NEG-003 | API-002 | Student B attempts to view Student A's request | Reject access without exposing Student A's information |
| NEG-004 | API-002 | Student enters an unknown request identifier | Report unavailable request without exposing unrelated records |
| NEG-005 | API-003 | Student attempts reviewer-only request listing | Reject unauthorized operation |
| NEG-006 | API-004 | Student attempts to change request status | Reject update; preserve stored status |
| NEG-007 | API-004 | Reviewer attempts unsupported status transition | Reject transition; preserve existing status |
| NEG-008 | API-005 | Student attempts reviewer note or resolution modification | Reject unauthorized modification |
| NEG-009 | API-001, API-006 | Persistence fails during request creation | Report failure without confirming creation |
| NEG-010 | API-004, API-006 | Persistence fails during status update | Report failure without confirming status change |
| NEG-011 | API-007 | Operation has missing or invalid session role context | Reject rather than assuming authorization |
| NEG-012 | API-004 | Reviewer attempts status update for nonexistent request | Reject without creating or modifying another record |
| NEG-013 | API-005 | Reviewer attempts note update for nonexistent request | Reject without saving the note |
| NEG-014 | API-005, API-006 | Persistence fails while saving reviewer text | Report failure without falsely confirming storage |
| NEG-015 | API-001 | Title is 4 or 101 characters after trimming | Reject submission |
| NEG-016 | API-001 | Description is 9 or 2,001 characters after trimming | Reject submission |
| NEG-017 | API-001 | Category is unsupported or missing | Reject submission |
| NEG-018 | API-001, API-007 | Reviewer attempts student-only submission | Reject unauthorized submission |
| NEG-019 | API-002, API-007 | Student B attempts to list Student A's requests | Return no Student A records |
| NEG-020 | API-004 | Reviewer attempts to resolve without saved nonblank resolution text | Reject transition; preserve status |
| NEG-021 | API-004 | Reviewer reopens a resolved request | Change status to In Progress and preserve note, ID, and owner |
| NEG-022 | API-005 | Reviewer attempts to clear resolution while status is Resolved | Reject clearing; preserve existing text |
| NEG-023 | API-005 | Reviewer submits note longer than 2,000 trimmed characters | Reject update; preserve existing text |
| NEG-024 | API-006 | Application restarts against the same local store | Previously saved request data, ownership, status, and note remain available |
| NEG-025 | API-007 | Form submits a forged Reviewer role or requester identity | Form values cannot override server-session context |
| NEG-026 | API-003, API-006 | Reviewer listing encounters storage failure | Show an error distinguishable from a successful empty list |
| NEG-027 | API-005 | Reviewer replaces an existing note | Store the new current value and make it visible to the owning student |
| NEG-028 | API-004, API-006 | Successful status update occurs | Preserve ID, owner, and creation time; update last-updated time |

### Verification Boundaries

- Q-001 through Q-004 are resolved for draft review, not implementation verification.
- Boundary tests should cover valid minimum and maximum lengths as well as invalid adjacent values.
- Application-level role and ownership enforcement must be tested independently of UI visibility.
- Persistence checks must include an ordinary application restart against the same local store.
- Failure tests must verify that rejected operations leave stored records unchanged.
- Tests will be implemented and linked to repository evidence during construction.
- No scenario in this document is claimed to have passed.

## 8. Review and Verification Plan

Before marking a contract Accepted:

1. Compare it with the accepted architecture overview and diagram.
2. Confirm component names and boundaries match the component responsibility map.
3. Reconcile the contracts with the approved requirements and acceptance criteria.
4. Review inputs, outputs, permissions, failures, invariants, and persistence behavior.
5. Confirm the dispositions of Q-001 through Q-004.
6. Record independent reviewer findings and their resolutions.
7. Reconcile planned tests with the test strategy in PR #44.
8. Update planning traceability, estimates, risks, and tasks if the design changes.
9. Verify that the final architecture decisions and PR review evidence are linked.

These are planned review activities, not claims of completed tests.

## 9. Related Engineering Evidence

- `docs/requirements/requirements.md`
- `docs/requirements/acceptance-criteria.md`
- `docs/requirements/assumptions-open-questions.md`
- `docs/architecture/architecture.md`
- `docs/architecture/component-responsibilities.md`
- `docs/planning/traceability.md`
- `docs/planning/risk-register.md`
- `docs/decisions/ADR-001-defer-production-technology-stack.md`
- `docs/testing/test-strategy.md`
- `docs/ai/ai-use-log.md`

**Related pull requests**

- PR #43 — Component Responsibilities and API Contracts.
- PR #46 — Draft Requirements and Acceptance Criteria Updates.
- PR #44 — Test Strategy Alignment.

Links to accepted architecture decisions, independent review findings, and final GitHub evidence must be verified before the A3 phase gate.

**Evidence status:** Architecture and contract proposal only. No implemented or verified API behavior is claimed.
