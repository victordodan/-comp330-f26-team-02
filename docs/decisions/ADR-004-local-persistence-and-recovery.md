# ADR-004: Local Persistence and Recovery

## A6 Implementation Clarification

**Date:** 2026-11-24  
**Phase:** A6 — Cycle 2 Final Release  
**Review Status:** Source-level review completed; independent human review pending  
**Original Decision Status:** Accepted — Cycle 1

### 1. Purpose

This clarification documents how the approved local persistence and recovery decision is represented in the implemented CampusConnect architecture.

The original decision and its October 16, 2026 approval remain preserved as historical engineering evidence.

The reviewed implementation uses Spring JDBC, file-backed H2, transactional database operations, and version-aware updates.

This clarification distinguishes implemented persistence capabilities, historically executed verification, operational recovery requirements, and remaining limitations.

Persistent storage across ordinary application restarts does not independently establish verified backup restoration or disaster recovery.

---

### 2. Approved Decision vs. Implemented Behavior

| Original Decision | Reviewed Implementation | Alignment |
|---|---|---|
| File-backed H2 database | Configured local file-backed H2 datasource | Aligned |
| Unique request identifiers | Server-generated UUIDs and database primary key | Aligned |
| Stable student ownership | Persisted synthetic student identifiers | Aligned |
| Persistent request content | Stored title, category, and description | Aligned |
| Persistent request status | Stored current status | Aligned |
| Persistent resolution notes | Stored current resolution note | Aligned |
| Creation and modification timestamps | Server-managed timestamps | Aligned |
| Concurrent-update detection | Stored version field and atomic version checks | Aligned |
| Persistence across ordinary restarts | Existing restart tests and historical execution evidence | Final A6 verification pending |
| Transaction rollback | TransactionTemplate-managed operations and existing rollback tests | Final A6 verification pending |
| No false success after storage failure | Structured storage failure responses | Aligned at source level |
| Documented database location | Default datasource configuration identified | Aligned |
| Basic backup procedure | Operational procedure requires completion and review | Pending |
| Verified backup restoration | No verified restoration evidence established | Pending |
| Synthetic data only | Classroom data-handling restriction | Applicable |
| Local database files excluded from GitHub | Repository hygiene requirement | Final verification pending |

---

### 3. Implemented Persistence Architecture

#### 3.1 Persistence Components

The implemented persistence architecture consists of:

| Component | Responsibility |
|---|---|
| WorkflowService | Business rules, authorization, transaction coordination, and version management |
| SupportRequest | Immutable representation of current request state |
| RequestRepository | Persistence abstraction |
| JdbcRequestRepository | Physical database operations using parameterized SQL |
| Spring TransactionTemplate | Transaction coordination |
| Spring JdbcTemplate | JDBC database access |
| File-backed H2 | Authoritative persistent request storage |
| ApiExceptionHandler | Structured HTTP failure responses |

WorkflowService delegates storage operations through RequestRepository.

JdbcRequestRepository implements the physical database operations.

The database stores authoritative request records rather than temporary in-memory workflow state.

#### 3.2 Database Configuration

The reviewed default configuration is defined in:

[src/main/resources/application.properties](../../src/main/resources/application.properties)

| Configuration | Default Value |
|---|---|
| Database engine | H2 |
| Storage mode | File-backed |
| Datasource URL | `jdbc:h2:file:./data/campusconnect` |
| Database directory | `./data/` relative to the application working directory |
| Database file basename | campusconnect |
| Application bind address | `127.0.0.1` |
| Application port | `8080` |
| SQL initialization mode | Always |
| H2 browser console | Disabled |

The configured database path is relative to the application's working directory.

Changing the working directory or datasource configuration may cause the application to use a different database.

The default configuration supports local classroom execution without requiring a separate database server.

It does not establish production database security or deployment readiness.

#### 3.3 Physical Database Schema

The implemented schema is defined in:

[src/main/resources/schema.sql](../../src/main/resources/schema.sql)

The support_request table contains:

