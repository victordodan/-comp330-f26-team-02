
# Component Responsibilities

**Project:** CampusConnect  
**Team:** Team 2 — Codex Ramblers  
**Assignment:** A3 — Cycle 1 Architecture and Review  
**Status:** Documented Cycle 1 proposal — final baseline acceptance pending

## 1. Purpose and Scope

This document defines the proposed component responsibilities, dependencies, ownership boundaries, and failure-handling responsibilities for CampusConnect Cycle 1.

CampusConnect supports one end-to-end workflow: a simulated student submits a synthetic support request, receives a unique identifier, and views its current status and note or resolution. A simulated reviewer can inspect requests from both students, change status using permitted transitions, and save or replace a note or resolution.

The design follows the requirements and acceptance criteria merged in PR #46, subject to explicit final baseline acceptance. Component names and boundaries must be checked against the architecture overview, diagram, and accepted ADRs.

These responsibilities describe proposed architecture, not implemented or verified functionality.

## 2. Component Responsibility Matrix

| Component | Primary Responsibilities | Does Not Own | Depends On | Used By | Related Requirements |
|---|---|---|---|---|---|
| Requester and Reviewer UI | Provide server-rendered student and reviewer views; collect and display request details; initiate submission, retrieval, status-change, and note-edit operations; display validation and failure feedback | Authoritative role and ownership checks, workflow transitions, persistent storage | Request Workflow Service | Student A, Student B, Reviewer | REQ-001 through REQ-006 |
| Request Workflow Service | Validate request fields; enforce server-session roles and ownership; coordinate request creation and retrieval; validate status transitions and note rules; confirm operations only after successful storage | UI rendering, physical storage, real institutional authentication | Simulated Role Context, Request Repository | Requester and Reviewer UI | REQ-001 through REQ-006 |
| Request Repository | Persist request IDs, stable owners, title/category/description, timestamps, current statuses, and note/resolution text; retrieve student-owned and reviewer-visible records; retain data across restarts | Authorization policy, user interface, status-transition decisions, role selection | File-backed H2 local persistent data store (ADR-002) | Request Workflow Service | REQ-001 through REQ-006 |
| Simulated Role Context | Provide Student A, Student B, or Reviewer identity from the server session; supply stable requester context for ownership enforcement | Real authentication, Loyola identity verification, final workflow authorization decisions | Demo selector and server-session mechanism (implementation pending) | Request Workflow Service | REQ-001 through REQ-006 |

## 3. Component Inputs, Outputs, and Failure Responsibilities

| Component | Inputs | Outputs | Failure Responsibilities |
|---|---|---|---|
| Requester and Reviewer UI | Student request fields, request identifiers, user actions, reviewer status and note inputs | Submission confirmations, validation feedback, student-owned request details, reviewer listings, current status and note information | Display errors without false success; do not expose unauthorized records; distinguish empty lists from retrieval failures |
| Request Workflow Service | Validated session context, submissions, retrieval requests, proposed status changes, note replacements | Authorized responses, persisted request confirmations, rejected-operation results | Reject invalid fields, unauthorized roles, ownership violations, invalid transitions, prohibited note changes, and failed writes |
| Request Repository | Create, retrieve, list, and update operations from the workflow service | Persisted records, unique identifiers, current data, storage-operation results | Report missing records and storage failures; preserve existing data on failed operations; distinguish empty results from retrieval errors |
| Simulated Role Context | Demo selection and server-session state | Selected role and stable student identity, when applicable | Report missing or invalid session context; never treat form-supplied roles as authoritative |

## 4. Responsibility Boundaries and Design Rationale

### Requester and Reviewer UI

The UI provides Java server-rendered pages for local demonstration.

Student-facing views allow Student A or Student B to:

- Submit a request with title, category, and description.
- Receive a successful submission confirmation and request identifier.
- List or inspect only their own requests.
- View current status and available reviewer note or resolution text.

Reviewer-facing views allow Reviewer to:

- List and inspect requests from both simulated students.
- Request permitted status changes.
- Save or replace the current note or resolution.

The UI may provide validation feedback and hide unavailable actions, but it is not the authoritative permission boundary. Students cannot edit or delete submitted requests.

