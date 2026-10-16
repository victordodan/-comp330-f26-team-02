# ADR-004: Local Persistence and Recovery

**Project:** CampusConnect  
**Team:** Codex Ramblers — Team 2  
**Phase:** A3 — Architecture and Review  
**Status:** Accepted — Cycle 1

## Context

CampusConnect must preserve support requests, statuses, ownership information, and resolution notes across normal application restarts.

The project is intended for local classroom demonstration using synthetic data. It does not require paid hosting or an external database service.

## Decision

CampusConnect will use a file-backed H2 database for Cycle 1, as approved in ADR-002.

The database will store:

- Unique request identifiers.
- Stable simulated student ownership.
- Request titles, categories, and descriptions.
- Creation and update timestamps.
- Current request statuses.
- Reviewer notes and resolution text.
- Version information for concurrent-update detection.

### Persistence

The application will use a persistent H2 database file rather than an in-memory database.

Records must remain available after the application stops and restarts using the same database location.

### Recovery

The application will report database failures without falsely confirming successful operations.

Failed transactions must not leave partially updated request records.

The team will document the local database location and a basic backup procedure.

For classroom recovery, the team may restore a previously saved database backup or recreate a demonstration dataset when appropriate.

Restoring a backup may lose changes made after that backup. Recovery procedures will be documented and tested before claiming that they are verified.

### Security and Configuration

The database will contain synthetic information only.

Local database files and machine-specific configuration should not be committed to GitHub.

Database configuration should remain separate from application source code.

## Alternatives Considered

**In-memory H2:** Easy to configure but does not retain records after normal application shutdown.

**MySQL or PostgreSQL:** Suitable for more demanding deployments but introduces additional local setup and administration.

**JSON or CSV file storage:** Simple initially but makes transactional updates, validation, and concurrent modification handling more difficult.

**File-backed H2:** Provides local persistence with relatively low setup complexity.

## Benefits

- No separate database server required.
- Supports persistence across restarts.
- Works with the approved Java and Spring Boot stack.
- Allows database-backed testing.
- Suitable for a small local demonstration.

## Risks and Tradeoffs

- Local database files can be lost or corrupted.
- Backup and recovery require explicit procedures.
- H2 is not automatically appropriate for a production deployment.
- Multiple application instances accessing the same database may require additional coordination.

## Consequences

The Request Repository will manage database access.

The development team must verify persistence across restarts, failed transactions, and recovery procedures.

The testing plan must distinguish proposed behavior from successfully executed tests.

## Related Evidence

- `docs/decisions/ADR-002-cycle-1-technology-stack.md`
- `docs/decisions/ADR-003-request-consistency.md`
- `docs/architecture/data-context.md`
- `docs/architecture/component-responsibilities.md`
- `docs/testing/test-strategy.md`

## Approval

**Decision Owner:** Codex Ramblers Team  
**Approval:** Approved by Victor Dodan (team lead) on October 16, 2026, following the project discussion  
**Implementation Verification:** Not yet performed
