# ADR-003: Request Consistency and Concurrent Updates


## A6 Implementation Clarification

**Date:** 2026-11-24  
**Phase:** A6 — Cycle 2 Final Release  
**Review Status:** Source-level review completed; independent human review pending  
**Original Decision Status:** Accepted — Cycle 1

### 1. Purpose

This clarification documents how the approved request consistency and concurrent-update decision is represented in the implemented CampusConnect architecture.

The original decision and its October 16, 2026 approval remain preserved as historical engineering evidence.

The reviewed implementation uses transactional JDBC persistence, server-managed request versions, ETag response headers, and If-Match modification preconditions.

This clarification also identifies remaining limitations involving duplicate submissions, retry behavior, and final release verification.

---

### 2. Approved Decision vs. Implemented Behavior

| Original Decision | Reviewed Implementation | Alignment |
|---|---|---|
| Unique request identifiers | Server-generated UUIDs and database primary key | Aligned |
| Prevent accidental repeated form submissions | REST request creation without verified Post/Redirect/Get | Implementation deviation |
| Request version tracking | Server-managed long version field | Aligned |
| Optimistic locking | Version comparison and atomic database updates | Aligned |
| Reject outdated modifications | HTTP 412 Precondition Failed | Aligned |
| Reload current request after conflict | Client retrieves latest record and ETag | Aligned |
| Invalid operations must not modify records | Validation and workflow checks before successful persistence | Aligned |
| Unauthorized operations must not modify records | Server-side role and ownership enforcement | Aligned |
| Database transactions | Spring TransactionTemplate | Aligned |
| No false success after failed storage | Storage exceptions translated into failure responses | Aligned |
| Stronger duplicate prevention | No implemented idempotency-key mechanism established | Remaining limitation |

The reviewed implementation substantially aligns with the original consistency and concurrency decision.

The primary implementation deviation concerns the originally proposed browser-based duplicate-submission mitigation.

---

### 3. Request Creation and Duplicate Submissions

#### 3.1 Implemented Request Creation

WorkflowService creates support requests using server-generated UUIDs.

Each successfully created request contains:

- Unique request identifier.
- Student ownership derived from the selected session identity.
- Validated title.
- Validated category.
- Validated description.
- Initial status Submitted.
- Initial empty resolution note.
- Server-generated creation timestamp.
- Server-generated last-updated timestamp.
- Initial version 0.

The request identifier is stored as the primary key in the support_request database table.

Request creation is performed through a transaction.

A successful creation response is returned only after the storage operation completes.

#### 3.2 Duplicate-Submission Limitations

Unique request identifiers prevent identifier collisions from producing duplicate primary-key records.

However, unique identifiers do not prevent multiple equivalent submissions from creating separate request records.

The original ADR proposed Post/Redirect/Get to reduce accidental repeated browser form submissions.

The reviewed implementation instead exposes:

`POST /api/requests`

The REST implementation does not establish:

- Post/Redirect/Get behavior.
- Submission-token-based duplicate prevention.
- Idempotency-key enforcement.
- Automatic equivalent-payload deduplication.
- Exactly-once request creation.
- Guaranteed safe automatic retries.

Repeated valid creation requests may produce separate records with different identifiers.

This limitation must remain documented in the final release package.

The REST interface deviation is addressed by:

[ADR-006 — REST API Interface Alignment](ADR-006-rest-api-interface-alignment.md)

The team accepted the REST interface for the local, synthetic-data A6 classroom release in ADR-006. This decision leaves duplicate-submission and retry limitations in place.

---

### 4. Optimistic Concurrency Implementation

#### 4.1 Request Version

The SupportRequest domain model contains a server-managed version field.

The corresponding database column is:

`version BIGINT NOT NULL`

New requests begin at version 0.

Every successful status or resolution note modification increments the stored version by one.

Clients cannot directly assign the authoritative stored version.

#### 4.2 ETag Response Header

RequestController exposes the current request version through an ETag response header.

Example:

```http
ETag: "0"
```

After a successful modification, the response contains the updated version.

Example:

```http
ETag: "1"
```

The ETag allows the client to identify the version associated with the returned request representation.

#### 4.3 If-Match Modification Preconditions

Reviewer modifications require an If-Match header containing the expected request version.

Example:

```http
If-Match: "0"
```

The precondition applies to:

- `PATCH /api/requests/{id}/status`
- `PATCH /api/requests/{id}/note`

