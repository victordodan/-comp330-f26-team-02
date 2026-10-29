# Assignment 4 — Construction, Integration and AI Code Review Evidence Package

**Team:** Codex Ramblers — Team 2  
**Project:** CampusConnect — Cycle 1  
**Index maintainer:** Giancarlo Herrera (Quality & Review Lead)  
**Status:** Working evidence index; not a phase-gate approval or claim of completed implementation  
**Last checked:** October 29, 2026  
**Repository clone URL:** `https://github.com/victordodan/-comp330-f26-team-02.git`

## How to use this index

This is the navigation and accountability record for the A4 engineering evidence package. Each deliverable must have a working repository path or PR link, a **primary** and **backup** owner, a current status, and an evidence-backed completion decision. Ownership below is a **proposed working allocation** to be confirmed by Victor and the named teammates, not a claim they accepted assignments. Where an artifact exists but contains only A3 planning or starter material, its status is **Needs A4 update**.

**Status meanings:** `Ready for review` = proposed A4 documentation awaits peer review; `Needs A4 update` = older or placeholder artifact exists; `Not started` = required A4 evidence absent; `Pending verification` = work exists but outcome is unverified; `Complete` = linked, independently checked evidence is present. `Merged; follow-up pending` = a document is on main but its outstanding evidence or adoption checks remain open.

## Deliverable index and owner matrix

