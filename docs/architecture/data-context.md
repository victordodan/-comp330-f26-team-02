# Data and Context Ownership — CampusConnect

> **Status:** Draft for Cycle 1 (A3 architecture phase gate)
> **Owner:** Shú Perez
> **Backup:** Giancarlo Herrera
> **Last updated:** 10/ 15/ 2026
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

These events are proposed to help diagnose the local workflow. REQ-005 and
REQ-006 describe status and note behavior; they do not establish that logs or
an audit history have been implemented or verified. Log location, retention,
and recovery remain architecture decisions.

| Event | Logged? | What is recorded | What is deliberately not recorded |
|---|---|---|---|
| Request creation | Proposed | Request id, category, timestamp, actor identity reference and role | Full title/description payload |
| Status transition, including reopening | Proposed | Request id, old/new status, actor identity reference and role, timestamp | Free-text note/resolution body |
| Note/resolution saved or replaced | Proposed | Request id, actor identity reference and role, timestamp | Full note body and prior versions |
| Rejected operation or storage failure | Proposed | Operation, request id when known, actor identity reference, safe reason category | Credentials, tokens, request bodies |
| Demo identity selection | Proposed | Selected synthetic identity reference and timestamp | Session IDs or cookies |
| Future AI request/response | Not applicable in Cycle 1 | Future decision required | No runtime AI payloads are collected |
| Future AI review decision | Not applicable in Cycle 1 | Future decision required | No runtime AI decisions are collected |

**Logging rule:** logs must not become a second store of descriptions, notes,
or edit history. Current request text belongs in the local data store, accessed
through the Request Repository and Request Workflow Service. Logging does not
introduce a user-visible status history or comment thread.

---

## 7. Data Lifecycle (Cycle 1 scope)

The intended lifecycle follows the Task 1 behavior draft:

| Phase | Behavior |
|---|---|
| Creation | Selected student submits title, category, and description via API-001. The service validates the field rules and assigns id, owner, status Submitted, and timestamps. Success requires storage (REQ-001, REQ-003). |
| Update | Reviewer may change Submitted → In Progress, In Progress → Resolved, or Resolved → In Progress via API-004. Other transitions are rejected. Successful updates preserve id, owner, and creation time and update last-updated time (REQ-005). |
| Resolution | Reviewer saves or replaces one student-visible note/resolution via API-005 (maximum 2,000 trimmed characters). Resolving requires saved nonblank text. Reopening preserves it; clearing is allowed only in Submitted or In Progress. No Closed state, thread, or edit history (REQ-005, REQ-006). |
| Read | Owning student views current status/text via API-002; Reviewer inspects all requests via API-003. Missing/invalid context and unauthorized operations are rejected (REQ-002, REQ-004). |
| Retention | Records and stable owner IDs survive ordinary application restarts. Longer-term retention or deliberate fixture resets between cycles remain undecided; no automatic deletion is assumed. |
| Deletion | No user-facing deletion in Cycle 1. Future deletion or controlled fixture-reset behavior needs an explicit decision. |

Rejected operations preserve stored records. Failed writes must not produce
success confirmations. Retry and concurrent-update policies remain design
follow-ups; they must preserve valid status and resolution combinations.

---

## 8. Assumptions and Dependencies

| ID | Assumption / Dependency | Impact |
|---|---|---|
| ASM-001 | One current request status, as recorded in the requirements assumption register. | Store Submitted, In Progress, or Resolved; concurrent-update handling must preserve valid state. |
| ASM-002 | One current note/resolution is sufficient, as recorded in the requirements assumption register. | Store editable current text; no thread or user-visible edit history. |
| DC-DEP-001 | Simulated Role Context in proposed PR #43 supplies identity through API-007. | Fixed demo identities and service-side ownership checks must align with Task 1; no live identity provider. |
| DC-DEP-002 | ADR-001's stack deferral must be revisited during A3. | Java, server-rendered pages, and local execution are selected in the Task 1 draft; Spring Boot, Thymeleaf, Maven, and file-backed H2 remain recommendations. |

---

## 9. Open Questions

| ID | Question | Owner | Next Action |
|---|---|---|---|
| DQ-001 | What is the retention or controlled-reset policy for synthetic data between cycles? | Malec Tarabein | Define it without changing persistence across ordinary application restarts |
| DQ-002 | Should a future cycle introduce runtime AI, and under what boundaries? | Shú Perez | No feature is approved; decide through scope review and a new ADR before implementation |
| DQ-003 | When ADR-001 is revisited, will the data store choice affect logging or visibility boundaries? | Shú Perez | Re-check when the stack ADR is finalized; confirm local retention and recovery rules |

---

## 10. Related Evidence

- Architecture overview: [architecture.md](architecture.md) — still a starter template on this branch; alignment and a reciprocal link are pending.
- Component responsibilities: [component-responsibilities.md](component-responsibilities.md) — proposed replacement in PR [#43](https://github.com/victordodan/-comp330-f26-team-02/pull/43).
- API contracts: [api-contracts.md](api-contracts.md) — API-001 through API-007 follow PR #43, not the older template on this branch.
- Requirements: [requirements.md](../requirements/requirements.md) — reconcile with the pending Task 1 draft linked above.
- Acceptance criteria: [acceptance-criteria.md](../requirements/acceptance-criteria.md) — Task 1 adds validation, ownership, permission, persistence, and transition checks.
- Assumptions: [assumptions-open-questions.md](../requirements/assumptions-open-questions.md) — preserve ASM-001/ASM-002 and reconcile draft answers Q-001 through Q-004.
- Scope: [scope.md](../planning/scope.md).
- Risk register: [risk-register.md](../planning/risk-register.md).
- ADR-001: [stack deferral](../decisions/ADR-001-defer-production-technology-stack.md).
- AI Use Policy: [ai-policy.md](../ai/ai-policy.md).
- AI Use Log: [ai-use-log.md](../ai/ai-use-log.md) — development assistance only.
- Test strategy: [test-strategy.md](../testing/test-strategy.md) — proposed replacement in PR [#44](https://github.com/victordodan/-comp330-f26-team-02/pull/44).

---

## Expectations Checklist

- [x] Identifies authoritative data owners.
- [x] Identifies application-owned demo identities; no external identity data is consumed.
- [x] Makes sensitive-data rules explicit.
- [x] Defines visibility boundaries between roles.
- [x] States what AI may and may not access.
- [x] Defines proposed logging without claiming implementation or storing request text in logs.
