
# ADR-006: REST API Interface Alignment

**Project:** CampusConnect  
**Team:** Codex Ramblers — Team 2  
**Phase:** A6 — Cycle 2 Final Release  
**Status:** Accepted — A6 classroom release  
**Date:** 2026-11-24  
**Architecture Owner:** Shú Perez  
**Backup:** Giancarlo Herrera  
**Decision Owner:** Codex Ramblers Team  

---

## 1. Context

CampusConnect requires a complete student support request workflow supporting:

- Request submission.
- Input validation.
- Student request ownership.
- Reviewer access.
- Status transitions.
- Resolution note management.
- Persistent storage.
- Consistent failure handling.
- Optimistic concurrency.

ADR-001 deferred the final production technology stack decision.

ADR-002 established the approved Cycle 1 technology stack, including Java, Spring Boot, Thymeleaf, Maven, and file-backed H2.

However, the integrated implementation exposes REST HTTP endpoints rather than the originally proposed Thymeleaf server-rendered interface.

The implemented application uses JSON request and response structures, server-side synthetic identity selection, and HTTP clients for classroom demonstration.

This creates a documented difference between the historical architecture decision and the implemented interface.

Assignment 6 requires architecture documentation, implementation, verification evidence, and architecture decisions to remain consistent.

The team accepts the implemented Spring Boot REST API as the supported interface for the local, synthetic-data A6 classroom release. The original Cycle 1 decision remains part of the project record.

---

## 2. Decision

Retain the implemented Spring Boot REST API as the supported interface for the Assignment 6 classroom release.

The accepted release architecture consists of:

| Technology or Component | Responsibility |
|---|---|
| Java | Primary programming language |
| Spring Boot | Application framework and HTTP request processing |
| Spring REST controllers | HTTP routing and JSON interfaces |
| HTTP clients | Classroom interaction and demonstration |
| Maven | Dependency management, builds, and automated testing |
| Spring JDBC | Database access |
| File-backed H2 | Local persistent request storage |
| HTTP Session | Synthetic identity context |
| ETag and If-Match | Version-aware modification preconditions |

The originally proposed Thymeleaf server-rendered interface is not required for the reviewed REST-based classroom workflow under this decision.

This ADR does not approve a new production frontend, production deployment, or institutional authentication system.

### 2.1 Interface Boundaries

The supported interface exposes the following operations:

| HTTP Method | Endpoint | Responsibility |
|---|---|---|
| POST | /api/demo/identity | Select synthetic identity |
| POST | /api/requests | Create student request |
| GET | /api/requests | List owned student requests |
| GET | /api/requests/{id} | Retrieve owned student request |
| GET | /api/reviewer/requests | List all requests as Reviewer |
| GET | /api/reviewer/requests/{id} | Retrieve request as Reviewer |
| PATCH | /api/requests/{id}/status | Perform permitted status transition |
| PATCH | /api/requests/{id}/note | Save or replace resolution note |

The interface uses JSON request and response structures.

The detailed interface contracts remain documented in `docs/architecture/api-contracts.md`.

### 2.2 Component Boundaries

The implemented request-processing architecture consists of:

- RequestController.
- RequestValidator.
- WorkflowService.
- SupportRequest.
- Request DTOs.
- RequestRepository.
- JdbcRequestRepository.
- File-backed H2.
- DemoIdentity and HTTP Session.
- WorkflowException.
- ApiExceptionHandler.

RequestController delegates business operations to WorkflowService.

WorkflowService delegates submission validation to RequestValidator and storage operations to RequestRepository.

JdbcRequestRepository implements the physical persistence operations.

Authoritative authorization and workflow rules remain in WorkflowService.

### 2.3 Identity and Authorization

The REST interface retains the existing synthetic identity model:

- student-a
- student-b
- reviewer

The selected identity is stored in the server-side HTTP session.

Client-provided role headers and requester identifiers do not establish authoritative identity.

Students may create requests and retrieve their own records.

Reviewer may retrieve requests across both students and perform authorized modifications.

The demo identity selector is not production authentication.

### 2.4 Persistence and Concurrency

This decision retains the existing persistence and concurrency architecture.

WorkflowService coordinates transactional operations through Spring TransactionTemplate.

JdbcRequestRepository uses parameterized SQL statements and atomic version-aware updates.

Individual request responses expose the current version through ETag.

Reviewer modifications require If-Match.

Stale modifications are rejected rather than silently overwriting newer stored data.

This ADR does not supersede the persistence or concurrency requirements established by ADR-003 and ADR-004.

---

## 3. Alternatives Considered

### Alternative A: Retain the REST API

