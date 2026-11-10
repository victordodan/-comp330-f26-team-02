# A5 Acceptance Closeout — Scoped Case Dispositions

> Current authority: [release decision](../release/a5-release-decision.md).
> Victor explicitly delegated final acceptance/risk/release decisions after
> the proposed-PASS review. All twelve cases are accepted within the stated
> scope. Historical proposed-status decisions are superseded by the current
> human-accountability section and release decision. Independent
> integrated review remains separate; no Mareddy/Giancarlo acceptance is invented.


Source checkpoint: `531d54ca0f391e218752888900c4668173f75e27`. Operator: Codex automated execution, not a student witness.
Fresh JDK17/Maven clean verify passed 35 tests with no failures/errors/skips;
packaged HTTP workflow passed 36 checks and full process restart passed 5 checks.
Execution date/environment and every executed method are preserved in
[execution manifest](../../test-evidence/a5-acceptance-closeout/execution.json).
These are twelve evidence-based case records derived from one suite plus HTTP
execution, not twelve separate manual runs. No final release SHA/tag is chosen.

| Case / criterion | Behavior assessed | Fresh executed JUnit evidence (combined with HTTP records) | Current disposition |
|---|---|---|---|
| TC-001 / AC-REQ-001-01 | Valid student request, Submitted state and ID | `createsTrimmedStudentOwnedRequestAndReturnsCurrentEtag`, `createsTrimmedOwnedRequestsWithDistinctIdsAndDefaults` | PASS — Victor-authorized delegated decision; bounded scope, 2026-11-10 |
| TC-002 / AC-REQ-001-02 | Invalid fields rejected with no write | `rejectsInvalidCreationWithoutStoringAnything`, `rejectsInvalidInputWithoutStoringAnything`, `rejectsOutOfRangeBoundaries`, `rejectsUnsupportedCategories`, `acceptsMinimumAndMaximumBoundaries`, `rejectsMissingAndWhitespaceOnlyFields`, `acceptsAndTrimsSurroundingCategoryWhitespace` | PASS — Victor-authorized delegated decision; bounded scope, 2026-11-10 |
| TC-003 / AC-REQ-002-01 | Owner sees current status after update/reopen | `resolvingRequiresSavedNoteAndReopeningPreservesImmutableFieldsAndText`, `permitsLifecycleAndPreservesResolutionOnReopen` | PASS — Victor-authorized delegated decision; bounded scope, 2026-11-10 |
| TC-004 / AC-REQ-002-02 | Owner sees latest saved note after reopen | `resolvingRequiresSavedNoteAndReopeningPreservesImmutableFieldsAndText`, `permitsLifecycleAndPreservesResolutionOnReopen` | PASS — Victor-authorized delegated decision; bounded scope, 2026-11-10 |
| TC-005 / AC-REQ-002-03 | Student B cannot list/read Student A request | `studentsSeeOnlyOwnedRequestsAndCannotReadOrChangeReviewerData`, `enforcesStudentOwnershipAndSeparateReviewerOperations`, `unknownIdsMalformedJsonAndUnsupportedTransitionsProduceClearErrors` | PASS — Victor-authorized delegated decision; bounded scope, 2026-11-10 |
| TC-006 / AC-REQ-003-01 | Unique IDs/owner/timestamps; failed write no success | `createsTrimmedOwnedRequestsWithDistinctIdsAndDefaults`, `rollsBackInsertWhenStorageFailsBeforeConfirmation`, `unavailableStorageReturnsFailureWithoutFalseSuccessOrRecordChanges` | PASS — Victor-authorized delegated decision; bounded scope, 2026-11-10 |
| TC-007 / AC-REQ-003-02 | Persist request/status/note across restart | `preservesOwnershipStatusNotesAndUniqueIdsAcrossFileStoreRestart` | PASS — Victor-authorized delegated decision; bounded scope, 2026-11-10 |
| TC-008 / AC-REQ-004-01 | Reviewer lists and inspects both students | `reviewerCanInspectBothStudentsButCannotUseStudentOnlyRetrieval`, `enforcesStudentOwnershipAndSeparateReviewerOperations` | PASS — Victor-authorized delegated decision; bounded scope, 2026-11-10 |
| TC-009 / AC-REQ-004-02 | Reject unauthorized reviewer actions and forged context | `missingInvalidAndWrongRoleContextsAreDenied`, `spoofedIdentityHeadersCannotGrantPermissionsOrOverrideTheSession`, `studentsSeeOnlyOwnedRequestsAndCannotReadOrChangeReviewerData`, `suppliedServerFieldsCannotForgeOwnerOrGrantReviewerRole`, `legacyClientStudentIdIsIgnoredWhenAssigningTheSessionOwner` | PASS — Victor-authorized delegated decision; bounded scope, 2026-11-10 |
| TC-010 / AC-REQ-005-01 | Allowed transitions, saved note, reopen preservation | `resolvingRequiresSavedNoteAndReopeningPreservesImmutableFieldsAndText`, `permitsLifecycleAndPreservesResolutionOnReopen` | PASS — Victor-authorized delegated decision; bounded scope, 2026-11-10 |
| TC-011 / AC-REQ-005-02 | Invalid transitions/unknown ID/write failure preserve state | `unknownIdsMalformedJsonAndUnsupportedTransitionsProduceClearErrors`, `rejectsUnsupportedSameStateAndUnresolvedTransitionsWithoutChanges`, `rollsBackStatusAndNoteWhenStorageFailsAfterWrite` | PASS — Victor-authorized delegated decision; bounded scope, 2026-11-10 |
| TC-012 / AC-REQ-006-01 | Save/replace note, bounds and failure rejection | `notesRespectTrimmedBoundaryAndCanBeClearedOnlyBeforeResolution`, `enforcesTrimmedNoteBoundariesAndResolvedClearingRules`, `rollsBackStatusAndNoteWhenStorageFailsAfterWrite` | PASS — Victor-authorized delegated decision; bounded scope, 2026-11-10 |

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
demo. Preserve historical Not Run counts as historical; these are combined
automated evidence records, not twelve newly witnessed manual runs.

