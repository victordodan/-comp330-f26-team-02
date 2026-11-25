# Task Plan

**Team:** Codex Ramblers  
**Project:** CampusConnect  
**Current Phase:** A6 — Final Cycle Release, Presentation and Peer Review
**Release Cycle:** Cycle 2

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
| TASK-012 | Define and review the system architecture, interfaces, and major engineering decisions | Shu Perez | Venkata Aravind Mareddy | `docs/architecture/`, `docs/decisions/` | EST-003 | A3 | Refined requirements and planning evidence | Complete (A5, `a5-cycle1-release`) |
| TASK-013 | Implement the controlled Cycle 1 CampusConnect workflow | Shu Perez | Venkata Aravind Mareddy | `src/`, GitHub Issues, and pull requests | EST-004 | A4 | TASK-012 | Complete (A5, `a5-cycle1-release`) |
| TASK-014 | Review implementation and establish automated testing and CI evidence | Giancarlo Herrera | Victor Dodan | `tests/`, `.github/`, `docs/reviews/`, `docs/testing/` | EST-004 | A4 | TASK-013 | Complete (A5, `a5-cycle1-release`) |
| TASK-015 | Verify the Cycle 1 workflow, address defects, and prepare release evidence | Giancarlo Herrera | Shelby Sierah | `docs/testing/`, `docs/quality/`, `docs/release/` | EST-005 | A5 | TASK-013 and TASK-014 | Complete (A5, `a5-cycle1-release`) |
| TASK-016 | Prepare and present the Cycle 1 release with known limitations and supporting evidence | Shelby Sierah | Victor Dodan | `docs/release/`, Cycle 1 repository baseline | EST-005 | A5 | TASK-015 | Complete (A5, `a5-cycle1-release`) |
| TASK-017 | Review Cycle 1 evidence and select a small set of Cycle 2 maturity improvements | Victor Dodan | Malec Tarabein | Cycle 1 postmortem, risks, defects, estimates, and review evidence | EST-006 | A6 | Cycle 1 release | Complete (PR #115; targets proposed) |
| TASK-018 | Complete selected maturity improvements and prepare the final release evidence | Codex Ramblers | Shelby Sierah | `docs/release/`, `docs/observability/`, `docs/security/`, `docs/operations/`, `docs/ai/` | EST-006 | A6 | TASK-017 | In Progress (TASK-018.1–018.7) |

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

No task is blocked, but one decision gates TASK-018 (see the A6 section below):
Victor's confirmation of the remaining maturity targets and owners in PR #115
(the MT-02 runbook assignment was confirmed on 2026-11-24).

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

Victor Dodan approved the following A3 decisions on October 16, 2026:

- **ADR-002:** Selects Java, Spring Boot, Thymeleaf, Maven, and file-backed H2 for Cycle 1.
- **ADR-003:** Defines duplicate-submission handling, optimistic locking, and transactional updates.
- **ADR-004:** Defines local database persistence, backup expectations, and recovery procedures.

These ADRs are accepted Cycle 1 design decisions. Their impact on implementation tasks and estimates must be reconciled before the final architecture package review. Implementation and verification remain pending.

### A3 Task Coordination

| Task | Primary Owner | Supporting Reviewer | Current Planning Status |
|---|---|---|---|
| Architecture overview and component integration | Shu Perez | Venkata Aravind Mareddy | Under review |
| Component responsibilities and API contracts | Venkata Aravind Mareddy | Shu Perez | Documentation merged; architecture alignment pending |
| Technology and engineering ADRs | Giancarlo Herrera | Shu Perez | Accepted by Victor Dodan on October 16, 2026; implementation pending |
| Architecture review and quality strategy | Giancarlo Herrera | Victor Dodan | Initial evidence merged; follow-up review needed |
| Security and governance checklist | Shelby Sierah | Giancarlo Herrera | Under review |
| Architecture package reconciliation | Shu Perez | Victor Dodan | Pending final review |
| A3 planning alignment and submission readiness | Victor Dodan | Codex Ramblers team | In progress |

### Integration and Review Dependencies

Before Cycle 1 implementation is treated as ready to begin, the team should:

1. Apply the accepted ADRs and confirm affected implementation dependencies.
2. Confirm that architecture, API contracts, and component responsibilities agree.
3. Resolve outstanding security and governance review findings.
4. Revisit implementation estimates and technology-learning risks.
5. Confirm responsibilities for database persistence, status updates, and automated testing.
6. Verify that the A3 evidence package is linked and ready for final team review.

### Planning Impact

The existing Cycle 1 implementation and verification estimates remain unchanged until the team has sufficient evidence to justify revising them.

The approved technology stack introduces specific areas for review, including framework setup, database persistence, concurrency handling, and recovery testing.

Any approved changes affecting scope, schedule, ownership, or effort should be recorded in the appropriate planning documents and GitHub evidence.

### A3 Completion Evidence

A3 planning alignment will be considered complete when the updated planning documents are reviewed, the relevant architecture decisions are resolved, and the team can trace its implementation tasks to the agreed architecture and requirements.

Documentation being merged does not, by itself, demonstrate that implementation or testing has been completed.


## A4 Planning and Process Follow-Up — October 29, 2026

**Prepared by:** Giancarlo Herrera as temporary documentation support for Malec's planning/process work. **Ownership reassignment is pending Victor's confirmation.** Existing A3 tasks and estimates remain unchanged.

| A4 coordination item | Dependency / evidence | Responsible role (proposed) | Status at review |
|---|---|---|---|
| Integrate Spring Boot build configuration | [PR #76](https://github.com/victordodan/-comp330-f26-team-02/pull/76) before application-dependent merges | Shu / Victor | Open; focused CI passed; not merged |
| Integrate model, service, controller and app entry point | [PRs #69, #71–73](https://github.com/victordodan/-comp330-f26-team-02/pulls) | Shu / Mareddy | Open; unresolved integration and review issues |
| Verify app context and negative/security cases | [PR #77](https://github.com/victordodan/-comp330-f26-team-02/pull/77); [test readiness](../testing/test-cases.md) | Mareddy / Shu; Giancarlo reviews | Context CI failing; acceptance/security execution pending |
| Reconcile quality, traceability and risk evidence | [PR #75](https://github.com/victordodan/-comp330-f26-team-02/pull/75), [risk register](risk-register.md) | Giancarlo / Victor | Documentation prepared; review and merge pending |
| Verify final A4 index, baseline tag and Word submission | [evidence index](../reviews/assignment4-review-package.md) | Victor / Shelby | Final check and tag not verified |

**Change control:** Do not mark a task complete solely because its PR exists. Recheck latest merged commits, CI, reviews, and test results. Any reassigned owners, revised estimates, or changed deadlines require team confirmation; none are approved by this addendum.

**Critical dependencies:** Spring Boot configuration → compatible application integration → build and negative/integration tests → independent review and evidence reconciliation → final tag and submission. Parallel documentation can proceed but does not substitute for running code.

### Post-merge task update — October 30, 2026

The earlier open/failing statuses are superseded:

- Build configuration: #76 merged.
- Model, bootstrap, service, and controller: #69/#73/#71/#72 merged.
- Startup verification: #77 merged; pre-merge CI passed.
- Backend verification: 35 tests passed on the combined implementation.
- Quality evidence: #75 merged; final post-merge reconciliation remains pending.
- Remaining acceptance/recovery evidence, ownership confirmation, final main verification, tag, and Word submission remain open.

## A5 — Release Task Reconciliation

**Reviewed checkpoint:** `ac228d07bc950251faee006b7ab98857fde81f43`. Proposed planning record; human review pending.

| Task | Evidence-supported state | Remaining work |
|---|---|---|
| TASK-012 architecture | Accepted ADRs and merged design/implementation clarification exist | Final integrated review remains separate; not automatically closed |
| TASK-013 implementation | HTTP workflow/JDBC/session code merged | Requirements acceptance and unverified UI scope remain distinct |
| TASK-014 tests/CI | Automated suite and all-change CI implemented | Final candidate CI and assurance decisions |
| TASK-015 verification/defects | Case mappings and actual 36/5/36 rehearsal exist | Use PR101 review after corrections, record actual human case dispositions and residual risks |
| TASK-016 release/presentation | Candidate notes/demo/gate merged; #100 indexes drafted locally | Readiness decision, independent review, Word/PPTX and tag/submission |
| TASK-017/018 Cycle2 | Candidate handoff prepared locally in #100 | Team prioritization and A6 work; no automatic new commitments |

Proposed coordination: Victor for release/submission decisions; testing owner
for evidence; quality lead for reconciliation; planning lead for schedule/risk
maintenance. These roles do not establish actual work or approval by an individual.
PR99 is excluded; no role-matrix edits or acknowledgements are invented. Tasks
awaiting evidence/decisions must say so rather than asserting that none are blocked.
Use Definition of Done before marking a task Complete, not just file existence.

AI assistance and limits are recorded in [AI-use log](../ai/ai-use-log.md).

## A6 — Cycle 2 Task Plan Update (2026-11-24)

**Prepared by:** Malec Tarabein (Planning & Process Lead) · **Backup:** Shú Perez.
**Status:** Proposed. Owners follow the [Cycle 2 maturity plan](cycle2-maturity-plan.md).
Victor confirmed the MT-02 runbook assignment on 2026-11-24; the other owners
still need Victor's confirmation and each owner's acknowledgement on the PR.

### Status corrections

The postmortem (PM-03) found task statuses left at "Planned" after the work was
done. The table above now reflects repository evidence:

- **TASK-012 to TASK-016 → Complete.** Cycle 1 was released and tagged
  `a5-cycle1-release` (`3f297d8`); see the [A5 release decision](../release/a5-release-decision.md).
  Residual items from that release (independent review, restore test,
  supply-chain review) are carried into TASK-018 rather than left open on the
  Cycle 1 tasks.
- **TASK-017 → Complete.** The Cycle 1 review and Cycle 2 target selection are
  recorded in the [postmortem](../operations/postmortem-cycle1.md) and
  [maturity plan](cycle2-maturity-plan.md) (PR #115). The selected targets stay
  **proposed** until Victor confirms them; if Victor changes the set, TASK-018's
  subtasks change with it.
- **TASK-018 → In Progress**, split into the subtasks below.

### TASK-018 subtasks

| ID | Subtask | Owner | Backup / Reviewer | Target | Estimate | Expected evidence | Depends on | Status |
|---|---|---|---|---|---|---|---|---|
| TASK-018.1 | Bounded backup and restore (tier B) | Shú Perez | Venkata Aravind Mareddy | MT-01 | C2-01 | Restore steps in `runbook.md`; executed restore transcript at the A6 SHA in `/test-evidence`; or Victor's dated deferral | Target confirmation | Not Started |
| TASK-018.2 | Operational readiness and observability | Shú Perez (runbook), Giancarlo Herrera (runbook exercise and evidence); Shelby Sierah (observability planning) | Venkata Aravind Mareddy | MT-02 | C2-02 | Project-specific `runbook.md` and `observability-plan.md`; runbook exercise (startup, one observed failure signal, same-store restart) with environment and candidate SHA in `runtime-evidence.md`; unused starter files marked out of scope | Target confirmation (runbook assignment confirmed 2026-11-24) | Not Started |
| TASK-018.3 | Identity boundary and negative authorization tests | Shú Perez | Venkata Aravind Mareddy | MT-03 | C2-03 | Filled `permission-boundaries.md`; negative-path tests passing in CI at the A6 SHA | Target confirmation | Not Started |
| TASK-018.4 | Acceptance re-run and independent review | Venkata Aravind Mareddy (acceptance), Giancarlo Herrera (quality gate) | Victor Dodan (decision) | MT-04 | C2-04 | TC-001–TC-012 human disposition per case at the A6 SHA; integrated browser workflow (PRs #126–#128) verified; named non-author review; CI URL and totals at the tag commit; Issues #104/#105 closed | TASK-018.1–018.3 merged (freeze candidate) | Not Started |
| TASK-018.5 | Supply-chain assurance (tier B) | Reviewer appointed by Victor | Victor Dodan | MT-05 | C2-05 | Tomcat/Boot update with CI re-run, or dated advisory/license review, or Victor's dated deferral | Reviewer appointment | Not Started |
| TASK-018.6 | Planning accuracy and continuous evidence | Malec Tarabein | Shú Perez | MT-06 | C2-06 | Cycle 2 `re-estimation.md`; this task plan and `schedule.md` kept current; effort log; CP-A6-1 and CP-A6-2 records; changed-file index per merged PR | PR #115 | In Progress |
| TASK-018.7 | A6 release package and presentation | Victor Dodan | Shelby Sierah | — | C2-07 | `release-notes.md`, `changed-files-since-a5.md`, `final-test-evidence.md`, Word summary, PPTX, rehearsal; maturity plan final disposition table filled | TASK-018.4; CP-A6-2 | Not Started |

Each subtask follows the Definition of Done above. A subtask closes only with
the expected evidence linked, or with a dated deferral recorded in the maturity
plan's final disposition table. A PR existing is not completion.

### A6 effort log

Started at A6 kickoff (2026-11-24) to close PM-04. Each member adds a row in the
same week they do the work. Record real hours only; do not estimate them later
from commit times. Planning totals are compared with the
[Cycle 2 estimates](re-estimation.md#cycle-2-estimates-proposed) at each checkpoint.

| Date | Member | Subtask | Actual hours | What was done (PR / issue / file) |
|---|---|---|---:|---|
| | | | | |

**Running totals** (updated at each checkpoint):

| Subtask | Likely estimate | Logged to date | Checked at |
|---|---:|---:|---|
| TASK-018.1 | 6 h | 0 h | — |
| TASK-018.2 | 9 h | 0 h | — |
| TASK-018.3 | 10 h | 0 h | — |
| TASK-018.4 | 12 h | 0 h | — |
| TASK-018.5 | 5 h | 0 h | — |
| TASK-018.6 | 8 h | 0 h | — |
| TASK-018.7 | 14 h | 0 h | — |

### Task changes in A6

- TASK-018 was split into seven owned subtasks matching MT-01–MT-06 plus the
  A6 release package.
- Notifications, institutional authentication and production hosting were cut
  or deferred; see [re-estimation](re-estimation.md#cut-or-deferred). No task
  was created for them.
- The lightweight browser client (LIM-004) was added by team decision and
  implemented in PRs #126–#128; its integrated workflow is verified at freeze
  under TASK-018.4.
- Open decision: Victor's confirmation of the remaining targets and owners
  (PR #115), needed before CP-A6-1. The MT-02 runbook assignment (Shú writes,
  Giancarlo exercises and records) was confirmed by Victor on 2026-11-24.

AI assistance: Claude (Claude Code) drafted this update for Malec Tarabein. No
hours, work or approvals are claimed. See the [AI-use log](../ai/ai-use-log.md).
