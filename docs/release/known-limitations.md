
# Known Limitations — CampusConnect Cycle 2 Final Release

## 1. Document Information

| Field | Details |
|---|---|
| Project | CampusConnect — University Support Portal |
| Course | COMP 330 — Software Engineering |
| Team | Team 02 — Codex Ramblers |
| Release Phase | Assignment 6 — Final Cycle 2 Release |
| Owner | Shú Perez |
| Backup | Giancarlo Herrera |
| Documentation Date | November 24, 2026 |
| Application Address | `http://campusconnect.local:8080` |
| Target Release Tag | `a6-final-release` |
| Status | Release candidate — final risk acceptance pending |

This document identifies known implementation limitations, residual technical risks, operational boundaries, and verification gaps associated with the CampusConnect Cycle 2 release.

The purpose of this record is to communicate the actual maturity of the application without overstating implementation completeness, verification coverage, security readiness, or operational capabilities.

This document does not certify that the application contains no unresolved defects or that it is suitable for production deployment.

---

## 2. Historical Release Context

### 2.1 Assignment 5 Release Decision

The November 10, 2026, Assignment 5 release decision authorized a conditional classroom release under the authority of team lead Victor Dodan.

Historical acceptance cases TC-001 through TC-012 were accepted within their documented scope.

Residual risks were accepted or deferred according to the recorded Assignment 5 release decision.

Final integrated human review was explicitly deferred for that conditional baseline. The associated course condition remained unfulfilled.

The Assignment 5 release decision is maintained in:

[Assignment 5 Release Decision](a5-release-decision.md)

Historical acceptance decisions must not be interpreted as automatic approval of the Assignment 6 release.

### 2.2 Cycle 2 Reconciliation

Cycle 2 introduces a browser-based presentation layer and additional verification capabilities.

The original Cycle 1 limitation record identified the absence of a verified graphical workflow as a usability limitation.

That limitation has been partially addressed through the implementation of:

- HTML browser interface.
- Responsive CSS styling.
- Vanilla JavaScript application logic.
- Student and reviewer dashboards.
- Browser-based request submission.
- Request status filtering.
- Reviewer workflow controls.
- Resolution note management.
- Frontend integration tests.
- Frontend smoke testing.
- Synthetic demonstration data generation.

The browser presentation layer is implemented, and its HTTP resource availability has been verified through local runtime execution.

Comprehensive browser interaction verification remains a separate consideration.

The original limitation identifiers are preserved to maintain continuity with earlier engineering evidence.

---

## 3. Current Implementation Facts

CampusConnect uses:

- Java.
- Spring Boot.
- Maven.
- REST-based HTTP interfaces.
- HTML, CSS, and vanilla JavaScript.
- Server-side HTTP sessions.
- Synthetic demonstration identities.
- JDBC repository implementation.
- File-backed H2 database storage.
- UUID-based request identifiers.
- Transactional workflow operations.
- ETag-based optimistic concurrency control.
- Structured HTTP error responses.

The browser presentation layer communicates with the existing REST backend.

Authoritative request state, ownership, authorization, validation, workflow transitions, and persistence remain controlled by the server.

The application does not use an in-memory ConcurrentHashMap as its authoritative request repository.

The browser does not maintain an independent authoritative request database.

Relevant implementation evidence:

- [Architecture Overview](../architecture/architecture.md)
- [Component Responsibilities](../architecture/component-responsibilities.md)
- [API Contracts](../architecture/api-contracts.md)
- [Data Context](../architecture/data-context.md)
- [Runtime Configuration](../../src/main/resources/application.properties)
- [JDBC Repository](../../src/main/java/edu/loyola/campusconnect/repository/JdbcRequestRepository.java)
- [Request Controller](../../src/main/java/edu/loyola/campusconnect/controller/RequestController.java)
- [Workflow Service](../../src/main/java/edu/loyola/campusconnect/service/WorkflowService.java)

---

## 4. Limitation Classification

Severity describes the potential impact if the documented application boundary is exceeded.

Severity does not independently establish that a runtime defect has occurred.

| Severity | Interpretation |
|---|---|
| High | Potential security, data integrity, availability, or operational impact requiring explicit release consideration |
| Medium | Potential workflow, usability, verification, or maintainability impact |
| Low | Bounded inconvenience or functionality outside the agreed classroom scope |

Disposition terminology:

| Disposition | Meaning |
|---|---|
| Mitigated | A control or implementation improvement reduces the identified risk |
| Partially Addressed | An improvement exists, but residual limitations remain |
| Deferred | Further work is outside the current implementation scope or awaits prioritization |
| Pending Verification | Available evidence does not establish the complete behavior |
| Pending Acceptance | Formal release risk acceptance has not been recorded |

