# Continuous Integration (CI) Evidence — CampusConnect

**Project:** CampusConnect  
**Team:** Team 2 — Codex Ramblers  
**Phase:** A3 — Cycle 1 Architecture and Review  
**Documentation Owner:** Giancarlo Herrera — Quality & Review Lead  
**Status:** CI not configured for project-specific build or tests; no successful CI verification claimed  
**Review Date:** October 16, 2026

## 1. Current CI Configuration

The repository currently contains [`.github/workflows/ci.yml`](../../.github/workflows/ci.yml). This is the **Starter Kit guard workflow**, named **CI - Configuration Required**. It is **not** a project-specific CI pipeline.

| Item | Current Verified Repository Configuration |
|---|---|
| Workflow file | `.github/workflows/ci.yml` |
| Workflow name | `CI - Configuration Required` |
| Trigger | Manual `workflow_dispatch` only |
| Job | `ci-not-configured` |
| Runner | `ubuntu-latest` |
| Behavior | Prints a configuration-required error and exits with code 1 |
| Project build | Not configured |
| Automated project tests | Not configured |
| PR/push validation triggers | Not configured |
| Successful build/test evidence | Not established |

**Important:** A deliberate failure of the starter guard means CI is unconfigured. It is **not** evidence that CampusConnect code failed its own build or tests. This review inspects the workflow definition; it does not claim a specific GitHub Actions run occurred.

## 2. Intended CI Checks — Not Yet Implemented

The Cycle 1 architecture records Java, Spring Boot, Thymeleaf, Maven, and a local file-backed H2 data store under ADR-002. Once executable project files are present and reviewed, the team should replace the guard with checks appropriate to the actual project:

1. Check out the reviewed repository commit.
2. Configure a compatible Java runtime and Maven environment.
3. Compile/package the application, failing the job on build errors.
4. Execute meaningful automated unit and integration tests, failing the job on test errors.
5. Report test results and preserve useful logs or artifacts.
6. Enable appropriate pull-request and/or push triggers after team agreement.
7. Keep workflow permissions minimal; do not commit secrets or real student data.

These steps are **recommendations and intended checks**, not existing workflow behavior. The architecture/development lead and team lead should approve the final implementation and CI scope.

## 3. Requirements and Test Traceability

The team has [12 proposed acceptance criteria](../requirements/acceptance-criteria.md) covering REQ-001 through REQ-006. The [test strategy](test-strategy.md) describes verification levels; the [test plan](test-plan.md) and [test-case catalog](test-cases.md) define intended coverage. The plan and catalog are proposed in [PR #56](https://github.com/victordodan/-comp330-f26-team-02/pull/56) as of this review.

| Verification Area | Related Requirements | CI Evidence Status |
|---|---|---|
| Input validation and request creation | REQ-001, REQ-003 | Not configured / Not verified |
| Owner-only request access | REQ-002 | Not configured / Not verified |
| Reviewer visibility and role restrictions | REQ-004 | Not configured / Not verified |
| Allowed status transitions and failures | REQ-005 | Not configured / Not verified |
| Reviewer note/resolution validation | REQ-006 | Not configured / Not verified |
| Persistence, restart, consistency and concurrency | REQ-003 and related ADRs | Not configured / Not verified |

A future passing build is not automatically proof that every acceptance criterion passed. Link individual executed tests to the criteria they actually verify.

## 4. Run Evidence Register

**No project-specific CI execution evidence has been verified or recorded in this document.** Do not infer that the starter workflow ran merely because its YAML exists.

| Workflow Run | Commit / Branch | Date | Checks Executed | Result | Evidence Link |
|---|---|---|---|---|---|
| None verified for CampusConnect project checks | — | — | No project-specific checks configured | Not Verified | — |

When real CI is configured and runs, replace or supplement the table with actual GitHub Actions run URLs, exact commit SHAs, observed conclusions, and the checks performed. Preserve failed-run evidence and any issue/PR that addresses a failure.

## 5. Merge Protection and Evidence Limits

The presence of a workflow file does not establish required status checks, branch protection, test execution, or a passing release gate. These settings and results have **not been verified** for this review.

Current limits:
- No Java/Maven build or automated test is configured in the starter workflow.
- No test results, coverage percentages, security scan results, or deployment results are established here.
- No CI evidence demonstrates authorization, persistence, concurrency, or recovery behavior.
- No production-readiness or successful release claim is made.

## 6. Follow-Up Actions

| Action | Suggested Owner | Evidence Needed |
|---|---|---|
| Replace Starter Kit guard with a real project build/test workflow when code is ready | Shu Perez — Architecture & Development Lead, with developers | Reviewed workflow PR and project-specific YAML |
| Add meaningful automated checks aligned to the approved requirements and test plan | Developers with Giancarlo — Quality & Review Lead | Test code and criterion mapping |
| Confirm CI triggers and any branch/merge rules | Victor Dodan — Team Lead | GitHub settings or documented team decision |
| Record real runs, failures, and retests without inventing outcomes | Giancarlo — Quality & Review Lead | Actions run links, exact commit SHAs, test results |
| Review data handling and secrets before enabling CI | Shelby Sierah — Operations & Evidence Lead | Security review evidence |

**A3 gate interpretation:** This document provides an accurate CI readiness assessment. It does not claim that CI is complete. Final A3 approval remains a team decision, and later implementation gates will need real automated execution evidence.