## Human accountability and release gates

**TC-001–TC-012: PASS within the approved classroom scope.** Victor Dodan
is the accountable acceptance and release authority. He explicitly authorized
Codex to make and record these decisions on his behalf. Codex recorded the
scoped PASS decisions using the executed evidence above; this is delegated
acceptance, not a claim that Victor personally executed tests or that Mareddy
or Giancarlo granted unrecorded final case acceptance.

The [release decision](../release/a5-release-decision.md) records the acceptance
basis, tested checkpoints, accepted TC-002/TC-007 limitations, risk dispositions
and conditional classroom GO. This supersedes the proposed-PASS status in the
earlier #108 review. It does not extend acceptance beyond loopback-only,
synthetic-data classroom use or replace the separate independent integrated
review requirement. No new test execution is claimed by this update.

| Gate | Current status |
|---|---|
| TC-001–TC-012 case dispositions | PASS within approved classroom scope, under Victor's delegated authority |
| Accountable acceptance authority | Victor Dodan; delegation and decision basis recorded in the release decision |
| #108/#109 documentation review | APPROVED by Giancarlo; both PRs merged |
| Independent non-author review of final integrated candidate | OPEN — documentation approvals do not establish this review |
| Security, support and license risk treatment | Accepted/deferred for bounded A5 scope under Victor's authorization; limits, expiry and ownership recorded; no security/legal clearance |
| Release authorization | CONDITIONAL GO for the classroom scope; pre-tag prerequisites retained |
| Final repository SHA / exact-main CI / `a5-cycle1-release` tag | OPEN — select and verify after integration; no tag created by this work |

## Reconciliation with Mareddy's PR #109

Mareddy's [review](https://github.com/victordodan/-comp330-f26-team-02/pull/109)
is dated 2026-11-10 and assesses CI #50 at
`000c0f89db51a64956566fc82fc7742973ce1e7b`: 35 tests passed; no new execution.
Its Pending case decisions describe that earlier review checkpoint; the later
Victor-authorized scoped PASS decisions above supersede that pending status.
This closeout instead records the fresh 2026-11-10 execution at
`531d54ca0f391e218752888900c4668173f75e27` (build finished 05:45:26 UTC,
HTTP workflow began 05:46:08 UTC; restart checks began 05:46:48 UTC).
Neither checkpoint is declared the final release SHA.

- TC-002: source review establishes structured validation errors; the exact
  HTTP validation-message assertion limitation in #109 remains OPEN for named
  human acceptance or a targeted assertion. Passing status checks alone do not
  establish exact message text coverage.
- TC-007: the fresh packaged-JVM restart supplies newer evidence than #109's
  historical A4 reference. It preserves one In Progress record, note, fields
  and ETag on the same file database. It is not backup restoration, all-state
  recovery coverage, or verification of a final release revision yet to be chosen.
- The remaining cases retain #109's source-review evidence alongside the newer
  executed methods above. No independent reviewer approval is inferred.

## Artifact and source verification

[Artifact verification](../../test-evidence/a5-acceptance-closeout/artifact-verification.json)
records checks against the retained originals: all 35 manifest entries match
Surefire XML; 36 workflow and 5 restart records match original transcripts and
expected statuses; the restart response equals the saved record and ETag.
The original application logs show distinct JVM PIDs for initial and restarted
processes. The manifest retains its original source SHA; application source,
POM and demo script have no diff from it. Checksums identify the retained
artifacts and packaged jar; this is a provenance consistency check, not a
signed build attestation. No new test execution or final release approval was
performed during this reconciliation.