### Request Workflow Service

The Request Workflow Service owns the business rules and application-level authorization.

For request submission, it:

- Accepts title, category, and description from a selected student.
- Trims surrounding whitespace.
- Validates title length of 5–100 characters.
- Validates category against Academic, IT, Campus Services, and Other.
- Validates description length of 10–2,000 characters.
- Uses the student identity from server-session context.
- Coordinates unique ID assignment, timestamps, initial `Submitted` status, and persistence.
- Confirms success only after the request has been stored.

For access control, it:

- Permits only students to submit requests and view their own requests.
- Permits Reviewer to inspect requests from both students.
- Restricts status and note modifications to Reviewer.
- Rejects missing or invalid session context.
- Rejects attempts to override role or ownership using form values.
- Applies ownership checks to both direct request retrieval and student request listings.

For status changes, it enforces:

| Current Status | Requested Status | Allowed? | Condition |
|---|---|---|---|
| Submitted | In Progress | Yes | Reviewer authorization |
| In Progress | Resolved | Yes | Reviewer authorization and saved nonblank resolution text |
| Resolved | In Progress | Yes | Reviewer authorization; preserve existing note |
| Any other transition | Any unsupported or unpermitted status | No | Reject without modifying stored data |

For note or resolution updates, it:

- Maintains one current student-visible text field per request.
- Permits Reviewer to save or replace the text.
- Trims the text and enforces the 2,000-character maximum.
- Allows clearing while status is `Submitted` or `In Progress`.
- Rejects clearing while status is `Resolved`.
- Preserves the text when a resolved request is reopened.
- Does not require threaded messaging or edit history.

The workflow service also ensures that successful updates preserve the request identifier, owner, and creation timestamp while updating the last-updated timestamp.

### Request Repository

The Request Repository owns storage operations, not business authorization.

It is responsible for:

- Persisting unique request identifiers.
- Storing validated request fields and stable student ownership.
- Storing creation and last-updated timestamps.
- Storing the current request status and current note or resolution.
- Retrieving a request by identifier.
- Retrieving requests for a specific student or the reviewer.
- Persisting authorized updates.
- Retaining stored records across ordinary application restarts against the same local store.
- Reporting storage failures accurately.

The repository must not become an alternate route for bypassing the workflow service's authorization checks.

Accepted ADR-002 selects file-backed H2. ADR-003 requires transactional updates and optimistic version checking: stale reviewer changes are rejected and the reviewer reloads the latest record. ADR-004 sets persistence and recovery expectations. Retry handling and identifier-generation implementation details remain to be defined.

### Simulated Role Context

The Simulated Role Context supplies the selected demo identity from the server session.

The proposed identities are:

- Student A
- Student B
- Reviewer

The demo selector intentionally permits switching between these identities for classroom testing. It is not real authentication.

The selected session identity determines which operations the workflow service permits. Form-supplied role values and requester IDs must not override the server session.

Stable stored requester identities preserve ownership when users switch demo roles or the application restarts.

ADR-002 selects Spring Boot for the server-side implementation; the exact session configuration remains implementation work.

## 5. Data Ownership and Consistency

| Data or Rule | Responsible Component |
|---|---|
| Student and reviewer page presentation | Requester and Reviewer UI |
| Required field validation | Request Workflow Service |
| Role and request ownership enforcement | Request Workflow Service |
| Status-transition policy | Request Workflow Service |
| Note editing and clearing rules | Request Workflow Service |
| Current simulated identity and role context | Simulated Role Context |
| Persisted request records and unique IDs | Request Repository |
| Persisted owner and timestamps | Request Repository |
| Persisted current status and note/resolution | Request Repository |
| Storage success or failure reporting | Request Repository, coordinated by Request Workflow Service |

The workflow service must confirm that storage succeeded before reporting successful creation or update.

Rejected operations must leave records unchanged. A failed request listing must be distinguishable from a successful empty list.

Successful updates preserve the original request ID, owner, and creation timestamp, and update the last-updated timestamp.

## 6. Architecture Constraints

