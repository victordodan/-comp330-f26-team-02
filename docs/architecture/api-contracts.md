
# API and Tool Boundary Contracts

**Project:** CampusConnect  
**Team:** Team 2 — Codex Ramblers  
**Assignment:** A3 — Cycle 1 Architecture and Review  
**Status:** Proposed — pending architecture and requirements review

## 1. Purpose and Scope

This document defines the significant interactions between CampusConnect components for the Cycle 1 support-request workflow.

It covers request submission, retrieval, reviewer access, status updates, notes or resolutions, persistence, and simulated role context.

These are proposed logical interface contracts. They are not claims that HTTP endpoints, a technology stack, or working implementations have been approved.

Cycle 1 uses synthetic data and simulated roles. Real Loyola authentication, institutional integrations, notifications, and user-facing AI are outside the required workflow.

## 2. Contract Summary

| ID | Consumer | Provider | Interaction | Requirements | Status |
|---|---|---|---|---|---|
| API-001 | Requester UI | Request Workflow Service | Submit support request | REQ-001, REQ-003 | Proposed |
| API-002 | Requester UI | Request Workflow Service | Retrieve own request | REQ-002 | Proposed |
| API-003 | Reviewer UI | Request Workflow Service | View submitted requests | REQ-004 | Proposed |
| API-004 | Reviewer UI | Request Workflow Service | Update request status | REQ-005 | Proposed |
| API-005 | Reviewer UI | Request Workflow Service | Record note or resolution | REQ-006 | Proposed |
| API-006 | Request Workflow Service | Request Repository | Persist and retrieve records | REQ-002 through REQ-006 | Proposed |
| API-007 | Request Workflow Service | Simulated Role Context | Obtain role and requester context | REQ-001, REQ-002, REQ-004 through REQ-006 | Proposed |

The contracts describe logical boundaries. Actual HTTP paths, schemas, and technology-specific details require an accepted architecture decision.

## 3. Shared Contract Rules

### Data constraints

- Only synthetic request data may be processed.
- Every successfully created request must have a unique identifier.
- Each request has one current status in the proposed Cycle 1 model.
- Reviewer notes or resolutions must remain associated with the correct request.
- Actual request fields and status values remain subject to Q-001 and Q-002.

### Role and ownership rules

- Requester and reviewer operations must remain distinguishable.
- A requester may view only requests associated with their simulated requester identity.
- Reviewer-only actions must be rejected for requester-role operations.
- The workflow service is responsible for enforcing role and ownership checks.
- The UI must not be treated as an authoritative security boundary.
- Simulated roles are a Cycle 1 testing mechanism, not production-grade authentication.

### Failure categories

The contracts distinguish:

- Invalid input
- Missing request
- Unauthorized role or request ownership
- Unsupported status or transition
- Persistence or unexpected service failure

Exact protocol error codes and response schemas will be finalized when the team accepts its implementation architecture.

### Observability

The implementation should provide enough evidence to investigate request creation, authorized updates, rejected operations, and persistence failures.

Logs should avoid unnecessary request descriptions or other potentially sensitive content. Logging design and retention require architecture review.

## 4. Detailed Interface Contracts

### API-001 — Submit Support Request

**Consumer:** Requester UI  
**Provider:** Request Workflow Service  
**Related requirements:** REQ-001, REQ-003  
**Acceptance criteria:** AC-REQ-001-01, AC-REQ-003-01  
**Status:** Proposed

**Input**

- Simulated requester context
- Request information required by the accepted Cycle 1 input specification

The exact required fields are unresolved under Q-001.

**Successful output**

- Unique request identifier
- Created request record or confirmation
- Initial current status, once the status model is approved

**Failure behavior**

- Reject missing or invalid required information.
- Reject an operation without an allowed requester context.
- Report persistence failure without claiming successful creation.

**Contract rules**

