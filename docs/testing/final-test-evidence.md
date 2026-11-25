# A5 Final Testing Evidence Index — Candidate

> Current decision (2026-11-10): [Victor-authorized conditional classroom release](../release/a5-release-decision.md).
> TC-001–TC-012 accepted within documented scope; residual risks explicitly
> accepted/deferred. Historical pending decisions below retain their original
> context. Final integrated human review is explicitly deferred for this conditional
> baseline under Victor’s release authority; the course condition is unfulfilled.
> Exact-main CI is required before tagging; no production/security/legal clearance.

**Review checkpoint:** `ac228d07bc950251faee006b7ab98857fde81f43`. Final release/tag: not selected.
**Coordinator:** testing owner; **review:** Giancarlo; **release decision:** Victor. Assignments require team confirmation.

| Evidence | Actual observed result | Tested revision / scope |
|---|---|---|
| [PR #95 CI](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42746026082) | clean verify: 35 tests, zero failures/errors/skips; packaging passed | Synthetic merge `5d791a7b657209a6c688251149db92828b313606`; see CI evidence for head/base |
| [Local packaged rehearsal](a5-demo-rehearsal.md) / [raw records](../../test-evidence/a5-http-demo/README.md) | JDK17 build: 35 tests passed; 36 initial, 5 restart and 36 repeat HTTP checks passed; stopped-server failure control exited 1 | Source `714a31bf5c9283e732fcfd895615a3471fef93b4`, script/jar hashes in manifest |
| [Acceptance assessment](../reviews/a5-acceptance-evidence-quality-review.md) | Merged PR101 supplies the corrected twelve-case assessment; human approval pending | Documentary assessment, no new case execution claimed |
| [Dependency inventory](../quality/a5-dependency-disposition.md) | Fresh dependency resolution succeeded; advisory query blocked | Current application/POM checkpoint, not vulnerability clearance |

A source/POM diff from the rehearsal source to this checkpoint is empty. That
supports reuse for unchanged behavior, but is not a new test of every later
commit. Logs retain their original SHAs. Before finalization, identify the exact
candidate, link its CI run and record independent review and case decisions.
Human presentation rehearsal, dependency acceptance and final release approval
remain pending. This index summarizes existing executions; it runs no new tests.

## Reuse of PR #101 — review findings

PR #101 is merged with the mapping corrections. The demo's reviewer detail GET
inspects Student A while JUnit covers both students; the unknown-UUID HTTP check
is a reviewer status PATCH, not a student lookup. The integrated assessment
preserves these distinctions and grants no human acceptance. No new case
execution is claimed by this index.

## Acceptance closeout update

[Proposed case dispositions](a5-acceptance-closeout.md) now link a fresh 35-test
build and 36 HTTP/5 packaged restart checks at source `531d54ca0f391e218752888900c4668173f75e27`.
The twelve dispositions remain proposed PASS pending named human acceptance
and accountability confirmation. The closeout reconciles Mareddy’s #109 and
links artifact verification. Independent non-author review, security/license
risk acceptance, final release authorization and release tag remain OPEN.


---

## Assignment 6 — final testing evidence intake (2026-11-23; OPEN)

This section is an **A6 evidence request**, not a new execution. The A5 evidence and scoped classroom acceptance described above remain historical. A5's [conditional release decision](../release/a5-release-decision.md) is not A6 sign-off. [Quality gate](../quality/a6-final-release-quality-gate.md) and [traceability](../planning/traceability.md) track final requirements.

| Required A6 evidence | Recorded result | Owner / next action |
|---|---|---|
| Final candidate commit SHA and execution date/environment | **PENDING** | Shu / Mareddy identify exact tested revision |
| Clean Maven verification, test counts, failures/skips and report | **PENDING** | Mareddy links actual log/CI artifacts |
| TC-001–TC-012 case-by-case results and human disposition | **PENDING** | Mareddy supplies execution; Victor records acceptance |
| HTTP happy path, authorization denials, invalid transitions and concurrency | **PENDING** | Shu / Mareddy provide transcript with tested SHA |
| Packaged restart, same H2 path and persistent state | **PENDING** | Shu / Mareddy provide restart transcript |
| Actual backup/restore test, if claimed | **NOT ESTABLISHED** | Shu documents verified procedure or explicit limitation |
| Regressions, defects, reviewer and residual risks | **PENDING** | Giancarlo links findings and independent reviewer |

Do not reuse historical 35 Maven / 36 HTTP / 5 restart counts as newly run A6 results. The exact A6 SHA, logs, operator and dates must be recorded before closing any row. No test was executed as part of this documentation edit.

## November 24 browser candidate

Codex ran the following checks on a clean checkout of merged main at
`373a0f6b6d6190d6067099178a8c71862bb12dec` on November 24, 2026.
[Main CI](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/45509064331) passed on the same revision.

| Check | Observed result |
|---|---|
| JDK 17, Maven 3.9.9, `mvn -B clean verify` | 51 tests passed, zero failures, errors, or skips. |
| Packaged Spring Boot application on loopback with a fresh file-backed H2 store | The root URL served the CampusConnect portal. |
| `bash scripts/frontend-smoke-test.sh` | All 38 HTTP checks passed. |
| Automated Chromium exercise against the packaged application | Student A submitted a request, Student B could not see it, and Reviewer updated its status and note. A stale reviewer edit preserved the concurrent update. Student A saw the resolved status and final note. |
| `bash scripts/demo-data.sh`, confirmed twice on the same store | First run created six examples. Second run skipped all six with zero new requests and zero reviewer updates. |

These results support the browser integration review. The Windows runbook exercise is recorded below. Final TC-001–TC-012 dispositions, independent integrated review, and release authorization remain open.


## November 24 — Quality Lead Windows operational exercise

Operator: Giancarlo Herrera. Tested checkout `0860160bc458af670c7944ccd035d21425e7511d` (not final tag). Windows PowerShell 5.1, Temurin Java 17.0.21. `mvn -B clean verify` BUILD SUCCESS at 22:04:18 CST, 23.277 s. Surefire summaries: application 1, frontend 15, request controller 15, workflow service 13, validator 7; total 51, zero failures/errors/skips. Operator screenshots supplied in chat; not repository attachments. Packaged JAR bound to 127.0.0.1:18080 with `jdbc:h2:file:./work/demo-runtime/campusconnect`. Initial connection refused was resolved by starting the server with the runtime directory prepared. Manual checks: build, startup, Student A submission, Student B empty dashboard, reviewer access, Submitted→In Progress, resolution note and Resolved transition, Student A status/note visibility, controlled stop/restart (operator confirmed), and persistence of same request ID/status/note after restart: 10/10 scoped checks passed. Synthetic request `cc60ec5e-b301-46b6-ad99-d2a0466b8e21`, created 22:07:48, In Progress 22:11:31, Resolved 22:12:08 CST. No independent outage screenshot, direct API authorization exercise, backup restoration, crash recovery or final-tag acceptance claimed. Final integrated release authorization remains pending.