A mitigation does not necessarily eliminate the underlying limitation.

---

## 5. Known Limitations and Residual Risk Register

| ID | Limitation | Severity | Mitigation | Proposed Disposition |
|---|---|---|---|---|
| LIM-001 | Local database retention and recovery | High | File-backed H2, consistent storage path, synthetic data | Partially Addressed |
| LIM-002 | Synthetic identities without production authentication | High outside classroom scope | Server-side session permissions and local deployment | Deferred |
| LIM-003 | No automated notification delivery | Low | Student dashboard and request retrieval | Deferred |
| LIM-004 | Browser interaction coverage | Medium | Passing HTTP smoke checks and a Chromium workflow on merged main | Partially Addressed |
| LIM-005 | No production deployment or complete operational recovery capability | High outside classroom scope | Local operation and documented release boundaries | Deferred |
| LIM-006 | Shared browser session behavior | Medium | Separate browser profiles for independent identities | Mitigated |
| LIM-007 | Temporary browser state and unsaved input | Medium | Explicit save operations and server-authoritative state | Partially Addressed |
| LIM-008 | Concurrent reviewer modification conflicts | Medium | ETags, conditional updates, and conflict responses | Mitigated |
| LIM-009 | Demonstration data modifies persistent storage | Medium | Synthetic records and execution confirmation | Mitigated |
| LIM-010 | Demonstration setup duplicate detection is not atomic | Medium | Sequential execution and existing-record matching | Partially Addressed |
| LIM-011 | Interrupted demonstration setup may leave partial records | Medium | Inspect existing records before repeating setup | Deferred |
| LIM-012 | Optional local hostname configuration | Low | Use the default loopback URL without a hosts-file entry | Mitigated |
| LIM-013 | Incomplete browser compatibility and accessibility verification | Medium | Responsive styling and accessibility-oriented interface design | Pending Verification |
| LIM-014 | Final integrated release verification | High | Existing local runtime evidence and final release gate | Pending Verification |
| LIM-015 | No verified production capacity or sustained-load assessment | High outside classroom scope | Restrict operation to bounded classroom use | Deferred |
| LIM-016 | Dependency and security assurance limitations | High outside classroom scope | Existing dependency evidence and human review | Pending Verification |

The proposed dispositions above require reconciliation with the team's risk register and final release authorization.

---

## 6. Detailed Technical Limitations

### 6.1 LIM-001 — Local Database Retention and Recovery

**Severity:** High

**Follow-up owner:** Architecture and testing owners

CampusConnect uses a JDBC repository with file-backed H2 persistence.

The default datasource is:

`jdbc:h2:file:./data/campusconnect`

The database location is relative to the application process working directory.

Starting the application from a different working directory or changing the datasource configuration may cause the application to use a different database.

This behavior must be distinguished from unexpected data loss when restarting against the same database.

The existing implementation supports persistent request storage, including:

- Request identifiers.
- Request ownership.
- Request titles.
- Request descriptions.
- Request categories.
- Workflow statuses.
- Resolution notes.
- Creation timestamps.
- Update timestamps.
- Request versions.

However, file-backed persistence does not independently establish:

- Verified backup restoration.
- Disaster recovery.
- Protection against database file deletion.
- Protection against filesystem corruption.
- Safe multi-instance deployment.
- Production database availability.

Mitigation:

- Run the application from a consistent repository directory.
- Preserve the configured database files.
- Use synthetic demonstration records.
- Avoid unauthorized database resets.
- Document recovery procedures.
- Distinguish ordinary restart retention from backup restoration.

The recorded Cycle 2 demonstration setup confirms successful storage and retrieval during the running application session.

It does not independently establish persistence across a subsequent application restart.

References:

- [ADR-004 — Local Persistence and Recovery](../decisions/ADR-004-local-persistence-and-recovery.md)
- [Runtime Configuration](../../src/main/resources/application.properties)
- [JDBC Repository](../../src/main/java/edu/loyola/campusconnect/repository/JdbcRequestRepository.java)
- [Historical Backend Verification](../testing/a4-backend-verification.md)

---

### 6.2 LIM-002 — Synthetic Identity and Authentication

**Severity:** High outside classroom scope

**Follow-up owner:** Architecture owner and team lead

CampusConnect supports three synthetic identities:

- `student-a`
- `student-b`
- `reviewer`

The identity selection endpoint is:

`POST /api/demo/identity`

The backend validates the selected identity and stores it in the HTTP session.

The application enforces role-specific permissions and request ownership through server-side checks.

Client-provided identity headers and ownership fields do not independently grant authorization.

