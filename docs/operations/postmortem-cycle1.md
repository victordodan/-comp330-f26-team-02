# Cycle 1 Postmortem — CampusConnect A5 Release

**Team:** Codex Ramblers
**Prepared by:** Malec Tarabein (Planning & Process Lead) · **Backup:** Shú Perez
**Accountable reviewer:** Victor Dodan (Team Lead; TASK-017 primary)
**Prepared:** 2026-11-24
**Baseline reviewed:** `a5-cycle1-release` → `3f297d8bbe7b70ef9662f0bb1cb4796014bd6510` (PR #110)
**Status:** Draft for team review. Findings are proposed until the team reviews this PR.

This is a blameless process postmortem of the Cycle 1 release, not an incident
report. It records what the evidence shows about how Cycle 1 was delivered, so
that Cycle 2 work is selected from evidence. The selected Cycle 2 targets and
their final disposition are in
[`docs/planning/cycle2-maturity-plan.md`](../planning/cycle2-maturity-plan.md).

## 1. Summary

Cycle 1 shipped a working HTTP support-request workflow (REQ-001–REQ-006) under
a **CONDITIONAL GO** for a loopback-only, synthetic-data classroom demonstration
([A5 release decision](../release/a5-release-decision.md)). A clean JDK 17 /
Maven verify passed 35 tests with zero failures, errors or skips at the
integration checkpoint, and five limitations (LIM-001–LIM-005) were disclosed
rather than hidden.

The release was defensible, but it was produced under heavy late compression.
All 102 commits between the `a4-construction-integration` and
`a5-cycle1-release` tags carry author dates of 2026-11-09 or 2026-11-10. Planning
and operations records were not kept current while work happened. The course's
independent non-author integrated review was **not completed**; it was recorded
as an explicit exception under the Team Lead's authority.

## 2. What shipped and what did not

| Area | Cycle 1 outcome | Evidence |
|---|---|---|
| Workflow | Submit, view own status/notes, reviewer queue, lifecycle transitions, notes. TC-001–TC-012 accepted within documented classroom scope. | [Acceptance closeout](../testing/a5-acceptance-closeout.md), [release decision](../release/a5-release-decision.md) |
| Persistence | File-backed H2 via JDBC; same-store restart retention verified. Backup/restore **not** verified. | LIM-001, [ADR-004](../decisions/ADR-004-local-persistence-and-recovery.md) |
| Identity | Server-session demo identities; forged role headers rejected. Reviewer identity is deliberately selectable. | LIM-002, [known limitations](../release/known-limitations.md) |
| Interface | HTTP API only; browser UI deferred as a scope change. | LIM-004, [scope](../planning/scope.md) |
| Notifications | Not implemented (pull-based status retrieval). | LIM-003 |
| Operations | No verified deployment, rollback, monitoring or recovery package. | LIM-005 |
| CI | Real Maven CI on push/PR to `main` ([ci.yml](../../.github/workflows/ci.yml)). | [CI evidence](../testing/ci-evidence.md), [build result](../../test-evidence/a5-release-authorization/build.json) |
| Dependencies | Inventory exists; Tomcat 10.1.55 advisories and Boot 3.5 support accepted for the bounded demo; full advisory/license review incomplete (OSV API blocked). | [A5 dependency disposition](../quality/a5-dependency-disposition.md) |

## 3. Timeline (from repository records)

| Date | Event | Source |
|---|---|---|
| 2026-10-16 – 10-29 | MS-004 implementation window | [schedule.md](../planning/schedule.md) |
| 2026-10-29 | `a4-construction-integration` tag | git tags |
| 2026-10-30 – 11-10 | MS-005 verification and release-readiness window | schedule.md |
| 2026-10-30 | Last planning-doc edits before the A5 crunch (`schedule.md`, `task-plan.md`, `risk-register.md`) | git log |
| 2026-11-09 | 62 commits; planning, roles, CI and acceptance work opened in parallel (#98–#107) | git log |
| 2026-11-10 | 40 commits; acceptance dispositions (#108, #109), release decision and package (#110) | git log |
| 2026-11-10 | `a5-cycle1-release` tag created on `3f297d8` | tag annotation |
| 2026-11-11 – 11-24 | MS-006 Cycle 2 maturity planning window; no commits on `main` in this window up to this draft | schedule.md, git log |

Author dates show when commits were made, not when work was done offline.
They are used here only to show *when evidence reached the repository*.

## 4. What went well

- **Honest limitation disclosure.** The release documents five concrete
  limitations, each with severity, workaround and owner, and does not claim
  production readiness. Earlier incorrect claims (in-memory storage,
  header-based identity, sub-two-second build) were found and removed in
  #81/#83.
- **Real automated verification.** Maven CI runs on every push and PR to
  `main`, and the release checkpoint passed 35 tests with no failures or skips.
- **Controlled baseline.** The A5 tag annotation names the exact release commit,
  integrated PR, decision authority and scope.
- **Security boundary held where it mattered.** Client-supplied role headers and
  ownership fields cannot elevate authority; the remaining gap (selectable
  Reviewer) is disclosed rather than hidden.
- **AI use was disclosed.** AI-assisted drafting, reconciliation and the Codex
  integrated technical review are recorded in the [AI-use log](../ai/ai-use-log.md)
  with their limits.

## 5. What did not go well

| ID | Finding | Evidence |
|---|---|---|
| PM-01 | **Late compression.** All 102 commits between the A4 and A5 tags landed in the final two days (62 on Nov 9, 40 on Nov 10). Review, acceptance and release decisions were made in the same window as the work they assessed. | `git log a4-construction-integration..a5-cycle1-release` |
| PM-02 | **Evidence drifted from reality, then had to be reconciled.** 30 of those 102 commit subjects are "reconcile" or "correct" changes, and 10 are "merge main" commits. Records cited multiple checkpoint SHAs (`ac228d0`, `b899a1b`, `531d54c`, `5fab39a`, `2349a14`) before the final one. | git log subjects; [known limitations](../release/known-limitations.md) header |
| PM-03 | **Planning records were not maintained as work happened.** Planning docs were untouched from Oct 30 to Nov 9. MS-004 and MS-005 still read "Planned" in `schedule.md` after both completed. `re-estimation.md` still describes the A2 phase. The Planning & Process Lead's first commit was on Nov 9. | schedule.md L29–31; git log |
| PM-04 | **No effort actuals.** No reliable task-hour records exist, so EST-001–EST-006 could not be compared with actual effort. | [estimates.md](../planning/estimates.md) "A5 — Estimate versus Actual" |
| PM-05 | **Acceptance and review accountability was compressed into delegation.** TC-001–TC-012 decisions were recorded by Codex under the Team Lead's delegated authority rather than case by case by a human tester. The independent non-author integrated review was deferred as an exception. Issues #104 and #105 remain open. | [release decision](../release/a5-release-decision.md); Issues #104, #105 |
| PM-06 | **Operational artifacts stayed as templates.** Eight files under `docs/operations/`, `docs/observability/` and `docs/security/` still contain the Sep 10 starter-kit text, so a reviewer opening them sees no project evidence. | Issue #107; git log for those paths |
| PM-07 | **Integration load concentrated on one person.** 61 of the 102 commits were authored by the Team Lead, largely merging `main` into PRs and reconciling them before landing. | `git log --format=%an` over the A4..A5 range |
| PM-08 | **Supply-chain assurance incomplete.** Tomcat advisories and the Boot 3.5 support window were accepted for the bounded demo, and full advisory/license review was blocked. | [A5 dependency disposition](../quality/a5-dependency-disposition.md), A5-F06 |

## 6. Root causes and contributing factors

1. **Evidence was produced at the gate instead of continuously.** The team's own
   rule ("evidence is created as work occurs rather than reconstructed before a
   phase gate", README) was not followed in A5. That caused PM-01, PM-02 and PM-03.
2. **No small, regular checkpoints between Oct 30 and Nov 9.** Without an interim
   checkpoint there was no forcing point to update plans, run acceptance cases
   or ask for review early (PM-01, PM-05).
3. **Effort was never recorded at the time work happened**, so it cannot be
   reconstructed afterwards (PM-04).
4. **Ownership of non-code artifacts was unclear.** Operations, observability and
   security files had role owners in `roles.md`, but no task in the A5 plan
   required them, so they were deferred by default (PM-06).
5. **Integration was serial through the Team Lead.** PRs were opened late and in
   parallel, so conflicts accumulated and one person resolved them (PM-02, PM-07).

## 7. Where we got lucky

- No source or POM change was needed after the release checkpoint, so the
  35-test build at `5fab39a` still described the tagged code.
- The data-loss risk (LIM-001) was never exercised, because only disposable
  synthetic data was used.

## 8. Cycle 1 presentation feedback

No separate notes from the Cycle 1 presentation were available for this
closeout. The written A5 assessment identified three follow-up priorities:
verify the exact release revision, record case-by-case acceptance outcomes,
and obtain an independent integrated review. Those priorities are tracked
under MT-04. The [A5 release decision](../release/a5-release-decision.md)
records the original scope and the independent-review exception.

## 9. Corrective actions → Cycle 2 maturity targets

Each finding maps to a proposed maturity target in the
[Cycle 2 maturity plan](../planning/cycle2-maturity-plan.md).

| Finding | Corrective action | Maturity target |
|---|---|---|
| PM-05, A5-F04/F05 | Case-by-case human acceptance at the A6 SHA; named non-author review before the A6 tag | MT-04 |
| PM-06, LIM-005 | Replace starter templates with project-specific runbook and observability evidence, or mark them out of scope | MT-02 |
| LIM-001, A5-F01, TC-007 deferral | Bounded backup/restore procedure and test, or an explicit deferral | MT-01 |
| LIM-002, A5-F02 | Negative authorization evidence and a documented permission boundary | MT-03 |
| PM-08, A5-F06 | Dated dependency review or stack update, or an explicit named deferral | MT-05 |
| PM-01–PM-04, PM-07 | Interim checkpoint, prospective effort log, changed-file index maintained per PR, early PRs | MT-06 |

## 10. Lessons learned

- A limitation that is documented and owned is worth more than an unproven claim.
  Keep doing that.
- Gate evidence should be the *summary* of records that already exist, not
  something written on the day of the gate.
- Pick fewer Cycle 2 targets and finish them with real evidence, rather than
  starting every candidate.

## 11. Evidence references

- [A5 release decision](../release/a5-release-decision.md)
- [Known limitations](../release/known-limitations.md)
- [Cycle 2 handoff](../release/cycle2-handoff.md)
- [Defect review](../quality/defect-review.md) (A5-F01–A5-F06)
- [Risk register](../planning/risk-register.md), [schedule](../planning/schedule.md), [estimates](../planning/estimates.md), [task plan](../planning/task-plan.md)
- Issues #104, #105, #107, #111; PRs #81, #83, #94, #108, #109, #110, #112, #113

**AI disclosure:** Claude (Claude Code) helped Malec Tarabein draft this
postmortem. Commit counts, dates, authors and SHAs were computed from `git log`
and tag data at `3f297d8`; findings were cross-checked against the linked
documents. No tests, reviews, acceptance decisions or risk decisions are
claimed. See the [AI-use log](../ai/ai-use-log.md).
