# A5 Final Testing Evidence Index — Candidate

**Review checkpoint:** `ac228d07bc950251faee006b7ab98857fde81f43`. Final release/tag: not selected.
**Coordinator:** testing owner; **review:** Giancarlo; **release decision:** Victor. Assignments require team confirmation.

| Evidence | Actual observed result | Tested revision / scope |
|---|---|---|
| [PR #95 CI](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42746026082) | clean verify: 35 tests, zero failures/errors/skips; packaging passed | Synthetic merge `5d791a7b657209a6c688251149db92828b313606`; see CI evidence for head/base |
| [Local packaged rehearsal](a5-demo-rehearsal.md) / [raw records](../../test-evidence/a5-http-demo/README.md) | JDK17 build: 35 tests passed; 36 initial, 5 restart and 36 repeat HTTP checks passed; stopped-server failure control exited 1 | Source `714a31bf5c9283e732fcfd895615a3471fef93b4`, script/jar hashes in manifest |
| [Acceptance assessment](https://github.com/victordodan/-comp330-f26-team-02/pull/101) | PR101 proposes a twelve-case assessment; mapping corrections prepared locally, integration and human approval pending | Documentary assessment, no new case execution claimed |
| [Dependency inventory](../quality/a5-dependency-disposition.md) | Fresh dependency resolution succeeded; advisory query blocked | Current application/POM checkpoint, not vulnerability clearance |

A source/POM diff from the rehearsal source to this checkpoint is empty. That
supports reuse for unchanged behavior, but is not a new test of every later
commit. Logs retain their original SHAs. Before finalization, identify the exact
candidate, link its CI run and record independent review and case decisions.
Human presentation rehearsal, dependency acceptance and final release approval
remain pending. This index summarizes existing executions; it runs no new tests.

## Reuse of PR #101 — review findings

PR #101 already supplies the twelve-case evidence reconciliation. Reuse it after
review rather than adding a second crosswalk. It remains unmerged and grants no
human acceptance. Two mapping corrections have been prepared locally on #101;
verify they are included in the version reviewed/merged. The corrected scope is: TC-008's transcript inspects Student A's detail and lists both students;
it does not inspect both detail endpoints (the controller test covers both).
TC-005's unknown-UUID exercise is a reviewer status update, not a student unknown-ID
lookup; distinguish those paths and cite JUnit evidence where appropriate.
Use neutral attribution for the tool-executed procedure/rehearsal; don't infer
personal student execution. The #101 corrections were checked against recorded
responses and controller test source; no new case execution or acceptance is claimed.
