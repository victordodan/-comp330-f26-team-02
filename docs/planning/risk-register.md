# Risk Register

**Team:** Codex Ramblers  
**Project:** CampusConnect  
**Current Phase:** A3 — Architecture and Review
**Release Cycle:** Cycle 1  

## Risk Register

| ID | Risk | Likelihood | Impact | Mitigation | Contingency / Response | Owner | Status | Related Evidence |
|---|---|---|---|---|---|---|---|---|
| R-001 | If the Cycle 1 scope expands beyond the required support-request workflow, then implementation and verification may become difficult to complete within the semester schedule. | Medium | High | Keep Cycle 1 focused on the workflow defined in `scope.md` and review proposed additions before accepting them. | Defer lower-priority additions to Cycle 2 or remove them from the current scope. | Malec Tarabein | Monitoring | `scope.md`, EST-004, MS-004 |
| R-002 | If the team selects technologies that require more learning or configuration than expected, then architecture and construction may take longer than planned. | Medium | High | Prefer technologies the team can reasonably understand, support, and test within the course timeline. | Reduce technical complexity, revise the architecture, or adjust implementation tasks if necessary. | Shu Perez | Open | EST-003, EST-004, MS-003, MS-004 |
| R-003 | If team members become unavailable or assigned work is completed late, then dependent tasks and phase-gate evidence may also be delayed. | Medium | High | Maintain primary and backup ownership, communicate blockers early, and keep work visible through GitHub and Microsoft Teams. | Reassign or redistribute affected work using the documented backup responsibilities. | Victor Dodan | Monitoring | `task-plan.md`, `schedule.md`, `docs/team/roles.md` |
| R-004 | If requirements remain unclear or change significantly after architecture or construction begins, then the team may need to redo design or implementation work. | Medium | High | Refine requirements and acceptance criteria before major architecture and construction decisions are finalized. | Update affected planning and architecture evidence, revise estimates, and rework only the impacted parts of the system. | Malec Tarabein | Open | `docs/requirements/`, EST-002, EST-003, EST-004 |
| R-005 | If independently developed parts of CampusConnect do not integrate as expected, then the complete Cycle 1 workflow may be delayed. | Medium | High | Define interfaces and responsibilities before implementation and integrate work regularly instead of waiting until the end of construction. | Identify the failing interface, simplify the integration where possible, and prioritize restoring the required end-to-end workflow. | Shu Perez | Open | MS-003, MS-004, EST-004 |
| R-006 | If testing and verification are delayed until late in Cycle 1, then important defects may be discovered too close to the release deadline. | Medium | High | Add tests and verification as implementation progresses and review important workflow behavior before formal verification begins. | Prioritize defects affecting the required workflow and document any remaining limitations honestly in release evidence. | Giancarlo Herrera | Open | EST-004, EST-005, MS-004, MS-005 |
| R-007 | If repository evidence is not kept current while work is completed, then the project may become difficult to review or trace even if the software itself works. | Low | High | Keep requirements, issues, pull requests, decisions, tests, and planning evidence updated as work occurs. | Reconcile missing evidence before the affected phase gate and document any gaps that cannot be reconstructed reliably. | Victor Dodan | Monitoring | `task-plan.md`, `docs/team/working-agreements.md`, GitHub repository |
| R-008 | If team communication becomes inconsistent, then team members may misunderstand requirements, deadlines, or assigned responsibilities. | Medium | Medium | Follow the communication expectations defined in the Team Charter and Working Agreements and provide regular project updates. | Schedule an additional team meeting, clarify responsibilities, and update affected repository evidence. | All Team Members | Monitoring | docs/team/team-charter.md, docs/team/working-agreements.md, GitHub activity |
| R-009 | If merge conflicts occur frequently because multiple contributors modify the same files or related work without coordination, then planning or development progress may be delayed. | Medium | Medium | Pull changes regularly, communicate ongoing work, and submit smaller focused pull requests. | Resolve conflicts collaboratively and coordinate ownership of affected files before additional changes are made. | Mareddy Venkata Aravind | Open | GitHub branches, pull requests, repository history |


