# Task Plan

**Team:** Codex Ramblers  
**Project:** CampusConnect  
**Current Phase:** A5 — Cycle 1 Release and Presentation
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
| TASK-011 | Refine scope, estimates, schedule, risks, and traceability based on requirements | Shu Perez | Victor Dodan | `docs/planning/` | EST-002 | A2 | TASK-010 | Complete |
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

## A5 — Cycle 1 Release Task Update

**Prepared by:** Malec Tarabein, Planning & Process Lead (backup: Shú Perez). AI-assisted drafting is disclosed in the [AI-use log](../ai/ai-use-log.md).
**Repository checkpoint reviewed:** `main` at `d640366`.
**Status:** Proposed for team review. Status changes below follow the Definition of Task Completion above: a task is **Complete** only when its merged evidence exists. Owner changes marked *proposed* need Victor's confirmation.

### Status reconciliation for the Active Task Plan

The Active Task Plan table above was last updated in A2. This reconciles each open row with merged evidence.

| ID | Recorded status | Reconciled status | Evidence | Remaining work |
|---|---|---|---|---|
| TASK-012 | Planned | Complete | Tag `a3-architecture-review`; ADR-002–ADR-004 accepted Oct 16; ADR-005 merged (#81) | None for Cycle 1 |
| TASK-013 | Planned | Complete | Tag `a4-construction-integration`; PRs #69, #71–#73, #76, #77 merged | None for Cycle 1. Browser UI not delivered (see scope change below) |
| TASK-014 | Planned | Complete with open findings | 35 passing tests (CI run #41); Maven CI consolidated in #93; review evidence in `docs/reviews/` | Dependency/advisory acceptance (A5-F06) carried into TASK-015 |
| TASK-015 | Planned | In progress | Acceptance-coverage report (#92) and AC-to-test matrix (#88); defect log and reconciliation (#87, #91); limitations (#83, #94) | Final-SHA CI, 0/12 → 12/12 case records or dispositions, defect dispositions |
| TASK-016 | Planned | In progress | Release notes candidate (#86, open); demo script revision (#96, open); planning traceability updated in `traceability.md` | Readiness review, tag, presentation, Word summary |
| TASK-017 | Planned | Planned | — | Starts after release; inputs listed under TASK-019 |
| TASK-018 | Planned | Planned | — | A6 |

### A5 release tasks

TASK-015 and TASK-016 are broken into the tasks below so each has a single owner, a dependency, and checkable exit evidence. Sequencing follows the [A5 critical path](schedule.md#remaining-a5-critical-path).

| ID | Task | Owner (proposed) | Backup / reviewer | Exit evidence | Depends on | Status |
|---|---|---|---|---|---|---|
| TASK-015a | Review and decide open A5 documentation PRs #82, #85, #86, #90, #96 | Victor Dodan | Non-author reviewer per PR | Each PR merged or closed with reason | — | Open |
| TASK-015b | Freeze the candidate release SHA and record it in release notes and limitations | Victor Dodan | Malec Tarabein | SHA appears in both documents | TASK-015a | Not started |
| TASK-015c | Obtain CI on the frozen SHA and record run, test count and artifact | Shú Perez | Venkata Aravind Mareddy | Run link in `docs/testing/` and release notes | TASK-015b | Not started |
| TASK-015d | Execute TC-001–TC-012 at the frozen SHA and store results | Venkata Aravind Mareddy | Giancarlo Herrera | Per-case records in `docs/testing/a5-acceptance-test-evidence.md`; raw output in `/test-evidence/` | TASK-015b | Not started (0/12) |
| TASK-015e | Disposition A5-F01–F06 and LIM-001–LIM-005, including residual-risk acceptance | Giancarlo Herrera | Victor Dodan (acceptance) | Every row in `docs/quality/defect-log.md` and `docs/release/known-limitations.md` has a human decision | TASK-015c, TASK-015d | Open |
| TASK-016a | Complete the release-readiness review with a go / no-go | Victor Dodan | Giancarlo Herrera | `docs/reviews/release-readiness-review.md` no longer contains starter text | TASK-015e | Not started (template) |
| TASK-016b | Tag `a5-cycle1-release` on the reviewed SHA | Victor Dodan | Malec Tarabein | Tag SHA = frozen SHA | TASK-016a | Not started |
| TASK-016c | Replace remaining starter-kit placeholders in release-facing docs, or mark them as out of Cycle 1 scope | Shelby Sierah | Malec Tarabein | `README.md`, `docs/release/traceability-summary.md`, `docs/team/roles.md` (demo script is covered by #96) and the `docs/operations/`, `docs/observability/`, `docs/security/` files reviewed | — | Not started |
| TASK-016d | Final AI-use log update covering all A5 work, including presentation preparation | Giancarlo Herrera | Malec Tarabein | `docs/ai/ai-use-log.md` entries for every A5 PR | TASK-015a | In progress (#90 open) |
| TASK-016e | Build the PowerPoint presentation from tagged repository evidence | Shelby Sierah | Victor Dodan | `.pptx` file; every release claim cites a repository path or link | TASK-016b | Not started |
| TASK-016f | Word summary indexing the final release evidence and submit the team package | Victor Dodan | Malec Tarabein | Sakai submission | TASK-016b, TASK-016e | Not started |
| TASK-016g | Individual peer reviews (private; not committed to this repository) | Each student | — | Sakai submission | — | Not started |
| TASK-019 | Record Cycle 1 planning lessons for Cycle 2 | Malec Tarabein | Victor Dodan | Section added to `re-estimation.md` or the Cycle 1 postmortem | TASK-016b | Not started |

Owners for TASK-015c, TASK-016c and TASK-016e follow the roles in `docs/team/roles.md` and existing release-document ownership; the team should confirm or reassign them.

### Scope change recorded

ADR-002 selected Thymeleaf for server-rendered pages, but the delivered Cycle 1 interface is an HTTP API ([LIM-004](../release/known-limitations.md)). SCP-001 to SCP-006 are delivered through API endpoints with simulated session identities. A graphical workflow is **deferred**, not delivered. This change is recorded here and in [`scope.md`](scope.md) so it is not changed silently.

### Blocked work

No task is blocked. TASK-016a to TASK-016f are waiting on the freeze-and-verify chain (TASK-015b to TASK-015e) and should not be reported as in progress until that chain moves.

### Inputs for Cycle 2 planning (TASK-019)

- Each phase gate closed on its final scheduled day, with most commits in the last one or two days (see the [schedule reconciliation](schedule.md#planned-versus-actual-milestones)).
- No effort actuals were recorded, so EST-004 and EST-005 cannot be compared with actual hours (see [`estimates.md`](estimates.md#a5--estimate-versus-actual-assessment)).
- The Active Task Plan statuses were not maintained between A2 and A5; this section is the first reconciliation.
