# Assumptions and Open Questions

**Team:** Codex Ramblers  
**Project:** CampusConnect  
**Current Phase:** A2 — Planning and Estimation  
**Release Cycle:** Cycle 1

This document records requirement-level uncertainty that may affect planning, architecture, implementation, or verification.

## Assumptions

| ID | Assumption | Basis | Related Evidence | Impact if Incorrect | Owner | Status |
|---|---|---|---|---|---|---|
| ASM-001 | A support request will have one current status at a time during Cycle 1. | The project requires the requester to view a current status and calls for a small, understandable status model. | REQ-002, REQ-005, `docs/planning/scope.md` | The request model, status acceptance criteria, planning, and later implementation may need to support more complex status information. | Shelby Sierah | Unvalidated |
| ASM-002 | A short reviewer note or resolution is sufficient for the required Cycle 1 workflow, and a threaded messaging system is not required. | The required vertical slice calls for a short note or resolution rather than a messaging feature. | REQ-002, REQ-006, `docs/planning/scope.md` | Additional communication behavior would expand requirements, planning, implementation, and verification work. | Shelby Sierah | Unvalidated |

## Open Questions

| ID | Question | Related Evidence | Owner | Needed By | Status / Resolution |
|---|---|---|---|---|---|
| Q-001 | What information must a student provide for a support request to be considered valid for Cycle 1? | REQ-001, AC-REQ-001-01 | Shelby Sierah | Before architecture / implementation | Open |
| Q-002 | What exact request statuses and status transitions will Cycle 1 support? | REQ-002, REQ-005, R-004 | Shelby Sierah / Shu Perez | Before architecture decision | Open |
| Q-003 | Can a reviewer replace or edit a previously recorded note or resolution, or is only the current recorded value required? | REQ-006, AC-REQ-006-01 | Shelby Sierah | Before implementation | Open |
| Q-004 | How will the implementation represent or select the student requester and support reviewer roles while authentication remains simulated? | Cycle 1 constraints, REQ-001 through REQ-006 | Shu Perez | Architecture phase | Open |

## Managing Changes

When an assumption is validated, invalidated, or superseded, the team will review every requirement, acceptance criterion, task, estimate, risk, or design decision that depends on it.

When an open question is resolved:

1. Record the resolution here.
2. Update the affected requirement or acceptance criterion if necessary.
3. Update related planning and traceability evidence.
4. Preserve the question as engineering history when it influenced project decisions.

Uncertainty should remain visible until the team has evidence supporting a decision.