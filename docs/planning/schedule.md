# Schedule

**Team:** Codex Ramblers  
**Project:** CampusConnect  
**Current Phase:** A5 — Cycle 1 Release and Presentation
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

| ID | Milestone | Planned Date / Date Range | Planned Outcome | Dependencies | Related Tasks / Estimates | Owner | Status |
|---|---|---|---|---|---|---|---|
| MS-001 | Project Launch baseline | Sep 10–Sep 20, 2026 | Establish initial team, repository, scope, planning, and engineering evidence. | Team formation and project direction | EST-001 | Codex Ramblers | Complete |
| MS-002 | A2 planning and requirements baseline | Sep 22–Sep 29, 2026 | Finalize/refine Cycle 1 requirements, acceptance criteria, scope, estimates, task plan, schedule, risks, and traceability. | MS-001 | TASK-010, TASK-011, EST-002 | Planning and Requirements owners | In Progress |
| MS-003 | Architecture baseline | Sep 30–Oct 15, 2026 | Establish system structure, interfaces, technology decisions, and major ADRs required for implementation. | MS-002 | TASK-012, EST-003 | Architecture & Development Lead | In Progress |
| MS-004 | Cycle 1 implementation baseline | Oct 16–Oct 29, 2026 | Complete the controlled CampusConnect support-request workflow and integrate its major components. | MS-003 | TASK-013, TASK-014, EST-004 | Development team | Planned |
| MS-005 | Cycle 1 verification and release readiness | Oct 30–Nov 10, 2026 | Verify the required workflow, resolve material defects, document limitations, and prepare release evidence. | MS-004 | TASK-015, TASK-016, EST-005 | Quality & Review Lead / team | Planned |
| MS-006 | Cycle 2 maturity planning | Nov 11–Nov 24, 2026 | Use Cycle 1 evidence to select a limited set of justified maturity improvements. | MS-005 | TASK-017, EST-006 | Codex Ramblers | Planned |

## Near-Term Cycle 1 Schedule

| Sequence | Planned Work | Planned Date Range | Entry Condition | Exit / Completion Evidence | Related Risk |
|---|---|---|---|---|---|
| 1 | Refine Cycle 1 requirements and acceptance criteria | Sep 22–Sep 24, 2026 | A1 baseline exists | Stable requirement and acceptance-criteria IDs are available | R-004 |
| 2 | Reconcile scope, task plan, estimates, schedule, risks, and traceability | Sep 25–Sep 29, 2026 | Refined requirements are available | A2 planning artifacts are mutually consistent and reviewed | R-001, R-003, R-007 |
| 3 | Define architecture, interfaces, and major engineering decisions | Sep 30–Oct 15, 2026 | A2 planning baseline is established | Architecture evidence and applicable ADRs are reviewed | R-002, R-004, R-005 |
| 4 | Implement the required Cycle 1 workflow | Oct 16–Oct 29, 2026 | Architecture and interfaces are sufficiently stable | Required workflow components are implemented and reviewable | R-001, R-002, R-005 |
| 5 | Integrate and test the end-to-end workflow | Oct 30–Nov 5, 2026 | Major implementation components are available | Student-to-reviewer workflow operates as an integrated system | R-005, R-006 |
| 6 | Verify, correct defects, and prepare release evidence | Nov 6–Nov 10, 2026 | Integrated workflow is available | Required behavior is verified and known limitations are documented | R-006, R-007 |

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
## A3 — Schedule and Integration Update

### Architecture Review Progress

During A3, the team prepared architecture documentation covering component responsibilities, API contracts, data context, and the controlled CampusConnect support-request workflow.

Architecture review and reconciliation remain in progress. The team must confirm that the architecture documents agree with the requirements and accepted engineering decisions before declaring the architecture baseline complete.

### Engineering Decision Dependencies

Victor Dodan approved three Cycle 1 Architecture Decision Records on October 16, 2026:

- **ADR-002:** Cycle 1 technology stack.
- **ADR-003:** Request consistency and concurrent updates.
- **ADR-004:** Local persistence and recovery.

These decisions establish the Cycle 1 technical direction. Dependent documents and implementation tasks must reflect them; final package review and implementation verification remain pending.

### Upcoming Integration and Testing Schedule

| Planned Period | Activity | Responsible Team Members | Expected Evidence |
|---|---|---|---|
| A3 architecture review | Reconcile architecture documents, ADRs, and outstanding review findings | Shu Perez, Giancarlo Herrera, Victor Dodan | Reviewed architecture package and decisions |
| Oct 16–Oct 29 | Implement student submission, request persistence, reviewer workflow, and status updates | Shu Perez, Venkata Aravind Mareddy | Source code, pull requests, and implementation reviews |
| Oct 16–Oct 29 | Prepare and run applicable automated tests as functionality becomes available | Giancarlo Herrera, development team | Test cases, test execution results, and CI evidence |
| Oct 30–Nov 5 | Integrate and verify the complete student-to-reviewer workflow | Development team, Giancarlo Herrera | Integration results and documented defects |
| Nov 6–Nov 10 | Complete verification, correct priority defects, and prepare release evidence | Giancarlo Herrera, Shelby Sierah, Victor Dodan | Verification records and release documentation |

