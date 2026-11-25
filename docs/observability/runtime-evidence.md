# CampusConnect runtime evidence

These checks used synthetic data and a local file-backed H2 store. Each
result applies to its recorded revision and execution scope.

## November 24, 2026 candidate at `0860160`

| Operator and environment | Executed check | Observed result |
|---|---|---|
| Codex, Linux, Java 17.0.17, Maven 3.9.9 | Maven clean verify and packaging | 51 tests passed, zero failures, errors, or skips. [CI on the same SHA](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/45543404188) |
| Codex, packaged app at `127.0.0.1:18080` with an isolated file-backed store | Startup and HTTP scenario | The portal loaded. All 36 workflow checks passed. [Workflow transcript](../../test-evidence/a6-operations-0860160/workflow.jsonl) |
| Codex, same JAR, port, and H2 path | Controlled stop and restart | Curl reported connection refused while the app was stopped. After restart, the owner and reviewer could retrieve the saved request. The original ID, status, note, and ETag matched. All five restart checks passed. [Restart transcript](../../test-evidence/a6-operations-0860160/restart.jsonl) |
| Giancarlo Herrera, Windows PowerShell 5.1, Temurin Java 17.0.21 | Maven build, manual browser workflow, controlled stop and same-store restart | Giancarlo reported 51 passing Maven tests and 10 of 10 scoped manual checks. He observed the same request ID, status, and note after restart. [Windows operator record in #112](https://github.com/victordodan/-comp330-f26-team-02/blob/30f1231b9042439ce363541bd6db0d41c63da5b2/docs/testing/final-test-evidence.md#november-24--quality-lead-windows-operational-exercise) |

The full tested revision was `0860160bc458af670c7944ccd035d21425e7511d`.
The transcript timestamps use UTC, which was still November 24 in Chicago.
The Codex run covers direct HTTP assertions and the stopped-process signal.
Giancarlo's operator record covers the Windows browser workflow and his
confirmed restart. Attach future observations to the actual operator and SHA.

## Earlier browser candidate

On `373a0f6`, the packaged app passed 38 HTTP smoke checks and a Chromium
workflow covering student submission and isolation, reviewer updates, stale-edit
rejection, and owner visibility. A repeat synthetic seed run skipped all six
existing requests. [Browser candidate evidence in #112](https://github.com/victordodan/-comp330-f26-team-02/blob/30f1231b9042439ce363541bd6db0d41c63da5b2/docs/testing/final-test-evidence.md#november-24-browser-candidate)

## Ownership and remaining checks

Shú Perez owns the runbook. Victor Dodan is assigned to execute and record
follow-up observations. Giancarlo Herrera retains attribution for his completed
Windows exercise. No Victor-run result is recorded here yet.

Same-store restart preserves the observed data. Backup restoration, crash
recovery, and application rollback remain unverified. Final acceptance and
release authorization are tracked separately. [Runbook](../operations/runbook.md) ·
[Known limitations](../release/known-limitations.md)
