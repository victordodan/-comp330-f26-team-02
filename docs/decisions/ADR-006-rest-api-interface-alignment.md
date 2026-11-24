
# ADR-006: REST API Interface Alignment

**Project:** CampusConnect  
**Team:** Codex Ramblers — Team 2  
**Phase:** A6 — Cycle 2 Final Release  
**Status:** Proposed — Human Review and Approval Pending  
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

This ADR proposes formally accepting the implemented REST interface as the classroom release architecture while preserving the original historical decision.

---

## 2. Decision

Retain the implemented Spring Boot REST API as the supported interface for the Assignment 6 classroom release.

The proposed release architecture consists of:

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

The originally proposed Thymeleaf server-rendered interface is not required for the reviewed REST-based classroom workflow under this proposed decision.

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

Alternative A is recommended for the Assignment 6 classroom release.

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
| Historical architecture differs from implementation | Preserve ADR-002 and document the proposed deviation through this ADR |
| Final documentation may diverge from source | Verify against the exact release candidate |
| Runtime evidence may not match the final revision | Require final test and CI evidence |

The decision does not eliminate production authentication, deployment, recovery, or data retention limitations.

---

## 7. Consequences

If approved:

- The REST API becomes the accepted classroom release interface.
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
| ADR-002 | Original approved technology selection is preserved; the Thymeleaf interface requirement is proposed for partial supersession |
| ADR-003 | Transactional consistency and optimistic concurrency requirements remain applicable |
| ADR-004 | Local persistence and recovery expectations remain applicable |
| ADR-0005 | Existing JDBC persistence clarification remains applicable and subject to its own review status |

This ADR does not invalidate the historical approval of ADR-002.

Upon explicit team approval, this ADR supersedes only the server-rendered interface requirement for the classroom release.

Java, Spring Boot, Maven, file-backed H2, local execution, and synthetic identity constraints remain consistent with the existing architecture decisions.

---

## 9. Requirements and Interface Traceability

| Requirement | REST Interface Support |
|---|---|
| REQ-001 | Student request submission |
| REQ-002 | Student-owned request visibility |
| REQ-003 | Validated and persistent request creation |
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
- [ ] Record team decision approval.
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

**Decision Status:** Proposed

**Human Review:** Pending

**Team Approval:** Pending

**Approval Date:** Pending

**Implementation Verification:** Source-level review completed; final runtime verification pending.

**Release Authorization:** Pending final Assignment 6 release-gate review.

This decision must not be represented as accepted until explicit team approval is recorded.

---

## 13. AI Assistance

ChatGPT assisted with comparing the historical architecture decision against the reviewed implementation and drafting this proposed decision record.

AI assistance supported:

- Identification of architecture and implementation divergence.
- Documentation of alternatives and tradeoffs.
- Component and interface traceability.
- Preparation of verification requirements.

Independent human review and team approval remain required.

No new runtime tests, source code changes, or release approvals were performed through this documentation activity.

Record this assistance and actual human verification in:

[AI Use Log](../ai/ai-use-log.md)
