# CampusConnect — A5 Defect and Quality Review

**Original preparer:** Giancarlo Herrera, Quality & Review Lead
**Reviewed checkpoint:** `56c0907c7c317156f1a86ac8dcc9b9f158052b9d`
**Status:** Updated draft; human quality recommendation and release approval pending.

## Assessment

Six finding IDs are retained in the [finding register](defect-log.md).
Persistence and identity documentation has been corrected through merged #81/#83.
The claim that #84 deleted four tests is unsupported: its diff changes static
imports only, preserving all 14 controller tests and helpers. Merged #92 now
provides exact-revision historical CI evidence. These are evidence corrections,
not newly verified runtime fixes or proof of zero defects.

| Finding | Updated assessment | Remaining work |
|---|---|---|
| A5-F01 | File-backed H2/JDBC documented consistently in merged corrections | Final-revision restart/rollback evidence; recovery limits remain |
| A5-F02 | Server-session identity described correctly; forged headers do not establish authority | Retain synthetic-selector limitation and obtain risk decision/negative-path evidence |
| A5-F03 | Import-only change and historical 35-test success verified; replacement register drafted in this PR | Human register/disposition review; no compilation defect asserted |
| A5-F04 | Twelve cases mapped to passing automation; no separate dated case records established | Case-specific acceptance dispositions with actual evidence or explicit deferrals |
| A5-F05 | Real Maven CI already configured; manual starter guard is separate | Final-SHA CI/review/release decision and tag; reconcile guard and documentation-only CI filtering |
| A5-F06 | Historical inventory is not complete dependency assurance | Advisory, transitive-license and maintenance decisions with named human risk acceptance |

## CI evidence boundary

[Run #41](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42613709038)
ran `mvn -B clean verify` on merge SHA
`0fcc79c29dfcdc89403d3c230f47438cb00c358c` (head
`00c034a74f22ce616f707848168909289b2db001`, base
`70d3726b64c0deafef2d770870bb14ef8f28b79b`). It reports 35 tests,
zero failures/errors/skips and successful packaging. This historical run is
not CI for the current checkpoint or a final A5 release. No new execution
is claimed by these documentation corrections.

## Accountability and release recommendation

Mareddy coordinates test/acceptance evidence; Shú supports implementation and
runtime verification; Giancarlo reconciles the register and traceability;
Victor assigns independent reviewers and records risk and release decisions.
These coordination expectations do not attest that an individual performed
unrecorded work or approved a finding.

Requirements/criteria still carry Proposed status. The demo script and release
notes on reviewed main retain starter content; proposed #85/#86/#88/#89/#90
must not be treated as merged final release proof. See the
[traceability ledger](../planning/traceability.md) for implemented evidence and
remaining demo/release linkages.

**Release recommendation: NOT YET DETERMINED.** The A4 Proceed with Conditions
assessment and documentation merges do not constitute A5 acceptance. No new
runtime test, security scan, defect reproduction, or final release approval
was performed by this correction. There is insufficient confirmed defect data
to calculate a meaningful defect rate or root-cause trend.

**AI disclosure:** Codex assisted with checking and correcting the original
Giancarlo draft. Human review remains pending; see [AI-use log](../ai/ai-use-log.md).
