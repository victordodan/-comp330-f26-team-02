# Traceability

**Team:** Codex Ramblers  
**Project:** CampusConnect  
**Current Phase:** A4 — Construction & Integration (A2/A3 evidence retained)  
**Release Cycle:** Cycle 1  

A4 carries forward the merged A3 requirements, architecture, and planned verification evidence from PRs #43, #44, #46, #47/#49, and #48. ADR-002 through ADR-004 are accepted for Cycle 1; final architecture-package review remains pending. No implementation or test execution is claimed.

The current requirements package contains proposed CampusConnect requirements and acceptance criteria. These are used as the current planning baseline while team review and finalization continue. Pending relationships are identified explicitly rather than inferred or invented.

## Requirements Traceability Matrix

The current CampusConnect requirements package contains six Cycle 1 requirements whose final acceptance status remains documented in the requirements files.

Current scope, task, ownership, and estimate relationships are linked below using the planning evidence that now exists. Proposed architecture and planned test evidence are linked below. Implementation and completed verification evidence will be added when those lifecycle artifacts exist.

| Requirement | Acceptance Criteria | Task / Planning Evidence | Owner | Estimate | Verification | Risk / Assumption | Status |
|---|---|---|---|---|---|---|---|
| REQ-001 — Student requester submits a support request containing the information required for Cycle 1 processing | AC-REQ-001-01 | SCP-001, TASK-010, TASK-013 | Shelby Sierah — requirements; Shu Perez — implementation | EST-002, EST-004 | Automated test / demonstration planned; no verification evidence yet | R-004 | Proposed |
| REQ-002 — Student requester views the current status and available resolution information for a submitted request | AC-REQ-002-01, AC-REQ-002-02 | SCP-006, TASK-010, TASK-013 | Shelby Sierah — requirements; Shu Perez — implementation | EST-002, EST-004 | Automated test / demonstration planned; no verification evidence yet | R-004 | Proposed |
| REQ-003 — System stores a submitted support request and assigns a unique identifier | AC-REQ-003-01 | SCP-002, TASK-010, TASK-013 | Shelby Sierah — requirements; Shu Perez — implementation | EST-002, EST-004 | Automated test / demonstration planned; no verification evidence yet | R-004 | Proposed |
| REQ-004 — Support reviewer views submitted support requests | AC-REQ-004-01 | SCP-003, TASK-010, TASK-013 | Shelby Sierah — requirements; Shu Perez — implementation | EST-002, EST-004 | Automated test / demonstration planned; no verification evidence yet | R-004 | Proposed |
| REQ-005 — Support reviewer changes the current status of a support request | AC-REQ-005-01 | SCP-004, TASK-010, TASK-013 | Shelby Sierah — requirements; Shu Perez — implementation | EST-002, EST-004 | Automated test / demonstration planned; no verification evidence yet | R-004 | Proposed |
| REQ-006 — Support reviewer records a short note or resolution for a support request | AC-REQ-006-01 | SCP-005, TASK-010, TASK-013 | Shelby Sierah — requirements; Shu Perez — implementation | EST-002, EST-004 | Automated test / demonstration planned; no verification evidence yet | R-004 | Proposed |

## A4 Construction Traceability Ledger — October 23, 2026

**Status distinction:** The table below maps **documented requirements and planned tests**, not completed implementation. `src/` and `tests/` each currently contain only a README; there is no verified executable feature or automated test code. The CI workflow remains the deliberately failing starter guard. No A4 implementation issue, commit, PR, or passing CI run is claimed.

| Requirement | Acceptance criteria | Planned test cases | Logical contracts (A3) | Implementation issue / branch / PR | Executed test / CI evidence | A4 status |
|---|---|---|---|---|---|---|
| REQ-001 — Submit request | AC-REQ-001-01, AC-REQ-001-02 | TC-001, TC-002 | API-001, API-007 | Not linked yet | None verified | Planned |
| REQ-002 — Own status and note | AC-REQ-002-01, AC-REQ-002-02, AC-REQ-002-03 | TC-003, TC-004, TC-005 | API-002, API-007 | Not linked yet | None verified | Planned |
| REQ-003 — Unique ID and persistence | AC-REQ-003-01, AC-REQ-003-02 | TC-006, TC-007 | API-001, API-006 | Not linked yet | None verified | Planned |
| REQ-004 — Reviewer access | AC-REQ-004-01, AC-REQ-004-02 | TC-008, TC-009 | API-003, API-007 | Not linked yet | None verified | Planned |
| REQ-005 — Status changes | AC-REQ-005-01, AC-REQ-005-02 | TC-010, TC-011; TC-009 also checks reviewer authorization | API-004, API-006, API-007 | Not linked yet | None verified | Planned |
| REQ-006 — Reviewer notes | AC-REQ-006-01 | TC-012; TC-009 also checks reviewer authorization | API-005, API-006, API-007 | Not linked yet | None verified | Planned |

