# Estimates

**Team:** Codex Ramblers  
**Project:** CampusConnect  
**Current Phase:** A2 — Planning and Estimation  
**Release Cycle:** Cycle 1  

## Estimation Approach

Codex Ramblers uses team person-hours and three-point estimates to represent uncertainty in Cycle 1 planning. Each planned item has a low, likely, and high estimate.

- **Low** represents a favorable case with limited rework.
- **Likely** represents the team's current expected effort.
- **High** represents additional effort caused by unresolved requirements, technical decisions, integration problems, testing, or rework.

The estimates are planning commitments based on the evidence currently available. They will be revised when new engineering evidence changes the assumptions on which an estimate depends. Earlier estimates will not be silently replaced; material changes will be recorded with the reason for re-estimation.

## Cycle 1 Estimates

| ID | Work Item | Low | Likely | High | Basis | Related Task(s) | Owner(s) | Confidence |
|---|---|---:|---:|---:|---|---|---|---|
| EST-002 | Requirements and planning refinement | 10 h | 14 h | 20 h | Refine requirements, acceptance criteria, assumptions, scope, schedule, risks, estimates, and traceability for the A2 baseline. | TASK-010, TASK-011 | Requirements and Planning owners | Medium |
| EST-003 | Architecture and design | 12 h | 18 h | 26 h | Define the technology stack, system structure, interfaces, and major engineering decisions after the A2 requirements baseline is established. | TASK-012 | Architecture & Development Lead / team | Low |
| EST-004 | Cycle 1 implementation and integration | 24 h | 36 h | 50 h | Implement and integrate the controlled student support-request workflow, including request submission, persistence, reviewer actions, status updates, resolution information, and supporting tests. | TASK-013, TASK-014 | Development team | Low |
| EST-005 | Cycle 1 verification and release preparation | 12 h | 18 h | 26 h | Perform end-to-end verification, correct defects, review evidence, document limitations, and prepare Cycle 1 release evidence. | TASK-015, TASK-016 | Quality & Review Lead / team | Low |

## Estimation Basis and Assumptions

| Estimate | Key Assumptions | Evidence / Dependency | Effect if Assumption Is Wrong |
|---|---|---|---|
| EST-002 | Cycle 1 remains limited to the agreed CampusConnect support-request workflow and the A2 requirements can be finalized without major scope expansion. | `docs/requirements/`, `scope.md`, TASK-010, TASK-011 | Requirements and planning work must be re-estimated if significant capabilities are added or acceptance criteria materially change. |
| EST-003 | Requirements and acceptance criteria are sufficiently stable before architecture decisions are finalized, and the team chooses technologies it can reasonably support. | TASK-010, TASK-011, TASK-012, R-002, R-004 | Architecture effort may increase because of additional investigation, redesign, or technology learning. |
| EST-004 | Architecture and interfaces are established before major implementation and the team continues to use synthetic data and simulated roles. | TASK-012, TASK-013, TASK-014, R-001, R-002, R-004, R-005 | Implementation may require additional integration or rework and must be re-estimated. |
| EST-005 | A complete Cycle 1 workflow is available early enough for verification and testing develops alongside implementation. | TASK-013, TASK-014, TASK-015, TASK-016, R-005, R-006 | Verification effort or schedule must be revised if implementation is late, unstable, or produces significant defects. |

## Confidence and Uncertainty

Confidence is based on how much engineering evidence currently exists.

| Estimate ID | Confidence | Current Uncertainty |
|---|---|---|
| EST-002 | Medium | The Cycle 1 workflow is understood, but the final A2 requirements and acceptance criteria may still cause planning adjustments. |
| EST-003 | Low | Architecture, interfaces, and technology decisions are not yet fully established. |
| EST-004 | Low | Implementation effort depends on finalized requirements, architecture choices, integration behavior, and team experience. |
| EST-005 | Low | Verification effort depends on the completeness and quality of the implementation produced during construction. |

The wider ranges for EST-003 through EST-005 are intentional. The team currently has less direct evidence for architecture, implementation, integration, and verification than for A2 planning work.

## Estimate-to-Task and Risk Relationships

| Estimate | Primary Tasks | Important Dependencies | Related Risks |
|---|---|---|---|
| EST-002 | TASK-010, TASK-011 | Finalized requirements and acceptance criteria | R-001, R-004, R-007 |
| EST-003 | TASK-012 | TASK-010 and TASK-011 | R-002, R-004 |
| EST-004 | TASK-013, TASK-014 | TASK-012 and stable interfaces | R-001, R-002, R-004, R-005 |
| EST-005 | TASK-015, TASK-016 | TASK-013 and TASK-014 | R-005, R-006, R-007 |

## Re-estimation Triggers

The team will review and, when necessary, revise an estimate when engineering evidence shows that its assumptions are no longer reasonable.

Re-estimation is specifically triggered when:

- requirements or acceptance criteria materially change;
- Cycle 1 scope is expanded or reduced;
- an architecture or technology decision changes expected implementation effort;
- a dependency or assigned task becomes blocked;
- integration work takes materially longer than expected;
- actual effort falls outside the current low-to-high range;
- significant defects or verification work are discovered;
- a tracked risk materializes and affects planned effort; or
- a milestone or phase-gate commitment is threatened.

A material revision should preserve the previous estimate in repository history and record why the estimate changed.

## Estimate Changes

### A1 to A2

The A1 estimates were initial planning ranges created before the requirements and task relationships were mature.

For A2:

- the planning estimate is connected to TASK-010 and TASK-011;
- architecture, implementation, and verification estimates are connected to their planned tasks;
- low, likely, and high values are stated explicitly;
- assumptions and dependencies are identified;
- relevant risks are linked to estimates;
- confidence and uncertainty are documented; and
- explicit re-estimation triggers are defined.

The current numerical ranges have not been changed solely to create a new A2 baseline. They remain the team's current planning judgment until additional engineering evidence supports a material revision.

## Estimate vs. Actual

A reliable estimate-versus-actual comparison is not yet available for Cycle 1 implementation because the related architecture, construction, and verification work has not been completed.

As estimated tasks are completed, the team will compare actual effort with the applicable estimate and use the difference as evidence for later re-estimation. Actual effort will not be invented retrospectively where reliable evidence was not recorded.

## Planning Implications

| Evidence | Planning Impact |
|---|---|
| EST-002 | Requirements and planning evidence should be stabilized before architecture is treated as committed. |
| EST-003 | Architecture decisions should be reviewed before major Cycle 1 implementation begins. |
| EST-004 | Construction is the largest expected technical effort, so Cycle 1 scope should remain intentionally small and integration should occur incrementally. |
| EST-005 | Verification should not be postponed until the end of Cycle 1; testing and review evidence should develop with implementation. |

## A2 Ownership and Review

**Primary owner:** Venkata Aravind Mareddy (@VAM-hub)  
**Tracking issue:** #15 — Create Cycle 1 estimates and schedule for A2

This estimate evidence should be reviewed together with the finalized Cycle 1 requirements, task plan, schedule, scope, and risk register before the A2 planning baseline is considered complete.
