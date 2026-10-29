# Assignment 4 — Changed-File Index Since A3

**Project:** CampusConnect — Codex Ramblers, Team 2  
**Prepared by:** Giancarlo Herrera, Quality & Review Lead  
**Evidence checked:** October 29, 2026  
**Baseline:** [`a3-architecture-review`](https://github.com/victordodan/-comp330-f26-team-02/releases/tag/a3-architecture-review), commit [`255c335343c242eade44181c3ba581157ec78bfe`](https://github.com/victordodan/-comp330-f26-team-02/commit/255c335343c242eade44181c3ba581157ec78bfe)  
**Comparison:** [A3 tag → `main`](https://github.com/victordodan/-comp330-f26-team-02/compare/a3-architecture-review...main)  
**Snapshot main:** [`997ff2085`](https://github.com/victordodan/-comp330-f26-team-02/commit/997ff2085)  
**Scope:** Verified merged changes on `main` only; the open PR #75 additions are tracked separately.

GitHub's comparison reported **23 commits and 14 changed paths** relative to the A3 tag: **12 added files and 2 modified files**. The following table describes the actual comparison, not a claim of full implementation, acceptance, or security verification.

| Changed path | Change | Evidence / PR | Responsible contributor or reviewer | Purpose and remaining verification |
|---|---|---|---|---|
| `.github/workflows/a4-validation-tests.yml` | Added | [#67](https://github.com/victordodan/-comp330-f26-team-02/pull/67) | Mareddy (implementation); Giancarlo (review) | Runs Java 17 Maven unit tests; does not by itself prove integrated app CI |
| `docs/ai/ai-use-log.md` | Modified | [#74](https://github.com/victordodan/-comp330-f26-team-02/pull/74) | Giancarlo (log); named contributors (disclosures) | Records A4 AI-use entries; continue disclosure and human-verification follow-up |
| `docs/planning/traceability.md` | Modified | [#62](https://github.com/victordodan/-comp330-f26-team-02/pull/62) | Giancarlo | Links REQs/ACs to planned tests; partial implementation linkage in open #75 |
| `docs/reviews/a4-ai-code-review-template.md` | Added | [#61](https://github.com/victordodan/-comp330-f26-team-02/pull/61) | Giancarlo | Review structure, not completed AI review results |
| `docs/reviews/a4-definition-of-done.md` | Added | [#63](https://github.com/victordodan/-comp330-f26-team-02/pull/63) | Giancarlo | Evidence-based completion criteria; adoption still needs checking |
| `docs/reviews/a4-dependency-supply-chain-review.md` | Added | [#64](https://github.com/victordodan/-comp330-f26-team-02/pull/64) | Giancarlo | Inventory and review framework; scan/license/security dispositions pending |
| `docs/reviews/a4-quality-review-checklist.md` | Added | [#61](https://github.com/victordodan/-comp330-f26-team-02/pull/61) | Giancarlo | Quality and review checks for A4 |
| `docs/reviews/assignment4-review-package.md` | Added | [#65](https://github.com/victordodan/-comp330-f26-team-02/pull/65) | Giancarlo | Central deliverable and proposed owner index; open #75 refresh pending |
| `docs/testing/a4-request-validation-evidence.md` | Added | [#67](https://github.com/victordodan/-comp330-f26-team-02/pull/67) | Mareddy | Evidence for six request-validator unit tests; no end-to-end acceptance claim |
| `pom.xml` | Added | [#67](https://github.com/victordodan/-comp330-f26-team-02/pull/67) | Mareddy | Initial Java 17/JUnit Maven build; Spring Boot changes proposed in open #76 |
| `src/main/java/edu/loyola/campusconnect/model/CreateRequestDto.java` | Added | [#68](https://github.com/victordodan/-comp330-f26-team-02/pull/68), [post-merge commit](https://github.com/victordodan/-comp330-f26-team-02/commit/997ff2085) | Shu (PR); Victor (integration commit) | Request DTO; `studentID` added on main after merge; validate client identity/authorization design |
| `src/main/java/edu/loyola/campusconnect/model/UpdateStatusDto.java` | Added | [#70](https://github.com/victordodan/-comp330-f26-team-02/pull/70) | Shu (PR); Victor (merge) | Status-update DTO; verify against accepted workflow |
| `src/main/java/edu/loyola/campusconnect/validation/RequestValidator.java` | Added | [#67](https://github.com/victordodan/-comp330-f26-team-02/pull/67) | Mareddy | Validates request fields; does not enforce role/ownership |
| `src/test/java/edu/loyola/campusconnect/validation/RequestValidatorTest.java` | Added | [#67](https://github.com/victordodan/-comp330-f26-team-02/pull/67) | Mareddy | Six JUnit tests; other acceptance/security tests pending |

## Unchanged files and why

The comparison is authoritative for **which paths changed**, but cannot prove *why* each unchanged file was left untouched. The following are evidence-backed observations and suggested dispositions, not invented team approvals:

- `.github/workflows/ci.yml` — **unchanged** in the A3-to-main comparison. The separate A4 validator workflow was added in PR #67; the original CI workflow still requires integrated-build evidence and review.
- `docs/reviews/architecture-review.md` — **unchanged**. A4 follow-up dispositions against actual code are still needed; an A3 architecture review does not automatically certify A4 integration.
- `docs/testing/test-cases.md` — **unchanged**. The twelve planned acceptance tests are not established as executed by the six new validator tests.
- `docs/planning/risk-register.md` — **unchanged**. New integration, authorization, and dependency risks need A4 review and explicit disposition.
- `docs/release/known-limitations.md` — **unchanged**. Update with verified Cycle 1 limitations and release decision once integration evidence exists.
- `docs/decisions/ADR-002-cycle-1-technology-stack.md` — **unchanged**. Accepted architectural intent remains; the proposed Spring Boot POM in #76 still needs verification and reconciliation.

These are **selected required or relevant unchanged paths**, not a claim that every unchanged repository file was individually audited. Owners should confirm the rationale for leaving each unchanged and update where A4 requires new evidence.

## Open changes excluded from the merged comparison

At this snapshot, the following PRs are **not yet merged** and their proposed files must not be counted as merged A4 changes:

- [#69](https://github.com/victordodan/-comp330-f26-team-02/pull/69) — `SupportRequest` model
- [#71](https://github.com/victordodan/-comp330-f26-team-02/pull/71) — workflow service
- [#72](https://github.com/victordodan/-comp330-f26-team-02/pull/72) — request controller
- [#73](https://github.com/victordodan/-comp330-f26-team-02/pull/73) — Spring Boot application entry point
- [#75](https://github.com/victordodan/-comp330-f26-team-02/pull/75) — quality evidence reconciliation **and this changed-file index**
- [#76](https://github.com/victordodan/-comp330-f26-team-02/pull/76) — Spring Boot Maven dependency configuration
- [#77](https://github.com/victordodan/-comp330-f26-team-02/pull/77) — application context test

**Maintenance instruction:** Re-run the [comparison](https://github.com/victordodan/-comp330-f26-team-02/compare/a3-architecture-review...main) after further merges, refresh this index and its PR/CI references, and use the final result for Victor's Word submission. Do not mark unverified integration, vulnerability checks, or acceptance tests complete.