| A4 deliverable | Repository path / evidence | Proposed primary | Proposed backup | Status / next proof |
|---|---|---|---|---|
| A4 package index and ownership | `docs/reviews/assignment4-review-package.md` (this PR) | Giancarlo Herrera | Shelby Sierah | Merged via PR #65; confirm owners and update status as evidence changes |
| Changed-file index since A3 and rationale for unchanged files | `docs/reviews/assignment4-changed-files.md` (to create); final Word summary | Shelby Sierah | Giancarlo Herrera | Not started; identify confirmed A3 baseline |
| Architecture-review findings and dispositions | [architecture review](architecture-review.md) | Shu Perez | Giancarlo Herrera | Needs A4 update; link findings to code/fixes |
| Requirements → issues → commits → PRs → CI/tests | [traceability](../planning/traceability.md), [PR #62](https://github.com/victordodan/-comp330-f26-team-02/pull/62) | Giancarlo Herrera | Shelby Sierah | Merged via PR #62; PR #67 validation/CI linked, remaining REQs pending |
| PR template and substantive review trail | [PR template](../../.github/pull_request_template.md), [quality template PR #61](https://github.com/victordodan/-comp330-f26-team-02/pull/61) | Giancarlo Herrera | Victor Dodan | PR #61 and PR template on main; PR #67 independently reviewed; integrated review trail pending |
| Definition of Done and merge decision discipline | [PR #63](https://github.com/victordodan/-comp330-f26-team-02/pull/63) | Giancarlo Herrera | Victor Dodan | Merged via PR #63; team adoption pending |
| AI-assisted code review findings and dispositions | [AI review template PR #61](https://github.com/victordodan/-comp330-f26-team-02/pull/61), per-PR findings TBD | Giancarlo Herrera | Shu Perez | Template merged via PR #61; PRs #68–73 received change requests; AI-specific findings and fixes pending |
| AI use disclosures, accepted/rejected suggestions, human checks | [AI use log](../ai/ai-use-log.md) | Giancarlo Herrera | Shelby Sierah | Four A4 entries merged via PR #74; further disclosures/verification as applicable |
| Build/test CI and real run evidence | [workflow](../../.github/workflows/ci.yml), [CI evidence](../testing/ci-evidence.md) | Shu Perez | Venkata Aravind Mareddy | Dedicated `.github/workflows/a4-validation-tests.yml` passed PR #67 unit tests; starter guard/integrated CI pending |
| Executable feature implementation and integration | [source directory](../../src/), relevant PRs TBD | Shu Perez | Venkata Aravind Mareddy | Validator merged via PR #67; application/controller/service/DTO integration not merged (PRs #68–73 open) |
| Automated tests and acceptance verification | [test cases](../testing/test-cases.md), [tests directory](../../tests/) | Venkata Aravind Mareddy | Giancarlo Herrera | Six JUnit validator tests passed via PR #67; TC-001–012 acceptance/integration tests pending |
| Dependency/version/license/maintenance/security review | [PR #64](https://github.com/victordodan/-comp330-f26-team-02/pull/64) | Giancarlo Herrera | Shu Perez | PR #64 framework merged; `pom.xml` inventory refreshed, license/security/tree checks pending |
| Risk register and deferred work | [risk register](../planning/risk-register.md) | Malec Tarabein | Giancarlo Herrera | Needs A4 risk review and dispositions |
| Known limitations and release readiness | [known limitations](../release/known-limitations.md) | Shelby Sierah | Victor Dodan | Needs A4 content; starter guidance present |
| Runtime/logging/recovery evidence (if applicable) | `docs/testing/` and `docs/release/` — links TBD | Shu Perez | Shelby Sierah | Pending implementation and actual observations |
| Final Word submission, changed-file summary, repo clone URL, A4 tag | Final `.docx` submitted to Sakai by Victor; Git tag `a4-construction-integration` | Victor Dodan | Shelby Sierah | Not started; submit only after evidence review |

## Merged documentation PRs and open implementation reviews

- [PR #61 — Quality and AI code review templates](https://github.com/victordodan/-comp330-f26-team-02/pull/61)
- [PR #62 — Requirements-to-test traceability](https://github.com/victordodan/-comp330-f26-team-02/pull/62)
- [PR #63 — Definition of Done](https://github.com/victordodan/-comp330-f26-team-02/pull/63)
- [PR #64 — Dependency and supply-chain review](https://github.com/victordodan/-comp330-f26-team-02/pull/64)

PRs #61–65 and #74 are now merged into `main` (as of October 29). [PR #67](https://github.com/victordodan/-comp330-f26-team-02/pull/67) is merged with six passing validator unit tests and review evidence. Shu's [PRs #68–73](https://github.com/victordodan/-comp330-f26-team-02/pulls) remain open with outstanding quality-review feedback. **Open PRs are not merged evidence.** Recheck statuses and exact CI results before submission.

## A4 evidence completeness checks

- [ ] Confirm owners and backup owners with Victor and each teammate.
- [ ] Confirm the A3 baseline tag or exact commit before producing the changed-file index.
- [x] A4 process documentation PRs #61–65 merged; confirm any remaining team adoption/review follow-up.
- [ ] Validator unit-test slice merged via PR #67; complete an end-to-end Cycle 1 feature and link issues, branches, commits and PRs.
- [ ] Capture substantive review comments, author responses and merge/no-merge decisions.
- [x] Focused validation CI passed for PR #67; [run evidence](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/40778855959). [ ] Integrated application CI remains pending.
- [ ] Execute and record tests with mapped acceptance criteria, including negative/security cases.
- [ ] Complete AI code review and AI-use log entries with human verification.
- [ ] Review actual dependency versions, licenses, maintenance and security advisories.
- [ ] Reconcile architecture findings, risk register, known limitations, and deferred work.
- [ ] Produce final changed-file index, Word evidence summary and clone URL.
- [ ] Have Victor verify and create/push `a4-construction-integration` tag for the approved commit.
- [ ] Victor submits the Word document and clone URL to Sakai.

## Open blockers and decisions

1. **Partial implementation only:** validator source and six JUnit tests are on `main` via PR #67; the full application and end-to-end acceptance checks are not verified.
2. **CI is split:** a dedicated validation workflow has passing unit-test evidence, while the original starter guard and integrated application-wide CI still need reconciliation.
3. **Implementation reviews exist:** PR #67 was reviewed and merged; PRs #68–73 have requested changes and are open. A4 AI-assisted code-review findings and dispositions require further evidence where applicable.
4. **A3 baseline not yet confirmed** for an accurate changed-file report.
5. **Primary/backup assignments require confirmation**; they are proposals, not recorded team commitments.

**October 29 quality checkpoint:** Giancarlo's responsibility is to keep the review ledger, dependency assessment, traceability, and evidence index current; Shu and the developers own code corrections and tests, Victor coordinates ownership and final submission. No teammate's implementation was changed by this evidence refresh.

**Integrity rule:** Keep planned checks separate from executed checks, open PRs separate from merged work, and proposed ownership separate from accepted responsibility. Do not infer approvals, successful tests, or resolved findings.
