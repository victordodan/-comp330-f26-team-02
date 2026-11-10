# A5 Acceptance Criteria Traceability and Evidence Matrix

> Current decision (2026-11-10): [Victor-authorized conditional classroom release](../release/a5-release-decision.md).
> TC-001–TC-012 accepted within documented scope; residual risks explicitly
> accepted/deferred. Historical pending decisions below retain their original
> context. Final integrated human review is explicitly deferred for this conditional
> baseline under Victor’s release authority; the course condition is unfulfilled.
> Exact-main CI is required before tagging; no production/security/legal clearance.

**Project:** CampusConnect | **Team:** Team 2 — Codex Ramblers
**Original preparer:** Giancarlo Herrera, Quality & Review Lead
**Status:** Draft evidence reconciliation; no new tests executed and no release acceptance granted
**Reviewed checkpoint:** `4ff2f0f12e636f177ec25202b8029498e613afde`; merged #92 is the execution report, while this document supplies the AC-to-test crosswalk. Final release-SHA review remains pending.

## Latest case dispositions

The rows below describe historical evidence. The newer
[proposed acceptance closeout](a5-acceptance-closeout.md) records fresh
2026-11-10 automated evidence at `531d54ca0f391e218752888900c4668173f75e27`.
TC-001–TC-012 remain proposed PASS pending named human accountability and
acceptance confirmation. Mareddy's [PR #109](https://github.com/victordodan/-comp330-f26-team-02/pull/109)
reviews CI #50 at `000c0f89db51a64956566fc82fc7742973ce1e7b`; its Pending
dispositions remain compatible. See the closeout for TC-002/TC-007 limitations,
artifact verification and explicitly OPEN independent-review, risk, release
and tag gates. Historical records and dates are preserved.

## Historical status definitions

- **Mapped automated coverage:** E1/E2 below report passing tests related to the case. This is **not** a separately executed TC record.
- **Case-specific execution:** A dated record with operator, SHA, environment, observed result and link as specified by `docs/testing/test-cases.md`. Merged #92 records **0/12 separate case-specific executions**; this correction does not create those records.
- **A5 final disposition:** Pending until exact-release-SHA verification, human review and documented pass/fail/blocked/deferred decision.

## Acceptance matrix

The following 12 case-to-criterion relationships come from `docs/testing/test-cases.md` and `docs/requirements/acceptance-criteria.md`. Test-method names are from the actual test sources; some methods cover multiple cases. The mapping is a coverage crosswalk, not a new execution result or proof that all scenario steps are covered. Automated evidence may support a case disposition; this does not impose twelve redundant manual runs.

