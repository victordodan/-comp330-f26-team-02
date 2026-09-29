# Assumptions and Open Questions

**Team:** Codex Ramblers  
**Project:** CampusConnect  
**Current Phase:** A2 — Planning and Estimation  
**Release Cycle:** Cycle 1

This document records requirement-level uncertainty that may affect planning, architecture, implementation, or verification.

## Assumptions

| ID | Assumption | Basis | Related Evidence | Impact if Incorrect | Owner | Status |
|---|---|---|---|---|---|---|
| ASM-001 | Each support request will have one current status at a time during Cycle 1. | The Cycle 1 scope calls for a small status model and current-status viewing. | REQ-004, REQ-006, `docs/planning/scope.md` | The workflow model, acceptance criteria, implementation, and verification may need to support more complex status history or concurrent state information. | Requirements owner | Unvalidated |
| ASM-002 | A simple reviewer note or resolution associated with the request is sufficient for Cycle 1 and a full discussion or messaging system is not required. | The Cycle 1 workflow requires the reviewer to record a note or resolution, while messaging is not part of the defined workflow. | REQ-005, REQ-006, `docs/planning/scope.md` | Additional communication behavior could expand scope, implementation effort, and verification requirements. | Requirements owner | Unvalidated |

## Open Questions

| ID | Question | Related Evidence | Owner | Needed By | Status / Resolution |
|---|---|---|---|---|---|
| Q-001 | What information must a student provide for a support request to be considered valid for Cycle 1? | REQ-001, AC-REQ-001-01, AC-REQ-001-02 | Shelby Sierah | Before architecture / implementation | Open |
| Q-002 | What exact request statuses and status transitions will Cycle 1 support? | REQ-004, REQ-006, R-004 | Shelby Sierah / Shu Perez | Before architecture decision | Open |
| Q-003 | Can a reviewer replace or edit a previously recorded note or resolution, or is only the current recorded value required? | REQ-005, AC-REQ-005-01 | Shelby Sierah | Before implementation | Open |
| Q-004 | How will the implementation represent or select the student requester and support reviewer roles while authentication remains simulated? | Cycle 1 constraints, REQ-003 through REQ-006 | Shu Perez | Architecture phase | Open |

## Managing Changes

When an assumption is validated, invalidated, or superseded, the team will review every requirement, acceptance criterion, task, estimate, risk, or design decision that depends on it.

When an open question is resolved:

1. Record the resolution here.
2. Update the affected requirement or acceptance criterion if necessary.
3. Update related planning and traceability evidence.
4. Preserve the question as engineering history when it influenced project decisions.

Uncertainty should remain visible until the team has evidence supporting a decision.