| Column | SQL Type | Constraint |
|---|---|---|
| id | VARCHAR(64) | Primary key |
| title | VARCHAR(100) | NOT NULL |
| category | VARCHAR(32) | NOT NULL |
| description | VARCHAR(2000) | NOT NULL |
| student_id | VARCHAR(32) | NOT NULL |
| status | VARCHAR(20) | NOT NULL |
| resolution_note | VARCHAR(2000) | NOT NULL |
| created_at | TIMESTAMP(9) WITH TIME ZONE | NOT NULL |
| updated_at | TIMESTAMP(9) WITH TIME ZONE | NOT NULL |
| version | BIGINT | NOT NULL |

The database stores the current request state.

The reviewed schema does not establish a separate request history, audit history, or resolution note revision history.

---

### 4. Persistence Across Application Restarts

#### 4.1 Expected Restart Behavior

Stored requests are intended to remain available after an ordinary application shutdown and restart using the same database location.

Persistent request information includes:

- Request identifier.
- Student ownership.
- Title.
- Category.
- Description.
- Current status.
- Current resolution note.
- Creation timestamp.
- Last-updated timestamp.
- Current version.

The fixed synthetic student identifiers allow previously stored requests to remain associated with their original owners.

The selected HTTP session identity is separate from persistent request ownership.

After restarting the application, a demonstration participant may need to select the appropriate synthetic identity again.

#### 4.2 Restart Verification Procedure

The following procedure defines the expected final release verification.

1. Start CampusConnect using the intended file-backed database configuration.
2. Select student-a.
3. Create a synthetic support request.
4. Record the generated request identifier and initial version.
5. Select reviewer.
6. Perform a permitted status transition.
7. Save a synthetic resolution note.
8. Record the complete current request state and version.
9. Stop the application normally.
10. Restart the application using the same database location.
11. Select student-a.
12. Retrieve the previously created request.
13. Compare the retrieved record against the saved pre-restart state.
14. Select student-b.
15. Attempt to retrieve student-a's request.
16. Confirm that the unauthorized retrieval is rejected.
17. Select reviewer and confirm that the persisted request remains accessible.

Expected results:

- The original request identifier is preserved.
- Student ownership is preserved.
- Request content is preserved.
- Current status is preserved.
- Resolution note is preserved.
- Timestamps are preserved.
- Request version is preserved.
- Cross-student retrieval returns HTTP 404.
- Reviewer access remains available after identity selection.

This procedure is a final release verification requirement.

It must not be represented as newly executed Assignment 6 evidence until actual results are recorded.

---

### 5. Transactional Persistence and Failure Handling

#### 5.1 Transaction Boundaries

WorkflowService coordinates database operations using Spring TransactionTemplate.

Transactional operations include:

- Request creation.
- Student request retrieval.
- Reviewer request retrieval.
- Status modifications.
- Resolution note modifications.

JdbcRequestRepository executes parameterized SQL statements through Spring JdbcTemplate.

The transaction boundary supports consistent storage operations and rollback when transactional writes fail.

#### 5.2 Storage Failure Handling

Database access and transaction exceptions are translated into STORAGE_ERROR application failures.

ApiExceptionHandler maps these failures to HTTP 503 Service Unavailable.

A failed database operation must not produce a false success confirmation.

A failed listing operation must not be represented as a successful empty request list.

Expected behavior includes:

| Failure Scenario | Expected Result |
|---|---|
| Database failure during request creation | HTTP 503; no successful creation confirmation |
| Database failure during status modification | HTTP 503; no successful modification confirmation |
| Database failure during note modification | HTTP 503; no successful modification confirmation |
| Database failure during request listing | HTTP 503 rather than an empty successful result |
| Transaction failure after a write | Transaction rollback where applicable |
| Stale modification version | HTTP 412; newer stored state preserved |

Final verification must confirm these outcomes against the integrated release candidate.

#### 5.3 Transaction Rollback

Existing service-level tests address failed insertions and failures occurring after status or note writes.

The intended rollback behavior preserves the previously committed database state when the transaction fails.

Transaction rollback protects against failed transactional operations.

It does not recover records lost through database-file deletion, filesystem corruption, or other failures outside the transaction boundary.

---

### 6. Data Consistency and Concurrent Updates

The persistence architecture retains the optimistic concurrency requirements established in ADR-003.

Each stored request contains a server-managed version field.

New requests begin at version 0.

Successful status and resolution note modifications increment the version.