The supplied version must be a single quoted, nonnegative numeric value within the Java long range.

Missing or blank If-Match headers are rejected.

Malformed version values are rejected.

A supplied version that does not match the current stored version is rejected.

#### 4.4 Service-Level Version Checking

WorkflowService retrieves the current stored request and compares its version against the supplied expected version.

The service rejects:

- Unknown request identifiers.
- Invalid expected versions.
- Stale expected versions.
- Unauthorized modification attempts.
- Unsupported status transitions.
- Invalid resolution note modifications.

The service constructs an updated SupportRequest only when the applicable business rules are satisfied.

The updated record preserves immutable request fields and increments the version.

#### 4.5 Atomic Repository Update

JdbcRequestRepository performs a parameterized database update matching both the request identifier and expected version.

The implemented update follows this structure:

```sql
UPDATE support_request
SET status = ?,
    resolution_note = ?,
    updated_at = ?,
    version = ?
WHERE id = ? AND version = ?
```

The repository reports success only when exactly one row is updated.

If another operation changes the stored version before the update completes, the expected version no longer matches.

The repository reports the unsuccessful replacement to WorkflowService.

WorkflowService translates the conflict into an application failure.

ApiExceptionHandler returns HTTP 412 Precondition Failed.

This prevents stale modifications from silently overwriting newer stored data.

---

### 5. Concurrent Update Scenario

The following scenario illustrates the intended concurrency behavior.

| Operation | Expected Version | Stored Version | Expected Result |
|---|---|---|---|
| Reviewer A retrieves request | Not applicable | 0 | HTTP 200; ETag "0" |
| Reviewer B retrieves same request | Not applicable | 0 | HTTP 200; ETag "0" |
| Reviewer A saves a valid modification | 0 | 0 | HTTP 200; stored version becomes 1 |
| Reviewer B attempts a valid modification using old version | 0 | 1 | HTTP 412; stored version remains 1 |
| Reviewer B reloads request | Not applicable | 1 | HTTP 200; ETag "1" |
| Reviewer B submits a valid modification using current version | 1 | 1 | HTTP 200; stored version becomes 2 |

Reviewer A and Reviewer B represent separate clients or sessions using the synthetic reviewer identity.

They do not represent separately authenticated production reviewer accounts.

This scenario describes expected behavior supported by the reviewed implementation.

It is not a claim of newly executed Assignment 6 runtime verification.

---

### 6. Transactional Consistency

WorkflowService coordinates database operations using Spring TransactionTemplate.

The service delegates persistence operations through RequestRepository.

JdbcRequestRepository performs physical database operations using Spring JdbcTemplate.

The transaction boundary supports:

- Request creation.
- Request retrieval.
- Status modification.
- Resolution note modification.
- Failure handling.
- Rollback of failed transactional writes.

Database access and transaction exceptions are translated into STORAGE_ERROR failures.

The application must not return successful creation or modification responses when the corresponding transaction fails.

Failed listing operations must not be represented as successful empty results.

Transaction behavior must be verified through automated tests and final release evidence.

---

### 7. Data Integrity During Modifications

#### 7.1 Immutable Fields

Successful status and note modifications preserve:

- Request identifier.
- Student ownership.
- Title.
- Category.
- Description.
- Creation timestamp.

Only the applicable status or resolution note, last-updated timestamp, and version are modified.

#### 7.2 Timestamp Consistency

WorkflowService generates the updatedAt timestamp during successful modifications.

The new timestamp must be later than the previously stored timestamp.

If the current system clock value is not later than the stored value, the service advances the previous timestamp by one nanosecond.

The database stores timestamps using:

`TIMESTAMP(9) WITH TIME ZONE`

#### 7.3 Status Consistency

The supported status transitions are:

| Current Status | New Status |
|---|---|
| Submitted | In Progress |
| In Progress | Resolved |
| Resolved | In Progress |

Resolving a request requires an existing nonblank resolution note.

Unsupported transitions and repeated same-state transitions are rejected.

Reopening a resolved request preserves its existing resolution note.

#### 7.4 Resolution Note Consistency

Each request contains one current resolution note.

The note is trimmed before storage and may contain a maximum of 2,000 characters.

A null note is normalized to an empty string.

A resolution note cannot be cleared while the request is Resolved.

Successful note modifications increment the request version.

Status and note modifications share the same version field.