Use the existing implemented REST endpoints and demonstrate the workflow through compatible HTTP clients.

Advantages:

- Aligns with the current implementation.
- Avoids introducing additional frontend development.
- Supports direct HTTP verification.
- Makes request and response contracts inspectable.
- Supports explicit HTTP error handling.
- Supports ETag and If-Match concurrency demonstrations.
- Reduces late-cycle integration risk.

Disadvantages:

- Requires an HTTP client for direct interaction.
- Does not provide a polished student-facing browser interface.
- Requires clients to manage session cookies and version headers.

### Alternative B: Implement the Originally Proposed Thymeleaf Interface

Add server-rendered pages and browser-based form handling.

Advantages:

- Provides a more approachable interface for nontechnical users.
- Aligns with the original frontend selection in ADR-002.
- Could simplify manual demonstration for users unfamiliar with HTTP clients.

Disadvantages:

- Introduces additional implementation and testing work.
- Requires browser interaction and form-handling verification.
- Increases integration risk near the final release gate.
- Requires additional review of session handling and interface behavior.
- Is not established as completed functionality in the reviewed implementation.

### Alternative C: Introduce a Separate Frontend Application

Build a separate frontend that consumes the REST API.

Advantages:

- Separates frontend and backend development.
- Provides flexibility for future user interface improvements.
- Retains the REST API as a reusable application boundary.

Disadvantages:

- Introduces additional dependencies and build tooling.
- Requires frontend integration and deployment decisions.
- Increases testing and maintenance requirements.
- Expands the current classroom release scope.

---

## 4. Decision Rationale

The team selected Alternative A for the Assignment 6 classroom release.

The existing REST implementation supports the core student and reviewer workflow without requiring additional frontend development.

Retaining the implemented interface:

- Prioritizes the complete support request workflow.
- Preserves existing application behavior.
- Reduces late-cycle architectural changes.
- Supports direct verification of authorization and failure handling.
- Makes optimistic concurrency behavior inspectable.
- Aligns architecture documentation with actual source code.
- Avoids claiming unimplemented browser functionality.

This recommendation concerns the classroom release interface only.

Future production interface decisions remain deferred.

---

## 5. Benefits

- Improved architecture and implementation consistency.
- Clearer HTTP interface contracts.
- Reduced final release integration risk.
- Directly inspectable request and response behavior.
- Explicit HTTP failure responses.
- Clear separation of controller, validation, workflow, and persistence responsibilities.
- Continued use of the existing local database architecture.
- Reduced scope expansion near the final release gate.

---

## 6. Risks and Tradeoffs

| Risk or Tradeoff | Mitigation or Disposition |
|---|---|
| No verified browser-based student interface | Document as a known limitation |
| HTTP client requires technical familiarity | Provide documented demonstration procedures |
| Session cookies must be retained | Document identity selection and session handling |
| Clients must retain current ETags | Document If-Match requirements and reload behavior |
| Synthetic identity switching is not authentication | Restrict demonstration to synthetic data |
| Historical architecture differs from implementation | Preserve ADR-002 and document the approved interface deviation through this ADR |
| Final documentation may diverge from source | Verify against the exact release candidate |
| Runtime evidence may not match the final revision | Require final test and CI evidence |

The decision does not eliminate production authentication, deployment, recovery, or data retention limitations.

---

## 7. Consequences

- The REST API is the accepted classroom release interface.
- Thymeleaf is no longer a required interface component for this release.
- Architecture documentation must describe the implemented REST boundaries.
- API contracts must document actual endpoints, DTOs, response codes, and version preconditions.
- Component responsibilities must reflect actual Java classes.
- Data context documentation must reflect the implemented database schema and session model.
- Verification must address HTTP behavior and negative paths.
- Known limitations must disclose the absence of a verified browser interface.
- Future frontend implementation requires separate scope and architecture review.

No application source changes are introduced by this decision record alone.

---

## 8. Relationship to Existing Architecture Decisions

| Decision Record | Relationship |
|---|---|
| ADR-001 | Production technology decisions remain deferred |
| ADR-002 | Original approved technology selection is preserved; the Thymeleaf interface requirement is superseded for this classroom release |
| ADR-003 | Transactional consistency and optimistic concurrency requirements remain applicable |
| ADR-004 | Local persistence and recovery expectations remain applicable |
| ADR-0005 | Existing JDBC persistence clarification remains applicable and subject to its own review status |

This ADR does not invalidate the historical approval of ADR-002.

This ADR supersedes only the server-rendered interface requirement for the classroom release.

Java, Spring Boot, Maven, file-backed H2, local execution, and synthetic identity constraints remain consistent with the existing architecture decisions.

