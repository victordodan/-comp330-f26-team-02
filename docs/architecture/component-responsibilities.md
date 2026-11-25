# Component Responsibilities

**Project:** CampusConnect  
**Team:** Team 2 — Codex Ramblers  
**Assignment:** A6 — Cycle 2 Final Release Candidate  
**Owner:** Shú Perez  
**Backup:** Giancarlo Herrera  
**Last Updated:** 2026-11-24  
**Target Release:** `a6-final-release`  
**Status:** Implementation-aligned documentation; final verification pending

---

## 1. Purpose and Scope

This document defines the implemented component responsibilities, dependencies, ownership boundaries, and failure-handling responsibilities for CampusConnect.

CampusConnect supports an end-to-end student support request workflow in which:

- A student selects a synthetic identity.
- The student submits a support request.
- The application validates and persists the request.
- The student receives a unique request identifier.
- A reviewer inspects submitted requests.
- The reviewer performs authorized status transitions and resolution note updates.
- The owning student retrieves the latest stored request information.

The implemented architecture uses HTML, CSS, vanilla JavaScript, Java, Spring Boot, REST HTTP endpoints, Spring JDBC, Maven, and file-backed H2.

The browser presentation layer communicates with the existing REST API through same-origin HTTP requests.

The application is accessible through the locally configured address:

`http://campusconnect.local:8080`

This document replaces the proposed server-rendered component structure from Assignment 3 with the reviewed implementation structure, including the approved Cycle 2 browser presentation layer.

Component responsibilities must remain consistent with the architecture overview, architecture diagram, API contracts, requirements, and applicable architecture decision records.

Final acceptance remains subject to integrated verification and independent human review.

---

## 2. Component Responsibility Matrix

| Component | Primary Responsibilities | Does Not Own | Dependencies | Related Contracts |
|---|---|---|---|---|
| Browser Presentation Layer | Provide student and reviewer interfaces, coordinate user interactions, and display application responses | Authoritative authorization, business rules, persistence | HTML, CSS, JavaScript, Spring Boot static resources, REST API | API-001 through API-005, API-007 |
| index.html | Define identity selection, dashboards, request forms, request cards, and feedback structures | Business rules, authorization, database operations | styles.css, app.js | API-001 through API-005, API-007 |
| styles.css | Provide responsive layout, status indicators, focus indicators, and visual feedback | Application state, workflow decisions, HTTP requests | HTML interface | Presentation only |
| app.js | Manage temporary browser state, identity selection, REST requests, conditional updates, filtering, and response handling | Authoritative authorization, workflow policy, persistent data | Browser Fetch API, HTML interface, REST API | API-001 through API-005, API-007 |
| Spring Boot Static Resource Delivery | Serve frontend HTML, CSS, and JavaScript resources | Protected API authorization, workflow decisions | Spring Boot, static resources | Presentation delivery |
| HTTP Client | Submit HTTP requests, retain session cookies, provide JSON input, retain ETags, interpret responses | Authoritative authorization, business rules, persistence | RequestController | API-001 through API-005, API-007 |
| RequestController | Expose REST endpoints, process DTOs, retrieve session identity, parse If-Match, construct HTTP responses | SQL operations, persistent storage, authoritative workflow rules | WorkflowService, DTOs, DemoIdentity, HTTP Session | API-001 through API-005, API-007 |
| RequestValidator | Normalize and validate student submission fields | Authorization, persistence, status transitions | Java standard library | API-001 |
| WorkflowService | Enforce authorization, ownership, lifecycle rules, note restrictions, transaction coordination, and version checks | HTTP routing, SQL implementation, real authentication | RequestValidator, RequestRepository, SupportRequest, DemoIdentity, transaction manager | API-001 through API-006 |
| SupportRequest | Represent immutable request data and current version | Authorization, database operations, HTTP routing | Java standard library | API-001 through API-006 |
| Request DTOs | Represent incoming JSON request data | Authorization, business decisions, persistence | Java record structures | API-001, API-004, API-005 |
| RequestRepository | Define persistence operations and version-aware replacement contract | SQL implementation, authorization, workflow policy | SupportRequest | API-006 |
| JdbcRequestRepository | Implement parameterized SQL operations and map database records | Role selection, authorization, lifecycle decisions | RequestRepository, JdbcTemplate, H2 | API-006 |
| File-backed H2 | Store request records across ordinary application restarts | Application authorization, HTTP communication, workflow decisions | Local filesystem and database configuration | API-006 |
| DemoIdentity / HTTP Session | Represent and retain selected synthetic identity | Real authentication, institutional identity verification | RequestController, server session | API-007 |
| WorkflowException | Represent application-level failure codes and messages | HTTP response construction, recovery execution | Java exception mechanism | API-001 through API-007 |
| ApiExceptionHandler | Translate handled exceptions into HTTP error responses | Workflow decisions, storage recovery, authorization policy | WorkflowException, Spring exception handling | API-001 through API-007 |

