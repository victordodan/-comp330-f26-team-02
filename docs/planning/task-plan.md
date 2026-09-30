# Task Plan

**Team:** Codex Ramblers  
**Project:** CampusConnect  
**Current Phase:** A2 — Planning and Estimation  
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
| TASK-011 | Refine scope, estimates, schedule, risks, and traceability based on requirements | Malec Tarabein | Victor Dodan | `docs/planning/` | EST-002 | A2 | TASK-010 | Complete |
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

