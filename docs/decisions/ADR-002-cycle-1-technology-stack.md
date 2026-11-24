# ADR-002: Cycle 1 Technology Stack

---

## A6 Implementation Clarification

**Date:** 2026-11-24  
**Phase:** A6 — Cycle 2 Final Release  
**Architecture Owner:** Shú Perez  
**Backup:** Giancarlo Herrera  
**Review Status:** Source-level review completed; human approval pending  
**Related Decision:** [ADR-006 — REST API Interface Alignment](ADR-006-rest-api-interface-alignment.md)

### Purpose

This clarification records the relationship between the originally approved Cycle 1 technology stack and the implemented CampusConnect architecture reviewed during Assignment 6.

The original decision and its October 16, 2026 approval remain preserved as historical engineering evidence.

This clarification does not retroactively modify the original approval or independently authorize a new architecture decision.

### Approved Stack vs. Implemented Stack

| Technology or Component | Original Decision | Reviewed Implementation | Alignment |
|---|---|---|---|
| Java | Primary programming language | Java application | Aligned |
| Spring Boot | Backend framework | Spring Boot REST application | Aligned |
| Thymeleaf | Server-rendered HTML interface | REST HTTP endpoints with JSON | Implementation deviation |
| Maven | Builds, dependencies, and testing | Maven-based project | Aligned |
| File-backed H2 | Local persistent storage | File-backed H2 database | Aligned |
| Local execution | Classroom demonstration | Local application execution | Aligned |
| Synthetic identities | Simulated students and reviewers | Fixed identities stored in HTTP sessions | Aligned |
| Database access | Repository-based persistence | Spring JDBC and JdbcRequestRepository | Implementation clarification |
| Concurrent updates | Consistent request modifications | Transactional version-aware updates | Aligned with ADR-003 |

### Implemented Architecture

The reviewed implementation uses:

- Java.
- Spring Boot.
- Spring REST controllers.
- JSON request and response structures.
- Maven.
- Spring JDBC.
- File-backed H2.
- Server-side HTTP sessions.
- ETag and If-Match concurrency preconditions.

The implemented component structure includes:

- RequestController.
- RequestValidator.
- WorkflowService.
- SupportRequest.
- Request DTOs.
- RequestRepository.
- JdbcRequestRepository.
- DemoIdentity.
- WorkflowException.
- ApiExceptionHandler.

RequestController exposes the REST interface and delegates business operations to WorkflowService.

WorkflowService enforces validation, authorization, ownership, lifecycle rules, and transaction boundaries.

JdbcRequestRepository implements physical database operations using parameterized SQL statements.

### Interface Deviation

The original decision selected Thymeleaf for server-rendered student and reviewer workflows.

The reviewed implementation instead provides REST HTTP endpoints supporting the same core request workflow.

The implemented interface supports:

- Synthetic identity selection.
- Student request submission.
- Student-owned request retrieval.
- Reviewer request retrieval.
- Authorized status transitions.
- Resolution note modifications.
- Structured HTTP error responses.
- Version-aware updates.

A verified Thymeleaf browser interface and Post/Redirect/Get workflow are not established by the reviewed implementation.

This deviation is addressed through:

[ADR-006 — REST API Interface Alignment](ADR-006-rest-api-interface-alignment.md)

ADR-006 records the team's acceptance of the implemented REST interface for the local, synthetic-data Assignment 6 classroom release.

The November 24, 2026 team approval is recorded in ADR-006.

### Persistence Clarification

The approved file-backed H2 decision remains consistent with the reviewed implementation.

The application uses Spring JDBC rather than an in-memory map or a Spring Data JPA persistence layer.

The default database configuration is:

`jdbc:h2:file:./data/campusconnect`

The implemented persistence architecture includes:

- UUID-based request identifiers.
- Parameterized SQL statements.
- TransactionTemplate-managed operations.
- Stable student ownership.
- Persistent request status and resolution notes.
- Server-managed timestamps.
- Version-aware database updates.
- Rejection of conflicting modifications.

Additional persistence clarification is documented in:

[ADR-0005 — Clarify Cycle 1 JDBC Persistence](ADR-0005-in-memory-persistence.md)

The existing persistence and recovery requirements remain governed by ADR-003 and ADR-004.

