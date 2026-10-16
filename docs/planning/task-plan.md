# Task Plan

**Team:** Codex Ramblers  
**Project:** CampusConnect  
**Current Phase:** A3 — Architecture and Review
**Release Cycle:** Cycle 1

This task plan records the major work Codex Ramblers expects to complete throughout the Fall semester. Tasks are maintained at a level where ownership, dependencies, progress, and completion can be clearly tracked. More detailed tasks may be added as requirements, architecture, implementation, testing, and release decisions become more specific.

## Active Task Plan

| ID | Task | Owner | Backup / Reviewer | Related Evidence | Estimate | Target | Dependencies | Status |
|---|---|---|---|---|---|---|---|---|
| TASK-001 | Complete the repository README and project entry point | Victor Dodan | Venkata Aravind Mareddy | `README.md` | EST-001 | A1 | Initial project direction | Complete |
| TASK-003 | Complete the Role Matrix and acknowledgement evidence | Malec Tarabein | Victor Dodan | `docs/team/roles.md` | EST-001 | A1 | Team role and ownership decisions | Complete |
| TASK-006 | Complete the Initial Requirements package | Shelby Sierah | Venkata Aravind Mareddy | `docs/requirements/` | EST-002 | A1 | CampusConnect project brief and agreed Cycle 1 scope | Complete |
| TASK-007 | Complete the initial Planning and Risk package | Victor Dodan | Malec Tarabein | `docs/planning/` | EST-001 | A1 | Initial scope and requirements direction | Complete |
| TASK-008 | Record the Cycle 1 technology-stack decision and supporting tradeoffs | Giancarlo Herrera | Shu Perez | `docs/decisions/ADR-001-defer-production-technology-stack.md` | EST-002 | A2 | Cycle 1 planning and architecture uncertainty | Complete |
| TASK-009 | Review A1 evidence and establish the Project Launch baseline | Victor Dodan | Giancarlo Herrera | A1 repository evidence and `a1-project-launch` tag | EST-001 | A1 | TASK-001, TASK-003, TASK-006, and TASK-007 | Complete |
| TASK-010 | Refine requirements, acceptance criteria, assumptions, and open questions | Shelby Sierah | Venkata Aravind Mareddy | `docs/requirements/` | EST-002 | A2 | Initial Requirements package | Complete |
| TASK-011 | Refine scope, estimates, schedule, risks, and traceability based on requirements | Shu Perez | Victor Dodan | `docs/planning/` | EST-002 | A2 | TASK-010 | Complete |
| TASK-012 | Define and review the system architecture, interfaces, and major engineering decisions | Shu Perez | Venkata Aravind Mareddy | `docs/architecture/`, `docs/decisions/` | EST-003 | A3 | Refined requirements and planning evidence | Planned |
| TASK-013 | Implement the controlled Cycle 1 CampusConnect workflow | Shu Perez | Venkata Aravind Mareddy | `src/`, GitHub Issues, and pull requests | EST-004 | A4 | TASK-012 | Planned |
| TASK-014 | Review implementation and establish automated testing and CI evidence | Giancarlo Herrera | Victor Dodan | `tests/`, `.github/`, `docs/reviews/`, `docs/testing/` | EST-004 | A4 | TASK-013 | Planned |
| TASK-015 | Verify the Cycle 1 workflow, address defects, and prepare release evidence | Giancarlo Herrera | Shelby Sierah | `docs/testing/`, `docs/quality/`, `docs/release/` | EST-005 | A5 | TASK-013 and TASK-014 | Planned |
| TASK-016 | Prepare and present the Cycle 1 release with known limitations and supporting evidence | Shelby Sierah | Victor Dodan | `docs/release/`, Cycle 1 repository baseline | EST-005 | A5 | TASK-015 | Planned |
| TASK-017 | Review Cycle 1 evidence and select a small set of Cycle 2 maturity improvements | Victor Dodan | Malec Tarabein | Cycle 1 postmortem, risks, defects, estimates, and review evidence | EST-006 | A6 | Cycle 1 release | Planned |
| TASK-018 | Complete selected maturity improvements and prepare the final release evidence | Codex Ramblers | Shelby Sierah | `docs/release/`, `docs/observability/`, `docs/security/`, `docs/operations/`, `docs/ai/` | EST-006 | A6 | TASK-017 | Planned |

## Definition of Task Completion

Task completion follows the Definition of Done established in `docs/team/working-agreements.md`.

A task in this plan should only be marked **Complete** when:

- the agreed work has been completed
- the expected repository artifact or implementation has been updated
- applicable acceptance criteria or requirements have been addressed
- applicable tests or repository checks pass
- review has occurred when required
- important decisions or AI-assisted work have been documented when applicable
- the completed work can be traced to the appropriate GitHub evidence

Tasks that are waiting on another task, decision, or dependency should be marked **Blocked** rather than remaining **In Progress** indefinitely.

