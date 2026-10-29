# A4 Definition of Done — Construction and Integration

**Project:** CampusConnect | **Team:** Codex Ramblers — Team 2  
**Owner:** Giancarlo Herrera — Quality & Review Lead  
**Status:** Proposed team checklist; not evidence that any implementation PR is complete  
**Applies to:** Code, test, workflow, dependency, and architecture-impacting PRs in Assignment 4

## 1. Scope and traceability
- [ ] PR explains what changed and why; links the GitHub issue/task and affected REQ, AC, risk, or ADR identifiers.
- [ ] Changed files and behavior are bounded and understandable; unrelated work is split or justified.
- [ ] Requirement → issue → branch → commit → PR → test/CI evidence is linked in [traceability](../planning/traceability.md).
- [ ] New or changed behavior stays within the accepted Cycle 1 scope; deviations update the architecture and ADRs.

## 2. Correctness, security, and architecture
- [ ] Reviewer checks validation and failure behavior, not only the happy path.
- [ ] Where applicable, simulated roles and student ownership are enforced server-side; direct unauthorized operations are rejected.
- [ ] State transitions, notes, unique IDs, timestamps, optimistic versioning, transactions, and persistence conform to relevant requirements and ADRs.
- [ ] Inputs and outputs are handled safely; sensitive information is not committed or exposed in logs.
- [ ] Architecture boundaries and data ownership are respected, or a documented change is reviewed.

## 3. Tests and CI/CD evidence
- [ ] Relevant automated tests exist or the absence is explicitly explained and treated as a blocking risk where appropriate.
- [ ] Tests exercise positive, negative, boundary, authorization, failure, and regression cases relevant to the change.
- [ ] PR records exact test commands, observed outcomes, and evidence links, including failures and retests.
- [ ] A real CI build/test workflow runs on the proposed commit, with visible run URL and pass/fail result.
- [ ] Required checks pass before merge; failures are fixed and rerun, or a documented authorized exception explicitly explains the risk. The starter CI guard is **not** passing CI evidence.
- [ ] Manual checks, if used, record tester, date, environment, steps, actual result, and artifacts; manual evidence does not silently substitute for required automated CI.

## 4. Review discipline and AI accountability
- [ ] An independent teammate reviews the change and leaves substantive feedback on correctness, security, architecture, tests, maintainability, and dependencies as relevant.
- [ ] Findings record risk category/severity, fix direction, and evidence required to resolve them.
- [ ] Author responds to findings; fixes and retests are linked before an approval or no-merge decision.
- [ ] AI assistance for code, tests, configuration, debugging, review, or documentation is disclosed in [AI use log](../ai/ai-use-log.md), including tool/model, accepted and rejected output, human edits, and verification.
- [ ] AI-assisted material receives an explicit correctness/security/architecture/maintainability/test/dependency review. AI output is not assumed correct.

## 5. Dependencies, risks, and operational visibility
- [ ] New/changed dependencies document name, version, license, maintenance status, and security considerations; no unreviewed package or API is introduced.
- [ ] Any runtime errors, logs, recovery behavior, or diagnostics relevant to the change are documented without claiming unperformed verification.
- [ ] Known defects, limitations, deferred work, and residual risks are linked to issues or [risk register](../planning/risk-register.md).
- [ ] Reviewer records the rationale for **merge / request changes / no merge**, with the exact reviewed commit and evidence.

## 6. Final PR decision record (fill per PR)

| Field | Actual evidence |
|---|---|
| PR / issue / branch / commit | |
| Requirements / AC / ADR | |
| Author and independent reviewer | |
| Review comments and author response | |
| Automated test commands, results and logs | |
| CI run URL and result | |
| AI-use and AI-code-review evidence | |
| Dependency review and license/security evidence | |
| Risks, defects, deferred items | |
| Decision, rationale, reviewer and date | |

**Merge rule:** A checked box requires verifiable evidence. Mark unmet items **Not met** or **Not applicable with rationale**, not automatically complete. If essential correctness, authorization, required CI, or independent review evidence is missing, request changes or block merge. Any exception needs explicit team-lead authorization and recorded residual risk; it does not convert a failed check into a pass.

**Current state:** This is a review standard, not an execution report. At the time of drafting, the repository had no verified application implementation or automated tests in `src/` or `tests/`, and the starter CI guard was not a real build/test pipeline. Recheck repository status before applying this checklist to a specific PR.

**Related:** [A4 quality-review checklist](a4-quality-review-checklist.md) (proposed in PR #61), [AI code review template](a4-ai-code-review-template.md) (proposed in PR #61), [test cases](../testing/test-cases.md), and [CI evidence](../testing/ci-evidence.md).