- Validate input before accepting the request.
- Persist the request and its identifier consistently.
- Do not report success unless storage succeeds.
- Retry and duplicate-submission behavior must be decided before implementation.

**Planned verification**

- Valid synthetic submission creates a stored request.
- Invalid submission is rejected.
- Created identifiers are unique.
- A simulated persistence failure does not produce false success.

### API-002 — Retrieve Requester-Owned Request

**Consumer:** Requester UI  
**Provider:** Request Workflow Service  
**Related requirement:** REQ-002  
**Acceptance criteria:** AC-REQ-002-01, AC-REQ-002-02  
**Status:** Proposed

**Input**

- Request identifier
- Simulated requester context

**Successful output**

- The request's current status
- Available resolution information
- The request identifier

**Failure behavior**

- Reject access to another requester's request.
- Handle unknown identifiers without exposing another requester's information.
- Report storage or retrieval failure.

**Contract rules**

- Enforce requester ownership in the workflow service.
- Do not rely only on UI filtering.
- Return the current stored status and resolution information.

**Planned verification**

- Requester retrieves their own request.
- Another requester cannot retrieve that request.
- Missing request is handled safely.
- Recorded resolution information is visible to the owner.

### API-003 — View Submitted Requests for Review

**Consumer:** Reviewer UI  
**Provider:** Request Workflow Service  
**Related requirement:** REQ-004  
**Acceptance criterion:** AC-REQ-004-01  
**Status:** Proposed

**Input**

- Simulated reviewer context

**Successful output**

- Submitted support requests available for review
- Information necessary to identify and inspect requests

**Failure behavior**

- Reject requester-role access to reviewer-only operations.
- Handle retrieval failure without showing misleading results.

**Contract rules**

- Verify reviewer role before returning reviewer-level information.
- Avoid unnecessary exposure of request data.
- Pagination and filtering are not assumed for Cycle 1.

**Planned verification**

- Reviewer can view submitted requests.
- Requester cannot use reviewer-only access.
- Empty results and retrieval failures are distinguishable.

### API-004 — Update Request Status

**Consumer:** Reviewer UI  
**Provider:** Request Workflow Service  
**Related requirement:** REQ-005  
**Acceptance criterion:** AC-REQ-005-01  
**Status:** Proposed

**Input**

- Request identifier
- Proposed new status
- Simulated reviewer context

**Successful output**

- Confirmation of the accepted change
- Updated current status

**Failure behavior**

- Reject unsupported statuses or transitions.
- Reject requester-role status changes.
- Reject changes to nonexistent requests.
- Report persistence failure without falsely confirming an update.

**Contract rules**

- Allowed statuses and transitions depend on Q-002.
- Validate the transition before persisting it.
- Keep the stored current status consistent with the confirmed result.
- Concurrent update handling requires a team design decision.

**Planned verification**

- Reviewer makes an allowed status change.
- Requester status change is rejected.
- Unsupported transition is rejected.
- Failed persistence does not produce a false confirmation.

### API-005 — Record Reviewer Note or Resolution

**Consumer:** Reviewer UI  
**Provider:** Request Workflow Service  
**Related requirement:** REQ-006  
**Acceptance criterion:** AC-REQ-006-01  
**Status:** Proposed

**Input**

- Request identifier
- Short note or resolution
- Simulated reviewer context

**Successful output**

- Confirmation that information was saved
- Current stored note or resolution information

**Failure behavior**

- Reject reviewer-only updates from requester roles.
- Reject invalid input according to accepted validation rules.
- Handle missing requests and persistence failure.

**Contract rules**

- Associate the note or resolution with the correct request.
- Persist the information before confirming success.
- Editing or replacing existing content remains unresolved under Q-003.

**Planned verification**

- Reviewer saves a note or resolution.
- Requester later sees available resolution information.
- Requester cannot perform reviewer-only edits.
- Failed storage is reported.

### API-006 — Request Repository Operations

