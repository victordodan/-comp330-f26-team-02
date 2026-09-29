# Schedule

**Team:** Codex Ramblers  
**Project:** CampusConnect  
**Current Phase:** A2 — Planning and Estimation  
**Release Cycle:** Cycle 1  

## Schedule Basis

The Cycle 1 schedule is based on the current CampusConnect requirements, task dependencies, three-point estimates, course phase gates, and team availability.

The schedule follows these sequencing rules:

- Requirements and planning are refined before architecture is finalized.
- Architecture and interfaces are established before major implementation begins.
- Implementation and integration occur before final Cycle 1 verification.
- Testing and review occur throughout implementation rather than only at the end.
- Schedule commitments are reconsidered when requirements, estimates, dependencies, or risks materially change.

Sakai deadlines remain the authoritative course deadlines. Internal milestones and checkpoints are used to create enough time for integration, review, correction, and evidence preparation before those deadlines.

## Cycle 1 Milestones

| ID | Milestone | Planned Outcome | Dependencies | Related Tasks / Estimates | Owner | Status |
|---|---|---|---|---|---|---|
| MS-001 | Project Launch baseline | Establish initial team, repository, scope, planning, and engineering evidence. | Team formation and project direction | EST-001 | Codex Ramblers | Complete |
| MS-002 | A2 planning and requirements baseline | Finalize/refine Cycle 1 requirements, acceptance criteria, scope, estimates, task plan, schedule, risks, and traceability. | MS-001 | TASK-010, TASK-011, EST-002 | Planning and Requirements owners | In Progress |
| MS-003 | Architecture baseline | Establish system structure, interfaces, technology decisions, and major ADRs required for implementation. | MS-002 | TASK-012, EST-003 | Architecture & Development Lead | Planned |
| MS-004 | Cycle 1 implementation baseline | Complete the controlled CampusConnect support-request workflow and integrate its major components. | MS-003 | TASK-013, TASK-014, EST-004 | Development team | Planned |
| MS-005 | Cycle 1 verification and release readiness | Verify the required workflow, resolve material defects, document limitations, and prepare release evidence. | MS-004 | TASK-015, TASK-016, EST-005 | Quality & Review Lead / team | Planned |
| MS-006 | Cycle 2 maturity planning | Use Cycle 1 evidence to select a limited set of justified maturity improvements. | MS-005 | TASK-017, EST-006 | Codex Ramblers | Planned |

## Near-Term Cycle 1 Schedule

| Sequence | Planned Work | Entry Condition | Exit / Completion Evidence | Related Risk |
|---|---|---|---|---|
| 1 | Refine Cycle 1 requirements and acceptance criteria | A1 baseline exists | Stable requirement and acceptance-criteria IDs are available | R-004 |
| 2 | Reconcile scope, task plan, estimates, schedule, risks, and traceability | Refined requirements are available | A2 planning artifacts are mutually consistent and reviewed | R-001, R-003, R-007 |
| 3 | Define architecture, interfaces, and major engineering decisions | A2 planning baseline is established | Architecture evidence and applicable ADRs are reviewed | R-002, R-004, R-005 |
| 4 | Implement the required Cycle 1 workflow | Architecture and interfaces are sufficiently stable | Required workflow components are implemented and reviewable | R-001, R-002, R-005 |
| 5 | Integrate and test the end-to-end workflow | Major implementation components are available | Student-to-reviewer workflow operates as an integrated system | R-005, R-006 |
| 6 | Verify, correct defects, and prepare release evidence | Integrated workflow is available | Required behavior is verified and known limitations are documented | R-006, R-007 |

## Dependencies and Integration Checkpoints

