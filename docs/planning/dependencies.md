# Planning Dependencies

**Team:** Codex Ramblers
**Project:** CampusConnect
**Current Phase:** A2 — Planning and Estimation
**Release Cycle:** Cycle 1

This document consolidates Cycle 1 dependency evidence across the task plan,
schedule, estimates, and risk register. Task IDs follow the `TASK-001`–`TASK-018`
convention used in `task-plan.md` for consistency across all planning artifacts.

> **Note on GitHub Issues:** Cycle 1 task issues are being created on a
> per-pull-request basis going forward as the team was previously working
> on the main file. Because of this, the GitHub Issue column below links to the repository's
> [Issues tab](https://github.com/victordodan/-comp330-f26-team-02/issues) rather
> than to individual issue numbers, which are assigned dynamically by GitHub.

---

## 1. Dependency Traceability Table

| Task ID | Task Description | Prerequisite Dependency | Dependency Type | Owner | GitHub Issue |
|---------|------------------|-------------------------|-----------------|-------|--------------|
| TASK-001 | Complete repository README and project entry point | None | Initial Setup | Victor Dodan | [Issues tab](https://github.com/victordodan/-comp330-f26-team-02/issues) |
| TASK-003 | Complete Role Matrix and acknowledgement evidence | Team role decisions | Initial Setup | Malec Tarabein | [Issues tab](https://github.com/victordodan/-comp330-f26-team-02/issues) |
| TASK-006 | Complete Initial Requirements package | Project brief / Cycle 1 scope | Initial Setup | Shelby Sierah | [Issues tab](https://github.com/victordodan/-comp330-f26-team-02/issues) |
| TASK-007 | Complete initial Planning and Risk package | Initial scope and requirements direction | Initial Setup | Victor Dodan | [Issues tab](https://github.com/victordodan/-comp330-f26-team-02/issues) |
| TASK-008 | Create Initial Decision Record | Initial project and workflow decisions | Initial Setup | Malec Tarabein | [Issues tab](https://github.com/victordodan/-comp330-f26-team-02/issues) |
| TASK-009 | Review A1 evidence and establish Project Launch baseline | TASK-001 – TASK-008 | Sequence / Gate | Victor Dodan | [Issues tab](https://github.com/victordodan/-comp330-f26-team-02/issues) |
| TASK-010 | Refine requirements, acceptance criteria, assumptions, open questions | Initial Requirements package (TASK-006) | Sequence | Shelby Sierah | [Issues tab](https://github.com/victordodan/-comp330-f26-team-02/issues) |
| TASK-011 | Refine scope, estimates, schedule, risks, traceability | TASK-010 | Sequence | Shu Perez | [Issues tab](https://github.com/victordodan/-comp330-f26-team-02/issues) |
| TASK-012 | Define system architecture, interfaces, major engineering decisions | Refined requirements and planning (TASK-011) | Technical / Sequence | Shu Perez | [Issues tab](https://github.com/victordodan/-comp330-f26-team-02/issues) |
| TASK-013 | Implement controlled Cycle 1 CampusConnect workflow | TASK-012 | Technical / Sequence | Shu Perez | [Issues tab](https://github.com/victordodan/-comp330-f26-team-02/issues) |
| TASK-014 | Review implementation, establish automated testing and CI | TASK-013 | Technical / Interface | Giancarlo Herrera | [Issues tab](https://github.com/victordodan/-comp330-f26-team-02/issues) |
| TASK-015 | Verify Cycle 1 workflow, address defects, prepare release evidence | TASK-013 and TASK-014 | Technical / Sequence | Giancarlo Herrera | [Issues tab](https://github.com/victordodan/-comp330-f26-team-02/issues) |
| TASK-016 | Prepare and present Cycle 1 release | TASK-015 | Sequence / Gate | Shelby Sierah | [Issues tab](https://github.com/victordodan/-comp330-f26-team-02/issues) |
| TASK-017 | Review Cycle 1 evidence, select Cycle 2 maturity improvements | Cycle 1 release (TASK-016) | Sequence | Victor Dodan | [Issues tab](https://github.com/victordodan/-comp330-f26-team-02/issues) |
| TASK-018 | Complete maturity improvements, prepare final release evidence | TASK-017 | Sequence | Codex Ramblers | [Issues tab](https://github.com/victordodan/-comp330-f26-team-02/issues) |

**Dependency types used:** Initial Setup · Sequence · Technical / Sequence ·
Technical / Interface · Sequence / Gate

> Downstream tasks (TASK-012 – TASK-018) are **Planned / Pending Prerequisite**,
> not actively blocked. Per team practice, a task is only marked **Blocked** during
> execution when work cannot proceed due to a stuck upstream PR, schema contract,
> or external dependency — not simply because it is scheduled later.

---

## 2. Milestone & Integration Dependencies

| Milestone | Planned Outcome | Depends On | Related Tasks / Estimates | Owner | Status |
|-----------|-----------------|------------|---------------------------|-------|--------|
| MS-001 | Project Launch baseline | Team formation and project direction | EST-001 | Codex Ramblers | Complete |
| MS-002 | A2 planning and requirements baseline | MS-001 | TASK-010, TASK-011, EST-002 | Planning & Requirements owners | In Progress |
| MS-003 | Architecture baseline | MS-002 | TASK-012, EST-003 | Architecture & Development Lead | Planned |
| MS-004 | Cycle 1 implementation baseline | MS-003 | TASK-013, TASK-014, EST-004 | Development team | Planned |
| MS-005 | Cycle 1 verification and release readiness | MS-004 | TASK-015, TASK-016, EST-005 | Quality & Review Lead / team | Planned |
| MS-006 | Cycle 2 maturity planning | MS-005 | TASK-017, EST-006 | Codex Ramblers | Planned |

### Integration Checkpoints & Review Windows

| Checkpoint | Dependency / Trigger | Planned Action | Schedule Impact if Not Met |
|------------|----------------------|----------------|----------------------------|
| CP-001 — Requirements readiness | Requirements and acceptance criteria sufficiently stable | Review A2 planning artifacts against finalized requirements before architecture work is committed | Architecture work may be delayed or require rework |
| CP-002 — Architecture readiness | Architecture, interfaces, and major technology decisions documented | Review implementation tasks and estimates before major construction begins | Implementation estimates and sequencing may require revision |
| CP-003 — Initial integration | Major workflow components become available | Integrate components before all implementation work is considered complete | Interface problems discovered late may threaten MS-004 |
| CP-004 — Verification readiness | Required end-to-end workflow operating | Review tests, defects, traceability, and remaining risks before release preparation | Verification or release prep may require additional time |

### Sequencing Rules

1. Requirements and planning are refined before architecture is finalized.
2. Architecture and interfaces are established before major implementation begins.
3. Implementation and integration occur before final Cycle 1 verification.
4. Testing and review occur throughout implementation, not only at the end.
5. Schedule commitments are reconsidered when requirements, estimates, dependencies, or risks materially change.

**Review windows:** Each phase-gate deliverable is prepared by the primary owner,
checked by the backup owner/reviewer, corrected, merged into `main`, and given a
final phase-gate check before submission. Schedule buffer is reserved between the
first reviewable version and the external Sakai deadline.

---

## 3. Technical Dependencies & Uncertainty (Estimate Alignment)

| Estimate | Primary Tasks | Important Dependencies | Uncertainty Effect | Related Risks |
|----------|---------------|------------------------|--------------------|---------------|
| EST-002 | TASK-010, TASK-011 | Finalized requirements and acceptance criteria | Medium — workflow understood, but final acceptance criteria may shift planning | R-001, R-004, R-007 |
| EST-003 | TASK-012 | TASK-010 and TASK-011 | Low confidence — architecture, interfaces, technology not yet established | R-002, R-004 |
| EST-004 | TASK-013, TASK-014 | TASK-012 and stable interfaces | Low confidence — depends on architecture choices, integration behavior, team experience | R-001, R-002, R-004, R-005 |
| EST-005 | TASK-015, TASK-016 | TASK-013 and TASK-014 | Low confidence — depends on completeness and quality of implementation | R-005, R-006, R-007 |

**Estimate ranges (person-hours):**

| Estimate | Low | Likely | High |
|----------|-----|--------|------|
| EST-002 | 10 h | 14 h | 20 h |
| EST-003 | 12 h | 18 h | 26 h |
| EST-004 | 24 h | 36 h | 50 h |
| EST-005 | 12 h | 18 h | 26 h |

Technical dependencies and missing prerequisites are the primary drivers of the
wider ranges for EST-003 through EST-005.

---

## 4. Risk Register Alignment

Each high-risk dependency is linked to its corresponding trigger in
`/docs/planning/risk-register.md`.

| Risk ID | Risk Event | Trigger Event (Dependency Link) | Impact | Mitigation Strategy | Owner |
|---------|------------|--------------------------------|--------|---------------------|-------|
| R-001 | Cycle 1 scope expansion | Scope grows beyond the required support-request workflow (affects TASK-013, TASK-014) | High | Keep scope focused per `scope.md`; review additions before accepting | Malec Tarabein |
| R-002 | Technology learning/configuration | TASK-012 architecture/setup takes longer than EST-003 predicts | High | Prefer supportable technologies; reduce complexity if needed | Shu Perez |
| R-003 | Team availability / delayed work | Assigned work misses an internal target (affects dependent tasks & review windows) | High | Maintain primary/backup ownership; communicate blockers early | Victor Dodan |
| R-004 | Late requirement changes | Requirements unresolved when architecture begins, or change after implementation starts (affects TASK-010 → TASK-012) | High | Stabilize requirements before major construction; re-estimate affected work | Malec Tarabein |
| R-005 | Integration problems | Components work independently but fail when combined (affects TASK-013, TASK-014) | High | Define interfaces before implementation; integrate incrementally | Shu Perez |
| R-006 | Late testing | Major workflow behavior lacks tests as Verification module approaches | High | Test throughout implementation; prioritize required-workflow defects | Giancarlo Herrera |
| R-007 | Missing engineering evidence | Completed work cannot be linked to expected issue, PR, review, test, or doc evidence | High | Keep requirements, issues, PRs, decisions, tests, and planning evidence current | Victor Dodan |

**Materialized risks:** None at this stage.
**Closed / accepted risks:** None.

---

## 5. Re-estimation & Schedule Triggers

Dependencies, estimates, and schedule commitments are reviewed when any of the
following occurs:

- A Cycle 1 requirement or acceptance criterion changes materially.
- A planned task becomes blocked by another task or decision.
- Architecture or technology choices require substantially more effort than assumed.
- Integration reveals an interface or rework problem.
- A primary owner becomes unavailable and work moves to a backup owner.
- Testing identifies defects that materially affect Cycle 1 completion.
- An estimate moves outside its documented low-to-high range.
- A tracked risk materializes and affects a milestone.

When a trigger occurs, the affected estimate, task, dependency, milestone, or
risk is updated rather than silently keeping an outdated commitment. Material
revisions preserve the previous value in repository history with the reason for
the change.

---

## 6. Traceability Summary

| Layer | Source Artifact | Connects To |
|-------|-----------------|-------------|
| Tasks | `task-plan.md` (TASK-001 – TASK-018) | GitHub Issues, estimates, milestones |
| Estimates | `estimates.md` (EST-002 – EST-005) | Tasks, risks |
| Milestones | `schedule.md` (MS-001 – MS-006) | Tasks, checkpoints, phase gates |
| Risks | `risk-register.md` (R-001 – R-007) | Estimates, tasks, milestones |
| Dependencies | This document | All of the above |

The same task ID (`TASK-001` … `TASK-018`) is used consistently across the task
plan, this dependency table, the traceability matrix, the estimate sheet, and the
GitHub issue, so every dependency can be traced end to end.

---

_Last updated: 9/29/26 20:45_
