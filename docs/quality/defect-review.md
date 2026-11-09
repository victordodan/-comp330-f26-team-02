# CampusConnect — A5 Defect and Quality Review

**Reviewer:** Giancarlo Herrera, Quality & Review Lead  
**Date:** 2026-11-09  
**Scope:** A4 integrated repository evidence, A5 open PRs and [finding log](defect-log.md).  
**Status:** Draft quality review; no new runtime test, security scan, or final release approval performed.

## Summary

The A5 documentation audit identified **six open findings**, primarily contradictions in release documentation and missing final verification/approval evidence. **Zero new runtime defects were independently reproduced by this audit.** Six findings are not six confirmed software bugs, and there is insufficient confirmed defect data to calculate a meaningful defect rate or root-cause trend.

## Quality observations

| Theme | Findings | Why it matters | Recommendation |
|---|---|---|---|
| Persistence claims | A5-F01 | PR #83 describes in-memory restart loss while configuration and historical A4 testing indicate file-backed H2 | Reconcile exact final runtime and rewrite limitations based on observed restart result |
| Identity and authorization | A5-F02 | Contradictory claims about forged headers vs server-session selection may misstate security risk | Verify negative tests; disclose synthetic identity and no institutional authentication |
| Defect tracking and test cleanup | A5-F03 | Starter defect templates and unverified PR #84 test cleanup prevent reliable defect disposition | Keep verified defects distinct from documentation discrepancies; obtain PR #84 CI |
| Acceptance execution | A5-F04 | Automated coverage does not substitute for dated case-by-case TC-001–TC-012 results | Record actual operator, SHA, environment, expected/actual result and evidence |
| Final build and release | A5-F05 | Historical A4 success cannot certify a future A5 SHA; starter CI guard remains | Replace/reconcile CI; verify frozen SHA; obtain independent review and release decision |
| Dependencies and licensing | A5-F06 | No documented complete CVE and transitive-license disposition | Obtain scan/advisory evidence and named human residual-risk acceptance |

## Evidence boundaries

- [A4 backend verification](../testing/a4-backend-verification.md) reports 35 passing tests in a local sequential integration simulation and packaged-process smoke checks.
- [A4 final quality verification](../testing/a4-final-quality-verification.md) links a successful historical main CI run and maps all twelve acceptance cases to automated coverage.
- Those historical checks do **not** establish final A5 acceptance execution, independent final-commit review, vulnerability clearance, or production readiness.
- `docs/testing/test-cases.md` still records 12/12 cases as Not Run for separately documented case execution.
- [PR #83](https://github.com/victordodan/-comp330-f26-team-02/pull/83) and [PR #84](https://github.com/victordodan/-comp330-f26-team-02/pull/84) require team verification and disposition.

## Proposed corrective actions and accountability

| Action | Proposed owner | Acceptance evidence |
|---|---|---|
| Verify and correct persistence/security limitations | Shu; Mareddy tests | Exact-SHA code/test evidence, corrected PR #83 |
| Verify test cleanup and build | Developer / Mareddy | PR #84 checks and test totals |
| Document all twelve acceptance outcomes | Mareddy | Individual dated execution records |
| Run final CI and resolve starter guard | Development lead / Victor | Real Maven CI on release SHA |
| Complete dependency/advisory/license decisions | Victor-appointed reviewer | Scan, license inventory, accepted residual risk |
| Maintain findings and traceability | Giancarlo | Linked dispositions and review evidence |
| Approve/deny release | Victor with independent reviewer | GO / CONDITIONAL GO / NO-GO, final SHA/tag |

## Release quality recommendation

**NOT YET DETERMINED.** The A4 instructor's “Proceed with Conditions” is not final A5 approval. Revisit this review after technical owners provide final-SHA evidence, defects are dispositioned, the requirements owner accepts scope, and Victor records the release decision.

This review does not claim defect closure or authorize a merge/tag.
