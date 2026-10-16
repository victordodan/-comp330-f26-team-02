# Data and Context Ownership — CampusConnect

> **Status:** Draft for Cycle 1 (A3 architecture phase gate)
> **Owner:** Shú Perez
> **Backup:** Giancarlo Herrera
> **Last updated:** 2026-10-15
> **Related:** `architecture.md`, `component-responsibilities.md`, `api-contracts.md`, ADR-001

This document identifies the authoritative data in CampusConnect, the owners of
that data, who can see it, what context AI or tools may access, and what
logging is expected. It supports the architecture overview (`architecture.md`)
and the API/interface contracts (`api-contracts.md`).

The CampusConnect project brief (§6) and the team's own planning evidence
constrain this document:

- **Synthetic data only.** No real student records, grades, private university
  data, or regulated records (`scope.md`, Out of Scope).
- **No live institutional integration.** External services require instructor
  approval and a documented risk review (`scope.md`, Out of Scope).
- **Real authentication is not required in Cycle 1.** Roles are simulated
  (`scope.md`, Out of Scope).
- **AI must be bounded, reviewable, and human-controlled.** User-facing AI is
  deferred to Cycle 2 (`scope.md`, Deferred Scope).

---

## 1. Data Ownership Summary

| Data / State | Authoritative Owner | Persistence | Visibility | Notes |
|---|---|---|---|---|
| Workflow request (id, requestType, description, status, resolution note) | Application Service | Database via Persistence Layer | Owning requester and reviewers | Created via API-001 (REQ-001); read via API-002 (REQ-002) |
| Request status | Application Service | Database via Persistence Layer | Owning requester and reviewers | Updated by reviewer (REQ-005) |
| Reviewer resolution note | Application Service | Database via Persistence Layer | Owning requester and reviewers | Recorded by reviewer (REQ-006) |
| User identity | Simulated (see Section 2) | Seeded configuration | Consumed by Application Service; not owned by CampusConnect | Real authentication is out of scope (`scope.md`) |
| Role assignments (student / reviewer / administrator) | Application Service | Database / seeded configuration | System; administrators | Seeded synthetic users in Cycle 1 (`scope.md`) |
| AI suggestion context (if user-facing AI is added in Cycle 2) | Application Service | Not persisted beyond the suggestion | AI boundary only | Deferred to Cycle 2 (`scope.md`) |
| Operational logs / audit events | Application Service | Persistence Layer | Operations / review workflow | See Section 5 |

**Authoritative data rule:** the Application Service is the single authority for
workflow state. The Web Client never holds authoritative state. The Persistence
Layer stores but does not interpret workflow meaning. Identity is simulated in
Cycle 1 and is consumed by the Application Service but not owned by it.

---

## 2. Identity and Role Simulation

Real authentication is **not required in Cycle 1** (`scope.md`, Out of Scope).
The team uses **simulated users and roles** to distinguish student requester
behavior from support reviewer behavior (`requirements.md`, Cycle 1 Constraints).

| Concern | Cycle 1 rule |
|---|---|
| Real Loyola authentication / SSO | Out of scope |
| Simulated roles | Required — student and reviewer behavior must remain distinguishable even when authentication is simulated (`requirements.md`) |
| Where simulated users are defined | Seeded configuration / seed data owned by the Application Service |
| How API-003 is treated in Cycle 1 | The contract exists; the provider is a simulated identity source rather than a live institutional service |

**Note:** `api-contracts.md` API-003 describes obtaining a trusted identity from
an Identity Provider. In Cycle 1, that provider is simulated. If the team later
integrates a real provider, this section must be updated and the change
documented through a new or superseding ADR.

---

## 3. Sensitive and Protected Data

CampusConnect uses **synthetic data only** in Cycle 1 (`requirements.md`,
`scope.md`). No real student records, grades, private university data, or
regulated records may be stored, processed, or shared with AI tools.

| Concern | Rule |
|---|---|
| Real student records | Prohibited |
| Grades or academic records | Prohibited |
| Regulated data (FERPA, HIPAA, etc.) | Prohibited |
| Credentials, tokens, private keys | Never enter the repository or AI tools |
| Confidential project information | Not shared with AI tools |
| Private personal information | Not shared with AI tools |

