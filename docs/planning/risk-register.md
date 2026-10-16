# Risk Register

**Team:** Codex Ramblers  
**Project:** CampusConnect  
**Current Phase:** A3 — Architecture and Review
**Release Cycle:** Cycle 1  

## Risk Register

| ID | Risk | Likelihood | Impact | Mitigation | Contingency / Response | Owner | Status | Related Evidence |
|---|---|---|---|---|---|---|---|---|
| R-001 | If the Cycle 1 scope expands beyond the required support-request workflow, then implementation and verification may become difficult to complete within the semester schedule. | Medium | High | Keep Cycle 1 focused on the workflow defined in `scope.md` and review proposed additions before accepting them. | Defer lower-priority additions to Cycle 2 or remove them from the current scope. | Malec Tarabein | Monitoring | `scope.md`, EST-004, MS-004 |
| R-002 | If the team selects technologies that require more learning or configuration than expected, then architecture and construction may take longer than planned. | Medium | High | Prefer technologies the team can reasonably understand, support, and test within the course timeline. | Reduce technical complexity, revise the architecture, or adjust implementation tasks if necessary. | Shu Perez | Open | EST-003, EST-004, MS-003, MS-004 |
| R-003 | If team members become unavailable or assigned work is completed late, then dependent tasks and phase-gate evidence may also be delayed. | Medium | High | Maintain primary and backup ownership, communicate blockers early, and keep work visible through GitHub and Microsoft Teams. | Reassign or redistribute affected work using the documented backup responsibilities. | Victor Dodan | Monitoring | `task-plan.md`, `schedule.md`, `docs/team/roles.md` |
| R-004 | If requirements remain unclear or change significantly after architecture or construction begins, then the team may need to redo design or implementation work. | Medium | High | Refine requirements and acceptance criteria before major architecture and construction decisions are finalized. | Update affected planning and architecture evidence, revise estimates, and rework only the impacted parts of the system. | Malec Tarabein | Open | `docs/requirements/`, EST-002, EST-003, EST-004 |
| R-005 | If independently developed parts of CampusConnect do not integrate as expected, then the complete Cycle 1 workflow may be delayed. | Medium | High | Define interfaces and responsibilities before implementation and integrate work regularly instead of waiting until the end of construction. | Identify the failing interface, simplify the integration where possible, and prioritize restoring the required end-to-end workflow. | Shu Perez | Open | MS-003, MS-004, EST-004 |
| R-006 | If testing and verification are delayed until late in Cycle 1, then important defects may be discovered too close to the release deadline. | Medium | High | Add tests and verification as implementation progresses and review important workflow behavior before formal verification begins. | Prioritize defects affecting the required workflow and document any remaining limitations honestly in release evidence. | Giancarlo Herrera | Open | EST-004, EST-005, MS-004, MS-005 |
| R-007 | If repository evidence is not kept current while work is completed, then the project may become difficult to review or trace even if the software itself works. | Low | High | Keep requirements, issues, pull requests, decisions, tests, and planning evidence updated as work occurs. | Reconcile missing evidence before the affected phase gate and document any gaps that cannot be reconstructed reliably. | Victor Dodan | Monitoring | `task-plan.md`, `docs/team/working-agreements.md`, GitHub repository |
| R-008 | If team communication becomes inconsistent, then team members may misunderstand requirements, deadlines, or assigned responsibilities. | Medium | Medium | Follow the communication expectations defined in the Team Charter and Working Agreements and provide regular project updates. | Schedule an additional team meeting, clarify responsibilities, and update affected repository evidence. | All Team Members | Monitoring | docs/team/team-charter.md, docs/team/working-agreements.md, GitHub activity |
| R-009 | If merge conflicts occur frequently because multiple contributors modify the same files or related work without coordination, then planning or development progress may be delayed. | Medium | Medium | Pull changes regularly, communicate ongoing work, and submit smaller focused pull requests. | Resolve conflicts collaboratively and coordinate ownership of affected files before additional changes are made. | Mareddy Venkata Aravind | Open | GitHub branches, pull requests, repository history |


## Risk Evaluation

The team uses **Low**, **Medium**, and **High** ratings for likelihood and impact.

- **Low** — currently unlikely or expected to have a limited effect on the project.
- **Medium** — reasonably possible and capable of affecting planned work.
- **High** — likely or capable of seriously affecting scope, schedule, quality, or a required phase gate.

These ratings represent the team's current judgment and may change as more project evidence becomes available.

## Risk Triggers / Indicators

