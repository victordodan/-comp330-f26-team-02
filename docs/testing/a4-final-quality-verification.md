# A4 Final Quality Verification — post-merge checkpoint

**Reviewer role:** Giancarlo Herrera, Quality & Review Lead (AI-assisted evidence reconciliation; human approval not implied)  
**Baseline:** `main` commit `4851dff34a2ced725e5ffee51d569e0ba4924cdc` (PR #75 merged after implementation PRs #69, #76, #73, #77, #71, #72).  
**Comparison:** [A3 baseline to main](https://github.com/victordodan/-comp330-f26-team-02/compare/a3-architecture-review...main), A3 tag `255c335343c242eade44181c3ba581157ec78bfe`.  
**Scope:** Review repository evidence and CI results; no new manual test execution or independent vulnerability scan performed by this review.

## Verification actually observed

- [Main CI run 41000100733](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/41000100733) concluded **success** for commit `d2bf5bfded8fcc03f3509394fa215b7f223efd94`, the last application-code merge (#72). Later documentation-only merge #75 is at `4851dff34a2c`; no separate check run was returned for that exact documentation commit.
- [Backend verification](../testing/a4-backend-verification.md) reports `mvn -B clean verify`, **35 passing tests**, zero failures/errors/skips on the locally simulated sequential integration; packaged HTTP and restart smoke checks also reported success. The simulation was local, not the GitHub merge commit. The GitHub main-branch CI above is independent confirmation of the application-code merge.
- Test sources: [RequestControllerTest](../../src/test/java/edu/loyola/campusconnect/controller/RequestControllerTest.java), [WorkflowServiceTest](../../src/test/java/edu/loyola/campusconnect/service/WorkflowServiceTest.java), [RequestValidatorTest](../../src/test/java/edu/loyola/campusconnect/validation/RequestValidatorTest.java), and [CampusConnectApplicationTests](../../src/test/java/edu/loyola/campusconnect/CampusConnectApplicationTests.java).

## TC-001–TC-012: evidence coverage versus case execution

**All 12 cases have relevant automated coverage, but 0/12 have a separately dated, case-by-case execution record meeting the template in [test-cases.md](test-cases.md).** Do not report twelve manual acceptance passes.

| Case | Automated / smoke evidence observed in repository | Case-specific execution |
|---|---|---|
| TC-001 valid submission | Controller create/ETag and service create tests; packaged HTTP smoke | Not separately recorded |
| TC-002 invalid input | Controller invalid creation, validator boundaries and service no-write tests | Not separately recorded |
| TC-003 current status read | Controller lifecycle/ownership and service lifecycle tests | Not separately recorded |
| TC-004 latest note/reopen | Controller resolution/reopen and service lifecycle tests | Not separately recorded |
| TC-005 student isolation | Controller student ownership, direct ID denial and service ownership tests | Not separately recorded |
| TC-006 IDs/metadata/failure | Service distinct IDs/defaults, rollback; controller storage-failure test | Not separately recorded |
| TC-007 restart persistence | Service file-store restart; packaged-process restart reported in backend verification | Not separately recorded |
| TC-008 reviewer visibility | Controller reviewer both-student inspection; service reviewer operations | Not separately recorded |
| TC-009 unauthorized reviewer access | Controller wrong-role, forged headers/owner and missing session tests | Not separately recorded |
| TC-010 transitions and reopen | Controller resolve/reopen; service lifecycle test | Not separately recorded |
| TC-011 invalid transitions/write | Controller unknown ID, invalid transition, stale version; service rollback tests | Not separately recorded |
| TC-012 note boundaries/failure | Controller note bounds/clearing and storage failure; service note tests | Not separately recorded |

## Review findings and release disposition

| Finding | Evidence-backed disposition | Remaining action |
|---|---|---|
| CR-001 authorization | **Implementation mitigated for demo scope:** session-selected synthetic identity, server ownership/reviewer checks, negative tests | Human re-review/acceptance; **not** real authentication |
| CR-002 lifecycle | **Implementation mitigated:** Submitted/In Progress/Resolved transitions, required saved resolution note, reopen | Confirm accepted requirement semantics |
| CR-003 persistence/concurrency | **Implementation mitigated:** file-backed H2, transactional writes, optimistic versioning, rollback/restart tests | Retain recovery/backup limitation; human acceptance |
| CR-004 dependencies | **Open review item:** Java 17, Spring Boot 3.5.16, resolved dependency tree and direct-license review documented | No CVE scan; maintenance/support/advisory and transitive license review not signed off |
| CR-005 verification | **Automated integration evidence present:** 35-test simulation and successful main CI | Dated TC-001–012 execution records absent; no browser UI demonstration |
| CR-006 integration | **Implementation merges complete:** #69/#76/#73/#77/#71/#72; main CI successful after #72 | PR #78 subsequently merged at `89dd750669ab`; final release/tag decision remains pending |

**Known limitations:** loopback-only synthetic classroom demo; selectable identities are not institutional authentication; no server-rendered browser forms/PRG; no verified backup/restore demonstration; no vulnerability scanner output; acceptance-case execution records still needed if rubric requires explicit per-case runs. No production or real student data readiness claimed.

**Gate decision:** **Evidence prepared; conditional/not final approval.** Human reviewers must acknowledge residual risks, confirm acceptance expectations, reconcile the now-merged #78 planning evidence, and finalize submission/tag. This document is an AI-assisted review record, not a substitute for a human approval.
