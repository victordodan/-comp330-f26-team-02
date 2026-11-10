# Assignment 5 — CampusConnect Release-Readiness Review

> Current decision (2026-11-10): [Victor-authorized conditional classroom release](../release/a5-release-decision.md).
> TC-001–TC-012 accepted within documented scope; residual risks explicitly
> accepted/deferred. Historical pending decisions below retain their original
> context. Final integrated independent review, exact-main CI and tag execution
> remain prerequisites; no production or security/legal clearance is asserted.

**Project:** CampusConnect — COMP 330 Team 2 (Codex Ramblers)  
**Prepared for review by:** Giancarlo Herrera, Quality & Review Lead  
**Review checkpoint:** `ac228d07bc950251faee006b7ab98857fde81f43`; local closeout additions not yet merged
**Target gate:** A5 Cycle 1 classroom release and presentation  
**Release baseline:** NOT FROZEN — `a5-cycle1-release` tag absent when checked  
**Review status:** DRAFT / proposed quality recommendation; no personal or team sign-off claimed  
**Environment:** Java 17, Spring Boot, file-backed H2, local loopback synthetic-data demo

## Intended Release Outcome

Demonstrate the bounded CampusConnect HTTP support-request workflow: students create and view their own requests; reviewers inspect requests, change status, record notes, and reopen resolved requests. This is a controlled classroom demonstration, **not** a production campus service. See [release notes](../release/release-notes.md) and [known limitations](../release/known-limitations.md).

## Readiness Summary

**Proposed quality assessment: Conditionally ready for preparation of a controlled classroom demo; final A5 release approval remains OPEN.** The recorded automated and packaged HTTP rehearsal results are positive. Case-specific acceptance dispositions, exact-release-revision checks, independent review, security/dependency risk treatment and human release authorization are not yet complete.

## Scope Readiness

| Review item | Evidence | Result | Gap / action |
|---|---|---|---|
| Student submit/status and reviewer lifecycle | [Release notes](../release/release-notes.md); [API](../testing/a4-http-api.md) | Implemented in documented scope | Confirm final revision and presentation wording |
| File-backed persistence, UUIDs, version checks | [Backend verification](../testing/a4-backend-verification.md); [demo rehearsal](../testing/a5-demo-rehearsal.md) | Related tests and rehearsal passed | Confirm exact frozen SHA |
| Deferred scope disclosed | [Known limitations](../release/known-limitations.md) | Documented | Human acceptance of bounded scope pending |

## Requirements and Traceability Readiness

| Review item | Evidence | Result | Gap / action |
|---|---|---|---|
| REQ-001 through REQ-006 linked to implementation and tests | [Traceability summary](../release/traceability-summary.md); [acceptance matrix](../testing/a5-acceptance-evidence-matrix.md) | Mapped | Confirm requirements/criteria approval status |
| TC-001 through TC-012 | [Acceptance evidence](../testing/a5-acceptance-test-evidence.md) | Related automated coverage reported for 12/12 | No separately dated per-case execution records in the published matrix; assign explicit evidence-backed dispositions, not automatic passes |

## Verification Readiness