However, any caller who can access the demonstration identity selector can deliberately select the Reviewer identity.

The browser identity selector is not a production authentication system.

The application does not establish:

- Verified real-world identities.
- University single sign-on.
- Password-based institutional authentication.
- Multifactor authentication.
- Production account provisioning.
- Institutional identity lifecycle management.

Mitigation:

- Restrict use to synthetic classroom identities.
- Preserve the default loopback-only server configuration.
- Do not process real student information.
- Do not expose the application as a public university service.
- Do not represent role selection as institutional authentication.

The local application hostname does not change the authentication model or provide an additional security boundary.

References:

- [Request Controller](../../src/main/java/edu/loyola/campusconnect/controller/RequestController.java)
- [API Contracts](../architecture/api-contracts.md)
- [Project Scope](../planning/scope.md)

---

### 6.3 LIM-003 — Automated Notification Delivery

**Severity:** Low

**Follow-up owner:** Architecture and requirements owners

CampusConnect does not implement automated notification delivery through:

- Email.
- SMS.
- Push notifications.
- External messaging services.

Students must access their dashboard or retrieve their requests to inspect current statuses and resolution notes.

The browser interface improves access to current request information but does not introduce a notification-delivery subsystem.

User-facing browser notifications communicate the results of current application operations. They are not equivalent to external or asynchronous notification delivery.

Mitigation:

- Provide visible request status indicators.
- Display saved resolution notes.
- Allow students to refresh their dashboard.
- Document notification delivery as outside the current implemented scope.

Future notification functionality requires separate requirements, architecture decisions, implementation, and verification.

References:

- [Project Scope](../planning/scope.md)
- [Browser Application Logic](../../src/main/resources/static/app.js)

---

### 6.4 LIM-004 — Browser Presentation Verification

**Severity:** Medium

**Follow-up owner:** Architecture and testing owners

The original Cycle 1 limitation identified the absence of a verified graphical application workflow.

Cycle 2 partially addresses this limitation through the implemented browser presentation layer.

The frontend consists of:

- `src/main/resources/static/index.html`
- `src/main/resources/static/styles.css`
- `src/main/resources/static/app.js`

The browser interface provides student and reviewer dashboards, request submission, status filtering, request details, resolution note management, and reviewer workflow controls.

Local smoke testing confirmed:

- Homepage availability.
- CSS resource availability.
- JavaScript resource availability.
- Presence of required interface elements.
- Synthetic identity selection.
- Role-specific API access.
- Unauthorized access rejection.
- Identity switching.
- Missing resource handling.

Observed result:

38 checks passed and 0 failed.

The follow-up Chromium run on merged main exercised the browser workflow. Broad browser, device, and accessibility coverage remains open.

The recorded evidence does not establish comprehensive verification of:

- Every browser interaction.
- Every form validation scenario.
- Every user-facing error notification.
- All dashboard refresh behavior.
- Every responsive layout.
- Every browser-side concurrency interaction.

Mitigation:

- Maintain automated frontend integration coverage.
- Maintain the frontend smoke testing script.
- Document the distinction between HTTP verification and browser interaction verification.
- Avoid claiming comprehensive browser verification without supporting evidence.

References:

- [HTML Interface](../../src/main/resources/static/index.html)
- [Frontend Stylesheet](../../src/main/resources/static/styles.css)
- [Browser Application Logic](../../src/main/resources/static/app.js)
- [Frontend Integration Tests](../../src/test/java/edu/loyola/campusconnect/controller/FrontendIntegrationTest.java)
- [Frontend Smoke Testing](../../scripts/frontend-smoke-test.sh)

---

### 6.5 LIM-005 — Production Deployment and Operational Readiness

**Severity:** High outside classroom scope

**Follow-up owner:** Architecture and release owners

CampusConnect is designed as a local classroom application.

The release does not establish:

- Production infrastructure.
- Public hosting.
- Institutional deployment approval.
- Automated production deployment.
- Automated production rollback.
- High availability.
- Centralized production monitoring.
- Automated incident response.
- Comprehensive disaster recovery.

The application can be operated locally using Maven and Spring Boot.

The application address is:

`http://campusconnect.local:8080`

The custom hostname does not represent public hosting or a production deployment.

Mitigation:

- Maintain the local classroom deployment boundary.
- Document startup and shutdown procedures.
- Preserve synthetic data restrictions.
- Document operational dependencies.
- Avoid production-readiness claims.

References:

- [Runtime Configuration](../../src/main/resources/application.properties)
- [Operational Runbook](../operations/runbook.md)
- [ADR-004](../decisions/ADR-004-local-persistence-and-recovery.md)

---

### 6.6 LIM-006 — Shared Browser Session Behavior