---

## 3. Component Inputs, Outputs, and Failure Responsibilities

| Component | Inputs | Outputs | Failure Responsibilities |
|---|---|---|---|
| Browser Presentation Layer | User actions, interface state, server responses | Student and reviewer dashboards, operation feedback | Communicate failed operations without displaying false success |
| index.html | Static interface definitions | Structured browser interface | Provides interface elements for loading, empty, and error states |
| styles.css | HTML elements and interface state classes | Visual presentation and responsive layout | Does not independently handle application failures |
| app.js | User actions, request fields, session context, JSON responses, ETags | REST requests, updated interface state, operation feedback | Report HTTP and network failures, prevent stale modifications, avoid automatic conflicting retries |
| Spring Boot Static Resource Delivery | HTTP requests for frontend resources | HTML, CSS, and JavaScript resources | Return appropriate HTTP responses for unavailable resources |
| HTTP Client | User actions, request fields, session cookie, current ETag | HTTP requests and interpreted responses | Display or report errors; retrieve current version before retrying stale updates |
| RequestController | HTTP requests, JSON bodies, request IDs, session state, If-Match headers | JSON responses, HTTP status codes, ETags | Reject missing identity, unauthorized reviewer access, invalid version headers, and prohibited combined status/note updates |
| RequestValidator | Title, category, description | Normalized fields and validation results | Identify missing, unsupported, and out-of-range input |
| WorkflowService | Selected identity, request fields, request ID, requested status or note, expected version | Authorized request records and persisted updates | Reject invalid input, unauthorized access, ownership violations, invalid transitions, stale versions, and failed writes |
| SupportRequest | Request data, ownership, timestamps, status, note, version | Immutable request record | Does not independently enforce workflow policy |
| Request DTOs | Deserialized JSON request bodies | Typed input records | Malformed JSON is handled at the HTTP boundary |
| RequestRepository | Request records, IDs, student IDs, expected versions | Stored records, lists, optional records, replacement result | Communicate missing records and unsuccessful replacements |
| JdbcRequestRepository | Repository operations and parameters | SQL execution results and mapped request records | Propagate database errors and report unsuccessful version-aware updates |
| File-backed H2 | SQL statements and transaction operations | Persisted database records | Report database and storage failures through JDBC |
| DemoIdentity / HTTP Session | Synthetic identity selection and session state | Selected synthetic identity | Reject unsupported identities and prevent missing identity from being treated as authorized |
| WorkflowException | Application failure code and message | Structured application exception | Preserve the failure category for HTTP translation |
| ApiExceptionHandler | Workflow, malformed JSON, database, and transaction exceptions | HTTP error status and JSON error body | Report handled failures without false success confirmation |

---

## 4. Responsibility Boundaries and Design Rationale

### 4.1 HTTP Client

The implemented application exposes a REST API for local demonstration.

HTTP clients may include the implemented browser presentation layer, Postman, cURL, or another compatible HTTP client.

Student operations include:

- Selecting Student A or Student B.
- Submitting a support request.
- Listing owned requests.
- Retrieving an owned request.
- Viewing current status and resolution note.

Reviewer operations include:

- Selecting Reviewer.
- Listing requests from both students.
- Inspecting individual requests.
- Updating request status.
- Saving or replacing resolution notes.

The client is responsible for maintaining the applicable session context and supplying the current ETag when performing conditional operations.

Client-side restrictions are not authoritative security controls.

The server-rendered Thymeleaf interface proposed during Assignment 3 is not implemented. Cycle 2 instead introduces a same-origin static browser presentation layer served by Spring Boot.

### 4.2 RequestController

**Source:** `controller/RequestController.java`

RequestController owns the HTTP interface boundary.

Responsibilities include:

- Exposing the eight implemented REST endpoints.
- Accepting JSON request bodies through DTOs.
- Selecting and storing fixed synthetic identities.
- Retrieving the selected identity from the HTTP session.
- Rejecting missing or invalid session context.
- Checking reviewer identity before reviewer modification operations.
- Parsing If-Match headers into expected numeric versions.
- Delegating business operations to WorkflowService.
- Returning HTTP success responses.
- Returning ETags for individual request responses.

The controller does not trust client-supplied ownership fields.

For request creation, the studentID field in CreateRequestDto is not used to assign ownership.

For status updates, a non-null resolutionNote field is rejected because note changes must occur through the separate note endpoint.

The controller does not execute SQL statements or independently manage database transactions.

### 4.3 RequestValidator

**Source:** `validation/RequestValidator.java`

RequestValidator owns submission field normalization and validation.

| Field | Responsibility |
|---|---|
| Title | Trim whitespace and enforce 5–100 characters |
| Category | Trim whitespace and verify supported category |
| Description | Trim whitespace and enforce 10–2,000 characters |

