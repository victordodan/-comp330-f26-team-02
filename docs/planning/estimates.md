# Estimates

**Team:** Codex Ramblers  
**Project:** CampusConnect  
**Current Phase:** A1 — Project Launch  
**Release Cycle:** Cycle 1  

## Estimation Approach

Codex Ramblers uses estimated team person-hours within a range to represent uncertainty. The estimate is the team's current expected effort, whereas the range accounts for unresolved requirements, technical decisions, integration work, and other such factors that may change as the project develops.

Initial estimates are expected to have wider ranges because architecture and implementation details are not yet fully known. Estimates will be refined throughout the semester as the team gains more engineering evidence and completes related work.

## Estimates

| ID | Work Item | Estimate | Range / Uncertainty | Basis | Assumptions | Owner(s) | Status |
|---|---|---|---|---|---|---|---|
| EST-001 | A1 project launch and planning evidence | 10 hours | 8–14 hours | Current A1 work primarily involves documentation, planning, repository setup, and review. | Team roles and the initial CampusConnect scope remain stable. | Codex Ramblers | Initial |
| EST-002 | Requirements and planning refinement | 14 hours | 10–20 hours | Includes refining requirements, acceptance criteria, assumptions, scope, schedule, risks, and traceability. | The Cycle 1 workflow remains limited to the current CampusConnect support-request process. | Codex Ramblers | Initial |
| EST-003 | Architecture and design | 18 hours | 12–26 hours | Includes selecting the technology stack, defining system structure and interfaces, and documenting major engineering decisions. | The team selects technologies that members can reasonably learn and support. | Codex Ramblers | Initial |
| EST-004 | Cycle 1 implementation | 36 hours | 24–50 hours | Based on implementing the student request workflow, persistence, reviewer actions, status updates, resolution information, integration, and supporting tests. | Requirements and architecture are stable enough before major construction begins. | Codex Ramblers | Initial |
| EST-005 | Cycle 1 verification and release preparation | 18 hours | 12–26 hours | Includes end-to-end testing, defect correction, review, documentation of limitations, and preparation of release evidence. | A complete Cycle 1 workflow is available for verification. | Codex Ramblers | Initial |
| EST-006 | Cycle 2 maturity improvements and final release | 32 hours | 20–48 hours | Cycle 2 work will depend on issues, risks, defects, and improvement opportunities identified from Cycle 1 evidence. | Cycle 2 remains focused on a limited number of evidence-based improvements rather than major feature expansion. | Codex Ramblers | Initial |

## Estimation Assumptions

| Assumption Reference | Estimate(s) Affected | Effect if Incorrect |
|---|---|---|
| Cycle 1 remains limited to the required support-request workflow | EST-002, EST-003, EST-004, EST-005 | Additional features would increase design, implementation, and verification effort. |
| Synthetic data and simulated user roles remain acceptable | EST-003, EST-004, EST-005 | Real authentication or institutional data integration would significantly increase project complexity. |
| The team selects a technology stack that members can reasonably support | EST-003, EST-004 | An unfamiliar or overly complex stack could increase implementation time and technical risk. |
| Requirements are refined before major construction begins | EST-003, EST-004, EST-005 | Significant requirement changes during implementation could cause rework and schedule changes. |
| Cycle 2 work is based on Cycle 1 evidence | EST-006 | The estimate may need to change significantly once actual defects, risks, and maturity needs are known. |

## Estimate Confidence

| Estimate ID | Confidence | Reason |
|---|---|---|
| EST-001 | High | A1 work is already underway and is mostly defined documentation and planning work. |
| EST-002 | Medium | The overall workflow is known, but requirements and planning details may still change. |
| EST-003 | Low | Major architecture and technology decisions have not yet been finalized. |
| EST-004 | Low | Implementation effort depends heavily on requirements, architecture, technology choices, and integration experience. |
| EST-005 | Low | Verification effort will depend on the quality and behavior of the implementation produced during construction. |
| EST-006 | Low | Cycle 2 work will be selected later based on evidence from Cycle 1. |

## Estimate Changes

No material estimate changes have been recorded yet. Estimates will be updated as the team gains more information rather than replacing earlier estimates without explanation.

## Estimate vs. Actual

No completed engineering work has enough estimation history yet to provide a useful estimate-versus-actual comparison. This section will be updated as estimated work is completed during the semester.

## Planning Implications

| Estimate / Evidence | Planning Impact | Related Scope / Schedule / Risk |
|---|---|---|
| EST-001 | Complete the A1 engineering baseline before beginning more detailed development planning. | A1 Project Launch |
| EST-003 and EST-004 | Architecture should be established before the team commits heavily to implementation work. | Architecture and Construction modules |
| EST-004 | Construction is expected to require the most technical effort, so Cycle 1 scope should remain intentionally small. | `scope.md`, semester schedule |
| EST-006 | Detailed Cycle 2 work should not be committed until Cycle 1 evidence shows where improvement is most valuable. | Cycle 2 planning |
