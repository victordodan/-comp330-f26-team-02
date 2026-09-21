# Schedule

**Team:** Codex Ramblers  
**Project:** CampusConnect  
**Current Phase:** A1 — Project Launch  
**Release Cycle:** Cycle 1  

## Schedule Basis

The CampusConnect schedule is determined based on the six course modules across the Fall semester: Lifecycle, Requirements, Architecture, Construction, Verification, and Operational Maturity. The team will use the corresponding phase gates and Sakai deadlines as the authoritative course deadlines.

Work is scheduled as such:
-Requirements and planning are established before architecture
-Architecture is established before major construction
-Working Cycle 1 system is available before verification. 

Later work may be adjusted as estimates are refined, risks are identified, and the team gains more engineering evidence and confidence.

## Milestones

| ID | Milestone | Target Date | Required By | Dependencies | Owner | Status |
|---|---|---|---|---|---|---|
| MS-001 | Establish the Project Launch baseline | End of Module 1 | A1 — Project Launch | Team formation, initial project direction, A1 engineering evidence | Team Lead | In Progress |
| MS-002 | Establish refined requirements and planning baseline | End of Module 2 | A2 — Requirements | MS-001, initial requirements, scope, estimates, risks | Planning and Requirements owners | Planned |
| MS-003 | Establish the initial system architecture and major design decisions | End of Module 3 | A3 — Architecture | MS-002, stable requirements and planning evidence | Architecture & Development Lead | Planned |
| MS-004 | Complete the controlled Cycle 1 CampusConnect workflow | End of Module 4 | A4 — Construction | MS-003, architecture decisions, implementation tasks | Development team | Planned |
| MS-005 | Verify the Cycle 1 workflow and prepare release evidence | End of Module 5 | A5 — Verification | MS-004, working implementation and testable workflow | Quality & Review Lead / team | Planned |
| MS-006 | Complete evidence-based maturity improvements and final project evidence | End of Module 6 | A6 — Operational Maturity | MS-005, Cycle 1 defects, risks, testing results, and review evidence | Codex Ramblers | Planned |

## Phase-Gate Readiness

| Gate | Internal Readiness Target | Key Evidence / Deliverables | Status |
|---|---|---|---|
| A1 — Project Launch | Before the A1 Sakai deadline | README, Team Charter, Role Matrix, Working Agreements, AI-use evidence, Initial Requirements, Planning and Risk, Initial Decision Record | In Progress |
| A2 — Requirements | Before the A2 Sakai deadline | Refined requirements, acceptance criteria, assumptions, scope, estimates, schedule, risks, and traceability | Planned |
| A3 — Architecture | Before the A3 Sakai deadline | Architecture overview, interfaces, design decisions, ADRs, and updated planning evidence | Planned |
| A4 — Construction | Before the A4 Sakai deadline | Working Cycle 1 implementation, repository history, pull requests, reviews, automated tests, and CI evidence | Planned |
| A5 — Verification | Before the A5 Sakai deadline | End-to-end verification, defect evidence, known limitations, and Cycle 1 release evidence | Planned |
| A6 — Operational Maturity | Before the A6 Sakai deadline | Evidence-based maturity improvements, updated operational evidence, and final release evidence | Planned |

## Major Dependencies

| Predecessor / Dependency | Dependent Work | Schedule Impact if Delayed | Related Risk |
|---|---|---|---|
| Initial requirements and scope | Architecture and implementation planning | Unresolved requirements may delay design decisions and create later rework. | To be tracked in `risk-register.md` |
| Technology and architecture decisions | Cycle 1 construction | Major implementation work may be delayed if the team has not agreed on the system structure or technology choices. | To be tracked in `risk-register.md` |
| Working Cycle 1 implementation | Verification and release preparation | Verification cannot be completed until the required workflow is available for testing. | To be tracked in `risk-register.md` |
| Cycle 1 verification evidence | Cycle 2 maturity planning | Cycle 2 priorities may be unclear if defects, risks, and limitations from Cycle 1 are not documented. | To be tracked in `risk-register.md` |
| Team availability and completion of assigned work | All milestones | Delayed individual work may affect dependent tasks and phase-gate readiness. | To be tracked in `risk-register.md` |

## Near-Term Planning Window

The current planning window extends through the A1 Project Launch phase gate.

| Time Window | Planned Outcome | Related Milestone(s) | Key Dependency / Risk |
|---|---|---|---|
| A1 Project Launch | Complete and review the initial repository evidence required to establish the project baseline. | MS-001 | A1 deliverables must be completed and reviewed before the final submission baseline is created. |
| Immediately following A1 | Refine requirements, estimates, planning, and open questions based on team review. | MS-002 | Changes identified during A1 review may affect the requirements and planning baseline. |

## Schedule Changes

No material schedule changes have been recorded yet. Significant milestone delays, scope changes, or estimate changes that affect the schedule will be recorded here as the project develops.

## Schedule Risks

Schedule-related risks will be assigned stable risk IDs in `docs/planning/risk-register.md`. Once the initial risk register is established, risks that materialy threaten a milestone or phase gate will be referenced in this table.