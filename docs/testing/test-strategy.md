# Test Strategy

**Project:** CampusConnect
**Team:** Team 2 — Codex Ramblers
**Assignment:** A3 — Cycle 1 Architecture and Review
**Primary Owner:** Giancarlo Herrera — Quality & Review Lead
**Backup Owner:** Shelby Sierah (pending confirmation)
**Status:** Proposed — Pending Team Review

## 1. Testing Objectives

The purpose of this testing strategy is to define how our team plans to verify that CampusConnect meets its requirements and provides a reliable support-request workflow for students and reviewers.

Testing will focus on the six Cycle 1 requirements: submitting requests, viewing status and resolution, storing requests with unique identifiers, viewing requests as a reviewer, updating request statuses, and recording notes or resolutions.

Our main objectives are to identify defects involving invalid inputs, unauthorized access, incorrect status updates, and storage or retrieval failures. Since Assignment 3 focuses on architecture and review, this document describes planned testing rather than completed test execution.

## 2. Testing Principles

- **Requirement-based testing:** Important tests will connect to approved requirements and acceptance criteria.
- **Positive and negative testing:** We will verify both successful operations and expected failures.
- **Early defect detection:** We will review architectural boundaries before implementation.
- **Risk-based testing:** Authorization, data integrity, and workflow changes will receive additional attention.
- **Repeatable verification:** We will automate suitable tests when the implementation technology is selected.
- **Human review:** Team members will verify test expectations and results.
- **Documented evidence:** Actual test results and defects will be recorded when testing occurs.

## 3. Test Levels

### Unit Testing

Unit testing will focus on individual workflow rules, including request validation, identifier uniqueness, and allowed status transitions. These tests will help identify logic errors without requiring the complete application.

### Component and Integration Testing

Integration testing will examine communication between the Requester and Reviewer UI, Request Workflow Service, Request Repository, and Simulated Role Context.

For example, we will verify that a valid submission reaches the repository and that a storage failure does not result in a false success message.

### API and Contract Testing

Contract testing will verify the seven proposed interfaces, API-001 through API-007, documented in `docs/architecture/api-contracts.md`.

Tests will examine inputs, outputs, authorization, failure handling, and component boundaries. These interfaces are proposed and have not yet been implemented or verified.

### End-to-End and Acceptance Testing

End-to-end testing will follow a request from student submission through reviewer processing and resolution.

These tests will help determine whether the complete workflow meets the approved acceptance criteria.

## 4. Test Types

- **Functional:** Verify the six CampusConnect requirements.
- **Negative-path:** Check invalid requests, missing records, and failed operations.
- **Security and permissions:** Verify that requester and reviewer actions remain separated.
- **Data integrity:** Confirm unique identifiers and correct storage of request information.
- **Regression:** Recheck working functionality after changes or defect corrections.
- **Usability:** Manually review whether the workflow and error messages are understandable.
- **Failure and recovery:** Check how the system responds to storage and service failures.

## 5. Requirements and Acceptance-Criteria Coverage

### REQ-001 — Submit Support Request

Verify that a student can submit a valid support request using synthetic data. Invalid or missing required information must be rejected according to the approved input specification.

**Related contract:** API-001

### REQ-002 — View Request Status and Resolution

Verify that a student can retrieve their own request and view its current status and available resolution information. Requests belonging to another simulated student must not be accessible.

**Related contract:** API-002

### REQ-003 — Store Request with Unique Identifier

Verify that each successfully submitted request is stored with a unique identifier and that storage failures do not produce false confirmation.

**Related contracts:** API-001, API-006

### REQ-004 — Reviewer Views Requests

Verify that an authorized reviewer can view submitted requests and that requester-role operations cannot access reviewer-only functionality.

**Related contracts:** API-003, API-007

### REQ-005 — Reviewer Updates Status

Verify that an authorized reviewer can change a request's status. Unsupported values and transitions must be rejected once the approved status model is established.

**Related contract:** API-004

### REQ-006 — Reviewer Records Note or Resolution

Verify that a reviewer can record a note or resolution and that it remains associated with the correct request.

**Related contract:** API-005

The team will maintain traceability between requirements, acceptance criteria, planned tests, and actual results. Planned tests will not be represented as passed tests.

**Related documents:**
- `docs/requirements/requirements.md`
- `docs/requirements/acceptance-criteria.md`
- `docs/planning/traceability.md`

