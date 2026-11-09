# A5 Acceptance Criteria Traceability and Evidence Matrix

**Project:** CampusConnect | **Team:** Team 2 — Codex Ramblers  
**Prepared by:** Giancarlo Herrera, Quality & Review Lead  
**Review date:** 2026-11-09  
**Status:** Draft evidence reconciliation; no new tests executed and no release acceptance granted  
**Reviewed basis:** main/A4 baseline `70d3726b64c0deafef2d770870bb14ef8f28b79b`. Review again at the eventual A5 release SHA.

## Status definitions

- **Mapped automated coverage:** A4 quality review links the case to source tests and reports passing integrated tests. This is **not** a separately executed TC record.
- **Case-specific execution:** A dated record with operator, SHA, environment, observed result and link as specified by `docs/testing/test-cases.md`. **None was found for TC-001–TC-012** in the reviewed A4 checkpoint.
- **A5 final disposition:** Pending until exact-release-SHA verification, human review and documented pass/fail/blocked/deferred decision.

## Acceptance matrix

The following 12 case-to-criterion relationships come from `docs/testing/test-cases.md` and `docs/requirements/acceptance-criteria.md`. Test-method names are from the actual test sources; some methods cover multiple cases. The mapping is a coverage crosswalk, not a new execution result.

| TC | Acceptance criterion | Expected behavior / review focus | Related automated test methods or smoke evidence | A4 evidence | Separate TC record | A5 disposition |
|---|---|---|---|---|---|---|
| TC-001 | AC-REQ-001-01 | Valid student request, Submitted state and ID | `createsTrimmedStudentOwnedRequestAndReturnsCurrentEtag`; `createsTrimmedOwnedRequestsWithDistinctIdsAndDefaults` | Mapped; A4 reported passing | Not recorded | Pending |
| TC-002 | AC-REQ-001-02 | Invalid fields rejected with no write | `rejectsInvalidCreationWithoutStoringAnything`; `rejectsInvalidInputWithoutStoringAnything`; `RequestValidatorTest boundary tests` | Mapped; A4 reported passing | Not recorded | Pending |
| TC-003 | AC-REQ-002-01 | Owner sees current status after update/reopen | `resolvingRequiresSavedNoteAndReopeningPreservesImmutableFieldsAndText`; `permitsLifecycleAndPreservesResolutionOnReopen` | Mapped; A4 reported passing | Not recorded | Pending |
| TC-004 | AC-REQ-002-02 | Owner sees latest saved note after reopen | `resolvingRequiresSavedNoteAndReopeningPreservesImmutableFieldsAndText`; `permitsLifecycleAndPreservesResolutionOnReopen` | Mapped; A4 reported passing | Not recorded | Pending |
| TC-005 | AC-REQ-002-03 | Student B cannot list/read Student A request | `studentsSeeOnlyOwnedRequestsAndCannotReadOrChangeReviewerData`; `enforcesStudentOwnershipAndSeparateReviewerOperations` | Mapped; A4 reported passing | Not recorded | Pending |
| TC-006 | AC-REQ-003-01 | Unique IDs/owner/timestamps; failed write no success | `createsTrimmedOwnedRequestsWithDistinctIdsAndDefaults`; `rollsBackInsertWhenStorageFailsBeforeConfirmation`; `unavailableStorageReturnsFailureWithoutFalseSuccessOrRecordChanges` | Mapped; A4 reported passing | Not recorded | Pending |
| TC-007 | AC-REQ-003-02 | Persist request/status/note across restart | `preservesOwnershipStatusNotesAndUniqueIdsAcrossFileStoreRestart`; `A4 packaged-process restart check` | Mapped; A4 reported passing | Not recorded | Pending |
| TC-008 | AC-REQ-004-01 | Reviewer lists and inspects both students | `reviewerCanInspectBothStudentsButCannotUseStudentOnlyRetrieval`; `enforcesStudentOwnershipAndSeparateReviewerOperations` | Mapped; A4 reported passing | Not recorded | Pending |
| TC-009 | AC-REQ-004-02 | Reject unauthorized reviewer actions and forged context | `missingInvalidAndWrongRoleContextsAreDenied`; `spoofedIdentityHeadersCannotGrantPermissionsOrOverrideTheSession`; `studentsSeeOnlyOwnedRequestsAndCannotReadOrChangeReviewerData` | Mapped; A4 reported passing | Not recorded | Pending |
| TC-010 | AC-REQ-005-01 | Allowed transitions, saved note, reopen preservation | `resolvingRequiresSavedNoteAndReopeningPreservesImmutableFieldsAndText`; `permitsLifecycleAndPreservesResolutionOnReopen` | Mapped; A4 reported passing | Not recorded | Pending |
| TC-011 | AC-REQ-005-02 | Invalid transitions/unknown ID/write failure preserve state | `unknownIdsMalformedJsonAndUnsupportedTransitionsProduceClearErrors`; `rejectsUnsupportedSameStateAndUnresolvedTransitionsWithoutChanges`; `rollsBackStatusAndNoteWhenStorageFailsAfterWrite` | Mapped; A4 reported passing | Not recorded | Pending |
| TC-012 | AC-REQ-006-01 | Save/replace note, bounds and failure rejection | `notesRespectTrimmedBoundaryAndCanBeClearedOnlyBeforeResolution`; `enforcesTrimmedNoteBoundariesAndResolvedClearingRules`; `rollsBackStatusAndNoteWhenStorageFailsAfterWrite` | Mapped; A4 reported passing | Not recorded | Pending |

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
| Final A5 test execution not verified | Implementation/test owner; Victor (gate decision) | Clean Maven/CI run linked to the frozen A5 SHA; verify PR #84 cleanup and no tests skipped. |
| Requirements/acceptance criteria still labeled Proposed | Shelby (requirements), Victor (approval) | Attributable approval or recorded deviations; do not silently change Proposed to Accepted. |
| Cross-document architecture/limitations conflict | Shu (architecture); Giancarlo (review) | Resolve H2 vs in-memory and session vs header claims in PRs #83/#86 and PR #87 before approving TC-007/TC-009. |
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
- Result: Pass / Fail / Blocked / Not Run
- Evidence link (CI/test log/screenshot):
- Defect ID / retest evidence (if applicable):
- Independent reviewer / disposition:
```

## Quality Lead recommendation

**Coverage mapping complete; A5 acceptance approval pending.** Keep the distinction between automated test-method coverage and separately witnessed acceptance-case execution. No new tests, scans or approvals were performed to prepare this matrix. Reconcile the release documentation with actual implementation and obtain human review before a Go decision.

**AI-use disclosure:** AI assisted with drafting and comparing repository artifacts. The author and independent reviewers must check accuracy and record assistance under team policy.