JdbcRequestRepository performs atomic updates using the request identifier and expected version.

The update predicate follows this structure:

```sql
WHERE id = ? AND version = ?
```

A stale modification must not overwrite a newer stored request state.

The application returns HTTP 412 when the expected version no longer matches the stored version.

Successful modifications preserve:

- Request identifier.
- Student ownership.
- Title.
- Category.
- Description.
- Creation timestamp.

Additional consistency requirements are documented in:

[ADR-003 — Request Consistency and Concurrent Updates](ADR-003-request-consistency.md)

---

### 7. Backup and Recovery Boundaries

#### 7.1 Persistence vs. Recovery

The following capabilities must remain distinct.

| Capability | Meaning | Evidence Status |
|---|---|---|
| Persistent storage | Request records are written to file-backed H2 | Implemented |
| Ordinary restart persistence | Records remain available after normal restart using the same store | Historical A4 execution evidence exists |
| Transaction rollback | Failed transactional writes do not leave partial committed changes | Historical test evidence exists |
| Database backup | A separate recoverable copy of the database is maintained | Procedure requires completion and verification |
| Backup restoration | A saved database copy is successfully restored and validated | Not established as verified |
| Dataset recreation | Synthetic demonstration records are recreated | Does not recover the original stored state |
| Disaster recovery | Recovery from database loss or major operational failure | Not established |
| Application version rollback | Return to a previous known-good application revision | Separate operational capability requiring verification |

Successful restart persistence must not be represented as successful backup restoration.

Successful transaction rollback must not be represented as application version rollback or disaster recovery.

#### 7.2 Proposed Local Backup Procedure

The following procedure is proposed for operational review.

It is not an established or executed backup capability.

1. Identify the effective datasource configuration and database location.
2. Confirm that the database contains synthetic demonstration data only.
3. Stop the application normally.
4. Confirm that no application process is actively writing to the database.
5. Identify the database files associated with the configured H2 store.
6. Preserve a copy in a separate approved local backup location.
7. Record the backup date, application revision, database location, and responsible operator.
8. Preserve the original database files.
9. Restart the application using its original configuration.
10. Confirm that the original stored requests remain accessible.

Database files must not be copied while active writes are occurring unless an appropriate and separately verified database backup mechanism is used.

The backup location must remain outside tracked repository content.

No automated backup schedule or retention policy is established by this clarification.

#### 7.3 Proposed Backup Restoration Verification

Backup restoration should first be tested using an isolated synthetic database location.

The original working database must not be overwritten during initial restoration testing.

The proposed verification procedure is:

1. Identify a previously saved synthetic database backup.
2. Preserve the current working database.
3. Prepare a separate restoration test location.
4. Restore the backup into the isolated location.
5. Configure a test application instance to use the restored database.
6. Start the test application.
7. Retrieve previously recorded synthetic requests.
8. Compare request identifiers, ownership, status, notes, timestamps, and versions.
9. Verify student ownership isolation.
10. Verify reviewer access.
11. Record the restoration outcome and any discrepancies.
12. Stop the test application and preserve the verification evidence.

A backup restoration may omit changes made after the backup was created.

No recovery point objective or recovery time objective is established for the classroom release.

Backup restoration must remain marked unverified until this procedure or another approved procedure is successfully executed and documented.

#### 7.4 Synthetic Dataset Recreation

The team may recreate synthetic demonstration records when appropriate.

Dataset recreation is not equivalent to restoring a previous database.

Recreated requests may contain different:

- Request identifiers.
- Creation timestamps.
- Modification timestamps.
- Versions.
- Status histories.

Controlled dataset recreation requires coordination to avoid unintentionally deleting existing verification evidence.

---

### 8. Security and Configuration Boundaries

The persistence architecture remains restricted to synthetic classroom data.

The database must not contain:

- Real student records.
- Academic records or grades.
- Private personal information.
- FERPA-protected information.
- HIPAA-protected information.
- Institutional credentials.
- API keys or access tokens.
- Confidential institutional information.

Local database files, backup copies, and machine-specific configuration must not be committed to GitHub.

Operational evidence must not expose credentials, session cookies, or sensitive request content.

The default H2 browser console is disabled.

The default application bind address is localhost.

