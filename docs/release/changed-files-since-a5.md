# CampusConnect changes since Assignment 5

**COMP 330 — Software Project Management** · Team 02, Codex Ramblers

The submitted A5 baseline is `a5-cycle1-release` at `3f297d8bbe7b70ef9662f0bb1cb4796014bd6510`.
This snapshot is generated from the closeout tree based on merged main at
`e9ca7c2e5890270793148f5bebc130dd494ae02a`, including the closeout documentation changes. It contains
38 changed paths (14 added and 24 modified).
[Comparison through the merged base](https://github.com/victordodan/-comp330-f26-team-02/compare/3f297d8bbe7b70ef9662f0bb1cb4796014bd6510...e9ca7c2e5890270793148f5bebc130dd494ae02a)

## Candidate file inventory

| Repository path | Change | Purpose | Contributor | PR or change |
|---|---|---|---|---|
| `README.md` | Modified | Launch instructions and release status | Victor Dodan | [#132](https://github.com/victordodan/-comp330-f26-team-02/pull/132) |
| `docs/ai/ai-use-log.md` | Modified | Postmortem and Cycle 2 scope | Malec Tarabein | [#115](https://github.com/victordodan/-comp330-f26-team-02/pull/115) · [#137](https://github.com/victordodan/-comp330-f26-team-02/pull/137) · Closeout update |
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
| `docs/observability/metrics-notes.md` | Modified | Interpretation of automated and manual checks | Victor Dodan | [#137](https://github.com/victordodan/-comp330-f26-team-02/pull/137) |
| `docs/observability/observability-plan.md` | Modified | Local signals and gaps | Shelby Sierah / Victor Dodan | [#137](https://github.com/victordodan/-comp330-f26-team-02/pull/137) |
| `docs/observability/runtime-evidence.md` | Modified | Windows and Linux candidate observations | Giancarlo Herrera / Victor Dodan | [#137](https://github.com/victordodan/-comp330-f26-team-02/pull/137) |
| `docs/operations/incident-response-notes.md` | Modified | Local troubleshooting and recovery | Shú Perez / Victor Dodan | [#137](https://github.com/victordodan/-comp330-f26-team-02/pull/137) |
| `docs/operations/postmortem-cycle1.md` | Added | Postmortem and Cycle 2 scope | Malec Tarabein | [#115](https://github.com/victordodan/-comp330-f26-team-02/pull/115) · Closeout update |
| `docs/operations/runbook.md` | Modified | Launch, stop, restart and Windows Maven PATH | Shú Perez / Victor Dodan | [#137](https://github.com/victordodan/-comp330-f26-team-02/pull/137) |
| `docs/planning/cycle2-maturity-plan.md` | Added | Postmortem and Cycle 2 scope | Malec Tarabein | [#115](https://github.com/victordodan/-comp330-f26-team-02/pull/115) · [#137](https://github.com/victordodan/-comp330-f26-team-02/pull/137) · Closeout update |
| `docs/planning/re-estimation.md` | Modified | Re-estimation and operational ownership | Malec Tarabein / Victor Dodan | [#136](https://github.com/victordodan/-comp330-f26-team-02/pull/136) · [#137](https://github.com/victordodan/-comp330-f26-team-02/pull/137) |
| `docs/planning/schedule.md` | Modified | A6 closeout schedule | Malec Tarabein | [#136](https://github.com/victordodan/-comp330-f26-team-02/pull/136) |
| `docs/planning/task-plan.md` | Modified | A6 tasks and operational ownership | Malec Tarabein / Victor Dodan | [#136](https://github.com/victordodan/-comp330-f26-team-02/pull/136) · [#137](https://github.com/victordodan/-comp330-f26-team-02/pull/137) |
| `docs/release/assignment6-final-release-package.md` | Added | A6 evidence index | Shú Perez / Victor Dodan | [#133](https://github.com/victordodan/-comp330-f26-team-02/pull/133) |
| `docs/release/changed-files-since-a5.md` | Added | Canonical candidate change inventory | Shú Perez / Victor Dodan | [#134](https://github.com/victordodan/-comp330-f26-team-02/pull/134) · Closeout update |
| `docs/release/final-release-notes.md` | Added | Release scope and candidate evidence | Shú Perez / Victor Dodan | [#133](https://github.com/victordodan/-comp330-f26-team-02/pull/133) · Closeout update |
| `docs/release/known-limitations.md` | Modified | Classroom limits and residual risks | Shú Perez | [#135](https://github.com/victordodan/-comp330-f26-team-02/pull/135) |
| `docs/security/data-handling-notes.md` | Modified | Synthetic data and H2 persistence | Shelby Sierah / Victor Dodan | [#137](https://github.com/victordodan/-comp330-f26-team-02/pull/137) |
| `docs/security/permission-boundaries.md` | Modified | Demo roles and authorization controls | Shú Perez / Victor Dodan | [#137](https://github.com/victordodan/-comp330-f26-team-02/pull/137) |
| `docs/security/security-governance-checklist.md` | Modified | A6 implementation, verification and governance limits | Shelby Sierah / Victor Dodan | Closeout update |
| `scripts/demo-data.sh` | Added | Optional synthetic demo setup | Shú Perez | [#131](https://github.com/victordodan/-comp330-f26-team-02/pull/131) |
| `scripts/frontend-smoke-test.sh` | Added | HTTP smoke checks | Shú Perez | [#130](https://github.com/victordodan/-comp330-f26-team-02/pull/130) |
| `src/main/resources/static/app.js` | Added | Browser client for existing REST routes | Shú Perez | [#128](https://github.com/victordodan/-comp330-f26-team-02/pull/128) |
| `src/main/resources/static/index.html` | Added | Portal markup | Shú Perez | [#126](https://github.com/victordodan/-comp330-f26-team-02/pull/126) |
| `src/main/resources/static/styles.css` | Added | Responsive portal styles | Shú Perez | [#127](https://github.com/victordodan/-comp330-f26-team-02/pull/127) |
| `src/test/java/edu/loyola/campusconnect/controller/FrontendIntegrationTest.java` | Added | Frontend integration and session checks | Shú Perez | [#129](https://github.com/victordodan/-comp330-f26-team-02/pull/129) |
| `src/test/java/edu/loyola/campusconnect/controller/RequestControllerTest.java` | Modified | Resolution-note version-precondition regression coverage | Venkata Aravind Mareddy | [#113](https://github.com/victordodan/-comp330-f26-team-02/pull/113) |
| `test-evidence/a6-operations-0860160/restart.jsonl` | Added | Executed packaged restart transcript | Victor Dodan | [#137](https://github.com/victordodan/-comp330-f26-team-02/pull/137) |
| `test-evidence/a6-operations-0860160/workflow.jsonl` | Added | Executed packaged HTTP transcript | Victor Dodan | [#137](https://github.com/victordodan/-comp330-f26-team-02/pull/137) |

## Evidence and pending changes

The browser evidence at `373a0f6` and the Linux and Windows runtime evidence
at `0860160` retain their tested revisions. Check the [runtime record](../observability/runtime-evidence.md)
and [release index](assignment6-final-release-package.md). The inventory covers
files present in this snapshot. Any approval or change after this snapshot
requires a refresh against the exact submission revision.

## Final reconciliation

Verify the tag resolves to the approved commit, then confirm the inventory from
the two tag trees. The Word evidence index should identify any required unchanged
artifact as **No change since Assignment 5**.

```sh
git diff --name-status a5-cycle1-release a6-final-release
git diff --stat a5-cycle1-release a6-final-release
```
