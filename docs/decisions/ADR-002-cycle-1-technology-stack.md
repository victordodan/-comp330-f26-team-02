# ADR-002: Cycle 1 Technology Stack

**Project:** CampusConnect  
**Team:** Codex Ramblers — Team 2  
**Phase:** A3 — Architecture and Review  
**Status:** Accepted — Cycle 1

## Context

CampusConnect requires a simple application that allows simulated students to submit support requests and reviewers to manage them. The application must support validation, role-based access rules, status changes, resolution notes, and persistent storage.

The team needs a technology stack that is practical for a student project, supports automated testing, and can run locally without paid hosting.

ADR-001 deferred the final production technology decision during A2. This ADR establishes the approved implementation stack for Cycle 1 without committing the team to a permanent production architecture.

## Decision

CampusConnect will use the following technologies for Cycle 1:

- **Java:** Primary programming language.
- **Spring Boot:** Backend application framework and request processing.
- **Thymeleaf:** Server-rendered HTML pages for student and reviewer workflows.
- **Maven:** Dependency management, builds, and automated testing.
- **File-backed H2 Database:** Local persistent storage for support requests.
- **Local execution:** Application runs on a team member's computer for development and classroom demonstration.

The application will use simulated student and reviewer identities rather than real institutional authentication.

## Alternatives Considered

**Spring Boot vs. plain Java:** Plain Java could reduce framework dependencies but would require more manual application setup and request handling.

**Thymeleaf vs. React:** React offers richer frontend interactivity but introduces additional frontend tooling and integration complexity.

**H2 vs. MySQL or PostgreSQL:** MySQL and PostgreSQL are stronger choices for many production environments, but H2 reduces setup requirements for a local demonstration.

**Maven vs. Gradle:** Both support Java builds and testing. Maven is selected because its conventional project structure and dependency configuration are suitable for this project.

## Benefits

- Supports a focused, end-to-end student support-request workflow.
- Allows the application to run locally without paid services.
- Reduces frontend and database setup complexity.
- Supports automated testing through Maven.
- Keeps the architecture understandable and manageable for the team.

## Risks and Tradeoffs

- Team members may need time to learn Spring Boot and Thymeleaf.
- H2 is not automatically suitable for a future multi-user production deployment.
- File-backed persistence requires careful handling of concurrent updates, database failures, and backups.
- The selected technologies must be reflected consistently in the architecture, implementation plan, and testing evidence.

## Consequences

The team will use this approved stack as its Cycle 1 implementation baseline. The architecture documents, task estimates, dependencies, and testing plans should be updated accordingly.

This decision does not establish the final production technology stack.

## Related Evidence

- `docs/decisions/ADR-001-defer-production-technology-stack.md`
- `docs/architecture/architecture.md`
- `docs/architecture/component-responsibilities.md`
- `docs/architecture/api-contracts.md`
- `docs/requirements/requirements.md`
- `docs/testing/test-strategy.md`

## Approval

**Decision Owner:** Codex Ramblers Team  
**Approval:** Approved by Victor Dodan (team lead) on October 16, 2026, following the project discussion  
**Implementation Verification:** Not yet performed
