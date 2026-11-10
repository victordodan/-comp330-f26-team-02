# CampusConnect — A5 Requirements and Release Traceability

**Team:** Codex Ramblers · **Quality coordination:** Giancarlo Herrera
**Reviewed checkpoint:** `56c0907c7c317156f1a86ac8dcc9b9f158052b9d`
**Historical A4 tag:** `a4-construction-integration` at `70d3726b64c0deafef2d770870bb14ef8f28b79b`
**Final A5 SHA/tag:** Not frozen; this ledger does not grant acceptance.

Requirements and acceptance criteria remain **Proposed** in their source files.
Implementation, passing automated tests, requirements approval and final release
acceptance are distinct states. The tables below restore planning/decision
relationships and link actual implementation evidence without inventing issues.

## Requirements, acceptance and implementation

P67/P71/P72 refer to exact branches/commits in the implementation index below.
V41 refers to the historical CI run; D1–D6 are proposed HTTP demonstration
steps, not completed rehearsals. RN refers to candidate release-note work.

| Requirement | Acceptance criteria / cases | Issue → implementation | Automated evidence / final disposition | Planned demo → release |
|---|---|---|---|---|
| REQ-001 — submission/validation | AC-REQ-001-01/02; TC-001/002 | Issue #66 → P67 for validation only; P71/P72 integrate submission (separate feature issue unrecorded) | Validator/controller/service tests; V41 passed; case acceptance pending | D1 → RN → final tag pending |
| REQ-002 — own status/notes/isolation | AC-REQ-002-01/02/03; TC-003/004/005 | Feature issue unrecorded → P71/P72 | Ownership/read/lifecycle tests; V41 passed; case acceptance pending | D2 → RN → final tag pending |
| REQ-003 — storage/ID/metadata | AC-REQ-003-01/02; TC-006/007 | Feature issue unrecorded → P71/P72 | Service rollback/file-restart and controller metadata/failure tests; V41 passed; packaged final-revision acceptance pending | D3 → RN → final tag pending |
| REQ-004 — reviewer access | AC-REQ-004-01/02; TC-008/009 | Feature issue unrecorded → P71/P72 | Reviewer list/detail and denied-role/header tests; V41 passed; case acceptance pending | D4 → RN → final tag pending |
| REQ-005 — lifecycle | AC-REQ-005-01/02 and cross-cutting AC-REQ-004-02; TC-010/011 and TC-009 | Feature issue unrecorded → P71/P72 | Lifecycle/note-before-resolution, stale/concurrent writes and rollback tests; V41 passed; case acceptance pending | D5 → RN → final tag pending |
| REQ-006 — note management | AC-REQ-006-01 and cross-cutting AC-REQ-004-02; TC-012 and TC-009 | Feature issue unrecorded → P71/P72 | Note boundary/clearing/preservation and write-failure tests; V41 passed; case acceptance pending | D6 → RN → final tag pending |

