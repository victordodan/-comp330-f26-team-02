# Assignment 4 — Construction, Integration and AI Code Review Evidence Package

**Team:** Codex Ramblers — Team 2  
**Project:** CampusConnect — Cycle 1  
**Index maintainer:** Giancarlo Herrera (Quality & Review Lead)  
**Status:** Working evidence index; not a phase-gate approval or claim of completed implementation  
**Last checked:** October 23, 2026  
**Repository clone URL:** `https://github.com/victordodan/-comp330-f26-team-02.git`

## How to use this index

This is the navigation and accountability record for the A4 engineering evidence package. Each deliverable must have a working repository path or PR link, a **primary** and **backup** owner, a current status, and an evidence-backed completion decision. Ownership below is a **proposed working allocation** to be confirmed by Victor and the named teammates, not a claim they accepted assignments. Where an artifact exists but contains only A3 planning or starter material, its status is **Needs A4 update**.

**Status meanings:** `Ready for review` = proposed A4 documentation exists in an open PR; `Needs A4 update` = older or placeholder artifact exists; `Not started` = required A4 evidence absent; `Pending verification` = work exists but outcome is unverified; `Complete` = linked, independently checked evidence is present.

## Deliverable index and owner matrix

| A4 deliverable | Repository path / evidence | Proposed primary | Proposed backup | Status / next proof |
|---|---|---|---|---|
| A4 package index and ownership | `docs/reviews/assignment4-review-package.md` (this PR) | Giancarlo Herrera | Shelby Sierah | Ready for review; confirm owners |
| Changed-file index since A3 and rationale for unchanged files | `docs/reviews/assignment4-changed-files.md` (to create); final Word summary | Shelby Sierah | Giancarlo Herrera | Not started; identify confirmed A3 baseline |
| Architecture-review findings and dispositions | [architecture review](architecture-review.md) | Shu Perez | Giancarlo Herrera | Needs A4 update; link findings to code/fixes |
| Requirements → issues → commits → PRs → CI/tests | [traceability](../planning/traceability.md), [PR #62](https://github.com/victordodan/-comp330-f26-team-02/pull/62) | Giancarlo Herrera | Shelby Sierah | Ready for review; implementation links pending |
| PR template and substantive review trail | [PR template](../../.github/pull_request_template.md), [quality template PR #61](https://github.com/victordodan/-comp330-f26-team-02/pull/61) | Giancarlo Herrera | Victor Dodan | Needs A4 implementation PR and reviewer comments |
| Definition of Done and merge decision discipline | [PR #63](https://github.com/victordodan/-comp330-f26-team-02/pull/63) | Giancarlo Herrera | Victor Dodan | Ready for review; team adoption pending |
| AI-assisted code review findings and dispositions | [AI review template PR #61](https://github.com/victordodan/-comp330-f26-team-02/pull/61), per-PR findings TBD | Giancarlo Herrera | Shu Perez | Ready for review template only; actual review pending |
| AI use disclosures, accepted/rejected suggestions, human checks | [AI use log](../ai/ai-use-log.md) | Giancarlo Herrera | Shelby Sierah | Needs A4 updates; log this and other A4 AI assistance |
| Build/test CI and real run evidence | [workflow](../../.github/workflows/ci.yml), [CI evidence](../testing/ci-evidence.md) | Shu Perez | Venkata Aravind Mareddy | Not configured; starter guard is not successful CI |
| Executable feature implementation and integration | [source directory](../../src/), relevant PRs TBD | Shu Perez | Venkata Aravind Mareddy | Not started on main; only README in src |
| Automated tests and acceptance verification | [test cases](../testing/test-cases.md), [tests directory](../../tests/) | Venkata Aravind Mareddy | Giancarlo Herrera | Planned cases only; no executed results verified |
| Dependency/version/license/maintenance/security review | [PR #64](https://github.com/victordodan/-comp330-f26-team-02/pull/64) | Giancarlo Herrera | Shu Perez | Ready for review framework; actual dependency scan pending |
| Risk register and deferred work | [risk register](../planning/risk-register.md) | Malec Tarabein | Giancarlo Herrera | Needs A4 risk review and dispositions |
| Known limitations and release readiness | [known limitations](../release/known-limitations.md) | Shelby Sierah | Victor Dodan | Needs A4 content; starter guidance present |
| Runtime/logging/recovery evidence (if applicable) | `docs/testing/` and `docs/release/` — links TBD | Shu Perez | Shelby Sierah | Pending implementation and actual observations |
| Final Word submission, changed-file summary, repo clone URL, A4 tag | Final `.docx` submitted to Sakai by Victor; Git tag `a4-construction-integration` | Victor Dodan | Shelby Sierah | Not started; submit only after evidence review |

## Existing review PRs awaiting independent review

- [PR #61 — Quality and AI code review templates](https://github.com/victordodan/-comp330-f26-team-02/pull/61)
- [PR #62 — Requirements-to-test traceability](https://github.com/victordodan/-comp330-f26-team-02/pull/62)
- [PR #63 — Definition of Done](https://github.com/victordodan/-comp330-f26-team-02/pull/63)
- [PR #64 — Dependency and supply-chain review](https://github.com/victordodan/-comp330-f26-team-02/pull/64)

All four were open at the time this index was prepared. **Open PRs are not merged evidence.** Recheck status, review comments, and target commit before submission.

## A4 evidence completeness checks

- [ ] Confirm owners and backup owners with Victor and each teammate.
- [ ] Confirm the A3 baseline tag or exact commit before producing the changed-file index.
- [ ] Review/merge the A4 process documentation PRs with meaningful independent feedback.
- [ ] Implement a bounded end-to-end Cycle 1 feature slice and link issues, branches, commits and PRs.
- [ ] Capture substantive review comments, author responses and merge/no-merge decisions.
- [ ] Run a real build/test workflow against the relevant commit; record actual CI URLs/results.
- [ ] Execute and record tests with mapped acceptance criteria, including negative/security cases.
- [ ] Complete AI code review and AI-use log entries with human verification.
- [ ] Review actual dependency versions, licenses, maintenance and security advisories.
- [ ] Reconcile architecture findings, risk register, known limitations, and deferred work.
- [ ] Produce final changed-file index, Word evidence summary and clone URL.
- [ ] Have Victor verify and create/push `a4-construction-integration` tag for the approved commit.
- [ ] Victor submits the Word document and clone URL to Sakai.

## Open blockers and decisions

1. **No verified executable implementation or tests on main.** `src/` and `tests/` contained only README files when checked.
2. **CI is a deliberate configuration guard**, not project build/test evidence.
3. **No A4 implementation PR review or AI code-review findings yet**; the existing A4 PRs are documentation work.
4. **A3 baseline not yet confirmed** for an accurate changed-file report.
5. **Primary/backup assignments require confirmation**; they are proposals, not recorded team commitments.

**Integrity rule:** Keep planned checks separate from executed checks, open PRs separate from merged work, and proposed ownership separate from accepted responsibility. Do not infer approvals, successful tests, or resolved findings.
