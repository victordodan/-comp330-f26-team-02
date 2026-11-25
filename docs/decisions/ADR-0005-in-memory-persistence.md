# ADR-0005: Clarify Cycle 1 JDBC Persistence

---

## A6 Implementation and Evidence Reconciliation

**Date:** 2026-11-24  
**Phase:** A6 — Cycle 2 Final Release  
**Review Status:** Source-level review completed; independent human review pending  
**Architecture Owner:** Shú Perez  
**Backup:** Giancarlo Herrera

### 1. Purpose

This clarification reconciles the existing ADR-0005 persistence correction with the Assignment 6 architecture documentation and final release verification requirements.

The original PR #81 proposal described an in-memory persistence architecture that did not represent the integrated implementation.

The existing ADR-0005 correction accurately identifies transactional Spring JDBC and file-backed H2 as the implemented persistence architecture.

This Assignment 6 update preserves that correction, its historical review baseline, and its historical verification evidence.

It does not establish a previously approved in-memory persistence decision, introduce a new persistence technology, or independently authorize the final release.

The existing filename is retained to preserve references from related engineering artifacts.

---

### 2. Historical Proposal vs. Implemented Architecture

| Architectural Concern | Original PR #81 Proposal | Reviewed Implementation |
|---|---|---|
| Request storage | ConcurrentHashMap | File-backed H2 |
| Request identifiers | AtomicLong sequence | Server-generated UUIDs |
| Persistence timing | H2 deferred to Cycle 2 | H2 implemented during Cycle 1 |
| Database access | In-memory collection operations | Spring JDBC |
| Repository implementation | Proposed in-memory storage | JdbcRequestRepository |
| Transaction management | In-memory operation coordination | Spring TransactionTemplate |
| Concurrent updates | In-memory coordination | Atomic database version checks |
| Restart persistence | Records lost on process exit | Records retained when using the same database location |
| JPA migration | Potential future persistence approach | Not required by the implemented architecture |

The original in-memory proposal must not be represented as the authoritative implementation.

The reviewed JDBC architecture remains consistent with the accepted decisions established in ADR-002, ADR-003, and ADR-004.

---

### 3. Implemented Persistence Architecture

The implemented persistence architecture consists of:

- Java.
- Spring Boot.
- Spring JDBC.
- Spring TransactionTemplate.
- Spring JdbcTemplate.
- RequestRepository.
- JdbcRequestRepository.
- File-backed H2.
- Server-generated UUID identifiers.
- Server-managed request timestamps.
- Server-managed request versions.
- Atomic version-aware database updates.

The application does not require a separate external database server for its intended classroom execution.

The reviewed implementation does not require Spring Data JPA or Hibernate-based persistence.

A migration to JPA, an external database service, or another storage architecture would require separate architectural justification, review, and verification.

No such migration is established as an approved Assignment 6 commitment by this clarification.

---

### 4. Component Responsibilities

| Component | Persistence Responsibility |
|---|---|
| RequestController | Receives HTTP requests, processes modification preconditions, and returns current ETags |
| RequestValidator | Validates and normalizes request creation fields |
| WorkflowService | Enforces business rules, authorization, ownership, transactions, and version checks |
| SupportRequest | Represents the immutable current request state |
| RequestRepository | Defines the persistence abstraction |
| JdbcRequestRepository | Implements physical database operations |
| Spring TransactionTemplate | Coordinates transactional operations |
| Spring JdbcTemplate | Executes parameterized SQL |
| File-backed H2 | Stores authoritative persistent request records |
| WorkflowException | Represents application-level failures |
| ApiExceptionHandler | Translates handled failures into structured HTTP responses |

The persistence architecture separates business rules from physical database operations.

WorkflowService depends on the RequestRepository abstraction rather than directly implementing SQL statements.

JdbcRequestRepository implements the database-specific persistence operations.

---

### 5. Repository Contract

The reviewed RequestRepository interface defines the following operations:

| Repository Operation | Responsibility |
|---|---|
| insert(SupportRequest request) | Persist a newly created request |
| findById(String id) | Retrieve an individual request |
| findByStudentID(String studentID) | Retrieve requests associated with a synthetic student |
| findAll() | Retrieve all stored requests |
| replace(SupportRequest request, long expectedVersion) | Perform a version-aware replacement |

The repository does not independently establish requester authorization.

Authorization and ownership enforcement remain responsibilities of WorkflowService.

JdbcRequestRepository uses parameterized SQL statements for database operations.