- Only synthetic or made-up request data is permitted.
- The baseline specifies Java with server-rendered pages and local classroom execution.
- Paid hosting, paid runtime services, and required cloud deployment are excluded.
- Real Loyola authentication and single sign-on are outside Cycle 1 scope.
- Student and reviewer roles are simulated through a demo selector and server session.
- Real email or text-message integration is excluded.
- Live institutional integrations are not required.
- User-facing AI is excluded from the current baseline.
- External APIs or services require instructor approval and documented risk review.
- ADR-002 selects Spring Boot, Thymeleaf, Maven, and file-backed H2 for Cycle 1; Victor Dodan approved ADR-002 through ADR-004 on October 16, 2026.
- The team prioritizes a complete support-request workflow over additional features.

## 7. Assumptions and Draft Decisions

| Reference | Draft Resolution | Component Impact |
|---|---|---|
| ASM-001 | One current request status at a time | Workflow Service and Repository |
| ASM-002 | One short note or resolution without threaded messaging | Workflow Service, Repository, UI |
| Q-001 | Title 5–100 characters; four categories; description 10–2,000 characters | UI, Workflow Service, Repository |
| Q-002 | Submitted → In Progress → Resolved, with Resolved → In Progress reopening | Workflow Service, Repository, UI |
| Q-003 | One editable student-visible note/resolution, maximum 2,000 trimmed characters; clearing prohibited while Resolved | Workflow Service, Repository, UI |
| Q-004 | Student A, Student B, Reviewer; demo selector stored in server session | Simulated Role Context, Workflow Service, UI |

These resolutions are selected for draft review and are not implementation-verified.

ADR-002 through ADR-004 establish the stack, transactional/versioned updates, and persistence/recovery expectations. Remaining implementation detail includes retry handling, logging location/retention, identifier generation, and executable backup/recovery procedures. Final package review remains pending.

## 8. Requirements and Acceptance-Criteria Traceability

| Component | Requirements Supported | Acceptance Criteria Supported |
|---|---|---|
| Requester and Reviewer UI | REQ-001 through REQ-006 | AC-REQ-001-01, AC-REQ-001-02, AC-REQ-002-01, AC-REQ-002-02, AC-REQ-002-03, AC-REQ-004-01, AC-REQ-004-02, AC-REQ-005-01, AC-REQ-005-02, AC-REQ-006-01 |
| Request Workflow Service | REQ-001 through REQ-006 | All 12 proposed acceptance criteria |
| Request Repository | REQ-001 through REQ-006 | AC-REQ-003-01, AC-REQ-003-02, and persistence behavior supporting the remaining criteria |
| Simulated Role Context | REQ-001 through REQ-006 | AC-REQ-002-03, AC-REQ-004-02, and role behavior supporting the remaining criteria |

The acceptance criteria are design targets, not completed verification results.

## 9. Review and Verification Dependencies

Before final A3 acceptance, the team must:

1. Reconcile this document with the architecture overview and diagram.
2. Confirm component names and responsibilities match the API contracts.
3. Review and accept or explicitly defer the Q-001 through Q-004 draft resolutions.
4. Confirm architectural decisions and ownership of remaining open issues.
5. Record independent reviewer findings and their dispositions.
6. Reconcile planned verification with the test strategy in PR #44.
7. Update planning traceability, risks, and estimates when design decisions affect them.
8. Link the accepted design and review evidence to the A3 submission.

No completed implementation or verification is claimed.

## 10. Related Engineering Evidence

- `docs/requirements/requirements.md`
- `docs/requirements/acceptance-criteria.md`
- `docs/requirements/assumptions-open-questions.md`
- `docs/architecture/architecture.md`
- `docs/architecture/api-contracts.md`
- `docs/planning/traceability.md`
- `docs/planning/risk-register.md`
- `docs/decisions/ADR-001-defer-production-technology-stack.md`
- `docs/testing/test-strategy.md`
- `docs/ai/ai-use-log.md`

**Related pull requests**

- PR #43 — Component Responsibilities and API Contracts.
- PR #46 — Draft Requirements and Acceptance Criteria Updates.
- PR #44 — Test Strategy Alignment.

**Evidence status:** Proposed component design only. No implemented or verified behavior is claimed.
