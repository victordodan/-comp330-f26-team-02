# Cycle 2 Maturity Plan and Target Disposition

**Team:** Codex Ramblers · **Project:** CampusConnect
**Task:** TASK-017 (primary: Victor Dodan · backup: Malec Tarabein) → feeds TASK-018
**Milestone:** MS-006 Cycle 2 maturity planning (Nov 11–Nov 24, 2026)
**Prepared by:** Malec Tarabein (Planning & Process Lead) · **Backup:** Shú Perez
**Prepared:** 2026-11-24 · **Cycle 1 baseline:** `a5-cycle1-release` → `3f297d8`
**Status:** **PROPOSED.** Victor confirmed the MT-02 runbook assignment on
November 24, 2026. Shú Perez owns the runbook. Giancarlo Herrera completed the
Windows operational exercise. Victor Dodan takes over execution and observation
recording for follow-up checks. Other target and owner confirmations are pending.

## Purpose

A6 requires every Cycle 2 maturity target to show the Cycle 1 evidence that
justified it, the proof expected, its owner, and a final disposition. This file
is that record. It starts from the six candidates in
[`cycle2-handoff.md`](../release/cycle2-handoff.md) and the findings in the
[Cycle 1 postmortem](../operations/postmortem-cycle1.md).

## Selection rules

1. A target must trace to a recorded Cycle 1 finding, limitation, defect or risk.
2. Prefer targets that reduce a **High** residual risk or close an A6-required
   evidence gap, over new features.
3. Keep the set small enough to finish with real evidence before the A6 freeze
   (hard due date: before Class 27, Week 15; Sakai is authoritative).
4. A target that cannot be proven is **deferred** with a named owner, not
   claimed. A documented deferral is an acceptable outcome.

## Scope decisions

The following decisions update the Cycle 1 handoff.

| Candidate | Rationale | Disposition |
|---|---|---|
| Browser GUI (LIM-004) | The team approved a lightweight HTML, CSS, and JavaScript client of the existing REST API for the classroom demo. | Implemented in PRs #126–#128. Verify the integrated browser workflow and record the final disposition at freeze. |
| Email/push notifications (LIM-003) | Outside agreed scope; pull-based status meets REQ-002. | Removed from scope |
| Institutional authentication / SSO (LIM-002) | Too large for the remaining time; the risk is controlled by the loopback/synthetic boundary instead (MT-03). | Deferred beyond the course |
| Production hosting / deployment (LIM-005) | No production use is authorized. | Deferred beyond the course |

## Selected maturity targets

