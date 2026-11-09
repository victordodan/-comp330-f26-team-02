# CampusConnect — A5 Defect and Finding Log

**Owner:** Giancarlo Herrera, Quality & Review Lead  
**Reviewed:** 2026-11-09  
**Basis:** A4 repository evidence and open PRs #83–#90. This is an evidence review, not a fresh test run.  
**Release baseline:** A5 final SHA not frozen.

## Classification and severity

- **Confirmed defect:** reproducible observed failure against a documented expectation, with revision and reproduction evidence.
- **Documentation discrepancy:** contradictory or outdated descriptions; does not by itself establish an application defect.
- **Verification gap:** missing required test, CI, risk, or approval evidence; does not by itself establish an application defect.
- **Severity:** Critical = release-blocking safety/data integrity exposure; High = material workflow or release-assurance risk; Medium = misleading documentation or incomplete traceability; Low = minor editorial problem. Severity is *proposed* pending team review.

**No new application defect has been independently reproduced during this A5 documentation audit.** Do not interpret the following findings as proven runtime defects.

| ID | Type / proposed severity | Evidence and observed issue | Status | Coordinator / next action | Closure evidence |
|---|---|---|---|---|---|
| A5-F01 | Documentation discrepancy / High | [PR #83](https://github.com/victordodan/-comp330-f26-team-02/pull/83) describes in-memory storage lost on restart; `application.properties` configures file-backed H2 and [A4 backend verification](../testing/a4-backend-verification.md) reports restart persistence. | Open, unverified on final A5 SHA | Shu verify effective persistence; Mareddy run restart test; Victor decide corrected wording | Final-SHA restart log, updated known limitations |
| A5-F02 | Documentation discrepancy / High | PR #83 describes forged `X-User-ID`/`X-User-Role` identity; [A4 HTTP API](../testing/a4-http-api.md) documents server-session demo identity and rejected forged headers. This is not proof of production authentication. | Open, unverified on final A5 SHA | Shu inspect controller/session; Mareddy run negative authorization tests | Final-SHA test evidence and corrected limitation |
| A5-F03 | Evidence/defect register gap / Medium | Existing defect log and review remained starter templates; [PR #84](https://github.com/victordodan/-comp330-f26-team-02/pull/84) reports legacy test cleanup without a linked fresh run in reviewed material. | Open | Mareddy/developer verify #84; Giancarlo record observed disposition | CI/build result and accepted defect disposition |
| A5-F04 | Acceptance evidence gap / High | [Test catalog](../testing/test-cases.md) lists TC-001–TC-012 as Not Run individually; [A4 quality report](../testing/a4-final-quality-verification.md) maps automated coverage and historical 35 passing tests. | Open | Mareddy execute/document each case; Giancarlo reconcile | Dated TC results, operator, environment, exact SHA |
| A5-F05 | Release/CI evidence gap / High | A5 release SHA/tag not frozen. `.github/workflows/ci.yml` is still an intentionally failing Starter Kit guard; historical A4 CI is not final-A5 evidence. | Open | Shu/developer establish real CI; Victor release owner | Passing final-SHA CI, approved tag and gate decision |
| A5-F06 | Supply-chain assurance gap / High | [A4 dependency assessment](../reviews/a4-build-dependency-assessment.md) does not establish a current CVE scan or complete transitive-license signoff. | Open | Victor appoint technical reviewer; Giancarlo track disposition | Scan output, license review, severity/risk acceptance |

## Confirmed defects

**None newly verified by this review.** Add a confirmed defect only with reproducible observed behavior, expected behavior, environment/SHA, severity, owner, issue/PR, fix, retest, and independent closure decision.

## Disposition procedure

1. The technical owner verifies or rejects each finding against the **exact final integrated SHA**.
2. For actual defects, record steps to reproduce, expected/actual result, severity, affected requirements, issue link, mitigation, owner, fix and retest.
3. For documentation-only discrepancies, correct the authoritative document and link the reviewed PR.
4. For gaps, link new test/CI/security evidence or record an explicitly approved deferral.
5. Victor records risk acceptance; Giancarlo provides quality recommendation but does not approve his own changes.

See [A5 detailed reconciliation](a5-defect-limitations-reconciliation.md) in [PR #87](https://github.com/victordodan/-comp330-f26-team-02/pull/87) (not merged as of drafting).
