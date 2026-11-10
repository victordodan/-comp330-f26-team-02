# ADR-0005: Clarify Cycle 1 JDBC Persistence

**Status:** Proposed clarification — human review pending
**Review baseline:** Integrated A4 source `70d3726b64c0deafef2d770870bb14ef8f28b79b`
**Architecture owner:** Shú Perez · **Decision owner:** Codex Ramblers Team
**Related proposal:** [PR #81](https://github.com/victordodan/-comp330-f26-team-02/pull/81)
**Related requirements/contracts:** REQ-003; API-006 (storage boundary); API-001 (request submission)

## Context

The original PR #81 draft described `ConcurrentHashMap` storage, an `AtomicLong`
sequence and deferral of H2 to Cycle 2. Those statements do not describe the
integrated source at the review baseline. This revision corrects that unmerged
proposal; it does not establish that an in-memory decision was approved or
superseded by a new team decision.

[ADR-002](ADR-002-cycle-1-technology-stack.md) selects file-backed H2 for Cycle 1.
[ADR-003](ADR-003-request-consistency.md) defines transaction/version expectations,
and [ADR-004](ADR-004-local-persistence-and-recovery.md) defines persistence and
recovery expectations. This clarification is consistent with those accepted
records and does not supersede them.

## Implementation to retain

The integrated implementation uses Spring JDBC with a file-backed H2 database:

- [WorkflowService](../../src/main/java/edu/loyola/campusconnect/service/WorkflowService.java)
  enforces validation, role/ownership checks and lifecycle rules. It runs storage
  work through `TransactionTemplate` and delegates storage to `RequestRepository`.
- [JdbcRequestRepository](../../src/main/java/edu/loyola/campusconnect/repository/JdbcRequestRepository.java)
  uses parameterized `JdbcTemplate` queries. Status/note updates match both ID
  and expected version, rejecting conflicting writes.
- [Runtime configuration](../../src/main/resources/application.properties) defaults
  to `jdbc:h2:file:./data/campusconnect`;
  [schema.sql](../../src/main/resources/schema.sql) defines the stored records.
- Request IDs come from `UUID.randomUUID()`, not an `AtomicLong` sequence.
  H2 persistence and the repository layer are already implemented; Spring Data
  JPA is not required by this implementation.

## Alternatives and trade-offs

File-backed H2 satisfies the existing restart-retention requirement without a
separate database server. An in-memory map would lose records on process exit
and would not satisfy AC-REQ-003-02. A JPA migration or external database would
be a separate architecture change requiring its own justification and review;
neither is an approved Cycle 2 commitment in this record.

Transactions and version checks support rollback and concurrent-update handling,
but do not establish production readiness. Retention depends on restarting with
the same database location; changing the working directory or datasource can
select a different store. Database-file loss, backup restoration, multiple
application instances and production operations require separate verification.

## Verification evidence and limits

[WorkflowServiceTest](../../src/test/java/edu/loyola/campusconnect/service/WorkflowServiceTest.java)
contains `preservesOwnershipStatusNotesAndUniqueIdsAcrossFileStoreRestart`,
`atomicallyAllowsOnlyOneConcurrentStatusOrNoteSave`, and rollback/failure tests.
[RequestControllerTest](../../src/test/java/edu/loyola/campusconnect/controller/RequestControllerTest.java)
covers HTTP ownership, permissions and version preconditions.

Historical execution is recorded in
[A4 backend verification](../testing/a4-backend-verification.md).
[PR #84 CI run #41](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42613709038)
also reports 35 tests passed with no failures, errors or skips under
`mvn -B clean verify`. That run tested merge commit
`0fcc79c29dfcdc89403d3c230f47438cb00c358c` (PR head
`00c034a74f22ce616f707848168909289b2db001` plus base
`70d3726b64c0deafef2d770870bb14ef8f28b79b`). It is not final A5 approval.
No sub-two-second build claim or fresh test execution is made by this correction.

## Approval and follow-up

Human review of this clarification, final integrated revision verification,
acceptance dispositions and release approval remain pending. Restart persistence
must not be presented as proof of tested backup/restore procedures.

**AI assistance:** Codex compared the PR draft with source, configuration, tests
and repository evidence and drafted this correction. No new runtime tests or
team approvals were performed by this documentation edit.
