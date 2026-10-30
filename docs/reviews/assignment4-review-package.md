# Assignment 4 — Construction, Integration and AI Code Review Evidence Package

**Team:** Codex Ramblers — Team 2  
**Project:** CampusConnect Cycle 1  
**Evidence maintainer:** Giancarlo Herrera, Quality & Review Lead  
**Checkpoint:** October 30, 2026 UTC  
**Reviewed main commit:** `4851dff34a2ced725e5ffee51d569e0ba4924cdc`  
**Clone URL:** `https://github.com/victordodan/-comp330-f26-team-02.git`  
**Post-checkpoint update:** PR #78 is merged at `89dd750669ab3ba0557641395dc3ee1d09c96ab6`; the 40-path comparison remains tied to the earlier reviewed commit.

**Status:** Integrated implementation and automated test evidence present; final human gate and submission pending.

## Current evidence index

Primary and backup allocations are proposed, preserving the earlier ownership matrix. Victor and the named teammates must confirm them; this index does not record their acceptance.

| Deliverable | Evidence | Proposed primary | Proposed backup | Status / next proof |
|---|---|---|---|---|
| A4 evidence index and ownership | This document | Giancarlo Herrera | Shelby Sierah | Evidence index refreshed; confirm primary/backup assignments with Victor and named teammates |
| Implementation and integration | Merged [#69](https://github.com/victordodan/-comp330-f26-team-02/pull/69), [#76](https://github.com/victordodan/-comp330-f26-team-02/pull/76), [#73](https://github.com/victordodan/-comp330-f26-team-02/pull/73), [#77](https://github.com/victordodan/-comp330-f26-team-02/pull/77), [#71](https://github.com/victordodan/-comp330-f26-team-02/pull/71), [#72](https://github.com/victordodan/-comp330-f26-team-02/pull/72) | Shu Perez | Venkata Aravind Mareddy | **Merged** |
| Integrated CI | [Main-branch A4 Java Build and Tests run 41000100733](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/41000100733) | Shu Perez | Venkata Aravind Mareddy | **Success** at `d2bf5bfded8f` (last application-code merge); no separate check returned for subsequent docs-only #75 |
| Test and smoke evidence | [Backend verification](../testing/a4-backend-verification.md) | Venkata Aravind Mareddy | Giancarlo Herrera | 35 tests passing in local sequential integration simulation; packaged-process checks reported |
| Acceptance scenario mapping | [Final quality verification](../testing/a4-final-quality-verification.md) and [TC catalog](../testing/test-cases.md) | Venkata Aravind Mareddy | Giancarlo Herrera | **12/12 have related automated coverage; 0/12 have individual case execution records** |
| Quality and security review | [Findings](a4-code-review-findings.md), [final disposition](../testing/a4-final-quality-verification.md), [architecture follow-up](architecture-review.md) | Giancarlo Herrera | Shu Perez | Code mitigations evidenced; human approval and residual risk sign-off pending |
| Dependency assessment | [Build dependency assessment](a4-build-dependency-assessment.md), [tree](a4-build-dependency-tree.txt) | Giancarlo Herrera | Shu Perez | Versions/direct-license evidence present; **no CVE scan, no final human support/advisory acceptance** |
| A3-to-A4 changed-file index | [40-path comparison and unchanged-file rationale](assignment4-changed-files.md) | Shelby Sierah | Giancarlo Herrera | Historical comparison at `4851dff34a2c`; refresh the final comparison after #78 and this PR |
| Requirements traceability | [Traceability](../planning/traceability.md) and [#62](https://github.com/victordodan/-comp330-f26-team-02/pull/62) | Giancarlo Herrera | Shelby Sierah | Existing traceability on main; verify final links/owner sign-off |
| PR template and substantive review trail | [Actual PR template](../../.github/pull_request_template.md), [review findings](a4-code-review-findings.md) | Giancarlo Herrera | Victor Dodan | Review evidence present; **AI-assistance disclosure in the actual PR template remains pending** |
| Definition of Done and merge decisions | [A4 Definition of Done](a4-definition-of-done.md), [PR #63](https://github.com/victordodan/-comp330-f26-team-02/pull/63) | Giancarlo Herrera | Victor Dodan | Document merged; confirm team adoption and final evidence-backed decisions |
| Architecture-review actions | [Architecture follow-up](architecture-review.md), [final dispositions](../testing/a4-final-quality-verification.md) | Shu Perez | Giancarlo Herrera | Implementation evidence present; final action dispositions and human acceptance pending |
| AI use and human verification | [AI log](../ai/ai-use-log.md), [PR fixes disclosure](../ai/a4-pr-fixes.md), [review record](a4-code-review-findings.md) | Giancarlo Herrera | Shelby Sierah | Disclosures present; no implied human approval |
| Planning, schedule, risks | [PR #78](https://github.com/victordodan/-comp330-f26-team-02/pull/78) | Malec Tarabein | Giancarlo Herrera | **Merged** after the recorded checkpoint: #78 at `89dd750669ab`; ownership confirmation and risk acceptance remain pending |
| Known limitations and release readiness | [Final quality verification](../testing/a4-final-quality-verification.md), [known limitations](../release/known-limitations.md) | Shelby Sierah | Victor Dodan | Demo-only, no institutional authentication, no browser UI or backup/restore verification; human gate pending |
| Runtime and recovery evidence (as applicable) | [Backend verification](../testing/a4-backend-verification.md), [runbook](../operations/runbook.md) | Shu Perez | Shelby Sierah | Packaged HTTP/restart checks reported; backup/restore demonstration remains unverified |
| Final Git tag and Sakai package | `a4-construction-integration`, Word document, repository clone URL | Victor Dodan | Shelby Sierah | **Tag not found** at checkpoint; final document/submission unverified |

## Acceptance and code review decision

The main build succeeded after the last application-code merge. Repository tests cover student-owned request creation and reads, reviewer permissions, state/note rules, rollback, restart persistence, concurrency, malformed input and forged context. This **does not** certify twelve individually executed acceptance cases. The case-specific dated execution records remain missing. See the [case-by-case evidence matrix](../testing/a4-final-quality-verification.md).

Earlier CR-001 authorization, CR-002 lifecycle and CR-003 persistence/concurrency implementation concerns are substantially mitigated by merged code and tests **within the synthetic demo scope**. CR-004 dependency/advisory acceptance, CR-005 case-specific records and CR-006 final release gate remain pending. See [review disposition](../testing/a4-final-quality-verification.md). No CVE scan, real identity integration, production readiness or human approval is claimed.

## Remaining final gate checklist

- [x] Implementation PRs #69/#71/#72/#73/#76/#77 merged.
- [x] Main-branch application-code CI passed after #72.
- [x] Automated test-to-acceptance mapping and 40-path changed-file comparison refreshed.
- [ ] Have test owner and reviewer confirm whether the rubric requires separate TC-001–TC-012 execution logs, and run/document any missing cases.
- [ ] Obtain human dependency, security and known-limitations acceptance; record decisions.
- [x] Planning/risk PR #78 merged at `89dd750669ab`.
- [ ] Refresh the final changed-file comparison; recheck main CI if code changes.
- [ ] Confirm primary/backup assignments and add AI-assistance disclosure to the actual PR template.
- [ ] Victor creates/pushes `a4-construction-integration` at the approved main commit.
- [ ] Update Word document with final commit, changed-file index, evidence links, limitations, and clone URL; submit to Sakai.

**Integrity rule:** Passing automated tests are not equivalent to dated manual case execution, branch CI is not main CI, and an AI-assisted evidence review is not human approval. Ownership assignments are proposed unless explicitly confirmed.
