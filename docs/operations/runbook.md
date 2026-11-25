# CampusConnect classroom runbook

Shú Perez owns this runbook. Giancarlo Herrera completed the November 24
Windows exercise. Victor Dodan takes over execution and observation recording
for follow-up checks. Record each run under the person who performed it.
[Runtime evidence](../observability/runtime-evidence.md)

## Build and start

Use Java 17 and Maven from the repository root. Use synthetic data on a
trusted local machine. The application defaults to `127.0.0.1:8080` and stores
data in a file-backed H2 database at `./data/campusconnect` relative to the
process working directory. [README](../../README.md) ·
[Application configuration](../../src/main/resources/application.properties)

Build and start the packaged app with an isolated store:

```sh
git rev-parse HEAD
java -version
mvn -B clean verify
mkdir -p work/demo-runtime
java -jar target/campusconnect-0.1.0-SNAPSHOT.jar --server.port=18080 --spring.datasource.url=jdbc:h2:file:./work/demo-runtime/campusconnect
```

In Windows PowerShell, use `New-Item -ItemType Directory -Force work/demo-runtime`
for the directory step. If launching with the default settings, open
`http://127.0.0.1:8080/`. For the command above, use
`http://127.0.0.1:18080/`. Both URLs work without a hosts-file entry.

## Verify and restart

1. Check the startup log for the web server port and H2 path. Open the portal.
2. As Student A, submit one synthetic request. Record its ID. Switch to Student B
   and check that the request is absent. Switch to Reviewer, move it to
   In Progress, save a resolution note, and resolve it. As Student A, check the
   final status and note.
3. Stop the process with Ctrl+C and wait for it to exit. A local request should
   now fail to connect. Run the same JAR again from the same directory with the
   same port and datasource URL. Reselect Student A and confirm the ID, status,
   and note match the saved record.
4. Record the tested commit, date, platform, operator, actual signal and outcome
   in [runtime evidence](../observability/runtime-evidence.md).

For the repeatable HTTP workflow and persisted-record comparison, follow the
[packaged workflow procedure](../release/demo-script.md) using its 18080
commands. The separate [frontend smoke script](../../scripts/frontend-smoke-test.sh)
targets the default port 8080.

## Troubleshoot

| Symptom | Check and response |
|---|---|
| Connection refused | Check the application terminal and port. Start the app if stopped. If startup failed, read the first relevant exception. |
| H2 startup failure or file lock | Verify the directory is writable and the path matches the intended isolated store. Stop any other instance using that store before retrying. |
| Portal opens but the saved request is missing | Reselect the original student identity and check the startup log for the datasource path. A different working directory or datasource can open a different store. |
| Reviewer update conflicts | Refresh the request and inspect the current version before applying the update again. |

Preserve the database files during diagnosis. Same-store restart was verified.
Backup restoration, crash recovery, and application rollback remain unverified.
[Incident response notes](incident-response-notes.md) ·
[Known limitations](../release/known-limitations.md)