| Verification area | Recorded evidence | Result | Remaining concern |
|---|---|---|---|
| Maven/JUnit | [PR #95 CI](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42746026082); [acceptance evidence](../testing/a5-acceptance-test-evidence.md) | 35 tests passed on recorded integration revision | Not the final A5 tagged SHA |
| Packaged local HTTP workflow | [A5 rehearsal](../testing/a5-demo-rehearsal.md) | 36 initial checks passed; 36 repeat checks passed | Automated rehearsal is not human acceptance |
| Process restart | [Rehearsal](../testing/a5-demo-rehearsal.md) | 5 restart HTTP checks passed with same H2 store | Does not verify backup restoration |
| Client failure control | [Rehearsal](../testing/a5-demo-rehearsal.md) | Stopped-server run failed as expected | Final demo environment must be verified |
| Acceptance | [A5 matrix](../testing/a5-acceptance-evidence-matrix.md) | Related test methods mapped | Per-case outcomes and human dispositions pending |
| Final integrated revision | [Quality gate](../quality/a5-final-release-quality-gate.md) | OPEN | Run and record final SHA checks and independent review |

## Defect Readiness

[Defect reconciliation](../quality/a5-defect-limitations-reconciliation.md) distinguishes corrected documentation from actual runtime defects. The prior in-memory storage and forged-header identity descriptions were corrected. No new runtime defect is asserted by this document. Final defect-log review, severity/disposition and human risk acceptance remain pending. Do not infer zero unresolved defects from passing tests.

## Known Limitations

| ID | Exposure | Current treatment | Decision needed |
|---|---|---|---|
| LIM-001 | Local H2 file loss; restart persistence is not backup recovery | Same-store local demo; synthetic data | Accept bounded recovery risk or add evidence |
| LIM-002 | Demo identity selector can intentionally select Reviewer; no real login | Loopback-only synthetic classroom demo | Explicitly prohibit real-data/public use |
| LIM-003 | No push/email notifications | Students poll HTTP endpoints | Disclose deferred feature |
| LIM-004 | No verified graphical UI | API/script demonstration | Present actual interface honestly |
| LIM-005 | No verified production deployment/recovery/monitoring | Local demonstration only | Do not claim operational readiness |

Source: [known limitations](../release/known-limitations.md).

## Security and Dependency Readiness

[Security/dependency review](../quality/a5-security-dependency-review.md) documents role/ownership negative tests, local-only configuration and historical dependency inventory. It **does not** establish current vulnerability clearance, complete transitive-license approval, production authentication or backup recovery. A designated technical reviewer must provide dated advisory/license findings or documented risk deferrals; Victor or the authorized decision-maker must record acceptance.

## Operations and Observability

The local packaged demo and same-store restart are supported by [rehearsal evidence](../testing/a5-demo-rehearsal.md). No verified production deployment, rollback, monitoring or backup restoration is established. These are release boundaries, not capabilities to imply in the presentation.

## Risk Review

| Risk | Release exposure | Mitigation / response | Accepted? |
|---|---|---|---|
| Synthetic identity selector | Reviewer identity is selectable in demo | Local-only synthetic demonstration; clear limitation | Pending Victor |
| Unverified backup recovery | Local database loss possible | Preserve same-store local data; avoid real records | Pending Victor |
| Acceptance evidence gap | 12 cases mapped but not individually dispositioned | Mareddy supplies case evidence; quality review reconciles | Pending |
| Final baseline not frozen | Tests may refer to earlier revisions | Select SHA, run final checks, independent review, tag | Pending |
| Dependency assurance incomplete | Advisories/licenses not fully assessed | Technical review and documented decision | Pending |

See [risk register](../planning/risk-register.md).

## Demonstration Readiness

| Review item | Evidence | Result | Gap / action |
|---|---|---|---|
| Happy path and negative path | [Demo script](../release/demo-script.md); [rehearsal](../testing/a5-demo-rehearsal.md) | Recorded PASS | Human presentation rehearsal pending |
| Repeatability and restart | [Rehearsal logs](../../test-evidence/a5-http-demo/) | Recorded PASS | Verify final release SHA/environment |
| Demo claims | [Release notes](../release/release-notes.md) | Scope documented | State that 36 checks are HTTP checks, not 12 independently approved acceptance cases |

## Blocking Items for Final A5 Approval

| ID | Blocking item | Evidence | Coordinator | Required resolution |
|---|---|---|---|---|
| RRB-001 | Final A5 SHA/tag and associated CI not established | [Quality gate](../quality/a5-final-release-quality-gate.md) | Victor + development/testing | Freeze revision, verify, and create tag after review |
| RRB-002 | Acceptance case dispositions outstanding | [Acceptance matrix](../testing/a5-acceptance-evidence-matrix.md) | Mareddy + Giancarlo | Record actual case-specific outcomes or justified deferrals |
| RRB-003 | Independent non-author final review not recorded | [Quality gate](../quality/a5-final-release-quality-gate.md) | Victor | Record reviewer, SHA, findings and disposition |
| RRB-004 | Dependency/security risk signoff outstanding | [Security review](../quality/a5-security-dependency-review.md) | Technical reviewer + Victor | Provide evidence or explicit bounded-demo risk decision |
| RRB-005 | Human release decision not recorded | [Quality gate](../quality/a5-final-release-quality-gate.md) | Victor | Record final outcome, limitations and approvals |

## Non-Blocking Follow-Up (Within Bounded Demo Scope)

| Follow-up | Proposed coordinator | Target | Evidence |
|---|---|---|---|
| Institutional authentication, notifications, GUI | Product/architecture team | Future scope only if approved | [Known limitations](../release/known-limitations.md) |
| Backup/restore, monitoring, deployment | Technical team | Future operational work | [Known limitations](../release/known-limitations.md) |

## Final Readiness Decision

**Decision: NOT YET APPROVED.** The **proposed** quality recommendation is *Conditionally Ready for a controlled classroom demo, subject to the blocking items above*. This is not a human Go decision and does not authorize production deployment.

**Rationale:** Passing 35-test CI and the recorded 36/5/36 HTTP rehearsal support the implemented workflow, but they cannot substitute for exact-release evidence, final acceptance dispositions, independent review, security/risk decisions or a named release approver.

## Required Conditions / Approvals

| Condition | Proposed owner | Evidence required | Status |
|---|---|---|---|
| Confirm exact release SHA and final CI | Victor + technical/test owners | Tag/SHA and passing workflow link | Pending |
| Disposition TC-001–TC-012 | Mareddy; Giancarlo reviews | Dated evidence, result, gaps and reviewer | Pending |
| Complete independent review | Victor-designated non-author | Reviewer, SHA, findings and disposition | Pending |
| Resolve or accept security/dependency risks | Technical reviewer + Victor | Dated scan/advisory/license evidence or explicit deferral | Pending |
| Record final human release decision | Victor / authorized team | Decision, accepted limitations, approval date | Pending |

**Human review / signature:** Pending.  
**AI-use disclosure:** ChatGPT assisted in drafting this document from repository evidence. No new test, vulnerability scan, human verification, acceptance or approval was performed by this edit. The named Quality & Review Lead must review and confirm its contents before representing it as personal signoff.

## Local Closeout Reconciliation

The [release evidence index](../release/assignment5-release-package.md) now links
the missing changed-file/test/presentation/handoff indexes and the detailed
[case assessment](a5-acceptance-evidence-quality-review.md). These are candidate records,
not a submitted Word/PPTX or human acceptance. The [dependency disposition](../quality/a5-dependency-disposition.md)
records a fresh resolved tree/POM license inventory and blocked advisory API;
CVE/support/license decisions remain open. The [technical review](a5-integrated-technical-review.md)
is AI-assisted and does not satisfy named human independent signoff.

| Reviewed evidence identity | Meaning |
|---|---|
| `714a31b` | Actual application source used for recorded local build/HTTP rehearsal |
| `5d791a7` | PR95 synthetic merge tested by recorded CI run #4 |
| Current review checkpoint above | Later documentation/evidence state; src/POM diff against rehearsal source is empty |
| Final A5 tag | Not selected/created; human decision and final candidate verification pending |

No gate is closed solely because a document exists. RRB-002 now has a technical
assessment to review; RRB-003/004/005 still require actual named decisions.
PR99 is explicitly excluded from this local work. Do not invent approvals,
update private peer records, or silently accept requirements on a student's behalf.

## Post-merge evidence update — 2026-11-10 UTC (proposed; no sign-off)

**Quality & Review Lead evidence checkpoint:** `main` at `dbb76f985761202021947a9e1a2aae3bd32261e4`. The original review checkpoint and assessment above remain historical and should not be read as current sign-off.

| Area | Newly verified evidence | Quality interpretation / remaining decision |
|---|---|---|
| Main-branch CI | [Run #48](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42761953805), Java CI with Maven, **success** at exact checkpoint SHA; [verify/test job](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42761953805/job/129649479940) reports successful build/test/package and test-report upload | This closes the *missing successful main CI at this checkpoint* evidence gap. It does not prove a future tagged SHA, acceptance decisions, or a security scan. Exact JUnit totals for this run have not been independently extracted here. |
| Documentation PRs | [#98](https://github.com/victordodan/-comp330-f26-team-02/pull/98), [#99](https://github.com/victordodan/-comp330-f26-team-02/pull/99), [#100](https://github.com/victordodan/-comp330-f26-team-02/pull/100), [#101](https://github.com/victordodan/-comp330-f26-team-02/pull/101) merged | Documentation available on main; merge does not constitute acceptance, independent release review, or approval. |
| Acceptance TC-001–TC-012 | [Matrix](../testing/a5-acceptance-evidence-matrix.md): 12/12 mapped to related automated evidence; 0/12 separately dated case-specific executions | **12/12 final case dispositions remain Pending**. Mareddy and authorized reviewers must reconcile actual evidence, scenario gaps and decisions. No extra manual test requirement is inferred when automation is sufficient. |
| A5 tag and release decision | No remote `a5-cycle1-release` tag found at this checkpoint | Victor must select/review a final baseline and record GO / CONDITIONAL GO / NO-GO before tagging. |
| Residual security and operational risk | Existing review covers demo identity and local file-backed H2 boundaries | Dependency/advisory/license review or explicit human deferral, independent review, and LIM-001–LIM-005 risk acceptance remain Pending. |

**Updated quality recommendation:** **Conditionally ready to prepare the controlled local synthetic-data demo; NOT APPROVED for final A5 release.** The passing main CI reduces verification uncertainty but does not remove RRB-001 through RRB-005 without the required recorded decisions. Preserve the existing pending decision log until the responsible people supply evidence. No new tests, scans, manual acceptance executions, approvals or tags were performed to prepare this update.

**AI-use disclosure:** ChatGPT assisted this documentation-only comparison against GitHub CI and main-branch evidence. Original review attribution to Giancarlo Herrera is preserved without claiming that he personally performed any unrecorded verification or approval.

## Latest-main CI completion — 2026-11-10 UTC

[GitHub Actions run 42762605137](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42762605137) completed with **success** for main commit `000c0f89db51a64956566fc82fc7742973ce1e7b`, which includes merged PR #102. This is newer than the #48 evidence in the preceding section. The latest-main build/test workflow is green, but the workflow outcome alone is not a case-specific acceptance decision, a dependency audit or final release authorization.

**Release-readiness recommendation remains unchanged:** controlled synthetic-data classroom demo preparation is supported; final A5 GO remains **NOT APPROVED**. Preserve pending TC-001–TC-012 dispositions, independent review, security/dependency and LIM-001–LIM-005 risk acceptance, and Victor's GO / CONDITIONAL GO / NO-GO. The `a5-cycle1-release` tag was not present in the remote tag inventory at this checkpoint. If a different commit is ultimately tagged, repeat or explicitly reconcile verification against that commit.

**AI-use disclosure:** ChatGPT assisted the documentation update; no new tests, scans, case executions or human decisions were carried out.
