# Cycle 1 Test Plan — CampusConnect

**Team:** Codex Ramblers — Team 2  
**Assignment:** A3 Architecture and Review  
**Owner:** Giancarlo Herrera — Quality & Review Lead  
**Status:** Planned — No test execution claimed

## Purpose and Scope
This plan translates the six Cycle 1 requirements and twelve proposed acceptance criteria into verification work for the local CampusConnect support-request workflow. It is subordinate to [the test strategy](test-strategy.md), [acceptance criteria](../requirements/acceptance-criteria.md), and the accepted architecture decisions. The current A3 deliverable is a test plan, not evidence of completed implementation.

**In scope:** Student A/B request submission and owner-only retrieval; unique IDs; field validation; reviewer listing; allowed status transitions; editable reviewer note/resolution; simulated server-session role boundaries; local persistence and restart; failed writes and concurrency/duplicate-submission behavior.

**Out of scope:** Real university authentication, real student data, cloud deployment, notifications, external services, runtime AI, and production security certification.

## Environment and Data
**Target (not yet verified):** Local Java Spring Boot application with Thymeleaf pages, Maven, and file-backed H2 as specified by ADR-002. Automated tests may exercise service, repository, and integration boundaries; manual demonstrations exercise rendered views and session selection. The actual runtime, test framework configuration, CI setup, and database reset procedure must be recorded when available.

Use **synthetic data only**, with Student A, Student B, and Reviewer demo identities. Prepare valid and invalid title lengths (5–100), description lengths (10–2,000), the four allowed categories, and note lengths up to 2,000 characters. Preserve isolation between test runs; avoid real credentials or student information.

## Coverage and Execution Order
| Requirement | Planned Test Cases | Priority |
|---|---|---|
| REQ-001 — Submit request | TC-001, TC-002 | High |
| REQ-002 — Student-owned status and note | TC-003, TC-004, TC-005 | High |
| REQ-003 — Identifier and durable storage | TC-006, TC-007 | High |
| REQ-004 — Reviewer access and role restrictions | TC-008, TC-009 | High |
| REQ-005 — Status changes | TC-010, TC-011 | High |
| REQ-006 — Note/resolution updates | TC-012 | High |

The [test-case catalog](test-cases.md) maps each TC to one of the twelve acceptance criteria. Include negative-path checks for rejected inputs, role bypass, unsupported transitions, and storage failure. Prioritize owner isolation, authorization, integrity, and persistence before optional polish.

## Levels and Responsibilities
- **Unit/component:** Developers verify validation, status transitions, ownership, and note rules against the workflow service.
- **Integration:** Developers verify repository persistence, failed writes, restart behavior, and role context across components.
- **Contract and acceptance:** Giancarlo coordinates traceability review and checks that observable results match acceptance criteria; architecture and development owners provide executable evidence.
- **Security-focused negative checks:** Giancarlo and Shelby review role isolation, direct operation attempts, and error disclosure; results remain unverified until executed.
- **Team Lead:** Victor confirms review evidence and gate disposition.

## Entry Criteria
1. Team confirms the requirements/acceptance baseline and the relevant architecture decisions.
2. Executable application and test environment are available.
3. Synthetic test records, test isolation, and expected results are prepared.
4. Test ownership and a place for recording results/defects are agreed.

## Execution and Defect Handling
For each test, record date, commit SHA, environment, operator, actual outcome, evidence link, and **Pass / Fail / Blocked / Not Run**. A failed or blocked test is not a pass. Record defects in GitHub Issues with reproduction steps, expected/actual behavior, severity, owner, and linked retest evidence. Re-run affected cases after fixes and relevant regression cases before gate decisions.

## Exit Criteria and Evidence
The team should review all twelve criteria, explain every failure or unexecuted high-risk case, and link actual test results to the exact commit or build. A3 readiness requires **reviewable planning and traceability**, not passing tests. Later implementation/release gates must define and record their own pass thresholds and approvals.

Evidence belongs in [test cases](test-cases.md), [CI evidence](ci-evidence.md) (only when real runs exist), GitHub PRs/issues, and the [architecture review](../reviews/architecture-review.md).

## Schedule and Risks
Testing preparation follows A3 architecture review. Execute focused tests as implementation becomes available during Cycle 1, then integration and regression checks before later phase gates; refer to [schedule](../planning/schedule.md) for current dates. Risks include unsettled requirements status, unavailable runtime/CI, local database state contamination, and missing failure-path coverage. Re-plan if architecture, status rules, or persistence choices change.

**Current execution status:** All cases are **Not Run**. No passing tests, CI jobs, security verification, or completed implementation are asserted.
