# A5 Defect and Known-Limitations Reconciliation — Review Findings

**Project:** CampusConnect — Team 2 (Codex Ramblers)  
**Prepared by:** Giancarlo Herrera, Quality & Review Lead  
**Review date:** 2026-11-09  
**Status:** Proposed findings; pending independent technical verification and team disposition  
**Basis:** Repository main at A4 baseline `70d3726b64c0deafef2d770870bb14ef8f28b79b`, open PRs #83, #84, #85, #86, and current defect/risk records. This is a documentation review, **not** a test run or release approval.

## Evidence reconciliation

| Finding | Observed conflicting evidence | Required action | Proposed coordinator | Closure evidence / status |
|---|---|---|---|---|
| A5-F01 — Persistence and restart | [PR #83](https://github.com/victordodan/-comp330-f26-team-02/pull/83) describes `ConcurrentHashMap` and loss on restart (LIM-001); [PR #86](https://github.com/victordodan/-comp330-f26-team-02/pull/86) describes file-backed H2 and restart persistence. A3 ADR-002 and R-010 also assume H2. | Inspect the exact final application's repository/service wiring and startup configuration; execute a restart persistence check at the release SHA. Correct the applicable ADRs, limitations, risk register and release notes to one verified implementation. | Shu (architecture), Mareddy (tests), Victor (decision) | **Open — conflicting documentation; no fresh A5 execution reviewed here.** |
| A5-F02 — Identity/authorization boundary | PR #83 LIM-002 says callers can forge `X-User-ID` / `X-User-Role`; PR #86 describes server-enforced demo identity/ownership and no production authentication. | Review the deployed controller/session code and execute negative tests for forged headers, Student A/B isolation and reviewer-only operations. Separate the known lack of production authentication from any demonstrated privilege bypass. | Shu (implementation), Mareddy (tests), Giancarlo (review) | **Open — do not assert either a confirmed exploit or verified security until code and tests are reconciled.** |
| A5-F03 — Unresolved defects claim | PR #83 states no material unresolved defects; [defect-log.md](../quality/defect-log.md) remains a blank starter template. [PR #84](https://github.com/victordodan/-comp330-f26-team-02/pull/84) reports four legacy tests that did not compile, with test execution pending. | Record confirmed defects and their evidence in the authoritative log; distinguish corrected/verified, open, deferred, and not-a-defect. Obtain a successful A5 build/test run for PR #84 before closing its finding. | Victor (triage/approval), Mareddy (test evidence), Giancarlo (reconciliation) | **Open — blank log is not proof of zero defects.** |
| A5-F04 — Acceptance/CI evidence | PRs #85 and #86 distinguish historical A4 automated coverage from final A5 acceptance dispositions. PR #84 reports pending compilation/CI verification. | Reconcile TC-001–TC-012 individually as pass/fail/blocked/deferred with test links, date, reviewer and exact SHA; link clean A5 CI and identify any gaps. | Mareddy (execution), Giancarlo (quality check), Victor (acceptance) | **Open — A4 evidence cannot alone approve the final A5 revision.** |
| A5-F05 — Release metadata and approvals | PR #83 calls `a5-cycle1-release` a release baseline; PR #86 says the A5 tag and approval are pending. | Confirm whether the tag exists and what SHA it identifies. Freeze the actual revision only after integrating reviewed changes; record independent reviewer and Go/Conditional Go/No-Go decision. | Victor (release owner), Giancarlo (quality gate) | **Open — release approval not established by these PRs.** |
| A5-F06 — Dependency/security review | PRs #85 and #86 explicitly leave dependency advisory/CVE, transitive license and human acceptance checks pending. | Capture dependency inventory, advisory results, license review, exceptions and named human risk acceptance for exact release SHA. | Victor (risk acceptance), designated technical reviewer (verification) | **Open — no completed scan claimed.** |

## Defect disposition protocol

The findings above are **review findings**, not automatically assigned `DEF-###` IDs. Create a defect record only when a reviewer confirms an observed implementation/test failure against an expected requirement. For each confirmed defect, capture reproduction, affected REQ/AC, severity, owner, fix PR, exact-SHA retest and final disposition. A documentation inconsistency may instead be tracked as an evidence/architecture finding.

Do **not** close the reported PR #84 test-compilation defect merely because tests were deleted: require a passing build and confirm the retained tests still exercise the expected cases. Do not treat a proposed Cycle 2 deferral as accepted until the release owner records the residual risk.

## Coordination before merge

1. Shu: reconcile LIM-001/LIM-002 and the architecture/persistence claims in PR #83 with actual code.
2. Mareddy: provide exact-revision test, restart, concurrency and acceptance-case evidence.
3. Victor: decide whether any remaining issue blocks release; record risk acceptance and independent review.
4. Giancarlo: update the release-note claims in PR #86 and quality gate in PR #85 after the authoritative evidence is available.

**Provisional quality recommendation:** **No final Go decision yet**. These are open evidence discrepancies, not proof that the application is defective. Reassess once the exact revision, test results, limitation descriptions and reviewer decisions agree.

## References

- [PR #83 — known limitations](https://github.com/victordodan/-comp330-f26-team-02/pull/83)
- [PR #84 — test cleanup](https://github.com/victordodan/-comp330-f26-team-02/pull/84)
- [PR #85 — quality gate](https://github.com/victordodan/-comp330-f26-team-02/pull/85)
- [PR #86 — release notes](https://github.com/victordodan/-comp330-f26-team-02/pull/86)
- [Defect log](../quality/defect-log.md)
- [Risk register](../planning/risk-register.md)

**AI use:** AI assisted with evidence comparison and drafting. A human reviewer must verify each finding against the actual release code and record applicable use in the team AI-use log.
