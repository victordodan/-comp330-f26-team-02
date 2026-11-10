# Changes Since A4 — Candidate Index

**Comparison:** tag `a4-construction-integration` (`70d3726`) → `main` at `531d54ca0f391e218752888900c4668173f75e27` ([GitHub compare](https://github.com/victordodan/-comp330-f26-team-02/compare/a4-construction-integration...531d54ca0f391e218752888900c4668173f75e27)).
**Totals:** 48 files changed (27 added, 21 modified, none deleted).
**Status:** Candidate. Refresh this index against the frozen A5 release SHA before tagging `a5-cycle1-release`. Any file changed after `531d54c` must be added here.
**Maintenance:** Originally assembled in #100; refreshed by Malec Tarabein (Planning & Process Lead) from `git diff --name-status` and the merge commit of each PR. AI-assisted drafting is disclosed in the [AI-use log](../ai/ai-use-log.md).

## Summary

- **Application source and build: no change since Assignment 4.** `src/main/` and `pom.xml` are identical to the A4 tag. The only Java change is test imports (#84).
- **CI:** workflows consolidated so `ci.yml` runs the Maven verification (#93).
- **Everything else is A5 release evidence:** testing and demo records, quality and dependency reviews, release documents, planning reconciliation and the Role Matrix.

## Changed files by area

| Area | Files (A = added, M = modified) | PRs | Coordination |
|---|---|---|---|
| Application tests | M `src/test/java/edu/loyola/campusconnect/controller/RequestControllerTest.java` (explicit static imports; test bodies unchanged) | #84 | Shú / development |
| CI | M `.github/workflows/ci.yml`, M `.github/workflows/a4-validation-tests.yml`, M `docs/testing/ci-evidence.md` | #93 | Shú / development |
| Demo tooling | A `scripts/demo-requests.sh`, M `scripts/README.md` | #97 | Shú |
| Architecture and decisions | M `docs/architecture/architecture.md`; A `docs/decisions/ADR-0005-in-memory-persistence.md` | #82, #81 | Shú (architecture) |
| Testing evidence | A `docs/testing/a5-acceptance-test-evidence.md`, A `docs/testing/a5-acceptance-evidence-matrix.md`, A `docs/testing/a5-demo-rehearsal.md`, A `docs/testing/final-test-evidence.md` | #92, #88, #103, #96, #100 | Mareddy / Giancarlo |
| Raw test and demo output | A `test-evidence/a5-http-demo/` (8 files: `README.md`, `build-summary.txt`, `environment.json`, `workflow.jsonl`, `restart.jsonl`, `restart-state.json`, `repeat.jsonl`, `transport-failure.jsonl`) | #96 | Shú |
| Dependency evidence | A `test-evidence/a5-dependencies/declared-licenses.csv`, A `test-evidence/a5-dependencies/resolved-tree.tgf`, A `docs/quality/a5-dependency-disposition.md`, A `docs/quality/a5-security-dependency-review.md` | #100, #89 | Giancarlo; Victor for risk acceptance |
| Quality and defects | A `docs/quality/a5-defect-limitations-reconciliation.md`, A `docs/quality/a5-final-release-quality-gate.md`, M `docs/quality/defect-log.md`, M `docs/quality/defect-review.md` | #87, #85, #102, #103, #91 | Giancarlo |
| Reviews | A `docs/reviews/a5-acceptance-evidence-quality-review.md`, A `docs/reviews/a5-integrated-technical-review.md`, A `docs/reviews/a5-giancarlo-individual-contribution-summary.md`, M `docs/reviews/release-readiness-review.md` | #101, #100, #90, #102, #103 | Giancarlo; Victor for decision |
| Release documents | A `docs/release/assignment5-release-package.md`, A `docs/release/changed-files-since-a4.md` (this file), A `docs/release/cycle2-handoff.md`, A `docs/release/presentation-index.md`, M `docs/release/release-notes.md`, M `docs/release/known-limitations.md`, M `docs/release/demo-script.md`, M `docs/release/traceability-summary.md` | #100, #86, #83, #95, #96 | Victor / Giancarlo |
| Planning | M `docs/planning/estimates.md`, M `docs/planning/risk-register.md`, M `docs/planning/schedule.md`, M `docs/planning/scope.md`, M `docs/planning/task-plan.md`, M `docs/planning/traceability.md` | #98, #91 | Malec; Victor |
| Team | M `docs/team/roles.md` | #99 | Malec |
| AI accountability | M `docs/ai/ai-use-log.md` | Most A5 PRs add their own entry | Each contributor |
| Repository entry point | M `README.md` (A5 status and evidence index) | This PR | Victor (owner) |

PR #94 was integrated through #95, and #80 was closed unmerged in favour of #81.

## No change since Assignment 4

| Area | Note |
|---|---|
| `src/main/` and `pom.xml` | Production code and build configuration identical to the A4 tag |
| `docs/requirements/` | REQ-001 to REQ-006 and acceptance criteria unchanged |
| `docs/architecture/` except `architecture.md` | API contracts, component responsibilities, data context and diagram unchanged |
| `docs/decisions/` ADR-001 to ADR-004 | Unchanged; ADR-0005 added |
| `docs/security/`, `docs/operations/`, `docs/observability/` | Still starter templates; completion is A6 work (TASK-018) |
| `data/`, `tests/`, `.github/ISSUE_TEMPLATE/`, `.github/pull_request_template.md` | Unchanged |

## Notes for reviewers

- **ADR-0005 file name.** The file is named `ADR-0005-in-memory-persistence.md`, but after #81 its content describes the implemented JDBC / file-backed H2 persistence, not in-memory storage. Read the content, not the file name.
- **Word summary.** Copy paths, owners and status from this index. For unchanged artifacts, keep the wording "No change since Assignment 4".
- **Source of truth.** If this index and `git diff --name-status a4-construction-integration <release SHA>` disagree, the git diff wins and this file must be corrected.