### Security and Data Boundaries

The implemented technology stack remains restricted to a synthetic classroom demonstration.

The application does not establish:

- Production authentication.
- Institutional single sign-on.
- Real student identity verification.
- Production database security.
- Institutional compliance.
- Production deployment readiness.
- Verified disaster recovery.

The selected synthetic identity is maintained through the server-side HTTP session.

Client-provided identity headers and requester identifiers do not establish authoritative ownership or permissions.

Students may access their own requests, while Reviewer may retrieve and perform authorized modifications across synthetic student records.

These boundaries demonstrate application-level authorization rather than production authentication.

### Relationship to Existing Decisions

| Decision Record | Relationship |
|---|---|
| ADR-001 | Production technology decisions remain deferred |
| ADR-002 | Original approved Cycle 1 technology selection remains preserved |
| ADR-003 | Transactional consistency and concurrent-update requirements remain applicable |
| ADR-004 | Local persistence and recovery expectations remain applicable |
| ADR-0005 | JDBC persistence implementation clarification |
| ADR-006 | Supersedes the Thymeleaf interface choice for the A6 classroom release |

ADR-006 supersedes only the server-rendered interface requirement for the classroom release.

The remaining approved technology selections continue to apply.

### Source-Level Verification Evidence

The implementation clarification is supported by review of:

- [RequestController](../../src/main/java/edu/loyola/campusconnect/controller/RequestController.java)
- [WorkflowService](../../src/main/java/edu/loyola/campusconnect/service/WorkflowService.java)
- [RequestValidator](../../src/main/java/edu/loyola/campusconnect/validation/RequestValidator.java)
- [RequestRepository](../../src/main/java/edu/loyola/campusconnect/repository/RequestRepository.java)
- [JdbcRequestRepository](../../src/main/java/edu/loyola/campusconnect/repository/JdbcRequestRepository.java)
- [Database Schema](../../src/main/resources/schema.sql)
- [Application Configuration](../../src/main/resources/application.properties)

Source-level review supports the documented implementation structure.

No new automated tests, live HTTP verification, or database recovery tests were performed through this documentation update.

### Related Engineering Evidence

- [Architecture Overview](../architecture/architecture.md)
- [Architecture Diagram](../architecture/architecture-diagram.png)
- [API Contracts](../architecture/api-contracts.md)
- [Component Responsibilities](../architecture/component-responsibilities.md)
- [Data and Context Ownership](../architecture/data-context.md)
- [Requirements](../requirements/requirements.md)
- [Acceptance Criteria](../requirements/acceptance-criteria.md)
- [HTTP API Guide](../testing/a4-http-api.md)
- [Backend Verification](../testing/a4-backend-verification.md)
- [CI Evidence](../testing/ci-evidence.md)
- [Known Limitations](../release/known-limitations.md)
- [Operational Runbook](../operations/runbook.md)
- [PR #119 — Component Responsibilities Update](https://github.com/victordodan/-comp330-f26-team-02/pull/119)
- [PR #120 — Data and Context Ownership Update](https://github.com/victordodan/-comp330-f26-team-02/pull/120)

### Final Release Verification

- [x] Original approved technology decision preserved.
- [x] Implementation deviation identified.
- [x] Implemented persistence configuration reviewed.
- [x] REST interface decision documented.
- [ ] ADR-006 independently reviewed.
- [x] Team approval of interface deviation recorded in ADR-006.
- [ ] Architecture documentation reconciled against the exact final release revision.
- [ ] Runtime verification completed.
- [ ] Persistence and concurrency evidence confirmed.
- [ ] Known limitations reconciled.
- [ ] Final CI evidence confirmed.
- [ ] AI assistance and human verification recorded.
- [ ] Final release authorization recorded.

### Approval Status

**Original ADR-002 Decision:** Accepted — Cycle 1.

**Original Approval:** Victor Dodan, October 16, 2026.

**A6 Implementation Clarification:** Human review pending.

**A6 Interface Decision:** Accepted by the team in ADR-006.

**Final Implementation Verification:** Pending integrated release verification.

**Final Release Authorization:** Pending.

ADR-006 records the separate approval of the REST interface for A6.

### AI Assistance

[AI Use Log](../ai/ai-use-log.md)