These settings reduce unnecessary exposure within the intended classroom environment but do not establish production security.

Direct filesystem and database access remain outside the application's synthetic role permission model.

No production encryption, access management, or institutional compliance capability is established by this decision.

---

### 9. Operational Recovery and Troubleshooting

| Operational Situation | Initial Response | Verification or Caution |
|---|---|---|
| Ordinary application shutdown | Restart using the same datasource configuration | Confirm previously stored records remain available |
| Requests appear missing after restart | Verify working directory and effective datasource location | Do not assume records were deleted |
| Database connection failure | Inspect configuration, filesystem access, and application errors | Do not report successful persistence |
| Database file locked | Check whether another process is using the same database | Avoid forcibly modifying active database files |
| HTTP 503 storage failure | Investigate the storage dependency | Confirm current record state before retrying |
| HTTP 412 concurrency conflict | Reload the current request and ETag | Do not blindly retry the stale modification |
| Suspected database corruption | Stop writes and preserve available database files | Escalate before attempting restoration |
| Accidental database deletion | Preserve remaining evidence and identify available backups | Recovery is not guaranteed |
| Failed application revision | Coordinate with the team before attempting rollback | No verified automated rollback capability is established |
| Synthetic dataset reset | Obtain appropriate team authorization | Preserve existing verification evidence |

Detailed operational procedures belong in:

[Operations Runbook](../operations/runbook.md)

At the reviewed repository baseline, the runbook still contains starter-kit guidance and incomplete sections.

The runbook must be completed and reconciled with actual operational procedures before the final release gate.

This ADR does not independently establish that those procedures have been executed.

---

### 10. Verification Evidence

#### 10.1 Source-Level Evidence

The implementation clarification is supported by review of:

- [WorkflowService](../../src/main/java/edu/loyola/campusconnect/service/WorkflowService.java)
- [SupportRequest](../../src/main/java/edu/loyola/campusconnect/model/SupportRequest.java)
- [RequestRepository](../../src/main/java/edu/loyola/campusconnect/repository/RequestRepository.java)
- [JdbcRequestRepository](../../src/main/java/edu/loyola/campusconnect/repository/JdbcRequestRepository.java)
- [ApiExceptionHandler](../../src/main/java/edu/loyola/campusconnect/controller/ApiExceptionHandler.java)
- [Database Schema](../../src/main/resources/schema.sql)
- [Application Configuration](../../src/main/resources/application.properties)

Source-level review supports the documented persistence architecture.

It does not independently establish successful execution of the final Assignment 6 release candidate.

#### 10.2 Existing Automated Test Coverage

The following existing test methods support persistence and recovery-related verification.

| Existing Test Method | Verification Focus |
|---|---|
| createsTrimmedOwnedRequestsWithDistinctIdsAndDefaults | Persistent creation and unique identifiers |
| rejectsInvalidInputWithoutStoringAnything | Invalid submissions do not create records |
| rollsBackInsertWhenStorageFailsBeforeConfirmation | Failed creation rollback |
| rollsBackStatusAndNoteWhenStorageFailsAfterWrite | Failed modification rollback |
| reportsFailedListingInsteadOfReturningAnEmptySuccess | Storage failure reporting |
| preservesOwnershipStatusNotesAndUniqueIdsAcrossFileStoreRestart | File-backed restart persistence |
| atomicallyAllowsOnlyOneConcurrentStatusOrNoteSave | Concurrent-update protection |
| unavailableStorageReturnsFailureWithoutFalseSuccessOrRecordChanges | HTTP storage failure behavior |
| twoReviewersCannotSaveChangesBasedOnTheSameLoadedVersion | HTTP concurrency protection |

Sources:

- [WorkflowServiceTest.java](../../src/test/java/edu/loyola/campusconnect/service/WorkflowServiceTest.java)
- [RequestControllerTest.java](../../src/test/java/edu/loyola/campusconnect/controller/RequestControllerTest.java)

These existing test methods provide verification coverage.

Their existence does not independently establish passing results against the final Assignment 6 release candidate.

#### 10.3 Historical A4 Execution Evidence

The historical A4 backend verification record is dated October 30, 2026.

Source:

[A4 Backend Verification](../testing/a4-backend-verification.md)