Supported categories:

- Academic
- IT
- Campus Services
- Other

The validator returns a ValidationResult containing:

- Overall validity.
- Normalized title.
- Normalized category.
- Normalized description.
- Validation error messages.

The validator does not authorize users, generate identifiers, access the database, or enforce status transitions.

WorkflowService consumes the validation result and determines whether request creation may proceed.

### 4.4 WorkflowService

**Source:** `service/WorkflowService.java`

WorkflowService owns authoritative application-level business rules and authorization.

#### Request Creation

The service:

- Requires an authorized student identity.
- Delegates field validation to RequestValidator.
- Rejects invalid submissions.
- Generates a unique UUID-based request identifier.
- Assigns ownership from the selected synthetic identity.
- Assigns creation and last-updated timestamps.
- Sets initial status to Submitted.
- Sets the initial resolution note to an empty string.
- Sets the initial version to 0.
- Coordinates transactional persistence.
- Returns the created request after successful storage.

#### Authorization and Ownership

The service:

- Permits students to submit requests.
- Restricts students to their own request records.
- Permits Reviewer to inspect requests across both students.
- Restricts status and note modifications to Reviewer.
- Rejects unauthorized roles.
- Returns a not-found failure for cross-student request access.

Authorization is enforced independently of client-side behavior.

#### Status Transitions

| Current Status | Requested Status | Allowed | Condition |
|---|---|---|---|
| Submitted | In Progress | Yes | Reviewer authorization and current version |
| In Progress | Resolved | Yes | Reviewer authorization, current version, and saved nonblank note |
| Resolved | In Progress | Yes | Reviewer authorization and current version |
| Any other transition | Unsupported or prohibited status | No | Reject without modifying the stored record |

#### Resolution Note Management

The service:

- Maintains one current student-visible resolution note.
- Permits Reviewer to save or replace the note.
- Normalizes null text to an empty string.
- Trims surrounding whitespace.
- Enforces a maximum length of 2,000 characters.
- Allows clearing while Submitted or In Progress.
- Rejects clearing while Resolved.
- Preserves the existing note when reopening a request.

#### Transaction and Concurrency Coordination

The service:

- Coordinates database operations using Spring TransactionTemplate.
- Retrieves the current stored record before modification.
- Compares the supplied expected version with the current version.
- Rejects stale versions.
- Constructs an updated immutable request record.
- Increments the version after a permitted modification.
- Advances the last-updated timestamp.
- Delegates atomic version-aware replacement to RequestRepository.
- Treats unsuccessful replacement as a concurrency conflict.
- Reports storage and transaction failures without confirming success.

WorkflowService does not own HTTP routing, response formatting, or physical SQL implementation.

### 4.5 SupportRequest Domain Model

**Source:** `model/SupportRequest.java`

SupportRequest is an immutable Java record representing the current request state.

| Field | Java Type | Responsibility |
|---|---|---|
| id | String | Unique request identifier |
| studentID | String | Stable student ownership |
| title | String | Validated request title |
| category | String | Validated request category |
| description | String | Validated request description |
| status | String | Current workflow status |
| resolutionNote | String | Current reviewer note |
| createdAt | Instant | Creation timestamp |
| updatedAt | Instant | Last-updated timestamp |
| version | long | Optimistic concurrency version |

The domain model carries request data between the service, repository, and HTTP response boundary.

The model does not independently authorize users, validate workflow transitions, or execute persistence operations.

WorkflowService owns those decisions.

### 4.6 Data Transfer Objects

The implemented request DTOs are Java records.

#### CreateRequestDto

**Source:** `model/CreateRequestDto.java`

Fields:

- studentID
- title
- category
- description

The studentID field is not authoritative for ownership assignment.

#### UpdateStatusDto

**Source:** `model/UpdateStatusDto.java`

Fields:

- status
- resolutionNote

The status endpoint rejects non-null resolutionNote values.

Resolution notes must be submitted through the separate note endpoint.

#### UpdateNoteDto

**Source:** `model/UpdateNoteDto.java`

Field:

- resolutionNote

This DTO represents a separate reviewer note modification.

#### DemoSelection

**Source:** `controller/RequestController.java`

Field:

- identity

This nested record represents synthetic identity selection.

DTOs transport input data. They do not establish authorization or guarantee that supplied values satisfy application rules.

### 4.7 RequestRepository

**Source:** `repository/RequestRepository.java`

RequestRepository defines the internal persistence abstraction consumed by WorkflowService.

| Method | Responsibility | Return Type |
|---|---|---|
| insert | Persist a newly created request | void |
| findById | Retrieve a request by identifier | Optional of SupportRequest |
| findByStudentID | Retrieve requests belonging to a student | List of SupportRequest |
| findAll | Retrieve all requests | List of SupportRequest |
| replace | Replace mutable request state using the expected version | boolean |