**Severity:** Medium

**Follow-up owner:** Architecture owner

The active demonstration identity is maintained in a server-side HTTP session.

Multiple browser tabs within the same browser profile may share the same session cookie.

Selecting a different identity in one tab can therefore affect the server-side identity used by another tab.

A previously rendered dashboard may temporarily display information associated with an earlier identity until the interface refreshes or performs another API operation.

Backend authorization remains authoritative.

Mitigation:

- Use separate browser profiles when demonstrating independent student and reviewer sessions.
- Refresh the interface after identity changes.
- Do not assume that separate tabs provide independent sessions.
- Clear or refresh displayed application state when changing identities.
- Continue enforcing authorization on every protected backend operation.

The successful smoke testing session-switching checks do not independently establish all cross-tab browser behavior.

References:

- [Request Controller](../../src/main/java/edu/loyola/campusconnect/controller/RequestController.java)
- [Browser Application Logic](../../src/main/resources/static/app.js)

---

### 6.7 LIM-007 — Temporary Browser State and Unsaved Input

**Severity:** Medium

**Follow-up owner:** Architecture owner

The browser maintains temporary interface state, including:

- Selected identity presentation.
- Loaded request records.
- Active status filters.
- Form input.
- Unsaved reviewer note text.
- Loading indicators.
- Operation feedback.

This state is not an independent persistent request store.

Unsaved input may be lost when the interface is refreshed, re-rendered, or navigated away from.

Reviewer notes must be explicitly saved before they become authoritative stored data.

The application does not establish a comprehensive draft recovery or automatic save mechanism.

Mitigation:

- Use explicit save operations.
- Distinguish saved resolution notes from temporary input.
- Avoid treating browser state as authoritative.
- Communicate failed operations through interface feedback.
- Preserve server-side validation and persistence.

References:

- [Browser Application Logic](../../src/main/resources/static/app.js)
- [Data Context](../architecture/data-context.md)

---

### 6.8 LIM-008 — Concurrent Reviewer Modifications

**Severity:** Medium

**Follow-up owner:** Architecture and testing owners

Multiple reviewer sessions may retrieve the same request version.

CampusConnect uses optimistic concurrency control to prevent a stale modification from silently overwriting a newer request version.

Reviewer modifications require the applicable `If-Match` precondition.

The repository performs conditional updates using the expected record version.

Relevant failure responses include:

- HTTP 412 for stale request versions.
- HTTP 428 for missing required update preconditions.
- HTTP 400 for malformed precondition values.

The frontend is designed to retrieve and compare current request version information before submitting reviewer modifications.

A concurrent update may still occur between retrieval and modification.

The backend conditional update remains the authoritative consistency control.

Mitigation:

- Require ETag-based conditional updates.
- Reject stale modifications.
- Refresh request information following a conflict.
- Require deliberate user review before resubmitting a conflicting modification.
- Avoid automatic overwriting of newer stored data.

Optimistic concurrency does not eliminate conflicts.

It provides controlled conflict detection rather than guaranteeing that every attempted modification will succeed.

References:

- [ADR-003 — Request Consistency](../decisions/ADR-003-request-consistency.md)
- [API Contracts](../architecture/api-contracts.md)
- [Workflow Service](../../src/main/java/edu/loyola/campusconnect/service/WorkflowService.java)
- [JDBC Repository](../../src/main/java/edu/loyola/campusconnect/repository/JdbcRequestRepository.java)

---

### 6.9 LIM-009 — Persistent Demonstration Data

**Severity:** Medium

**Follow-up owner:** Architecture and release owners

The demonstration data script creates synthetic support requests in the application's configured persistent database.

The script is located at:

`scripts/demo-data.sh`

The script is not a read-only verification operation.

It can create records, save resolution notes, and perform reviewer status transitions.

The script requires confirmation before beginning demonstration setup.

Observed local execution created:

- Six synthetic support requests.
- Ten confirmed reviewer updates.
- Three Student A demonstration records.
- Three Student B demonstration records.

The Reviewer identity retrieved all six demonstration records.

Mitigation:

- Execute only against an appropriate synthetic classroom database.
- Require operator confirmation.
- Clearly identify demonstration records.
- Do not execute against real institutional data.
- Preserve existing matching records.
- Document the persistent effects of demonstration setup.

The script does not automatically delete demonstration records after execution.

References:

