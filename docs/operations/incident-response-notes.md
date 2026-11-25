# Classroom demo incident response

For a local failure, the current operator records the revision, time, URL,
synthetic test action, expected result, and actual signal. Shú Perez maintains
the runbook. Victor Dodan records observations from follow-up checks he runs.
[Runbook](runbook.md)

1. Check whether the application is running, which loopback port it binds,
   and the datasource path in its Spring Boot startup logs.
2. Reproduce the symptom once with synthetic data and preserve the relevant log
   lines or HTTP result. Keep session cookies and database files out of evidence.
3. For a stopped process, restart with the same working directory and H2 path
   and verify the original request. For storage errors, preserve the store and
   check whether an operation took effect before retrying.
4. Record the action and outcome. If the workflow is still blocked, record
   the unresolved symptom and tell the release lead before resuming the demo.

The recorded connection refusal came from a controlled shutdown check.
The evidence covers planned local recovery. Backup restoration, recovery after
a crash, and application rollback remain unverified.
[Runtime evidence](../observability/runtime-evidence.md) ·
[Known limitations](../release/known-limitations.md)
