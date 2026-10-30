# A4 Code Review Findings and AI-Assisted Review Disposition

**Prepared by:** Giancarlo Herrera, Quality & Review Lead  
**Snapshot:** October 29, 2026  
**Status:** Evidence-based review summary; findings remain open until corrected and re-reviewed.

## Scope and AI disclosure

ChatGPT was used to help organize this A4 review summary and documentation; the human reviewer must check the linked PR diffs and findings before treating them as approved. This document **does not claim that Shu's code was AI-generated**: no such claim is established by the reviewed PR evidence. See [AI use log](../ai/ai-use-log.md) for existing team disclosures. No automated AI-generated fix or vulnerability scan was executed for this document.

## Review evidence and findings

| ID | Review lens / severity | Evidence and risk | Required fix direction | Proof before closure |
|---|---|---|---|---|
| CR-001 | Security / High | [PR #72](https://github.com/victordodan/-comp330-f26-team-02/pull/72), [Mareddy authorization review](https://github.com/victordodan/-comp330-f26-team-02/pull/72#discussion_r4417431922): GET-all and PATCH endpoints lack visible server-enforced role/ownership checks; REQ-002/REQ-004 | Enforce simulated role and student ownership on the server, including service-level checks; do not trust a client-supplied studentID as authorization | Negative student A/B isolation and unauthorized reviewer-action tests; re-review |
| CR-002 | Correctness / High | [PR #72](https://github.com/victordodan/-comp330-f26-team-02/pull/72), [status finding](https://github.com/victordodan/-comp330-f26-team-02/pull/72#discussion_r4417458265): initial status previously used `New`; later branch uses `Submitted` but includes states beyond accepted model | Reconcile DTO, controller, service with accepted Submitted/In Progress/Resolved/reopen workflow | State-transition tests, required note and invalid transition tests; re-review |
| CR-003 | Architecture / High | [PR #71](https://github.com/victordodan/-comp330-f26-team-02/pull/71) uses in-memory service state while ADR-002/004 call for file-backed H2 | Implement accepted persistence or document approved deviation in ADR and limitations | Restart/recovery test, transaction and data-integrity evidence |
| CR-004 | Dependencies / Medium | [PR #76](https://github.com/victordodan/-comp330-f26-team-02/pull/76) adds Spring Boot 3.2.5 and Java 17, [dependency review](https://github.com/victordodan/-comp330-f26-team-02/pull/76#pullrequestreview-5706305897) | Integrate compatible POM; record exact versions/scopes, licenses, advisories and dependency tree | Integrated CI, startup check and reviewed dependency inventory |
| CR-005 | Tests / High | [PR #67](https://github.com/victordodan/-comp330-f26-team-02/pull/67) covers six validator JUnit cases; no claim of TC-001–TC-012 execution | Add acceptance, negative/security, integration, conflict and recovery coverage | Run-specific logs, links and mapped test cases |
| CR-006 | Maintainability / Medium | Multiple open interdependent PRs [#69, #71–73, #76–77](https://github.com/victordodan/-comp330-f26-team-02/pulls) | Integrate in dependency order, keep PRs reviewable, explain any architecture/contract changes | Passing CI on integrated commit and reviewer sign-off |

## AI-assisted review lenses

- **Correctness:** Input rules and accepted workflow state transitions must be reflected in code and negative tests.
- **Security:** Simulated roles are not production authentication; enforce server-side isolation and reviewer-only actions.
- **Architecture:** Accepted service/repository responsibilities, H2 persistence and version/transaction rules require implementation evidence.
- **Maintainability:** DTO/service/controller contracts and error handling should be understandable and consistent.
- **Dependencies:** Spring Boot/Maven additions require version, license and advisory review; no scan is claimed.
- **Tests:** Unit validator passes do not substitute for integrated security, persistence and acceptance tests.
- **Evidence/stewardship:** Reviewer comments and CI results must be tied to exact PR commits and human decisions.

## Review disposition

[PR #67](https://github.com/victordodan/-comp330-f26-team-02/pull/67) was reviewed and merged with focused test evidence. [PR #72](https://github.com/victordodan/-comp330-f26-team-02/pull/72) has formal changes-requested reviews, with no closure inferred. [PR #76](https://github.com/victordodan/-comp330-f26-team-02/pull/76) received a COMMENT review focused on supply-chain evidence. **No approval of open implementation PRs is given by this document.** Link author responses, fixes, tests, and final merge/no-merge decisions when available.


## Re-review of Shu's October 30 revised heads

**Review scope:** PR #69 `74608ffa1`, #71 `c9c5d19b4`, #72 `ef15ad649`, #73 `94d9b9cee`, #76 `70b52d6`, and #77 `603df1cd7`. All six head-specific GitHub Actions `test` checks reported success. These are *separate PR branch checks*, not a post-merge `main` integration run.

| Finding | Current evidence | Updated disposition |
|---|---|---|
| CR-001 role and ownership enforcement | [PR #72](https://github.com/victordodan/-comp330-f26-team-02/pull/72) now selects fixed demo identity via server session; controller routes student/reviewer operations separately; [WorkflowService](https://github.com/victordodan/-comp330-f26-team-02/pull/72/files) checks ownership and reviewer role | **Implementation addressed for synthetic demo scope; final closure pending integrated test evidence and human re-review.** Fixed identity switching is not production authentication. |
| CR-002 workflow transitions | [PR #71](https://github.com/victordodan/-comp330-f26-team-02/pull/71) service permits Submitted → In Progress → Resolved and Resolved → In Progress; nonblank resolution note required | **Implementation addressed; integrated acceptance verification pending.** |
| CR-003 persistence/concurrency | [PR #71](https://github.com/victordodan/-comp330-f26-team-02/pull/71) adds file-backed H2, transaction template, version checks, restart/concurrency/rollback tests | **Implementation and branch test evidence present; mainline integration pending.** |
| CR-004 dependencies | [PR #76](https://github.com/victordodan/-comp330-f26-team-02/pull/76) now pins Spring Boot 3.5.16, Java 17, and records dependency tree/licenses | **Build evidence improved; advisory/CVE and support acceptance still pending.** |
| CR-005 tests | [PRs #71, #72, #77](https://github.com/victordodan/-comp330-f26-team-02/pulls) add service, controller and startup tests | **New automated tests present and branch CI green; TC-001–012 catalog execution and final main CI remain unverified.** |
| CR-006 integration | Six interdependent PRs include overlapping POM, source and test files | **Open until merge sequence, conflict handling and combined-main CI verified.** |

**Review outcome:** Reassess earlier change requests against these exact heads; do not silently treat previous approvals or objections as current. No open implementation PR is approved by this document. The next required review is an independent examination of the integrated mainline code and test results, followed by updated links and final disposition.