The repository contract supports the existing request workflow without requiring an in-memory persistence implementation.

---

### 6. Database Configuration and Schema

#### 6.1 Runtime Configuration

The reviewed default datasource configuration is:

`jdbc:h2:file:./data/campusconnect`

Additional reviewed defaults include:

| Configuration | Default |
|---|---|
| Application bind address | 127.0.0.1 |
| Application port | 8080 |
| SQL initialization mode | Always |
| H2 browser console | Disabled |

The database path is relative to the application's working directory.

Restarting the application from a different working directory or using a different datasource configuration may select a different database.

Source:

[Application Configuration](../../src/main/resources/application.properties)

#### 6.2 Stored Request Data

The support_request database table stores:

- Request identifier.
- Student identifier.
- Title.
- Category.
- Description.
- Current status.
- Current resolution note.
- Creation timestamp.
- Last-updated timestamp.
- Current version.

The request identifier is the database primary key.

The version field supports optimistic concurrency.

The schema stores current request state rather than a complete historical audit trail.

Source:

[Database Schema](../../src/main/resources/schema.sql)

Detailed data ownership and field constraints are documented in:

[Data and Context Ownership](../architecture/data-context.md)

---

### 7. Transactional Consistency

WorkflowService coordinates persistence operations using Spring TransactionTemplate.

The service validates applicable business rules before confirming successful operations.

JdbcRequestRepository performs physical storage operations through Spring JdbcTemplate.

The reviewed implementation supports:

- Transactional request creation.
- Transactional request retrieval.
- Transactional status modifications.
- Transactional resolution note modifications.
- Rollback of failed transactional writes.
- Storage failure reporting.
- Preservation of previously committed state when applicable transactions fail.

Database access and transaction exceptions are translated into STORAGE_ERROR application failures.

ApiExceptionHandler maps storage failures to HTTP 503 Service Unavailable.

A failed storage operation must not be represented as a successful creation or modification.

A failed listing operation must not be represented as a successful empty result.

Final execution evidence must confirm the behavior against the exact Assignment 6 release revision.

---

### 8. Optimistic Concurrency

Each stored request contains a server-managed version.

New requests begin at version 0.

Successful status and resolution note modifications increment the version.

RequestController returns the current version through an ETag response header.

Reviewer modifications require an If-Match header identifying the expected version.

WorkflowService checks the expected version against the current stored request.

JdbcRequestRepository performs an atomic database update matching both request identifier and expected version.

The implemented update follows this structure:

```sql
UPDATE support_request
SET status = ?,
    resolution_note = ?,
    updated_at = ?,
    version = ?
WHERE id = ? AND version = ?
```

The repository reports successful replacement only when exactly one row is updated.

A conflicting update must not silently overwrite newer stored request data.

The application returns HTTP 412 Precondition Failed when the expected version no longer matches the current version.

The same request version governs both status and resolution note modifications.

Additional concurrency requirements are documented in:

[ADR-003 — Request Consistency and Concurrent Updates](ADR-003-request-consistency.md)

---

### 9. Persistence and Recovery Boundaries

The implemented file-backed H2 architecture supports ordinary restart persistence when the application uses the same database location.

Persistent request records retain:

- Request identifiers.
- Student ownership.
- Request content.
- Current status.
- Resolution notes.
- Timestamps.
- Current versions.

The selected HTTP session identity is separate from persistent request ownership.

The reviewed implementation does not independently establish:

- Automated database backups.
- Verified backup restoration.
- Automated disaster recovery.
- Production database replication.
- Distributed transaction coordination.
- Production multi-instance availability.
- Automated application rollback.
- Long-term institutional data retention.

Ordinary restart persistence and transaction rollback must not be represented as verified backup restoration.

The proposed backup and restoration procedures remain subject to operational review and execution.

Additional recovery requirements are documented in:

[ADR-004 — Local Persistence and Recovery](ADR-004-local-persistence-and-recovery.md)

---

### 10. Security and Data Boundaries

The persistence architecture remains restricted to synthetic classroom data.

The application must not be used to store real student records, protected institutional information, private credentials, or other sensitive production data.

The reviewed implementation enforces application-level role and ownership restrictions through WorkflowService.

The persistence layer stores the authoritative synthetic student identifier associated with each request.

Client-supplied identity headers and requester identifiers do not establish authoritative ownership or permissions.

Parameterized SQL operations reduce exposure to SQL injection through the reviewed repository operations.

Local database files and backup copies must remain outside tracked repository content.