The record documents:

- Java 17 and Maven 3.9.9 verification environment.
- Execution of `mvn -B clean verify`.
- Service-level H2 persistence and rollback tests.
- HTTP/controller integration tests.
- A local sequential merge simulation with 35 passing tests.
- Packaged application startup checks.
- Packaged application restart using the same temporary file-backed H2 store.
- Preservation of the complete saved request after restart.
- Continued student ownership isolation after restart.

The recorded A4 verification used synthetic temporary data.

This evidence supports historical restart persistence and transactional consistency.

The A4 verification record explicitly distinguishes these results from unverified backup and restoration procedures.

#### 10.4 Historical CI Evidence

ADR-0005 references PR #84 CI run #41.

The historical run recorded:

- 35 passing tests.
- Zero test failures.
- Zero test errors.
- Zero skipped tests.
- Execution of `mvn -B clean verify`.

The tested merge commit was:

`0fcc79c29dfcdc89403d3c230f47438cb00c358c`

Historical CI run:

[PR #84 — CI Run #41](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42613709038)

These results apply to the recorded historical revision.

They must not be represented as final Assignment 6 verification evidence.

#### 10.5 Final A6 Evidence Requirements

Final release evidence must identify:

- Exact tested commit.
- Verification date.
- Execution environment.
- Commands or procedures executed.
- Actual results.
- Relevant test output.
- Remaining failures or limitations.
- Responsible human reviewer.

Restart persistence, transaction rollback, and backup restoration must be recorded as separate verification activities.

---

### 11. Requirements and Architecture Traceability

| Reference | Persistence or Recovery Relationship |
|---|---|
| REQ-001 | Successful request creation |
| REQ-002 | Persistent student ownership and privacy |
| REQ-003 | Validated and persistent request creation |
| REQ-004 | Authorized reviewer access to stored records |
| REQ-005 | Persistent status transitions |
| REQ-006 | Persistent resolution notes |
| AC-REQ-003-02 | Persistence across ordinary application restarts |
| API-001 | Request creation |
| API-002 | Student-owned request retrieval |
| API-003 | Reviewer request retrieval |
| API-004 | Persistent status modifications |
| API-005 | Persistent resolution note modifications |
| API-006 | Repository persistence boundary |
| ADR-003 | Transactional consistency and optimistic concurrency |
| AR-005 | Concurrent-update, retry, and storage consistency risks |

Detailed acceptance-criteria mappings remain in the central requirements, API contracts, and testing evidence.

---

### 12. Relationship to Existing Decisions

| Decision Record | Relationship |
|---|---|
| ADR-001 | Production technology decisions remain deferred |
| ADR-002 | Establishes file-backed H2 as part of the approved technology stack |
| ADR-003 | Establishes transactional consistency and optimistic concurrency |
| ADR-004 | Establishes local persistence and recovery expectations |
| ADR-0005 | Clarifies implemented transactional JDBC persistence |
| ADR-006 | Proposes retaining the implemented REST interface |

The implemented persistence architecture remains consistent with the original file-backed H2 decision.

ADR-006 does not supersede the persistence or recovery requirements established in ADR-004.

---

### 13. Remaining Risks and Limitations

| Risk or Limitation | Current Disposition |
|---|---|
| Local database-file loss | Backup and recovery procedures require verification |
| Database corruption | No verified disaster recovery capability established |
| Working directory changes | May select a different database location |
| Datasource configuration changes | May select a different database |
| Concurrent application instances | Production multi-instance behavior not established |
| Backup restoration | Verification pending |
| Automated backup scheduling | Not established |
| Backup retention policy | Unresolved |
| Long-term request retention | Unresolved |
| Controlled dataset reset | Requires documented procedures |
| Application version rollback | Separate operational verification required |
| Production database security | Outside classroom release scope |
| Incomplete operational runbook | Requires completion before final release |
| Final A6 persistence evidence | Exact-revision verification pending |

File-backed H2 is appropriate for the intended local classroom workflow.

It does not independently establish production availability, institutional compliance, or disaster recovery readiness.

---

### 14. Final Release Verification Checklist

- [x] Original approved persistence decision preserved.
- [x] File-backed H2 configuration identified.
- [x] Database location documented.
- [x] Database schema reviewed.
- [x] TransactionTemplate usage identified.
- [x] Existing restart persistence test identified.
- [x] Existing transaction rollback tests identified.
- [x] Historical A4 restart execution evidence identified.
- [x] Historical CI evidence referenced.
- [x] Persistence and backup restoration distinguished.
- [x] Unverified recovery capabilities documented.
- [ ] Confirm final datasource configuration.
- [ ] Confirm local database files and backups are excluded from GitHub.
- [ ] Execute relevant persistence tests against the final release revision.
- [ ] Execute relevant transaction rollback tests.
- [ ] Verify ordinary application restart persistence.
- [ ] Verify ownership isolation after restart.
- [ ] Verify stored status, notes, timestamps, and versions after restart.
- [ ] Verify storage failure responses.
- [ ] Complete and review the operational runbook.
- [ ] Approve a safe local backup procedure.
- [ ] Execute and document isolated backup restoration verification, or record its approved deferral.
- [ ] Reconcile remaining recovery limitations.
- [ ] Confirm final CI evidence for the exact release revision.
- [ ] Complete independent human review.
- [ ] Record AI assistance and actual human verification.
- [ ] Confirm final release authorization.

---

### 15. Related Engineering Evidence

#### Architecture Decisions

- [ADR-001 — Production Technology Stack Deferral](ADR-001-defer-production-technology-stack.md)
- [ADR-002 — Cycle 1 Technology Stack](ADR-002-cycle-1-technology-stack.md)
- [ADR-003 — Request Consistency and Concurrent Updates](ADR-003-request-consistency.md)
- [ADR-0005 — JDBC Persistence Clarification](ADR-0005-in-memory-persistence.md)
- [ADR-006 — REST API Interface Alignment](ADR-006-rest-api-interface-alignment.md)

#### Architecture Documentation

- [Architecture Overview](../architecture/architecture.md)
- [Architecture Diagram](../architecture/architecture-diagram.png)
- [API Contracts](../architecture/api-contracts.md)
- [Component Responsibilities](../architecture/component-responsibilities.md)
- [Data and Context Ownership](../architecture/data-context.md)

#### Testing and Release Evidence

- [Requirements](../requirements/requirements.md)
- [Acceptance Criteria](../requirements/acceptance-criteria.md)
- [Test Strategy](../testing/test-strategy.md)
- [A4 Backend Verification](../testing/a4-backend-verification.md)
- [A5 Acceptance Evidence Matrix](../testing/a5-acceptance-evidence-matrix.md)
- [CI Evidence](../testing/ci-evidence.md)
- [Known Limitations](../release/known-limitations.md)
- [Risk Register](../planning/risk-register.md)

#### Operations and Observability

- [Operations Runbook](../operations/runbook.md)
- [Observability Plan](../observability/observability-plan.md)
- [Runtime Evidence](../observability/runtime-evidence.md)
- [Metrics Notes](../observability/metrics-notes.md)

#### Related Pull Requests

- [PR #119 — Component Responsibilities Update](https://github.com/victordodan/-comp330-f26-team-02/pull/119)
- [PR #120 — Data and Context Ownership Update](https://github.com/victordodan/-comp330-f26-team-02/pull/120)

---

### 16. Approval Status

**Original ADR-004 Decision:** Accepted — Cycle 1.

**Original Approval:** Victor Dodan, October 16, 2026.

**A6 Implementation Clarification:** Independent human review pending.

**Persistence Implementation:** Supported by source-level review.

**Historical Restart Verification:** Documented in A4 backend verification evidence.

**Historical Transaction Verification:** Documented in A4 backend and CI evidence.

**Backup Procedure:** Proposed; operational review pending.

**Backup Restoration Verification:** Pending.

**Final A6 Runtime Verification:** Pending.

**Final Release Authorization:** Pending.

The original approval remains preserved.

This clarification does not independently establish verified backup restoration, disaster recovery, or final release readiness.

---

### 17. AI Assistance

AI assisted with comparing the original ADR-004 decision against the reviewed persistence implementation, historical testing evidence, and current operational documentation. Followed by human verification and documentation in the AI Log, as is required.

[AI Use Log](../ai/ai-use-log.md)
