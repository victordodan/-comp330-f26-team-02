# A4 Construction PR Quality Review Checklist

**Project:** CampusConnect | **Owner:** Giancarlo Herrera, Quality & Review Lead | **Status:** Review process proposed; no implementation approved by this checklist

## Review record (complete for each meaningful implementation PR)
- PR / issue / branch / commit:
- Author / independent reviewer / review date:
- Requirements and acceptance criteria affected:
- Changed-file summary and reason:
- Architecture / ADR alignment or deviation:
- Risk category, severity, and mitigation:
- Test commands and actual results (link logs/run):
- CI check run URL and status:
- AI assistance disclosure and human verification:
- Dependency changes (name, version, license, maintenance, vulnerabilities):
- Reviewer comments, author responses, and retest links:
- Decision: Approve / Request changes / Block / Defer; rationale:

## Before approval
- [ ] Scope is bounded and linked to a requirement or issue.
- [ ] No unreviewed architecture deviation; update ADR/docs if necessary.
- [ ] Inputs, server-side role checks, student ownership and output encoding reviewed.
- [ ] Correct status transitions, validation boundaries and failed writes addressed.
- [ ] Local H2 persistence, transaction and optimistic version behavior reviewed if touched.
- [ ] Tests cover happy, invalid, ownership, state, failure and restart cases as relevant.
- [ ] Actual test results are recorded; planned tests are not labeled passed.
- [ ] PR CI workflow ran on relevant commit and its status is linked; failing checks block merge unless an explicit authorized exception is documented.
- [ ] AI use and rejected suggestions are disclosed; generated code manually inspected.
- [ ] New/updated dependencies have version, license, maintenance and security review.
- [ ] Review feedback has author response and evidence of correction.
- [ ] Decision and residual risks are recorded before merge.

**Current A4 baseline:** On October 23, 2026, /src and /tests contained README files only; CI was a deliberately failing starter guard. This checklist does not claim any build, tests, or PR approvals.
