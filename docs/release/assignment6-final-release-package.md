# CampusConnect A6 evidence index

**Team 02 · Codex Ramblers · COMP 330 Software Project Management**

**Repository clone URL:** https://github.com/victordodan/-comp330-f26-team-02.git

**Verified development candidate:** `373a0f6b6d6190d6067099178a8c71862bb12dec`.
**Final release disposition:** Pending. The planned release tag is `a6-final-release`.

| Evidence | Repository record |
|---|---|
| Release scope and operating instructions | [Release notes](final-release-notes.md), [README](../../README.md), [runbook](../operations/runbook.md) |
| Changes since the submitted A5 tag | [Candidate inventory](changed-files-since-a5.md) |
| Learning, scope, and planning | [Postmortem](../operations/postmortem-cycle1.md), [maturity targets and dispositions](../planning/cycle2-maturity-plan.md), [re-estimation](../planning/re-estimation.md), [task plan](../planning/task-plan.md), [schedule](../planning/schedule.md) |
| Technical design and workflow | [Architecture](../architecture/architecture.md), [API contracts](../architecture/api-contracts.md), [REST decision](../decisions/ADR-006-rest-api-interface-alignment.md), [traceability](../planning/traceability.md) |
| Executed checks | [Candidate CI](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/45509064331), [test evidence and acceptance](../testing/final-test-evidence.md), [runtime evidence](../observability/runtime-evidence.md) |
| Risks, limitations, and defects | [Known limitations](known-limitations.md), [risk register](../planning/risk-register.md), [defect review](../quality/defect-review.md) |
| Governance and AI disclosure | [Security governance checklist](../security/security-governance-checklist.md), [permission boundaries](../security/permission-boundaries.md), [AI-use log](../ai/ai-use-log.md) |

The local classroom scope uses loopback, selectable synthetic identities, and
a file-backed H2 store. Candidate results cover 51 Maven tests, 38 HTTP checks,
a Chromium workflow exercise, and a repeatable seed procedure. The
[runtime record](../observability/runtime-evidence.md) adds the Linux and Windows
operational exercises on `0860160`. [Post-merge CI for #137](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/45549423920)
passed on `e9ca7c2`.

## A6 candidate acceptance evidence

Codex executed the packaged Linux workflow on November 24, 2026 at
`0860160bc458af670c7944ccd035d21425e7511d` with Java 17, Maven 3.9.9,
and an isolated file-backed H2 store. That run recorded 51 passing JUnit
tests, 36 passing [workflow checks](../../test-evidence/a6-operations-0860160/workflow.jsonl),
and 5 passing [restart checks](../../test-evidence/a6-operations-0860160/restart.jsonl).
The named JUnit methods below were checked against the executed Surefire
reports. [Main CI for the candidate](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/45543404188)
passed. Giancarlo separately reported the Windows exercise at the same
revision in the [runtime record](../observability/runtime-evidence.md).

| Case | Behavior | HTTP steps | Executed JUnit example | Observed scope | Final disposition |
|---|---|---|---|---|---|
| TC-001 | Submit a valid owned request | 9–10 | `createsTrimmedStudentOwnedRequestAndReturnsCurrentEtag` | The script creates requests for both demo students and observes HTTP 201. | Pending |
| TC-002 | Reject invalid fields without a write | 7–8 | `rejectsInvalidCreationWithoutStoringAnything` | The HTTP script checks a short title and unchanged count. JUnit also checks required fields, length and category rules. | Pending |
| TC-003 | Owner sees current and reopened status | 21, 30, 34–36 | `resolvingRequiresSavedNoteAndReopeningPreservesImmutableFieldsAndText` | The owner reads the newly reopened record. | Pending |
| TC-004 | Owner sees replaced note and note survives reopening | 33–36 | `permitsLifecycleAndPreservesResolutionOnReopen` | The HTTP response shows the revised note before and after reopening. | Pending |
| TC-005 | Student access stays owner-scoped | 9–12 | `studentsSeeOnlyOwnedRequestsAndCannotReadOrChangeReviewerData` | A student list is owner-scoped and direct lookup by the other student returns 404. JUnit also covers unknown IDs. | Pending |
| TC-006 | Unique server metadata and failed-write behavior | 9–10; restart 5 | `rollsBackInsertWhenStorageFailsBeforeConfirmation` | HTTP responses establish the request metadata and distinct IDs. JUnit injects storage failure to cover rollback. | Pending |
| TC-007 | Retain a request through same-store restart | Restart 1–5 | `preservesOwnershipStatusNotesAndUniqueIdsAcrossFileStoreRestart(Path)` | The saved ID, status, note, and version are retained. Backup restoration is a separate risk. | Pending |
| TC-008 | Reviewer can list both students and inspect requests | 13–14 | `reviewerCanInspectBothStudentsButCannotUseStudentOnlyRetrieval` | The reviewer list includes both students and a detail request succeeds. | Pending |
| TC-009 | Reject missing or unauthorized demo context | 1–2, 12, 15–16 | `spoofedIdentityHeadersCannotGrantPermissionsOrOverrideTheSession` | The HTTP run covers missing context, forged headers and student denials. JUnit exercises additional context variants. | Pending |
| TC-010 | Allow only the supported lifecycle | 17–18, 21–23, 27, 30, 35–36 | `permitsLifecycleAndPreservesResolutionOnReopen` | Submitted moves to In Progress, resolves with a saved note, and reopens with the note preserved. | Pending |
| TC-011 | Reject bad transitions, unknown IDs and failed writes | 17–24, 28–29 | `rollsBackStatusAndNoteWhenStorageFailsAfterWrite` | The HTTP script covers transition, version and unknown-ID failures. JUnit covers injected write failures. | Pending |
| TC-012 | Save and replace notes with valid version and length | 25–29, 31–34 | `noteUpdateRejectsMissingAndMalformedIfMatchWithoutChangingRequest` | HTTP checks include an oversized note, clearing while resolved, and replacement. Automated cases also cover version format, note bounds and write rollback. | Pending |

Before submission, add the approved revision and tag, final case dispositions,
independent review outcome or recorded gap, and the accountable release decision.
The operational exercise and any unverified backup restoration or dependency
assurance must carry their actual status and named owners. The Sakai team
package includes the Word index, clone URL, and editable PowerPoint. Individual
peer reviews go to the private course submission.