[Requirements](../requirements/requirements.md),
[criteria](../requirements/acceptance-criteria.md),
[test catalog](../testing/test-cases.md), and
[merged #92 evidence at the reviewed checkpoint](https://github.com/victordodan/-comp330-f26-team-02/blob/56c0907c7c317156f1a86ac8dcc9b9f158052b9d/docs/testing/a5-acceptance-test-evidence.md)
provide the detailed source. All twelve cases have related automated coverage;
0/12 separate case-specific execution records are established by #92. Obtain
actual case dispositions, date, operator, environment, revision and evidence,
or explicit blocked/deferred decisions. Additional manual work should address
actual scenario gaps rather than duplicate automated checks without purpose.

## Implementation / branch / commit index

These PRs are merged. Their original branch descriptions may retain historical
pre-merge wording; the merge commits below establish integration, not final
independent human acceptance.

| Key | Issue / scope | Branch → PR | Reviewed head → integrated merge | Evidence |
|---|---|---|---|---|
| P67 | [#66](https://github.com/victordodan/-comp330-f26-team-02/issues/66): request validation only | `a4-aravind-request-validation` → [#67](https://github.com/victordodan/-comp330-f26-team-02/pull/67) | `90dbb331b0a0576c354564c863d7aa6b75a11d00` → `c43784fc521c9348094c16fda81e89ea81578003` | [Original validator evidence](../testing/a4-request-validation-evidence.md); six original tests, not the complete later suite |
| P71 | REQ-001–006 service/persistence; separate issue not recorded | `sperez-808-patch-12` → [#71](https://github.com/victordodan/-comp330-f26-team-02/pull/71) | `c9c5d19b4144c14c28daf5d5614f8a2e9c8c2790` → `a844ce759b802ad23152d0c86995dde4ed47933e` | [WorkflowService](../../src/main/java/edu/loyola/campusconnect/service/WorkflowService.java), [service tests](../../src/test/java/edu/loyola/campusconnect/service/WorkflowServiceTest.java), [repository](../../src/main/java/edu/loyola/campusconnect/repository/JdbcRequestRepository.java) |
| P72 | REQ-001–006 HTTP/session integration; separate issue not recorded | `sperez-808-patch-13` → [#72](https://github.com/victordodan/-comp330-f26-team-02/pull/72) | `ef15ad6493045dc425febe162b4f33f0d9204935` → `d2bf5bfded8fcc03f3509394fa215b7f223efd94` | [Controller](../../src/main/java/edu/loyola/campusconnect/controller/RequestController.java), [controller tests](../../src/test/java/edu/loyola/campusconnect/controller/RequestControllerTest.java), [HTTP guide](../testing/a4-http-api.md) |

Build/bootstrap prerequisites are merged [#76](https://github.com/victordodan/-comp330-f26-team-02/pull/76)
(`a0e8ad0`), [#73](https://github.com/victordodan/-comp330-f26-team-02/pull/73)
(`a69169e`) and [#77](https://github.com/victordodan/-comp330-f26-team-02/pull/77)
(`c9be063`). Their scopes are build/dependencies, startup and HTTP smoke testing;
they do not independently establish all workflow acceptance cases. Issue #66
must not be repurposed as the issue for all six requirements. Missing feature
issue links remain a traceability gap for the team to resolve honestly.

## CI and A5 integration evidence

| Key | Actual evidence | Boundary |
|---|---|---|
| V41 | [Run #41](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42613709038) / [logs](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42613709038/job/129148251587) / [reports](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42613709038/artifacts/13671781417): `mvn -B clean verify`, 35 tests, zero failures/errors/skips, packaging success | Tested merge `0fcc79c29dfcdc89403d3c230f47438cb00c358c`, head `00c034a74f22ce616f707848168909289b2db001`, base `70d3726b64c0deafef2d770870bb14ef8f28b79b`; not final A5 SHA |
| Corrected persistence/limitations | Merged #81 (`4444ed0`) and #83 (`0bc95fb`) | Documentation corrections, not new runtime fixes or release approval |
| Import cleanup | Merged #84 (`7270d96`); branch `sperez-808-patch-20`, head `00c034a74f22ce616f707848168909289b2db001` | Imports only; no test deletion |
| Acceptance evidence | Merged #92 (`56c0907`), corrected head `c9c38f4d370ca17f6bf56edc4bf623c96707a5b9`, branch `a5-aravind-acceptance-test-evidence` | Historical execution report and mapping, not twelve separate acceptance results |

The real [Maven workflow](../../.github/workflows/a4-validation-tests.yml)
uses source/POM/workflow path filters. Documentation-only revisions may not
trigger it. The separate [starter guard](../../.github/workflows/ci.yml) is
manual-only. Obtain final-SHA CI evidence and explain skipped/unused checks;
do not describe this repository as lacking real CI.

## Preserved planning and architecture relationships

These are planning relationships from the prior ledger, not new completion,
reassignment or re-estimation decisions. Task-plan statuses can lag merged
implementation; acceptance must come from actual evidence.

| Requirement | Scope / tasks | Planning owners | Estimates / risk | Logical contracts |
|---|---|---|---|---|
| REQ-001 | SCP-001; TASK-010/013 | Shelby requirements; Shú implementation | EST-002/004; R-004 | API-001/007 |
| REQ-002 | SCP-006; TASK-010/013 | Shelby requirements; Shú implementation | EST-002/004; R-004 | API-002/007 |
| REQ-003 | SCP-002; TASK-010/013 | Shelby requirements; Shú implementation | EST-002/004; R-004 | API-001/006 |
| REQ-004 | SCP-003; TASK-010/013 | Shelby requirements; Shú implementation | EST-002/004; R-004 | API-003/007 |
| REQ-005 | SCP-004; TASK-010/013 | Shelby requirements; Shú implementation | EST-002/004; R-004 | API-004/006/007 |
| REQ-006 | SCP-005; TASK-010/013 | Shelby requirements; Shú implementation | EST-002/004; R-004 | API-005/006/007 |

Source: [scope](scope.md), [task plan](task-plan.md), [estimates](estimates.md),
[risk register](risk-register.md), [API contracts](../architecture/api-contracts.md).
TASK-014 covers review/CI; TASK-015 covers testing/defects/release evidence;
TASK-016 covers presentation, with EST-004/005 as recorded in the task plan.
These links preserve planned responsibility without claiming unrecorded work.

- [ADR-001](../decisions/ADR-001-defer-production-technology-stack.md): historical production-stack deferral.
- [ADR-002](../decisions/ADR-002-cycle-1-technology-stack.md): accepted local Cycle 1 stack, not permanent production approval.
- [ADR-003](../decisions/ADR-003-request-consistency.md): accepted transaction/version design.
- [ADR-004](../decisions/ADR-004-local-persistence-and-recovery.md): accepted retention/recovery expectations; restoration testing is separate.
- [Merged ADR-0005 clarification](https://github.com/victordodan/-comp330-f26-team-02/blob/56c0907c7c317156f1a86ac8dcc9b9f158052b9d/docs/decisions/ADR-0005-in-memory-persistence.md): JDBC/H2 implementation clarification; does not invent approval of an earlier in-memory decision.

Historical design evidence includes PRs #43 (contracts/components), #44
(test strategy), #46 (requirements), #47 (data/context), #48 (architecture)
and #45 (security review). The [complete prior ledger](https://github.com/victordodan/-comp330-f26-team-02/blob/70d3726b64c0deafef2d770870bb14ef8f28b79b/docs/planning/traceability.md)
is preserved at the A4 checkpoint; its old implementation-pending assertions
are superseded by this evidence, not copied as current status.

## Planned demo and release chain

These proposed D labels make the next link explicit; they are not existing
approved demo-script steps or executed demonstrations.

| Planned step | HTTP behavior / expected result | Requirements / evidence |
|---|---|---|
| D1 | Select Student A; submit valid/invalid input; confirm ID and no write on rejection | REQ-001; P67/P71/P72; TC-001/002 |
| D2 | Owner sees latest status/note; Student B cannot list/read A's record | REQ-002; P71/P72; TC-003/004/005 |
| D3 | Inspect managed IDs/metadata; restart same store and reselect identity | REQ-003; P71/P72; TC-006/007; failure injection requires suitable test environment |
| D4 | Reviewer lists/inspects both students; deny student reviewer operations | REQ-004; P71/P72; TC-008/009 |
| D5 | In Progress → saved note → Resolved → reopen; reject invalid/stale writes | REQ-005; P71/P72; TC-010/011; concurrency/rollback use test evidence |
| D6 | Save/replace note, check 2,000-character boundary and reject clearing while Resolved | REQ-006; P71/P72; TC-012 |

Use [HTTP guide](../testing/a4-http-api.md) for implemented operations. The
[demo script](../release/demo-script.md) remains starter content at the checkpoint;
replace it with reviewed/rehearsed steps and evidence before treating these
links as complete. RN = [candidate release-note PR #86](https://github.com/victordodan/-comp330-f26-team-02/pull/86),
not a merged release claim. [Main release notes](../release/release-notes.md)
still contain starter content. Final demo evidence, accepted scope, release-note
section links and the approved tag/SHA remain open for every requirement.

## Remaining decisions and workflow links

- Requirements/AC approval: Shelby and Victor; preserve Proposed until attributable acceptance or deferral.
- Missing feature issues: development owners/Victor; link real issues or record the gap without inventing historical assignments.
- Acceptance/runtime evidence: Mareddy and Shú; Giancarlo reconciles per-case outcomes and limitations.
- Independent final review, risk decision and tag: Victor appoints non-author reviewer and records exact reviewed SHA and decision.
- Quality records: this PR proposes [finding log](../quality/defect-log.md) and [review](../quality/defect-review.md); [#87](https://github.com/victordodan/-comp330-f26-team-02/pull/87) remains a proposed reconciliation.
- Candidate gate/matrix/security/disclosure: #85/#88/#89/#90 remain open proposals at this checkpoint. No scans, complete license clearance or release signoffs are inferred from them.
- [AI-use log](../ai/ai-use-log.md): correction disclosure added here; human verification remains pending. Codex assisted source/PR/evidence comparison and writing, without new runtime tests, scans or approvals.