## Blocked Work

No tasks are currently blocked.

Future work may be marked **Blocked** when a required dependency, decision, review, or prerequisite prevents meaningful progress.

## Task Changes

During A2, the Cycle 1 task plan was refined as requirements and planning evidence became more specific.

The following planning changes were incorporated:

- Cycle 1 requirements, acceptance criteria, assumptions, and open questions were refined.
- Scope, estimates, schedule, risks, and traceability were updated to reflect the current Cycle 1 requirements.
- ADR-001 was completed to document the decision to defer selection of the final production technology stack until sufficient architecture and implementation evidence is available.
- Planning responsibilities were distributed across team members while maintaining primary and backup ownership in the supporting planning evidence.
- Re-estimation criteria were documented so estimates can be revisited when requirements, dependencies, risks, or implementation evidence materially change.
- GitHub Issues and the GitHub Project board are used to provide visible task status and supporting workflow evidence.

Future significant task splits, reassignments, deferrals, cancellations, or scope changes will be recorded here or in the appropriate repository evidence rather than being changed silently.

## Task Plan Maintenance

This task plan represents the team's current Cycle 1 planning baseline.

As the project progresses:

- task statuses will be updated when repository evidence supports the change
- new tasks may be added when implementation work becomes more specific
- blocked work will identify the dependency preventing progress
- material ownership changes will be documented
- estimate changes will follow the re-estimation process in `docs/planning/re-estimation.md`
- scope or requirement changes will be reflected in the appropriate planning and traceability artifacts
- completed work will remain traceable to repository evidence such as files, GitHub Issues, pull requests, reviews, tests, or decision records

The task plan should remain consistent with the requirements, scope, estimates, schedule, risk register, traceability evidence, and GitHub Project throughout the Cycle 1 lifecycle.

## A3 — Architecture and Review Planning Update

### Current Progress

The team has progressed from the A2 planning baseline into A3 architecture and review activities. Current work focuses on documenting the CampusConnect system architecture, defining component responsibilities and interfaces, reviewing engineering decisions, and preparing the project for Cycle 1 implementation.

The architecture package includes component responsibilities, API contracts, data context, and architecture review evidence. These documents establish the expected behavior of the student support-request workflow before implementation.

### Technology and Engineering Decisions

ADR-001 remains the historical A2 decision to defer the final production technology stack.

The following A3 decisions have been drafted for team review:

- **ADR-002:** Proposes Java, Spring Boot, Thymeleaf, Maven, and file-backed H2 for Cycle 1.
- **ADR-003:** Proposes duplicate-submission handling, optimistic locking, and transactional updates.
- **ADR-004:** Proposes local database persistence, backup expectations, and recovery procedures.

These ADRs are proposed decisions, not approved or implemented capabilities. Their impact on implementation tasks and estimates must be reviewed before the architecture baseline is finalized.

### A3 Task Coordination

| Task | Primary Owner | Supporting Reviewer | Current Planning Status |
|---|---|---|---|
| Architecture overview and component integration | Shu Perez | Venkata Aravind Mareddy | Under review |
| Component responsibilities and API contracts | Venkata Aravind Mareddy | Shu Perez | Documentation merged; architecture alignment pending |
| Technology and engineering ADRs | Giancarlo Herrera | Shu Perez | Drafted; pending team approval |
| Architecture review and quality strategy | Giancarlo Herrera | Victor Dodan | Initial evidence merged; follow-up review needed |
| Security and governance checklist | Shelby Sierah | Giancarlo Herrera | Under review |
| Architecture package reconciliation | Shu Perez | Victor Dodan | Pending final review |
| A3 planning alignment and submission readiness | Victor Dodan | Codex Ramblers team | In progress |

### Integration and Review Dependencies

Before Cycle 1 implementation is treated as ready to begin, the team should:

1. Review and approve or revise the proposed ADRs.
2. Confirm that architecture, API contracts, and component responsibilities agree.
3. Resolve outstanding security and governance review findings.
4. Revisit implementation estimates and technology-learning risks.
5. Confirm responsibilities for database persistence, status updates, and automated testing.
6. Verify that the A3 evidence package is linked and ready for final team review.

### Planning Impact

The existing Cycle 1 implementation and verification estimates remain unchanged until the team has sufficient evidence to justify revising them.

The proposed technology stack introduces specific areas for review, including framework setup, database persistence, concurrency handling, and recovery testing.

Any approved changes affecting scope, schedule, ownership, or effort should be recorded in the appropriate planning documents and GitHub evidence.

### A3 Completion Evidence

A3 planning alignment will be considered complete when the updated planning documents are reviewed, the relevant architecture decisions are resolved, and the team can trace its implementation tasks to the agreed architecture and requirements.

Documentation being merged does not, by itself, demonstrate that implementation or testing has been completed.
