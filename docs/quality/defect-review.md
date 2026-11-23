# CampusConnect — A5 Defect and Quality Review

> Current decision (2026-11-10): [Victor-authorized conditional classroom release](../release/a5-release-decision.md).
> TC-001–TC-012 accepted within documented scope; residual risks explicitly
> accepted/deferred. Historical pending decisions below retain their original
> context. Final integrated human review is explicitly deferred for this conditional
> baseline under Victor’s release authority; the course condition is unfulfilled.
> Exact-main CI is required before tagging; no production/security/legal clearance.

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


---

# Assignment 6 — Cycle 2 Quality Lead Defect Review (initial baseline, 2026-11-23)

**Tracking:** [Issue #111](https://github.com/victordodan/-comp330-f26-team-02/issues/111)  
**Prepared for independent review by:** Giancarlo Herrera, Quality & Review Lead  
**Scope:** Initial Cycle 2 evidence inventory; no Cycle 2 implementation or test execution is asserted.  
**Release posture:** **OPEN / NOT YET RECOMMENDED** pending A6 testing, independent review, residual-risk decisions and exact final baseline.  
**Historical context:** [Victor's A5 conditional classroom decision](../release/a5-release-decision.md) supersedes some earlier *historical* pending language in this file. That decision did **not** grant production/security/legal clearance or independently verify all final integration/recovery conditions.

## Classification and disposition rules

- **Confirmed application defect:** reproducible deviation from a specified expected behavior with environment, SHA, actual/expected result and regression proof.
- **Corrected evidence/documentation finding:** discrepancy addressed in reviewed documentation; does not prove new runtime behavior.
- **Residual limitation:** known bounded feature/operational constraint; severity is potential impact outside documented scope, **not** proof of a current bug.
- **Release-assurance gap:** required verification, human approval or operational artifact not yet established at the final A6 SHA.
- **Status:** “historically corrected,” “open,” or “deferred/conditional in A5” describes the evidence record, not automatic A6 closure.

## Carry-forward finding review

| ID | Type / severity | A6 initial disposition and evidence boundary | Next evidence / action | Coordinator / decision owner |
|---|---|---|---|---|
| A5-F01 | Documentation correction; High potential data loss | **Historical documentation corrected** by [#81](https://github.com/victordodan/-comp330-f26-team-02/pull/81) and [#83](https://github.com/victordodan/-comp330-f26-team-02/pull/83); file-backed H2/JDBC is documented. **Recovery assurance remains open** (LIM-001). | Run and record A6 same-store restart plus separately document any real backup/restore test; never equate restart with recovery. | Shu (technical); Mareddy (testing); Victor (risk decision) |
| A5-F02 | Identity documentation correction; High outside demo | **Historical documentation corrected** in #83. Session checks reject forged role headers, but demo identity selection is not real authentication (LIM-002). | Final-SHA negative authorization evidence and clear loopback/synthetic-only deployment boundary; record accepted/deferred identity risk. | Shu / Mareddy; Victor |
| A5-F03 | Test-evidence accuracy; Medium | **Historical concern resolved as unsupported**: [#84](https://github.com/victordodan/-comp330-f26-team-02/pull/84) was import-only, not deletion of four controller tests. Historical 35-test CI is not new A6 validation. | Verify final A6 tests/CI and record actual counts and run URL; do not reopen an unproven compilation defect. | Giancarlo / Mareddy |
| A5-F04 | Acceptance governance; High | **A5 conditional classroom acceptance recorded** in [A5 release decision](../release/a5-release-decision.md), with independent final integrated review deferred. [Issue #104](https://github.com/victordodan/-comp330-f26-team-02/issues/104) still tracks human case dispositions. This does not constitute A6 final acceptance. | Reconcile TC-001–TC-012 at exact A6 SHA with execution/mapping evidence and attributable human disposition. | Mareddy; Victor (acceptance authority); Giancarlo (traceability) |
| A5-F05 | Release baseline and CI; High | A5 conditional decision exists; historical CI and release records must be checked against the actual A5 tag and later A6 candidate. [Issue #105](https://github.com/victordodan/-comp330-f26-team-02/issues/105) remains a follow-up. | Verify final A6 commit, passing relevant CI, independent non-author review, authorized decision and pushed \`a6-final-release\` tag. | Victor; independent reviewer; Giancarlo tracks |
| A5-F06 | Dependency assurance; High outside bounded demo | **Residual supply-chain/legal assurance gap**; historical inventory is not comprehensive clearance. A5 decision defers/accepts bounded risks but does not provide production clearance. | Dated dependency/advisory/license review or explicit named residual-risk disposition for final A6 baseline. | Victor-appointed technical reviewer / Victor |

## Residual limitations (not automatically defects)

| ID | Severity / impact boundary | A6 baseline status | Required evidence or honest deferral |
|---|---|---|---|
| LIM-001 | **High** — loss of local H2 files may lose data | **Open operational risk**; restart retention is not backup/restore | Runbook with verified start/stop/path and recovery guidance; actual restoration test if claimed; owner Shu |
| LIM-002 | **High outside local demo** — synthetic identity selector permits reviewer selection | **Deferred / restricted classroom boundary** | Synthetic-only, loopback-only scope; final authorization tests; Victor owns any broader deployment decision |
| LIM-003 | **Low in scoped demo** — no outbound notifications | **Known feature limitation** | Document polling/manual status retrieval; do not claim notification implementation |
| LIM-004 | **Medium** — HTTP API only, no verified GUI | **Known usability limitation** | Demo actual HTTP endpoints; GUI requires separate evidence if added |
| LIM-005 | **High outside local demo** — production monitoring, deployment and rollback/recovery unverified | **Open operational maturity gap** | Actual runbook, observability/failure evidence and residual-risk ownership; no production-ready claim |

See [known limitations](../release/known-limitations.md), [risk register](../planning/risk-register.md), and [A5 defect log](defect-log.md). [Issue #107](https://github.com/victordodan/-comp330-f26-team-02/issues/107) tracks unfinished operations/observability/security starter-template replacement and should remain open until the actual files are project-specific and reviewed.

## A6 quality gate — initial check

| Gate | Initial status | Closure requirement |
|---|---|---|
| Final defects and regression evidence | **OPEN** | Final test runs at identified A6 SHA, severity and dispositions for any reproduced defects |
| TC-001–TC-012 and traceability | **OPEN** | Case-by-case evidence/authorized decision aligned with final baseline |
| CI/build and workflow controls | **OPEN** | Successful relevant workflow at final commit; explain skipped or failed checks |
| Operations and observability | **OPEN** | Reviewed project-specific runbook, logs/failure signals and recovery assumptions; runtime evidence for claimed controls |
| Security and supply chain | **OPEN** | Governance review, actual dependency evidence or approved deferrals, named owners |
| Independent review and release decision | **OPEN** | Non-author review, Victor's explicit GO / CONDITIONAL GO / NO-GO with residual risks |
| Final baseline | **OPEN** | \`a6-final-release\` tag pushed at the exact approved submission commit |

## Required defect-record format for newly discovered A6 bugs

Record: ID; expected vs actual behavior; reproduction steps; severity and user impact; environment and exact commit SHA; owner; issue/PR; affected requirement/TC; fix or mitigation; regression test/run; independent reviewer; human disposition and date. Do not label missing evidence as a reproduced runtime defect.

## Review and AI accountability

This initial A6 addendum was drafted with AI assistance from prior repository evidence and must receive **independent human review** before it is treated as approved. It did not execute tests, inspect runtime logs, run security scans, approve residual risk, or authorize release. The contributor responsible for the A6 AI-use log should record this assistance and its verification before the final release.

**Next action:** Use [Issue #111](https://github.com/victordodan/-comp330-f26-team-02/issues/111) to link final A6 test/CI results, defect dispositions, reviewer comments and the exact release SHA. Do not merge solely to mark a gate complete.
