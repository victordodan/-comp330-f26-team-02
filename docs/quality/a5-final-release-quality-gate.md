# Assignment 5 — Final Release Quality Gate and Closure Decisions

**Original preparer:** Giancarlo Herrera, Quality & Review Lead
**Reviewed repository checkpoint:** `d640366e6addcb495a3449cb59253e5cb12599cf`; A4 historical tag remains `a4-construction-integration`.
**A5 final baseline:** **NOT YET FROZEN**. Replace with the exact commit referenced by `a5-cycle1-release` only after all release changes are reviewed and merged.
**Decision status:** **OPEN — not a final approval**. This checklist records completed CI/documentation work and remaining decisions; it does not imply final release approval.

## Why this gate exists

The instructor's A4 assessment was **91/100, Proceed with Conditions**. It specifically requested (1) independent non-author review of the final integrated revision, (2) reconciliation of 12 acceptance cases to actual execution, (3) dependency/supply-chain signoff with reproducible CI/restart/concurrency evidence, and (4) honest documentation of synthetic identity limitations and unresolved controls. A5 requires concise, observable closure decisions, not just a longer document.

## Release closure checklist

| ID | Gate / exact evidence required | Current repository evidence | Status at drafting | Decision owner / verifier | Observable closure |
|---|---|---|---|---|---|
| QG-01 | Independent non-author review of final integrated revision | Merged #87/#91 reconcile findings and traceability; document reviews are not integrated application review | OPEN | Victor designates reviewer; Giancarlo tracks | Named reviewer, exact SHA, findings/dispositions and human decision |
| QG-02 | TC-001–TC-012 evidence-backed acceptance dispositions | Merged [#92 report](../testing/a5-acceptance-test-evidence.md); [merged #88 matrix](../testing/a5-acceptance-evidence-matrix.md) supplies detailed AC mapping | PARTIAL — automation recorded; acceptance pending | Mareddy; Giancarlo; Victor | Per-case result or blocked/deferred decision, date, operator, environment, SHA and evidence; automated evidence may support acceptance without redundant manual runs |
| QG-03 | CI/build verification at final candidate revision | Merged #93 supplies all-change/manual CI; PR #95 run #4 passed clean verify and 35 tests | PARTIAL — working CI, final candidate not selected | Shú/Mareddy; Victor | Exact final tested SHA, run URL, result, totals and disposition of skipped/failing checks |
| QG-04 | Authorization, rollback, concurrency and restart evidence | Service/controller tests executed in PR #95 CI; A4 packaged-process report retained | PARTIAL — scenario/environment limits remain | Shú/Mareddy; Giancarlo | Final-revision reproducible evidence; distinguish file-store service restart from packaged restart and unverified backup restoration |
| QG-05 | Dependency/advisory/license/maintenance disposition | Historical inventory; [merged #89 assessment](a5-security-dependency-review.md) records outstanding advisory/license/support decisions | OPEN — no complete clearance | Victor-appointed technical reviewer | Dated authoritative advisory/scan evidence, complete scoped license/support assessment or explicit human exceptions with mitigations |
| QG-06 | Defect/limitation register with owners and dispositions | Merged [register](defect-log.md), [review](defect-review.md), [limitations](../release/known-limitations.md); #81/#83/#94/#95 correct descriptions | PARTIAL — records exist; risk acceptance pending | Giancarlo; Victor | Human finding dispositions, residual-risk decisions and follow-up owners; no invented runtime defects or zero-defects certification |
| QG-07 | Requirements → issues/PRs/commits → tests → demo → release/tag | Merged [traceability](../planning/traceability.md) restores actual links and identifies missing feature issues | PARTIAL | Giancarlo; Victor | Accepted scope, real issue links or explicit gaps, executed demo steps, release-note links and approved baseline |
| QG-08 | Complete AI disclosure and verified attribution | [Log](../ai/ai-use-log.md) includes merged correction disclosures; #90 remains proposed contribution/disclosure work | PARTIAL | Contributors; Giancarlo; Victor | Verify actual contribution wording and human checks; disclose remaining presentation/release work without inventing authorship or approvals |
| QG-09 | Controlled evidence-backed demo and submission package | HTTP guide exists; main demo script remains starter content at checkpoint | OPEN | Shú; Victor; Giancarlo | Reviewed/rehearsed demo, presentation index/PPTX, Word evidence index with team information and changed-file summary, repository clone URL and Cycle 2 handoff; private peer reviews submitted separately |
| QG-10 | Release decision and immutable A5 baseline | No a5-cycle1-release tag in reviewed remote inventory; A4 tag retained | OPEN | Victor with independent reviewer | Record GO/CONDITIONAL GO/NO-GO, reasons and risks; create/push tag at approved commit and disclose post-freeze changes |

## Verified integration CI evidence

[PR #95 run #4](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42746026082)
passed `mvn -B clean verify` with 35 tests and zero failures/errors/skips, including
successful packaging. [Job logs](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42746026082/job/129598203601)
identify tested merge `5d791a7b657209a6c688251149db92828b313606`, head
`e724e84af257a69237d51f8b6dab3d1927bd8cfb` and base
`6c6584162deb4c396f162670b39e4d48fdd548da`. #95 subsequently merged as
`4ff2f0f12e636f177ec25202b8029498e613afde`; the tested synthetic merge and
final main commit are distinct. This run supports the integration reviewed,
but is not final A5-tag verification or acceptance approval.

## Known boundary conditions to carry forward

- **Synthetic demo identities are not institutional authentication.** Demonstration should use synthetic records on loopback only; do not claim real-user security or Internet deployment readiness.
- **Related automated coverage needs a case-specific acceptance disposition.** Reuse actual automated evidence where sufficient, document gaps, and mark blocked/deferred cases explicitly.
- **Build success is not a vulnerability scan or legal license clearance.** Dependency risks need a named human decision.
- **Prior A4 CI and local simulations do not certify an A5 final commit.** Capture evidence against the frozen SHA.
- **No browser UI was verified in the A4 evidence.** Demo the supported HTTP workflow unless later UI evidence exists.

## Decision log (to complete with observed evidence)

| Gate | Final commit reviewed | Evidence links | Reviewer (non-author where required) | Decision/date | Remaining risk and owner |
|---|---|---|---|---|---|
| Independent technical review | Pending | Pending | Pending | Pending | Pending |
| Acceptance cases TC-001–012 | Pending | Pending | Pending | Pending | Pending |
| CI, restart and concurrency | Pending | Pending | Pending | Pending | Pending |
| Dependency/supply chain | Pending | Pending | Pending | Pending | Pending |
| Defects/risks/limitations | Pending | Pending | Pending | Pending | Pending |
| Overall release | Pending | Pending | Victor Dodan | Pending | Pending |

**Release recommendation at the reviewed checkpoint: NOT YET DETERMINED.** Do not interpret the A4 `Proceed with Conditions` assessment as an A5 GO. Final sign-off requires new A5 evidence and a stable tag.

## Suggested coordination

1. **Victor:** designate independent reviewer for the exact A5 final commit and accept/reject residual risks; ensure the final `a5-cycle1-release` tag is created only after release approval.
2. **Mareddy:** supply case-by-case TC-001–012 execution records and A5 CI/negative-path evidence.
3. **Shu:** demonstrate the integrated request workflow and supply reproducible restart/concurrency evidence; help close dependency findings.
4. **Giancarlo:** reconcile evidence links, maintain this gate, challenge unsupported claims, track defect/limitation dispositions and provide a final recommendation **without approving his own authored changes**.

## Source links

- [A4 release baseline](https://github.com/victordodan/-comp330-f26-team-02/releases/tag/a4-construction-integration)
- [A4 acceptance mapping](../testing/a4-final-quality-verification.md)
- [A4 build verification](../testing/a4-backend-verification.md)
- [A4 dependency assessment](../reviews/a4-build-dependency-assessment.md)
- [Existing planning traceability](../planning/traceability.md)
- [Existing AI-use log](../ai/ai-use-log.md)

## Evidence maintenance and disclosure

#88 and #89 are now merged on main. Their evidence/mappings are available,
but acceptance and dependency clearance remain open. Merging this gate
records the checklist; it does not close its remaining items. Complete the Word
index, changed-file evidence, final test/CI links, presentation materials and
Cycle 2 handoff at the actual selected baseline. Avoid a self-referential SHA
claim: record exactly what revision was reviewed/tested, then identify the
submitted/tagged revision and any subsequent evidence-only changes.

Codex assisted the correction against current source, merged evidence and CI
logs. Original Giancarlo attribution does not assert his verification of these
corrections. No new test/scan or human release decision was performed. See
[AI-use log](../ai/ai-use-log.md).

## Quality Lead addendum — post-merge main CI (2026-11-10 UTC)

**Prepared for review by:** Giancarlo Herrera, Quality & Review Lead. **Evidence checkpoint:** main `dbb76f985761202021947a9e1a2aae3bd32261e4` after merged PRs #98–#101. This addendum supplements, rather than retroactively changes, the historical checkpoint and statuses above.

- **Current main verification:** [Java CI with Maven run #48](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42761953805) completed **successfully** on the exact main SHA above. The [verify / test job](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42761953805/job/129649479940) reports successful Java 17 setup, build/test/package step and test-report upload. The workflow summary does not independently establish the exact per-test count; retain 35/35 only as the explicitly documented historical #95 integration result unless final JUnit reports are inspected.
- **Review documentation merged:** PRs [#98](https://github.com/victordodan/-comp330-f26-team-02/pull/98), [#99](https://github.com/victordodan/-comp330-f26-team-02/pull/99), [#100](https://github.com/victordodan/-comp330-f26-team-02/pull/100), and [#101](https://github.com/victordodan/-comp330-f26-team-02/pull/101) are merged. Merging review documents does not constitute acceptance or release authorization.
- **TC-001–TC-012:** [A5 matrix](../testing/a5-acceptance-evidence-matrix.md) maps 12/12 cases to related automated methods; all 12 final dispositions remain **Pending**. It records 0/12 separate dated case-specific executions. Existing automated evidence may be reused when sufficient, but must be reconciled with the exact candidate SHA, uncovered variants and an attributable human decision; do not invent manual tests.
- **A5 release tag:** no `a5-cycle1-release` reference was found in the remote tag inventory at this check. The passing main CI is **candidate evidence**, not proof of a frozen or approved tagged release.
- **Unclosed gates:** independent non-author review, exact-SHA acceptance dispositions, dependency/advisory and transitive-license review or approved deferrals, defect and LIM-001–LIM-005 risk decisions, and Victor's recorded GO / CONDITIONAL GO / NO-GO remain **Pending**. Shelby's acknowledgement and the Team Charter backup discrepancy remain human follow-ups in the role matrix.

**Quality Lead recommendation:** Continue controlled synthetic classroom demo preparation, but keep the final A5 release gate **OPEN / NOT APPROVED** until evidence and authorized decisions are recorded. No new tests, scans, approvals, or release tags were performed for this documentation update.

**AI-use disclosure:** ChatGPT assisted the evidence reconciliation and wording of this addendum; the GitHub commit records the connected account. Giancarlo's role attribution does not imply personally executed tests or approvals.

## Subsequent main CI completion — 2026-11-10 UTC

The [main push run #49 (42762605137)](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42762605137) finished **successfully** at commit `000c0f89db51a64956566fc82fc7742973ce1e7b`, after merge of quality-review PR #102. This supersedes the earlier #48 checkpoint for latest-main CI evidence. The workflow success is confirmed; no separate per-case execution, security scan, human approval or tagged release is inferred. The exact test count was not independently extracted from this run.

**Gate disposition:** main CI evidence at this checkpoint = **PASS**. A5 final release = **OPEN / NOT APPROVED**. TC-001–TC-012 dispositions, independent non-author review, dependency/license and limitations risk decisions, and Victor's explicit release decision remain **PENDING**. No `a5-cycle1-release` tag was present in the remote tag inventory at this check. Any later main change requires a fresh final-SHA verification.

**Quality & Review Lead action:** submit this evidence update for independent review; request Mareddy's case-level evidence reconciliation, technical/security review, and Victor's documented release decision. ChatGPT assisted this evidence-only documentation; no tests or approvals were performed by this change.
