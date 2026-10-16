# ADR-003: Request Consistency and Concurrent Updates

**Project:** CampusConnect  
**Team:** Codex Ramblers — Team 2  
**Phase:** A3 — Architecture and Review  
**Status:** Accepted — Cycle 1

## Context

CampusConnect allows simulated students to submit support requests and reviewers to update request statuses and resolution notes. The application must prevent accidental duplicate operations, conflicting updates, and misleading success messages.

The system must maintain accurate request records while remaining simple enough for Cycle 1.

## Decision

### Duplicate Submissions

Each successful request submission will create one uniquely identified request.

The application will prevent accidental repeated form submissions where practical by using the Post/Redirect/Get pattern.

This does not guarantee complete duplicate prevention. If stronger protection becomes necessary, the team may introduce submission tokens or idempotency keys.

### Concurrent Updates

Each request will have a version number used for optimistic locking.

When a reviewer attempts to save a change, the application will verify that the request has not been modified since it was loaded.

If another update has occurred, the application will reject the outdated change and ask the reviewer to reload the latest request information.

### Failed Operations

Invalid or unauthorized operations must not modify stored request records.

The application will only display a successful submission or update after the database confirms that the operation was completed.

Database transactions will be used to protect updates from partial completion.

## Alternatives Considered

- **Allow repeated submissions:** Simpler, but increases accidental duplicates.
- **Last-write-wins updates:** Easier to implement, but can overwrite another reviewer's changes.
- **Pessimistic locking:** Provides stronger coordination but adds unnecessary complexity for a small classroom application.
- **Optimistic locking:** Detects conflicting updates without requiring long-held database locks.

## Benefits

- Reduces accidental duplicate submissions.
- Protects reviewer changes from silent overwrites.
- Keeps request information consistent.
- Supports predictable error handling.
- Provides testable rules for future implementation.

## Risks and Tradeoffs

- Optimistic locking requires additional version tracking.
- Users may occasionally need to reload a request after a conflict.
- Post/Redirect/Get does not prevent every possible duplicate submission.
- Transaction and conflict-handling behavior must be verified during implementation.

## Consequences

The Request Workflow Service will enforce business rules and coordinate updates.

The Request Repository will support transactional persistence and version checking.

Testing should include repeated submissions, conflicting updates, invalid operations, and failed database writes.

## Related Evidence

- `docs/architecture/component-responsibilities.md`
- `docs/architecture/api-contracts.md`
- `docs/requirements/acceptance-criteria.md`
- `docs/testing/test-strategy.md`

## Approval

**Decision Owner:** Codex Ramblers Team  
**Approval:** Approved by Victor Dodan (team lead) on October 16, 2026, following the project discussion  
**Implementation Verification:** Not yet performed