The repository interface separates workflow policy from physical database implementation.

Repository operations are not exposed directly through public HTTP endpoints.

Authorization remains the responsibility of WorkflowService.

### 4.8 JdbcRequestRepository

**Source:** `repository/JdbcRequestRepository.java`

JdbcRequestRepository implements RequestRepository using Spring JdbcTemplate.

Responsibilities include:

- Executing parameterized SQL statements.
- Inserting complete request records.
- Retrieving records by identifier.
- Retrieving records by stored student ownership.
- Retrieving all records for authorized reviewer operations.
- Mapping database rows into SupportRequest records.
- Converting stored timestamps into Java Instant values.
- Performing atomic version-aware updates.
- Returning whether a version-aware replacement affected exactly one row.

Student-specific and reviewer-wide listings are ordered by creation timestamp and request identifier.

The version-aware replacement uses the following condition:

```sql
WHERE id = ? AND version = ?
```

The update modifies only:

- status
- resolution_note
- updated_at
- version

This preserves the stored request identifier, student ownership, title, category, description, and creation timestamp.

Parameterized SQL reduces exposure to SQL injection through these repository operations.

JdbcRequestRepository does not determine which user is authorized to access or modify a request.

### 4.9 File-backed H2 Database

The H2 database provides local persistent request storage.

Default database configuration:

```text
jdbc:h2:file:./data/campusconnect
```

The implemented repository accesses the `support_request` table.

Stored fields include:

- id
- student_id
- title
- category
- description
- status
- resolution_note
- created_at
- updated_at
- version

Persistence across ordinary application restarts requires reuse of the same local database store.

Same-store restart persistence was demonstrated during the recorded November 24, 2026, operational exercises.

The database does not replace application-level authorization or workflow validation.

Production database deployment, distributed storage, backup restoration, and disaster recovery are not established by the local persistence implementation.

Reference: [Runtime Evidence](../observability/runtime-evidence.md).

### 4.10 DemoIdentity and HTTP Session

**Source:** `model/DemoIdentity.java`

Supported synthetic identities:

- student-a
- student-b
- reviewer

RequestController stores the selected identity in the HTTP session under:

```text
campusconnect.demoIdentity
```

DemoIdentity defines the supported fixed identities and distinguishes students from Reviewer.

The controller retrieves the selected identity and passes it to WorkflowService.

WorkflowService enforces authorization and ownership using that identity.

Client-supplied role headers and requester identifiers cannot override the selected server-session identity.

Switching identities does not modify stored request ownership.

The demo selector intentionally permits synthetic identity switching.

The browser presentation layer uses the same server-side session mechanism as other HTTP clients.

Multiple browser tabs may share the same session cookie. Selecting a different identity in one tab may affect the server-side identity used by another tab.

Independent browser profiles should be used for simultaneous student and reviewer demonstrations.

It does not provide:

- Real authentication.
- Password verification.
- Loyola single sign-on.
- Institutional identity verification.
- Production account management.

### 4.11 WorkflowException and ApiExceptionHandler

**Sources:**

- `service/WorkflowException.java`
- `controller/ApiExceptionHandler.java`

WorkflowException communicates application failure categories and messages.

ApiExceptionHandler translates handled exceptions into HTTP error responses.

| Application Error | HTTP Status | Responsibility |
|---|---|---|
| BAD_INPUT | 400 | Invalid input or prohibited operation |
| DENIED | 403 | Missing or unauthorized identity |
| NOT_FOUND | 404 | Missing or inaccessible request |
| CONFLICT | 412 | Stale request version |
| PRECONDITION_REQUIRED | 428 | Missing required version header |
| STORAGE_ERROR | 503 | Storage or transaction failure |

Malformed JSON is handled as BAD_INPUT with HTTP 400.

Handled database and transaction failures return HTTP 503.

Handled application errors contain:

- code
- errors

ApiExceptionHandler does not determine workflow policy or independently execute recovery operations.

The browser presentation layer consumes these HTTP responses and communicates operation outcomes to the user.

### 4.12 Browser Presentation Layer

The approved Cycle 2 browser presentation layer provides a graphical interface for the existing CampusConnect REST workflow.

The frontend resources are located in:

`src/main/resources/static/`

The presentation layer introduces no additional business REST endpoints, separate application server, or independent persistent database.

#### HTML Interface

**Source:** `src/main/resources/static/index.html`

Responsibilities include:

- Defining synthetic identity selection controls.
- Providing student and reviewer dashboard structures.
- Providing student request submission fields.
- Providing request listing and status filter structures.
- Providing reviewer interaction containers.
- Providing loading, empty, and notification structures.
- Communicating the synthetic classroom demonstration boundary.

The HTML interface does not independently authorize users or enforce backend workflow rules.