| Risk ID | Trigger / Indicator | Monitoring Evidence |
|---|---|---|
| R-001 | New features are repeatedly added before the required Cycle 1 workflow is complete. | `scope.md`, task plan, GitHub issues |
| R-002 | Architecture or setup work takes significantly longer than EST-003 predicts. | `estimates.md`, architecture evidence, task status |
| R-003 | Assigned work misses an internal target or a team member reports that they cannot complete a responsibility. | Microsoft Teams communication, GitHub activity, `task-plan.md` |
| R-004 | Important requirements remain unresolved when architecture work begins or existing requirements change after implementation starts. | `docs/requirements/`, ADRs, GitHub issues |
| R-005 | Components work independently but fail when combined into the end-to-end workflow. | Pull requests, integration tests, defect evidence |
| R-006 | Major workflow behavior still lacks tests as the project approaches the Verification module. | `tests/`, CI evidence, `docs/testing/` |
| R-007 | Completed work cannot be linked to the expected issue, pull request, review, test, or documentation evidence. | GitHub repository and traceability evidence |
| R-008 | Important project updates, deadlines, or ownership changes are missed or misunderstood by team members. | `docs/team/team-charter.md`, `docs/team/working-agreements.md`, GitHub activity |
| R-009 | Repeated merge conflicts occur or multiple contributors modify the same files without coordination. | Pull requests, branch history, GitHub activity |

## Materialized Risks

No tracked risks have materialized at this stage of the project.

## Closed or Accepted Risks

No tracked risks have been closed or formally accepted yet.

## Risk Review

The team will review the risk register before each phase-gate submission and whenever a major change to scope, requirements, architecture, estimates, or schedule occurs.

Risks may be added, updated, closed, or marked as materialized as the project develops. If a risk affects another planning artifact, the related scope, task, estimate, or schedule evidence should also be updated.

## A3 — Architecture and Engineering Risk Update

### Risk Review Overview

During A3, the team developed architecture documentation and proposed engineering decisions for the CampusConnect support-request workflow.

The existing risks R-001 through R-009 remain part of the project risk register. The following additional risks address uncertainties introduced by the proposed technology stack, data consistency requirements, and architecture review process.

These risks are proposed for team review and have not been recorded as materialized incidents.

### Additional A3 Risks

| ID | Risk | Likelihood | Impact | Mitigation | Contingency / Response | Proposed Owner | Status | Related Evidence |
|---|---|---|---|---|---|---|---|---|
| R-010 | If the local H2 database fails or stored files become unavailable, support-request records may be lost or inaccessible. | Medium | High | Use file-backed persistence, database transactions, and a documented backup procedure. | Restore a verified backup when available or recreate synthetic demonstration data while documenting any data loss. | Shu Perez | Proposed — Monitoring | ADR-002, ADR-004, EST-004 |
| R-011 | If two reviewer operations modify the same request without conflict detection, a status change or resolution note may be overwritten. | Medium | High | Use version checking and optimistic locking as proposed in ADR-003. | Reject conflicting updates, reload current request information, and investigate any inconsistent records. | Venkata Aravind Mareddy | Proposed — Monitoring | ADR-003, API contracts, EST-004 |
| R-012 | If architecture decisions or outstanding reviews remain unresolved, implementation may begin with inconsistent technical assumptions. | Medium | High | Complete ADR review and reconcile architecture documents before treating the design as approved. | Prioritize required architecture decisions, document remaining limitations, and reassess affected implementation milestones. | Victor Dodan | Proposed — Monitoring | ADR-002, ADR-003, ADR-004, MS-003, MS-004 |

### A3 Risk Triggers and Monitoring

| Risk ID | Trigger / Indicator | Monitoring Evidence |
|---|---|---|
| R-010 | Persistence fails across a restart, a database operation reports an error, or a recovery attempt cannot restore expected data. | Database tests, implementation reviews, recovery procedure evidence |
| R-011 | Concurrent updates overwrite information, version conflicts are not detected, or reviewer actions produce inconsistent request records. | Concurrency tests, defect reports, API and repository implementation |
| R-012 | ADR approval remains pending, architecture documents disagree, or implementation begins without an agreed technical baseline. | ADR status, architecture review findings, GitHub pull requests, schedule checkpoints |

### Risk Ownership and Approval

The proposed owners reflect current team responsibilities and must be confirmed by the team before these risks are accepted into the active register.

The Quality & Review Lead will help verify that the planned testing strategy addresses the identified risks.

Risk likelihood and impact ratings are preliminary planning judgments. They should be revisited when implementation and testing provide stronger evidence.

### A3 Risk Assessment

The proposed Java and Spring Boot architecture, file-backed H2 database, and concurrency controls provide a more specific direction for Cycle 1 development, but they also introduce implementation and verification responsibilities.

The team should prioritize approval of architecture decisions, early database testing, and incremental integration to reduce the chance of schedule delays and data consistency problems.

No successful implementation, backup restoration, or concurrency testing is claimed in this risk update.
