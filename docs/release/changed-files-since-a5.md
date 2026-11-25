# CampusConnect changes since Assignment 5

**COMP 330 — Software Project Management** · Team 02, Codex Ramblers

The submitted A5 baseline is `a5-cycle1-release` at `3f297d8bbe7b70ef9662f0bb1cb4796014bd6510`.
The comparison ends at verified main candidate `373a0f6b6d6190d6067099178a8c71862bb12dec`.
It contains 21 merged file changes (9 added, 12 modified), based on the two commit trees.
[Candidate comparison](https://github.com/victordodan/-comp330-f26-team-02/compare/3f297d8bbe7b70ef9662f0bb1cb4796014bd6510...373a0f6b6d6190d6067099178a8c71862bb12dec) · [Candidate CI](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/45509064331)

## Merged file inventory

| Repository path | Change | Purpose | Contributor | PR |
|---|---|---|---|---|
| `README.md` | Modified | Launch instructions and release status | Victor Dodan | [#132](https://github.com/victordodan/-comp330-f26-team-02/pull/132) |
| `docs/ai/ai-use-log.md` | Modified | Postmortem and Cycle 2 scope | Malec Tarabein | [#115](https://github.com/victordodan/-comp330-f26-team-02/pull/115) |
| `docs/architecture/api-contracts.md` | Modified | Current API contracts | Shú Perez | [#118](https://github.com/victordodan/-comp330-f26-team-02/pull/118) |
| `docs/architecture/architecture-diagram.png` | Modified | Architecture diagram | Shú Perez | [#117](https://github.com/victordodan/-comp330-f26-team-02/pull/117) |
| `docs/architecture/architecture.md` | Modified | Current application architecture | Shú Perez | [#116](https://github.com/victordodan/-comp330-f26-team-02/pull/116) |
| `docs/architecture/component-responsibilities.md` | Modified | Component responsibilities | Shú Perez | [#119](https://github.com/victordodan/-comp330-f26-team-02/pull/119) |
| `docs/architecture/data-context.md` | Modified | Data ownership and visibility | Shú Perez | [#120](https://github.com/victordodan/-comp330-f26-team-02/pull/120) |
| `docs/decisions/ADR-0005-in-memory-persistence.md` | Modified | JDBC storage clarification | Shú Perez | [#125](https://github.com/victordodan/-comp330-f26-team-02/pull/125) |
| `docs/decisions/ADR-002-cycle-1-technology-stack.md` | Modified | Reconcile accepted stack with implementation | Shú Perez | [#122](https://github.com/victordodan/-comp330-f26-team-02/pull/122) |
| `docs/decisions/ADR-003-request-consistency.md` | Modified | Concurrency and consistency limits | Shú Perez | [#123](https://github.com/victordodan/-comp330-f26-team-02/pull/123) |
| `docs/decisions/ADR-004-local-persistence-and-recovery.md` | Modified | Persistence and recovery boundaries | Shú Perez | [#124](https://github.com/victordodan/-comp330-f26-team-02/pull/124) |
| `docs/decisions/ADR-006-rest-api-interface-alignment.md` | Added | Accept REST for the classroom release | Shú Perez | [#121](https://github.com/victordodan/-comp330-f26-team-02/pull/121) |
| `docs/operations/postmortem-cycle1.md` | Added | Postmortem and Cycle 2 scope | Malec Tarabein | [#115](https://github.com/victordodan/-comp330-f26-team-02/pull/115) |
| `docs/planning/cycle2-maturity-plan.md` | Added | Postmortem and Cycle 2 scope | Malec Tarabein | [#115](https://github.com/victordodan/-comp330-f26-team-02/pull/115) |
| `scripts/demo-data.sh` | Added | Optional synthetic demo setup | Shú Perez | [#131](https://github.com/victordodan/-comp330-f26-team-02/pull/131) |
| `scripts/frontend-smoke-test.sh` | Added | HTTP smoke checks | Shú Perez | [#130](https://github.com/victordodan/-comp330-f26-team-02/pull/130) |
| `src/main/resources/static/app.js` | Added | Browser client for existing REST routes | Shú Perez | [#128](https://github.com/victordodan/-comp330-f26-team-02/pull/128) |
| `src/main/resources/static/index.html` | Added | Portal markup | Shú Perez | [#126](https://github.com/victordodan/-comp330-f26-team-02/pull/126) |
| `src/main/resources/static/styles.css` | Added | Responsive portal styles | Shú Perez | [#127](https://github.com/victordodan/-comp330-f26-team-02/pull/127) |
| `src/test/java/edu/loyola/campusconnect/controller/FrontendIntegrationTest.java` | Added | Frontend integration and session checks | Shú Perez | [#129](https://github.com/victordodan/-comp330-f26-team-02/pull/129) |
| `src/test/java/edu/loyola/campusconnect/controller/RequestControllerTest.java` | Modified | Resolution-note version-precondition regression coverage | Venkata Aravind Mareddy | [#113](https://github.com/victordodan/-comp330-f26-team-02/pull/113) |

## Candidate evidence

The [quality review evidence](https://github.com/victordodan/-comp330-f26-team-02/blob/a6-quality-defect-review-gherrera/docs/testing/final-test-evidence.md#november-24-browser-candidate) records the 51 Maven tests, 38 HTTP
checks, Chromium workflow, and repeat demo setup run on `373a0f6`.

## Open documentation work

PRs #112 and #133–#136 are outside the candidate comparison. The quality-team
contributions originally inventoried in #112 remain attributable to Giancarlo
Herrera and the named supporting owners.

| Pending path | Change included in #112 | Owner |
|---|---|---|
| `docs/quality/defect-review.md` | Add A6 defect dispositions, residual limitations and release-assurance gaps; correct Markdown | Giancarlo |
| `docs/quality/a6-final-release-quality-gate.md` | New A6 quality gate with 11 evidence checks and decision template | Giancarlo |
| `docs/planning/traceability.md` | Add REQ-001–006 / TC-001–012 Cycle 2 traceability, maturity and evidence ledger; correct Markdown | Giancarlo |
| `docs/ai/ai-use-log.md` | Add A6 AI-assistance disclosure and verification limits | Giancarlo |
| `docs/testing/final-test-evidence.md` | Add A6 final testing intake with exact-SHA and case-evidence gaps | Giancarlo / Mareddy |
| `docs/testing/ci-evidence.md` | Add A6 CI intake for run, SHA, test totals and skipped checks | Giancarlo / Mareddy / Shu |
| `docs/planning/risk-register.md` | Add A6 risk and limitation disposition intake | Giancarlo; Victor approves risks |

## Final reconciliation

After approval, regenerate the inventory from `a5-cycle1-release` to the approved
A6 SHA and verify that `a6-final-release` resolves to it. Link any required artifact
that has no difference as **No change since Assignment 5**. Keep the Word summary
and release notes aligned with the verified submission revision.

```sh
git diff --name-status a5-cycle1-release a6-final-release
git diff --stat a5-cycle1-release a6-final-release
```

Final review and release authorization are pending.