- [Demonstration Data Script](../../scripts/demo-data.sh)
- [PR #131](https://github.com/victordodan/-comp330-f26-team-02/pull/131)

---

### 6.10 LIM-010 — Demonstration Data Duplicate Detection

**Severity:** Medium

**Follow-up owner:** Architecture owner

The demonstration data script checks for matching existing records before creating new demonstration requests.

Matching behavior is based on the expected demonstration record attributes.

This reduces unnecessary duplication during sequential execution.

However, the existing-record check and subsequent creation are separate operations.

The implementation does not establish an atomic duplicate-prevention guarantee across concurrent script executions.

UUID-based request identifiers provide distinct record identifiers but do not independently prevent semantically duplicate requests.

Mitigation:

- Execute demonstration setup sequentially.
- Avoid simultaneous demonstration setup processes.
- Inspect existing matching records.
- Do not represent UUID generation as duplicate prevention.
- Do not claim exactly-once request creation.

Repeated execution behavior has not been confirmed by the recorded Cycle 2 demonstration setup.

References:

- [Demonstration Data Script](../../scripts/demo-data.sh)
- [ADR-003](../decisions/ADR-003-request-consistency.md)

---

### 6.11 LIM-011 — Partial Demonstration Setup Failure

**Severity:** Medium

**Follow-up owner:** Architecture and operations owners

Demonstration setup performs multiple independent HTTP operations.

The entire setup procedure is not executed as a single database transaction.

If the script stops after creating a request but before completing its intended reviewer updates, partially prepared demonstration records may remain in the database.

A subsequent execution may identify an existing matching record and skip its creation without completing the originally intended status or resolution note preparation.

Mitigation:

- Inspect existing demonstration records following interrupted execution.
- Review their current statuses and saved resolution notes.
- Correct incomplete demonstration records through authorized reviewer operations.
- Avoid unnecessary database deletion.
- Do not claim automatic whole-script rollback or recovery.

The observed local execution completed successfully.

Interrupted execution recovery has not been established by that successful run.

References:

- [Demonstration Data Script](../../scripts/demo-data.sh)
- [Operational Runbook](../operations/runbook.md)

---

### 6.12 LIM-012 — Local Hostname Configuration

**Severity:** Low

**Follow-up owner:** Architecture and operations owners

The application uses the following locally configured address:

`http://campusconnect.local:8080`

The optional hostname requires a local hosts-file entry. The default URL is `http://127.0.0.1:8080/` and needs no hosts-file entry:

`127.0.0.1 campusconnect.local`

This configuration is specific to each computer.

Cloning the repository does not automatically modify the operating system's hostname resolution configuration.

The `.local` hostname suffix may also interact with operating system multicast DNS behavior.

Mitigation:

- Document the required hosts-file configuration.
- Preserve the loopback address mapping.
- Confirm hostname resolution on the demonstration computer.
- Keep the application within its local deployment boundary.
- Avoid representing the hostname as a publicly registered domain.

The hostname does not establish:

- Public internet availability.
- External DNS registration.
- HTTPS encryption.
- Production hosting.
- Institutional network authorization.

References:

- [Repository README](../../README.md)
- [Operational Runbook](../operations/runbook.md)

---

### 6.13 LIM-013 — Browser Compatibility and Accessibility

**Severity:** Medium

**Follow-up owner:** Architecture and testing owners

The browser presentation layer includes responsive styling and accessibility-oriented interface features.

These include:

- Form labels.
- Visible focus states.
- Responsive layouts.
- Structured headings.
- User-facing feedback.
- Reduced-motion styling.
- Status indicators.

The presence of these implementation features does not independently establish comprehensive accessibility compliance.

The release does not claim:

- Formal WCAG compliance certification.
- Comprehensive screen reader verification.
- Complete keyboard-only interaction verification.
- Verification across all browser versions.
- Verification across all mobile devices.
- Formal usability evaluation.

Mitigation:

- Preserve semantic HTML.
- Maintain visible focus indicators.
- Provide meaningful interface feedback.
- Avoid unnecessary visual complexity.
- Document unverified compatibility and accessibility behavior.

References:

- [HTML Interface](../../src/main/resources/static/index.html)
- [Frontend Stylesheet](../../src/main/resources/static/styles.css)

---

### 6.14 LIM-014 — Final Integrated Release Verification

**Severity:** High

**Follow-up owner:** Team lead, architecture owner, and testing owner

Local runtime verification was completed on November 24, 2026.

Observed results include:

- 38 successful frontend smoke checks.
- Zero failed frontend smoke checks.
- Six successfully created demonstration requests.
- Ten successful reviewer updates.
- Expected demonstration record visibility for both students and the reviewer.

The recorded local checkout commit is:

`f09bd551456c539c92b185dcbbabc0d29f192be6`

The recorded commit identifies the local checkout state reported by the smoke testing script.

It is not independent proof of the exact running application binary.

The merged revision has its own regression, CI, and Chromium results in section 7.4. The following release gates remain open:

- Independent technical review.
- Final release authorization.
- Exact final release tag alignment.

Mitigation:

- Preserve observed runtime evidence.
- Identify the approved final repository revision.
- Reconcile final CI evidence with that revision.
- Document remaining verification dispositions.
- Obtain independent review.
- Record the final release decision.
- Verify that the release tag identifies the approved baseline.

References:

- [Final Release Notes](final-release-notes.md)
- [Changed Files Since Assignment 5](changed-files-since-a5.md)
- [Final Test Evidence](../testing/final-test-evidence.md)

---

### 6.15 LIM-015 — Performance and Capacity

**Severity:** High outside classroom scope

**Follow-up owner:** Architecture and testing owners

CampusConnect is intended for bounded classroom demonstration workloads.

The available evidence does not establish:

- Sustained high-volume request processing.
- Large-scale concurrent user capacity.
- Production response-time guarantees.
- Database scaling behavior.
- Long-duration availability.
- Institutional workload capacity.
- Formal performance service-level objectives.

Successful local request creation and retrieval do not independently establish production capacity.

Mitigation:

- Restrict use to the agreed classroom scope.
- Avoid unsupported performance guarantees.
- Preserve the existing transaction and consistency controls.
- Treat future capacity evaluation as separate engineering work.

References:

- [Architecture Overview](../architecture/architecture.md)
- [Project Scope](../planning/scope.md)
- [Risk Register](../planning/risk-register.md)

---

### 6.16 LIM-016 — Dependency and Security Assurance

**Severity:** High outside classroom scope

**Follow-up owner:** Quality, architecture, and release owners

CampusConnect relies on Java, Spring Boot, Maven-managed dependencies, JDBC, and H2.

Historical dependency inventory and licensing evidence exists from earlier engineering cycles.

The browser presentation layer does not introduce a separate frontend package manager or external JavaScript framework.

However, historical dependency evidence does not automatically establish that the final Assignment 6 dependency set is free from security, maintenance, or licensing concerns.

The release does not claim:

- Comprehensive vulnerability clearance.
- Formal penetration testing certification.
- Complete dependency security assurance.
- Institutional security approval.
- Legal compliance certification.
- Production security authorization.

Mitigation:

- Preserve dependency inventory evidence.
- Record relevant dependency findings and dispositions.
- Maintain human review of security-sensitive changes.
- Avoid unnecessary dependencies.
- Restrict deployment to the documented classroom boundary.

References:

- [Historical Dependency Disposition](../quality/a5-dependency-disposition.md)
- [Historical Dependency Assessment](../reviews/a4-build-dependency-assessment.md)
- [Risk Register](../planning/risk-register.md)

---

## 7. Observed Cycle 2 Verification Evidence

### 7.1 Verification Environment

| Field | Recorded Value |
|---|---|
| Application | CampusConnect |
| Current Application Address | `http://campusconnect.local:8080` |
| Execution Date | November 24, 2026 |
| Environment | Local macOS development environment |
| Local Integration Branch | `a6-local-integration` |
| Recorded Checkout Commit | `f09bd551456c539c92b185dcbbabc0d29f192be6` |

The current application address describes the locally configured hostname.

The original verification scripts targeted the local application through its loopback interface.

The recorded checkout commit does not independently establish the exact running application binary.

### 7.2 Frontend Smoke Testing

| Verification Area | Passed | Failed |
|---|---:|---:|
| Application Availability | 2 | 0 |
| Frontend Resources | 7 | 0 |
| Required Interface Elements | 11 | 0 |
| Unauthorized API Access | 4 | 0 |
| Student A Session | 3 | 0 |
| Student B Session | 3 | 0 |
| Reviewer Session | 3 | 0 |
| Identity Switching | 4 | 0 |
| Missing Resource Handling | 1 | 0 |
| **Total** | **38** | **0** |

Observed result: PASS.

The execution supports frontend resource availability, synthetic session behavior, role-specific API permissions, and missing-resource handling.

### 7.3 Demonstration Data Verification

| Verification Item | Observed Result |
|---|---:|
| Synthetic identities selected | 3 |
| New demonstration requests created | 6 |
| Existing requests skipped | 0 |
| Confirmed reviewer updates | 10 |
| Student A accessible demonstration records | 3 |
| Student B accessible demonstration records | 3 |
| Reviewer accessible demonstration records | 6 |

Observed demonstration status distribution:

- Submitted: 2.
- In Progress: 2.
- Resolved: 2.

The demonstration setup completed successfully.

These results support successful request creation, reviewer status changes, resolution note saving, and role-specific retrieval of the prepared demonstration records.

---

### 7.4 Merged Candidate Verification

The November 24 merged candidate `373a0f6b6d6190d6067099178a8c71862bb12dec` passed [GitHub CI](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/45509064331).
On a clean checkout of that revision, Codex ran 51 Maven tests and 38 HTTP smoke
checks with zero failures. The Chromium exercise covered student submission
and isolation, reviewer status and note updates, stale-edit rejection, and
owner visibility. Demo setup created six requests on the first run. A repeat
run skipped all six and made zero reviewer updates. [Candidate evidence in #112](https://github.com/victordodan/-comp330-f26-team-02/blob/a6-quality-defect-review-gherrera/docs/testing/final-test-evidence.md#november-24-browser-candidate)

Final acceptance, independent integrated review, release authorization, and
tag verification remain pending.

---

## 8. Verification Boundaries and Remaining Gaps

| Verification Area | Available Evidence | Remaining Boundary |
|---|---|---|
| Frontend resource availability | 38 passing local smoke checks | Final release revision reconciliation |
| Synthetic identity permissions | Observed HTTP access restrictions | Production authentication remains outside scope |
| Demonstration request creation | Six successful creations | Broader request scenarios require separate evidence |
| Reviewer modifications | Ten confirmed updates | Comprehensive browser interaction coverage |
| Student record visibility | Three owned demonstration records per student | Additional record-level negative-path evidence |
| Reviewer queue visibility | Six accessible demonstration records | Production authorization is not established |
| Browser interaction | Chromium workflow passed on merged main | Broader browser, device, and accessibility coverage |
| Database persistence | Successful storage and retrieval during execution | Cycle 2 restart verification and backup restoration |
| Duplicate prevention | Sequential repeat skipped all six matching examples and made zero updates | Concurrent duplicate prevention |
| Demonstration recovery | Successful complete execution | Interrupted execution recovery |
| Regression suite | 51 Maven tests and GitHub CI passed on merged main | Confirm on the approved final revision |
| CI | Existing repository CI infrastructure and historical evidence | Exact final release revision verification |
| Dependency assurance | Historical dependency evidence | Final dependency disposition |
| Accessibility | Accessibility-oriented implementation | Formal compliance and assistive technology verification |
| Independent review | Existing engineering review history | Final integrated review confirmation |
| Release authorization | Historical Assignment 5 conditional decision | Separate Assignment 6 authorization |

Outstanding verification must remain documented as pending, deferred, or explicitly accepted according to the final team release decision.

Unperformed verification must not be represented as successful.

---

## 9. Defect Management

Known limitations and confirmed defects are distinct engineering categories.

A limitation describes a documented implementation boundary, deferred capability, or verification constraint.

A defect describes an observed deviation from an applicable requirement or expected behavior.

The recorded frontend smoke testing execution reported zero failed checks.

The demonstration setup completed successfully.

These results do not establish that the application contains no unresolved defects.

Confirmed defects should include:

- Defect identifier.
- Description.
- Reproduction information.
- Affected requirement or component.
- Severity.
- Assigned owner.
- Mitigation or correction.
- Verification evidence.
- Final disposition.

Authoritative defect records are maintained in:

- [Defect Log](../quality/defect-log.md)
- [Defect Review](../quality/defect-review.md)
- [Final Defect Summary](../quality/defect-review.md)
- [Risk Register](../planning/risk-register.md)

This document does not replace those records.

---

## 10. Security and Data Handling Restrictions

CampusConnect must remain within its documented synthetic classroom demonstration scope.

The application must not be used to process:

- Real student educational records.
- University account credentials.
- Personally identifiable information.
- Confidential institutional requests.
- Medical information.
- Financial information.
- Other regulated or sensitive institutional data.

The local browser interface does not establish institutional authentication, transport security, regulatory compliance, or production deployment approval.

The server's default loopback binding is a deployment boundary rather than a complete production security architecture.

The operator must preserve the local synthetic-data restriction.

---

## 11. Deferred Functionality

The following capabilities are not established as implemented or approved deliverables of the current release:

- University single sign-on.
- Production identity management.
- Automated email or push notifications.
- Public deployment.
- Production infrastructure.
- High availability.
- Automated production recovery.
- Comprehensive database backup restoration.
- Distributed request processing.
- Production-scale performance guarantees.
- Comprehensive accessibility certification.
- Automatic browser draft recovery.
- Atomic demonstration data duplicate prevention.
- Automatic whole-script demonstration rollback.

Future implementation requires separate prioritization, architecture evaluation, requirements definition, verification, and approval.

Listing a deferred capability does not establish a commitment to implement it.

---

## 12. Risk Ownership and Release Disposition

| Limitation ID | Proposed Follow-up Owner |
|---|---|
| LIM-001 | Architecture and testing owners |
| LIM-002 | Architecture owner and team lead |
| LIM-003 | Architecture and requirements owners |
| LIM-004 | Architecture and testing owners |
| LIM-005 | Architecture and release owners |
| LIM-006 | Architecture owner |
| LIM-007 | Architecture owner |
| LIM-008 | Architecture and testing owners |
| LIM-009 | Architecture and release owners |
| LIM-010 | Architecture owner |
| LIM-011 | Architecture and operations owners |
| LIM-012 | Architecture and operations owners |
| LIM-013 | Architecture and testing owners |
| LIM-014 | Team lead, architecture owner, and testing owner |
| LIM-015 | Architecture and testing owners |
| LIM-016 | Quality, architecture, and release owners |

Proposed ownership identifies appropriate coordination responsibilities.

It does not independently establish that each owner has accepted the assigned risk or follow-up obligation.

Final ownership and disposition must be reconciled with:

[Risk Register](../planning/risk-register.md)

The team lead retains responsibility for recording the final classroom release authorization.

---

## 13. Historical Correction Record

### 13.1 Cycle 1 Corrections

The merged PR #83 correction removed inaccurate or unsupported statements concerning:

- In-memory authoritative request storage.
- Header-based identity authority.
- Unsupported build performance guarantees.
- Premature Assignment 5 baseline confirmation.
- Premature defect-clearance statements.
- Unapproved Cycle 2 implementation commitments.

PR #94 preserved these corrections and added technical disclosures concerning:

- Local database persistence and recovery.
- Synthetic session authority.
- Deliberate reviewer identity selection.
- Requester notification limitations.

These historical corrections remain applicable where the underlying implementation has not changed.

### 13.2 Cycle 2 Corrections

The Assignment 6 revision updates the limitation record to reflect:

- Implemented browser presentation layer.
- Responsive frontend styling.
- Browser-based REST interaction.
- Student and reviewer dashboards.
- Frontend smoke testing results.
- Synthetic demonstration data preparation.
- Browser session-sharing limitations.
- Temporary interface state.
- Reviewer concurrency handling.
- Demonstration setup persistence.
- Duplicate detection limitations.
- Partial setup recovery limitations.
- Local hostname configuration.
- Browser compatibility and accessibility boundaries.
- Final integrated release verification requirements.

The original limitation identifiers LIM-001 through LIM-005 remain preserved.

LIM-004 has been revised from the historical absence of a verified graphical interface to the remaining browser interaction verification boundary.

New identifiers LIM-006 through LIM-016 extend the limitation record to address Cycle 2 implementation and release considerations.

---

## 14. AI Assistance and Human Accountability

AI assistance contributed to drafting and organizing this limitation record.

The documentation incorporates existing repository implementation information, historical limitation disclosures, architecture decisions, and observed local runtime verification results.

Human engineering responsibilities include:

- Reviewing implementation accuracy.
- Reviewing identified limitations.
- Evaluating severity classifications.
- Confirming proposed ownership.
- Reconciling the risk register.
- Reviewing observed runtime evidence.
- Approving final risk dispositions.
- Authorizing the final release.

AI-generated documentation does not constitute:

- Independent technical review.
- Security clearance.
- Defect clearance.
- Production certification.
- Formal risk acceptance.
- Final release authorization.

The AI-use record is maintained in:

[AI Use Log](../ai/ai-use-log.md)

---

## 15. Final Risk Acceptance

| Field | Status |
|---|---|
| Final Assignment 6 Release Revision | Pending |
| Final CI Evidence | Pending Confirmation |
| Independent Integrated Review | Pending Confirmation |
| Final Defect Dispositions | Pending Confirmation |
| Residual Risk Acceptance | Pending |
| Release Authorization Owner | Team Lead |
| Release Authorization Date | Pending |
| Final Release Tag | `a6-final-release` — verification pending |

The proposed limitations and mitigations in this document must receive appropriate team disposition before being represented as formally accepted release risks.

The historical Assignment 5 conditional release decision does not independently authorize the Assignment 6 release.

---

## 16. Final Release Boundary

CampusConnect Cycle 2 provides an implemented university support request workflow with a browser-based presentation layer, server-controlled authorization, structured workflow rules, optimistic concurrency, and persistent local storage.

Observed local runtime verification supports frontend resource availability, synthetic identity permissions, request creation, reviewer modifications, and role-specific demonstration record visibility.

The application remains a bounded classroom demonstration.

Known limitations, outstanding verification, deferred functionality, and residual risks must remain visible within the final release package.

The objective is to provide an accurate and defensible engineering baseline rather than claim unrestricted production readiness.

Final release authorization remains subject to the team's documented approval process.