| TC | Acceptance criterion | Expected behavior / review focus | Related automated test methods or smoke evidence | Related automated evidence | Separate TC record | A5 disposition |
|---|---|---|---|---|---|---|
| TC-001 | AC-REQ-001-01 | Valid student request, Submitted state and ID | `createsTrimmedStudentOwnedRequestAndReturnsCurrentEtag`; `createsTrimmedOwnedRequestsWithDistinctIdsAndDefaults` | E1/E2: related tests passed | Not recorded | Pending |
| TC-002 | AC-REQ-001-02 | Invalid fields rejected with no write | `rejectsInvalidCreationWithoutStoringAnything`; `rejectsInvalidInputWithoutStoringAnything`; `rejectsOutOfRangeBoundaries`; `rejectsUnsupportedCategories` | E1/E2: related tests passed | Not recorded | Pending |
| TC-003 | AC-REQ-002-01 | Owner sees current status after update/reopen | `resolvingRequiresSavedNoteAndReopeningPreservesImmutableFieldsAndText`; `permitsLifecycleAndPreservesResolutionOnReopen` | E1/E2: related tests passed | Not recorded | Pending |
| TC-004 | AC-REQ-002-02 | Owner sees latest saved note after reopen | `resolvingRequiresSavedNoteAndReopeningPreservesImmutableFieldsAndText`; `permitsLifecycleAndPreservesResolutionOnReopen` | E1/E2: related tests passed | Not recorded | Pending |
| TC-005 | AC-REQ-002-03 | Student B cannot list/read Student A request | `studentsSeeOnlyOwnedRequestsAndCannotReadOrChangeReviewerData`; `enforcesStudentOwnershipAndSeparateReviewerOperations` | E1/E2: related tests passed | Not recorded | Pending |
| TC-006 | AC-REQ-003-01 | Unique IDs/owner/timestamps; failed write no success | `createsTrimmedOwnedRequestsWithDistinctIdsAndDefaults`; `rollsBackInsertWhenStorageFailsBeforeConfirmation`; `unavailableStorageReturnsFailureWithoutFalseSuccessOrRecordChanges` | E1/E2: related tests passed | Not recorded | Pending |
| TC-007 | AC-REQ-003-02 | Persist request/status/note across restart | `preservesOwnershipStatusNotesAndUniqueIdsAcrossFileStoreRestart`; A4 packaged-process restart report (historical) | E1/E2: related tests passed | Not recorded | Pending |
| TC-008 | AC-REQ-004-01 | Reviewer lists and inspects both students | `reviewerCanInspectBothStudentsButCannotUseStudentOnlyRetrieval`; `enforcesStudentOwnershipAndSeparateReviewerOperations` | E1/E2: related tests passed | Not recorded | Pending |
| TC-009 | AC-REQ-004-02 | Reject unauthorized reviewer actions and forged context | `missingInvalidAndWrongRoleContextsAreDenied`; `spoofedIdentityHeadersCannotGrantPermissionsOrOverrideTheSession`; `studentsSeeOnlyOwnedRequestsAndCannotReadOrChangeReviewerData` | E1/E2: related tests passed | Not recorded | Pending |
| TC-010 | AC-REQ-005-01 | Allowed transitions, saved note, reopen preservation | `resolvingRequiresSavedNoteAndReopeningPreservesImmutableFieldsAndText`; `permitsLifecycleAndPreservesResolutionOnReopen` | E1/E2: related tests passed | Not recorded | Pending |
| TC-011 | AC-REQ-005-02 | Invalid transitions/unknown ID/write failure preserve state | `unknownIdsMalformedJsonAndUnsupportedTransitionsProduceClearErrors`; `rejectsUnsupportedSameStateAndUnresolvedTransitionsWithoutChanges`; `rollsBackStatusAndNoteWhenStorageFailsAfterWrite` | E1/E2: related tests passed | Not recorded | Pending |
| TC-012 | AC-REQ-006-01 | Save/replace note, bounds and failure rejection | `notesRespectTrimmedBoundaryAndCanBeClearedOnlyBeforeResolution`; `enforcesTrimmedNoteBoundariesAndResolvedClearingRules`; `rollsBackStatusAndNoteWhenStorageFailsAfterWrite` | E1/E2: related tests passed | Not recorded | Pending |

## Executed CI evidence and revision boundaries

- **E1 — PR #84 historical verification:** [run #41](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42613709038), `mvn -B clean verify`, 35 tests, zero failures/errors/skips. Tested merge `0fcc79c29dfcdc89403d3c230f47438cb00c358c`; head `00c034a74f22ce616f707848168909289b2db001`; base `70d3726b64c0deafef2d770870bb14ef8f28b79b`. Merged #92 records this evidence.
- **E2 — PR #95 integration verification:** [run #4](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42746026082) / [job logs](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42746026082/job/129598203601), `mvn -B clean verify`, 35 tests, zero failures/errors/skips and successful packaging. Tested merge `5d791a7b657209a6c688251149db92828b313606`; head `e724e84af257a69237d51f8b6dab3d1927bd8cfb`; base `6c6584162deb4c396f162670b39e4d48fdd548da`.

E2 used the consolidated CI configuration from merged #93. #95 subsequently
merged as `4ff2f0f12e636f177ec25202b8029498e613afde`. The tested synthetic
merge and final main commit are distinct revisions; no final A5 tag or release
approval is inferred. No new build was run to prepare this correction.

## Coverage limits requiring disposition

TC-007's automated service test shuts down/reopens a file store with an In Progress
record and a saved note. It supports data retention but is not an independent
packaged-application restart execution for every status/session configuration.
The A4 process-restart report is separate historical evidence. The team should
identify remaining scenario gaps and record an evidence-backed acceptance result
or approved deferral at the chosen release revision.

For other cases, test names identify relevant assertions; the table is not a
claim that the full scenario passed independently. No case is pre-approved.

## Test source and historical execution links

- [Controller integration tests](../../src/test/java/edu/loyola/campusconnect/controller/RequestControllerTest.java): session ownership, invalid input, reviewer access, lifecycle, notes, optimistic preconditions and failure responses.
- [Workflow service tests](../../src/test/java/edu/loyola/campusconnect/service/WorkflowServiceTest.java): IDs, ownership, transitions, rollback, concurrent writes and file-store restart.
- [Validator unit tests](../../src/test/java/edu/loyola/campusconnect/validation/RequestValidatorTest.java): required fields, categories, trimmed boundaries.
- [A4 backend verification](a4-backend-verification.md): reports 35 passing tests in a local sequential integration simulation and packaged HTTP/restart checks; this is historical, not a run at the final A5 SHA.
- [A4 final quality verification](a4-final-quality-verification.md): explicitly maps all 12 cases to automated coverage and notes **zero separately dated case-by-case execution records**.
- [Main CI run 41000100733](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/41000100733): A4 application-code merge success at `d2bf5bfded8fcc03f3509394fa215b7f223efd94`, not the eventual A5 revision.

