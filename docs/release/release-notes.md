# CampusConnect — Cycle 1 Release Notes (A5 Candidate)

**Project:** CampusConnect | **Team:** Team 2 — Codex Ramblers
**Original preparer:** Giancarlo Herrera, Quality & Review Lead
**Reviewed checkpoint:** `d640366e6addcb495a3449cb59253e5cb12599cf` (AI-assisted correction; human review pending)
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

**Scope qualifier:** The requirement-to-implementation mapping above describes implemented code and bounded historical/integration evidence; it does not imply that every acceptance criterion was independently approved at the A5 frozen revision.

## Significant Changes and Baseline Comparison

The **A4 tag** captures the integrated backend, tests, planning and review documentation from the previous gate. The **A5 delta is not final**; compare `a4-construction-integration...a5-cycle1-release` only after the A5 tag exists. Do not describe this release-note update as a new application feature.

| Area | Release significance | Evidence |
|---|---|---|
| Backend workflow | Student submission/ownership and reviewer status/note lifecycle implemented by A4 | [A4 HTTP API](../testing/a4-http-api.md), [merged PRs](https://github.com/victordodan/-comp330-f26-team-02/pulls?q=is%3Apr+is%3Amerged) |
| Data reliability | H2 persistence, transaction rollback, restart and version-conflict behavior covered by A4 tests | [Backend verification](../testing/a4-backend-verification.md) |
| Review and release readiness | A5 will reconcile final revision, defects, acceptance results, CI, risk and AI disclosure | [Merged A5 quality gate](../quality/a5-final-release-quality-gate.md) |
| Changes since A4 | At this checkpoint: documentation/evidence corrections, consolidated CI and explicit controller-test imports; no production source/POM change | [Fixed comparison](https://github.com/victordodan/-comp330-f26-team-02/compare/a4-construction-integration...d640366e6addcb495a3449cb59253e5cb12599cf) |

## Verified integration CI evidence

[PR #95 run #4](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42746026082)
passed `mvn -B clean verify` with 35 tests and zero failures/errors/skips, including
successful packaging. [Job logs](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42746026082/job/129598203601)
identify tested merge `5d791a7b657209a6c688251149db92828b313606`, head
`e724e84af257a69237d51f8b6dab3d1927bd8cfb` and base
`6c6584162deb4c396f162670b39e4d48fdd548da`. #95 subsequently merged as
`4ff2f0f12e636f177ec25202b8029498e613afde`; the tested synthetic merge and
final main commit are distinct. This run supports the integration reviewed,
but is not final A5-tag verification or acceptance approval.

## Verification Summary

| Verification | Observed evidence | A5 release disposition |
|---|---|---|
| Automated tests | [A4 backend verification](../testing/a4-backend-verification.md) reports **35 passing tests**, zero failures/errors/skips in local sequential integration simulation | Historical baseline supplemented by PR #95 integration CI above; final candidate verification pending |
| GitHub CI | [Run 41000100733](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/41000100733) succeeded for `d2bf5bfded8fcc03f3509394fa215b7f223efd94`, the final A4 application-code merge | Historical baseline; merged #93 now supplies consolidated all-change CI, exercised by PR #95; final release SHA still pending |
| Packaged HTTP/restart | [A4 backend verification](../testing/a4-backend-verification.md) reports negative authorization, note/reopen, stale version and restart persistence checks | Repeat or document bounded A5 evidence on frozen revision |
| Acceptance cases TC-001–TC-012 | [A4 final quality verification](../testing/a4-final-quality-verification.md) maps all 12 to automated tests | Merged #92 records 0/12 separate case executions; obtain per-case evidence-backed dispositions, reusing automation where sufficient |
| Independent integrated review | [A4 findings](../reviews/a4-code-review-findings.md) and [Merged A5 quality gate](../quality/a5-final-release-quality-gate.md) | Independent non-author review of exact final A5 SHA **pending** |
| Dependency security/license | [A4 dependency assessment](../reviews/a4-build-dependency-assessment.md), [tree](../reviews/a4-build-dependency-tree.txt) | CVE/advisory and transitive-license/maintenance human acceptance **not yet evidenced** |

## Defects Resolved / Outstanding Findings

The [A4 code-review findings](../reviews/a4-code-review-findings.md) track CR-001 (authorization), CR-002 (workflow), CR-003 (persistence), CR-004 (dependencies), CR-005 (tests) and CR-006 (integration). A4 verification indicates implementation mitigations for the synthetic demo, but final human closure is not inferred. Merged #87/#91 provide the [finding register](../quality/defect-log.md) and [quality review](../quality/defect-review.md). Documentation discrepancies were corrected; no new runtime defect fix is asserted. Existing registers do not certify zero defects or final risk acceptance.

## Known Limitations, Mitigations and Residual Risks

| Limitation / risk | Impact and mitigation | A5 disposition |
|---|---|---|
| Synthetic identities rather than real authentication | Fixed demo student/reviewer selection cannot protect real student accounts; use synthetic records, loopback access, and no Internet deployment | **Accepted only for bounded demo if Victor signs off**; production authentication deferred |
| HTTP backend, no verified browser UI | Demo is through documented HTTP endpoints; do not promise a graphical interface | Use [HTTP API guide](../testing/a4-http-api.md); UI out of verified scope |
| Acceptance records incomplete in repository | Related automated tests do not independently grant all TC-001–TC-012 acceptance dispositions | Mareddy to provide exact-revision execution records or explicitly defer |
| A5 CI and integrated independent review pending | Recent integration CI exists; it does not establish final-candidate human review or acceptance | Obtain final SHA checks and non-author review before release GO |
| Dependency/advisory and transitive-license signoff missing | Unknown residual vulnerability, support or license risk | Named human review, scan/advisory evidence or documented conditional risk acceptance |
| Operational readiness beyond demo not verified | No claim of production deployment, monitoring, backup/restore validation, or real institutional integrations | Keep demonstration local; consider operational controls in Cycle 2 |

The authoritative [`known-limitations.md`](known-limitations.md) incorporates merged #83/#94/#95: LIM-001 local-store retention/recovery, LIM-002 deliberate synthetic role selection, LIM-003 notifications outside scope, LIM-004 HTTP interface limits and LIM-005 unverified production operations. These remain bounded limitations, not newly accepted production risks. The [risk register](../planning/risk-register.md) provides existing risk context.

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
| CI entry / reusable verification | [`ci.yml`](../../.github/workflows/ci.yml) / [`a4-validation-tests.yml`](../../.github/workflows/a4-validation-tests.yml) |
| Merged execution report | [`a5-acceptance-test-evidence.md`](../testing/a5-acceptance-test-evidence.md) |
| Current traceability | [`traceability.md`](../planning/traceability.md) |
| Current acceptance crosswalk | [`a5-acceptance-evidence-matrix.md`](../testing/a5-acceptance-evidence-matrix.md) |
| Current security assessment | [`a5-security-dependency-review.md`](../quality/a5-security-dependency-review.md) |
| Dependency inventory | [`a4-build-dependency-assessment.md`](../reviews/a4-build-dependency-assessment.md) |
| Review findings | [`a4-code-review-findings.md`](../reviews/a4-code-review-findings.md) |
| Risk register | [`risk-register.md`](../planning/risk-register.md) |
| AI-use disclosure | [`ai-use-log.md`](../ai/ai-use-log.md) |

## Submission and finalization work still open

These candidate notes are not the complete A5 submission. Finish the repository
release-evidence index, changed-file summary, final testing/CI index, reviewed
and rehearsed demo, presentation index/PPTX, Word summary with team information
and clone URL, and Cycle 2 handoff. Keep individual peer reviews private and
submit them separately. Main's demo script is starter content at this checkpoint.

#88 acceptance mappings and #89 security assessment are merged. They document
evidence and outstanding decisions; neither establishes completed acceptance
or dependency clearance.
#90's contributor/disclosure wording also awaits review. Requirements and
criteria remain Proposed until attributable approval or explicit disposition.

## Final Release Decision — Pending

**Current recommendation:** Do not label the A5 release as GO until the exact frozen SHA, non-author technical review, acceptance dispositions, CI and dependency-risk decisions are linked and Victor records the final **GO / CONDITIONAL GO / NO-GO** decision. At that point update the release date, exact tag/SHA, A4-to-A5 changed-file comparison, limitation IDs, test evidence and approval in this file **before** merging/tagging the final baseline.

**AI-use disclosure:** Codex assisted this correction using current source, merged evidence and CI results. Original Giancarlo draft attribution is retained without implying his personal verification of the correction. No new tests/scans, risk acceptance or release approval were performed; see [AI-use log](../ai/ai-use-log.md).