**Cross-cutting acceptance coverage:** AC-REQ-004-02 / TC-009 also tests unauthorized reviewer operations relevant to REQ-005 and REQ-006; it is not an additional thirteenth criterion.

### A4 Evidence Link Protocol

For each implementation slice, update the corresponding ledger row **after evidence exists**, including:
1. A GitHub Issue with a requirement/AC ID, assigned owner, bounded change and risk.
2. Branch name, commit SHA and PR URL; list changed source and test files.
3. Independent reviewer feedback, author response, risk severity and merge/no-merge decision.
4. CI run URL, exact commit and actual build/test result; link test logs or manual execution evidence by TC ID.
5. AI-use log disclosure and AI code review, if applicable; dependency/version/license/security review if changed.
6. Known defects, deferred work and architecture/ADR changes.

**Review process evidence:** [A4 quality-review PR #61](https://github.com/victordodan/-comp330-f26-team-02/pull/61) proposes the review checklist and AI-assisted review template. It is process documentation, **not** evidence that implementation was reviewed or verified.

**Ownership:** Quality & Review Lead Giancarlo Herrera maintains this ledger and checks evidence completeness; feature developers supply code and test evidence, independent reviewers record decisions, and Victor coordinates final gate readiness.

### A4 Traceability Gaps and Next Actions

| Gap | Responsible role | Required next evidence |
|---|---|---|
| Requirements still marked Proposed | Requirements owners / Victor | Attributable baseline acceptance or recorded changes |
| No feature-specific implementation issue/PR | Shu and developers / Victor | Issues and small implementation PRs mapped to the ledger |
| No executable tests or actual results | Developers / Giancarlo | Test code, run output and TC mappings |
| Starter CI guard only | Developers / operations | Real PR-triggered build/test workflow and run URLs |
| No AI/dependency review of implementation | PR authors / Giancarlo / operations | Disclosures and evidence when actual changes arrive |
| No A4 release/phase gate | Victor / team | Reviewed evidence package and final tagged commit |

## A3 Proposed Architecture and Verification Traceability (October 15, 2026)

The relationships below are **proposed design and test coverage**, not completed tests. Merged PR #46 records twelve acceptance criteria and detailed workflow rules, reflected in the merged component/contracts and data/context documents. Final package review remains pending.

| Requirement | Proposed Logical Contract(s) — PR #43 | Planned Verification — PR #44 | Cross-Document Review |
|---|---|---|---|
| REQ-001 — Submit request | API-001, API-007 | Valid and invalid fields, requester context, failed storage; AC-REQ-001-01/02 proposed in PR #46 | Validate fields/limits and simulated-role session rules against PR #46 |
| REQ-002 — View own status and resolution | API-002, API-007 | Owner retrieval, Student B denial, unknown ID, note visibility; AC-REQ-002-01/02/03 proposed | Confirm ownership and reopened-note behavior |
| REQ-003 — Unique ID and persistence | API-001, API-006 | Unique identifiers, failed writes, restart persistence; AC-REQ-003-01/02 proposed | Confirm storage and lifecycle rules in PR #47 |
| REQ-004 — Reviewer access | API-003, API-007 | Reviewer listing and direct unauthorized-operation rejection; AC-REQ-004-01/02 proposed | Confirm simulated-role-only design, not real login |
| REQ-005 — Reviewer status changes | API-004, API-006, API-007 | Allowed/invalid transitions, reopen, missing IDs, failed writes; AC-REQ-005-01/02 proposed | Merged documents agree on the three-status model and reopening; verify implementation later |
| REQ-006 — Reviewer notes | API-005, API-006, API-007 | Save/replace note, visibility, input bounds, clearing restrictions, failed writes; AC-REQ-006-01 proposed | Align note editing and resolved-state rules |

**Proposed component owners of behavior:** Requester and Reviewer UI (interaction); Request Workflow Service (validation, authorization, transitions); Request Repository (persistence); Simulated Role Context (demo role and requester identity). These are design responsibilities, not assignments to individual team members.

**Source and review evidence:** [PR #43 — component responsibilities and API contracts](https://github.com/victordodan/-comp330-f26-team-02/pull/43), [PR #46 — proposed requirements and 12 acceptance checks](https://github.com/victordodan/-comp330-f26-team-02/pull/46), [PR #47 — data/context design](https://github.com/victordodan/-comp330-f26-team-02/pull/47), [PR #44 — test strategy and architecture review](https://github.com/victordodan/-comp330-f26-team-02/pull/44), and [PR #45 — security checklist review](https://github.com/victordodan/-comp330-f26-team-02/pull/45).

**ADR confirmation:** Victor Dodan approved ADR-002 through ADR-004 and confirmed active risks R-010 through R-012 and their owners on October 16, 2026. The overview and inline diagram are merged. Final cross-document review, review-finding dispositions, planning reconciliation, and implementation detail remain outstanding. Actual code/test evidence is expected in later construction and verification work.

## Decision Traceability

ADR-001 preserves the historical A2 deferral of the final production technology stack. ADR-002 through ADR-004, approved by Victor Dodan on October 16, 2026, establish local Cycle 1 technology, consistency, and persistence/recovery decisions. They do not establish completed implementation or a permanent production architecture.

| **Decision** | **Drivers / Inputs** | **Affected Architecture / Implementation** | **Verification / Follow-Up** |
| ------------ | -------------------- | ------------------------------------------ | ---------------------------- |
| ADR-001 — Defer final production technology stack | Cycle 1 requirements, `scope.md`, A2 planning evidence | Historical deferral of permanent production choices; Cycle 1 decisions now recorded in ADR-002–004 | Preserve history and distinguish local Cycle 1 decisions from production choices. |
| ADR-002 — Cycle 1 technology stack | Local Java/server-rendered demonstration; REQ-001–006 | Spring Boot, Thymeleaf, Maven, and file-backed H2; TASK-012/013, EST-003/004 | Accepted October 16, 2026; verify setup and implementation during construction. |
| ADR-003 — Request consistency | Request integrity and failure behavior; R-011 | Post/Redirect/Get, transactional updates, optimistic version checks; API-001/004/005/006 | Accepted October 16, 2026; plan repeated-submission, stale-write, and rollback checks. |
| ADR-004 — Local persistence and recovery | Restart persistence; R-010 | File-backed storage, backup and recovery expectations; API-006, EST-004/005 | Accepted October 16, 2026; document and test restart and recovery procedures during implementation. |

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
| R-010 — Local persistence failure | ADR-002/004, API-006, EST-004/005 | Active monitoring; Shu Perez confirmed as owner; document and verify persistence and recovery. |
| R-011 — Conflicting reviewer updates | ADR-003, API-004/005/006, EST-004/005 | Active monitoring; Venkata Aravind Mareddy confirmed as owner; verify transactional version checks and rejection of stale changes. |
| R-012 — Inconsistent architecture assumptions | ADR-002–004, TASK-012/013, MS-003/004 | Active monitoring; Victor Dodan confirmed as owner; reconcile accepted decisions and final review findings. |

## Change Impact Traceability

The transition from A1 to A2 established REQ-001 through REQ-006 and their corresponding acceptance-criteria identifiers as the current Cycle 1 planning baseline.

Current scope, task, ownership, and estimate relationships can now be traced to these requirements. The requirements remain Proposed, so downstream planning evidence must be reviewed if a requirement or acceptance criterion changes during team review.

Architecture, implementation, and completed verification relationships will be added as those artifacts are established. ADR-001 preserves the production-stack deferral; ADR-002 through ADR-004 provide the accepted local Cycle 1 decision traceability.

## Traceability Gaps

| Gap | Why It Matters | Owner | Planned Resolution | Target Gate |
|---|---|---|---|---|
| Cycle 1 requirements remain Proposed. | REQ-001 through REQ-006 provide the current planning baseline, but later requirement changes may require downstream traceability updates. | Shelby Sierah | Review and stabilize the current requirements package and resolve material requirement-level uncertainty. | A2 |
| Acceptance criteria remain Proposed. | Existing AC identifiers can now be traced, but changes during team review may affect planning and later verification evidence. | Shelby Sierah | Review and stabilize `docs/requirements/acceptance-criteria.md`. | A2 |
| Architecture evidence is proposed but not yet finalized. | Component/API and data/context documents and the overview/inline diagram are merged. ADR-002–004 are accepted; final cross-document review remains pending. | Architecture & Development Lead | Finish the cross-document review and record finding dispositions against the accepted ADRs and current merged documents. | A3 |
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