A concurrent note update can therefore invalidate a previously loaded status update, and a concurrent status update can invalidate a previously loaded note update.

This protects the complete current request state rather than independently versioning individual fields.

---

### 8. Failure Handling

| Failure Scenario | Application Error | HTTP Status | Expected Data Behavior |
|---|---|---|---|
| Invalid request creation | BAD_INPUT | 400 | No request created |
| Unsupported status transition | BAD_INPUT | 400 | Existing record preserved |
| Resolve without saved nonblank note | BAD_INPUT | 400 | Existing record preserved |
| Invalid resolution note | BAD_INPUT | 400 | Existing record preserved |
| Malformed If-Match header | BAD_INPUT | 400 | Modification rejected |
| Missing or unauthorized identity | DENIED | 403 | Protected operation rejected |
| Student attempts reviewer modification | DENIED | 403 | Existing record preserved |
| Cross-student request retrieval | NOT_FOUND | 404 | No unauthorized record returned |
| Unknown request identifier | NOT_FOUND | 404 | No unrelated record modified |
| Stale expected version | CONFLICT | 412 | Newer stored state preserved |
| Missing or blank If-Match header | PRECONDITION_REQUIRED | 428 | Modification rejected |
| Database or transaction failure | STORAGE_ERROR | 503 | No success confirmation |

The listed outcomes describe reviewed implementation behavior and expected verification results.

Final release evidence must establish the actual results for the integrated release candidate.

---

### 9. Component Responsibilities

| Component | Consistency Responsibility |
|---|---|
| RequestController | Processes If-Match, returns ETag, and delegates operations |
| RequestValidator | Validates and normalizes request creation fields |
| WorkflowService | Enforces permissions, lifecycle rules, transactions, and version checks |
| SupportRequest | Represents the immutable current request state |
| RequestRepository | Defines the persistence and version-aware replacement boundary |
| JdbcRequestRepository | Performs parameterized SQL operations and atomic version-aware updates |
| File-backed H2 | Stores request records, timestamps, and versions |
| DemoIdentity / HTTP Session | Supplies the selected synthetic identity |
| WorkflowException | Represents application-level failures |
| ApiExceptionHandler | Maps handled failures to structured HTTP responses |

The architecture maintains separation between HTTP processing, business rules, and physical database operations.

---

### 10. Verification Evidence

#### 10.1 Source-Level Evidence

The implementation clarification is supported by review of:

- [RequestController](../../src/main/java/edu/loyola/campusconnect/controller/RequestController.java)
- [WorkflowService](../../src/main/java/edu/loyola/campusconnect/service/WorkflowService.java)
- [RequestValidator](../../src/main/java/edu/loyola/campusconnect/validation/RequestValidator.java)
- [SupportRequest](../../src/main/java/edu/loyola/campusconnect/model/SupportRequest.java)
- [RequestRepository](../../src/main/java/edu/loyola/campusconnect/repository/RequestRepository.java)
- [JdbcRequestRepository](../../src/main/java/edu/loyola/campusconnect/repository/JdbcRequestRepository.java)
- [ApiExceptionHandler](../../src/main/java/edu/loyola/campusconnect/controller/ApiExceptionHandler.java)
- [Database Schema](../../src/main/resources/schema.sql)
- [Application Configuration](../../src/main/resources/application.properties)

#### 10.2 Existing Service-Level Test Coverage

The following test methods exist in WorkflowServiceTest.java:

| Existing Test Method | Verification Focus |
|---|---|
| createsTrimmedOwnedRequestsWithDistinctIdsAndDefaults | Unique identifiers, ownership, and initial values |
| rejectsInvalidInputWithoutStoringAnything | Invalid creation without persistence |
| rejectsMissingInvalidOrWrongRoleContextBeforeAction | Authorization boundaries |
| permitsLifecycleAndPreservesResolutionOnReopen | Valid lifecycle and note preservation |
| rejectsUnsupportedSameStateAndUnresolvedTransitionsWithoutChanges | Invalid transitions and data preservation |
| enforcesTrimmedNoteBoundariesAndResolvedClearingRules | Resolution note consistency |
| rejectsUnknownIdsNegativeVersionsAndStaleStatusAndNoteWrites | Invalid identifiers and stale modifications |
| atomicallyAllowsOnlyOneConcurrentStatusOrNoteSave | Concurrent modification protection |
| rollsBackInsertWhenStorageFailsBeforeConfirmation | Creation rollback |
| rollsBackStatusAndNoteWhenStorageFailsAfterWrite | Modification rollback |
| reportsFailedListingInsteadOfReturningAnEmptySuccess | Storage failure reporting |
| preservesOwnershipStatusNotesAndUniqueIdsAcrossFileStoreRestart | Restart persistence and data integrity |