## 6. Risk-Based Testing

### Unauthorized Access

Test whether a requester can access another student's request or perform reviewer-only operations. The Request Workflow Service must enforce permissions rather than relying only on the user interface.

### Incorrect Status Updates

Verify that status changes follow approved workflow rules and that invalid transitions are rejected.

### Data Integrity and Storage Failures

Check that identifiers remain unique and that request records, statuses, and resolutions are stored consistently. Simulated persistence failures will be tested where practical.

### Component Communication Failures

Test how the workflow responds when repository operations fail or role context is missing or invalid.

### Unresolved Design Decisions

Open questions about required input fields, status transitions, note editing, and simulated role representation may affect expected test behavior. The team will update testing plans when these decisions are resolved.

**Related document:** `docs/planning/risk-register.md`

## 7. Positive and Negative Testing

Positive tests will verify valid request submission, status retrieval, reviewer updates, and resolution recording.

Negative tests will include:

- Missing or invalid request information.
- Accessing another requester's record.
- Performing reviewer-only operations as a requester.
- Updating a nonexistent request.
- Using an unsupported status or transition.
- Missing or invalid simulated role context.
- Repository failures during creation or updating.
- Repeated submissions where duplicate-handling behavior remains undecided.

The team will prioritize failure scenarios that affect permissions, data integrity, and workflow reliability.

## 8. Automation Strategy

The team intends to automate repeatable tests after selecting an implementation technology and suitable testing framework.

Potential automated checks include validation, request creation, unique identifiers, authorization, status changes, repository operations, and regression tests.

Manual verification will remain important for usability, architecture consistency, requirements interpretation, and reviewing test evidence.

No automated pipeline or testing framework is claimed to be operational at this stage.

## 9. Regression Strategy

When a meaningful defect is corrected, the team will recheck the affected behavior and related requirements.

Where practical, an automated test will be added or updated to detect the same defect if it returns.

Regression testing will also be considered when component responsibilities, API contracts, or workflow rules change.

**Related document:** `docs/quality/defect-log.md`

## 10. Test Data Strategy

CampusConnect will use synthetic data only. Real student records, Loyola credentials, and sensitive institutional information will not be used.

Test data will include sample requests, simulated requester and reviewer identities, valid and invalid identifiers, and different approved request statuses.

Separate simulated requester identities will help verify ownership restrictions.

**Related document:** `docs/security/data-handling-notes.md`

## 11. Environment Strategy

The team plans to begin testing in local development environments when implementation becomes available.

If a continuous integration workflow is later configured, suitable automated tests may run during pull request review.

The team has not yet confirmed a staging environment, deployed test environment, or automated testing pipeline. Actual environments and limitations will be documented when established.

## 12. AI-Assisted Testing

AI tools may assist with identifying edge cases, drafting test scenarios, and reviewing testing coverage.

AI-generated suggestions are not proof of correct system behavior. Team members must review proposed tests, verify expectations, and independently confirm actual results.

AI assistance used in preparing this strategy will be recorded in the team AI Use Log.

**Related documents:**
- `docs/ai/ai-policy.md`
- `docs/ai/ai-use-log.md`

## 13. Test Evidence

When tests are executed, the team will preserve relevant evidence in GitHub, including test results, pull request reviews, defect records, and automated output where available.

Evidence should identify what was tested, the result, and the associated requirement or interface contract.

**Related documents:**
- `docs/testing/test-plan.md`
- `docs/testing/test-cases.md`
- `docs/testing/ci-evidence.md`

## 14. Known Testing Limitations

- **No verified implementation:** This document defines planned testing, not completed execution.
- **Technology pending:** Specific frameworks have not been approved.
- **Open requirements questions:** Some expected behaviors depend on unresolved decisions.
- **Synthetic-data scope:** Real institutional integrations are excluded.
- **No confirmed CI or staging environment:** These may be introduced later.

The team will revisit these limitations as architecture and implementation progress.

## 15. Review and Maintenance

Giancarlo Herrera, as Quality & Review Lead, will coordinate review of test coverage and identify gaps affecting the Cycle 1 workflow.

Team members will review testing assumptions, verify their component contracts, and contribute evidence as implementation progresses.

This strategy will be updated when approved requirements, architecture decisions, or interface contracts change.
