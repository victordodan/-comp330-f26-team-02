# Assignment 6 — Final Quality Gate and Release Evidence Checklist

**Project:** CampusConnect — Codex Ramblers / COMP 330  
**Prepared for review:** 2026-11-23  
**Quality coordination:** Giancarlo Herrera  
**Tracking:** [Issue #111](https://github.com/victordodan/-comp330-f26-team-02/issues/111) · [A6 defect review](defect-review.md)  
**Current A6 decision:** **OPEN — no final release recommendation or authorization recorded in this document.**

## Purpose and evidence boundary

Assignment 6 evaluates Cycle 2 maturity: what changed after Cycle 1, whether it was verified, and whether another engineering team could operate and review the release. This is a **pre-release checklist**, not a completed sign-off. It introduces no new test, scan, monitoring or recovery execution. The [A5 conditional classroom release decision](../release/a5-release-decision.md) applies to A5's local synthetic demonstration only; it does not automatically authorize A6 or production use.

## Final quality gates

| Gate | Evidence needed at final A6 baseline | Initial status (2026-11-23) | Coordinator / accountable decision |
|---|---|---|---|
| A6-QG-01 Cycle 1 learning | Dated postmortem findings connected to actual Cycle 2 issues, PRs, re-estimation and dispositions | **OPEN** — Cycle 2 change mapping not established in this review | Malec / Victor |
| A6-QG-02 Defects and limitations | [A6 defect review](defect-review.md), reproducible defects with owners, fix PRs and retests; LIM-001–LIM-005 dispositions | **PARTIAL** — initial inventory only; no new A6 regression results | Giancarlo coordinates; Victor accepts residual risk |
| A6-QG-03 Acceptance and traceability | REQ/AC → issue/PR/commit → TC-001–TC-012 → executed evidence → human disposition at exact A6 SHA | **OPEN** — A5 history exists, A6 evidence pending | Mareddy / Giancarlo; Victor accepts |
| A6-QG-04 CI/CD and validation | Exact final SHA, CI run URL and outcome, test totals, negative paths, restart/concurrency evidence, skipped-check explanation | **OPEN** — no new A6 run asserted | Shu / Mareddy |
| A6-QG-05 Runbook and recovery | Project-specific startup, health checks, troubleshooting, data path, safe restart, backup/restore status, incident owner | **OPEN** — [runbook](../operations/runbook.md) still needs project-specific cleanup and verification | Shú writes the runbook. Giancarlo follows it and records the results. |
| A6-QG-06 Observability | Actual logs/health signals, how to detect failures, evidence of exercising a failure signal, operator response and gaps | **OPEN** — [observability plan](../observability/observability-plan.md) needs evidence and cleanup | Giancarlo records the runbook exercise and observed failure signal. |
| A6-QG-07 Security and governance | Updated [security checklist](../security/security-governance-checklist.md), demo-identity boundary, advisory/license review and documented exceptions | **OPEN** — A3/A5 records do not certify A6 security | Shelby / Victor |
| A6-QG-08 AI accountability | AI-use log for A6 changes: tool, purpose, outputs, human verification, rejected/incorrect outputs, decision authority | **OPEN** — add A6 work to [AI-use log](../ai/ai-use-log.md) | Each contributor / Victor |
| A6-QG-09 Independent review | Named non-author reviewer checks final integrated SHA, relevant evidence and remaining findings | **OPEN** — PR review alone must be scoped accurately | Victor assigns; Giancarlo tracks |
| A6-QG-10 Release package | Final release notes, changed-file index since A5, Word evidence index, repository URL, presentation and private peer reviews | **OPEN** — package not verified | Victor; individual contributors |
| A6-QG-11 Authorization and tag | Victor records GO / CONDITIONAL GO / NO-GO, scope, residual risks and exact SHA; tag `a6-final-release` points to approved commit | **OPEN** — no tag or approval claimed | Victor |

## Cycle 1 baseline: established facts vs A6 work

- A5 recorded a **CONDITIONAL GO** for a local synthetic classroom HTTP demonstration under Victor's delegated authority. See [release decision](../release/a5-release-decision.md).
- A5 recorded 35 passing Maven tests at its stated integration checkpoint; earlier packaged HTTP/restart evidence is bounded to its stated source and environment. **Do not present these as newly executed A6 results.**
- A5 accepted TC-001–TC-012 **within the bounded classroom scope**, while independent final integrated review and broader security/operations assurance were explicitly deferred.
- The local file-backed H2 database, synthetic session identity, missing outbound notifications, HTTP-only interface and unverified production operations were A5 limitations. For the A6 browser interface, see the [candidate test results](../testing/final-test-evidence.md#november-24-browser-candidate).
- [A5 quality gate](a5-final-release-quality-gate.md) and [traceability ledger](../planning/traceability.md) retain historical checkpoints and must not be silently reinterpreted as A6 sign-off.

## Evidence ledger — fill only after actual work

| Evidence item | Exact SHA / date | URL or repository path | Operator / reviewer | Result / limitation |
|---|---|---|---|---|
| A6 final clean build and JUnit tests | Pending | Pending | Pending | Pending |
| A6 HTTP workflow and negative cases | Pending | Pending | Pending | Pending |
| A6 restart and data recovery exercise | Pending | Pending | Pending | Pending; distinguish restart from backup restore |
| A6 observability failure exercise | Pending | Pending | Pending | Pending |
| A6 dependency/security assessment | Pending | Pending | Pending | Pending |
| A6 TC-001–TC-012 dispositions | Pending | Pending | Pending | Pending |
| Independent final integrated review | Pending | Pending | Pending | Pending |
| Victor's final release decision | Pending | Pending | Victor | Pending |
| `a6-final-release` tag and target | Pending | Pending | Victor | Pending |

## Release decision template (Victor or authorized decision maker completes)

**Candidate SHA:** Pending  
**Independent reviewer / reviewed SHA:** Pending  
**Final test and operational evidence:** Pending  
**Open high-severity findings:** Pending review; not assumed zero  
**Accepted/deferred risks and owners:** Pending  
**Decision:** NOT RECORDED (GO / CONDITIONAL GO / NO-GO)  
**Authorized by / timestamp:** Pending  
**Release tag / target SHA:** Pending

No person should fill an approval on another person's behalf without explicit attributable delegation. A document-only PR does not constitute final release readiness.

## Next review actions

1. Mareddy and Shú provide reproducible A6 test and CI evidence. Shú writes the runbook. Giancarlo executes it and records the restart and failure observations against the candidate SHA.
2. Shelby and Victor record security, dependency and governance dispositions.
3. Malec connects postmortem findings to Cycle 2 issues, re-estimation and actual changes.
4. Giancarlo updates [Issue #111](https://github.com/victordodan/-comp330-f26-team-02/issues/111) and this gate with verified links, challenges unsupported claims, and requests **independent** review.
5. Victor authorizes (or declines) the exact final release and creates the tag only after resolving the gate.

**AI-use disclosure:** AI assisted in drafting this checklist from existing repository records. It did not run software, verify final A6 evidence, independently review this PR, or authorize release. Human review and an A6 AI-use log entry are required before final submission.


## A6 documentation intake update (2026-11-23)

The [AI-use log](../ai/ai-use-log.md), [final testing evidence intake](../testing/final-test-evidence.md), [CI evidence intake](../testing/ci-evidence.md), [risk register](../planning/risk-register.md) and [partial changed-file index](../release/changed-files-since-a5.md) were updated in [draft PR #112](https://github.com/victordodan/-comp330-f26-team-02/pull/112). **This is documentation progress only:** all A6 execution, independent review, risk authorization and final release gates remain OPEN until supported by attributable final-SHA evidence. The changed-file index is PR-scoped, not the final tag-to-tag team inventory.


## November 24 quality review reconciliation

PR #113 and browser PRs #128–#131 are merged. Candidate evidence recorded in [final test evidence](../testing/final-test-evidence.md#november-24-browser-candidate) reports 51 Maven tests, 38 HTTP checks, Chromium workflow and repeatable demo setup at revision `373a0f6`; underlying reports were not independently re-executed in this review. QG-02/03/04 are PARTIAL, not final PASS. QG-09–11 remain OPEN. GitHub reported no `a6-final-release` tag at the status check. Reviews: [#133](https://github.com/victordodan/-comp330-f26-team-02/pull/133#pullrequestreview-6051482315), [#134](https://github.com/victordodan/-comp330-f26-team-02/pull/134#pullrequestreview-6051482653), [#135](https://github.com/victordodan/-comp330-f26-team-02/pull/135#pullrequestreview-6051483154), [#136](https://github.com/victordodan/-comp330-f26-team-02/pull/136#pullrequestreview-6051483490). Coordinate canonical changed-file inventory with #134; reconcile expanded limitations with #135. Independent review of #112 and Victor's decision remain required.
