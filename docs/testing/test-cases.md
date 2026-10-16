# CampusConnect Cycle 1 Test Cases

**Team:** Codex Ramblers — Team 2  
**Owner:** Giancarlo Herrera — Quality & Review Lead  
**Phase:** A3 — Architecture and Review  
**Status:** Planned — All cases Not Run

## Test Case Catalog
The following scenarios map **one-to-one to all twelve proposed acceptance criteria** in [acceptance-criteria.md](../requirements/acceptance-criteria.md). They are planning artifacts, not executed results.

| ID | Acceptance Criterion | Scenario / Steps | Expected Result | Execution |
|---|---|---|---|---|
| TC-001 | AC-REQ-001-01 | Student A selects demo identity and submits valid title/category/description | Stored request with Submitted status and visible ID | Not Run |
| TC-002 | AC-REQ-001-02 | Try blank, trimmed-out-of-range, and unsupported submission values | Validation error; no request created | Not Run |
| TC-003 | AC-REQ-002-01 | Student A views request after reviewer updates and reopens it | Current stored status shown | Not Run |
| TC-004 | AC-REQ-002-02 | Reviewer edits note; Student A views; reviewer reopens | Latest note visible and preserved | Not Run |
| TC-005 | AC-REQ-002-03 | Student B lists or directly requests Student A's record; try unknown ID | No Student A data disclosed | Not Run |
| TC-006 | AC-REQ-003-01 | Create two valid requests; inspect IDs, owner, timestamps; simulate failed write | Unique IDs and managed metadata; failed write never reports success | Not Run |
| TC-007 | AC-REQ-003-02 | Persist status/note; restart application using same local store | ID, owner, fields, status and note retained | Not Run |
| TC-008 | AC-REQ-004-01 | Create requests as both students; select Reviewer | Reviewer can list and inspect both | Not Run |
| TC-009 | AC-REQ-004-02 | Student directly attempts reviewer actions; try invalid session and forged role fields | Denied with no data leak or mutation | Not Run |
| TC-010 | AC-REQ-005-01 | Reviewer moves Submitted to In Progress to Resolved with note, then reopens | Only allowed transitions saved; note and ID preserved | Not Run |
| TC-011 | AC-REQ-005-02 | Try invalid transition, missing resolution, unknown ID and failed status write | Rejected without false success or record changes | Not Run |
| TC-012 | AC-REQ-006-01 | Reviewer saves/replaces note; test >2,000 characters, clearing while Resolved and failed write | Valid current note visible to owner; invalid actions rejected without mutation | Not Run |

## Test Setup
Use synthetic Student A, Student B, and Reviewer identities. Unless a case specifies otherwise, begin with a clean local test store and valid request title, category, and description. Verify both user-facing results and stored data or service responses where available. For direct unauthorized operations, do not rely solely on hiding buttons in the UI.

## Detailed Boundary and Failure Variants
- **TC-002:** Test title lengths 4, 5, 100, and 101; description lengths 9, 10, 2,000, and 2,001; whitespace-only values; invalid category; and trimmed input.
- **TC-005 / TC-009:** Try both listing and direct lookup/update paths, switching identities after request creation, missing session context, and form-supplied requester/role overrides.
- **TC-006 / TC-007:** Check persistence before success messages, uniqueness across requests, retention after normal restart, and behavior after simulated storage failure. Use repeat submissions and concurrent updates to exercise ADR-003 once implementation supports them.
- **TC-010 / TC-011:** Cover Submitted → In Progress, In Progress → Resolved with nonblank note, Resolved → In Progress with preserved note, and every unsupported transition.
- **TC-012:** Test empty and whitespace-only notes, 2,000/2,001-character boundaries, replacement, clearing only when permitted, and failed writes.

## Execution Record Template
For each actual run, add a dated record containing:

| Field | Required Value |
|---|---|
| Test case | TC identifier |
| Baseline | Commit SHA / build |
| Date and operator | Actual execution details |
| Environment and data | Local setup, identity, store state |
| Result | Pass / Fail / Blocked / Not Run |
| Actual result | Observed behavior, not expectation |
| Evidence | GitHub Actions link, test log, screenshot, or issue |
| Defect / retest | Linked issue and follow-up result if needed |

## Current Results and Known Gaps
**Executed:** 0 of 12 documented cases. **Not Run:** 12 of 12. These counts refer only to the catalog's documentation status; no execution has been independently verified. Automated test implementations, CI integration, real results, and regression evidence are pending. Do not change a case to Pass without run-specific evidence.

See [test-plan.md](test-plan.md), [test-strategy.md](test-strategy.md), and [ci-evidence.md](ci-evidence.md).
