# CampusConnect — A5 Requirements and Release Traceability

**Team:** Codex Ramblers | **Quality review:** Giancarlo Herrera  
**Updated:** 2026-11-09 | **Cycle:** 1 | **Status:** Evidence reconciliation, pending A5 approval  
**Historical baseline:** A4 main `70d3726b64c0deafef2d770870bb14ef8f28b79b`  
**Final A5 SHA/tag:** Not frozen; do not treat this as release acceptance.

This A5 view supersedes stale A3/A4 *implementation pending* descriptions in the prior traceability ledger while preserving historical evidence through the links below. The requirements and acceptance criteria remain marked **Proposed** in their source documents; this table does not change their approval status.

## Requirement-to-evidence crosswalk

| Requirement | Acceptance cases | Implemented evidence at A4 | Historical verification | Final A5 disposition |
|---|---|---|---|---|
| REQ-001 — submit and validate requests | TC-001, TC-002 | [RequestValidatorTest](../../src/test/java/edu/loyola/campusconnect/validation/RequestValidatorTest.java), [RequestControllerTest](../../src/test/java/edu/loyola/campusconnect/controller/RequestControllerTest.java) | A4 reported passing automated tests | Pending dated case results, approved requirement, final SHA |
| REQ-002 — student view and isolation | TC-003, TC-004, TC-005 | [RequestControllerTest](../../src/test/java/edu/loyola/campusconnect/controller/RequestControllerTest.java), [WorkflowServiceTest](../../src/test/java/edu/loyola/campusconnect/service/WorkflowServiceTest.java) | A4 mapped student owner isolation, note/status behavior | Pending final-SHA authorization and case evidence |
| REQ-003 — persistence and managed fields | TC-006, TC-007 | [WorkflowServiceTest](../../src/test/java/edu/loyola/campusconnect/service/WorkflowServiceTest.java), [runtime properties](../../src/main/resources/application.properties) | A4 reported file-backed H2/restart and rollback smoke checks | Pending final-SHA restart proof |
| REQ-004 — reviewer visibility/permissions | TC-008, TC-009 | [RequestControllerTest](../../src/test/java/edu/loyola/campusconnect/controller/RequestControllerTest.java) | A4 mapped reviewer access and negative role/header tests | Pending final-SHA case results; demo-only identity boundary |
| REQ-005 — lifecycle transitions | TC-010, TC-011 | [WorkflowServiceTest](../../src/test/java/edu/loyola/campusconnect/service/WorkflowServiceTest.java), [RequestControllerTest](../../src/test/java/edu/loyola/campusconnect/controller/RequestControllerTest.java) | A4 mapped resolve/reopen, invalid transitions, concurrency | Pending final-SHA tests and case disposition |
| REQ-006 — reviewer note management | TC-012 | [RequestControllerTest](../../src/test/java/edu/loyola/campusconnect/controller/RequestControllerTest.java), [WorkflowServiceTest](../../src/test/java/edu/loyola/campusconnect/service/WorkflowServiceTest.java) | A4 mapped note validation and preservation | Pending final-SHA case execution |

## Traceability chain and source of truth

1. **Requirements/acceptance:** [requirements](../requirements/requirements.md), [acceptance criteria](../requirements/acceptance-criteria.md); source status is **Proposed** pending explicit requirements-owner/team acceptance.
2. **Case catalog:** [TC-001–TC-012](../testing/test-cases.md); 12 individually dated executions **not recorded** in reviewed A4 checkpoint.
3. **Historical implementation verification:** [A4 backend verification](../testing/a4-backend-verification.md) reports 35 passing tests; [A4 quality checkpoint](../testing/a4-final-quality-verification.md) links [historical CI](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/41000100733) at application-code commit `d2bf5bfded8fcc03f3509394fa215b7f223efd94`, **not** A5 final SHA.
4. **A5 acceptance mapping:** [PR #88](https://github.com/victordodan/-comp330-f26-team-02/pull/88), draft `docs/testing/a5-acceptance-evidence-matrix.md`; not yet accepted or merged.
5. **Defect/limitation disposition:** [defect log](../quality/defect-log.md), [defect review](../quality/defect-review.md), [PR #87](https://github.com/victordodan/-comp330-f26-team-02/pull/87), [PR #83](https://github.com/victordodan/-comp330-f26-team-02/pull/83).
6. **Dependency/security:** [PR #89](https://github.com/victordodan/-comp330-f26-team-02/pull/89) and [A4 assessment](../reviews/a4-build-dependency-assessment.md); scan/license signoff pending.
7. **Release decision:** [PR #85 quality gate](https://github.com/victordodan/-comp330-f26-team-02/pull/85), [PR #86 release notes](https://github.com/victordodan/-comp330-f26-team-02/pull/86). No A5 GO or tag claimed.

## Outstanding linkages and decision owners

| Open item | Responsible role | Closure evidence |
|---|---|---|
| Requirement/acceptance status remains Proposed | Shelby (requirements), Victor (decision) | Recorded approval or explicit deferral |
| 12 dated acceptance executions | Mareddy (testing), Giancarlo (reconciliation) | Actual results, operator, environment, SHA, evidence |
| A5 CI and restart/concurrency checks | Shu/developers, Mareddy | Final-SHA CI/test and smoke links |
| PR #83 known limitations contradiction | Shu, Victor | Reviewed correction with technical evidence |
| PR #84 test cleanup | Developer, Mareddy | Verified test outcome |
| Dependency/CVE/license assessment | Victor-appointed reviewer | Dated scan and residual-risk acceptance |
| Independent final review and tag | Victor | Reviewer, exact SHA, release gate decision, tag |

**Limitations:** A4's 35 automated tests and successful historical CI are not evidence that the final A5 revision has passed. This document is a crosswalk, not an assertion of release readiness. No new tests or scans were run in preparing it.

## Historical planning evidence

Earlier A2–A4 planning relationships, estimates, risks, issue/PR assignments and ADR decisions remain available in the repository's Git history. This A5 revision prioritizes current requirement → implementation → test → release evidence and should be independently reviewed before merge.