---

## 9. Requirements and Interface Traceability

| Requirement | REST Interface Support |
|---|---|
| REQ-001 | Student request submission |
| REQ-002 | Student-owned request visibility |
| REQ-003 | Unique request identifiers and persistence across application restarts |
| REQ-004 | Reviewer request visibility and permissions |
| REQ-005 | Authorized status transitions |
| REQ-006 | Authorized resolution note management |

Related logical contracts:

- API-001 — Request creation.
- API-002 — Student request retrieval.
- API-003 — Reviewer request retrieval.
- API-004 — Status modification.
- API-005 — Resolution note modification.
- API-006 — Repository persistence boundary.
- API-007 — Synthetic identity context.

Detailed acceptance-criteria mappings remain in the central requirements, API contracts, and testing evidence.

---

## 10. Verification Evidence

### 10.1 Source-Level Review

The implemented interface and supporting architecture were reviewed against:

- [RequestController](../../src/main/java/edu/loyola/campusconnect/controller/RequestController.java)
- [WorkflowService](../../src/main/java/edu/loyola/campusconnect/service/WorkflowService.java)
- [RequestValidator](../../src/main/java/edu/loyola/campusconnect/validation/RequestValidator.java)
- [SupportRequest](../../src/main/java/edu/loyola/campusconnect/model/SupportRequest.java)
- [RequestRepository](../../src/main/java/edu/loyola/campusconnect/repository/RequestRepository.java)
- [JdbcRequestRepository](../../src/main/java/edu/loyola/campusconnect/repository/JdbcRequestRepository.java)
- [ApiExceptionHandler](../../src/main/java/edu/loyola/campusconnect/controller/ApiExceptionHandler.java)
- [Database Schema](../../src/main/resources/schema.sql)
- [Application Configuration](../../src/main/resources/application.properties)

Source-level review supports the documented REST architecture.

It does not establish successful execution of the final release candidate.

### 10.2 Required Negative-Path Verification

| Scenario | Expected Result |
|---|---|
| Invalid request submission | HTTP 400 |
| Unsupported status transition | HTTP 400 |
| Resolution without saved nonblank note | HTTP 400 |
| Missing or unauthorized identity | HTTP 403 |
| Student attempts reviewer modification | HTTP 403 |
| Cross-student request retrieval | HTTP 404 |
| Unknown request identifier | HTTP 404 |
| Stale modification version | HTTP 412 |
| Missing required If-Match header | HTTP 428 |
| Storage or transaction failure | HTTP 503 |

These outcomes are implementation-aligned verification expectations, not newly executed test results.

### 10.3 Final Verification Checklist

- [ ] Confirm REST endpoint alignment.
- [ ] Confirm DTO and response structure alignment.
- [ ] Confirm student ownership isolation.
- [ ] Confirm reviewer-only modifications.
- [ ] Confirm negative-path behavior.
- [ ] Confirm ETag and If-Match behavior.
- [ ] Confirm transactional persistence.
- [ ] Confirm restart persistence.
- [ ] Confirm architecture diagram alignment.
- [ ] Confirm API contract alignment.
- [ ] Confirm component responsibility alignment.
- [ ] Confirm data context alignment.
- [ ] Confirm known limitations.
- [ ] Confirm final automated test results.
- [ ] Confirm CI evidence for the exact release revision.
- [ ] Complete independent human review.
- [x] Record team decision approval.
- [ ] Record AI assistance and human verification.

---

## 11. Related Engineering Evidence

### Architecture Decision Records

- [ADR-001 — Production Technology Stack Deferral](ADR-001-defer-production-technology-stack.md)
- [ADR-002 — Cycle 1 Technology Stack](ADR-002-cycle-1-technology-stack.md)
- [ADR-003 — Request Consistency](ADR-003-request-consistency.md)
- [ADR-004 — Local Persistence and Recovery](ADR-004-local-persistence-and-recovery.md)
- [ADR-0005 — JDBC Persistence Clarification](ADR-0005-in-memory-persistence.md)

### Architecture Documentation

- [Architecture Overview](../architecture/architecture.md)
- [Architecture Diagram](../architecture/architecture-diagram.png)
- [API Contracts](../architecture/api-contracts.md)
- [Component Responsibilities](../architecture/component-responsibilities.md)
- [Data and Context Ownership](../architecture/data-context.md)

### Requirements and Verification

- [Requirements](../requirements/requirements.md)
- [Acceptance Criteria](../requirements/acceptance-criteria.md)
- [Traceability](../planning/traceability.md)
- [Risk Register](../planning/risk-register.md)
- [HTTP API Guide](../testing/a4-http-api.md)
- [Backend Verification](../testing/a4-backend-verification.md)
- [CI Evidence](../testing/ci-evidence.md)
- [Known Limitations](../release/known-limitations.md)
- [Operational Runbook](../operations/runbook.md)

