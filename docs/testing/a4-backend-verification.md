# A4 backend verification — 2026-10-30 UTC

Scope: the coordinated fixes on PRs #69, #76, #73, #77, #71, #72 and #75. Main at verification was `997ff2085180afb05ba5e19baf3b0d1d347c4eae`; these results do not mean the PRs have been merged. Final commit/check links are in the respective PR descriptions.

## Executed local checks

Temurin Java 17.0.21+9 and Maven 3.9.9; each code branch ran `mvn -B clean verify`. The environment used a Maven proxy/cache configuration outside the repository; no credentials or local settings are committed.

| Branch | Passing tests | Scope |
|---|---:|---|
| #69 | 6 | Existing validator regression suite; plain Java model compilation/packaging |
| #76 | 6 | Managed Java 17 dependency foundation, ordinary-jar packaging without app class |
| #73 | 6 | Validator regressions and executable-jar packaging |
| #77 | 7 | Validator tests plus real HTTP listener startup probe |
| #71 | 21 | Seven validator tests, startup probe, 13 service/H2 tests |
| #72 | 35 | Above plus 14 HTTP/controller integration tests |

All listed tests completed with zero failures, errors or skipped tests. An initial offline #69 package attempt lacked a cached Maven plugin; normal dependency resolution completed and the full verify command passed. An initial packaged-app smoke attempt was blocked by the execution sandbox's socket permission; rerunning with the permitted local-network capability passed.

A local sequential merge simulation in order **#69 → #76 → #73 → #77 → #71 → #72 → #75** merged without file conflicts and its extracted tree also passed all **35 tests**. Simulation objects were kept local; they are not commits pushed to the PRs. Identical prerequisite copies make each dependent branch build independently while retaining one added ordinary commit per branch.

Packaged-process checks also passed: #73 jar served HTTP 404 at its unmapped root; #72 jar rejected missing context and forged reviewer authority, assigned ownership from the student session despite a forged body studentID, rejected inline-note resolution, executed progress/save-note/resolve/reopen, and rejected a stale version. After stopping and restarting the Java process with the same temporary file H2 store, the full saved request remained identical and Student B could not read Student A's request. Synthetic temporary data only.

## Requirement mapping

| Contract | Executed evidence |
|---|---|
| REQ-001 submission/validation | RequestValidatorTest; service creation/no-write failures; HTTP JSON/validation tests |
| REQ-002 own student views | Service and HTTP ownership isolation, including direct-ID lookup |
| REQ-003 persistence | H2 repository execution, rollback, file-store restart and packaged-process restart |
| REQ-004 reviewer access | Validated session selector, missing/invalid context and forged-role/owner denial tests |
| REQ-005 status workflow | Exact transitions, already-saved note, reopen preservation, stale/concurrent status writes |
| REQ-006 notes | Trim/2,000-character bounds, resolved clearing denial, note replacement, stale/concurrent writes |
| ADR-003 consistency | Atomic id/version SQL predicate, one concurrent winner, after-write rollback, failed listing errors |

## Limits and next evidence

MockMvc tests exercise real service/repository behavior but do not demonstrate a browser UI. The separate startup and packaged checks exercise real HTTP. Server-rendered pages/PRG, backup/restore demonstrations, final submission-wide traceability, finalized owners, independent human re-review, and the Word/tag/Sakai package remain separate work. The demo intentionally allows choosing fixed identities and is not production authentication.

Dependency maintenance/advisory acceptance is not implied by test success; see the [dependency assessment](../reviews/a4-build-dependency-assessment.md). New AI assistance and its limits are recorded in [the AI entry](../ai/a4-pr-fixes.md). Earlier reviews are not dismissed or automatically converted to human approval.
