# Architecture Overview — CampusConnect

> **Status:** Proposed for Cycle 1; final A3 review pending
> **Baseline version:** PR #48 working draft; final submission tag pending
> **Last updated:** 2026-10-15
> **Owner:** Shú Perez · **Backup:** Giancarlo Herrera
> **Team:** Codex Ramblers (Team 2)

This document describes **what CampusConnect is now** — its structure, components, boundaries, data/context, AI/tool use, risks, and evidence links. It is **not** a home for every architecture decision. Significant decisions are recorded as individual ADRs in `/docs/decisions/` and indexed in Section 10.

---

## 1. Project Information

| Item | Team Response |
|---|---|
| Project name | CampusConnect |
| Team number / name | Team 2 — Codex Ramblers |
| Repository URL (clone) | https://github.com/victordodan/-comp330-f26-team-02.git |
| Architecture owner | Shú Perez |
| Backup owner | Giancarlo Herrera |
| Cycle 1 vertical slice | A student creates a support request and views its status; a support reviewer views the request, updates status, and records a resolution note. |
| AI governance | Team AI Use Policy (`/docs/ai/ai-policy.md`) and AI Use Log (`/docs/ai/ai-use-log.md`) define how AI is used and recorded. |

---

## 2. System Overview

CampusConnect is a **student support request and workflow system**. It gives a student a single, simple entry point to submit a support request, gives a support reviewer a controlled way to triage, clarify, update, and resolve that request, and gives both parties visibility into status and resolution. It replaces informal request handling through scattered email, shared documents, or verbal follow-up with a simple, inspectable workflow.

The primary workflow is: **Student creates request → system stores it with a unique ID → reviewer views it → reviewer updates status → reviewer records a resolution note → student views current status and resolution.**

The current release focus is the **Cycle 1 vertical slice** defined in the CampusConnect brief §4: a single, demonstrable, testable, reviewable end-to-end path using **synthetic data only**. The architecture is considered stable, governable, and reviewable enough for Cycle 1 because: (a) the slice is one bounded workflow over one authoritative data store; (b) role boundaries and status transitions are explicit (Sections 3 and 6); (c) any user-facing AI is advisory with a human approval gate (Section 8); and (d) failure and recovery paths are defined and testable (Section 9).

**Cycle 1 vertical slice (brief §4, all 7 steps):**

1. A student creates a new support request using synthetic data.
2. The system stores the request and gives it a unique identifier.
3. A support reviewer can view the request.
4. The reviewer can change the request status.
5. The reviewer can record a short note or resolution.
6. The student can view the current status and resolution information.
7. The repository contains evidence linking the workflow to requirements, issues, pull requests, tests, and release notes.

### Structural Approach

One local Java application contains the Requester and Reviewer UI, Request
Workflow Service, Request Repository, and Simulated Role Context. Controllers
and server-rendered templates implement the UI boundary. Services enforce
validation, role/ownership checks, and workflow rules. Repository code owns
storage operations. These logical components do not require separately deployed
services or a separate frontend application.

Only the workflow service initiates business storage operations through the
repository. The browser has no direct database access. Demo identity options
are application-owned configuration; the selected identity is stored in the
server session. Victor Dodan approved ADR-002 through ADR-004 on October 16, 2026. The Cycle 1
stack is Java, Spring Boot, Thymeleaf, Maven, and file-backed H2. The decisions
cover transactional/versioned updates and local persistence/recovery expectations;
implementation and final package review remain pending.

---

## 3. Users, Roles, and Permissions

| User / Role | Primary Goals | Allowed Actions | Limitations / Approvals |
|---|---|---|---|
| Student Requester (Student A / Student B) | Submit a request and understand what is happening | Create a request; view own request status and resolution | Cannot change status; cannot view other students' requests; cannot resolve or close |
| Support Reviewer | Triage, clarify, update, and resolve requests | List and inspect requests; update status; save or replace a note or resolution | Status changes must follow the valid state model (Section 6); resolving requires saved nonblank resolution text |
| Team Administrator | Maintain categories or basic workflow settings | Manage categories; approve escalated closures | Optional in Cycle 1; may be represented through seeded configuration |
| AI Assistant (optional) | Suggest, summarize, classify, or identify missing information | Produce advisory suggestions only | **Advisory only** — no status change, no resolution, no record mutation; every suggestion is human-reviewed before any action |

**Trust boundaries:** the UI is untrusted input; all role and authorization checks are enforced server-side in the Request Workflow Service. Identity is **simulated** in Cycle 1 — demo roles (Student A, Student B, Reviewer) are selected through the demo selector and stored in the server session, then supplied to the Request Workflow Service through the Simulated Role Context (API-007). Form-supplied roles or requester IDs cannot override session context. Real authentication and SSO are out of scope (brief §11; `assumptions-open-questions.md` Q-004).