Source:

[WorkflowServiceTest.java](../../src/test/java/edu/loyola/campusconnect/service/WorkflowServiceTest.java)

#### 10.3 Existing Controller-Level Test Coverage

The following test methods exist in RequestControllerTest.java:

| Existing Test Method | Verification Focus |
|---|---|
| createsTrimmedStudentOwnedRequestAndReturnsCurrentEtag | Request creation and initial ETag |
| rejectsInvalidCreationWithoutStoringAnything | Invalid creation |
| suppliedServerFieldsCannotForgeOwnerOrGrantReviewerRole | Client-supplied identity restrictions |
| legacyClientStudentIdIsIgnoredWhenAssigningTheSessionOwner | Server-managed ownership |
| missingInvalidAndWrongRoleContextsAreDenied | Authorization failure |
| studentsSeeOnlyOwnedRequestsAndCannotReadOrChangeReviewerData | Student privacy and reviewer restrictions |
| resolvingRequiresSavedNoteAndReopeningPreservesImmutableFieldsAndText | Lifecycle and immutable data |
| updatePreconditionsRejectMissingMalformedAndStaleVersionsWithoutChanges | HTTP modification preconditions |
| unknownIdsMalformedJsonAndUnsupportedTransitionsProduceClearErrors | Negative-path handling |
| spoofedIdentityHeadersCannotGrantPermissionsOrOverrideTheSession | Identity spoofing restrictions |
| unavailableStorageReturnsFailureWithoutFalseSuccessOrRecordChanges | Storage failure handling |
| twoReviewersCannotSaveChangesBasedOnTheSameLoadedVersion | Concurrent modification protection |
| noteUpdateRejectsMissingAndMalformedIfMatchWithoutChangingRequest | Note modification preconditions |

Source:

[RequestControllerTest.java](../../src/test/java/edu/loyola/campusconnect/controller/RequestControllerTest.java)

The existence of these test methods establishes available test coverage.

It does not independently establish that the tests passed against the final Assignment 6 release candidate.

#### 10.4 Historical Verification Evidence

Historical implementation and testing evidence is documented in:

- [A4 Backend Verification](../testing/a4-backend-verification.md)
- [HTTP API Guide](../testing/a4-http-api.md)
- [CI Evidence](../testing/ci-evidence.md)
- [A5 Acceptance Evidence Matrix](../testing/a5-acceptance-evidence-matrix.md)

ADR-0005 records historical CI execution associated with PR #84, including 35 passing tests.

Historical test results must not be represented as final Assignment 6 execution evidence.

The exact final release revision requires independent verification.

---

### 11. Requirements and Interface Traceability

| Reference | Consistency Relationship |
|---|---|
| REQ-001 | Successful student request creation |
| REQ-002 | Student request ownership and privacy |
| REQ-003 | Unique request identifiers and persistence across application restarts |
| REQ-004 | Reviewer access and authorization |
| REQ-005 | Valid status transitions |
| REQ-006 | Resolution note consistency |
| API-001 | Request creation |
| API-002 | Student request retrieval |
| API-003 | Reviewer request retrieval |
| API-004 | Version-aware status modification |
| API-005 | Version-aware resolution note modification |
| API-006 | Transactional repository boundary |
| API-007 | Synthetic identity context |
| AR-005 | Concurrency, retry, and storage consistency risks |

Detailed acceptance-criteria mappings remain in the requirements, API contracts, and testing evidence.

---

### 12. Remaining Risks and Limitations

| Risk or Limitation | Current Disposition |
|---|---|
| Repeated valid creation requests may create equivalent records | Document as known limitation |
| Post/Redirect/Get is not established in the reviewed REST implementation | ADR-006 accepts the REST interface. Duplicate-submission and retry limitations remain. |
| Idempotency keys are not implemented | Deferred |
| Automatic retry safety is not guaranteed | Document client retry limitations |
| Concurrent reviewer modifications require current versions | Retain ETag and If-Match requirements |
| Stale clients must reload current request state | Document expected recovery behavior |
| Production multi-instance behavior is not established | Outside verified classroom scope |
| Backup restoration and disaster recovery remain unverified | Reconcile with ADR-004 and operational evidence |
| Final release test execution remains pending | Require exact-revision verification |

