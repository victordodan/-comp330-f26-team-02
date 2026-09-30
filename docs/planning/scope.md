# Project Scope

**Team:** Codex Ramblers\
**Project:** CampusConnect\
**Current Phase:** A2 — Planning and Estimation\
**Release Cycle:** Cycle 1

## Scope Statement

CampusConnect is a semester-long project focused on creating a student support request system. The system allows students to submit support requests and view their status, while a support reviewer can view those requests, update their status, and record a note or resolution.

For Cycle 1, our team's goal is to keep the project small and minimal, and complete one working end-to-end request workflow instead of trying to build a full university support platform. The project uses fake data and simulated users rather than real student or university information.

## In Scope

| **Scope ID** | **Included Capability / Work** | **Related Requirements** | **Notes** |
| ------------ | ------------------------------ | ------------------------ | --------- |
| SCP-001 | Student request submission | REQ-001 | Students will be able to create a support request using synthetic data. |
| SCP-002 | Request storage and identification | REQ-003 | Submitted requests will be stored and given a unique identifier. |
| SCP-003 | Reviewer access to requests | REQ-004 | A support reviewer will be able to view submitted requests. |
| SCP-004 | Request status updates | REQ-005 | A reviewer will be able to update the status of a request. |
| SCP-005 | Reviewer notes and resolution | REQ-006 | A reviewer will be able to add a note or resolution to a request. |
| SCP-006 | Student status and resolution viewing | REQ-002 | Students will be able to view the current status and resolution information for their requests. |
| SCP-007 | Repository engineering evidence | A2 engineering evidence | Requirements, planning, issues, pull requests, reviews, tests, decisions, and later release evidence will be maintained in GitHub as the project develops. |

The first six items correspond to the required Cycle 1 CampusConnect workflow defined in `docs/requirements/requirements.md`.

## Out of Scope

| **Item** | **Reason Excluded** | **Future Consideration** |
| -------- | ------------------- | ------------------------ |
| Real student records or private university data | CampusConnect is required to use made-up data and does not require access to private Loyola information. | Not planned unless specifically requested. |
| Real Loyola authentication or single sign-on | Simulated user roles are enough for the required Cycle 1 workflow. | Could be considered outside the current semester scope. |
| Live integration with Loyola systems | The project does not require institutional integrations and they would add unnecessary complexity. | Only if approved and justified later. |
| Email or text-message notifications | Notifications are not needed to complete the core request workflow. | Possible later improvement. |
| User-facing AI features | AI may help us during development, but an AI feature is not required for the product itself. | Could be considered in Cycle 2 if justified. |
| Native mobile application | The project only needs one reviewable implementation. | Not currently planned. |
| Advanced analytics or reporting | These features are not necessary for the initial workflow. | Possible later improvement. |
| Full university support platform | CampusConnect is intentionally limited to a small support-request workflow. | Outside the current project scope. |

The Cycle 1 requirements keep the project intentionally small. Real authentication, production institutional integration, advanced analytics, native mobile applications, and user-facing AI are not required for the minimum Cycle 1 workflow.

## Scope Constraints

| **Constraint** | **Impact on Scope** | **Related Evidence** |
| -------------- | ------------------- | -------------------- |
| Cycle 1 must remain intentionally small | Our team will focus on one complete workflow instead of adding many unnecessary features. | `docs/requirements/requirements.md` |
| Simulated data only | Real student records, grades, private university data, or regulated records will not be used. | `docs/requirements/requirements.md` |
| Separate requester and reviewer behavior | The system must distinguish between student and reviewer actions even if authentication is simulated. | `docs/requirements/requirements.md` |
| Limited status model | Request statuses should remain simple and understandable. | `docs/requirements/requirements.md`, `docs/requirements/assumptions-open-questions.md` |
| No required live institutional integration | The project will not depend on Loyola production systems or services. | `docs/requirements/requirements.md` |
| GitHub is the authoritative engineering record | Project evidence and engineering decisions must remain visible and reviewable in the repository. | Assignment 2 planning evidence |
| Fall semester schedule | Work must remain realistic across the course project schedule and development cycles. | COMP 330 course project structure |

These constraints establish the boundary for Cycle 1 planning and should be reconsidered only through documented requirement, planning, or decision changes.

## Dependencies Affecting Scope

| **Dependency** | **Why It Matters** | **Owner / Source** | **Related Evidence / Risk** |
| -------------- | ------------------ | ------------------ | --------------------------- |
| Cycle 1 requirements | Planning scope must stay consistent with the requirements and acceptance criteria maintained by the team. | Shelby Sierah / `docs/requirements/` | `docs/planning/traceability.md` |
| Team availability | Deliverables depend on team members completing and reviewing assigned work. | Codex Ramblers | `docs/planning/risk-register.md` |
| Technology stack decision | Implementation, testing, and setup planning will eventually depend on a committed technology stack. The final production technology stack is intentionally deferred until sufficient engineering evidence exists. | Architecture & Development Lead / team | `docs/decisions/ADR-001-defer-production-technology-stack.md` |
| GitHub repository | GitHub is where the team's authoritative engineering evidence is maintained. | Codex Ramblers / GitHub | Assignment 2 repository evidence |

## Deferred Scope

| **Item** | **Reason Deferred** | **Decision / Evidence** | **Reconsider By** |
| -------- | ------------------- | ----------------------- | ----------------- |
| Final production technology stack | The team does not yet have enough implementation and architecture evidence to make a well-supported permanent technology choice. | `docs/decisions/ADR-001-defer-production-technology-stack.md` | Architecture phase / when implementation requires a committed stack |
| User-facing AI assistance | It is not necessary for the required Cycle 1 workflow. | `docs/requirements/requirements.md` | Cycle 2 planning |
| Search, filtering, and expanded status history | The first priority is completing the required end-to-end request workflow. | `docs/requirements/requirements.md` | Cycle 2 planning |
| Additional operational and observability features | These will become more relevant after implementation and testing evidence exists. | A2 planning evidence | Later course modules |

## Scope Change History

The A1 scope baseline remains the foundation for the project. During A2, the team refined the scope evidence by connecting the existing Cycle 1 capabilities to stable requirement identifiers and current planning and decision evidence.

| **Effective Gate** | **Change** | **Added / Removed** | **Reason** | **Related Evidence** |
| ------------------ | ---------- | ------------------- | ---------- | -------------------- |
| A1 | Initial CampusConnect scope established | Initial baseline | Establish the project boundary before detailed planning and implementation begins. | `docs/requirements/` |
| A2 | Scope aligned with Cycle 1 requirements and planning evidence | Refined | Connect the existing scope baseline to stable requirement identifiers and current A2 planning evidence. | `docs/requirements/`, `docs/planning/` |
| A2 | Final production technology stack explicitly deferred | Deferred decision | Preserve architecture flexibility until sufficient engineering evidence exists to support a technology decision. | `docs/decisions/ADR-001-defer-production-technology-stack.md` |