**Consumer:** Request Workflow Service  
**Provider:** Request Repository  
**Related requirements:** REQ-002 through REQ-006  
**Status:** Proposed

**Logical operations**

- Create and persist a request with a unique identifier.
- Retrieve a request by identifier.
- Retrieve requests for reviewer access.
- Persist current status changes.
- Persist reviewer notes or resolutions.

**Contract rules**

- Repository operations must not bypass workflow authorization.
- The workflow service checks permissions before calling the repository.
- Storage operations must report success or failure accurately.
- The selected persistence technology remains subject to an accepted ADR.
- Transaction, concurrency, and identifier-generation details require design decisions.

**Planned verification**

- Stored records can be retrieved.
- Unique identifiers remain unique.
- Failed writes are reported.
- Updates preserve the association between request and stored information.

### API-007 — Simulated Role Context

**Consumer:** Request Workflow Service  
**Provider:** Simulated Role Context  
**Related requirements:** REQ-001, REQ-002, REQ-004 through REQ-006  
**Status:** Proposed

**Purpose**

Provide the simulated role and requester context required to distinguish student requester and support reviewer behavior.

**Expected information**

- Simulated role
- Simulated requester identity when applicable

**Contract rules**

- Role selection does not constitute real authentication.
- The workflow service applies role and ownership policies.
- The representation and selection mechanism remain unresolved under Q-004.
- No real Loyola identity provider is required.

**Planned verification**

- Requester and reviewer operations are distinguishable.
- Requester cannot perform reviewer-only operations.
- Requester ownership checks reject another requester's access.
- Missing or invalid simulated context is handled safely.

## 5. AI and External Tool Boundaries

The Cycle 1 support-request workflow does not require user-facing AI, external APIs, or institutional integrations.

Accordingly:

- No AI model is authorized by this proposal to change request state.
- No external service contract is assumed.
- Development-time AI assistance is recorded separately in the team's AI-use log.
- Any future AI or external integration requires explicit scope review, architecture decisions, permissions, failure handling, and applicable instructor approval.

## 6. Open Decisions and Dependencies

| Reference | Decision Needed | Affected Contracts |
|---|---|---|
| Q-001 | Required request fields and validation rules | API-001 |
| Q-002 | Supported statuses and transitions | API-001, API-002, API-004 |
| Q-003 | Editing or replacing notes and resolutions | API-005, API-006 |
| Q-004 | Simulated identity and role representation | API-001 through API-005, API-007 |
| Architecture ADR | Implementation technology, persistence and transport choices | All relevant contracts |

The team must resolve architecture-critical questions before accepting dependent contracts for construction. Deferred choices must have an owner and decision point.

## 7. Review and Verification Plan

Before marking a contract Accepted:

1. Compare it with the accepted architecture overview and diagram.
2. Confirm its consumer and provider names match the component responsibility map.
3. Review inputs, outputs, permissions, failures, and invariants.
4. Resolve or explicitly track open decisions.
5. Record independent reviewer findings and their dispositions.
6. Update the test strategy with the corresponding verification cases.
7. Update estimates, risks, and traceability if the design changes.

These are planned verification activities, not claims of completed tests.

## 8. Related Engineering Evidence

- `docs/requirements/requirements.md`
- `docs/requirements/acceptance-criteria.md`
- `docs/requirements/assumptions-open-questions.md`
- `docs/architecture/architecture.md`
- `docs/architecture/component-responsibilities.md`
- `docs/planning/traceability.md`
- `docs/planning/risk-register.md`
- `docs/decisions/ADR-001-defer-production-technology-stack.md`
- `docs/testing/test-strategy.md`
- `docs/ai/ai-use-log.md`

Links to the architecture review, GitHub issue, pull request, and accepted design decisions must be added or verified before the A3 phase gate.

**Evidence status:** Design proposal only. No implemented or verified API behavior is claimed.
