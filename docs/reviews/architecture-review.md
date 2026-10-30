# Architecture Review

**Project:** CampusConnect
**Team:** Team 2 — Codex Ramblers
**Assignment:** A3 — Cycle 1 Architecture and Review
**Document Owner:** Giancarlo Herrera — Quality & Review Lead
**Status:** In Progress — Expanded Cross-Document Review

## Review Identification

**Initial Review Date:** October 14, 2026 (Chicago local time)
**Follow-Up Review Date:** October 15, 2026 (Chicago local time)
**Applicable Gate / Milestone:** Assignment 3 — Cycle 1 Architecture and Review
**Architecture Baseline / Commit:** PR #43, commit `f15a048aacd6c338d19926d5583b4c7112b46e6c`
**Reviewer:** Giancarlo Herrera
**Review Evidence:** https://github.com/victordodan/-comp330-f26-team-02/pull/43#pullrequestreview-5527186657

The initial review covers PR #43. The follow-up assessment also compares the proposed requirements in PR #46, data/context ownership in PR #47, and the security/governance checklist in PR #45. These are open pull requests and do not yet constitute an accepted or merged architecture baseline.

## Review Scope

The initial review covered:

- `docs/architecture/component-responsibilities.md`
- `docs/architecture/api-contracts.md`
- CampusConnect requirements REQ-001 through REQ-006
- Proposed role and workflow boundaries
- Planned failure handling and testability

The data/context proposal and security checklist have received preliminary review; the finalized architecture overview, diagram, ADR alignment, and full risk review remain to be evaluated against completed A3 versions.

## Architecture Summary

CampusConnect is a proposed support-request system with separate student requester and support reviewer workflows. Students submit requests using synthetic data, receive unique identifiers, and view status and resolution information. Reviewers inspect requests, change statuses, and record notes or resolutions.

PR #43 proposes four logical components:

- Requester and Reviewer UI
- Request Workflow Service
- Request Repository
- Simulated Role Context

The Request Workflow Service is intended to enforce workflow and authorization rules. The repository is responsible for persistence, while simulated role context supports Cycle 1 testing without real institutional authentication.

Seven proposed contracts, API-001 through API-007, describe interactions among these components. These contracts are design proposals, not implemented or verified APIs.

## Review Criteria

The initial review considered:

- Whether component responsibilities support REQ-001 through REQ-006.
- Whether workflow rules and authorization have clear ownership.
- Whether request data and persistence responsibilities are separated.
- Whether API contracts describe important inputs, outputs, and failures.
- Whether simulated requester and reviewer roles remain distinguishable.
- Whether documents are understandable and free of starter-template material.
- Whether unresolved decisions are clearly identified rather than treated as settled.

## Findings

### ARF-001 — Clear Proposed Component Boundaries

**Type:** Strength
**Importance:** Medium
**Evidence:** PR #43, component-responsibilities.md
**Finding:** The proposed components provide a reasonable separation between user interaction, workflow validation, request persistence, and simulated role context.
**Action:** Confirm component names and boundaries against the finalized architecture overview and diagram.

### ARF-002 — Contract Coverage of the Core Workflow

**Type:** Strength
**Importance:** Medium
**Evidence:** PR #43, api-contracts.md
**Finding:** The seven proposed contracts cover the main submission, retrieval, review, status update, resolution, persistence, and role-context interactions.
**Action:** Reconcile contract definitions with the finalized architecture and approved requirements.

### ARF-003 — Remaining Starter-Template Content

**Type:** Concern
**Importance:** Low
**Evidence:** Component Responsibilities review comment on PR #43
**Finding:** The initial PR #43 review raised a starter-template cleanup concern. The currently fetched PR #43 document appears substantially populated; any remaining template text must be verified in the latest diff before treating this concern as open.
**Action:** Venkata Mareddy should clean up the affected sections and request confirmation that the revised document is readable and consistent.

### ARF-004 — Final Architecture Alignment Pending

**Type:** Open Question
**Importance:** Medium
**Evidence:** PR #43 description and current architecture overview
**Finding:** The proposed component names and interfaces have not yet been confirmed against the finalized architecture overview, diagram, and related design decisions.
**Action:** Perform a follow-up cross-document review when those artifacts are available.

## Requirements Alignment

**REQ-001 — Submit Request:** API-001 proposes request submission through the workflow service and persistence boundary. Detailed required input fields remain unresolved.

**REQ-002 — View Status and Resolution:** API-002 proposes requester-owned retrieval with ownership checks.

**REQ-003 — Unique Identifier:** API-001 and API-006 describe creation and persistence responsibilities.

