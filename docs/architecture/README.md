# CampusConnect — Architecture and Review Package

**Project:** CampusConnect  
**Team:** Codex Ramblers — Team 2  
**Phase:** A3 — Architecture and Review  
**Release Cycle:** Cycle 1  
**Status:** Architecture documentation under team review

## 1. Overview

This folder contains the Cycle 1 architecture documentation for CampusConnect, a student support-request management application.

CampusConnect is designed to allow simulated students to submit and track support requests while simulated reviewers manage request statuses and resolution notes.

The purpose of this architecture package is to define the system structure, component responsibilities, interfaces, data handling, and engineering decisions before major implementation work begins.

The Cycle 1 scope uses synthetic data and simulated user roles. Real institutional authentication, external integrations, and production deployment are outside the current scope.

## 2. Architecture Documentation

| Document | Description |
|---|---|
| [Architecture Overview](architecture.md) | Describes the overall system architecture, major components, and system boundaries. |
| [Component Responsibilities](component-responsibilities.md) | Defines the responsibilities of the user interface, workflow service, repository, and simulated role context. |
| [API Contracts](api-contracts.md) | Documents the expected application operations, validation rules, and request/response behavior. |
| [Data Context](data-context.md) | Explains the request data model, ownership, persistence expectations, and data-handling boundaries. |

The architecture overview includes the versioned [inline Mermaid diagram](architecture.md#4-architecture-diagram). It shows the current logical boundaries; final package review remains pending.

## 3. Architecture Decision Records

The following Architecture Decision Records document the team's technology and engineering decisions.

| ADR | Description | Status |
|---|---|---|
| [ADR-001 — Defer Production Technology Stack](../decisions/ADR-001-defer-production-technology-stack.md) | Records the earlier decision to defer the permanent production technology stack. | Accepted historical decision |
| [ADR-002 — Cycle 1 Technology Stack](../decisions/ADR-002-cycle-1-technology-stack.md) | Proposes Java, Spring Boot, Thymeleaf, Maven, and file-backed H2. | Proposed — Pending Team Approval |
| [ADR-003 — Request Consistency](../decisions/ADR-003-request-consistency.md) | Proposes duplicate-submission handling, optimistic locking, and transactional updates. | Proposed — Pending Team Approval |
| [ADR-004 — Local Persistence and Recovery](../decisions/ADR-004-local-persistence-and-recovery.md) | Proposes local database persistence, backup expectations, and recovery procedures. | Proposed — Pending Team Approval |

The proposed ADRs must be reviewed and approved or revised before being treated as the implementation baseline.

## 4. Requirements and Acceptance Criteria

The architecture is based on the agreed Cycle 1 student support-request workflow.

Supporting requirements evidence:

- [Requirements](../requirements/requirements.md)
- [Acceptance Criteria](../requirements/acceptance-criteria.md)
- [Assumptions and Open Questions](../requirements/assumptions-open-questions.md)

The architecture should remain consistent with the documented requirements, validation rules, role boundaries, and request status transitions.

## 5. Testing and Quality Review

Testing and review evidence supports the evaluation of the proposed architecture.

| Document | Purpose |
|---|---|
| [Test Strategy](../testing/test-strategy.md) | Defines the planned approach to verifying the required CampusConnect workflow. |
| [Test Plan](../testing/test-plan.md) | Records testing preparation and scope. |
| [Test Cases](../testing/test-cases.md) | Provides the location for detailed test cases and execution evidence. |
| [Architecture Review](../reviews/architecture-review.md) | Records architecture review findings, concerns, and follow-up actions. |

Planned tests and documented acceptance checks do not represent successful execution results. Test outcomes must be recorded when implementation becomes available.

## 6. Planning and Implementation Dependencies

The architecture package connects to the team's planning documentation:

- [Task Plan](../planning/task-plan.md)
- [Schedule](../planning/schedule.md)
- [Estimates](../planning/estimates.md)
- [Risk Register](../planning/risk-register.md)
- [Dependencies](../planning/dependencies.md)

Implementation readiness depends on resolving outstanding architecture decisions, confirming component and interface consistency, and reviewing the effect of engineering decisions on effort and risk.

## 7. Review and Approval Status

The architecture documentation has been prepared for A3 review.

Before the architecture baseline is considered complete, the team should:

1. Review and reconcile the architecture overview, component responsibilities, API contracts, and data context.
2. Approve or revise ADR-002, ADR-003, and ADR-004.
3. Address outstanding architecture and security review findings.
4. Confirm that the implementation plan and testing strategy align with the approved design.
5. Record the relevant review and approval evidence in GitHub.

**Architecture & Development Lead:** Shu Perez  
**Quality & Review Lead:** Giancarlo Herrera  
**Team Lead:** Victor Dodan

**Current status:** Pending final team review and approval.

## 8. Cycle 1 Scope and Limitations

This package describes the intended architecture and planned engineering behavior for Cycle 1.

It does not establish that the application has been implemented, that automated tests have passed, or that the system is ready for production.

Implementation and verification results will be recorded in later project phases.