## Risk Evaluation

The team uses **Low**, **Medium**, and **High** ratings for likelihood and impact.

- **Low** — currently unlikely or expected to have a limited effect on the project.
- **Medium** — reasonably possible and capable of affecting planned work.
- **High** — likely or capable of seriously affecting scope, schedule, quality, or a required phase gate.

These ratings represent the team's current judgment and may change as more project evidence becomes available.

## Risk Triggers / Indicators

| Risk ID | Trigger / Indicator | Monitoring Evidence |
|---|---|---|
| R-001 | New features are repeatedly added before the required Cycle 1 workflow is complete. | `scope.md`, task plan, GitHub issues |
| R-002 | Architecture or setup work takes significantly longer than EST-003 predicts. | `estimates.md`, architecture evidence, task status |
| R-003 | Assigned work misses an internal target or a team member reports that they cannot complete a responsibility. | Microsoft Teams communication, GitHub activity, `task-plan.md` |
| R-004 | Important requirements remain unresolved when architecture work begins or existing requirements change after implementation starts. | `docs/requirements/`, ADRs, GitHub issues |
| R-005 | Components work independently but fail when combined into the end-to-end workflow. | Pull requests, integration tests, defect evidence |
| R-006 | Major workflow behavior still lacks tests as the project approaches the Verification module. | `tests/`, CI evidence, `docs/testing/` |
| R-007 | Completed work cannot be linked to the expected issue, pull request, review, test, or documentation evidence. | GitHub repository and traceability evidence |
| R-008 | Important project updates, deadlines, or ownership changes are missed or misunderstood by team members. | `docs/team/team-charter.md`, `docs/team/working-agreements.md`, GitHub activity |
| R-009 | Repeated merge conflicts occur or multiple contributors modify the same files without coordination. | Pull requests, branch history, GitHub activity |

## Materialized Risks

No tracked risks have materialized at this stage of the project.

## Closed or Accepted Risks

No tracked risks have been closed or formally accepted without further mitigation. R-010 through R-012 are confirmed entries in the active register under Monitoring; their mitigation and verification work remain required.

## Risk Review

The team will review the risk register before each phase-gate submission and whenever a major change to scope, requirements, architecture, estimates, or schedule occurs.

Risks may be added, updated, closed, or marked as materialized as the project develops. If a risk affects another planning artifact, the related scope, task, estimate, or schedule evidence should also be updated.

## A3 — Architecture and Engineering Risk Update

### Risk Review Overview

During A3, the team developed architecture documentation and proposed engineering decisions for the CampusConnect support-request workflow.

The existing risks R-001 through R-009 remain part of the project risk register. The following additional risks address uncertainties associated with the approved technology stack, data consistency requirements, and architecture review process.

Victor Dodan confirmed R-010 through R-012 and their owners on October 16, 2026. They are active risks under monitoring; no materialized incident or successful mitigation test is claimed.

### Additional A3 Risks

| ID | Risk | Likelihood | Impact | Mitigation | Contingency / Response | Owner | Status | Related Evidence |
|---|---|---|---|---|---|---|---|---|
| R-010 | If the local H2 database fails or stored files become unavailable, support-request records may be lost or inaccessible. | Medium | High | Use file-backed persistence, database transactions, and a documented backup procedure. | Restore a verified backup when available or recreate synthetic demonstration data while documenting any data loss. | Shu Perez | Monitoring | ADR-002, ADR-004, EST-004 |
| R-011 | If two reviewer operations modify the same request without conflict detection, a status change or resolution note may be overwritten. | Medium | High | Use version checking and optimistic locking as accepted in ADR-003. | Reject conflicting updates, reload current request information, and investigate any inconsistent records. | Venkata Aravind Mareddy | Monitoring | ADR-003, API contracts, EST-004 |
| R-012 | If architecture decisions or outstanding reviews remain unresolved, implementation may begin with inconsistent technical assumptions. | Medium | High | Complete ADR review and reconcile architecture documents before treating the design as approved. | Prioritize required architecture decisions, document remaining limitations, and reassess affected implementation milestones. | Victor Dodan | Monitoring | ADR-002, ADR-003, ADR-004, MS-003, MS-004 |

