# Assignment 4 — Construction, Integration and AI Code Review Evidence Package

**Team:** Codex Ramblers — Team 2  
**Project:** CampusConnect Cycle 1  
**Evidence maintainer:** Giancarlo Herrera, Quality & Review Lead  
**Checkpoint:** October 30, 2026 UTC  
**Reviewed main commit:** `4851dff34a2ced725e5ffee51d569e0ba4924cdc`  
**Clone URL:** `https://github.com/victordodan/-comp330-f26-team-02.git`  
**Status:** Integrated implementation and automated test evidence present; final human gate and submission pending.

## Current evidence index

| Deliverable | Evidence | Status / owner |
|---|---|---|
| Implementation and integration | Merged [#69](https://github.com/victordodan/-comp330-f26-team-02/pull/69), [#76](https://github.com/victordodan/-comp330-f26-team-02/pull/76), [#73](https://github.com/victordodan/-comp330-f26-team-02/pull/73), [#77](https://github.com/victordodan/-comp330-f26-team-02/pull/77), [#71](https://github.com/victordodan/-comp330-f26-team-02/pull/71), [#72](https://github.com/victordodan/-comp330-f26-team-02/pull/72) | **Merged**; Shu/Victor |
| Integrated CI | [Main-branch A4 Java Build and Tests run 41000100733](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/41000100733) | **Success** at `d2bf5bfded8f` (last application-code merge); no separate check returned for subsequent docs-only #75 |
| Test and smoke evidence | [Backend verification](../testing/a4-backend-verification.md) | 35 tests passing in local sequential integration simulation; packaged-process checks reported; Shu |
| Acceptance scenario mapping | [Final quality verification](../testing/a4-final-quality-verification.md) and [TC catalog](../testing/test-cases.md) | **12/12 have related automated coverage; 0/12 have individual case execution records**; Mareddy/Giancarlo |
| Quality/security/architecture review | [Findings](a4-code-review-findings.md), [final disposition](../testing/a4-final-quality-verification.md), [architecture follow-up](architecture-review.md) | Code mitigations evidenced; human approval and residual risk sign-off pending; Giancarlo/Victor |
| Dependency assessment | [Build dependency assessment](a4-build-dependency-assessment.md), [tree](a4-build-dependency-tree.txt) | Versions/direct-license evidence present; **no CVE scan, no final human support/advisory acceptance**; Giancarlo/Shu |
| A3-to-A4 changed-file index | [40-path comparison and unchanged-file rationale](assignment4-changed-files.md) | Refreshed at `4851dff34a2c`; refresh again if #78 merges; Giancarlo/Shelby |
| Requirements traceability | [Traceability](../planning/traceability.md) and [#62](https://github.com/victordodan/-comp330-f26-team-02/pull/62) | Existing traceability on main; verify final links/owner sign-off; Giancarlo/Shelby |
| AI use and human verification | [AI log](../ai/ai-use-log.md), [PR fixes disclosure](../ai/a4-pr-fixes.md), [review record](a4-code-review-findings.md) | Disclosures present; no implied human approval; Giancarlo/team |
| Planning, schedule, risks | [PR #78](https://github.com/victordodan/-comp330-f26-team-02/pull/78) | **Open**, mergeable and Victor review requested at checkpoint; Malec coverage / Victor decision |
| Known limitations and release readiness | [Final quality verification](../testing/a4-final-quality-verification.md), [known limitations](../release/known-limitations.md) | Demo-only, no institutional authentication, no browser UI or backup/restore verification; human gate pending |
| Final Git tag and Sakai package | `a4-construction-integration`, Word document, repository clone URL | **Tag not found** at checkpoint; final document/submission unverified; Victor |

## Acceptance and code review decision

The main build succeeded after the last application-code merge. Repository tests cover student-owned request creation and reads, reviewer permissions, state/note rules, rollback, restart persistence, concurrency, malformed input and forged context. This **does not** certify twelve individually executed acceptance cases. The case-specific dated execution records remain missing. See the [case-by-case evidence matrix](../testing/a4-final-quality-verification.md).

Earlier CR-001 authorization, CR-002 lifecycle and CR-003 persistence/concurrency implementation concerns are substantially mitigated by merged code and tests **within the synthetic demo scope**. CR-004 dependency/advisory acceptance, CR-005 case-specific records and CR-006 final release gate remain pending. See [review disposition](../testing/a4-final-quality-verification.md). No CVE scan, real identity integration, production readiness or human approval is claimed.

## Remaining final gate checklist

- [x] Implementation PRs #69/#71/#72/#73/#76/#77 merged.
- [x] Main-branch application-code CI passed after #72.
- [x] Automated test-to-acceptance mapping and 40-path changed-file comparison refreshed.
- [ ] Have test owner and reviewer confirm whether the rubric requires separate TC-001–TC-012 execution logs, and run/document any missing cases.
- [ ] Obtain human dependency, security and known-limitations acceptance; record decisions.
- [ ] Victor reviews/merges #78 if accepted; recheck comparison and final main CI if code changes.
- [ ] Victor creates/pushes `a4-construction-integration` at the approved main commit.
- [ ] Update Word document with final commit, changed-file index, evidence links, limitations, and clone URL; submit to Sakai.

**Integrity rule:** Passing automated tests are not equivalent to dated manual case execution, branch CI is not main CI, and an AI-assisted evidence review is not human approval. Ownership assignments are proposed unless explicitly confirmed.
