# Known Limitations — CampusConnect Cycle 1 Candidate

**Owner:** Shú Perez (Architecture) · **Backup:** Giancarlo Herrera
**Review baseline:** Integrated repository checkpoint `b899a1b40b9d0539ae6152ee2ad874338533673a`
**A5 baseline:** Not frozen; `a5-cycle1-release` is the intended tag, not an established release baseline.
**Status:** Proposed technical clarification for PR #94; final human review and risk acceptance pending.

This record describes implementation boundaries and verification gaps. It does
not certify zero defects, final acceptance or production readiness. Owners below
coordinate follow-up; dispositions do not imply their approval.

## Implementation facts

The application uses a JDBC repository and defaults to file-backed H2 at
`jdbc:h2:file:./data/campusconnect`. Requests are not stored in a
`ConcurrentHashMap`; IDs are UUIDs. Restarting with the same store is intended
to retain records. See [runtime configuration](../../src/main/resources/application.properties),
[repository](../../src/main/java/edu/loyola/campusconnect/repository/JdbcRequestRepository.java),
and [ADR-004](../decisions/ADR-004-local-persistence-and-recovery.md).

The demo selector validates a fixed identity and stores its ID in the server
session. `X-User-ID`, `X-User-Role` and client-supplied ownership fields do not
grant permissions. This is still a simulation: anyone able to reach the demo
selector can choose the fixed Reviewer identity. See the
[controller](../../src/main/java/edu/loyola/campusconnect/controller/RequestController.java)
and [HTTP API guide](../testing/a4-http-api.md).

## Current limitations and residual risks

Severity reflects impact if the stated boundary is exceeded, not a confirmed
runtime defect. Priorities and future work require team disposition.

| ID | Limitation and impact | Severity | Mitigation / workaround | Follow-up owner | Disposition / next action | Evidence |
|---|---|---|---|---|---|---|
| LIM-001 | Local H2 files depend on the configured path and working directory. Loss of the database file can lose records; restart retention is not backup recovery. | High data-loss risk | Reuse the same store; use synthetic data and preserve local files. No verified backup/restore workaround is claimed. | Shú; testing owner | Recovery verification pending; propose operational work during Cycle 2 planning. | ADR-004; [backend verification](../testing/a4-backend-verification.md) |
| LIM-002 | Fixed session-based demo identities provide no real identity verification. A caller can deliberately select Reviewer; session role checks do not protect real student accounts. | High outside local demo | Use synthetic records and loopback-only demonstration. Do not expose as a real campus service. | Shú; Victor for risk acceptance | Production authentication out of current scope; bounded-demo risk acceptance pending. | [HTTP API](../testing/a4-http-api.md); [scope](../planning/scope.md) |
| LIM-003 | No automated email/push notification delivery. Students must retrieve current status and notes. | Low within agreed scope | Query the student endpoints with the selected student's session. | Architecture / requirements owners | Notifications out of Cycle 1 scope; future work requires prioritization, not a promised Cycle 2 service. | [Scope](../planning/scope.md) |
| LIM-004 | Implemented interface is an HTTP API; browser forms and a graphical workflow are not verified release capabilities. | Medium demo/usability limit | Demonstrate the documented HTTP workflow. | Shú; Victor for presentation | Disclose demo interface; any UI work needs separate implementation and evidence. | [HTTP API](../testing/a4-http-api.md) |
| LIM-005 | No verified production deployment, automated deployment/rollback or complete operational monitoring/recovery package is established by the reviewed evidence. | High outside local demo | Run locally with synthetic data; do not claim production operational readiness. | Architecture / release owners | Operational readiness remains unverified; prioritize follow-up separately. | [Runtime configuration](../../src/main/resources/application.properties); ADR-004 |

## Detailed technical boundaries

### LIM-001 — Local database retention and recovery

`WorkflowService` delegates to `JdbcRequestRepository` inside Spring transactions.
The repository uses parameterized JDBC statements; updates compare the expected
record version. IDs are generated with `UUID.randomUUID()`. The default H2 URL
is relative to the process working directory. Starting from a different directory
or overriding the datasource can select a different store; this is distinct from
losing records during an ordinary restart against the same store. Persisted
records retain their owner, but the demo user must be selected again in a new
session. Restart/concurrency tests do not prove backup restoration or safe
multi-instance deployment. H2/JDBC already exists; no JPA migration is required
or approved by this document.

### LIM-002 — Session authority and deliberate role selection

`POST /api/demo/identity` accepts one of `student-a`, `student-b`, or `reviewer`
and records it in the server session. Services enforce role/ownership checks;
client-supplied role headers and ownership fields cannot elevate authority.
However, the selector intentionally lets a caller choose Reviewer. This is the
actual boundary preventing use as institutional authentication. The default
server binds to `127.0.0.1`; runtime configuration can override defaults, so the
operator must preserve the local synthetic-data boundary.

### LIM-003 — Requester notification

There is no notification-delivery subsystem. Students retrieve current status
and notes through their session's own-request endpoints. Notifications remain
outside Cycle 1 scope; their absence is not a failed delivery test or an approved
commitment to a Cycle 2 notification service.

## Verification and release gaps

| Gap | Evidence available | Remaining evidence / disposition | Coordinator |
|---|---|---|---|
| Final A5 revision and independent review | Reviewed main checkpoint with merged #81/#83/#84/#87/#91/#92 | Integrate reviewed changes; identify exact candidate SHA, record non-author review and release decision, then establish the tag. | Victor; independent reviewer |
| Acceptance dispositions | Test catalog and mapped automated coverage | TC-001–TC-012 require actual case-specific evidence or explicit blocked/deferred dispositions; do not count mappings as separate acceptance executions. | Testing owner; Giancarlo |
| Final CI | Historical A4 evidence; PR #84 run #41 reports 35 passing tests | Obtain CI for the final integrated A5 revision. PR #84 evidence does not certify that future revision. | Development / testing owners |
| Dependencies | A4 dependency inventory and partial license assessment | Dated advisory, transitive-license and maintenance review; record findings, mitigations and human risk decisions. | Victor-appointed technical reviewer |
| Recovery and load | Service restart/concurrency tests and historical packaged restart evidence | Backup restoration, sustained load and production capacity remain unverified. | Architecture / testing owners |

References: [test catalog](../testing/test-cases.md),
[A4 final verification](../testing/a4-final-quality-verification.md),
[A4 backend verification](../testing/a4-backend-verification.md),
[dependency assessment](../reviews/a4-build-dependency-assessment.md), and
[PR #84 CI run #41](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42613709038).
That run tested merge SHA `0fcc79c29dfcdc89403d3c230f47438cb00c358c`,
not a final A5 release. This correction performs no new tests or scans.

## Defect disposition

No claim that there are no material unresolved defects is supported by this
record. Consult the [defect log](../quality/defect-log.md),
[defect review](../quality/defect-review.md), and
[risk register](../planning/risk-register.md). Template entries or missing
execution records are not evidence of defect absence. Confirmed defects need
reproduction, severity, owner, mitigation, retest and final disposition.

## Correction history

The merged PR #83 correction removed incorrect in-memory storage and
header-based identity claims, the unsupported sub-two-second build claim,
premature A5 baseline/defect-clearance statements, and unapproved Cycle 2
commitments. PR #94 preserves that correction and adds source-backed technical
disclosures for LIM-001 through LIM-003. Final human acceptance remains pending.

**AI assistance:** Codex compared PR #94 against corrected main, source, configuration,
tests and repository evidence and drafted this correction. This edit does not
constitute a runtime verification, security clearance or release approval.