These periods reflect the existing planning baseline rather than newly approved deadlines.

### Schedule Dependencies

Implementation readiness depends on applying the accepted architecture decisions and confirming interface compatibility.

Testing depends on having reviewable implementation components and clearly defined acceptance criteria.

If architecture approval or implementation is delayed, the team must reassess affected milestones and estimates rather than treating the original schedule as automatically achievable.

### A3 Schedule Assessment

The original architecture milestone was scheduled to finish on October 15, 2026. Any remaining package-review findings create a potential overlap with the planned implementation period beginning October 16.

The team should confirm whether the implementation schedule remains realistic after architecture reconciliation.

No implementation completion, successful test execution, or final architecture-package approval is claimed by this update.


## A4 Schedule Checkpoint — October 29, 2026

**Prepared by:** Giancarlo Herrera in support of the planning/process function. This is a status checkpoint, **not** a new approved schedule or changed estimate.

- **Current A4 deadline:** October 29, 2026, per team coordination. Victor coordinates the final Word submission.
- **Implementation critical path:** Spring Boot build setup ([PR #76](https://github.com/victordodan/-comp330-f26-team-02/pull/76)) → integration of [PRs #69, #71–73](https://github.com/victordodan/-comp330-f26-team-02/pulls) → application context and workflow/security tests ([PR #77](https://github.com/victordodan/-comp330-f26-team-02/pull/77) and test evidence) → review/merge decision → final baseline tag.
- **Parallel evidence path:** A4 changed-file index, architecture follow-up, risk/limitations review and AI review documentation can proceed while implementation is pending; all must be refreshed after final merges.
- **Current exposure:** Multiple interdependent implementation PRs are open, and several focused CI checks are failing. A successful validator workflow alone does not establish integrated readiness.
- **Contingency:** Victor should decide which work is essential to the required Cycle 1 vertical slice, document any scope deferral in [known limitations](../release/known-limitations.md), and avoid reporting unverified acceptance tests as passed.
- **Required checkpoint:** Confirm exact final merged SHA, CI and test evidence, unresolved review findings, final tag `a4-construction-integration`, and submission links before declaring the A4 phase gate complete.

No revised date, resource commitment, completion percentage, or approval is invented in this checkpoint.

### Post-merge schedule update — October 30, 2026

The build, model, application bootstrap, workflow, controller, startup-test, and quality-evidence PRs are merged. Their pre-merge code checks passed, superseding the earlier failing-CI checkpoint.

Remaining sequence: verify final main CI → refresh submission evidence and unresolved dispositions → approve the final baseline → create the A4 tag and complete the Word/Sakai submission.

## A5 — Cycle 1 Release Schedule Reconciliation

**Prepared by:** Malec Tarabein, Planning & Process Lead (backup: Shú Perez). AI-assisted drafting is disclosed in the [AI-use log](../ai/ai-use-log.md).
**Repository checkpoint reviewed:** `main` at `ac228d0` (after PRs #82–#97 merged). Later open-PR references below are a worklist update, not evidence that those PRs were merged at this checkpoint.
**Status:** Proposed planning record for team review. It reconciles the plan with repository history; it does not approve the release, freeze the A5 baseline, or set new course deadlines. Sakai remains the authoritative source for the A5 due date.

### Planned versus actual milestones

Actual dates come from phase-gate tags and commit history, not from memory or estimates.

| ID | Planned window | Actual repository evidence | Variance and assessment | Status |
|---|---|---|---|---|
| MS-001 | Sep 10–Sep 20 | Tag `a1-project-launch` on `4e76386` (Sep 20) | On plan. | Complete |
| MS-002 | Sep 22–Sep 29 | Tag `a2-planning-estimate` on `5aa88e1` (Sep 29) | On plan; 27 of the 28 first-parent commits in the A2 window landed on Sep 29. | Complete (status above was not updated after A2) |
| MS-003 | Sep 30–Oct 15 | Tag `a3-architecture-review` on `255c335` (Oct 15); ADR-002–ADR-004 accepted Oct 16 | Tag on plan; ADR acceptance one day after the window, overlapping the start of MS-004 as anticipated in the A3 assessment. | Complete |
| MS-004 | Oct 16–Oct 29 | Tag `a4-construction-integration` on `70d3726` (Oct 29); post-merge corrections authored Oct 30 | Tagged on the last day of the window. Implementation PRs #69, #71–#73, #76, #77 merged at the deadline rather than incrementally, so integration consumed the planned buffer. | Complete |
| MS-005 | Oct 30–Nov 10 | No commits on any branch between Oct 31 and Nov 8. A5 work (PRs #80–#99) is concentrated on Nov 9–10. No `a5-cycle1-release` tag exists at this checkpoint. | **Schedule compression materialized.** The planned integration-test window (Oct 30–Nov 5) and verification window (Nov 6–Nov 10) have no repository-visible activity until Nov 9. Work may have occurred off-repository, but it is not evidence until committed. See R-006 and R-017 in the [risk register](risk-register.md). | In progress |
| MS-006 | Nov 11–Nov 24 | Not started | Depends on MS-005 closure. | Planned |

The near-term schedule rows 5 (integrate and test, Oct 30–Nov 5) and 6 (verify and prepare release evidence, Nov 6–Nov 10) above are superseded by the remaining A5 sequence below.

### Remaining A5 critical path

Each step's exit evidence must exist in the repository before the next step is treated as complete.

| Seq | Step | Entry condition | Exit / completion evidence | Proposed coordinator | Current state at `ac228d0` |
|---|---|---|---|---|---|
| 1 | Review and decide the open A5 documentation PRs | PRs open | Every A5 documentation PR merged, revised, or closed with a recorded reason | Victor (merge decisions); non-author reviewer per PR | #82–#97 merged at this checkpoint; #98 (this planning update), #99 (Role Matrix), #100 (release-readiness draft) and #101 (acceptance quality review) are open in the subsequent worklist |
| 2 | Freeze the candidate release revision | Step 1 done; no further code changes planned | Candidate SHA recorded in [release notes](../release/release-notes.md) and [known limitations](../release/known-limitations.md) | Victor | Not frozen (known-limitations states "Not frozen") |
| 3 | Run CI on the frozen SHA | Step 2 | `Java CI with Maven` run linked with SHA, test count and artifact; documentation-only commits after the freeze explained | Development / testing owners | Last cited run #41 is on `0fcc79c`, not the current checkpoint (defect log A5-F05) |
| 4 | Execute and record TC-001–TC-012 at the frozen SHA | Steps 2–3 | Per-case result, date, operator, environment, SHA, evidence path under `/test-evidence/`, or an explicit blocked/deferred disposition | Mareddy; Giancarlo reconciles | 0/12 recorded (defect log A5-F04). `/test-evidence/a5-http-demo/` now holds the #96 HTTP demo rehearsal (synthetic workflow, restart and failure transcripts at `714a31b`); it supports the demo but is not a per-case TC record |
| 5 | Disposition defects, findings and limitations | Steps 3–4 | A5-F01–F06 and LIM-001–LIM-005 each have a recorded human decision; residual-risk acceptance for LIM-001/LIM-002 recorded | Giancarlo (register); Victor (acceptance) | Dispositions pending |
| 6 | Complete the release-readiness review | Steps 3–5 | [`release-readiness-review.md`](../reviews/release-readiness-review.md) replaced with an evidence-backed go / no-go for the frozen SHA | Victor; Giancarlo reviews | PR #100 supplies an evidence-backed draft; human review, frozen-SHA reconciliation and final decision pending |
| 7 | Tag the release | Step 6 is "go" | Tag `a5-cycle1-release` on the reviewed SHA; tag SHA matches release notes and the Word summary | Victor | Not created |
| 8 | Final AI-use update and presentation | Step 7 | AI-use log covers implementation, testing, documentation, release notes and presentation; PowerPoint (not PDF) cites repository evidence | All; Shelby / Victor for presentation | Partially logged |
| 9 | Submission | Step 8 | Word summary indexes the tagged evidence; individual peer reviews submitted privately, not stored in this repository | Victor (team package); each student (peer review) | Not started |

Step 1 must finish before step 2 freezes the candidate SHA. Step 3 verifies that SHA; step 4 follows steps 2–3 as specified in the table. Preparatory acceptance-case mapping may proceed alongside PR review, but final case execution/disposition must use the frozen, verified candidate. Steps 5–7 depend on the recorded verification and case outcomes.

### Schedule controls for the remaining work

- **No backdating.** A result recorded after the freeze carries its real execution date and the SHA it ran against.
- **Re-freeze rule.** If a code change is needed after step 2, steps 2–4 repeat for the new SHA. Documentation-only changes after the freeze must not alter release claims without review.
- **Scope protection.** No new features enter Cycle 1. Anything incomplete is recorded as deferred in the release notes and known limitations, not finished under deadline pressure.
- **Deadline fallback.** If steps 4–6 cannot finish before the Sakai deadline, the release is presented as a candidate with its open items listed, rather than tagged as approved.

### A5 schedule assessment

Every phase gate so far has closed on its final scheduled day, and the A2, A4 and A5 commit histories each show most of the work landing in the last one or two days. The A2 plan's buffer rule ("work should reach reviewable form early enough to allow another team member to inspect the evidence") has not been met in practice. This is the main process finding for Cycle 1 and is carried forward to MS-006 as a Cycle 2 planning input (see the [task plan](task-plan.md), TASK-019).

No completion percentage, effort actual, approval, or new deadline is claimed by this section.
