# A5 Packaged HTTP Demo Rehearsal

**Operator:** Codex automated tool execution; human acceptance pending.
**Start (America/Chicago):** `2026-11-09T21:43:07.986110-06:00`. Raw events use UTC.
**Application source:** `714a31bf5c9283e732fcfd895615a3471fef93b4`; no application-source or POM change for this work.
**Runtime:** Temurin JDK 17.0.17+10, Maven 3.9.9, packaged Spring Boot/H2 application on loopback port 18080.

## Actual results

| Exercise | Observed outcome | Evidence |
|---|---|---|
| Clean build, tests and packaging | PASS — 35 tests, zero failures/errors/skips | [build summary](../../test-evidence/a5-http-demo/build-summary.txt) |
| Initial normal workflow and negative cases | PASS — 36 HTTP checks plus JSON/state assertions | [transcript](../../test-evidence/a5-http-demo/workflow.jsonl) |
| Full packaged process restart, same H2 store | PASS — 5 HTTP checks; saved record and ETag retained; unique new ID | [restart transcript](../../test-evidence/a5-http-demo/restart.jsonl) |
| Repeat workflow against persisted existing records | PASS — 36 HTTP checks | [repeat transcript](../../test-evidence/a5-http-demo/repeat.jsonl) |
| Stopped-server client failure control | Expected FAIL and exit 1; no false successful demo | [control transcript](../../test-evidence/a5-http-demo/transport-failure.jsonl) |

The first process was terminated normally and fully exited before a second JVM
started the same jar with the same datasource URL. New cookie jars reselected
identities before the restart comparison. Both application processes are stopped
after rehearsal. Data and Maven/JDK setup remain in workspace scratch paths,
not tracked database files.

## Reproduction and identity

Follow [demo procedure](../release/demo-script.md). The executable belongs to
companion [PR #97](https://github.com/victordodan/-comp330-f26-team-02/pull/97);
this PR #96 owns the procedure and actual results, avoiding two divergent scripts.
PR #97 is now merged into main; this PR supplies the procedure and evidence. See [environment/hash manifest](../../test-evidence/a5-http-demo/environment.json)
for the exact local script and jar fingerprints, source tree and per-class totals.
The reviewed main source was merged locally into each branch without committing;
these logs do not claim to test a nonexistent final demo/release commit.

## Scope and remaining work

The rehearsal exercises submission, role/ownership, current notes/status,
resolve/reopen, missing/stale ETags, invalid input and oversized/blank notes,
unknown IDs and retention of one In Progress record across a process restart.
It provides related evidence for TC-001–TC-012; it does not declare every
criterion fully accepted. Failed-write injection, rollback/concurrency races,
all field boundaries, other restart states and backup restoration are separate.
No dependency scan, license signoff, real authentication, graphical UI or human
presentation rehearsal was performed. Final candidate review, case dispositions
and A5 release approval remain open.

**AI disclosure:** Codex corrected the original demo assumptions, built and ran
the application, executed the client and captured actual responses. No personal
student execution or independent human signoff is implied.