#### CSS Presentation

**Source:** `src/main/resources/static/styles.css`

Responsibilities include:

- Defining application layout and visual appearance.
- Providing responsive interface styling.
- Styling forms, buttons, request cards, and dashboards.
- Visually distinguishing workflow statuses.
- Providing focus indicators and operation feedback styling.
- Supporting readable presentation across different viewport sizes.

CSS does not perform HTTP requests, modify stored data, or determine application permissions.

Comprehensive browser compatibility and accessibility compliance remain unverified.

#### JavaScript Application Logic

**Source:** `src/main/resources/static/app.js`

Responsibilities include:

- Coordinating synthetic identity selection.
- Submitting same-origin REST requests.
- Loading student-specific or reviewer-wide request lists.
- Maintaining temporary browser interface state.
- Coordinating student request submission.
- Rendering request records and saved resolution notes.
- Applying status filters.
- Coordinating reviewer note and status modifications.
- Retrieving current request versions and ETags.
- Preventing submission of detected stale modifications.
- Supplying If-Match headers for conditional updates.
- Displaying successful and failed operation outcomes.
- Refreshing relevant request information after successful modifications.
- Preventing conflicting automatic retries.
- Clearing or updating interface state when authorization changes.

The browser uses the following existing REST endpoints:

| Method | Endpoint | Browser Responsibility |
|---|---|---|
| POST | `/api/demo/identity` | Select or switch synthetic identity |
| POST | `/api/requests` | Submit a student support request |
| GET | `/api/requests` | Load student-owned requests |
| GET | `/api/reviewer/requests` | Load the reviewer queue |
| GET | `/api/reviewer/requests/{id}` | Retrieve current request details and ETag |
| PATCH | `/api/requests/{id}/status` | Submit a conditional status update |
| PATCH | `/api/requests/{id}/note` | Submit a conditional resolution note update |

The browser obtains student request information through the student request list. The individual student request-detail endpoint remains available to compatible REST clients.

JavaScript does not replace server-side authorization, validation, ownership enforcement, workflow rules, or persistent storage.

#### Static Resource Delivery

Spring Boot serves the frontend resources through its existing static resource support.

The browser presentation layer and REST API are delivered from the same application origin.

Static resource availability does not grant authorization to protected REST endpoints.

The frontend requires no separate application server or JavaScript build pipeline.

#### Browser Failure Handling

The presentation layer communicates relevant application failures, including:

- Invalid input or prohibited operations.
- Unauthorized identity or role.
- Missing or inaccessible resources.
- Stale request versions.
- Missing update preconditions.
- Storage or transaction failures.
- Network and application availability failures.

A successful backend modification followed by a failed dashboard refresh must remain distinguishable from a failed modification.

The browser must not display an unsuccessful modification as confirmed successful.

#### Browser State Boundary

Temporary browser state includes:

- Displayed synthetic identity.
- Loaded request information.
- Active status filters.
- Unsaved request form input.
- Unsaved reviewer note text.
- Loading indicators.
- Operation feedback.

This state is not an independent authoritative application datastore.

Unsaved input may be lost when the interface refreshes, changes identity, or re-renders relevant components.

Persistent request information remains authoritative in the backend and configured H2 database.

### 4.13 Verification and Demonstration Support Components

The following scripts support local verification and demonstration preparation.

They are engineering support artifacts rather than independent application runtime components.

| Artifact | Responsibility | Boundary |
|---|---|---|
| `scripts/frontend-smoke-test.sh` | Verify frontend resource availability, required interface content, and selected REST permission boundaries | Does not establish comprehensive browser interaction coverage or create persistent request records |
| `scripts/demo-data.sh` | Prepare synthetic requests and reviewer updates through the existing REST API | Modifies the configured persistent database; does not bypass backend workflow rules |

The demonstration data script uses sequential existing-record matching to reduce unnecessary duplicate creation.

It does not establish atomic duplicate prevention across concurrent executions or automatic recovery from interrupted preparation.

Demonstration preparation must remain restricted to synthetic classroom data.

---

## 5. Data Ownership and Consistency

| Data or Rule | Responsible Component |
|---|---|
| Browser interface structure | index.html |
| Browser visual presentation | styles.css |
| Temporary browser state | app.js |
| Browser REST request coordination | app.js |
| Browser operation feedback | app.js |
| Static frontend resource delivery | Spring Boot |
| HTTP request routing | RequestController |
| JSON request structure | Request DTOs |
| Session identity selection | RequestController / DemoIdentity |
| Selected identity storage | HTTP Session |
| Submission field normalization | RequestValidator |
| Submission field validation | RequestValidator |
| Authoritative authorization | WorkflowService |
| Student ownership enforcement | WorkflowService |
| Unique identifier generation | WorkflowService |
| Initial status and note assignment | WorkflowService |
| Status-transition policy | WorkflowService |
| Resolution note restrictions | WorkflowService |
| Request record representation | SupportRequest |
| Expected version parsing | RequestController |
| Current version comparison | WorkflowService |
| Version increment | WorkflowService |
| Atomic version-aware replacement | JdbcRequestRepository |
| Transaction coordination | WorkflowService |
| Physical SQL execution | JdbcRequestRepository |
| Persistent request storage | File-backed H2 |
| HTTP success responses and ETags | RequestController |
| Application failure categories | WorkflowException |
| HTTP error translation | ApiExceptionHandler |