The default localhost binding and disabled H2 console support the intended local classroom environment.

These controls do not establish production database security, institutional compliance, or protection against unauthorized direct filesystem access.

---

### 11. Requirements and Interface Traceability

| Reference | Persistence Relationship |
|---|---|
| REQ-001 | Successful request submission |
| REQ-002 | Persistent student ownership and privacy |
| REQ-003 | Unique request identifiers and persistence across application restarts |
| REQ-004 | Authorized reviewer access |
| REQ-005 | Persistent status transitions |
| REQ-006 | Persistent resolution notes |
| AC-REQ-003-02 | Persistence across ordinary application restarts |
| API-001 | Request creation |
| API-002 | Student-owned request retrieval |
| API-003 | Reviewer request retrieval |
| API-004 | Persistent status modification |
| API-005 | Persistent resolution note modification |
| API-006 | Repository persistence boundary |
| API-007 | Synthetic identity context |
| AR-004 | Architecture and implementation divergence |
| AR-005 | Concurrent updates, retry behavior, and storage consistency |

Detailed acceptance-criteria mappings remain in the central requirements, API contracts, and verification evidence.

---

### 12. Verification Evidence

#### 12.1 Reviewed Implementation Sources

The persistence clarification is supported by review of:

- [RequestController](../../src/main/java/edu/loyola/campusconnect/controller/RequestController.java)
- [WorkflowService](../../src/main/java/edu/loyola/campusconnect/service/WorkflowService.java)
- [RequestValidator](../../src/main/java/edu/loyola/campusconnect/validation/RequestValidator.java)
- [SupportRequest](../../src/main/java/edu/loyola/campusconnect/model/SupportRequest.java)
- [RequestRepository](../../src/main/java/edu/loyola/campusconnect/repository/RequestRepository.java)
- [JdbcRequestRepository](../../src/main/java/edu/loyola/campusconnect/repository/JdbcRequestRepository.java)
- [ApiExceptionHandler](../../src/main/java/edu/loyola/campusconnect/controller/ApiExceptionHandler.java)
- [Database Schema](../../src/main/resources/schema.sql)
- [Application Configuration](../../src/main/resources/application.properties)

Source-level review supports the documented persistence implementation.

It does not independently establish passing runtime verification against the final release candidate.

#### 12.2 Existing Service-Level Test Coverage

The following existing test methods were identified in WorkflowServiceTest.java:

| Existing Test Method | Verification Focus |
|---|---|
| createsTrimmedOwnedRequestsWithDistinctIdsAndDefaults | Request creation, ownership, and unique identifiers |
| rejectsInvalidInputWithoutStoringAnything | Invalid creation without persistence |
| rejectsUnknownIdsNegativeVersionsAndStaleStatusAndNoteWrites | Version and identifier validation |
| atomicallyAllowsOnlyOneConcurrentStatusOrNoteSave | Atomic concurrent-update protection |
| rollsBackInsertWhenStorageFailsBeforeConfirmation | Failed creation rollback |
| rollsBackStatusAndNoteWhenStorageFailsAfterWrite | Failed modification rollback |
| reportsFailedListingInsteadOfReturningAnEmptySuccess | Storage failure reporting |
| preservesOwnershipStatusNotesAndUniqueIdsAcrossFileStoreRestart | Restart persistence |

Source:

[WorkflowServiceTest.java](../../src/test/java/edu/loyola/campusconnect/service/WorkflowServiceTest.java)

#### 12.3 Existing Controller-Level Test Coverage

The following existing test methods were identified in RequestControllerTest.java:

| Existing Test Method | Verification Focus |
|---|---|
| createsTrimmedStudentOwnedRequestAndReturnsCurrentEtag | Persistent creation and initial version |
| rejectsInvalidCreationWithoutStoringAnything | Invalid request rejection |
| legacyClientStudentIdIsIgnoredWhenAssigningTheSessionOwner | Server-managed ownership |
| studentsSeeOnlyOwnedRequestsAndCannotReadOrChangeReviewerData | Student ownership isolation |
| updatePreconditionsRejectMissingMalformedAndStaleVersionsWithoutChanges | Modification preconditions |
| unavailableStorageReturnsFailureWithoutFalseSuccessOrRecordChanges | Storage failure handling |
| twoReviewersCannotSaveChangesBasedOnTheSameLoadedVersion | Concurrent modification protection |
| noteUpdateRejectsMissingAndMalformedIfMatchWithoutChangingRequest | Resolution note preconditions |

Source:

