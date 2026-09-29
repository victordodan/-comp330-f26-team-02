# Traceability

**Team:** Codex Ramblers  
**Project:** CampusConnect  
**Current Phase:** A2 — Planning & Estimation  
**Release Cycle:** Cycle 1  

Traceability will be expanded as additional engineering evidence becomes available.

The current requirements package contains proposed CampusConnect requirements and acceptance criteria. These are used as the current planning baseline while team review and finalization continue. Pending relationships are identified explicitly rather than inferred or invented.

## Requirements Traceability Matrix

The current CampusConnect requirements package contains two proposed Cycle 1 requirements.

Task, estimate, architecture, implementation, and verification references will be added as the corresponding authoritative engineering evidence is established.

| Requirement | Acceptance Criteria | Task / Planning Evidence | Owner | Estimate | Verification | Risk / Assumption | Status |
|---|---|---|---|---|---|---|---|
| REQ-001 — Authenticated student submits a workflow request containing required information | AC-REQ-001-01, AC-REQ-001-02 | Pending — awaiting finalized task plan | Pending — awaiting task ownership | Pending — awaiting estimates | Automated test / demonstration planned; no verification evidence yet | R-004 | Proposed |
| REQ-002 — Requester views the current status of submitted workflow requests | AC-REQ-002-01 | Pending — awaiting finalized task plan | Pending — awaiting task ownership | Pending — awaiting estimates | Automated test / demonstration planned; no verification evidence yet | R-004 | Proposed |

## Decision Traceability

The Initial Decision Record has not yet been completed. Decision relationships will be added after the first project ADR is accepted.

| Decision | Drivers / Inputs | Affected Architecture / Implementation | Verification / Follow-Up |
|---|---|---|---|
| Initial ADR pending | CampusConnect Project Brief, `scope.md`, proposed CampusConnect requirements | Not yet available | Update traceability after the Initial Decision Record is completed. |

## Risk and Assumption Traceability

| Risk / Assumption | Affected Evidence | Current Effect / Action |
|---|---|---|
| R-001 — Cycle 1 scope expansion | `scope.md`, `task-plan.md`, EST-004, MS-004 | Keep Cycle 1 limited to the required end-to-end support-request workflow and defer unnecessary additions. |
| R-002 — Technology learning or configuration takes longer than expected | EST-003, EST-004, MS-003, MS-004 | Technology choices should remain manageable for the team and be reconsidered if they create unnecessary complexity. |
| R-003 — Team availability or delayed assigned work | `task-plan.md`, `schedule.md`, `docs/team/roles.md` | Primary and backup ownership and early communication are used to reduce schedule disruption. |
| R-004 — Requirements remain incomplete or change late | `docs/requirements/`, EST-002, EST-003, EST-004 | Review and finalize the proposed requirements before major architecture and construction work begins. |
| R-005 — Independently developed components fail to integrate | MS-003, MS-004, EST-004 | Interfaces should be defined before implementation and integration should occur throughout construction. |
| R-006 — Testing begins too late | EST-004, EST-005, MS-004, MS-005 | Testing and verification should develop alongside implementation instead of being postponed until the end of Cycle 1. |
| R-007 — Repository evidence becomes incomplete or outdated | `task-plan.md`, `docs/team/working-agreements.md`, GitHub repository | Engineering evidence should be updated as work occurs and reviewed before each phase gate. |

## Change Impact Traceability

The transition from A1 to A2 introduces proposed requirement and acceptance-criteria IDs that can now be used for planning traceability.

REQ-001 and REQ-002 are currently marked Proposed. Downstream task, ownership, estimate, decision, architecture, implementation, and verification relationships will be reviewed as those artifacts are finalized.

## Traceability Gaps

| Gap | Why It Matters | Owner | Planned Resolution | Target Gate |
|---|---|---|---|---|
| Proposed CampusConnect requirements have not yet been finalized. | REQ-001 and REQ-002 can be used for current planning, but their Proposed status means later changes may require downstream traceability updates. | Shelby Sierah | Review and finalize the current requirements package and record any additional approved requirements. | A2 |
| Acceptance criteria remain Proposed. | Existing AC IDs can be traced, but changes during team review may affect tasks and later verification evidence. | Shelby Sierah | Review and finalize `docs/requirements/acceptance-criteria.md`. | A2 |
| Task and ownership relationships are pending. | Requirements cannot yet be fully connected to Cycle 1 implementation work and responsible owners. | Planning team | Update this matrix after the authoritative task plan and GitHub Issues establish task IDs and ownership. | A2 |
| Estimate relationships are pending. | Requirements cannot yet be traced to finalized effort ranges and estimation assumptions. | Planning team | Add estimate references after `docs/planning/estimates.md` is finalized. | A2 |
| Initial ADR has not yet been completed. | Significant project decisions cannot yet be traced forward to architecture or implementation. | Malec Tarabein | Complete the Initial Decision Record and update Decision Traceability. | A2 |
| Architecture evidence does not yet exist. | Requirements cannot yet be traced to specific architectural components or interfaces. | Architecture & Development Lead | Add architecture relationships as Module 3 evidence is created. | A3 |
| Implementation evidence does not yet exist. | Requirements cannot yet be traced to source code or implementation pull requests. | Development team | Add implementation references during Construction. | A4 |
| Verification evidence does not yet exist. | Requirements and acceptance criteria cannot yet be traced to tests or other proof of behavior. | Quality & Review Lead / team | Add test and verification references as evidence becomes available. | A4–A5 |

## Traceability Maintenance

Codex Ramblers will update traceability as engineering evidence develops instead of attempting to reconstruct all relationships at the end of the semester.

Traceability should be reviewed when:

- a requirement or acceptance criterion is added, finalized, or changed;
- a Cycle 1 task or GitHub Issue is created or changed;
- an estimate or task owner changes;
- a significant ADR is accepted or revised;
- architecture or interface decisions change;
- implementation is merged through a pull request;
- verification evidence becomes available;
- a tracked risk materializes or changes significantly; or
- the team prepares for a phase-gate submission.

Missing downstream evidence will remain identified as a traceability gap until the related lifecycle work has actually been completed.