### A3 Risk Triggers and Monitoring

| Risk ID | Trigger / Indicator | Monitoring Evidence |
|---|---|---|
| R-010 | Persistence fails across a restart, a database operation reports an error, or a recovery attempt cannot restore expected data. | Database tests, implementation reviews, recovery procedure evidence |
| R-011 | Concurrent updates overwrite information, version conflicts are not detected, or reviewer actions produce inconsistent request records. | Concurrency tests, defect reports, API and repository implementation |
| R-012 | Approved ADRs are not reflected in dependent documents, architecture documents disagree, or implementation begins without the agreed technical baseline. | ADR status, architecture review findings, GitHub pull requests, schedule checkpoints |

### Risk Ownership and Approval

Victor Dodan confirmed the active-register entries and owners on October 16, 2026: Shu Perez for R-010, Venkata Aravind Mareddy for R-011, and Victor Dodan for R-012. These coordination assignments do not restrict other contributors.

The Quality & Review Lead will help verify that the planned testing strategy addresses the identified risks.

Risk likelihood and impact ratings are preliminary planning judgments. They should be revisited when implementation and testing provide stronger evidence.

### A3 Risk Assessment

The approved Java and Spring Boot stack, file-backed H2 database, and concurrency controls provide a more specific direction for Cycle 1 development, but they also introduce implementation and verification responsibilities.

The team should prioritize applying the approved decisions, early database testing, and incremental integration to reduce the chance of schedule delays and data consistency problems.

No successful implementation, backup restoration, or concurrency testing is claimed in this risk update.


## A4 — Construction, Integration, and Review Risk Update (October 29, 2026)

**Prepared by:** Giancarlo Herrera, Quality & Review Lead, as an A4 evidence update to support the team's planning/process work. **Original risk owners remain as recorded above until Victor confirms any reassignment.** This is an evidence-based review of GitHub at the stated snapshot, not a claim of formal team risk acceptance.

### Current evidence and risk reassessment

