# Assignment 5 — Final Release Quality Gate and Closure Decisions

**Prepared by:** Giancarlo Herrera, Quality & Review Lead  
**Date:** 2026-11-09  
**Current review baseline:** A4 release `a4-construction-integration` at `70d3726b64c0deafef2d770870bb14ef8f28b79b`  
**A5 final baseline:** **NOT YET FROZEN**. Replace with the exact commit referenced by `a5-cycle1-release` only after all release changes are reviewed and merged.  
**Decision status:** **OPEN — not a final approval**. This is an evidence-based checklist, not a claim that A5 checks have run.

## Why this gate exists

The instructor's A4 assessment was **91/100, Proceed with Conditions**. It specifically requested (1) independent non-author review of the final integrated revision, (2) reconciliation of 12 acceptance cases to actual execution, (3) dependency/supply-chain signoff with reproducible CI/restart/concurrency evidence, and (4) honest documentation of synthetic identity limitations and unresolved controls. A5 requires concise, observable closure decisions, not just a longer document.

## Release closure checklist

| ID | Gate / exact evidence required | Current repository evidence | Status at drafting | Decision owner / verifier | Observable closure |
|---|---|---|---|---|---|
| QG-01 | Independent non-author technical review of **exact A5 final commit**, including integrated auth, lifecycle, persistence, errors, and dependency impact | [A4 code review findings](../reviews/a4-code-review-findings.md), [A4 final quality review](../testing/a4-final-quality-verification.md); these do **not** establish A5 final-commit review | **OPEN** | Victor to designate a non-author reviewer; Giancarlo tracks disposition | Named reviewer, reviewed SHA, substantive findings, severity, dispositions, and review decision recorded in PR or `docs/quality/` |
| QG-02 | Final TC-001 through TC-012 disposition with execution date, environment, exact revision, operator, actual result and evidence | [Case catalog](../testing/test-cases.md), [A4 mapping](../testing/a4-final-quality-verification.md): 12 mapped to automation; **0/12 separately dated case records in the A4 repository** | **OPEN** | Venkata Aravind Mareddy test owner; Giancarlo verifies mapping | All 12 individually marked PASS/FAIL/BLOCKED/DEFERRED with evidence links; unresolved cases accepted explicitly, not silently counted as passes |
| QG-03 | Reproducible A5 CI/build evidence on frozen release SHA; record any failing/skipped checks | [A4 integrated CI](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/41000100733) succeeded on `d2bf5bfded8fcc03f3509394fa215b7f223efd94`, **not** the later A4 release SHA; [backend verification](../testing/a4-backend-verification.md) reports 35 local integration tests | **OPEN** | Shu Perez implementation; Mareddy testing; Victor final release gate | GitHub Actions link showing final A5 commit SHA, workflow conclusion, test totals, failures/skips, and disposition |
| QG-04 | Restart, rollback, concurrency, authorization and negative-path evidence for A5 revision | [A4 backend verification](../testing/a4-backend-verification.md) and source tests; not re-run on A5 final revision in this document | **OPEN** | Shu and Mareddy; Giancarlo challenges evidence | Dated reproducible commands/logs and linked tests at final SHA; explain any environment-specific limitations |
| QG-05 | Dependency/advisory, transitive-license and maintenance assessment with explicit human acceptance | [A4 dependency assessment](../reviews/a4-build-dependency-assessment.md) and [dependency tree](../reviews/a4-build-dependency-tree.txt); no recorded CVE scan or complete transitive-license acceptance | **OPEN** | Victor assigns non-author dependency reviewer; Giancarlo tracks decision | Dated scanner/advisory evidence, scope/version, severity dispositions, license/maintenance decision, reviewer and accepted residual risk |
| QG-06 | Defect and known-limitation register: severity, user impact, mitigation, owner, disposition and Cycle 2 action | [A4 findings](../reviews/a4-code-review-findings.md), [risk register](../planning/risk-register.md); final A5 defect disposition not yet indexed | **OPEN** | Giancarlo quality; Victor risk acceptance; relevant implementer | `docs/quality/defect-review.md` and `docs/release/known-limitations.md` link actual issues/PRs and accepted dispositions |
| QG-07 | Requirements → issue → PR/commit → CI/test → demo → release note → final tag traceability | [Traceability](../planning/traceability.md) from A4; final A5 release/demo links not yet established | **OPEN** | Giancarlo review; Victor release ownership | Every accepted requirement has a traceable final A5 evidence row, or an explicit deferred/limited disposition |
| QG-08 | AI-use final disclosure, human verification and ownership accuracy | [AI-use log](../ai/ai-use-log.md), [A4 AI fixes](../ai/a4-pr-fixes.md) | **OPEN** | Each contributor discloses; Giancarlo reconciles; Victor accepts | Log identifies A5 AI-assisted tests/docs/release/presentation work, validation performed, reviewer and decisions; no unverified authorship claims |
| QG-09 | Controlled demo: happy path, negative/boundary path and one proof link, matching frozen SHA | [HTTP demo guide](../testing/a4-http-api.md); A5 demo script and final presentation evidence not yet linked | **OPEN** | Shu demo driver; Victor presentation owner; Giancarlo verifies claims | Dated rehearsal result, demo script, evidence links, no unsupported UI or production-security claims |
| QG-10 | Final release approval and stable phase-gate tag | [A4 release](https://github.com/victordodan/-comp330-f26-team-02/releases/tag/a4-construction-integration) is **not** the A5 release | **OPEN** | Victor final decision; non-author technical reviewer; Giancarlo quality recommendation | Explicit **GO / CONDITIONAL GO / NO-GO** with reasons and accepted risks; `a5-cycle1-release` points to exact approved commit; any post-freeze change disclosed |

## Known boundary conditions to carry forward

- **Synthetic demo identities are not institutional authentication.** Demonstration should use synthetic records on loopback only; do not claim real-user security or Internet deployment readiness.
- **Automated test mapping is not equivalent to twelve separately executed acceptance cases.** Record individual results or transparently mark cases deferred/blocked.
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

**Release recommendation as of 2026-11-09: NOT YET DETERMINED.** Do not interpret the A4 `Proceed with Conditions` assessment as an A5 GO. Final sign-off requires new A5 evidence and a stable tag.

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
