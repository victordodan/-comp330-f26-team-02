# Known Limitations — CampusConnect (Cycle 1 Release)

> **Team:** Team 2 — Codex Ramblers
> **Release Baseline Tag:** `a5-cycle1-release`
> **Date:** 2026-11-09
> **Owner:** Shú Perez (Architecture) · **Backup:** Giancarlo Herrera

This document records the architectural and functional known limitations, technical debt, and temporary trade-offs present in the Cycle 1 release of CampusConnect. A known limitation is not necessarily a defect — it is intentionally deferred functionality, a supported behavior with a known restriction, or an operational/verification constraint that reviewers and users should not have to discover by accident.

---

## Current Limitations

| ID | Limitation | Impact | Affected Area | Workaround | Disposition | Related Evidence |
|---|---|---|---|---|---|---|
| **LIM-001** | Support request data is held in a `ConcurrentHashMap<String, SupportRequest>` in memory rather than a persistent relational database. | All request data resets when the Spring Boot application stops or restarts. | Architecture / Operations (persistence) | Re-create synthetic test requests after application restart; automated tests seed required state per execution context. | Planned Fix (Cycle 2: H2 file storage + JPA persistence) | ADR-0005; R-010 (persistence) |
| **LIM-002** | Role-based access control relies on HTTP request headers (`X-User-ID`, `X-User-Role`) that are unauthenticated synthetic simulation headers. | Headers can be forged; no real identity verification. | Security / API | Use headers strictly in local execution and automated test suites; controller returns `403 Forbidden` when a non-reviewer attempts status triage. | Deferred (OAuth2 / SSO integration deferred to future releases) | ADR-0005; AR-001 (demo impersonation); Q-004 |
| **LIM-003** | Status changes do not send automated email or push notifications to students. | Requesters are not proactively informed of status changes. | Capability / Operations | Requester views current status by querying `GET /api/requests`. | Planned Improvement (Cycle 2: asynchronous event notification service) | REQ-002; REQ-004 |

---

## Deferred Capabilities

| Capability | Reason Deferred | Impact on Current Release | Future Reference |
|---|---|---|---|
| File-backed H2 relational persistence with JPA/Repository layers | Cycle 1 velocity and CI reliability prioritized; setup overhead and environmental fragility in GitHub Actions | No permanent data retention; see LIM-001 | ADR-0005; Cycle 2 (Maturity Phase) |
| Full OAuth2 / SSO authentication and real identity verification | Not required for controlled Cycle 1 vertical slice; synthetic header simulation sufficient for local testing | No real authentication; see LIM-002 | ADR-0005; `assumptions-open-questions.md` Q-004 |
| Asynchronous email / push notification delivery | Lower priority than core workflow verification for Cycle 1 | No outbound notifications; see LIM-003 | Cycle 2 |

---

## Unresolved Defects Affecting the Release

| Defect ID | Impact | Severity | Current Disposition |
|---|---|---|---|
| None identified | No unresolved defects materially affect the Cycle 1 release baseline (`a5-cycle1-release`). | — | — |

---

## Operational Limitations

| Limitation | Operational Impact | Related Evidence |
|---|---|---|
| Manual local execution only (`mvn spring-boot:run`) — no automated deployment pipeline or rollback | Deployment and recovery are manual; no automated rollback path | `/docs/operations/runbook.md` |
| Volatile storage across process restarts | Any local test state must be re-seeded after restart | ADR-0005; LIM-001 |
| Synthetic header–based identity | No real session/token audit trail; role simulation is per-request | LIM-002 |

---

## Verification Limitations

| Limitation / Gap | What Has Been Verified | What Has Not Been Verified | Related Evidence |
|---|---|---|---|
| Performance under sustained load | Functional tests pass in `mvn -B clean test`; clean isolated build under 2 seconds in GitHub Actions CI | Load / stress behavior under sustained concurrent traffic | ADR-0005 (CI evidence) |
| Recovery behavior | Volatility documented; restart reset behavior observed | Formal recovery procedures and tested backup/restore | ADR-0005; Risk 1 (Section 11 of Architecture Overview) |
| Authentication boundary | Header presence checked; `403 Forbidden` enforced for non-reviewer status updates | Real identity verification (no OAuth/SSO in Cycle 1) | LIM-002; `RequestControllerTest.java` |
| Notification delivery | Not applicable — no notification subsystem exists in Cycle 1 | End-to-end delivery verification | LIM-003 |

---

## Limitation Changes

| Date / Release | Limitation ID | Change | Reason | Evidence |
|---|---|---|---|---|
| 2026-11-09 (`a5-cycle1-release`) | LIM-001, LIM-002, LIM-003 | Initial baseline established for Cycle 1 release | First documented release baseline | `a5-cycle1-release` tag; this document |

---

## No Known Limitation Claims

No claim of "no known limitations" is made for the Cycle 1 release. The limitations above reflect a deliberate review of: scope (Cycle 1 vertical slice per brief §4), defects (none material), verification gaps (performance, recovery, real authentication), operational constraints (manual execution, volatile storage), risks (R-010 persistence, AR-001 impersonation, risk register Section 11), and external dependencies (none — no OAuth/SSO or runtime AI integration in Cycle 1). Review basis: Architecture Overview §11 (Known Architecture Risks), ADR-0005, and `/docs/planning/risk-register.md`.