### 5.1 Consistency Invariants

Successful status and note updates preserve:

- Request identifier.
- Student ownership.
- Title.
- Category.
- Description.
- Creation timestamp.

Successful modifications update:

- The applicable status or resolution note.
- Last-updated timestamp.
- Record version.

Rejected operations must not modify stored records.

A failed retrieval must remain distinguishable from a successful empty result.

Successful creation or modification must not be confirmed before the corresponding storage operation completes.

Displayed browser information must not be treated as more authoritative than the corresponding server response.

### 5.2 Optimistic Concurrency Responsibilities

Optimistic concurrency is shared across component boundaries.

1. RequestController returns the current version through an ETag.
2. The HTTP client retains or retrieves the applicable ETag.
3. The HTTP client submits the ETag through If-Match.
4. RequestController validates and parses the supplied version.
5. WorkflowService checks the expected version against the stored version.
6. WorkflowService constructs an updated record with an incremented version.
7. JdbcRequestRepository performs an atomic version-aware update.
8. An unsuccessful replacement is treated as a concurrency conflict.
9. ApiExceptionHandler returns HTTP 412 for the conflict.
10. A successful modification returns the updated record and new ETag.

This division prevents stale reviewer changes from silently overwriting newer stored data.

The browser presentation layer supplements this mechanism by retrieving current reviewer request details before submitting a modification.

The retrieved request version and ETag are compared with the version originally displayed to the reviewer.

If a mismatch is detected, the browser prevents the stale modification and displays conflict feedback.

A concurrent update may still occur between the browser's retrieval and its subsequent modification request.

The backend remains responsible for detecting and rejecting such conflicts.

The browser does not automatically retry conflicting modifications or overwrite newer stored data.

---

## 6. Architecture Constraints

The reviewed implementation operates within the following boundaries:

- Synthetic or made-up request data only.
- Local classroom demonstration.
- Java and Spring Boot application.
- Same-origin HTML, CSS, and JavaScript browser presentation.
- REST HTTP and JSON interfaces.
- Maven-based build.
- Spring JDBC persistence.
- File-backed H2 storage.
- Server-side synthetic identity selection.
- No verified production authentication.
- No Loyola single sign-on.
- No required cloud deployment.
- No paid runtime services.
- No real email or text-message integration.
- No live institutional integrations.
- No runtime user-facing AI.
- No server-rendered Thymeleaf interface.
- No separate frontend application server.
- No independent persistent browser request database.
- No comprehensive browser compatibility or accessibility certification.
- No verified production monitoring or disaster recovery.

Development-time AI assistance must be documented and independently verified.

The team prioritizes the complete support request workflow over additional unapproved features.

---

## 7. Assumptions and Architecture Decisions

| Reference | Implemented Behavior or Remaining Boundary | Component Impact |
|---|---|---|
| ASM-001 | One current status per request | WorkflowService, SupportRequest, Repository |
| ASM-002 | One current resolution note without threaded messaging | WorkflowService, SupportRequest, Repository |
| Q-001 | Submission field normalization and validation | RequestValidator, WorkflowService |
| Q-002 | Supported status transitions and reopening | WorkflowService |
| Q-003 | Resolution note length and clearing restrictions | WorkflowService |
| Q-004 | Fixed synthetic identities and stable ownership | RequestController, DemoIdentity, WorkflowService |
| ADR-001 | Production technology decisions remain deferred | All components |
| ADR-002 | Local Java, Spring Boot, Maven, and H2 architecture | Application and persistence components |
| ADR-003 | Transactional updates and optimistic version checking | RequestController, WorkflowService, Repository |
| ADR-004 | Local persistence and recovery expectations | Repository, H2, operations |
| ADR-0005 | Clarification of transactional JDBC and file-backed persistence | WorkflowService, JdbcRequestRepository |
| ADR-006 | REST interface alignment and approved browser presentation extension | Browser presentation layer, static resource delivery, RequestController |

ADR-002 through ADR-004 were historically approved on October 16, 2026.

The original architecture proposed Thymeleaf and Post/Redirect/Get. The reviewed implementation instead exposes REST endpoints extended by a same-origin static browser presentation layer.

