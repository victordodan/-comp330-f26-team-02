# A5 Defect and Known-Limitations Reconciliation

**Project:** CampusConnect — Team 2 (Codex Ramblers)
**Original preparer:** Giancarlo Herrera, Quality & Review Lead
**Reviewed repository checkpoint:** `56c0907c7c317156f1a86ac8dcc9b9f158052b9d` (merged #81, #83, #84 and #92)
**Status:** Updated evidence reconciliation; human disposition pending, no release approval.

## Findings and dispositions

These six tracked IDs preserve the original review history. They are not six
confirmed software defects or six wholly unresolved discrepancies. Documentation
corrections are distinguished from runtime assurance and accepted residual risk.

| ID | Type / proposed severity | Verified evidence / finding | Current disposition | Coordinator / remaining action | Evidence |
|---|---|---|---|---|---|
| A5-F01 | Persistence documentation / High | PRs [#81](https://github.com/victordodan/-comp330-f26-team-02/pull/81) and [#83](https://github.com/victordodan/-comp330-f26-team-02/pull/83) are merged. The corrected ADR and limitations describe transactional JDBC/file-backed H2, consistent with service, repository and runtime configuration. | Documentation corrected; final-revision runtime verification pending | Shú / Mareddy: retain restart and rollback evidence at the release revision; Victor: disposition | Merge commits `4444ed0` and `0bc95fb`; [limitations](../release/known-limitations.md), [A4 backend evidence](../testing/a4-backend-verification.md) |
| A5-F02 | Identity documentation / High | Merged #83 correctly describes fixed server-session demo identities. Headers cannot establish authority, but the reachable demo selector intentionally permits choosing Reviewer. | Documentation corrected; synthetic-identity limitation remains | Shú / Mareddy: final-revision authorization evidence; Victor: bounded-demo risk decision | [Controller](../../src/main/java/edu/loyola/campusconnect/controller/RequestController.java), limitations LIM-002, [#92 evidence](https://github.com/victordodan/-comp330-f26-team-02/pull/92) |
| A5-F03 | Defect/evidence accuracy / Medium | Merged #84 changes static imports only; all 14 controller tests and helpers are unchanged. The earlier allegation of four deleted non-compiling tests is unsupported by the diff. Run #41 reports 35 passing tests. Main still has starter defect records at this checkpoint; #91 proposes their replacement. | Import/CI concern resolved in reviewed evidence; register reconciliation pending #91 review/merge | Giancarlo: maintain actual findings; Victor: review disposition | [#84](https://github.com/victordodan/-comp330-f26-team-02/pull/84), merge `7270d96`; [run #41](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42613709038) |
| A5-F04 | Acceptance dispositions / High | Merged #92 identifies related automated coverage for all twelve cases and reports 35 passing tests; it explicitly records 0/12 separately documented case executions. | Open — coverage is not final acceptance | Mareddy: per-case result/evidence or blocked/deferred disposition, date, operator, environment and SHA; Giancarlo: reconcile; Victor: acceptance | [Merged acceptance evidence](https://github.com/victordodan/-comp330-f26-team-02/blob/56c0907c7c317156f1a86ac8dcc9b9f158052b9d/docs/testing/a5-acceptance-test-evidence.md); [catalog](../testing/test-cases.md) |
| A5-F05 | Final revision/release controls / High | Real Maven CI exists in `a4-validation-tests.yml`; the separate `ci.yml` starter guard is manual-only. Source/path filters skip documentation-only revisions. No A5 tag exists in the remote tag inventory reviewed at this checkpoint. | Open — final-SHA CI, independent review and release decision not established | Development/testing: obtain final-revision checks and explain skipped/manual workflows; Victor: freeze, review and tag | [Actual workflow](../../.github/workflows/a4-validation-tests.yml), [starter guard](../../.github/workflows/ci.yml); #85 remains a proposed gate |
| A5-F06 | Supply-chain assurance / High | Historical dependency inventory does not provide complete advisory, transitive-license or maintenance acceptance. #89 remains a proposed assessment, not clearance. | Open | Victor appoints technical reviewer; record advisory/license evidence, mitigations and human residual-risk decision | [Dependency assessment](../reviews/a4-build-dependency-assessment.md), [#89](https://github.com/victordodan/-comp330-f26-team-02/pull/89) |

## CI evidence boundary

[Run #41](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42613709038)
ran `mvn -B clean verify` on merge SHA
`0fcc79c29dfcdc89403d3c230f47438cb00c358c` (head
`00c034a74f22ce616f707848168909289b2db001`, base
`70d3726b64c0deafef2d770870bb14ef8f28b79b`). It reports 35 tests,
zero failures/errors/skips and successful packaging. This historical run is
not CI for the current checkpoint or a final A5 release. No new execution
is claimed by these documentation corrections.

## Disposition and coordination

1. Preserve the merged storage/identity corrections. Do not reopen the obsolete in-memory/header descriptions as current behavior.
2. Reject the unsupported test-deletion/compilation allegation; link #84's actual diff and historical CI. Do not invent a runtime defect from a misleading PR description.
3. Review #91's proposed defect register, quality summary and traceability. This reconciliation does not certify those records before they merge.
4. Record each acceptance-case disposition with evidence; automated execution may support it, but mappings alone do not grant acceptance. Do not invent a requirement for twelve additional manual runs regardless of existing coverage.
5. Complete final-revision CI/restart/concurrency evidence, non-author technical review, dependency-risk decisions and the release decision before establishing the A5 tag. Proposed owners coordinate work; assignment here is not proof of their approval.

The [current limitations](../release/known-limitations.md) retain local-store,
synthetic-identity, interface and operational boundaries. #85/#86 remain open
candidate gate/release-note work at this checkpoint; reconcile them with these
outcomes rather than assuming they are accepted final artifacts.

**Quality recommendation:** Final release decision remains undetermined. No new
runtime defect was reproduced, no new test or scan was run, and no final
acceptance or risk decision was performed by this review.

**AI disclosure:** Codex assisted with checking merged PR/source evidence and
updating these findings. Original Giancarlo attribution is retained for the
original draft; this correction does not claim his personal verification or
approval. See the correction entry in [AI-use log](../ai/ai-use-log.md).