**REQ-004 — Reviewer Access:** API-003 and API-007 describe reviewer access and simulated role context.

**REQ-005 — Update Status:** API-004 proposes authorized status changes, subject to approval of allowed statuses and transitions.

**REQ-006 — Record Note or Resolution:** API-005 proposes authorized recording of notes or resolutions.

**Review Result:** The proposed contracts provide a plausible architectural path for all six requirements. This is a design-level assessment, not proof of implementation or completed acceptance testing.

## Component and Responsibility Review

### Requester and Reviewer UI

**Result:** Reasonable proposed boundary.
**Strength:** Handles user interaction without being the authoritative source of workflow permissions.
**Follow-up:** Confirm consistency with the architecture diagram.

### Request Workflow Service

**Result:** Reasonable proposed boundary.
**Strength:** Centralizes validation, workflow rules, and authorization decisions.
**Follow-up:** Clarify behavior after the status model and input rules are approved.

### Request Repository

**Result:** Reasonable proposed boundary.
**Strength:** Separates durable storage from workflow policy.
**Follow-up:** Confirm data ownership and failure handling with the data/context document.

### Simulated Role Context

**Result:** Provisional.
**Strength:** Supports requester/reviewer testing without assuming real institutional authentication.
**Follow-up:** Resolve how simulated identities and roles are represented and controlled.

## Interface and Dependency Review

The initial review found the proposed seven logical interfaces suitable for further architectural review.

- **API-001:** Submission and request creation.
- **API-002:** Requester-owned request retrieval.
- **API-003:** Reviewer request access.
- **API-004:** Reviewer status updates.
- **API-005:** Reviewer notes or resolutions.
- **API-006:** Persistence and retrieval.
- **API-007:** Simulated role context.

**Remaining work:** Confirm contract details against the accepted architecture, data ownership rules, and open requirements decisions. No implementation verification is claimed.

## Quality Attribute Review

**Security:** The workflow service is proposed as the authoritative permission boundary. Simulated roles are not production authentication.

**Reliability:** Proposed contracts distinguish successful operations from storage or retrieval failures.

**Data Integrity:** The design requires unique request identifiers and consistent request updates.

**Maintainability:** Separation of UI, workflow logic, persistence, and role context may reduce responsibility overlap.

**Testability:** Proposed interfaces support validation, authorization, failure-path, and integration test planning.

**Remaining Concern:** These are design-level strengths. Their implementation and verification remain future work.

## Architecture Risks and Open Questions

**Q-001 — Required Request Fields:** The team must define required inputs before validation and contract tests can be finalized.

**Q-002 — Status Values and Transitions:** The approved status model is needed to verify reviewer updates.

**Q-003 — Note or Resolution Editing:** The team must determine whether previously recorded information can be changed.

**Q-004 — Simulated Roles:** The team must decide how requester and reviewer identities are represented and controlled.

These questions should be tracked in `docs/requirements/assumptions-open-questions.md` and reflected in affected architecture and testing evidence.

## Decision Record Review

**ADR-001 — Defer Production Technology Stack**

The existing decision record documents deferral of the production technology stack.

**Review Result:** Full A3 reconciliation is pending.
**Revisit Needed:** Yes, when the architecture lead confirms implementation choices or explains why deferral remains justified.

The final architecture review must check whether the overview, component contracts, and implementation plan agree with the accepted decision records.

## Required Actions

### ARA-001 — Remove Remaining Starter Content

**Driver:** ARF-003
**Suggested Owner:** Venkata Mareddy
**Target:** Before final A3 submission
**Status:** Initial comment submitted; recheck the latest PR #43 diff before concluding cleanup remains necessary
**Evidence:** https://github.com/victordodan/-comp330-f26-team-02/pull/43#pullrequestreview-5527186657

### ARA-002 — Reconcile Architecture Documents

**Driver:** ARF-004
**Suggested Owner:** Architecture & Development Lead
**Target:** Before final A3 architecture approval
**Status:** Pending
**Evidence Needed:** Architecture overview, diagram, and confirmed component/interface alignment

### ARA-003 — Resolve or Explicitly Defer Open Questions

**Driver:** Q-001 through Q-004 and PR #46 proposed resolutions
**Suggested Owner:** Team Lead and relevant requirement owners
**Target:** Before dependent implementation decisions
**Status:** Proposed answers documented in PR #46; pending review, acceptance, and propagation into PR #43 contracts
**Evidence Needed:** Merged or accepted open-question records and updated component/API contracts

These are proposed review actions. The team should confirm ownership and create or link GitHub Issues for work requiring tracking.

## Follow-Up Review — October 15, 2026 (Open PRs)