- **R-003 — Team availability / late work:** Active schedule exposure. Several interdependent implementation PRs are open close to the A4 submission target. Victor should confirm backup assignments and the final integration sequence. [Open PRs](https://github.com/victordodan/-comp330-f26-team-02/pulls).
- **R-005 — Integration failure:** Elevated attention warranted. [PR #67](https://github.com/victordodan/-comp330-f26-team-02/pull/67) merged a validator and six JUnit tests; [#68](https://github.com/victordodan/-comp330-f26-team-02/pull/68) and [#70](https://github.com/victordodan/-comp330-f26-team-02/pull/70) merged DTOs. Service/controller/application PRs [#71](https://github.com/victordodan/-comp330-f26-team-02/pull/71), [#72](https://github.com/victordodan/-comp330-f26-team-02/pull/72), and [#73](https://github.com/victordodan/-comp330-f26-team-02/pull/73) remain open at this snapshot. Passing a focused validation CI job does not prove end-to-end integration.
- **R-006 — Testing delay:** Active evidence gap. The six validator tests have passing [CI evidence](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/40778855959); the planned TC-001–TC-012 acceptance, ownership/authorization, state-transition, persistence, and recovery checks are not shown as executed by that result.
- **R-007 — Evidence traceability:** Partially mitigated by merged [PRs #61–65](https://github.com/victordodan/-comp330-f26-team-02/pulls?q=is%3Apr+is%3Amerged) and [#74](https://github.com/victordodan/-comp330-f26-team-02/pull/74). The [A4 index](../reviews/assignment4-review-package.md), [changed-file index](../reviews/assignment4-changed-files.md), and PR #75 reconciliation still need review/merge and final refresh.
- **R-010 — Persistence/recovery:** Not verified. The accepted file-backed H2 design requires actual restart/recovery and transaction evidence; the merged validator/DTO slice does not provide it.
- **R-011 — Concurrent updates:** Not verified. The accepted optimistic-locking/version strategy requires implementation and conflicting-update tests before it can be marked mitigated.
- **R-012 — Architecture drift:** Active review concern. [PR #72](https://github.com/victordodan/-comp330-f26-team-02/pull/72) received substantive change requests about authorization and status consistency; corrections, aligned ADRs and tests require verification.

**Correction to the earlier “Materialized Risks” section:** Its statement that no risks had materialized describes the prior A3 review, not an independently verified A4 status. The current A4 evidence shows schedule/integration/test *exposures* and open review findings; it does not establish an incident or verified loss of data. No risk is closed by this addendum.

### Additional A4 risks requiring disposition

| ID | Risk and evidence | Likelihood | Impact | Mitigation / required verification | Response if unresolved | Proposed coordinator | Status |
|---|---|---|---|---|---|---|---|
| R-013 | **Authorization / privacy:** Without server-enforced simulated role and student ownership checks, a student could retrieve another student's requests or invoke reviewer operations. [PR #72 review](https://github.com/victordodan/-comp330-f26-team-02/pull/72#pullrequestreview-5704379886). | Medium | High | Require role/ownership checks in service/controller; execute negative student A/B and reviewer tests; independent re-review. | Block affected behavior from being represented as secure or complete; document limitation. | Shu (implementation); Giancarlo (review) | Open finding; not verified fixed |
| R-014 | **Workflow/state drift:** Controller status options and transitions may diverge from accepted Submitted → In Progress → Resolved / reopen rules. [PR #72](https://github.com/victordodan/-comp330-f26-team-02/pull/72). | Medium | High | Align enums/DTO/controller/service with accepted criteria; test invalid transitions and required resolution notes. | Record discrepancy and defer unverified workflow claims. | Shu (implementation); Giancarlo (review) | Open finding; not verified fixed |
| R-015 | **Dependency / CI evidence:** Spring Boot integration and transitive dependency/security review are not yet complete on the merged snapshot. [PR #76](https://github.com/victordodan/-comp330-f26-team-02/pull/76) proposes Spring Boot 3.2.5; [quality review](https://github.com/victordodan/-comp330-f26-team-02/pull/76#pullrequestreview-5706305897). | Medium | High | Review/merge compatible POM, rerun integrated CI, document exact dependency tree, licenses and advisories; verify startup. | Keep dependency approval and integrated build claims pending; disclose unsupported features. | Shu (build); Giancarlo (dependency review) | Pending verification |
| R-016 | **Phase-gate reproducibility:** If final A4 tag or Word index is created before the last reviewed merge, the submitted baseline may not match the evidence. [A4 package index](../reviews/assignment4-review-package.md). | Medium | High | Recheck merged PRs, A3 comparison, final CI, evidence links, and create/verify `a4-construction-integration` tag on the approved final commit. | Correct the baseline before Sakai submission; explicitly identify gaps if unresolved. | Victor (submitter); Giancarlo (quality support) | Open until final submission verified |

These ratings are provisional reviewer assessments, not approvals by the named coordinators.

### A4 risk actions for the phase gate

1. **Victor:** Confirm who owns the planning/risk-register A4 follow-up and who is backup; reconcile outstanding PR merge decisions and the final tag.
2. **Shu and Mareddy:** Supply runnable integration and negative/security tests for the accepted workflow; close or document authorization, state, persistence and concurrency findings.
3. **Giancarlo:** Re-review PR fixes, update the evidence index/traceability, and record the exact dependency and CI verification outcomes.
4. **Team:** Copy any confirmed deferred behavior into [known limitations](../release/known-limitations.md), without claiming unexecuted tests passed.

**A4 disposition:** The risk register has been reviewed and extended; **risk mitigation, final owner reassignment, integrated test results, and release readiness remain pending**.