---

## 4. Architecture Diagram

```mermaid
flowchart LR
    Students["Student A / Student B"] --> Browser["Local browser"]
    Reviewer["Reviewer"] --> Browser
    subgraph App["One local Java application"]
        UI["Requester and Reviewer UI<br/>Controllers and server-rendered templates"]
        Workflow["Request Workflow Service<br/>Validation, roles, ownership, status and note rules"]
        Context["Simulated Role Context<br/>Fixed demo identities and server session"]
        Repository["Request Repository"]
        Logs["Proposed local application logs<br/>Storage and retention pending design"]
        UI -->|"API-001 to API-005 logical operations"| Workflow
        UI -->|"Validated demo-user selection"| Context
        Workflow -->|"API-007: selected identity and role"| Context
        Workflow -->|"API-006: storage operations"| Repository
        Workflow -.->|"Safe event metadata; proposed"| Logs
    end
    Browser -->|"Page requests and form actions"| UI
    UI -->|"Rendered HTML and feedback"| Browser
    Repository --> Store[("Local persistent data store<br/>File-backed H2; ADR-002")]
```

The diagram is versioned in this document. It shows logical boundaries, not
separate servers. No external identity provider, administrator, runtime AI,
paid service, or institutional integration is part of the Cycle 1 architecture.

---

## 5. Component Responsibility Map