**Evidence reviewed:** [PR #43](https://github.com/victordodan/-comp330-f26-team-02/pull/43), [PR #46](https://github.com/victordodan/-comp330-f26-team-02/pull/46), [PR #47](https://github.com/victordodan/-comp330-f26-team-02/pull/47), and [PR #45](https://github.com/victordodan/-comp330-f26-team-02/pull/45). Findings refer to proposed branch content, not accepted implementation or executed tests.

### ARF-005 — Requirements and Contracts Need Reconciliation

**Type:** Concern  
**Importance:** High  
**Evidence:** PR #46 proposed requirements and acceptance criteria compared with PR #43 component responsibilities and API contracts.  
**Finding:** PR #43 still describes Q-001 through Q-004 as unresolved and omits details proposed in PR #46: title (5–100 characters), category (Academic, IT, Campus Services, Other), description (10–2000 characters), simulated student A/B and reviewer server-session selection, statuses Submitted → In Progress → Resolved with Resolved → In Progress reopening, and editable reviewer notes (up to 2000 characters, not clearable while Resolved). PR #46 also proposes twelve acceptance checks. These details are not yet an accepted baseline.  
**Action:** Update the affected API-001, API-004, API-005, API-007 and component documents after team agreement; align acceptance-criteria references and planned tests without claiming tests have passed.

### ARF-006 — Data/Context Design Needs Status-Model Alignment

**Type:** Concern  
**Importance:** Medium  
**Evidence:** PR #47 data-context proposal versus PR #46 proposed workflow; [review approval on PR #47](https://github.com/victordodan/-comp330-f26-team-02/pull/47#pullrequestreview-5541852388).  
**Finding:** PR #47 describes a Closed state in its lifecycle, while PR #46 proposes only Submitted, In Progress, and Resolved. The PR #47 review was approved with an explicit consistency caveat; approval of that individual document is not approval of the entire architecture. Operational logging and development AI-use logging should also be distinguished.  
**Action:** Reconcile lifecycle terminology and logging responsibilities before the final architecture gate.

### ARF-007 — Security Checklist Overstates Implemented Controls

**Type:** Concern  
**Importance:** High  
**Evidence:** PR #45 security-governance checklist and [review comment](https://github.com/victordodan/-comp330-f26-team-02/pull/45#pullrequestreview-5541888178).  
**Finding:** The checklist describes authenticated user accounts and login credentials, which conflict with the proposed simulated-role-only Cycle 1 scope. It also labels security tests Passed and controls Complete without linked execution evidence.  
**Action:** Correct the authentication language; label design intentions and unverified tests as planned or not yet verified until actual evidence exists.

### ARF-008 — Architecture Overview and Diagram Are Not Yet Reviewable as a Final Set

**Type:** Open Question  
**Importance:** High  
**Evidence:** The main-branch `docs/architecture/architecture.md` still contains starter guidance; `docs/architecture/architecture-diagram.png` and `docs/architecture/README.md` were not present in the checked main-branch architecture directory.  
**Finding:** A full end-to-end architecture review cannot be completed against the current main-branch artifacts. Other work may be in progress outside the reviewed pull requests.  
**Action:** Obtain completed overview, diagram, architecture index, and relevant ADRs, then check their consistency with component ownership, role boundaries, persistence, and API contracts.

### Follow-Up Actions

| Action | Suggested Owner | Status | Completion Evidence |
|---|---|---|---|
| ARA-004 — Align PR #43 with proposed PR #46 behavior after agreement | Venkata Mareddy, with Team Lead | Open; teammate notified separately | Revised component and API files, accepted requirements |
| ARA-005 — Align PR #47 lifecycle and logging language | Architecture & Development Lead | Open; caveat recorded in approval | Revised data-context document and consistent status model |
| ARA-006 — Correct PR #45 authentication and test-status claims | Operations & Evidence Lead | Open; GitHub review comment submitted | Updated checklist with accurate verification status |
| ARA-007 — Review completed architecture overview, diagram, index, and ADRs | Architecture & Development Lead; Quality & Review Lead | Pending artifacts | Linked final files, review record, and dispositions |

**Gate decision:** Not ready for final A3 architecture approval. The above findings require responses or explicitly accepted deferrals, and the complete architecture evidence package must be reviewed. This assessment does not assert that any implementation or test has been completed.

## Review Conclusion

**Review Outcome: In Progress — Not Yet Ready for Final Architecture Approval**

The reviewed component responsibilities and API contracts provide a reasonable starting point for the CampusConnect Cycle 1 architecture. The initial review identified useful responsibility boundaries and coverage of the six requirements.

However, the overall architecture cannot yet be marked ready. Verification of starter-template cleanup, reconciliation of proposed requirements and contracts, correction of unsupported security-test claims, status-model alignment, and review of the completed architecture overview, diagram, and ADRs remain outstanding. PR #46 proposes resolutions for Q-001 through Q-004, but those resolutions have not yet been accepted and propagated through all documents.

This conclusion applies to the current partial review only.

## Follow-Up

The Quality & Review Lead will revisit PR #43 after the requested cleanup and compare its proposed components and contracts with the other completed A3 architecture documents.

Follow-up findings and action status will be updated using actual GitHub review evidence. Final architecture readiness will be assessed only after the relevant documents and decisions have been reviewed.


---

## Follow-Up Review — October 16, 2026 (Current Main Baseline)

**Reviewer:** Giancarlo Herrera, Quality & Review Lead  
**Status:** Cross-document review updated; final A3 approval remains pending.

**Interpretation:** This dated update supersedes the earlier *open PR*, *missing file*, and *unresolved decision* descriptions above where the main-branch evidence has changed. Earlier findings remain as a historical review record; they should not be read as current GitHub status.

### Evidence Reviewed

- [Architecture overview](../architecture/architecture.md), including Section 4's editable Mermaid diagram.
- [Architecture diagram PNG](../architecture/architecture-diagram.png), now present on `main`; PR [#50](https://github.com/victordodan/-comp330-f26-team-02/pull/50) is still open and should be reconciled with the revised main-branch image.
- [Architecture package index](../architecture/README.md), [component responsibilities](../architecture/component-responsibilities.md), [API contracts](../architecture/api-contracts.md), and [data/context ownership](../architecture/data-context.md).
- [Requirements](../requirements/requirements.md), [acceptance criteria](../requirements/acceptance-criteria.md), and [security governance checklist](../security/security-governance-checklist.md).
- Accepted Cycle 1 ADR-002, ADR-003, and ADR-004, as recorded in the [architecture package index](../architecture/README.md), which records Victor's October 16 approval.

### Finding Dispositions

| Finding / Action | October 16 Disposition | Evidence / Remaining Work |
|---|---|---|
| ARF-001 / ARF-004 — Component and overview alignment | **Substantially addressed at design level** | Overview, components, contracts, and Mermaid diagram use UI, Workflow Service, Repository, and Simulated Role Context. Final package approval still required. |
| ARF-002 / ARF-005 — Requirements and contracts | **Improved; final approval pending** | REQ-001–REQ-006 and API-001–API-007 cover submission, owner-only retrieval, reviewer actions, persistence, and role context. Requirements and some component/contract headings still describe the behavior as proposed/draft; team should confirm baseline status. |
| ARF-003 / ARA-001 — Starter-template concern | **Recheck substantially addressed** | Current component responsibilities file contains a populated matrix and detailed boundaries. Original review concern should be closed only if the reviewer confirms the specific old template lines are absent. |
| ARF-006 / ARA-005 — Status-model mismatch | **Resolved in current data/context text** | Data/context now explicitly says *no Closed state* and uses Submitted, In Progress, Resolved, matching the other architecture files. |
| ARF-007 / ARA-006 — Security checklist claims | **Documentation correction completed** | PR #45 merged; checklist now states simulated identities, no real login, and planned rather than passed verification where appropriate. Security enforcement and testing remain unverified. |
| ARF-008 / ARA-007 — Missing overview, diagram, index, ADRs | **Required artifacts now present; visual comparison pending** | Overview, inline Mermaid, PNG, architecture index, and ADR records are on main. Compare PNG visually against Mermaid before accepting a single diagram baseline; PR #50 remains open. |
| ARA-003 — Q-001 to Q-004 | **Documented design answers; acceptance check pending** | Requirements specify fields, statuses, editable notes, and simulated session identities. Confirm team approval and align any remaining 'draft' language. |

### Accepted Engineering Decisions and Remaining Verification

The architecture index records ADR-002 (Java/Spring Boot/Thymeleaf/Maven/file-backed H2), ADR-003 (duplicate-submission and optimistic-locking/transaction consistency expectations), and ADR-004 (local persistence and recovery) as accepted for Cycle 1. These decisions supersede the earlier unresolved implementation-stack question while preserving ADR-001 as a historical production-stack deferral. Acceptance of an ADR is not evidence that code, backup recovery, concurrent updates, or tests work.

The architecture documents consistently describe a local, synthetic-data demonstration with simulated Student A, Student B, and Reviewer identities; no real institutional authentication, runtime AI, or external service is in scope. Server-side role and ownership enforcement is a proposed responsibility, not an implemented security guarantee.

### Open Follow-Up Before Final Gate

1. **Shu / Architecture Lead:** Compare the current PNG against Section 4's editable Mermaid diagram and decide whether to embed the PNG in the overview; reconcile the still-open PR #50 with the PNG already on main.
2. **Victor / Requirements Owners:** Confirm whether proposed Q-001–Q-004 behavior is formally accepted and update draft/proposed status language consistently across requirements, component, and API documents.
3. **Giancarlo / Quality & Review Lead:** Record final review disposition after the diagram and baseline decisions are confirmed; check planned tests against acceptance criteria without representing them as executed.
4. **Team Lead:** Record final A3 gate approval and evidence links before tagging/submission.

**October 16 gate assessment:** Documentation coverage is materially improved and several earlier findings are resolved. **Final A3 architecture approval is still pending** the diagram reconciliation, baseline confirmation, and recorded team review. No running implementation, passing tests, successful recovery, or production readiness is claimed.


## Final Gate Control — Approval Record

The final review and baseline acceptance must be recorded in [A3 Final Baseline and Architecture Gate — Approval Record](a3-final-gate-approval.md). This document reconciles the now-merged A3 PR evidence and defines the explicit sign-offs required before the `a3-architecture-review` tag. Earlier findings are retained as review history. **Do not interpret a merged PR, completed documentation, or this preparation as Victor's or Shu's approval.** The final gate remains pending until authorized reviewers record decisions and evidence links.


## A4 Construction Follow-Up — October 29, 2026

**Reviewer:** Giancarlo Herrera (Quality & Review Lead). **Scope:** Follow-up against merged A4 validator/DTO work and open integration PRs. This is not a new A3 approval or a claim of complete runtime verification.

| Earlier finding / action | A4 construction evidence | Disposition and next proof |
|---|---|---|
| ARF-005 / ARA-004 — requirements and API contracts | [PR #67](https://github.com/victordodan/-comp330-f26-team-02/pull/67) merged input validation and six JUnit tests; [PR #68](https://github.com/victordodan/-comp330-f26-team-02/pull/68) merged request DTO | **Partially addressed:** request-field validation exists. Full API-001 and REQ-001 end-to-end persistence and identity tests still required |
| ARF-006 — inconsistent lifecycle vocabulary | [PR #70](https://github.com/victordodan/-comp330-f26-team-02/pull/70) merged status DTO; [PR #72](https://github.com/victordodan/-comp330-f26-team-02/pull/72) proposes additional statuses | **Open:** accepted Submitted → In Progress → Resolved / reopen semantics must be reconciled across controller, DTO, service and tests |
| ARF-007 — security controls overstated | [Mareddy's review of PR #72](https://github.com/victordodan/-comp330-f26-team-02/pull/72#pullrequestreview-5704379886) and [Giancarlo's review](https://github.com/victordodan/-comp330-f26-team-02/pull/72#pullrequestreview-5697921975) identify missing visible role/ownership enforcement | **Open, high priority:** negative student A/B ownership and reviewer-only update tests plus server-side enforcement and re-review |
| ARF-004 / ARF-008 / ARA-002 — architecture alignment | [PR #71](https://github.com/victordodan/-comp330-f26-team-02/pull/71) workflow service and [PR #73](https://github.com/victordodan/-comp330-f26-team-02/pull/73) app entry point are open; [PR #76](https://github.com/victordodan/-comp330-f26-team-02/pull/76) proposes Spring Boot dependencies | **Pending:** verify controller/service/repository/role boundaries, actual startup, and ADR-002–004 alignment after integration |
| ARA-003 — resolve or defer design questions | Accepted requirements/ADRs are referenced in [traceability](../planning/traceability.md) | **Partially addressed at planning level:** executed status, role, note, persistence and concurrency behavior not verified |
| ARA-005 / persistence and concurrency concerns | No merged H2/recovery/optimistic-version test evidence in the reviewed validator/DTO slice | **Open:** prove file-backed persistence, conflict detection and recovery or explicitly disclose gaps |

### Review and merge control

- [PR #67](https://github.com/victordodan/-comp330-f26-team-02/pull/67) has substantive review and passing focused validator CI. It **does not** close application-level architecture findings.
- [PRs #69, #71–73, #76–77](https://github.com/victordodan/-comp330-f26-team-02/pulls) remain proposed changes at this snapshot. Review findings on #72 are not considered resolved solely because its branch has changed.
- Do not mark architecture alignment, security, database persistence, or release readiness approved until implementation, CI and tests substantiate them.
- **Next check:** re-review integrated PR heads, confirm ADR decisions and update this matrix with actual fix commits and test/run links.