Tier **C** = committed for A6. Tier **B** = bounded: do it if capacity allows,
otherwise record an explicit deferral. Owners follow [`roles.md`](../team/roles.md)
and the owners already named in draft [PR #112](https://github.com/victordodan/-comp330-f26-team-02/pull/112).

| ID | Target | Tier | Cycle 1 evidence that justifies it | Expected proof (A6) | Owner / backup | Tracking |
|---|---|---|---|---|---|---|
| MT-01 | **Bounded data recovery** | B | LIM-001 (High data-loss risk), A5-F01, R-010; TC-007 restoration deferred in the A5 decision | Documented backup and restore steps in the runbook, plus one executed restore transcript at the A6 SHA in `/test-evidence`. Otherwise an explicit deferral recorded by Victor. | Shú / Mareddy | Defect review A5-F01 |
| MT-02 | **Operational readiness and observability** | C | LIM-005; PM-06 (eight starter-kit files untouched since Sep 10); Issue #107 | Project-specific `runbook.md` (start, verify, restart, common failures), `observability-plan.md` stating what logging actually exists, and a runbook exercise covering startup, one observed failure signal, and same-store restart. Record observations, environment, and candidate SHA in `runtime-evidence.md`. Any file not used is marked out of scope instead of kept as a template. | Shú (runbook), Victor (follow-up exercise and evidence); Shelby / Mareddy (observability planning) | Issue #107 |
| MT-03 | **Identity boundary and negative authorization** | C | LIM-002 (High outside demo), A5-F02 | `permission-boundaries.md` filled in with the real who-can-do-what table, plus negative-path tests (student cannot act as reviewer, forged headers rejected) passing in CI at the A6 SHA. | Shú / Mareddy | Defect review A5-F02 |
| MT-04 | **Acceptance and independent review discipline** | C | PM-05; A5-F04, A5-F05; independent review deferred in A5; Issues #104, #105 | TC-001–TC-012 re-run or re-mapped at the A6 SHA with a named human disposition per case; a **named non-author** integrated review before tagging; CI run URL and test totals at the exact tag commit. | Mareddy (acceptance), Giancarlo (quality gate) / Victor (decision) | #104, #105, #111, #112, #113 |
| MT-05 | **Supply-chain assurance** | B | PM-08; A5-F06; Tomcat 10.1.55 advisories and Boot 3.5 support accepted for A5 only | Either a compatible Tomcat/Boot update with CI re-run, or a dated, scoped advisory/license review. Otherwise Victor's explicit, dated deferral. | Victor-appointed reviewer / Victor | A5 dependency disposition |
| MT-06 | **Planning accuracy and continuous evidence** | C | PM-01–PM-04, PM-07; no effort actuals; 102 commits in 2 days; stale MS/TASK statuses | Effort log kept from A6 kickoff (in `task-plan.md`), Cycle 2 `re-estimation.md`, MS-006/MS-007 statuses current, an interim checkpoint before the freeze, and the changed-file index updated per merged PR. | Malec / Shú | This PR and follow-ups |

## Interim checkpoint (MT-06)

To avoid repeating PM-01, the plan adds one checkpoint before the A6 freeze:

| Checkpoint | Exit evidence | Coordinator |
|---|---|---|
| CP-A6-1 — midpoint | Every C-tier target has an open PR or issue with an owner; every B-tier target is marked "in progress" or "deferred"; effort log has entries from each member | Malec |
| CP-A6-2 — freeze candidate | Candidate SHA named; CI green at that SHA; MT-04 review requested | Victor / Giancarlo |

Dates for CP-A6-1 and CP-A6-2 are set by the team in `schedule.md`; this file
does not invent deadlines.

### Open decisions for MT-05 and MT-06

- MT-05 remains open for Victor's scoped A6 dependency and license decision.
  A documented deferral is proposed if the scoped review is unavailable.
  [Historical assessment](../quality/a5-dependency-disposition.md) ·
  [Current limitations](../release/known-limitations.md#616-lim-016--dependency-and-security-assurance)
- Malec proposes **Mitigated** for MT-06 in [#138](https://github.com/victordodan/-comp330-f26-team-02/pull/138).
The merged planning updates support the recommendation. The missing effort
actuals and separate midpoint checkpoint remain visible. Victor's decision
is pending.

## Final disposition (complete at the A6 freeze)

Allowed values: **Fixed**, **Mitigated**, **Deferred**, **Accepted**, **Removed
from scope**. Record what actually happened, link the evidence, and state
whether the risk reduction we expected was achieved. Leave a row OPEN rather
than claim an outcome without evidence.

| ID | Final disposition | Evidence (PR / commit / run / file) | Expected risk reduction achieved? | Decided by / date |
|---|---|---|---|---|
| MT-01 | OPEN. Backup restoration deferral proposed. | [Runtime evidence](../observability/runtime-evidence.md), [LIM-001](../release/known-limitations.md#61-lim-001--local-database-retention-and-recovery) | Same-store restart passed. Backup restoration remains unverified. | Victor decision pending |
| MT-02 | Mitigated for the classroom candidate | [#137](https://github.com/victordodan/-comp330-f26-team-02/pull/137), [runbook](../operations/runbook.md), [runtime evidence](../observability/runtime-evidence.md) | Startup, a controlled failure signal, and same-store restart were observed. The runbook and evidence are merged. | Candidate evidence recorded Nov 24, 2026 |
| MT-03 | Mitigated for the classroom candidate | [Permissions](../security/permission-boundaries.md), [workflow transcript](../../test-evidence/a6-operations-0860160/workflow.jsonl), [candidate CI](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/45543404188) | Role and ownership denials were exercised on `0860160`. Selectable identities remain a classroom limit. Reconcile with the final SHA. | Candidate evidence recorded Nov 24, 2026 |
| MT-04 | OPEN | [Candidate CI](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/45543404188), [runtime evidence](../observability/runtime-evidence.md), [quality PR #112](https://github.com/victordodan/-comp330-f26-team-02/pull/112) | Candidate automation and manual checks are recorded. Final case dispositions, independent integrated review, and release authorization are pending. | Victor decision and non-author review pending |
| MT-05 | OPEN | — | — | — |
| MT-06 | Mitigated | [#115](https://github.com/victordodan/-comp330-f26-team-02/pull/115) (`fa020be`); [#136](https://github.com/victordodan/-comp330-f26-team-02/pull/136) (`0ad0d6a`, [approved by Giancarlo Herrera](https://github.com/victordodan/-comp330-f26-team-02/pull/136#pullrequestreview-6051623597)); [`re-estimation.md`](re-estimation.md), [`task-plan.md`](task-plan.md), [`schedule.md`](schedule.md), [`changed-files-since-a5.md`](../release/changed-files-since-a5.md) | Partly: re-estimation and MS/TASK statuses are current and reviewed, but the effort log has no entries, CP-A6-1 was not held separately, and the inventory still needs reconciliation against the approved release SHA. | Proposed by Malec Tarabein. Approved by Victor Dodan on November 24, 2026 (Chicago time). |

## Related records

- [Cycle 1 postmortem](../operations/postmortem-cycle1.md)
- [Cycle 2 handoff](../release/cycle2-handoff.md)
- [Risk register](../planning/risk-register.md) and the A6 intake in draft PR #112
- [Traceability](../planning/traceability.md) (A6 addendum in draft PR #112)
- [Task plan](../planning/task-plan.md): TASK-017, TASK-018
- [Re-estimation](../planning/re-estimation.md): Cycle 2 scope, estimates and cut items

**AI disclosure:** Claude (Claude Code) helped Malec Tarabein draft this plan
from the Cycle 2 handoff, A5 release decision, known limitations, defect review
(A5-F01–F06) and draft PR #112. Victor confirmed the MT-02 runbook assignment. The remaining target, tier, and owner proposals await confirmation. No work, test or
disposition is claimed as complete. See the [AI-use log](../ai/ai-use-log.md).