| Component | Owns / Responsible For | Must Not Own | Inputs / Outputs | Evidence Link |
|---|---|---|---|---|
| Requester and Reviewer UI | Page rendering, form handling, user feedback, student views, and reviewer queue/detail views | Final workflow/permission decisions or direct storage access | In: page/form actions; Out: HTML, feedback, logical service operations | [Component responsibilities](component-responsibilities.md) |
| Request Workflow Service | Validation, role/ownership enforcement, status/note rules, coordination of storage and success/failure responses | HTML rendering, database implementation, or real authentication | In: request data and session context; Out: authorized results and repository operations | [API-001 through API-007](api-contracts.md) |
| Request Repository | Persistent request IDs, owners, fields, timestamps, current status/text, retrieval, and write results | Authorization policy or status-transition decisions | In: service storage operations; Out: records or storage failures | [API-006](api-contracts.md#api-006--request-repository-operations) |
| Local persistent data store | Durable request records across ordinary restarts | Workflow policy, role decisions, or browser access | In/out: repository storage operations; file-backed H2; implementation pending | [Data/context ownership](data-context.md) |
| Simulated Role Context | Fixed demo identities and selected session context | Real identity verification or final workflow authorization | In: validated demo selection/session state; Out: role and stable requester identity | [API-007](api-contracts.md#api-007--simulated-role-context) |
| Proposed local logging | Diagnostic metadata for accepted/rejected operations and storage failures | Request descriptions, note bodies, credentials, or user-visible edit history | In: safe event metadata; output location/retention pending | [Proposed logging](data-context.md#6-logging-and-observability-expectations) |

Full responsibility detail: `component-responsibilities.md` (PR #43).

---

## 5.5 Architectural Qualities and Tradeoffs

The qualities below materially influenced the Cycle 1 architecture.

| Quality / Concern | Architectural Response | Tradeoff / Consequence |
|---|---|---|
| Maintainability | Separate page handling, workflow rules, session context, and persistence within one application. | Requires clear internal interfaces and consistent names. |
| Testability | Service-side validation/authorization and controlled repository failures supplement browser tests. | UI demonstrations alone cannot prove permission or failure behavior. |
| Governability | Reviewer-only updates and explicit status/resolution rules; development AI use is disclosed separately. | Demo identity switching supports classroom testing but provides no real authentication. |
| Reviewability | Link the overview to requirements, contracts, data/context rules, ADRs, and planned tests. | Cross-document alignment and review records need maintenance. |
| Honesty about uncertainty | Apply accepted stack, concurrency, and persistence decisions; keep remaining retry, logging, and recovery implementation details explicit. | Dependent implementation must wait for suitable decisions; no final readiness claim yet. |

---

## 6. Interfaces, APIs, and Tool Boundaries

| Interface / Tool | Consumer | Provider | Purpose | Protocol | Requirement | Status |
|---|---|---|---|---|---|---|
| API-001 | Requester UI | Request Workflow Service | Submit valid request | Logical service operation behind local page/form handling; routes pending | REQ-001, REQ-003 | Proposed |
| API-002 | Requester UI | Request Workflow Service | List/view student-owned requests | Logical service operation behind local page handling; routes pending | REQ-002 | Proposed |
| API-003 | Reviewer UI | Request Workflow Service | List/inspect all demo requests | Logical service operation behind local page handling; routes pending | REQ-004 | Proposed |
| API-004 | Reviewer UI | Request Workflow Service | Change status under transition rules | Logical service operation behind local form handling; routes pending | REQ-005 | Proposed |
| API-005 | Reviewer UI | Request Workflow Service | Save/replace note or resolution | Logical service operation behind local form handling; routes pending | REQ-006 | Proposed |
| API-006 | Request Workflow Service | Request Repository | Persist/retrieve request records | Internal storage boundary; file-backed H2, ADR-002 | REQ-001 through REQ-006 | Proposed |
| API-007 | Request Workflow Service | Simulated Role Context | Obtain selected session identity/role | Internal session/context boundary; implementation pending | REQ-001 through REQ-006 | Proposed |

These seven logical contracts do not require seven public endpoints or a JSON
API. ADR-002 selects the stack; exact HTTP routes, form schemas, and framework configuration remain implementation work.

**Valid status model:** Submitted → In Progress → Resolved, with Resolved →
In Progress for reopening. Only Reviewer may change status; other transitions
are rejected. Resolving requires saved nonblank note/resolution text. Reopening
preserves the text, request ID, and owner. Reviewer may edit the current text
(up to 2,000 trimmed characters) but cannot clear it while Resolved. See the
[requirements](../requirements/requirements.md) and [full contracts](api-contracts.md)
for field validation, permissions, and failure behavior.

---

## 7. Data and Context Ownership

Full detail is in `data-context.md`. Summary:

| Data / Context | Authoritative Owner | Persistence | Visible To | Logging Expectation |
|---|---|---|---|---|
| Request (id, title, category, description, requester id, status, note/resolution, created/updated timestamps) | Request Workflow Service controls changes; stored records are authoritative | Local store via Request Repository | Owning student; Reviewer | Proposed operation/state metadata only; no request text |
| Demo identity | Application-owned fixed configuration, exposed by Simulated Role Context | Stable demo IDs; current selection in server session | Demo selector options visible to users; current context consumed by service | Proposed synthetic identity-selection metadata; no session tokens |
| Student/reviewer role mapping | Application-owned demo configuration | Fixed identity-to-role mapping | Application and users inspecting demo options | No role-management workflow or real-authentication log |
| Runtime AI context | Not applicable in Cycle 1 | None | No runtime AI access | Development AI assistance is recorded separately |
| Operational logs | Request Workflow Service | Proposed local logs; location/retention pending design | Developers inspecting the local demonstration | No note bodies, descriptions, credentials, or session cookies |

**Data rules:** synthetic data only. The server assigns ID, owner, timestamps,
and initial status Submitted; normal form values cannot override them. Request
data and stable owner identities survive ordinary restarts. Successful updates
preserve ID, owner, and creation time and update last-updated time. Rejected
operations leave records unchanged; failed writes must not produce success
confirmations. Failed listings must be distinguishable from empty lists.

**Assumptions:** ASM-001 means one current request status; ASM-002 means one
short current note/resolution without threaded messaging. Q-004's recorded
answer uses the demo selector and server session. These meanings are preserved
from [assumptions-open-questions.md](../requirements/assumptions-open-questions.md).
Accepted ADR-003 requires transactional updates and optimistic version checks
for reviewer status and note changes. Reject stale writes and ask the reviewer
to reload. Post/Redirect/Get reduces repeated submissions without guaranteeing
deduplication. ADR-004 sets backup/recovery expectations. Longer-term retention,
controlled reset policy, logging location/retention, exact retry handling, and
executable recovery procedures remain follow-ups. No live identity or
institutional integration is required.

---

## 8. AI / Tool Boundary and Governance Model

CampusConnect operates under the team's AI Use Policy (`/docs/ai/ai-policy.md`). The policy treats AI as a support tool rather than a replacement for team judgment, and it requires that AI-assisted work be reviewed, verified, and documented before it becomes part of the project.

Per the CampusConnect brief (§9), AI is expected to assist engineering work across the lifecycle. User-facing AI is optional and, if used, must remain advisory and human-controlled. The team's AI Use Policy and AI Use Log define how this is governed in practice.

### Governance references

| Question | Where the answer lives |
|---|---|
| What is permitted or prohibited? | `/docs/ai/ai-policy.md` |
| What AI use has actually occurred? | `/docs/ai/ai-use-log.md` |
| Where is detailed verification recorded? | `/docs/ai/ai-verification-notes.md` |
| What did the team accept, modify, or reject? | The "Result / Action" column in the AI Use Log |

### AI boundary questions

The table below summarizes the boundaries the team has established through its AI Use Policy. It is a reference summary, not a substitute for the policy — the policy is authoritative, and the AI Use Log is the authoritative record of what actually happened.

| Boundary Question | How the AI Use Policy governs it | Where evidence is recorded |
|---|---|---|
| **Read** | AI tools may be used with project artifacts and, where a user-facing feature exists, the minimum request text needed for a suggestion. The policy prohibits sharing passwords, tokens, private keys, credentials, confidential project information, or private personal information. | AI Use Log; AI Use Policy §"Sensitive and Protected Information" |
| **Infer** | AI may suggest, summarize, classify, or identify missing information. The policy states AI is a support tool and not a replacement for team judgment; suggestions are advisory and must be reviewed. | AI Use Log; AI Use Policy §"Permitted Uses" |
| **Call** | No external tool calls are made by the running system in Cycle 1. Engineering AI tools are used interactively by team members, consistent with the policy's permitted uses. | AI Use Log; `api-contracts.md` §5 |
| **Change** | AI does not change records or repository state on its own. The policy requires that AI-generated code and technical recommendations be reviewed and, when applicable, tested before being accepted into the project. | AI Use Policy §"Human Review Requirements"; AI Use Log |
| **Approve** | The team member contributing AI-assisted work is responsible for reviewing and understanding it before it enters the repository. For user-facing suggestions, the reviewer decides whether to apply the suggestion. | AI Use Policy §"Human Review Requirements"; AI Use Log |
| **Observe** | Significant AI-assisted work is documented, and human verification explains what was actually checked. | AI Use Log; AI Verification Notes |
| **Log** | Significant AI use is recorded in the AI Use Log with the tool, purpose, artifact affected, human verification, and result/action. | `/docs/ai/ai-use-log.md` |
| **Recover** | If AI-assisted information is found to be incorrect, incomplete, misleading, or unsuitable, the policy requires the team to correct or remove it and to determine whether other work was affected. | AI Use Policy §"Handling Incorrect AI Output"; AI Use Log |

### How to read this section

The architecture treats AI as a **bounded, logged, and human-reviewed** part of the engineering process — not as an autonomous actor in the system. The specific rules are defined by the AI Use Policy, and the specific uses are recorded in the AI Use Log. Reviewers should read those two artifacts alongside this section.

For a representative example of how the team applies these rules in practice, see the AI Use Log entries from 2026-09-17 through 2026-09-29, which document how AI-assisted planning, requirements, and decision work was reviewed, modified, or rejected by team members before being merged.

---

## 9. Testing and Review Implications

| Architecture Claim | What Must Be Tested or Reviewed | Owner | Evidence Link |
|---|---|---|---|
| Request creation | Valid fields produce a stored Submitted request, unique ID, owner, and timestamps; invalid input creates none | Giancarlo Herrera | AC-REQ-001-01, AC-REQ-001-02, AC-REQ-003-01; [criteria](../requirements/acceptance-criteria.md) |
| Status transitions | Three permitted transitions; resolution requires text; reopening preserves ID/note; reject invalid changes | Giancarlo Herrera | AC-REQ-005-01, AC-REQ-005-02, AC-REQ-002-01 |
| Role boundary | Reviewer sees both students' requests; students cannot call reviewer operations or override roles with form data | Giancarlo Herrera | AC-REQ-004-01, AC-REQ-004-02 |
| Resolution note | Reviewer saves/replaces current text; owner sees it; oversized text and clearing while Resolved are rejected | Giancarlo Herrera | AC-REQ-006-01, AC-REQ-002-02 |
| API-001 contract | Exercise trimmed title/description boundaries, category validation, and failed storage | Mareddy Venkata Aravind | AC-REQ-001-02; [contracts](api-contracts.md) |
| API-002 contract | Student B cannot list or directly retrieve Student A's records; unknown IDs disclose no unrelated data | Mareddy Venkata Aravind | AC-REQ-002-03; [contracts](api-contracts.md) |
| Data/context control | Synthetic data only, stable ownership across identity changes, and no runtime AI access | Giancarlo Herrera | [Data/context rules](data-context.md); [test strategy](../testing/test-strategy.md) |
| Restart persistence | Status, owner, fields, and current text remain available after an ordinary restart and identity reselection | Giancarlo Herrera | AC-REQ-003-02 |
| Operational observability | Review proposed safe event metadata; simulate failures and distinguish failed listings from empty ones | Giancarlo Herrera | [Proposed logging](data-context.md#6-logging-and-observability-expectations); [test strategy](../testing/test-strategy.md) |

The 12 criteria are currently Proposed; baseline acceptance and executed test
results are separate milestones. Full plan: [test-strategy.md](../testing/test-strategy.md).
Plan concurrency, failed-transaction, repeated-submission, and recovery checks
against accepted ADR-003 and ADR-004. Execution remains future work.

---

## 10. ADR Index

| ADR ID | Decision Title | Status | Owner | File / Evidence Link |
|---|---|---|---|---|
| ADR-001 | Defer final production technology stack decision | Accepted — Deferred | Giancarlo Herrera | [ADR-001](../decisions/ADR-001-defer-production-technology-stack.md) |
| ADR-002 | Cycle 1 technology stack | Accepted — Cycle 1 | Codex Ramblers Team | [ADR-002](../decisions/ADR-002-cycle-1-technology-stack.md) |
| ADR-003 | Request consistency and concurrent updates | Accepted — Cycle 1 | Codex Ramblers Team | [ADR-003](../decisions/ADR-003-request-consistency.md) |
| ADR-004 | Local persistence and recovery | Accepted — Cycle 1 | Codex Ramblers Team | [ADR-004](../decisions/ADR-004-local-persistence-and-recovery.md) |

**Note:** retain ADR-001 as historical evidence of the production-stack deferral.
Victor Dodan approved ADR-002 through ADR-004 on October 16, 2026, establishing
the local Cycle 1 implementation decisions. This completes that decision
confirmation; implementation verification and final A3 package review remain pending.

---

## 11. Known Architecture Risks and Open Questions

| ID | Risk / Question | Impact | Owner | Next Action |
|---|---|---|---|---|
| AR-001 | Demo selection permits impersonation; service-side roles and ownership must still hold within the selected context | Medium | Giancarlo Herrera | Test direct operations and forged form context; keep the authentication limitation explicit |
| AR-002 | Uncontrolled status expansion could invalidate contracts and tests | Medium | Malec Tarabein | Preserve the three-state baseline; review future changes through requirements and ADRs |
| AR-003 | Runtime AI or external integrations could expand the class-project scope or introduce costs | Medium | Shú Perez | Keep them excluded; require explicit scope/risk review for any future addition |
| AR-004 | Implementation may diverge from the accepted framework/database decisions | Medium | Giancarlo Herrera | Apply ADR-002 and review estimate assumptions as implementation evidence develops |
| AR-005 | Concurrent updates, retries, or storage failures could violate valid status/text combinations | High | Victor Dodan | Apply ADR-003 transactional/versioned updates and ADR-004 recovery expectations; define remaining retry/procedure details and preserve the resolution invariant |

These concerns relate to R-001 (scope), R-002 (technology), R-004 (requirements),
R-005 (integration), R-006 (late testing), and confirmed active risks R-010
(persistence), R-011 (conflicting updates), and R-012 (decision alignment) in
the [risk register](../planning/risk-register.md). A3 planning updates retain
existing estimate ranges. ADR approval does not confirm available team hours
or establish that re-estimation is unnecessary.

---

## 12. Review & Change Control

- **Baseline under review:** Git tag `a3-architecture-review`.
- **Peer review findings:** `/docs/reviews/architecture-review.md` (Giancarlo Herrera, PR #44) with dispositions (accepted / deferred / rejected) and rationale.
- **Action tracking:** GitHub Issues #37, #38, #39, #40, #41; PRs #43, #44 (GitHub Projects may coordinate status but does not replace this document or the ADRs).
- **Change control:** any post-review change to boundaries, responsibilities, data flow, AI/tool use, security assumptions, observability, or recovery is recorded via updated evidence, a new/updated ADR, and planning updates.

### Architecture Evolution

| Effective Gate | Change | Reason | Related Evidence |
|---|---|---|---|
| A3 draft | Align the overview and inline diagram with the local Java behavior baseline and merged components/data rules | Remove conflicting states, roles, identity/API references, and premature baseline claims | PR #43, #44, #46, #47/#49; ADR-002–004 accepted October 16, 2026; final review pending |

---

## Final Check

- [x] Describes the proposed local system structure and logical boundaries.
- [x] Record acceptance of ADR-002 through ADR-004 for the stack, consistency, and persistence/recovery expectations; exact retry, logging, and recovery-procedure details remain follow-ups.
- [x] Links requirements, contracts, data/context rules, and planned verification without claiming passing tests.
- [ ] Complete independent cross-document review and resolve or explicitly disposition remaining findings.
- [ ] Confirm final A3 package readiness before creating the submission tag.