| Checkpoint | Dependency / Trigger | Planned Action | Schedule Impact if Not Met |
|---|---|---|---|
| CP-001 — Requirements readiness | Requirements and acceptance criteria are sufficiently stable | Review A2 planning artifacts against the finalized requirements before architecture work is committed. | Architecture work may be delayed or require rework. |
| CP-002 — Architecture readiness | Architecture, interfaces, and major technology decisions are documented | Review implementation tasks and estimates before major construction begins. | Implementation estimates and task sequencing may require revision. |
| CP-003 — Initial integration | Major workflow components become available | Integrate components before all implementation work is considered complete. | Interface problems discovered late may threaten MS-004. |
| CP-004 — Verification readiness | Required end-to-end workflow is operating | Review tests, defects, traceability, and remaining risks before release preparation. | Verification or release preparation may require additional time. |

## Review Windows and Schedule Buffers

The team will not treat the Sakai deadline as the planned completion time for engineering work. Work should reach reviewable form early enough to allow another team member to inspect the evidence and request corrections.

For each major phase-gate deliverable:

1. The primary owner prepares the required evidence.
2. The backup owner or reviewer checks the work before merge.
3. Review comments and identified gaps are addressed.
4. The final evidence is merged into `main`.
5. The team performs a final phase-gate check before submission.

Schedule buffer is therefore reserved between the first reviewable version and the external course deadline. If work consumes this buffer, lower-priority work should be deferred before required Cycle 1 capabilities or verification evidence are compromised.

## Re-estimation and Schedule Triggers

The schedule and related estimates must be reviewed when any of the following occurs:

- a Cycle 1 requirement or acceptance criterion changes materially;
- a planned task becomes blocked by another task or decision;
- architecture or technology choices require substantially more effort than assumed;
- integration reveals an interface or rework problem;
- a primary owner becomes unavailable and work must move to a backup owner;
- testing identifies defects that materially affect Cycle 1 completion;
- an estimate moves outside its documented low-to-high range; or
- a tracked risk materializes and affects a milestone.

When one of these conditions occurs, the team will update the affected estimate, task, dependency, milestone, or risk rather than silently keeping an outdated commitment.

## Schedule Risks

| Risk | Schedule Effect | Planned Response |
|---|---|---|
| R-001 — Cycle 1 scope expansion | Additional features could delay implementation and verification. | Protect the required workflow and defer lower-priority additions. |
| R-002 — Technology learning/configuration | Architecture or implementation may take longer than estimated. | Simplify technology choices or re-estimate affected work. |
| R-003 — Team availability or delayed work | Dependent tasks and review windows may move. | Use backup ownership and redistribute work when necessary. |
| R-004 — Late requirement changes | Architecture and implementation may require rework. | Stabilize requirements before major construction and re-estimate affected work. |
| R-005 — Integration problems | End-to-end workflow completion may be delayed. | Integrate incrementally and resolve interface problems before release preparation. |
| R-006 — Late testing | Defects may consume release-preparation time. | Test throughout implementation and prioritize required workflow defects. |
| R-007 — Missing engineering evidence | Phase-gate readiness may be delayed even when implementation work exists. | Keep GitHub issues, PRs, reviews, planning, and traceability current as work occurs. |

## Schedule Change History

| Effective Gate | Change | Reason | Related Evidence |
|---|---|---|---|
| A1 | Initial semester schedule established | Establish the initial lifecycle sequence and phase-gate plan. | A1 planning baseline |
| A2 | Reworked schedule around Cycle 1 dependencies, checkpoints, review windows, buffers, and re-estimation triggers. | A2 requires the initial plan to become an actionable Cycle 1 engineering schedule tied to current planning evidence. | `estimates.md`, `task-plan.md`, `risk-register.md`, `traceability.md` |

## Current Schedule Assessment

The Cycle 1 plan is currently achievable under the documented estimate ranges provided that the required workflow remains intentionally small, requirements are stabilized before major construction, and integration and testing are not postponed until the end of the cycle.

The largest schedule uncertainties remain architecture/technology decisions, implementation and integration effort, and requirement changes. These uncertainties are represented in the three-point estimates and risks and will trigger re-estimation if the current assumptions no longer hold.
