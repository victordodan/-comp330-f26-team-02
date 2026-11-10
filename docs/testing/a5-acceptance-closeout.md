# A5 Acceptance Closeout — Delegated Case Dispositions

Source checkpoint: `531d54ca0f391e218752888900c4668173f75e27`. Operator: Codex automated execution, not a student witness.
Fresh JDK17/Maven clean verify passed 35 tests with no failures/errors/skips;
packaged HTTP workflow passed 36 checks and full process restart passed 5 checks.
Execution date/environment and every executed method are preserved in
[execution manifest](../../test-evidence/a5-acceptance-closeout/execution.json).
These are twelve evidence-based case records derived from one suite plus HTTP
execution, not twelve separate manual runs. No final release SHA/tag is chosen.

| Case / criterion | Behavior assessed | Fresh executed JUnit evidence (combined with HTTP records) | Proposed disposition |
|---|---|---|---|
| TC-001 / AC-REQ-001-01 | Valid student request, Submitted state and ID | `createsTrimmedStudentOwnedRequestAndReturnsCurrentEtag`, `createsTrimmedOwnedRequestsWithDistinctIdsAndDefaults` | PASS — approved under Victor’s delegated authority for the recorded classroom-demo scenario scope |
| TC-002 / AC-REQ-001-02 | Invalid fields rejected with no write | `rejectsInvalidCreationWithoutStoringAnything`, `rejectsInvalidInputWithoutStoringAnything`, `rejectsOutOfRangeBoundaries`, `rejectsUnsupportedCategories`, `acceptsMinimumAndMaximumBoundaries`, `rejectsMissingAndWhitespaceOnlyFields`, `acceptsAndTrimsSurroundingCategoryWhitespace` | PASS — approved under Victor’s delegated authority for the recorded classroom-demo scenario scope |
| TC-003 / AC-REQ-002-01 | Owner sees current status after update/reopen | `resolvingRequiresSavedNoteAndReopeningPreservesImmutableFieldsAndText`, `permitsLifecycleAndPreservesResolutionOnReopen` | PASS — approved under Victor’s delegated authority for the recorded classroom-demo scenario scope |
| TC-004 / AC-REQ-002-02 | Owner sees latest saved note after reopen | `resolvingRequiresSavedNoteAndReopeningPreservesImmutableFieldsAndText`, `permitsLifecycleAndPreservesResolutionOnReopen` | PASS — approved under Victor’s delegated authority for the recorded classroom-demo scenario scope |
| TC-005 / AC-REQ-002-03 | Student B cannot list/read Student A request | `studentsSeeOnlyOwnedRequestsAndCannotReadOrChangeReviewerData`, `enforcesStudentOwnershipAndSeparateReviewerOperations`, `unknownIdsMalformedJsonAndUnsupportedTransitionsProduceClearErrors` | PASS — approved under Victor’s delegated authority for the recorded classroom-demo scenario scope |
| TC-006 / AC-REQ-003-01 | Unique IDs/owner/timestamps; failed write no success | `createsTrimmedOwnedRequestsWithDistinctIdsAndDefaults`, `rollsBackInsertWhenStorageFailsBeforeConfirmation`, `unavailableStorageReturnsFailureWithoutFalseSuccessOrRecordChanges` | PASS — approved under Victor’s delegated authority for the recorded classroom-demo scenario scope |
| TC-007 / AC-REQ-003-02 | Persist request/status/note across restart | `preservesOwnershipStatusNotesAndUniqueIdsAcrossFileStoreRestart` | PASS — approved under Victor’s delegated authority for the recorded classroom-demo scenario scope |
| TC-008 / AC-REQ-004-01 | Reviewer lists and inspects both students | `reviewerCanInspectBothStudentsButCannotUseStudentOnlyRetrieval`, `enforcesStudentOwnershipAndSeparateReviewerOperations` | PASS — approved under Victor’s delegated authority for the recorded classroom-demo scenario scope |
| TC-009 / AC-REQ-004-02 | Reject unauthorized reviewer actions and forged context | `missingInvalidAndWrongRoleContextsAreDenied`, `spoofedIdentityHeadersCannotGrantPermissionsOrOverrideTheSession`, `studentsSeeOnlyOwnedRequestsAndCannotReadOrChangeReviewerData`, `suppliedServerFieldsCannotForgeOwnerOrGrantReviewerRole`, `legacyClientStudentIdIsIgnoredWhenAssigningTheSessionOwner` | PASS — approved under Victor’s delegated authority for the recorded classroom-demo scenario scope |
| TC-010 / AC-REQ-005-01 | Allowed transitions, saved note, reopen preservation | `resolvingRequiresSavedNoteAndReopeningPreservesImmutableFieldsAndText`, `permitsLifecycleAndPreservesResolutionOnReopen` | PASS — approved under Victor’s delegated authority for the recorded classroom-demo scenario scope |
| TC-011 / AC-REQ-005-02 | Invalid transitions/unknown ID/write failure preserve state | `unknownIdsMalformedJsonAndUnsupportedTransitionsProduceClearErrors`, `rejectsUnsupportedSameStateAndUnresolvedTransitionsWithoutChanges`, `rollsBackStatusAndNoteWhenStorageFailsAfterWrite` | PASS — approved under Victor’s delegated authority for the recorded classroom-demo scenario scope |
| TC-012 / AC-REQ-006-01 | Save/replace note, bounds and failure rejection | `notesRespectTrimmedBoundaryAndCanBeClearedOnlyBeforeResolution`, `enforcesTrimmedNoteBoundariesAndResolvedClearingRules`, `rollsBackStatusAndNoteWhenStorageFailsAfterWrite` | PASS — approved under Victor’s delegated authority for the recorded classroom-demo scenario scope |

Raw [workflow](../../test-evidence/a5-acceptance-closeout/workflow.jsonl) and
[restart](../../test-evidence/a5-acceptance-closeout/restart.jsonl) results retain
actual timestamps and synthetic response data. Related test assertions were
reviewed in source: validator min/max/whitespace/category variants; owner list,
direct/unknown lookup and both reviewer details; forged body/session contexts;
transaction rollback after insert/status/note write; saved-note lifecycle,
concurrency and note boundaries. The previously missing demo variants are
supported by the freshly executed unit/integration assertions, not falsely
reported as HTTP steps.

Restart covered a saved In Progress record with its note/fields/ETag; other
states and backup restoration were not separately exercised. Storage failures
were injected in the service/controller tests, not the packaged external HTTP
demo. The combined automated evidence is accepted for these case dispositions under
Victor Dodan’s explicit delegation to Codex in this session. Preserve historical
Not Run counts as historical: these are newer evidence-backed dispositions,
not twelve newly witnessed manual runs. Named independent human review, broader
requirements-baseline approval and final release authorization remain separate.

## Approval provenance and baseline

Decision recorded by Codex under Victor Dodan's explicit session authorization
to approve case dispositions when aligned with project goals and expectations.
All twelve PASS decisions use the actual source/test/HTTP evidence above and
the bounded synthetic-data HTTP scope. No approval of production use, dependencies,
backup recovery, additional features or final release is implied. This is not
an independent non-author human signoff and does not claim a student's personal
execution. Application acceptance baseline is the recorded source SHA above;
final submitted repository/tag identity must be recorded after integration.
