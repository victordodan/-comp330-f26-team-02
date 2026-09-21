# Project Scope

**Team:** Codex Ramblers  
**Project:** CampusConnect  
**Current Phase:** A1 — Project Launch  
**Release Cycle:** Cycle 1  

## Scope Statement

CampusConnect is a semester-long project focused on creating a student support request system. The system allows students to submit support requests and view their status, while a support reviewer can view those requests, update their status, and record a note or resolution.

For Cycle 1, our team's goal is to keep the project small and minimal, and complete one working end-to-end request workflow instead of trying to build a full university support platform. The project uses fake data and simulated users rather than real student or university information.

## In Scope

| Scope ID | Included Capability / Work | Related Requirements | Notes |
|---|---|---|---|
| SCP-001 | Student request submission | Initial Requirements | Students will be able to create a support request using synthetic data. |
| SCP-002 | Request storage and identification | Initial Requirements | Submitted requests will be stored and given a unique identifier. |
| SCP-003 | Reviewer access to requests | Initial Requirements | A support reviewer will be able to view submitted requests. |
| SCP-004 | Request status updates | Initial Requirements | A reviewer will be able to update the status of a request. |
| SCP-005 | Reviewer notes and resolution | Initial Requirements | A reviewer will be able to add a note or resolution to a request. |
| SCP-006 | Student status and resolution viewing | Initial Requirements | Students will be able to view the current status and resolution information for their requests. |
| SCP-007 | Repository engineering evidence | Assignment 1 evidence | Requirements, planning, issues, pull requests, reviews, tests, and later release evidence will be maintained in GitHub as the project develops. |

The first six items come directly from the required Cycle 1 CampusConnect workflow defined by the project brief. 

## Out of Scope

| Item | Reason Excluded | Future Consideration |
|---|---|---|
| Real student records or private university data | CampusConnect is required to use made-up data and does not require access to private Loyola information. | Not planned unless specifically requested. |
| Real Loyola authentication or single sign-on | Simulated user roles are enough for the required Cycle 1 workflow. | Could be considered outside the current semester scope. |
| Live integration with Loyola systems | The project does not require institutional integrations and they would add unnecessary complexity. | Only if approved and justified later. |
| Email or text-message notifications | Notifications are not needed to complete the core request workflow. | Possible later improvement. |
| User-facing AI features | AI may help us during development, but an AI feature is not required for the product itself. | Could be considered in Cycle 2 if justified. |
| Native mobile application | The project only needs one reviewable implementation. | Not currently planned. |
| Advanced analytics or reporting | These features are not necessary for the initial workflow. | Possible later improvement. |
| Full university support platform | CampusConnect is intentionally limited to a small support-request workflow. | Outside the current project scope. |

The project brief specifically keeps Cycle 1 small and states that real authentication, production integration, advanced analytics, mobile apps, and user-facing AI are not required for the minimum product.

## Scope Constraints

| Constraint | Impact on Scope | Related Evidence |
|---|---|---|
| Cycle 1 must remain intentionally small | Our team will focus on one complete workflow instead of adding many unnecessary features. | CampusConnect Project Brief |
| Simulated data only | Real student records, grades, private university data, or regulated records will not be used. | CampusConnect Project Brief |
| Separate requester and reviewer behavior | The system must distinguish between student and reviewer actions even if authentication is simulated. | CampusConnect Project Brief |
| Limited status model | Request statuses should remain simple and understandable. | CampusConnect Project Brief |
| No required live institutional integration | The project will not depend on Loyola production systems or services. | CampusConnect Project Brief |
| GitHub is the authoritative engineering record | Project evidence and engineering decisions must remain visible and reviewable in the repository. | Assignment 1 Project Launch Evidence Package |
| Fall semester schedule | Work must remain realistic across the six course modules and two development cycles. | COMP 330 course project structure |

The brief establishes synthetic-data use, simulated roles, a small status model, and no required institutional integrations as initial constraints. 

## Dependencies Affecting Scope

| Dependency | Why It Matters | Owner / Source | Related Risk |
|---|---|---|---|
| Initial Requirements | The planning scope should stay consistent with the requirements the team agrees on. | Shelby Sierah / `docs/requirements/` | To be tracked in risk register |
| Team availability | Deliverables depend on team members completing and reviewing assigned work. | Codex Ramblers | To be tracked in risk register |
| Technology stack decision | Implementation, testing, and setup planning depend on the stack the team selects. | Architecture & Development Lead / team | To be tracked in risk register |
| GitHub repository | GitHub is where the team's authoritative engineering evidence is maintained. | Codex Ramblers / GitHub | To be tracked in risk register |

## Deferred Scope

| Item | Reason Deferred | Decision / Evidence | Reconsider By |
|---|---|---|---|
| User-facing AI assistance | It is not necessary for the required Cycle 1 workflow. | CampusConnect Project Brief | Cycle 2 planning |
| Search, filtering, and expanded status history | The first priority is completing the required end-to-end request workflow. | CampusConnect Project Brief | Cycle 2 planning |
| Additional operational and observability features | These will become more relevant after implementation and testing evidence exists. | Team Project Overview | Later course modules |

## Scope Change History

At A1, this file represents the initial project scope baseline.

| Effective Gate | Change | Added / Removed | Reason | Related Evidence |
|---|---|---|---|---|
| A1 | Initial CampusConnect scope established | Initial baseline | Establish the project boundary before detailed implementation begins | `docs/requirements/` |
