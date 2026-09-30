# Traceability

**Team:** Codex Ramblers  
**Project:** CampusConnect  
**Current Phase:** A2 — Planning & Estimation  
**Release Cycle:** Cycle 1  

Traceability will be expanded as additional engineering evidence becomes available.

The current requirements package contains proposed CampusConnect requirements and acceptance criteria. These are used as the current planning baseline while team review and finalization continue. Pending relationships are identified explicitly rather than inferred or invented.

## Requirements Traceability Matrix

The current CampusConnect requirements package contains six proposed Cycle 1 requirements.

Current scope, task, ownership, and estimate relationships are linked below using the planning evidence that now exists. Architecture, implementation, and concrete verification evidence will be added as those lifecycle artifacts are established.

| Requirement | Acceptance Criteria | Task / Planning Evidence | Owner | Estimate | Verification | Risk / Assumption | Status |
|---|---|---|---|---|---|---|---|
| REQ-001 — Student requester submits a support request containing the information required for Cycle 1 processing | AC-REQ-001-01 | SCP-001, TASK-010, TASK-013 | Shelby Sierah — requirements; Shu Perez — implementation | EST-002, EST-004 | Automated test / demonstration planned; no verification evidence yet | R-004 | Proposed |
| REQ-002 — Student requester views the current status and available resolution information for a submitted request | AC-REQ-002-01, AC-REQ-002-02 | SCP-006, TASK-010, TASK-013 | Shelby Sierah — requirements; Shu Perez — implementation | EST-002, EST-004 | Automated test / demonstration planned; no verification evidence yet | R-004 | Proposed |
| REQ-003 — System stores a submitted support request and assigns a unique identifier | AC-REQ-003-01 | SCP-002, TASK-010, TASK-013 | Shelby Sierah — requirements; Shu Perez — implementation | EST-002, EST-004 | Automated test / demonstration planned; no verification evidence yet | R-004 | Proposed |
| REQ-004 — Support reviewer views submitted support requests | AC-REQ-004-01 | SCP-003, TASK-010, TASK-013 | Shelby Sierah — requirements; Shu Perez — implementation | EST-002, EST-004 | Automated test / demonstration planned; no verification evidence yet | R-004 | Proposed |
| REQ-005 — Support reviewer changes the current status of a support request | AC-REQ-005-01 | SCP-004, TASK-010, TASK-013 | Shelby Sierah — requirements; Shu Perez — implementation | EST-002, EST-004 | Automated test / demonstration planned; no verification evidence yet | R-004 | Proposed |
| REQ-006 — Support reviewer records a short note or resolution for a support request | AC-REQ-006-01 | SCP-005, TASK-010, TASK-013 | Shelby Sierah — requirements; Shu Perez — implementation | EST-002, EST-004 | Automated test / demonstration planned; no verification evidence yet | R-004 | Proposed |

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

The transition from A1 to A2 established REQ-001 through REQ-006 and their corresponding acceptance-criteria identifiers as the current Cycle 1 planning baseline.

Current scope, task, ownership, and estimate relationships can now be traced to these requirements. The requirements remain Proposed, so downstream planning evidence must be reviewed if a requirement or acceptance criterion changes during team review.

Decision, architecture, implementation, and completed verification relationships will be added as those artifacts are established.

## Traceability Gaps

| Gap | Why It Matters | Owner | Planned Resolution | Target Gate |
|---|---|---|---|---|
| Cycle 1 requirements remain Proposed. | REQ-001 through REQ-006 provide the current planning baseline, but later requirement changes may require downstream traceability updates. | Shelby Sierah | Review and stabilize the current requirements package and resolve material requirement-level uncertainty. | A2 |
| Acceptance criteria remain Proposed. | Existing AC identifiers can now be traced, but changes during team review may affect planning and later verification evidence. | Shelby Sierah | Review and stabilize `docs/requirements/acceptance-criteria.md`. | A2 |
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