Optimistic concurrency protects against conflicting modifications to existing request records.

It does not provide complete duplicate-submission prevention, production distributed coordination, or exactly-once execution guarantees.

---

### 13. Final Release Verification Checklist

- [x] Original approved consistency decision preserved.
- [x] Implemented request version field identified.
- [x] ETag and If-Match behavior reviewed at source level.
- [x] TransactionTemplate usage identified.
- [x] Atomic version-aware SQL update reviewed.
- [x] Existing concurrency test methods identified.
- [x] Existing rollback test methods identified.
- [x] Duplicate-submission limitations documented.
- [ ] Execute relevant service-level tests.
- [ ] Execute relevant controller-level tests.
- [ ] Verify invalid creation returns HTTP 400 without persistence.
- [ ] Verify unauthorized modifications return HTTP 403.
- [ ] Verify cross-student retrieval returns HTTP 404.
- [ ] Verify stale status modification returns HTTP 412.
- [ ] Verify stale note modification returns HTTP 412.
- [ ] Verify missing If-Match returns HTTP 428.
- [ ] Verify malformed If-Match returns HTTP 400.
- [ ] Verify storage failures return HTTP 503 without false success.
- [ ] Verify concurrent modifications cannot silently overwrite newer records.
- [ ] Verify successful modifications preserve immutable fields.
- [ ] Verify transaction rollback behavior.
- [ ] Verify persistence across ordinary application restarts.
- [ ] Reconcile duplicate-submission and retry limitations.
- [ ] Confirm final CI evidence for the exact release revision.
- [ ] Complete independent human review.
- [ ] Record AI assistance and actual human verification.
- [ ] Confirm final release authorization.

---

### 14. Related Engineering Evidence

#### Architecture Decisions

- [ADR-001 — Production Technology Stack Deferral](ADR-001-defer-production-technology-stack.md)
- [ADR-002 — Cycle 1 Technology Stack](ADR-002-cycle-1-technology-stack.md)
- [ADR-004 — Local Persistence and Recovery](ADR-004-local-persistence-and-recovery.md)
- [ADR-0005 — JDBC Persistence Clarification](ADR-0005-in-memory-persistence.md)
- [ADR-006 — REST API Interface Alignment](ADR-006-rest-api-interface-alignment.md)

#### Architecture Documentation

- [Architecture Overview](../architecture/architecture.md)
- [Architecture Diagram](../architecture/architecture-diagram.png)
- [API Contracts](../architecture/api-contracts.md)
- [Component Responsibilities](../architecture/component-responsibilities.md)
- [Data and Context Ownership](../architecture/data-context.md)

#### Requirements and Release Evidence

- [Requirements](../requirements/requirements.md)
- [Acceptance Criteria](../requirements/acceptance-criteria.md)
- [Test Strategy](../testing/test-strategy.md)
- [Risk Register](../planning/risk-register.md)
- [Known Limitations](../release/known-limitations.md)
- [Operational Runbook](../operations/runbook.md)
- [CI Evidence](../testing/ci-evidence.md)

#### Related Pull Requests

- [PR #119 — Component Responsibilities Update](https://github.com/victordodan/-comp330-f26-team-02/pull/119)
- [PR #120 — Data and Context Ownership Update](https://github.com/victordodan/-comp330-f26-team-02/pull/120)

---

### 15. Approval Status

**Original ADR-003 Decision:** Accepted — Cycle 1.

**Original Approval:** Victor Dodan, October 16, 2026.

**A6 Implementation Clarification:** Independent human review pending.

**Implementation Alignment:** Transactional consistency and optimistic concurrency supported by source-level review.

**Implementation Deviation:** Post/Redirect/Get is not established in the reviewed REST implementation.

**Final Runtime Verification:** Pending.

**Final Release Authorization:** Pending.

The original approval remains preserved.

This clarification does not independently approve new functionality, deferred limitations, or the final release.

---

### 16. AI Assistance

AI assisted with comparing the original ADR-003 decision against the reviewed implementation and identifying relevant existing test methods. Followed by independent human review and documentation in the AI Log, as required.

[AI Use Log](../ai/ai-use-log.md)
