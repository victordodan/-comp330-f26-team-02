# CampusConnect — Cycle 1 Release Notes (A5 Candidate)

**Project:** CampusConnect | **Team:** Team 2 — Codex Ramblers  
**Prepared:** 2026-11-09 by Giancarlo Herrera, Quality & Review Lead (AI-assisted draft; human review required)  
**Release gate:** Assignment 5 — Cycle 1 Release Presentation and Final Evidence  
**Candidate status:** **NOT YET APPROVED / NOT YET FROZEN**  
**Prior verified baseline:** [A4 `a4-construction-integration`](https://github.com/victordodan/-comp330-f26-team-02/releases/tag/a4-construction-integration), commit `70d3726b64c0deafef2d770870bb14ef8f28b79b`.  
**A5 baseline:** `a5-cycle1-release` **to be created only after final reviewed changes and evidence are merged**; exact SHA not yet available.  
**Environment:** Java 17 / Spring Boot 3.5.16 backend with file-backed H2; synthetic local/demo identity selection, not an institutional or Internet production deployment.

## Release Summary

CampusConnect Cycle 1 delivers a bounded support-request HTTP workflow for classroom demonstration. A student demo identity can create requests and inspect its own request status and notes. A reviewer demo identity can inspect submitted requests, update status, save resolution notes and reopen resolved requests. Server-side authorization boundaries, validation, persistent storage, optimistic version checks and automated tests support this demo scope. **This is not a production-ready campus service.** A5 release approval is pending exact-revision independent review, acceptance dispositions, final CI and dependency/risk decisions.

## Included Capabilities

| Capability | Requirements | Evidence / current scope |
|---|---|---|
| Submit support request with validation | REQ-001 | [Controller tests](../../src/test/java/edu/loyola/campusconnect/controller/RequestControllerTest.java), [validator tests](../../src/test/java/edu/loyola/campusconnect/validation/RequestValidatorTest.java) |
| Student views own status and saved notes | REQ-002 | [HTTP API](../testing/a4-http-api.md), [controller tests](../../src/test/java/edu/loyola/campusconnect/controller/RequestControllerTest.java) |
| Unique request ID and persistent storage | REQ-003 | [Repository](../../src/main/java/edu/loyola/campusconnect/repository/JdbcRequestRepository.java), [service tests](../../src/test/java/edu/loyola/campusconnect/service/WorkflowServiceTest.java) |
| Reviewer reads requests | REQ-004 | [Controller/service tests](../../src/test/java/edu/loyola/campusconnect/controller/RequestControllerTest.java) |
| Reviewer changes status, including reopen | REQ-005 | [Workflow service](../../src/main/java/edu/loyola/campusconnect/service/WorkflowService.java), [backend verification](../testing/a4-backend-verification.md) |
| Reviewer saves a note or resolution | REQ-006 | [Controller/service tests](../../src/test/java/edu/loyola/campusconnect/service/WorkflowServiceTest.java) |
| Demo identity ownership checks and optimistic concurrency | Cross-cutting | [A4 quality review](../testing/a4-final-quality-verification.md), [backend verification](../testing/a4-backend-verification.md) |

**Scope qualifier:** The requirement-to-implementation mapping above describes code and A4 verification evidence; it does not imply that every acceptance criterion was independently approved at the A5 frozen revision.

## Significant Changes and Baseline Comparison

The **A4 tag** captures the integrated backend, tests, planning and review documentation from the previous gate. The **A5 delta is not final**; compare `a4-construction-integration...a5-cycle1-release` only after the A5 tag exists. Do not describe this release-note update as a new application feature.

| Area | Release significance | Evidence |
|---|---|---|
| Backend workflow | Student submission/ownership and reviewer status/note lifecycle implemented by A4 | [A4 HTTP API](../testing/a4-http-api.md), [merged PRs](https://github.com/victordodan/-comp330-f26-team-02/pulls?q=is%3Apr+is%3Amerged) |
| Data reliability | H2 persistence, transaction rollback, restart and version-conflict behavior covered by A4 tests | [Backend verification](../testing/a4-backend-verification.md) |
| Review and release readiness | A5 will reconcile final revision, defects, acceptance results, CI, risk and AI disclosure | [A5 quality gate PR #85](https://github.com/victordodan/-comp330-f26-team-02/pull/85) (open when drafted) |
| Changes since A4 | **Pending final A5 comparison and review** | [Compare A4 to main](https://github.com/victordodan/-comp330-f26-team-02/compare/a4-construction-integration...main) |

## Verification Summary

| Verification | Observed evidence | A5 release disposition |
|---|---|---|
| Automated tests | [A4 backend verification](../testing/a4-backend-verification.md) reports **35 passing tests**, zero failures/errors/skips in local sequential integration simulation | **Historical A4 evidence**; re-run and link A5 exact-SHA results |
| GitHub CI | [Run 41000100733](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/41000100733) succeeded for `d2bf5bfded8fcc03f3509394fa215b7f223efd94`, the final A4 application-code merge | **Historical A4 CI**, not evidence for final A5 SHA |
| Packaged HTTP/restart | [A4 backend verification](../testing/a4-backend-verification.md) reports negative authorization, note/reopen, stale version and restart persistence checks | Repeat or document bounded A5 evidence on frozen revision |
| Acceptance cases TC-001–TC-012 | [A4 final quality verification](../testing/a4-final-quality-verification.md) maps all 12 to automated tests | **No separately dated case-specific execution records in A4 repository**; obtain A5 actual pass/fail/blocked/deferred evidence |
| Independent integrated review | [A4 findings](../reviews/a4-code-review-findings.md) and [A5 proposed quality gate](https://github.com/victordodan/-comp330-f26-team-02/pull/85) | Independent non-author review of exact final A5 SHA **pending** |
| Dependency security/license | [A4 dependency assessment](../reviews/a4-build-dependency-assessment.md), [tree](../reviews/a4-build-dependency-tree.txt) | CVE/advisory and transitive-license/maintenance human acceptance **not yet evidenced** |

## Defects Resolved / Outstanding Findings

The [A4 code-review findings](../reviews/a4-code-review-findings.md) track CR-001 (authorization), CR-002 (workflow), CR-003 (persistence), CR-004 (dependencies), CR-005 (tests) and CR-006 (integration). A4 verification indicates implementation mitigations for the synthetic demo, but final human closure is not inferred. **No final A5 defect IDs or fixes are asserted here**; link the approved [defect review](../quality/defect-review.md) when the team creates it. Do not claim “zero defects” from an empty log.

## Known Limitations, Mitigations and Residual Risks

| Limitation / risk | Impact and mitigation | A5 disposition |
|---|---|---|
| Synthetic identities rather than real authentication | Fixed demo student/reviewer selection cannot protect real student accounts; use synthetic records, loopback access, and no Internet deployment | **Accepted only for bounded demo if Victor signs off**; production authentication deferred |
| HTTP backend, no verified browser UI | Demo is through documented HTTP endpoints; do not promise a graphical interface | Use [HTTP API guide](../testing/a4-http-api.md); UI out of verified scope |
| Acceptance records incomplete in repository | Automated mapping does not prove separate TC-001–TC-012 manual executions | Mareddy to provide exact-revision execution records or explicitly defer |
| A5 CI and integrated independent review pending | A4 results cannot certify the final A5 candidate | Obtain final SHA checks and non-author review before release GO |
| Dependency/advisory and transitive-license signoff missing | Unknown residual vulnerability, support or license risk | Named human review, scan/advisory evidence or documented conditional risk acceptance |
| Operational readiness beyond demo not verified | No claim of production deployment, monitoring, backup/restore validation, or real institutional integrations | Keep demonstration local; consider operational controls in Cycle 2 |

The authoritative limitations file [`known-limitations.md`](known-limitations.md) is currently being revised separately in [PR #83](https://github.com/victordodan/-comp330-f26-team-02/pull/83); reconcile wording and IDs after that PR merges. The [risk register](../planning/risk-register.md) provides existing risk context.

## Deployment / Operational Notes

- Use the [HTTP API guide](../testing/a4-http-api.md) and source configuration [`application.properties`](../../src/main/resources/application.properties) to prepare a local demo. The backend is intended for loopback/local demonstration with synthetic data.
- The A4 backend verification reports successful restart persistence using a temporary file-backed H2 database. This is **not** evidence of production backup or disaster recovery.
- Do not enter real student information or expose the demo as a public service.
- Any post-freeze code or evidence change requires a documented reason, retest/review and updated release baseline.

## Deferred or Unverified for This Release

Institutional authentication/authorization integration, production deployment, verified graphical UI, complete operational monitoring/recovery, and final A5 acceptance/dependency assurance are **not included as verified Cycle 1 release capabilities**. These are candidate Cycle 2 follow-ups subject to team prioritization, not approved promises.

## Evidence References

| Evidence | Location |
|---|---|
| A4 phase gate | [`a4-construction-integration`](https://github.com/victordodan/-comp330-f26-team-02/releases/tag/a4-construction-integration) |
| A5 quality closure checklist | [PR #85](https://github.com/victordodan/-comp330-f26-team-02/pull/85) |
| Backend test and runtime record | [`a4-backend-verification.md`](../testing/a4-backend-verification.md) |
| Acceptance mapping | [`a4-final-quality-verification.md`](../testing/a4-final-quality-verification.md) |
| Test case catalog | [`test-cases.md`](../testing/test-cases.md) |
| CI workflow | [`a4-validation-tests.yml`](../../.github/workflows/a4-validation-tests.yml) |
| Dependency inventory | [`a4-build-dependency-assessment.md`](../reviews/a4-build-dependency-assessment.md) |
| Review findings | [`a4-code-review-findings.md`](../reviews/a4-code-review-findings.md) |
| Risk register | [`risk-register.md`](../planning/risk-register.md) |
| AI-use disclosure | [`ai-use-log.md`](../ai/ai-use-log.md) |

## Final Release Decision — Pending

**Current recommendation:** Do not label the A5 release as GO until the exact frozen SHA, non-author technical review, acceptance dispositions, CI and dependency-risk decisions are linked and Victor records the final **GO / CONDITIONAL GO / NO-GO** decision. At that point update the release date, exact tag/SHA, A4-to-A5 changed-file comparison, limitation IDs, test evidence and approval in this file **before** merging/tagging the final baseline.

**AI-use disclosure:** ChatGPT assisted Giancarlo in drafting and organizing these release notes from repository evidence on 2026-11-09. The human team must verify technical claims, ownership and the final A5 baseline before approving the PR; record this activity in [`docs/ai/ai-use-log.md`](../ai/ai-use-log.md).
