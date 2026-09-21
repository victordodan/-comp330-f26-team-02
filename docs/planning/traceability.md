# Traceability

**Team:** Codex Ramblers  
**Project:** CampusConnect  
**Current Phase:** A1 — Project Launch  
**Release Cycle:** Cycle 1  

Traceability will be expanded as additional engineering evidence becomes available.

At A1, the Initial Requirements package and Initial Decision Record are still incomplete, so related requirement and decision references remain pending.

## Requirements Traceability Matrix

The Initial Requirements package has not yet been finalized, so authoritative CampusConnect requirement and acceptance-criteria IDs are not currently available.

The traceability matrix will be populated after `docs/requirements/requirements.md` and `docs/requirements/acceptance-criteria.md` are completed.

| Requirement | Acceptance Criteria | Architecture / Design | Implementation | Verification | Risk / Assumption | Status |
|---|---|---|---|---|---|---|
| Pending | Pending | Not yet available | Not yet implemented | Not yet available | R-004 | Requirements baseline in progress |

## Decision Traceability

The Initial Decision Record has not yet been completed. Decision relationships will be added after the first project ADR is accepted.

| Decision | Drivers / Inputs | Affected Architecture / Implementation | Verification / Follow-Up |
|---|---|---|---|
| Initial ADR pending | CampusConnect Project Brief, `scope.md`, Initial Requirements | Not yet available | Update traceability after the Initial Decision Record is completed. |

## Risk and Assumption Traceability

| Risk / Assumption | Affected Evidence | Current Effect / Action |
|---|---|---|
| R-001 — Cycle 1 scope expansion | `scope.md`, `task-plan.md`, EST-004, MS-004 | Keep Cycle 1 limited to the required end-to-end support-request workflow and defer unnecessary additions. |
| R-002 — Technology learning or configuration takes longer than expected | EST-003, EST-004, MS-003, MS-004 | Technology choices should remain manageable for the team and be reconsidered if they create unnecessary complexity. |
| R-003 — Team availability or delayed assigned work | `task-plan.md`, `schedule.md`, `docs/team/roles.md` | Primary and backup ownership and early communication are used to reduce schedule disruption. |
| R-004 — Requirements remain incomplete or change late | `docs/requirements/`, EST-002, EST-003, EST-004 | Complete and review the Initial Requirements package before major architecture and construction work begins. |
| R-005 — Independently developed components fail to integrate | MS-003, MS-004, EST-004 | Interfaces should be defined before implementation and integration should occur throughout construction. |
| R-006 — Testing begins too late | EST-004, EST-005, MS-004, MS-005 | Testing and verification should develop alongside implementation instead of being postponed until the end of Cycle 1. |
| R-007 — Repository evidence becomes incomplete or outdated | `task-plan.md`, `docs/team/working-agreements.md`, GitHub repository | Engineering evidence should be updated as work occurs and reviewed before each phase gate. |

## Change Impact Traceability

No material upstream changes have occurred yet.

This section will be updated when a requirement, assumption, decision, scope item, or architecture element changes in a way that requires downstream evidence to be reviewed or updated.

## Traceability Gaps

| Gap | Why It Matters | Owner | Planned Resolution | Target Gate |
|---|---|---|---|---|
| Initial CampusConnect requirements have not yet been finalized. | Authoritative CampusConnect requirement IDs are not yet available for traceability. | Shelby Sierah | Complete the Initial Requirements package and establish the team's actual requirements. | A1 |
| Acceptance criteria have not yet been finalized. | Requirements cannot yet be connected to authoritative acceptance-criteria IDs or later verification evidence. | Shelby Sierah | Complete `docs/requirements/acceptance-criteria.md` and establish stable acceptance-criteria IDs. | A1 |
| Planning traceability cannot yet use finalized requirement IDs. | Scope and risk relationships exist, but requirement-level links cannot be completed until the requirements baseline is finalized. | Victor Dodan | Update this file after the Initial Requirements package is completed. | A1 |
| Initial ADR has not yet been completed. | Significant project decisions cannot yet be traced forward to architecture or implementation. | Malec Tarabein | Complete the Initial Decision Record and update Decision Traceability. | A1 |
| Architecture evidence does not yet exist. | Requirements cannot yet be traced to specific architectural components or interfaces. | Architecture & Development Lead | Add architecture relationships as Module 3 evidence is created. | A3 |
| Implementation evidence does not yet exist. | Requirements cannot yet be traced to source code or implementation pull requests. | Development team | Add implementation references during Construction. | A4 |
| Verification evidence does not yet exist. | Requirements and acceptance criteria cannot yet be traced to tests or other proof of behavior. | Quality & Review Lead / team | Add test and verification references as evidence becomes available. | A4–A5 |

## Traceability Maintenance

Codex Ramblers will update traceability as engineering evidence develops instead of attempting to reconstruct all relationships at the end of the semester.

Traceability should be reviewed when:

- a requirement or acceptance criterion is added or changed;
- a significant ADR is accepted or revised;
- architecture or interface decisions change;
- implementation is merged through a pull request;
- verification evidence becomes available;
- a tracked risk materializes or changes significantly; or
- the team prepares for a phase-gate submission.

Missing downstream evidence will remain identified as a traceability gap until the related lifecycle work has actually been completed.