The Cycle 2 browser presentation direction is documented in the [Cycle 2 Maturity Plan](../planning/cycle2-maturity-plan.md) and the corresponding [ADR-006](../decisions/ADR-006-rest-api-interface-alignment.md) amendment.

The original decisions must remain available as historical engineering evidence.

Implementation deviations and remaining limitations must be documented rather than silently treated as approved changes.

Remaining concerns include:

- Stronger duplicate-submission prevention.
- Idempotency and exact retry behavior.
- Production authentication.
- Operational logging and retention.
- Verified backup restoration and recovery.
- Shared browser session behavior.
- Temporary browser state synchronization.
- Recovery of unsaved browser input.
- Comprehensive browser compatibility and accessibility verification.
- Final integrated human review.

---

## 8. Requirements and Acceptance-Criteria Traceability

| Component | Requirements Supported | Acceptance Criteria Supported |
|---|---|---|
| Browser Presentation Layer | REQ-001 through REQ-006 | Browser interaction supporting the existing workflow criteria |
| HTTP Client / RequestController | REQ-001 through REQ-006 | HTTP interaction and response behavior supporting the workflow criteria |
| RequestValidator | REQ-001, REQ-003 | Submission validation behavior |
| WorkflowService | REQ-001 through REQ-006 | All core workflow acceptance criteria |
| SupportRequest | REQ-001 through REQ-006 | Request data and consistency behavior |
| Request DTOs | REQ-001, REQ-005, REQ-006 | Submission, status, and note input boundaries |
| RequestRepository / JdbcRequestRepository | REQ-001 through REQ-006 | AC-REQ-003-01, AC-REQ-003-02, and supporting persistence behavior |
| DemoIdentity / HTTP Session | REQ-001 through REQ-006 | AC-REQ-002-03, AC-REQ-004-02, and supporting role behavior |
| ApiExceptionHandler | REQ-001 through REQ-006 | Failure handling supporting the relevant acceptance criteria |

The browser presentation layer supports the existing workflow requirements without replacing their authoritative backend implementation.

Detailed requirement-to-test mappings remain in the central requirements, acceptance criteria, and traceability documents.

Component responsibility alignment does not independently establish that acceptance criteria have passed.

Final verification must reference evidence associated with the exact release candidate.

---

## 9. Review and Verification Dependencies

### 9.1 Recorded Component Verification Evidence

The following existing verification results support the reviewed component responsibilities.

| Verification Context | Recorded Results |
|---|---|
| Local frontend verification by Shú Perez, November 24, 2026 | 38 smoke checks passed, zero failed; six synthetic requests created; ten reviewer updates completed |
| Integrated browser candidate `373a0f6` | 38 passing HTTP smoke checks, Chromium browser workflow verification, stale-edit rejection, owner visibility, and repeat demonstration setup that skipped six matching records |
| Integrated revision `0860160bc458af670c7944ccd035d21425e7511d` | 51 Maven tests passed with zero failures, errors, or skips; 36 packaged HTTP workflow checks and five restart checks passed |
| Separate Windows exercise reported by Giancarlo Herrera | Ten of ten manual workflow checks passed and same-store restart retention reported |

The Linux build, packaged HTTP workflow, and restart checks were performed by Codex.

Giancarlo Herrera performed and reported the separate Windows browser and restart exercise.

Supporting evidence:

- [Runtime Evidence](../observability/runtime-evidence.md)
- [Integrated CI Execution](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/45543404188)
- [Final Test Evidence](../testing/final-test-evidence.md)
- [Packaged Workflow Transcript](../../test-evidence/a6-operations-0860160/workflow.jsonl)
- [Packaged Restart Transcript](../../test-evidence/a6-operations-0860160/restart.jsonl)

HTTP smoke checks, automated regression tests, and browser workflow exercises provide distinct verification evidence.

These recorded results retain their original execution contexts and tested revisions.

They do not independently establish verification of the final tagged release.

### 9.2 Final Review Checklist

Before final Assignment 6 acceptance:

- [ ] Confirm component names against the final Java implementation.
- [ ] Confirm frontend component names against the final static resource implementation.
- [ ] Confirm dependency directions against the architecture diagram.
- [ ] Confirm browser-to-REST integration against API contracts.
- [ ] Confirm controller responsibilities against API contracts.
- [ ] Confirm submission validation boundaries.
- [ ] Confirm service-level role and ownership enforcement.
- [ ] Confirm supported lifecycle transitions.
- [ ] Confirm resolution note restrictions.
- [ ] Confirm immutable request fields.
- [ ] Confirm repository method signatures.
- [ ] Confirm parameterized SQL operations.
- [ ] Confirm transactional persistence.
- [ ] Confirm atomic optimistic concurrency behavior.
- [ ] Confirm browser-side concurrency handling.
- [ ] Confirm temporary browser state and persistent data boundaries.
- [ ] Confirm shared session behavior is accurately documented.
- [ ] Confirm HTTP error mappings.
- [ ] Confirm negative-path test evidence.
- [ ] Confirm final CI results.
- [ ] Reconcile requirements and acceptance-criteria traceability.
- [ ] Reconcile applicable architecture decision records.
- [ ] Confirm known limitations remain accurately documented.
- [ ] Record independent reviewer findings and dispositions.
- [ ] Record AI assistance and human verification.
- [ ] Confirm alignment with the approved a6-final-release revision.