These rules are also stated in the team's AI Use Policy
(`/docs/ai/ai-policy.md`, §"Sensitive and Protected Information").

---

## 4. Context Visibility and Boundaries

| Context | Who may see it | Who may not see it | Rule |
|---|---|---|---|
| A requester's own request | The owning requester; reviewers | Other requesters | Requesters cannot read other users' requests even if they know the ID (`api-contracts.md` API-002) |
| All requests | Reviewers | Requesters | Reviewers view requests to triage them (REQ-004) |
| User identity | Application Service; the authenticated (simulated) user | Other users | Identity is consumed, not exposed broadly |
| Role assignments | System; administrators | Requesters; reviewers (except their own role) | Managed by Application Service |
| AI context (if added in Cycle 2) | AI tool boundary only | Other users; the repository | Minimum text needed for a suggestion |
| Operational logs | Operations / review workflow | End users | Logs must remain reviewable and free of sensitive payloads |

**Boundary rule:** knowing a request identifier must not be sufficient to access
protected request information. Authorization is decided by the Application
Service based on the (simulated) authenticated identity. This is reflected in
`api-contracts.md` API-002 ("A caller must not receive protected request
information solely because they know a request identifier").

---

## 5. AI Context Sources and Limits

User-facing AI is **deferred to Cycle 2** (`scope.md`, Deferred Scope). This
section therefore describes the current Cycle 1 position and the boundary rules
that would apply if user-facing AI were added later.

### 5.1 Engineering AI assistance (used throughout the project)

AI tools may be used with project artifacts (requirements, planning documents,
architecture drafts, code, tests). Team members are responsible for reviewing
and understanding AI-assisted work before it enters the repository.

- **What AI may see:** project artifacts already committed or shared with the
  team, plus the team member's own working context.
- **What AI must not see:** credentials, tokens, private keys, confidential
  project information, or private personal information.
- **What is logged:** significant AI use is recorded in `/docs/ai/ai-use-log.md`
  with the tool, purpose, artifact affected, human verification, and result.

### 5.2 User-facing AI (deferred to Cycle 2)

User-facing AI is **not part of Cycle 1** (`scope.md`, Deferred Scope). If it is
introduced in Cycle 2, the following boundary rules would apply, consistent
with the team's AI Use Policy.

| Boundary Question | Rule for CampusConnect |
|---|---|
| **Read** | AI may read only the minimum request text needed to produce a suggestion. It may not read other requests, user identities, role assignments, or logs. |
| **Infer** | AI may suggest a category, summary, or missing-info flag. It may not infer priority, approvals, or resolution. |
| **Call** | No external tool calls from the running system unless separately approved and documented. |
| **Change** | AI may not create, update, resolve, close, or notify. It is read-only with respect to records. |
| **Approve** | Any suggestion requires human reviewer acceptance before it is applied. |
| **Observe** | Suggestion requests, returns, and reviewer accept/reject decisions are logged for review. |
| **Log** | Suggestion inputs, outputs, and reviewer decisions are recorded in the AI Use Log. |
| **Recover** | If AI is unavailable or fails, the reviewer categorizes manually. No workflow is blocked. |

**Retention rule:** AI suggestion context is not persisted beyond what is needed
for the suggestion and its review record. Request text already stored in the
Database is not duplicated into AI-specific storage.

---

## 6. Logging and Observability Expectations

CampusConnect requires that important status changes and reviewer actions be
inspectable (`requirements.md` REQ-005, `acceptance-criteria.md` AC-REQ-005-01).
The architecture supports this through application-level logging.

| Event | Logged? | What is recorded | What is deliberately not recorded |
|---|---|---|---|
| Request creation | Yes | Request id, requestType, timestamp, actor role | Full request description payload (stored in Database, not in logs) |
| Status transition | Yes | Request id, old status, new status, actor role, timestamp | Free-text resolution note body |
| Resolution note recorded | Yes | Request id, author role, timestamp | Full note body (stored in Database, not in logs) |
| Authorization failure | Yes | Request id (if known), actor identity reference, reason category | Credentials or tokens |
| Authentication events | Simulated | Per simulated identity source | Credentials |
| AI suggestion requested/returned (Cycle 2) | Yes | Request id, that a suggestion was requested, reviewer decision | Full AI prompt or model output where avoidable |
| AI suggestion accepted/rejected (Cycle 2) | Yes | Request id, reviewer identity reference, decision | — |

**Logging rule:** logs must remain reviewable and must not become a secondary
store of sensitive data. Sensitive fields stay in the Database, behind the
Persistence Layer, and are accessed only through the Application Service.

---

## 7. Data Lifecycle (Cycle 1 scope)

Cycle 1 uses synthetic data only and does not require production-grade data
lifecycle management. The intended behavior for the vertical slice:

| Phase | Behavior |
|---|---|
| Creation | A requester creates a request via API-001 (REQ-001); the Application Service assigns a unique id and initial status (REQ-003). |
| Update | Reviewers update status via the workflow service (REQ-005); valid transitions follow the status model in `architecture.md` §6. |
| Resolution | A reviewer records a short note or resolution (REQ-006); the request reaches `Resolved` and later `Closed`. |
| Read | The owning requester views status and resolution via API-002 (REQ-002); reviewers view any request (REQ-004). |
| Retention | Not formally decided in Cycle 1. Synthetic data may be reset between cycles. |
| Deletion | Not required in Cycle 1. If added later, it must go through the Application Service and be logged. |

---

## 8. Assumptions and Dependencies

| ID | Assumption / Dependency | Impact |
|---|---|---|
| ASM-001 | Cycle 1 uses simulated users and roles; real authentication is out of scope. | Identity is seeded, not integrated. Aligns with `scope.md` and `requirements.md` Cycle 1 Constraints. |
| ASM-002 | Cycle 1 uses synthetic data only. | Simplifies data handling and avoids regulated-data obligations. |
| ASM-003 | No live institutional integration is required in Cycle 1. | External services remain out of scope unless approved and documented. |
| DEP-001 | The Identity Provider is treated as simulated in Cycle 1 (`api-contracts.md` API-003). | Must not be replaced with a live provider without instructor approval and a documented risk review. |
| DEP-002 | Final production technology stack is deferred (`ADR-001`). | Data storage technology remains uncommitted; current design assumes a lightweight persistence layer. |

---

## 9. Open Questions

| ID | Question | Owner | Next Action |
|---|---|---|---|
| DQ-001 | What is the minimum retention rule for synthetic data between cycles? | Malec Tarabein | Confirm with team during Cycle 1 planning |
| DQ-002 | If user-facing AI is added in Cycle 2, what is the approved context scope? | Shú Perez | Revisit with a new ADR before implementation |
| DQ-003 | When ADR-001 is revisited, will the data store choice affect the logging or visibility boundaries in this document? | Shú Perez | Re-check this document when the technology stack ADR is finalized |

---

## 10. Related Evidence

- Architecture overview: `architecture.md`
- Component responsibilities: `component-responsibilities.md`
- API and interface contracts: `api-contracts.md`
- Requirements: `docs/requirements/requirements.md`
- Acceptance criteria: `docs/requirements/acceptance-criteria.md`
- Scope: `docs/planning/scope.md`
- Risk register: `docs/planning/risk-register.md`
- ADR-001: `docs/decisions/ADR-001-defer-production-technology-stack.md`
- AI Use Policy: `/docs/ai/ai-policy.md`
- AI Use Log: `/docs/ai/ai-use-log.md`
- Test strategy: `/docs/testing/test-strategy.md` (draft, PR #44)

---

## Expectations Checklist

- [x] Identifies authoritative data owners.
- [x] Distinguishes owned data from consumed external data.
- [x] Makes sensitive-data rules explicit.
- [x] Defines visibility boundaries between roles.
- [x] States what AI may and may not access.
- [x] Defines logging expectations without storing sensitive payloads.
- [x] Links to related architecture, contract, and AI-governance evidence.
- [x] Records open questions rather than inventing answers.
