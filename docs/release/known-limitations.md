# Known Limitations — CampusConnect (Cycle 1 Release)
> **Team:** Team 2 — Codex Ramblers  
> **Release Baseline Tag:** `a5-cycle1-release`  
> **Date:** 2026-11-09  

This document records the architectural and functional known limitations, technical debt, and temporary trade-offs present in the Cycle 1 release of CampusConnect. A known limitation is not necessarily a defect — it is intentionally deferred functionality, a supported behavior with a known restriction, or an operational/verification constraint that reviewers and users should not have to discover by accident.

---

## 1. Summary of Current Limitations

| ID | Limitation | Impact | Affected Area | Workaround | Disposition | Related Evidence |
|---|---|---|---|---|---|---|
| **LIM-001** | Support request data is held in an in-memory `ConcurrentHashMap<String, SupportRequest>` managed by `WorkflowService` with sequence `REQ-1001` (**ADR-0005**). | All stored request data resets when the Spring Boot application stops or restarts. | Architecture / Operations (persistence) | Re-create synthetic test requests after application restart; automated tests seed required state per execution context. | Planned Fix (Cycle 2: Full migration to file-backed H2 database + JPA repositories) | ADR-0005; R-010 (persistence) |
| **LIM-002** | Role-based access control relies on synthetic, unauthenticated HTTP request headers (`X-User-ID`, `X-User-Role`) handled by `RequestController`. | Headers can be forged; no identity provider or token validation exists. | Security / API | Use headers strictly for local role simulation and automated API testing; controller returns `403 Forbidden` when a non-reviewer attempts status triage. | Deferred (Production OAuth2 / SSO integration deferred to future release cycles) | ADR-0005; AR-001 (demo impersonation); Q-004 |
| **LIM-003** | Status changes do not send automated email or push notifications to students. | Requesters are not proactively informed of status changes. | Capability / Operations | Requester views current status by querying `GET /api/requests`. | Planned Improvement (Cycle 2: asynchronous event notification service) | REQ-002; REQ-004 |

---

## 2. Detailed Technical Disclosures

### LIM-001: Database & Persistence
* **Current Implementation**: Support requests reside in an in-memory `ConcurrentHashMap<String, SupportRequest>` managed by `WorkflowService` with sequence `REQ-1001` (**ADR-0005**).
* **Impact**: All stored request data resets when the Spring Boot application restarts.
* **Justification**: Accepted Cycle 1 trade-off to enable zero-configuration, deterministic CI builds (`mvn clean test` executing in under 2 seconds).
* **Cycle 2 Mitigation**: Full migration to a file-backed H2 relational database using Spring Data JPA repositories.

### LIM-002: Authentication & Security
* **Current Implementation**: Server-side role boundaries rely on synthetic, unauthenticated HTTP request headers (`X-User-ID`, `X-User-Role`) handled by `RequestController`.
* **Impact**: Headers are intended for local role simulation and automated API testing; no identity provider or token validation exists.
* **Justification**: Live institutional SSO/OAuth2 integration is explicitly out of scope for Cycle 1 (CampusConnect Brief §11).
* **Cycle 2 Mitigation**: Production OAuth2 / SSO integration is deferred to future release cycles.

---

## 3. Deferred Capabilities

| Capability | Reason Deferred | Impact on Current Release | Future Reference |
|---|---|---|---|
| File-backed H2 relational persistence with JPA/Repository layers | Cycle 1 velocity and CI reliability prioritized; setup overhead and environmental fragility in GitHub Actions | No permanent data retention; see LIM-001 | ADR-0005; Cycle 2 (Maturity Phase) |
| Full OAuth2 / SSO authentication and real identity verification | Not required for controlled Cycle 1 vertical slice; synthetic header simulation sufficient for local testing | No real authentication; see LIM-002 | ADR-0005; `assumptions-open-questions.md` Q-004 |
| Asynchronous email / push notification delivery | Lower priority than core workflow verification for Cycle 1 | No outbound notifications; see LIM-003 | Cycle 2 |

---

## 4. Unresolved Defects Affecting the Release

| Defect ID | Impact | Severity | Current Disposition |
|---|---|---|---|
| None identified | No unresolved defects materially affect the Cycle 1 release baseline (`a5-cycle1-release`). | — | — |

---

## 5. Operational Limitations

| Limitation | Operational Impact | Related Evidence |
|---|---|---|
| Volatile storage across process restarts | Any local test state must be re-seeded after restart | ADR-0005; LIM-001 |
| Synthetic header–based identity | No real session/token audit trail; role simulation is per-request | LIM-002 |

---

## 6. Verification Limitations

| Limitation / Gap | What Has Been Verified | What Has Not Been Verified | Related Evidence |
|---|---|---|---|
| Performance under sustained load | Functional tests pass in `mvn -B clean test`; clean isolated build under 2 seconds in GitHub Actions CI | Load / stress behavior under sustained concurrent traffic | ADR-0005 (CI evidence) |
| Authentication boundary | Header presence checked; `403 Forbidden` enforced for non-reviewer status updates | Real identity verification (no OAuth/SSO in Cycle 1) | LIM-002; `RequestControllerTest.java` |
| Notification delivery | Not applicable — no notification subsystem exists in Cycle 1 | End-to-end delivery verification | LIM-003 |

---

## 7. Limitation Changes

| Date / Release | Limitation ID | Change | Reason | Evidence |
|---|---|---|---|---|
| 2026-11-09 (`a5-cycle1-release`) | LIM-001, LIM-002, LIM-003 | Initial baseline established for Cycle 1 release | First documented release baseline | `a5-cycle1-release` tag; this document |

---

## 8. No Known Limitation Claims

No claim of "no known limitations" is made for the Cycle 1 release. The limitations above reflect a deliberate review of: scope (Cycle 1 vertical slice per brief §4), defects (none material), verification gaps (performance, recovery, real authentication), operational constraints (manual execution, volatile storage), risks (R-010 persistence, AR-001 impersonation, risk register Section 11), and external dependencies (none — no OAuth/SSO or runtime AI integration in Cycle 1). Review basis: Architecture Overview §11 (Known Architecture Risks), ADR-0005, and `/docs/planning/risk-register.md`.
