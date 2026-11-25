# CampusConnect metrics notes

The reported totals are counts of assertions passed during bounded test
executions. Record the revision, environment, date, and operator alongside each
count so another reviewer can reproduce the same scope.
[Runtime evidence](runtime-evidence.md)

| Measure | Source | Interpretation |
|---|---|---|
| Maven tests passed, failed, errored, or skipped | Maven Surefire summary and GitHub Actions | Packaged build and automated test results for one revision. |
| HTTP workflow and restart checks passed | `scripts/demo-requests.sh` transcripts | Expected HTTP results and record state for the synthetic scenario used. |
| Frontend smoke checks passed | `scripts/frontend-smoke-test.sh` summary | Static assets and HTTP/session behavior covered by the script. |
| Manual observations | Named operator's dated evidence | Only the workflow steps and conditions the operator observed. |

Sustained load, request latency, uptime, resource use, and alert response
remain unmeasured. The local release has no operational telemetry dashboard.
[Observability plan](observability-plan.md) ·
[Known limitations](../release/known-limitations.md)
