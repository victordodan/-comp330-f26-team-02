# Assumptions and Open Questions

**Team:** Codex Ramblers  
**Project:** CampusConnect  
**Current Phase:** A3 — Architecture and Review  
**Release Cycle:** Cycle 1

This document records requirement-level uncertainty that may affect planning, architecture, implementation, or verification. Q-001 through Q-004 are resolved and explicitly approved by Victor Dodan in [the final A3 approval record](../reviews/a3-final-gate-approval.md). Implementation verification remains later work. Existing owner names preserve the A2 record and do not restrict contributions to the current work.

## Assumptions

| ID | Assumption | Basis | Related Evidence | Impact if Incorrect | Owner | Status |
|---|---|---|---|---|---|---|
| ASM-001 | A support request will have one current status at a time during Cycle 1. | The project requires the requester to view a current status and calls for a small, understandable status model. | REQ-002, REQ-005, `docs/planning/scope.md` | The request model, status acceptance criteria, planning, and later implementation may need to support more complex status information. | Shelby Sierah | Approved for Cycle 1; implementation unverified |
| ASM-002 | A short reviewer note or resolution is sufficient for the required Cycle 1 workflow, and a threaded messaging system is not required. | The required vertical slice calls for a short note or resolution rather than a messaging feature. | REQ-002, REQ-006, `docs/planning/scope.md` | Additional communication behavior would expand requirements, planning, implementation, and verification work. | Shelby Sierah | Approved for Cycle 1; implementation unverified |

## Open Questions

| ID | Question | Related Evidence | Owner | Needed By | Status / Resolution |
|---|---|---|---|---|---|
| Q-001 | What information must a student provide for a support request to be considered valid for Cycle 1? | REQ-001, AC-REQ-001-01, AC-REQ-001-02 | Shelby Sierah | Before architecture / implementation | Resolved and approved: title (5–100 trimmed characters), category (Academic, IT, Campus Services, Other), and description (10–2,000 trimmed characters). The application assigns ID, requester, timestamps, and initial status Submitted. Categories and limits are approved Cycle 1 rules. |
| Q-002 | What exact request statuses and status transitions will Cycle 1 support? | REQ-002, REQ-005, AC-REQ-005-01, AC-REQ-005-02, R-004 | Shelby Sierah / Shu Perez | Before architecture decision | Resolved and approved: Submitted → In Progress → Resolved, with Resolved → In Progress reopening. Reviewer-only changes; resolving requires saved nonblank resolution text. Other transitions are rejected. |
| Q-003 | Can a reviewer replace or edit a previously recorded note or resolution, or is only the current recorded value required? | REQ-006, AC-REQ-006-01, AC-REQ-004-02 | Shelby Sierah | Before implementation | Resolved and approved: one editable, student-visible field, maximum 2,000 trimmed characters. Reopening preserves it; clearing is permitted only in Submitted or In Progress. No comment thread or edit history. |
| Q-004 | How will the implementation represent or select the student requester and support reviewer roles while authentication remains simulated? | Cycle 1 constraints, REQ-001 through REQ-006, AC-REQ-002-03, AC-REQ-004-02 | Shu Perez | Architecture phase | Resolved and approved: a demo selector chooses Student A, Student B, or Reviewer and stores the selection in the server session. Stable identities preserve ownership across restarts. Application checks enforce roles and ownership; demo selection is not real authentication. |

Victor Dodan approved ADR-002 through ADR-004 on October 16, 2026: Java, Spring Boot, Thymeleaf, Maven, and file-backed H2; Post/Redirect/Get and optimistic version checking with transactions; and local persistence with documented backup/recovery expectations. Implementation and verification remain pending. Exact retry handling, logging location/retention, controlled reset policy, and executable recovery procedures still require implementation detail. Victor approves the A3 decision baseline and accepts the remaining documented implementation details as follow-up work in the final approval record.

## Managing Changes

When an assumption is validated, invalidated, or superseded, the team will review every requirement, acceptance criterion, task, estimate, risk, or design decision that depends on it.

When an open question is resolved:

1. Record the resolution here.
2. Update the affected requirement or acceptance criterion if necessary.
3. Update related planning and traceability evidence.
4. Preserve the question as engineering history when it influenced project decisions.

Uncertainty should remain visible until the team has evidence supporting a decision.