## Outstanding acceptance actions

| Gap | Owner / reviewer | Required evidence before A5 sign-off |
|---|---|---|
| TC-001–TC-012 have no individually dated execution record | Mareddy (test execution); Giancarlo (evidence review) | For each TC: date, operator, exact SHA, environment, actual result, pass/fail/blocked/deferred, link and defect/retest if applicable. |
| Final A5 test execution not verified | Implementation/test owner; Victor (gate decision) | Use E1/E2 as existing evidence, then link verification to the selected final A5 SHA. #84 is merged and changes imports only; no tests were deleted. |
| Requirements/acceptance criteria still labeled Proposed | Shelby (requirements), Victor (approval) | Attributable approval or recorded deviations; do not silently change Proposed to Accepted. |
| Corrected architecture / remaining runtime assurance | Shú (architecture); Giancarlo (review) | Merged #81/#83/#94/#95 document JDBC/H2 and session-based demo identity; #87/#91 reconcile findings. Retain final-revision restart/authorization evidence and synthetic-selector limitations. |
| Dependency/security and release sign-off | Technical reviewer and Victor | Advisory/license decisions, independent reviewer sign-off and final release decision recorded at the exact SHA. |

## Per-case execution record template

Copy once for each actual TC execution; **do not prefill Pass**.

```markdown
### TC-___ — [scenario]
- Acceptance criterion:
- Commit SHA / build:
- Execution date and operator:
- Environment / synthetic test data:
- Steps performed:
- Actual observed result:
- Evidence type: Automated / Manual / Mixed
- Result/disposition: Pass / Fail / Blocked / Deferred / Not Run
- Uncovered scenario steps and deferral owner/approval:
- Evidence link (CI/test log/screenshot):
- Defect ID / retest evidence (if applicable):
- Independent reviewer / disposition:
```

## Quality Lead recommendation

**All twelve cases mapped to related tests; full scenario completeness and final acceptance remain pending.** Keep the distinction between automated test-method coverage and case-specific acceptance dispositions. No new tests, scans or approvals were performed to prepare this matrix. Reconcile the release documentation with actual implementation and obtain human review before a Go decision.

**AI-use disclosure:** Codex assisted this correction by comparing current source, criteria, merged #92 and E1/E2 evidence. Original Giancarlo attribution does not assert his personal verification of the correction. No new tests, acceptance decisions or human approvals were performed; see [AI-use log](../ai/ai-use-log.md).

## Latest-main automated verification checkpoint — 2026-11-10 UTC

[Main CI run 42762605137](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42762605137) completed **successfully** for `000c0f89db51a64956566fc82fc7742973ce1e7b`. This adds current-main workflow evidence to E1/E2, **not** twelve new TC-specific execution records. The 12 mapped cases remain **Pending** final disposition, and the previously documented 0/12 separate dated case executions is unchanged. Test counts and complete scenario-step coverage were not independently audited from the new run.

**For Mareddy and the authorized acceptance reviewer:** reconcile each TC-001–TC-012 against the run's reports, exact test assertions and uncovered steps; record Pass / Fail / Blocked / Deferred with evidence and accountable reviewer. Automated evidence can suffice when it covers the case; do not manufacture redundant manual runs or automatically mark all cases Pass. Giancarlo's quality role is to check traceability and gaps, not to impersonate test execution or approve the release. ChatGPT assisted this evidence note; no new execution or approval occurred.

## Mareddy — A5 acceptance evidence review (2026-11-10)

**Reviewer:** Venkata Aravind Mareddy | **Scope:** TC-001–TC-012 | **Evidence type:** Source-code review and existing automated CI results; no new test execution.

