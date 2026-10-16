# Data and Context Ownership — CampusConnect

> **Status:** Draft for Cycle 1 (A3 architecture phase gate); implementation unverified
> **Owner:** Shú Perez
> **Backup:** Giancarlo Herrera
> **Last updated:** 2026-10-16
> **Related:** `architecture.md`, `component-responsibilities.md`, `api-contracts.md`, ADR-001 through ADR-004

This document identifies authoritative request data, ownership, visibility,
AI/tool access, and proposed logging. Component names and API IDs follow merged
PR [#43](https://github.com/victordodan/-comp330-f26-team-02/pull/43). Behavior follows
the [requirements baseline from PR #46](https://github.com/victordodan/-comp330-f26-team-02/blob/c0d6782ed4bdf9899765477f6f2f4b4fb7ab4211/docs/requirements/requirements.md),
now merged into main. Full cross-document architecture review remains pending.

The requirements and scope constrain this document:

- **Synthetic data only.** No real student records, grades, private university data, or regulated records.
- **No live institutional integration.** External services require instructor approval and documented risk review.
- **Simulated identities.** No institutional login or real authentication in Cycle 1.
- **Local Java demonstration.** Server-rendered pages and local persistence; no paid hosting or required cloud services.
- **No runtime AI in Cycle 1.** A future cycle may reconsider it through scope and architecture review; no feature is committed.

---

## 1. Data Ownership Summary

| Data / State | Authoritative Owner | Persistence | Visibility | Notes |
|---|---|---|---|---|
| Workflow request (id, title, category, description, requester id, status, note/resolution, created/updated timestamps) | Request Workflow Service | Local data store via Request Repository | Owning requester and reviewers | Created via API-001 (REQ-001, REQ-003); requester read via API-002; reviewer read via API-003 |
| Request status | Request Workflow Service | Local data store via Request Repository | Owning requester and reviewers | Submitted, In Progress, or Resolved; reviewer updates via API-004 (REQ-005) |
| Reviewer note/resolution | Request Workflow Service | Local data store via Request Repository | Owning requester and reviewers | One editable current value via API-005 (REQ-006); preserved on reopening |
| Demo identities | Application-owned demo configuration, exposed through Simulated Role Context | Stable configured IDs; selected identity in server session | Demo options visible to users; selected context consumed by Request Workflow Service | Student A, Student B, Reviewer; no external identity source |
| Demo roles (student / reviewer) | Application-owned demo configuration | Fixed identity-to-role mapping | Application and users inspecting the demo options | No administrator role or role-management workflow |
| Runtime AI context | Not applicable in Cycle 1 | None | No runtime AI access | Future context policy requires a separate decision |
| Operational logs | Request Workflow Service | Proposed local application logs; location and retention pending design | Developers inspecting the local demonstration | Proposed events in Section 6; no implemented audit trail claimed |

**Authoritative data rule:** stored request records are authoritative. The
Request Workflow Service enforces validation, permissions, and workflow rules;
the Request Repository stores and retrieves records through API-006. The
Requester and Reviewer UI renders results and cannot establish ownership or
status. Demo identities are application-owned configuration, not consumed
external identity data. Their IDs remain stable across application restarts.

---

## 2. Identity and Role Simulation

Cycle 1 uses simulated users to distinguish student and reviewer behavior.

| Concern | Cycle 1 rule |
|---|---|
| Real Loyola authentication / SSO | Out of scope |
| Simulated roles | Student A and Student B are requesters; Reviewer inspects all requests and performs reviewer updates |
| Where simulated users are defined | Fixed application-owned demo configuration; a validated selector stores the chosen identity in the server session |
| How API-007 is treated in Cycle 1 | Simulated Role Context supplies the selected identity and role to the Request Workflow Service; API-003 is reviewer request access |

**Note:** API IDs follow the logical contracts merged in PR #43. The selector intentionally allows
switching among all three identities for demonstration. It is not authentication
or a production privacy control. Normal request forms cannot supply a trusted
role or requester identity. The service checks session context and ownership on
every operation, rejecting missing or invalid context. Switching users changes
subsequent permissions without changing stored request ownership.

---

## 3. Sensitive and Protected Data

Only synthetic request data may be stored, processed, or shared during Cycle 1.

| Concern | Rule |
|---|---|
| Real student records | Prohibited |
| Grades or academic records | Prohibited |
| Regulated data (FERPA, HIPAA, etc.) | Prohibited |
| Credentials, tokens, private keys | Never enter the repository or AI tools |
| Confidential project information | Not shared with AI tools |
| Private personal information | Not shared with AI tools |

These restrictions also follow the [AI Use Policy](../ai/ai-policy.md),
"Sensitive and Protected Information."

---

## 4. Context Visibility and Boundaries

| Context | Who may see it | Who may not see it | Rule |
|---|---|---|---|
| A requester's own request | Owning requester; Reviewer | Other requester identities | Knowing the ID is insufficient; service checks session ownership (API-002) |
| All requests | Reviewer | Student requester identities | Reviewer listing and detail access use API-003 (REQ-004) |
| Demo identity options | Anyone using the local demo selector | No secrecy is claimed for synthetic identity options | Current selection is stored in the server session |
| Demo role mapping | Application and users inspecting the demo options | Request forms cannot redefine the mapping | Fixed student/reviewer roles; no role-management interface |
| Runtime AI context | Not applicable in Cycle 1 | Runtime models and external tools have no request access | Future access requires scope and architecture decisions |
| Operational logs | Developers inspecting the local demonstration | Not exposed through student or reviewer application pages | Local machine access is outside the demo-role permission model |

**Boundary rule:** the Request Workflow Service enforces permissions based on
the selected session identity and request ownership. The selector permits
switching to another demo user, so the checks demonstrate role behavior rather
than protection from someone who controls the local demonstration.

---

## 5. AI Context Sources and Limits

Runtime AI is excluded from Cycle 1. The scope allows reconsideration in Cycle 2
planning; no model, runtime feature, or integration has been selected.

### 5.1 Engineering AI assistance (used throughout the project)

AI may assist with project artifacts such as requirements, architecture, code,
and tests. Team members review and understand assisted work before accepting it.

- **What AI may see:** approved project artifacts and working context, subject to the data restrictions above.
- **What AI must not see:** credentials, private keys, confidential information, or private personal information.
- **What is logged:** significant development assistance in [ai-use-log.md](../ai/ai-use-log.md), including purpose, affected artifact, actual human verification, and result.

### 5.2 User-facing AI (future consideration)

The following table records Cycle 1 exclusions; it does not approve future AI behavior.

| Boundary Question | Rule for CampusConnect |
|---|---|
| **Read** | No runtime model receives request data. |
| **Infer** | No runtime AI inference is performed. |
| **Call** | No runtime model or external tool calls are required. |
| **Change** | No runtime AI may create or modify request records. |
| **Approve** | A future integration requires scope/risk review, a new ADR, and applicable instructor approval. |
| **Observe** | Runtime AI monitoring is not applicable; future requirements remain undecided. |
| **Log** | No runtime prompts or responses exist. The repository AI Use Log records development assistance only. |
| **Recover** | The core workflow has no runtime AI dependency. |

**Retention rule:** no runtime AI context is created or retained in Cycle 1.
Future AI storage and retention need decisions before implementation.

---

## 6. Logging and Observability Expectations

These events are proposed to help diagnose the local workflow. REQ-005 and
REQ-006 describe status and note behavior; they do not establish that logs or
an audit history have been implemented or verified. Log location and retention
remain design follow-ups; accepted ADR-004 establishes database recovery expectations, not log recovery.

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

The intended lifecycle follows the requirements baseline from PR #46:

| Phase | Behavior |
|---|---|
| Creation | Selected student submits title, category, and description via API-001. The service validates the field rules and assigns id, owner, status Submitted, and timestamps. Success requires storage (REQ-001, REQ-003). |
| Update | Reviewer may change Submitted → In Progress, In Progress → Resolved, or Resolved → In Progress via API-004. Other transitions are rejected. Successful updates preserve id, owner, and creation time and update last-updated time (REQ-005). |
| Resolution | Reviewer saves or replaces one student-visible note/resolution via API-005 (maximum 2,000 trimmed characters). Resolving requires saved nonblank text. Reopening preserves it; clearing is allowed only in Submitted or In Progress. No Closed state, thread, or edit history (REQ-005, REQ-006). |
| Read | Owning student views current status/text via API-002; Reviewer inspects all requests via API-003. Missing/invalid context and unauthorized operations are rejected (REQ-002, REQ-004). |
| Retention | Records and stable owner IDs survive ordinary application restarts. Longer-term retention or deliberate fixture resets between cycles remain undecided; no automatic deletion is assumed. |
| Deletion | No user-facing deletion in Cycle 1. Future deletion or controlled fixture-reset behavior needs an explicit decision. |

Rejected operations preserve stored records. Failed writes must not produce
success confirmations. Accepted ADR-003 requires transactional updates and optimistic
version checking for status and note changes. Stale writes are rejected and the
reviewer reloads the current record. Post/Redirect/Get reduces accidental repeat
submissions without guaranteeing deduplication. Exact retry handling remains open.
ADR-004 requires persistent storage and documented backup/recovery procedures;
implementation and verification remain pending.

---

## 8. Assumptions and Dependencies

| ID | Assumption / Dependency | Impact |
|---|---|---|
| ASM-001 | One current request status, as recorded in the requirements assumption register. | Store Submitted, In Progress, or Resolved; concurrent-update handling must preserve valid state. |
| ASM-002 | One current note/resolution is sufficient, as recorded in the requirements assumption register. | Store editable current text; no thread or user-visible edit history. |
| DC-DEP-001 | Simulated Role Context from merged PR #43 supplies identity through API-007. | Fixed demo identities and service-side ownership checks must align with the requirements baseline; no live identity provider. |
| DC-DEP-002 | ADR-002 through ADR-004 were approved by Victor Dodan on October 16, 2026; ADR-001 remains the historical production-stack deferral. | Use Java, Spring Boot, Thymeleaf, Maven, and file-backed H2 with transactional/versioned updates and local persistence/recovery expectations; implementation remains pending. |

---

## 9. Open Questions

| ID | Question | Owner | Next Action |
|---|---|---|---|
| DQ-001 | What is the retention or controlled-reset policy for synthetic data between cycles? | Malec Tarabein | Define it without changing persistence across ordinary application restarts |
| DQ-002 | Should a future cycle introduce runtime AI, and under what boundaries? | Shú Perez | No feature is approved; decide through scope review and a new ADR before implementation |
| DQ-003 | Does the accepted local data-store choice affect logging or visibility boundaries? | Shú Perez | ADR-002 selects H2 and ADR-004 defines recovery expectations; preserve visibility boundaries and define remaining log retention and recovery implementation details |

---

## 10. Related Evidence

- Architecture overview: [architecture.md](architecture.md) — the replacement overview is under review in PR [#48](https://github.com/victordodan/-comp330-f26-team-02/pull/48); final alignment is pending.
- Component responsibilities: [component-responsibilities.md](component-responsibilities.md) — logical boundaries merged in PR [#43](https://github.com/victordodan/-comp330-f26-team-02/pull/43).
- API contracts: [api-contracts.md](api-contracts.md) — API-001 through API-007 follow merged PR #43.
- Requirements: [requirements.md](../requirements/requirements.md) — behavior baseline merged in PR #46, linked above.
- Acceptance criteria: [acceptance-criteria.md](../requirements/acceptance-criteria.md) — 12 proposed checks from PR #46, including validation, ownership, permissions, persistence, and transitions.
- Assumptions: [assumptions-open-questions.md](../requirements/assumptions-open-questions.md) — preserve ASM-001/ASM-002 and the recorded answers to Q-001 through Q-004.
- Scope: [scope.md](../planning/scope.md).
- Risk register: [risk-register.md](../planning/risk-register.md).
- ADR-001: [stack deferral](../decisions/ADR-001-defer-production-technology-stack.md).
- AI Use Policy: [ai-policy.md](../ai/ai-policy.md).
- AI Use Log: [ai-use-log.md](../ai/ai-use-log.md) — development assistance only.
- Test strategy: [test-strategy.md](../testing/test-strategy.md) — planned test coverage merged in PR [#44](https://github.com/victordodan/-comp330-f26-team-02/pull/44).

---

## Expectations Checklist

- [x] Identifies authoritative data owners.
- [x] Identifies application-owned demo identities; no external identity data is consumed.
- [x] Makes sensitive-data rules explicit.
- [x] Defines visibility boundaries between roles.
- [x] States what AI may and may not access.
- [x] Defines proposed logging without claiming implementation or storing request text in logs.
- [ ] Complete cross-document review against the corrected architecture overview and diagram; final A3 approval is pending.
- [x] Records open questions rather than inventing answers.
