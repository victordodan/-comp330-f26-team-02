
# Component Responsibilities

**Project:** CampusConnect  
**Team:** Team 2 — Codex Ramblers  
**Assignment:** A3 — Cycle 1 Architecture and Review  
**Status:** Proposed — pending team architecture review

## Purpose

This document defines the major responsibilities, dependencies, and boundaries of CampusConnect components for the Cycle 1 support-request workflow.

The system allows a student requester to submit a support request using synthetic data, receive a unique request identifier, and view the request's status and resolution. A support reviewer can inspect requests, update their status, and record notes or resolutions.

The component structure below is a proposal for team review. Final component names and boundaries must agree with the architecture overview and diagram.

## Component Responsibility Matrix

| Component | Primary Responsibilities | Does Not Own | Depends On | Used By | Related Requirements |
|---|---|---|---|---|---|
| Requester and Reviewer UI | Collect request input, display requests and status information, and initiate permitted user actions | Authoritative authorization, workflow rules, persistent records | Request Workflow Service | Student requester, support reviewer | REQ-001, REQ-002, REQ-004, REQ-005, REQ-006 |
| Request Workflow Service | Validate operations, enforce workflow and role rules, coordinate request creation, retrieval, status changes, and resolution updates | UI rendering, physical data storage, real institutional authentication | Simulated Role Context, Request Repository | Requester and Reviewer UI | REQ-001 through REQ-006 |
| Request Repository | Store and retrieve support requests, unique identifiers, current statuses, and recorded notes or resolutions | Role authorization, user interface, workflow policy | Persistent data store (technology pending) | Request Workflow Service | REQ-002, REQ-003, REQ-004, REQ-005, REQ-006 |
| Simulated Role Context | Supply the selected requester or reviewer context for Cycle 1 and support requester ownership checks | Real authentication, institutional identity verification, workflow authorization decisions | Team-approved simulation mechanism (pending Q-004) | Request Workflow Service | REQ-001, REQ-002, REQ-004, REQ-005, REQ-006 |

## Architecture Constraints

- Only synthetic data is permitted in Cycle 1.
- Real Loyola authentication and single sign-on are outside Cycle 1 scope.
- Live institutional integrations and external services are not required.
- Requester and reviewer permissions must remain distinguishable.
- The workflow service, not the UI, must enforce authoritative workflow and permission rules.
- User-facing AI is optional and is not assumed to be part of this proposed component design.
- External services require instructor approval and documented risk review before use.

## Open Architecture Decisions

| Reference | Unresolved Question | Effect on Component Responsibilities |
|---|---|---|
| Q-001 | Required request input fields | Determines validation responsibilities and request data exchanged |
| Q-002 | Supported statuses and transitions | Determines workflow validation and state-change rules |
| Q-003 | Whether existing notes or resolutions can be edited | Determines update behavior and persistence requirements |
| Q-004 | How simulated requester and reviewer roles are represented | Determines the role-context interface and ownership enforcement |

These decisions must be resolved or explicitly deferred with owners and conditions before dependent construction.

## Evidence and Alignment

- Requirements: `docs/requirements/requirements.md`
- Acceptance criteria: `docs/requirements/acceptance-criteria.md`
- Open questions: `docs/requirements/assumptions-open-questions.md`
- Architecture overview: `docs/architecture/architecture.md`
- API contracts: `docs/architecture/api-contracts.md`

The team must review this proposal against the final architecture overview, diagram, ADRs, and API contracts. No implementation or verification is claimed by this document.