**CI checkpoint:** [Main run #50](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42762605137), commit `000c0f89db51a64956566fc82fc7742973ce1e7b`. The `mvn -B clean verify` logs report **35 tests run, 0 failures, 0 errors, 0 skipped; BUILD SUCCESS**. This is a main-branch checkpoint, not yet the confirmed final A5 release SHA.

### Case-by-case evidence reconciliation

| Case | Source/test evidence reviewed | Finding / remaining limitation |
|---|---|---|
| TC-001 | Request controller and service creation tests | Valid creation, student ownership, Submitted status and returned ID have related automated assertions. |
| TC-002 | Validator boundary tests; invalid-create controller tests; `WorkflowService` and `ApiExceptionHandler` | Invalid input is rejected before insert; HTTP 400 returns a structured `BAD_INPUT` error with validator messages. Reviewed HTTP tests do not directly assert the exact error-message content. |
| TC-003 | Workflow lifecycle and owner-read tests | Current status after reviewer updates/reopening has related automated assertions. |
| TC-004 | Note-update and owner-read tests | Saved and preserved resolution-note behavior has related automated assertions. |
| TC-005 | Student-ownership and isolation tests | Cross-student listing and direct-read restrictions have related automated assertions. |
| TC-006 | Creation/defaults, unique-ID and failed-storage tests | IDs, ownership, timestamps and no false success on failed writes have related automated assertions. |
| TC-007 | File-store restart service test; historical A4 packaged-process restart report | Persistence across file-store reopening is automated; packaged-application restart evidence is historical and not verified at the final A5 release SHA. |
| TC-008 | Reviewer list/read authorization tests | Reviewer access to both students' requests has related automated assertions. |
| TC-009 | Missing/invalid identity, forged-header and student-role tests | Unauthorized reviewer operations and identity spoofing have related automated assertions. |
| TC-010 | Allowed-transition, saved-note and reopening tests | Submitted → In Progress → Resolved and reopening behavior have related automated assertions. |
| TC-011 | Invalid-transition, missing-note, unknown-ID and rollback tests | Rejected operations and preservation of stored state have related automated assertions. |
| TC-012 | Note-update, length-boundary, resolved-state and rollback tests | Save/replace, boundary enforcement and rejection without unintended mutation have related automated assertions. |

**Disposition:** All 12 cases remain **Pending** final acceptance disposition. The 35 passing automated tests are not 12 separately dated TC executions. This review does not assert that every scenario step was independently executed or that release approval was granted.

**Follow-up:** Confirm the final A5 release SHA with Victor; verify the corresponding CI run; resolve or explicitly accept the TC-002 response-message assertion limitation and TC-007 final-revision restart-evidence limitation; obtain Giancarlo's evidence review and the authorized case-level dispositions. No tests, scans or release approvals were performed as part of this documentation review.

## Addendum — reconciliation with PR #108 (2026-11-10)

This addendum supplements Mareddy's historical main-CI review above. It does not replace the earlier checkpoint or claim that Mareddy personally executed the newer tests.

**Earlier checkpoint:** Main CI run #50, commit `000c0f89db51a64956566fc82fc7742973ce1e7b`, reported 35 tests passing. The original Pending findings above describe the evidence available at that checkpoint.

**Newer evidence:** [PR #108](https://github.com/victordodan/-comp330-f26-team-02/pull/108) records fresh automated execution against application source `531d54ca0f391e218752888900c4668173f75e27`, operated by Codex. Its `execution.json` manifest records JDK Temurin 17.0.17+10, Maven 3.9.9, build completion at **2026-11-10 05:45:26 UTC**, and **35 JUnit PASS results**. It also reports **36 packaged HTTP workflow checks** and **5 post-restart HTTP checks**. These are combined automated evidence, not twelve separate witnessed manual executions. The tested application source SHA is not yet the final integrated release SHA/tag.

**TC-002 reconciliation:** The newer recorded HTTP workflow returned `400 BAD_INPUT` with the explanatory message `Title must contain 5 to 100 characters.` for an invalid title and recorded no stored request afterward. Fresh validator tests also cover input boundaries, whitespace and categories. The previous concern about absence of observed explanatory HTTP error content is addressed for this exercised scenario; this does not assert every invalid variant was exercised over HTTP.

**TC-007 reconciliation:** The newer `restart.jsonl` records five successful post-restart HTTP checks, beginning at **2026-11-10 05:46:48 UTC**. After reselecting Student A and Reviewer identities, the saved request was retrievable with its original ID, owner, title, description, timestamps, In Progress status, revised resolution note, and ETag `"5"`; the reviewer could list persisted records and a new request could be created. This supports packaged-process restart persistence for the recorded scenario, not backup recovery or every lifecycle state.

**Case dispositions and authority:** `docs/testing/a5-acceptance-closeout.md` in PR #108 records **12 PASS dispositions under Victor Dodan's stated delegation to Codex** for the bounded classroom-demo scenarios. These are later proposed/recorded delegated decisions and do not retroactively change the historical Pending checkpoint above. The accompanying `execution.json` still states `"acceptance_approval": "Pending human decision; not a release freeze"`. The decision provenance and meaning of this discrepancy require explicit confirmation by Victor and Giancarlo. No independent non-author human review, final release authorization, or approval of production/dependency/backup-recovery scope is established by these records.

**Next gate:** Giancarlo to reassess the reconciliation and confirm any remaining evidence/authority issues; Victor to identify the final integrated release SHA and document the final release decision. This addendum is a documentary review only: Mareddy did not execute additional tests, independently approve the cases, or authorize release.
