# A5 Acceptance Test Evidence — TC-001 through TC-012

**Project:** CampusConnect — COMP 330, Team 2 (Codex Ramblers)  
**Prepared by:** Venkata Aravind Mareddy  
**Verification date:** November 9, 2026  
**Evidence baseline:** PR #84, commit `00c034a`  
**Environment:** GitHub Actions, Java 17, Maven, JUnit 5, Spring Boot, H2  
**CI evidence:** [PR #84](https://github.com/victordodan/-comp330-f26-team-02/pull/84)
**Successful CI run:** [GitHub Actions Run #41](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42613709038) — Success; 35 tests passed, 0 failures, 0 errors, 0 skipped, as recorded in the downloaded `java-test-reports` artifact.

## 1. Automated Test Execution Summary

The GitHub Actions workflow **A4 Java Build and Tests** successfully executed `mvn -B clean verify` for PR #84.

The downloaded `java-test-reports` artifact contains the following JUnit XML results:

| Test class | Tests | Failures | Errors | Skipped |
|---|---:|---:|---:|---:|
| RequestValidatorTest | 7 | 0 | 0 | 0 |
| WorkflowServiceTest | 13 | 0 | 0 | 0 |
| RequestControllerTest | 14 | 0 | 0 | 0 |
| CampusConnectApplicationTests | 1 | 0 | 0 | 0 |
| **Total** | **35** | **0** | **0** | **0** |

**Automated test result: PASS — 35/35 tests successful.**

This result applies to the tested PR #84 revision. It does not independently establish that a newer `main` commit has passed CI.

## 2. Acceptance Test Evidence Matrix

The following matrix maps the twelve planned acceptance cases to relevant executed automated tests.

**Status definitions:**
- **Automated coverage passed:** Related JUnit tests executed successfully.
- **Separate acceptance execution not recorded:** No independent, dated, case-specific acceptance run has been documented.

| Case | Scenario | Relevant Automated Evidence | Automated Coverage | Separate Acceptance Execution |
|---|---|---|---|---|
| TC-001 | Valid student request submission | `createsTrimmedStudentOwnedRequestAndReturnsCurrentEtag`; `createsTrimmedOwnedRequestsWithDistinctIdsAndDefaults` | Passed | Not recorded |
| TC-002 | Invalid inputs rejected without persistence | `rejectsInvalidCreationWithoutStoringAnything`; `rejectsOutOfRangeBoundaries`; `rejectsUnsupportedCategories` | Passed | Not recorded |
| TC-003 | Student retrieves current request status | `resolvingRequiresSavedNoteAndReopeningPreservesImmutableFieldsAndText`; `permitsLifecycleAndPreservesResolutionOnReopen` | Passed | Not recorded |
| TC-004 | Latest resolution note and reopening | `resolvingRequiresSavedNoteAndReopeningPreservesImmutableFieldsAndText`; `permitsLifecycleAndPreservesResolutionOnReopen` | Passed | Not recorded |
| TC-005 | Student ownership and isolation | `studentsSeeOnlyOwnedRequestsAndCannotReadOrChangeReviewerData`; `enforcesStudentOwnershipAndSeparateReviewerOperations` | Passed | Not recorded |
| TC-006 | Unique IDs, metadata, failed writes | `createsTrimmedOwnedRequestsWithDistinctIdsAndDefaults`; `rollsBackInsertWhenStorageFailsBeforeConfirmation`; `unavailableStorageReturnsFailureWithoutFalseSuccessOrRecordChanges` | Passed | Not recorded |
| TC-007 | Persistence after restart | `preservesOwnershipStatusNotesAndUniqueIdsAcrossFileStoreRestart` | Passed | Not recorded |
| TC-008 | Reviewer visibility across students | `reviewerCanInspectBothStudentsButCannotUseStudentOnlyRetrieval` | Passed | Not recorded |
| TC-009 | Unauthorized reviewer access rejected | `missingInvalidAndWrongRoleContextsAreDenied`; `spoofedIdentityHeadersCannotGrantPermissionsOrOverrideTheSession`; `suppliedServerFieldsCannotForgeOwnerOrGrantReviewerRole` | Passed | Not recorded |
| TC-010 | Valid lifecycle transitions and reopening | `resolvingRequiresSavedNoteAndReopeningPreservesImmutableFieldsAndText`; `permitsLifecycleAndPreservesResolutionOnReopen` | Passed | Not recorded |
| TC-011 | Invalid transitions and failed updates | `unknownIdsMalformedJsonAndUnsupportedTransitionsProduceClearErrors`; `updatePreconditionsRejectMissingMalformedAndStaleVersionsWithoutChanges`; `rollsBackStatusAndNoteWhenStorageFailsAfterWrite` | Passed | Not recorded |
| TC-012 | Resolution note boundaries and failures | `notesRespectTrimmedBoundaryAndCanBeClearedOnlyBeforeResolution`; `enforcesTrimmedNoteBoundariesAndResolvedClearingRules` | Passed | Not recorded |

## 3. Additional Verification Evidence

The automated suite includes tests for:

- Field validation, trimming, supported categories, and length boundaries.
- Request creation with server-controlled ownership and initial `Submitted` status.
- Student isolation and reviewer-only access.
- Rejection of forged identity headers and unauthorized operations.
- Valid and invalid status transitions.
- Resolution note requirements, editing, and length boundaries.
- H2 file-store persistence across a service-level restart.
- Storage failure handling and rollback behavior.
- Optimistic concurrency and stale-version rejection.
- Spring Boot application HTTP startup.

The successful Maven verification also confirms that the tested revision completed its build and packaging process.

## 4. Remaining Verification Gaps

Although all 35 automated tests passed, the following work is not established by this evidence:

1. Twelve separately dated, case-by-case acceptance execution records have not been provided.
2. The latest merged `main` revision has not been independently verified using the PR #84 test artifact.
3. The service-level restart test is not a substitute for a separately documented packaged-application restart acceptance run.
4. Browser-based end-to-end acceptance testing has not been demonstrated by these JUnit reports.
5. Real institutional authentication and production security readiness are outside the demonstrated synthetic-session testing scope.

No unexecuted manual acceptance case is marked as passed.

## 5. Evidence References

- [PR #84 — Automated CI verification](https://github.com/victordodan/-comp330-f26-team-02/pull/84)
- [A4 Java Build and Tests workflow](https://github.com/victordodan/-comp330-f26-team-02/actions/workflows/a4-validation-tests.yml)
- `src/test/java/edu/loyola/campusconnect/controller/RequestControllerTest.java`
- `src/test/java/edu/loyola/campusconnect/service/WorkflowServiceTest.java`
- `src/test/java/edu/loyola/campusconnect/validation/RequestValidatorTest.java`
- `src/test/java/edu/loyola/campusconnect/CampusConnectApplicationTests.java`
- GitHub Actions artifact: `java-test-reports`
- `docs/testing/test-cases.md`
- `docs/testing/a4-final-quality-verification.md`

## 6. Verification Conclusion

**Automated verification: PASS (35/35).**

Relevant automated coverage exists for all twelve planned acceptance cases, with no reported JUnit failures, errors, or skips in the PR #84 evidence.

**Separate acceptance execution: NOT YET DOCUMENTED (0/12).**

The automated results provide traceable implementation evidence, but final acceptance status requires the team to confirm whether separate case-by-case execution is required and to record those runs if necessary.

**Release readiness:** Pending review of remaining acceptance evidence, latest-main CI status, and team approval.
