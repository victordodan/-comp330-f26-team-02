# Continuous Integration Evidence — CampusConnect

**Proposed configuration:** PR #93; execution of this revision remains pending.
**Reviewed main checkpoint:** `b899a1b40b9d0539ae6152ee2ad874338533673a`

## Workflow and scope

[ci.yml](../../.github/workflows/ci.yml), named **Java CI with Maven**, is the
entry workflow for pushes to `main`, pull requests targeting `main`, and manual
`workflow_dispatch`. There are no path filters, so documentation-only changes
also receive checks. Manual dispatch allows verification of a selected branch
or tag after this workflow becomes available on the default branch.

It calls [a4-validation-tests.yml](../../.github/workflows/a4-validation-tests.yml)
from the same revision. That file retains the historical path but now exposes
only `workflow_call`, preventing duplicate automatic builds. The prior
`a4-aravind-request-validation` push trigger is removed; work on that branch
is covered through PRs to main or manual dispatch. No application secrets are
passed to the reusable workflow.

The reusable job checks out the event revision, sets up Temurin Java 17 with
Maven caching, runs `mvn -B clean verify` and uploads `target/surefire-reports/`
as `java-test-reports`, including on failure when reports exist. Missing reports
produce a warning; Maven failures still fail the job. Both workflows request
`contents: read`; the test job has a 15-minute timeout. Standard checkout,
setup-java and upload-artifact actions use version 4.

`clean verify` includes compilation, automated tests and packaging; it does not
establish separate acceptance execution, vulnerability clearance, transitive
license approval, backup recovery, production readiness or deployment.

## Existing execution evidence

Before this PR, actual Java CI already existed in `a4-validation-tests.yml`;
`ci.yml` was a separate manual starter guard. This change consolidates their
roles; it does not claim that the project previously lacked automated tests.

[Run #41](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42613709038)
ran `mvn -B clean verify` successfully on historical PR #84 merge SHA
`0fcc79c29dfcdc89403d3c230f47438cb00c358c` (head
`00c034a74f22ce616f707848168909289b2db001`, base
`70d3726b64c0deafef2d770870bb14ef8f28b79b`). The run reports 35 tests,
zero failures/errors/skips and successful packaging. See
[job logs](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42613709038/job/129148251587)
and [reports](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42613709038/artifacts/13671781417).

This historical run does not verify the changed workflow in PR #93 or the
final integrated A5 revision. The merged
[#92 report](https://github.com/victordodan/-comp330-f26-team-02/pull/92)
maps related automated coverage to all twelve planned acceptance cases and
keeps separate case execution/acceptance gaps explicit.

## Validation and final release evidence

Actionlint 1.7.7 passed on both changed workflows. Local review also checks
event scope, reusable-workflow wiring,
permissions, command and report-upload configuration. No GitHub execution of
these staged changes or fresh application test result is claimed here.

After committing/pushing, inspect the actual PR check, record its tested SHA,
workflow/job URL, test totals, failures/skips and report link. For the final
integrated release, record a run against the intended release revision and
obtain independent review, case-specific dispositions and a release decision.
The reusable workflow exposes a nested check (verify / test); confirm repository
branch-protection rules require the new check if required-check rules exist.
No branch-protection configuration was changed by this local work.

**AI disclosure:** Codex assisted workflow review, configuration corrections and
evidence documentation. Human review and actual CI execution remain separate.


---

## Assignment 6 — CI final-baseline intake (2026-11-23)

**Status: OPEN.** This entry does not report a newly executed workflow. The historical CI links above are not proof of a passing A6 final SHA. Record the workflow actually used, its triggering event and whether path filters, manual-only jobs, skipped checks or branch protection affected the candidate.

| Required field | A6 value |
|---|---|
| Final candidate commit SHA / branch | Pending |
| Workflow file, event and run URL | Pending |
| Job status, run conclusion and completion timestamp | Pending |
| Maven/test totals, failures, errors and skips | Pending |
| Test report/artifact URL | Pending |
| Skipped or non-required checks and explanation | Pending |
| Independent reviewer and final tag SHA match | Pending |

**Scoped A6 evidence:** [PR #113](https://github.com/victordodan/-comp330-f26-team-02/pull/113) added HTTP 428/400 regression coverage for resolution-note updates. The PR records 36 tests passed in [run #73](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/45249178158), and Giancarlo verified [run #74](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/45253604010) on the exact PR head before approval. Treat these as PR-level evidence.

**Acceptance rule:** The tested commit and the eventual `a6-final-release` tag target must be reconciled; a documentation-only CI run cannot substitute for verified application behavior. See [A6 quality gate](../quality/a6-final-release-quality-gate.md). AI drafted this intake; it did not run CI.
