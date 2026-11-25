# CampusConnect observability plan

For the local classroom release, the operator checks the Spring Boot console,
the loopback HTTP response, and the state returned by the workflow. The release
has no automated alerting, metrics exporter, or configured Actuator health
endpoint. The checks below are operator-triggered.
[Application configuration](../../src/main/resources/application.properties) ·
[Build dependencies](../../pom.xml)

| Signal | How to observe it | What it supports |
|---|---|---|
| Web server and H2 startup | Read the Tomcat and Hikari startup messages. Open the loopback root URL. | Confirm that the expected process and database path are in use. |
| Workflow and permission outcomes | Inspect returned HTTP status and JSON error code, or run the existing workflow and smoke scripts. | Check browser asset delivery, role boundaries, and expected failure responses. |
| Saved record after restart | Reselect its owner and compare the ID, status, and note after a same-store restart. | Verify ordinary restart persistence. |
| A failed local connection | Record the client error and check the application terminal and port. | Distinguish a stopped or unreachable process from an application response. |

Use [runtime evidence](runtime-evidence.md) for dated observations. Use the
[runbook](../operations/runbook.md) for startup and response steps.
[Metrics notes](metrics-notes.md) records what the existing check counts mean.