[RequestControllerTest.java](../../src/test/java/edu/loyola/campusconnect/controller/RequestControllerTest.java)

These test methods establish available verification coverage.

Their existence does not independently establish successful execution against the final Assignment 6 release revision.

---

### 13. Historical Evidence Reconciliation

#### 13.1 Historical A4 Verification

The A4 backend verification record dated October 30, 2026 documents:

- Java 17 and Maven 3.9.9 verification.
- Execution of `mvn -B clean verify`.
- Service-level H2 persistence testing.
- Transaction rollback testing.
- HTTP/controller integration testing.
- A local sequential merge simulation with 35 passing tests.
- Packaged application startup verification.
- Packaged application restart using the same temporary file-backed H2 database.
- Preservation of the saved request after restart.
- Continued student ownership isolation after restart.

Source:

[A4 Backend Verification](../testing/a4-backend-verification.md)

The historical verification used synthetic temporary data.

The record explicitly identifies backup restoration and other final release activities as separate work.

#### 13.2 Historical CI Verification

The original ADR-0005 correction references the following historical CI evidence:

| Evidence | Recorded Value |
|---|---|
| Pull request | PR #84 |
| CI run | Run #41 |
| Verification command | mvn -B clean verify |
| Passing tests | 35 |
| Failures | 0 |
| Errors | 0 |
| Skipped tests | 0 |
| Tested merge commit | 0fcc79c29dfcdc89403d3c230f47438cb00c358c |
| PR head | 00c034a74f22ce616f707848168909289b2db001 |
| Base revision | 70d3726b64c0deafef2d770870bb14ef8f28b79b |

Source:

