# A5 release authorization and residual-risk decisions

Decision date: 2026-11-10 UTC. Accountable release authority and risk owner:
Victor Dodan. Recorded by Codex under Victor's explicit instruction in this
session to make the case, risk and release decisions on his behalf. This is
attributable delegated authorization, not a claim Victor personally executed
tests, a new approval by Mareddy, or an independent non-author review.

## Decision: CONDITIONAL GO — bounded classroom release

Authorize the implemented Cycle 1 HTTP workflow for a local demonstration and
course repository submission using synthetic data only, bound to 127.0.0.1.
No public hosting, real student information, institutional authentication,
production security assurance or binary redistribution outside the course is
authorized. This decision accepts the residual risks below for A5 only.
It supersedes historical pending Victor case/risk/release decisions in linked
records; it does not rewrite prior test results or other people's approvals.

Verified integration checkpoint: `5fab39a6b6fd64bf82756d5766c617f34b778430`
(merged #109 and #108). Fresh JDK17/Maven clean verify passed 35 tests with
zero failures/errors/skips, build finished 2026-11-10T06:44:48Z.
[Machine-readable result](../../test-evidence/a5-release-authorization/build.json).
The source, POM and demo script are unchanged from the earlier executed
`531d54ca0f391e218752888900c4668173f75e27` HTTP/restart checkpoint.
No new packaged HTTP execution is implied by this build.

## Acceptance decisions

TC-001–TC-012: **PASS within documented classroom scenario scope**, decided by
Codex under Victor's delegated release/acceptance authority on 2026-11-10.
The [case records](../testing/a5-acceptance-closeout.md) supply criteria,
executed methods, operator, environment, source SHA and transcript links.
There were not twelve new manual runs. Mareddy's #109 is his evidence review;
no final personal acceptance is attributed to him.

- TC-002: accept the observed rejection, structured BAD_INPUT error handling,
  source-reviewed validator messages and passing boundary tests. Exact HTTP
  validation-message text is not asserted. Defer that additional assertion to
  the next validation change; Victor owns the follow-up before broader use.
- TC-007: accept saved In Progress record/note/fields/ETag retention across
  separate JVMs using the same file database. Other-state restart and backup
  restoration are not certified; defer recovery coverage to Cycle 2 planning.

The course's separate independent-review requirement is retained below; delegated
case decisions do not satisfy or impersonate that requirement.

## Residual risks and explicit treatment

All deferrals expire at the end of the A5 classroom demonstration/submission
scope, and must be revisited before Cycle 2 implementation or any wider use,
whichever occurs first. Victor retains accountability; proposed technical
coordinators elsewhere are not assigned unrecorded approvals.

| Risk | Decision and mitigation | Accountable owner / next action |
|---|---|---|
| Tomcat 10.1.55 advisory findings, including CVE-2026-55956 and CVE-2026-55276 | ACCEPT bounded-demo exposure; DEFER compatible stack upgrade. Preserve loopback/synthetic scope; no immunity or zero-CVE claim. Configuration limits some described paths but is not a clearance. | Victor: arrange stack update and rerun verification before broader use; see dependency disposition sources. |
| Boot 3.5 support and no established commercial entitlement | ACCEPT short-lived classroom use; DEFER supported-stack migration. | Victor: review support/update path during Cycle 2 planning, before continued non-demo operation. |
| Incomplete whole-graph/plugin advisory and license-obligation review; blocked OSV API | ACCEPT uncertainty for private source/course use; DEFER complete assurance. Inventory is not vulnerability/legal clearance. No external binary redistribution. | Victor: commission full graph/plugin review and notices before broader distribution/use. |
| H2 dual-license election | Select MPL 2.0 for this bounded release, using unmodified upstream H2; retain upstream license material. Defer complete distribution/notice compliance review; no legal certification. | Victor: verify notices/source obligations before redistribution outside the course. |
| LIM-001 same-store persistence, no verified backup restoration | ACCEPT synthetic-data loss risk; use disposable synthetic records and consistent database path. DEFER backup/recovery and capacity assurance. | Victor: prioritize operational recovery before real data. |
| LIM-002 selectable fixed Reviewer identity | ACCEPT as intentional simulation only; require loopback and synthetic data. DEFER real authentication. | Victor: prevent public/real-user deployment. |
| LIM-003 no push/email notifications | ACCEPT agreed pull-based scope; student retrieves current status/notes. | Victor: decide any future notification scope separately. |
| LIM-004 API without verified browser workflow | ACCEPT HTTP demonstration as release interface. | Victor: use the documented executable demo. |
| LIM-005 production deployment/rollback/monitoring unverified | DEFER production readiness; no production release authorized. | Victor: review before any operational rollout. |
| Missing dedicated feature issue links / incomplete historical effort data | ACCEPT disclosed traceability/process gaps; preserve real PR/test links and do not invent issues or time entries. | Victor: maintain prospective issue and effort records. |

## Review provenance and prerequisites for the tag

Giancarlo approved #109's evidence reconciliation and #108's revised evidence/
risk documentation. Those approvals do not certify a final integrated application
revision or constitute his release GO. The independent final integrated review
requested by the instructor remains **OPEN**: a named non-author reviewer must
record the exact candidate, findings and disposition. Victor's delegation to
Codex does not replace independent human review.

Release authorization is recorded now; execute the tag only when:

1. This branch is reviewed and merged, including a recorded independent
   integrated review or a separately authorized instructor-compatible disposition.
2. CI passes on the exact resulting main commit. Preserve run URL and JUnit
   counts; source/POM changes require renewed targeted/runtime verification.
3. Record that exact commit in the GitHub release/tag annotation, along with
   this conditional authorization and known limitations. Avoid a self-referential
   commit embedded into a file within itself. The final SHA is not this branch's
   parent merely because its tests pass.
4. Create/push `a5-cycle1-release` once, at that approved revision. No tag is
   created by this work. Later fixes require disclosed post-freeze handling.

## Submission handoff

[Word evidence index](CampusConnect-A5-Release-Index.docx) and
[six-slide presentation](CampusConnect-A5-Release-Presentation.pptx) are generated
from repository evidence. They are prepared artifacts, not proof of a completed
human presentation rehearsal or Sakai submission. Victor reviews the final
team information and presentation, includes the clone URL in Sakai comments,
and submits the team package. Each student privately completes their own peer
review; no personal peer review or acknowledgement is fabricated here.