Source-level documentation review does not replace automated testing, runtime verification, or final release authorization.

---

## 10. Related Engineering Evidence

### Architecture

- [Architecture Overview](architecture.md)
- [Architecture Diagram](architecture-diagram.png)
- [API Contracts](api-contracts.md)
- [Data Context](data-context.md)
- [ADR-006 — REST API Interface Alignment](../decisions/ADR-006-rest-api-interface-alignment.md)

### Requirements and Planning

- [Requirements](../requirements/requirements.md)
- [Acceptance Criteria](../requirements/acceptance-criteria.md)
- [Assumptions and Open Questions](../requirements/assumptions-open-questions.md)
- [Traceability](../planning/traceability.md)
- [Risk Register](../planning/risk-register.md)
- [Cycle 2 Maturity Plan](../planning/cycle2-maturity-plan.md)
- [Team Roles](../team/roles.md)

### Implementation

- [Browser Interface](../../src/main/resources/static/index.html)
- [Frontend Styling](../../src/main/resources/static/styles.css)
- [Browser Application Logic](../../src/main/resources/static/app.js)
- [RequestController](../../src/main/java/edu/loyola/campusconnect/controller/RequestController.java)
- [ApiExceptionHandler](../../src/main/java/edu/loyola/campusconnect/controller/ApiExceptionHandler.java)
- [WorkflowService](../../src/main/java/edu/loyola/campusconnect/service/WorkflowService.java)
- [WorkflowException](../../src/main/java/edu/loyola/campusconnect/service/WorkflowException.java)
- [RequestValidator](../../src/main/java/edu/loyola/campusconnect/validation/RequestValidator.java)
- [SupportRequest](../../src/main/java/edu/loyola/campusconnect/model/SupportRequest.java)
- [CreateRequestDto](../../src/main/java/edu/loyola/campusconnect/model/CreateRequestDto.java)
- [UpdateStatusDto](../../src/main/java/edu/loyola/campusconnect/model/UpdateStatusDto.java)
- [UpdateNoteDto](../../src/main/java/edu/loyola/campusconnect/model/UpdateNoteDto.java)
- [DemoIdentity](../../src/main/java/edu/loyola/campusconnect/model/DemoIdentity.java)
- [RequestRepository](../../src/main/java/edu/loyola/campusconnect/repository/RequestRepository.java)
- [JdbcRequestRepository](../../src/main/java/edu/loyola/campusconnect/repository/JdbcRequestRepository.java)

### Testing and Operations

- [Test Strategy](../testing/test-strategy.md)
- [HTTP API Guide](../testing/a4-http-api.md)
- [CI Evidence](../testing/ci-evidence.md)
- [A5 Acceptance Evidence Matrix](../testing/a5-acceptance-evidence-matrix.md)
- [Final Test Evidence](../testing/final-test-evidence.md)
- [Frontend Smoke Testing Script](../../scripts/frontend-smoke-test.sh)
- [Demonstration Data Script](../../scripts/demo-data.sh)
- [Known Limitations](../release/known-limitations.md)
- [Operational Runbook](../operations/runbook.md)
- [Observability Plan](../observability/observability-plan.md)
- [Runtime Evidence](../observability/runtime-evidence.md)
- [Permission Boundaries](../security/permission-boundaries.md)

### AI Governance

- [AI Use Policy](../ai/ai-policy.md)
- [AI Use Log](../ai/ai-use-log.md)

### Historical Review References

- PR #43 — Component Responsibilities and API Contracts.
- PR #44 — Test Strategy Alignment.
- PR #46 — Draft Requirements and Acceptance Criteria Updates.

### Cycle 2 Review References

- PR #116 — Architecture Overview.
- PR #117 — Architecture Diagram.
- PR #118 — API Contracts.
- PR #119 — Component Responsibilities.
- PR #120 — Data Context.
- PR #126 — HTML Browser Interface.
- PR #127 — Frontend Styling.
- PR #128 — Browser Application Logic.
- PR #129 — Frontend Integration Tests.
- PR #130 — Frontend Smoke Testing.
- PR #131 — Synthetic Demonstration Data Generation.

---

**Architecture Lead:** Shú Perez

**Backup:** Giancarlo Herrera

**Evidence Status:** Component responsibilities aligned with reviewed implementation source. Final integrated verification, independent review, and release authorization remain pending.

