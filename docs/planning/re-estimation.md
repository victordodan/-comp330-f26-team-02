# Cycle 2 Re-Estimation and Maturity Backlog

**Team:** Codex Ramblers · **Project:** CampusConnect
**Current Phase:** A6 — Final Cycle Release, Presentation and Peer Review
**Release Cycle:** Cycle 2 (baseline: `a5-cycle1-release` → `3f297d8`)
**Prepared by:** Malec Tarabein (Planning & Process Lead) · **Backup:** Shú Perez
**Prepared:** 2026-11-24
**Status:** **PROPOSED.** Scope, estimates and owners follow the
[Cycle 2 maturity plan](cycle2-maturity-plan.md). Victor confirmed the MT-02
runbook assignment on 2026-11-24; the remaining targets still need Victor's
confirmation and each owner's acknowledgement. Until then, no other row here
is a commitment.

## Why this was re-estimated

The A2 version of this file defined *when* to re-estimate and recorded that no
re-estimation had happened. Several of its triggers have now fired:

| Trigger (from the A2 process) | What happened | Evidence |
|---|---|---|
| Scope changes | Cycle 2 replaces most feature work with six maturity targets; notifications and real authentication are cut or deferred; a lightweight browser client was added by team decision (PRs #126–#128) | [Maturity plan](cycle2-maturity-plan.md), [Cycle 2 handoff](../release/cycle2-handoff.md) |
| Actual progress differs from the estimate | All 102 A4→A5 commits landed in the last two days of Cycle 1 | [Postmortem](../operations/postmortem-cycle1.md) PM-01 |
| Estimates could not be checked | No task-hour actuals exist for EST-001–EST-005 | Postmortem PM-04; [estimates.md](estimates.md) "A5 — Estimate versus Actual" |
| A tracked risk occurred | R-007 (missing engineering evidence) materialised as stale MS/TASK statuses and template-only operations docs | Postmortem PM-03, PM-06 |
| Capacity concentrated on one person | 61 of 102 commits by the Team Lead | Postmortem PM-07 |

## Cycle 2 scope

### In scope (proposed)

Cycle 2 hardens the Cycle 1 release and produces the A6 evidence package. Work
is the six maturity targets plus the release package itself. The only feature
addition is the lightweight browser client already implemented (see below).

| ID | Work item | Tier | Maturity target | Why (Cycle 1 evidence) |
|---|---|---|---|---|
| C2-01 | Bounded backup and restore | B | MT-01 | LIM-001, A5-F01, R-010 |
| C2-02 | Operational readiness and observability | C | MT-02 | LIM-005, PM-06, Issue #107 |
| C2-03 | Identity boundary and negative authorization tests | C | MT-03 | LIM-002, A5-F02 |
| C2-04 | Acceptance re-run and independent review | C | MT-04 | PM-05, A5-F04/F05, Issues #104/#105 |
| C2-05 | Supply-chain assurance | B | MT-05 | PM-08, A5-F06 |
| C2-06 | Planning accuracy and continuous evidence | C | MT-06 | PM-01–PM-04, PM-07 |
| C2-07 | A6 release package (release notes, `changed-files-since-a5.md`, final test evidence, Word summary, PPTX, presentation) | C | — (A6 required) | A6 spec §2 |

Tier **C** = committed for A6 once confirmed. Tier **B** = bounded: done if
capacity allows, otherwise closed with an explicit, dated deferral.

### Cut or deferred

| Item | Decision | Reason | Where it is recorded |
|---|---|---|---|
| Email / push notifications (LIM-003) | **Removed from scope** | Outside the agreed scope; pull-based status already meets REQ-002. | Maturity plan, scope decisions |
| Institutional authentication / SSO (LIM-002) | **Deferred beyond the course** | Too large for the remaining time. The risk is controlled by the loopback-only, synthetic-identity boundary and proven by C2-03 instead. | Maturity plan, risk register |
| Production hosting / deployment (LIM-005) | **Deferred beyond the course** | No production use is authorized. C2-02 covers local operation only. | Maturity plan |
| MT-01, MT-05 if capacity runs out | **Deferred with named owner** | Tier B by design; a documented deferral is an acceptable outcome. | Maturity plan final disposition |

### Added by team decision

| Item | Decision | Reason | Where it is recorded |
|---|---|---|---|
| Browser GUI (LIM-004) | **Added (lightweight)** | The team approved a lightweight HTML, CSS and JavaScript client of the existing REST API for the classroom demo; implemented in PRs #126–#128. The integrated browser workflow is verified and its final disposition recorded at freeze, under C2-04. Its implementation hours were not estimated here. | Maturity plan, scope decisions |

## Cycle 2 estimates (proposed)

Estimates are three-point ranges in person-hours. They are **Low confidence**
because Cycle 1 produced no actuals to calibrate against (PM-04). They are
deliberately sized from the expected proof in the maturity plan, not from
Cycle 1 commit counts. They will be compared with the effort log at CP-A6-1.

| ID | Work item | Low | Likely | High | Basis | Primary / backup owner | Task |
|---|---|---:|---:|---:|---|---|---|
| C2-01 | Bounded backup and restore | 4 h | 6 h | 10 h | Write restore steps for the H2 file store, run one restore at the A6 SHA, save the transcript | Shú / Mareddy | TASK-018.1 |
| C2-02 | Operational readiness and observability | 6 h | 9 h | 14 h | Rewrite runbook and observability plan from real behaviour; runbook exercise covering startup, one failure signal and same-store restart; mark unused starter files out of scope (8 files) | Shú (runbook), Giancarlo (exercise and evidence); Shelby / Mareddy (observability planning) | TASK-018.2 |
| C2-03 | Identity boundary and negative authorization | 6 h | 10 h | 16 h | Fill `permission-boundaries.md`; add negative-path tests (student acting as reviewer, forged headers); green CI at the A6 SHA | Shú / Mareddy | TASK-018.3 |
| C2-04 | Acceptance re-run and independent review | 8 h | 12 h | 18 h | Re-run or re-map TC-001–TC-012 with a human disposition per case; named non-author review; CI URL and totals at the tag commit | Mareddy, Giancarlo / Victor | TASK-018.4 |
| C2-05 | Supply-chain assurance | 3 h | 5 h | 10 h | Either update Tomcat/Boot and re-run CI, or a dated advisory and license review; high end covers an update that breaks tests | Victor-appointed reviewer / Victor | TASK-018.5 |
| C2-06 | Planning accuracy and continuous evidence | 5 h | 8 h | 12 h | This re-estimation, task plan and schedule updates, effort log upkeep, two checkpoints, changed-file index per merged PR | Malec / Shú | TASK-018.6 |
| C2-07 | A6 release package | 10 h | 14 h | 20 h | Release notes, `changed-files-since-a5.md`, final test evidence, Word summary, PPTX, rehearsal | Victor, Shelby / team | TASK-018.7 |
| | **Total** | **42 h** | **64 h** | **100 h** | | | |

**Capacity check.** Six members over roughly two weeks (A6 opens after
Class 21, Week 13; hard due before Class 27, Week 15). The likely total is about
11 hours per person, which fits only if work starts now and is spread across
members rather than landing on the Team Lead (PM-07). If the effort log at
CP-A6-1 shows the team tracking toward the high end, MT-01 and MT-05 are the
first to be deferred, in that order, before any C-tier target is reduced.

## Re-estimation rules for Cycle 2

The A2 triggers still apply. Cycle 2 adds three concrete rules so PM-03 and
PM-04 do not repeat:

1. **Log effort as it happens.** Every member records actual hours against a
   TASK-018 subtask in the [effort log](task-plan.md#a6-effort-log) in the
   same week the work is done. Hours are never reconstructed from commit times.
2. **Re-estimate at each checkpoint.** At CP-A6-1 and CP-A6-2, compare logged
   hours with the Likely value. If a work item has used its Likely estimate and
   is less than half done, or has passed its High estimate, re-estimate it here
   and apply the deferral order above.
3. **Record every change.** A changed estimate keeps the old value, the new
   value, the evidence and the date in the change log below. Nothing is
   silently overwritten.

## Re-estimation change log

| Date | Item | Previous | Revised | Reason / evidence | Approved by |
|---|---|---|---|---|---|
| 2026-11-24 | Cycle 2 baseline | EST-006 (no numeric range recorded) | C2-01–C2-07, 42 / 64 / 100 h | Cycle 2 scope set by the maturity plan; Cycle 1 postmortem | *Pending Victor* |

## Ownership

The Planning & Process Lead (Malec Tarabein, backup Shú Perez) keeps this file,
the task plan and the schedule current. Each work-item owner logs their own
hours and flags a re-estimation trigger when they see one. Victor confirms scope
changes and deferrals. The Quality & Review Lead (Giancarlo Herrera) checks that
estimates, tasks, schedule, risks and traceability agree before the A6 tag.

## Related records

- [Cycle 2 maturity plan](cycle2-maturity-plan.md)
- [Cycle 1 postmortem](../operations/postmortem-cycle1.md)
- [Task plan](task-plan.md): TASK-018 subtasks and effort log
- [Schedule](schedule.md): MS-007 and checkpoints
- [Estimates](estimates.md): Cycle 1 EST-001–EST-006 (historical)
- [Risk register](risk-register.md) and [traceability](traceability.md): Cycle 2 updates to follow

**AI disclosure:** Claude (Claude Code) drafted this file for Malec Tarabein
from the maturity plan, postmortem and A6 specification. The estimate ranges and
capacity check are proposals for the team to confirm; no hours, work or
approvals are claimed. See the [AI-use log](../ai/ai-use-log.md).