### Related Pull Requests

- [PR #43](https://github.com/victordodan/-comp330-f26-team-02/pull/43) — Original Component Responsibilities and API Contracts.
- [PR #48](https://github.com/victordodan/-comp330-f26-team-02/pull/48) — Historical Architecture Overview.
- [PR #81](https://github.com/victordodan/-comp330-f26-team-02/pull/81) — Persistence Decision Proposal.
- [PR #119](https://github.com/victordodan/-comp330-f26-team-02/pull/119) — Assignment 6 Component Responsibilities Update.
- [PR #120](https://github.com/victordodan/-comp330-f26-team-02/pull/120) — Assignment 6 Data and Context Ownership Update.

---

## 12. Approval

**Decision Owner:** Codex Ramblers Team

**Architecture Owner:** Shú Perez

**Backup:** Giancarlo Herrera

**Team Lead:** Victor Dodan

**Decision Status:** Accepted — A6 classroom release

**Independent technical review:** Pending

**Team Approval:** Giancarlo Herrera, Victor Dodan (team lead), Shú Perez, and Venkata Aravind Mareddy. Agreement reported by Victor Dodan.

**Approval Recorded:** November 24, 2026

**Implementation Verification:** Source-level review completed; final runtime verification pending.

**Release Authorization:** Pending final Assignment 6 release-gate review.

---

## 13. AI Assistance

ChatGPT assisted with comparing the historical architecture decision against the reviewed implementation and drafting this proposed decision record.

AI assistance supported:

- Identification of architecture and implementation divergence.
- Documentation of alternatives and tradeoffs.
- Component and interface traceability.
- Preparation of verification requirements.

Independent technical review and final release verification remain pending.

No new runtime tests, source code changes, or release approvals were performed through this documentation activity.

Record this assistance and actual human verification in:

[AI Use Log](../ai/ai-use-log.md)



---

## 14. Cycle 2 Amendment — Browser Presentation Layer

### 14.1 Amendment Information

| Field | Details |
|---|---|
| Amendment Date | November 24, 2026 |
| Release Phase | Assignment 6 — Cycle 2 |
| Architecture Owner | Shú Perez |
| Decision | Extend the existing REST architecture with a same-origin browser presentation layer |
| Application Address | `http://campusconnect.local:8080` |
| Implementation Status | Implemented |
| Scope | Local classroom demonstration |
| Team Approval | Frontend scope approved as documented in the Cycle 2 maturity plan |
| Final Release Authorization | Pending final release-gate review |

This amendment extends the accepted REST architecture documented in ADR-006.

The original REST architecture decision and its recorded approval remain preserved.

The amendment does not replace the existing backend architecture or introduce a separate production technology stack.

---

### 14.2 Context and Motivation

The original implementation provided a complete support request workflow through REST HTTP endpoints.

Although the REST interface supported the required application behavior, users needed an HTTP client or scripted requests to interact with the system.

This introduced a usability limitation for classroom demonstrations and ordinary student and reviewer interactions.

The team approved a lightweight browser presentation layer to improve application usability while preserving the implemented REST backend.

The approved frontend scope is documented in:

[Cycle 2 Maturity Plan](../planning/cycle2-maturity-plan.md)

The objective is to provide a usable graphical interface without introducing unnecessary architectural complexity or replacing verified backend behavior.

---

### 14.3 Architectural Decision

Extend the existing Spring Boot application with a same-origin browser client implemented using:

- HTML.
- CSS.
- Vanilla JavaScript.
- Browser Fetch API.
- Existing REST endpoints.
- Existing server-side HTTP sessions.

The browser presentation layer is served directly by the Spring Boot application.

Frontend resources are located in:

`src/main/resources/static/`

The browser client communicates with the existing backend using HTTP requests and JSON representations.

No separate frontend application server, JavaScript framework, package manager, or additional frontend build pipeline is required.

The existing Spring Boot REST API remains the authoritative application interface.

---

### 14.4 Updated Architectural Structure

```mermaid
flowchart TD
    Student[Student Requester]
    Reviewer[Support Reviewer]

    Student --> Browser
    Reviewer --> Browser

    subgraph Presentation["Browser Presentation Layer"]
        Browser[Web Browser]
        HTML[index.html]
        CSS[styles.css]
        JS[app.js]

        Browser --> HTML
        HTML --> CSS
        HTML --> JS
    end

    subgraph Backend["Spring Boot Backend"]
        Controller[RequestController]
        Session[HTTP Session / DemoIdentity]
        Service[WorkflowService]
        Validator[RequestValidator]
        Domain[SupportRequest]
        Repository[RequestRepository]
        JDBC[JdbcRequestRepository]
        Errors[ApiExceptionHandler]

        Controller --> Session
        Controller --> Service
        Service --> Validator
        Service --> Domain
        Service --> Repository
        Repository --> JDBC
        Controller -. Failure Handling .-> Errors
        Service -. Failure Handling .-> Errors
    end

    JS -->|Same-origin HTTP / JSON| Controller
    Controller -->|HTTP Responses / ETags| JS

    JDBC --> Database[(File-backed H2)]
```

The browser presentation layer introduces an additional interaction boundary without transferring authoritative application responsibilities away from the backend.

---

### 14.5 Frontend Component Responsibilities

| Component | Responsibility | Implementation |
|---|---|---|
| Browser interface | Provides the graphical interaction structure | `index.html` |
| Presentation styling | Provides layout, responsive design, status indicators, and visual feedback | `styles.css` |
| Browser application logic | Coordinates interface state, HTTP requests, user actions, and response handling | `app.js` |
| HTTP client | Communicates with the existing REST endpoints | Browser Fetch API |
| Session handling | Associates API operations with the selected synthetic identity | Existing server-side HTTP session |
| Backend controller | Receives and processes HTTP requests | `RequestController.java` |
| Workflow service | Enforces application workflow and authorization rules | `WorkflowService.java` |
| Persistence layer | Stores and retrieves authoritative request records | `JdbcRequestRepository.java` |

The browser components are responsible for presentation and interaction coordination.

The backend remains responsible for:

- Authorization.
- Request ownership.
- Input validation.
- Workflow transition enforcement.
- Resolution note requirements.
- Concurrency control.
- Transaction management.
- Persistent storage.
- Authoritative error responses.

---

### 14.6 Frontend Implementation Artifacts

| Artifact | Purpose | Related PR |
|---|---|---|
| [`index.html`](../../src/main/resources/static/index.html) | Browser interface and dashboard structure | [#126](https://github.com/victordodan/-comp330-f26-team-02/pull/126) |
| [`styles.css`](../../src/main/resources/static/styles.css) | Responsive presentation and visual feedback | [#127](https://github.com/victordodan/-comp330-f26-team-02/pull/127) |
| [`app.js`](../../src/main/resources/static/app.js) | Browser workflow and REST integration | [#128](https://github.com/victordodan/-comp330-f26-team-02/pull/128) |
| [`FrontendIntegrationTest.java`](../../src/test/java/edu/loyola/campusconnect/controller/FrontendIntegrationTest.java) | Frontend integration verification | [#129](https://github.com/victordodan/-comp330-f26-team-02/pull/129) |
| [`frontend-smoke-test.sh`](../../scripts/frontend-smoke-test.sh) | Runtime HTTP smoke verification | [#130](https://github.com/victordodan/-comp330-f26-team-02/pull/130) |
| [`demo-data.sh`](../../scripts/demo-data.sh) | Synthetic demonstration data preparation | [#131](https://github.com/victordodan/-comp330-f26-team-02/pull/131) |

---

### 14.7 Browser-to-REST Interface

The browser presentation layer uses the following existing REST endpoints.

| Method | Endpoint | Browser Responsibility |
|---|---|---|
| POST | `/api/demo/identity` | Select a synthetic identity |
| POST | `/api/requests` | Submit a student support request |
| GET | `/api/requests` | Retrieve the selected student's requests |
| GET | `/api/reviewer/requests` | Retrieve the reviewer queue |
| GET | `/api/reviewer/requests/{id}` | Retrieve current reviewer request details and version information |
| PATCH | `/api/requests/{id}/status` | Submit a conditional workflow status update |
| PATCH | `/api/requests/{id}/note` | Submit a conditional resolution note update |

The existing student request-detail endpoint remains available through the REST API but is not directly required by the implemented browser workflow.

The browser presentation layer does not introduce additional business API endpoints.

The existing API contracts remain authoritative.

Reference:

[API Contracts](../architecture/api-contracts.md)

---

### 14.8 Student Interaction Workflow

The student-facing browser interface supports:

1. Selecting Student A or Student B.
2. Loading the selected student's dashboard.
3. Entering request information.
4. Submitting a support request.
5. Receiving operation feedback.
6. Retrieving owned support requests.
7. Filtering requests by workflow status.
8. Viewing current request information.
9. Viewing saved resolution notes.
10. Refreshing the request list.

```mermaid
sequenceDiagram
    actor Student
    participant Browser
    participant API as REST Controller
    participant Service as Workflow Service
    participant DB as H2 Database

    Student->>Browser: Select synthetic identity
    Browser->>API: POST /api/demo/identity
    API-->>Browser: Selected identity

    Student->>Browser: Submit support request
    Browser->>API: POST /api/requests
    API->>Service: Validate and create request
    Service->>DB: Persist owned request
    DB-->>Service: Storage completed
    Service-->>API: Created request
    API-->>Browser: HTTP 201 and ETag

    Browser->>API: GET /api/requests
    API->>Service: Retrieve owned requests
    Service->>DB: Query by student identity
    DB-->>Service: Owned records
    Service-->>API: Request list
    API-->>Browser: HTTP 200
    Browser-->>Student: Updated dashboard
```

Request ownership is determined by the server-side session rather than a browser-supplied ownership value.

---

### 14.9 Reviewer Interaction Workflow

The reviewer-facing browser interface supports:

1. Selecting the Reviewer identity.
2. Loading the reviewer request queue.
3. Inspecting submitted support requests.
4. Filtering requests by workflow status.
5. Moving eligible requests to In Progress.
6. Entering and saving resolution notes.
7. Resolving eligible requests.
8. Reopening resolved requests.
9. Refreshing current request information.
10. Receiving operation and conflict feedback.

The browser follows the existing workflow transition rules.

```mermaid
stateDiagram-v2
    [*] --> Submitted
    Submitted --> InProgress: Begin review
    InProgress --> Resolved: Resolve with saved note
    Resolved --> InProgress: Reopen
```

The backend remains responsible for determining whether a requested transition is permitted.

Resolution notes are saved separately from status updates.

The browser does not bypass the backend requirement for a saved nonblank resolution note before resolution.

---

### 14.10 Identity and Security Boundaries

The browser presentation layer uses the existing synthetic identity mechanism.

Supported identities remain:

- `student-a`
- `student-b`
- `reviewer`

The selected identity is maintained in the server-side HTTP session.

Browser controls may hide operations that are unavailable to the selected identity.

However, hiding an interface control does not constitute authorization.

Every protected operation remains subject to backend role and ownership checks.

The browser presentation layer does not introduce:

- Production authentication.
- University single sign-on.
- Real student account management.
- Institutional identity verification.
- Public deployment authorization.

Any caller who can access the synthetic identity selector can deliberately select Reviewer.

Multiple browser tabs may share the same HTTP session.

Separate browser profiles should be used when demonstrating independent identities.

The application remains restricted to synthetic classroom data and local operation.

References:

- [Permission Boundaries](../security/permission-boundaries.md)
- [Known Limitations](../release/known-limitations.md)
- [Request Controller](../../src/main/java/edu/loyola/campusconnect/controller/RequestController.java)

---

### 14.11 Browser State and Data Ownership

The browser maintains temporary presentation state, including:

- Displayed identity.
- Loaded request records.
- Status filters.
- Form input.
- Unsaved resolution note text.
- Loading state.
- Operation feedback.

Temporary browser state is not authoritative persistent application data.

The server remains authoritative for:

- Request identifiers.
- Student ownership.
- Request content.
- Workflow status.
- Saved resolution notes.
- Creation and modification timestamps.
- Record versions.

The browser does not introduce an independent persistent request database.

Unsaved input may be lost when the interface refreshes, changes identity, or re-renders relevant components.

Reference:

[Data Context](../architecture/data-context.md)

---

### 14.12 Optimistic Concurrency Integration

The browser presentation layer preserves the existing optimistic concurrency architecture.

Before submitting a reviewer modification, the browser retrieves current request details and associated ETag information.

The browser compares the retrieved version with the version originally displayed to the reviewer.

If the versions differ, the browser prevents the stale modification and reports a conflict.

Eligible modifications include the applicable `If-Match` header.

The backend performs the authoritative conditional update.

```mermaid
sequenceDiagram
    actor Reviewer
    participant Browser
    participant API as REST Controller
    participant Service as Workflow Service
    participant DB as H2 Database

    Reviewer->>Browser: Modify request
    Browser->>API: GET reviewer request details
    API-->>Browser: Current request and ETag

    alt Displayed version differs
        Browser-->>Reviewer: Conflict feedback
    else Displayed version matches
        Browser->>API: PATCH with If-Match
        API->>Service: Conditional modification
        Service->>DB: Update using expected version

        alt Expected version matches
            DB-->>Service: Update completed
            Service-->>API: Updated request
            API-->>Browser: HTTP 200 and new ETag
            Browser-->>Reviewer: Success feedback
        else Expected version is stale
            DB-->>Service: Conditional update rejected
            Service-->>API: Conflict
            API-->>Browser: HTTP 412
            Browser-->>Reviewer: Refresh and review conflict
        end
    end
```

Browser-side version comparison improves interaction feedback.

It does not replace the atomic backend consistency mechanism.

References:

- [ADR-003 — Request Consistency](ADR-003-request-consistency.md)
- [API Contracts](../architecture/api-contracts.md)

---

### 14.13 Error Handling

The browser presentation layer communicates backend operation outcomes through visible interface feedback.

| HTTP Response | Meaning | Browser Handling |
|---|---|---|
| 200 | Successful retrieval or modification | Display or refresh current information |
| 201 | Successful request creation | Confirm submission and refresh request list |
| 400 | Invalid input or unsupported operation | Display validation or operation feedback |
| 403 | Missing or unauthorized identity | Display access restriction and reconcile interface state |
| 404 | Missing or inaccessible resource | Display resource-unavailable feedback |
| 412 | Stale request version | Report conflict and require refreshed information |
| 428 | Missing update precondition | Report the failed update requirement |
| 503 | Storage or service failure | Report unsuccessful operation without claiming persistence |

The browser does not represent a failed backend operation as a confirmed successful modification.

A successful modification and a subsequent failed dashboard refresh are distinct operation outcomes.

---

### 14.14 Alternatives Considered

This amendment supplements the alternatives considered in the original ADR.

| Alternative | Advantages | Disadvantages | Disposition |
|---|---|---|---|
| REST API without graphical client | Minimal implementation complexity | Requires manual HTTP interaction | Retained as an available interface, but extended |
| Thymeleaf server-rendered interface | Integrated server-side rendering | Requires additional template and rendering implementation | Not selected |
| Separate frontend application | Greater frontend deployment independence | Additional dependencies, deployment configuration, and integration complexity | Not selected |
| Same-origin static HTML/CSS/JavaScript | Reuses existing REST backend, lightweight deployment, minimal dependencies | Requires explicit browser state and interaction handling | Selected |

The selected approach improves usability while preserving the established backend architecture and limiting additional deployment complexity.

---

### 14.15 Verification Evidence

#### A. Local Frontend Runtime Verification

Execution date: November 24, 2026.

Operator: Shú Perez.

Recorded local checkout:

`f09bd551456c539c92b185dcbbabc0d29f192be6`

| Verification Item | Observed Result |
|---|---:|
| Frontend smoke checks passed | 38 |
| Frontend smoke checks failed | 0 |
| Synthetic demonstration requests created | 6 |
| Confirmed reviewer updates | 10 |
| Student A demonstration records retrieved | 3 |
| Student B demonstration records retrieved | 3 |
| Reviewer demonstration records retrieved | 6 |

The observed results support frontend resource availability, synthetic session permissions, request creation, reviewer modifications, and role-specific demonstration record visibility.

The recorded checkout identifies the local source checkout reported during execution rather than independently proving the exact running binary.

#### B. Integrated Browser Candidate Verification

The team recorded additional browser candidate verification at revision:

`373a0f6`

The recorded evidence includes:

- 38 passing HTTP smoke checks.
- Chromium browser workflow verification.
- Student request submission.
- Student request isolation.
- Reviewer modifications.
- Stale-edit rejection.
- Owner visibility.
- Repeated demonstration setup that skipped all six existing matching records.

These results extend the available verification beyond the initial local HTTP smoke execution.

Reference:

[Runtime Evidence — Earlier Browser Candidate](../observability/runtime-evidence.md#earlier-browser-candidate)

#### C. Integrated Build and Operational Verification

The team recorded additional verification at:

`0860160bc458af670c7944ccd035d21425e7511d`

| Verification Item | Recorded Result |
|---|---|
| Maven regression suite | 51 passed |
| Test failures | 0 |
| Test errors | 0 |
| Test skips | 0 |
| Packaged HTTP workflow checks | 36 passed |
| Packaged restart checks | 5 passed |
| Windows manual workflow checks | 10 of 10 passed, as reported by Giancarlo Herrera |
| Same-store restart | Saved request retained |

The Linux build, HTTP workflow, and restart checks were performed by Codex.

Giancarlo Herrera performed and reported the separate Windows browser and restart exercise.

References:

- [CI Execution](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/45543404188)
- [Runtime Evidence](../observability/runtime-evidence.md)
- [Workflow Transcript](../../test-evidence/a6-operations-0860160/workflow.jsonl)
- [Restart Transcript](../../test-evidence/a6-operations-0860160/restart.jsonl)

These verification records retain their original execution operators, environments, and tested revisions.

They must not be represented as verification of a different final release revision without appropriate reconciliation.

---

### 14.16 Architectural Consequences

Positive consequences:

- Improved application usability.
- Browser-based student and reviewer interaction.
- Simplified classroom demonstration.
- Reuse of the existing REST architecture.
- Preservation of backend authorization.
- Preservation of transactional persistence.
- Preservation of optimistic concurrency.
- No separate frontend deployment service.
- No additional frontend package management.
- Additional frontend verification capabilities.

Residual consequences:

- Browser state requires explicit synchronization.
- Unsaved interface input may be lost.
- Shared browser sessions require careful identity handling.
- Browser-side error handling requires maintenance.
- Comprehensive accessibility verification remains outstanding.
- Production authentication remains outside scope.
- Backup restoration remains unverified.
- Demonstration setup modifies persistent synthetic records.
- Duplicate detection is not atomic across concurrent setup executions.

These consequences are documented further in:

[Known Limitations](../release/known-limitations.md)

---

### 14.17 Architecture Reconciliation Checklist

- [x] Existing REST architecture retained.
- [x] Browser presentation layer implemented.
- [x] Frontend implementation artifacts identified.
- [x] Browser-to-REST integration documented.
- [x] Backend authorization boundary preserved.
- [x] Synthetic identity restrictions documented.
- [x] Browser state and persistent data responsibilities distinguished.
- [x] Optimistic concurrency integration documented.
- [x] Local frontend runtime evidence recorded.
- [x] Integrated browser candidate evidence identified.
- [x] Integrated regression evidence identified.
- [x] Same-store restart evidence identified.
- [ ] Architecture overview reconciled with this amendment.
- [ ] Architecture diagram reconciled with this amendment.
- [ ] Component responsibilities reconciled with this amendment.
- [ ] API contracts reconciled with this amendment.
- [ ] Data context reconciled with this amendment.
- [ ] Independent human architecture review recorded.
- [ ] Final release revision and CI evidence reconciled.
- [ ] Final release authorization recorded.

The unchecked items identify remaining documentation and release reconciliation responsibilities.

They do not establish implementation defects.

---

### 14.18 Related Engineering Evidence

- [Architecture Overview](../architecture/architecture.md)
- [Architecture Diagram](../architecture/architecture-diagram.png)
- [Component Responsibilities](../architecture/component-responsibilities.md)
- [API Contracts](../architecture/api-contracts.md)
- [Data Context](../architecture/data-context.md)
- [Cycle 2 Maturity Plan](../planning/cycle2-maturity-plan.md)
- [Permission Boundaries](../security/permission-boundaries.md)
- [Runtime Evidence](../observability/runtime-evidence.md)
- [Operational Runbook](../operations/runbook.md)
- [Final Testing Evidence](../testing/final-test-evidence.md)
- [Known Limitations](../release/known-limitations.md)
- [Final Release Notes](../release/final-release-notes.md)
- [Changed Files Since Assignment 5](../release/changed-files-since-a5.md)
- [AI Use Log](../ai/ai-use-log.md)

---

### 14.19 Amendment Approval and Accountability

The Cycle 2 maturity plan records team approval of the lightweight HTML, CSS, and JavaScript browser client.

This amendment documents that approved implementation direction and reconciles it with the original REST architecture decision.

The original ADR approval remains preserved in Section 12.

Approval of the frontend implementation scope does not independently establish approval of this documentation amendment or final release authorization.

| Approval Item | Status |
|---|---|
| Original REST architecture decision | Accepted |
| Browser presentation layer scope | Team-approved, as recorded in the maturity plan |
| Browser presentation implementation | Completed |
| Local frontend runtime verification | Completed |
| Integrated browser candidate verification | Recorded |
| Integrated build and restart verification | Recorded |
| Independent review of this amendment | Pending |
| Final architecture reconciliation | Pending |
| Final release authorization | Pending |

The architecture owner is responsible for maintaining consistency between the approved architecture, implemented system, verification evidence, and documented technical limitations.

The team lead retains responsibility for final release authorization.

---

### 14.20 AI Assistance Disclosure

ChatGPT assisted with drafting this architecture amendment, documenting component boundaries, organizing verification evidence, and describing browser-to-REST interaction. 

The amendment incorporates existing implementation information, the documented team-approved frontend scope, local runtime results, and additional repository-recorded verification evidence, and no additional runtime tests were performed through preparation of this amendment.

AI assistance does not constitute independent human architecture review, formal risk acceptance, or final release authorization. The corresponding AI assistance and actual human verification must be recorded in:

[AI Use Log](../ai/ai-use-log.md)