[PR #84 — CI Run #41](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42613709038)

The historical CI result remains valid evidence for its recorded revision.

It does not establish final Assignment 5 approval or final Assignment 6 verification.

#### 13.3 Final A6 Evidence Requirements

Final release evidence must identify:

- Exact tested commit.
- Verification date.
- Execution environment.
- Executed verification commands.
- Actual test results.
- Relevant runtime observations.
- Remaining failures or limitations.
- Responsible human reviewer.
- Final CI run reference.
- Relationship to the final release tag.

The final release tag must identify the revision authorized through the team's release-gate process.

Historical CI evidence must not be substituted for final exact-revision verification.

---

### 14. Relationship to Existing Architecture Decisions

| Decision Record | Relationship |
|---|---|
| ADR-001 | Production technology decisions remain deferred |
| ADR-002 | Establishes the approved Java, Spring Boot, Maven, and file-backed H2 technology selections |
| ADR-003 | Establishes transactional consistency and optimistic concurrency requirements |
| ADR-004 | Establishes local persistence and recovery requirements |
| ADR-0005 | Corrects the inaccurate in-memory persistence proposal |
| ADR-006 | Accepts the implemented REST interface for the local, synthetic-data A6 classroom release |

ADR-0005 remains consistent with ADR-002, ADR-003, and ADR-004.

It does not supersede their accepted persistence, consistency, or recovery requirements.

ADR-006 concerns the REST interface deviation and does not introduce a new persistence technology.

---

### 15. Remaining Risks and Limitations

| Risk or Limitation | Current Disposition |
|---|---|
| Inaccurate historical in-memory proposal | Corrected in ADR-0005 |
| Misleading historical filename | Retained for reference stability |
| Independent approval of ADR-0005 clarification | Pending |
| Duplicate request submissions | Stronger prevention not established |
| Automatic retry safety | Not guaranteed |
| Database location changes | May select a different persistent store |
| Database-file loss or corruption | Recovery not guaranteed |
| Backup restoration | Verification pending |
| Production multi-instance operation | Outside verified classroom scope |
| Production database security | Outside classroom release scope |
| Incomplete operational runbook | Requires completion and reconciliation |
| Final A6 test execution | Exact-revision verification pending |
| Final CI evidence | Pending confirmation |
| Final release authorization | Pending |

The existing JDBC implementation is the architecture to retain unless the team approves a separate architectural change.

This clarification does not establish a new production persistence commitment.

---

### 16. Final Release Verification Checklist

- [x] Historical in-memory proposal identified.
- [x] Existing JDBC persistence correction preserved.
- [x] Original historical review baseline preserved.
- [x] File-backed H2 configuration identified.
- [x] RequestRepository contract reviewed.
- [x] JdbcRequestRepository implementation reviewed.
- [x] UUID-based identifier generation identified.
- [x] TransactionTemplate usage identified.
- [x] Atomic version-aware updates identified.
- [x] Existing persistence test methods identified.
- [x] Existing concurrency test methods identified.
- [x] Existing transaction rollback test methods identified.
- [x] Historical A4 verification evidence referenced.
- [x] Historical PR #84 CI evidence preserved.
- [x] Restart persistence distinguished from backup restoration.
- [x] Existing architecture decisions cross-referenced.
- [x] Complete independent human review of ADR-0005.
- [ ] Record the team's clarification approval or disposition.
- [ ] Verify implementation against the exact final release revision.
- [ ] Execute relevant service-level tests.
- [ ] Execute relevant controller-level tests.
- [ ] Verify ordinary application restart persistence.
- [ ] Verify transaction rollback behavior.
- [ ] Verify concurrent-update protection.
- [ ] Verify storage failure handling.
- [ ] Reconcile operational recovery limitations.
- [ ] Confirm final CI evidence.
- [ ] Confirm final acceptance dispositions.
- [ ] Record AI assistance and actual human verification.
- [ ] Confirm final release authorization.

---

### 17. Related Engineering Evidence

#### Architecture Decisions

- [ADR-001 — Production Technology Stack Deferral](ADR-001-defer-production-technology-stack.md)
- [ADR-002 — Cycle 1 Technology Stack](ADR-002-cycle-1-technology-stack.md)
- [ADR-003 — Request Consistency and Concurrent Updates](ADR-003-request-consistency.md)
- [ADR-004 — Local Persistence and Recovery](ADR-004-local-persistence-and-recovery.md)
- [ADR-006 — REST API Interface Alignment](ADR-006-rest-api-interface-alignment.md)

#### Architecture Documentation

- [Architecture Overview](../architecture/architecture.md)
- [Architecture Diagram](../architecture/architecture-diagram.png)
- [API Contracts](../architecture/api-contracts.md)
- [Component Responsibilities](../architecture/component-responsibilities.md)
- [Data and Context Ownership](../architecture/data-context.md)

#### Requirements and Verification

- [Requirements](../requirements/requirements.md)
- [Acceptance Criteria](../requirements/acceptance-criteria.md)
- [A4 Backend Verification](../testing/a4-backend-verification.md)
- [HTTP API Guide](../testing/a4-http-api.md)
- [A5 Acceptance Evidence Matrix](../testing/a5-acceptance-evidence-matrix.md)
- [CI Evidence](../testing/ci-evidence.md)
- [Known Limitations](../release/known-limitations.md)
- [Operational Runbook](../operations/runbook.md)

#### Related Pull Requests

- [PR #81 — Original Persistence Proposal](https://github.com/victordodan/-comp330-f26-team-02/pull/81)
- [PR #84 — Historical CI Verification](https://github.com/victordodan/-comp330-f26-team-02/pull/84)
- [PR #119 — Component Responsibilities Update](https://github.com/victordodan/-comp330-f26-team-02/pull/119)
- [PR #120 — Data and Context Ownership Update](https://github.com/victordodan/-comp330-f26-team-02/pull/120)

---

### 18. Approval Status

**ADR-0005 Status:** Proposed implementation clarification.

**Original Historical Review Baseline:** `70d3726b64c0deafef2d770870bb14ef8f28b79b`

**Implementation Alignment:** Transactional JDBC and file-backed H2 supported by source-level review.

**Historical Verification:** Documented through A4 backend verification and PR #84 CI Run #41.

**Independent Human Review:** Pending.

**Clarification Approval:** Pending documented team disposition.

**Final A6 Verification:** Pending.

**Final Release Authorization:** Pending.

The accepted decisions established in ADR-002, ADR-003, and ADR-004 remain preserved.

This clarification does not independently establish approval of the original in-memory proposal, a new persistence decision, or final release readiness.

---

### 19. AI Assistance

The original ADR-0005 correction documents Codex assistance in comparing the historical persistence proposal against source code, configuration, tests, and repository evidence.

For Assignment 6, ChatGPT assisted with reviewing the existing correction and reconciling it with the updated architecture documentation.

Additional AI assistance supported:

- Historical proposal comparison.
- Persistence architecture documentation.
- Repository contract documentation.
- Transactional consistency clarification.
- Optimistic concurrency clarification.
- Security and data boundary documentation.
- Existing test coverage identification.
- Historical verification evidence reconciliation.
- Final release verification planning.
- Identification of remaining recovery limitations.

Followed by human review and documentation in the AI Log, as is required.

[AI Use Log](../ai/ai-use-log.md)
