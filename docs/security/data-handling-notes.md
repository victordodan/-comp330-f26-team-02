# Classroom data handling

Use synthetic requests only. A request contains its ID, synthetic student
owner, title, category, description, status, resolution note, creation and
update times, and version. The application assigns the ID, owner, timestamps,
and version on the server. [Request model](../../src/main/java/edu/loyola/campusconnect/model/SupportRequest.java) ·
[Workflow service](../../src/main/java/edu/loyola/campusconnect/service/WorkflowService.java)

The browser and API use the same local application. The server session holds
the selected demo identity, and the browser carries the session cookie. The
request record lives in file-backed H2. The default datasource path is
`./data/campusconnect` relative to the app's working directory. H2 console
access is disabled. Reselect a demo identity after restart to retrieve the
saved record. [Application configuration](../../src/main/resources/application.properties) ·
[Permission boundaries](permission-boundaries.md) ·
[Runtime evidence](../observability/runtime-evidence.md)

For exercises and seed runs, specify a dedicated H2 path under a local work
directory, preferably outside the checkout for data that will be retained.
The optional seed script writes persistent examples after confirmation.
Store only synthetic excerpts in shared evidence. Do not commit the H2 files,
session cookies, or local secrets. [Runbook](../operations/runbook.md) ·
[Demo-data script](../../scripts/demo-data.sh)

Local files persist until the operator removes them. Stop the application
before removing an isolated store that is no longer needed. Preserve any store
needed for troubleshooting or evidence. Same-store restart was verified. Backup
restoration and production retention controls remain unverified.
[Known limitations](../release/known-limitations.md)
