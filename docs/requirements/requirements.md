# Requirements

<!--
STARTER KIT GUIDANCE — DELETE BEFORE PHASE-GATE SUBMISSION


-->

## Requirements

<!--
Replace the sample rows below with your team's actual requirements.

Use unique IDs in the form REQ-###.

Requirement:
It’s requirement is to be a small, bounded student support request workflow system that is not a full university platform.


Rationale:
It exists to replace the unorganized handling of emails/documents by using one controlled workflow that should be testable.
Priority:
Use Must, Should, or Could.

Acceptance Criteria Reference:
Reference the criteria in acceptance-criteria.md that demonstrate whether
the requirement has been satisfied.

Status:
Recommended values are Proposed, Accepted, Changed, Deferred, or Removed.
-->

| ID | Requirement | Rationale | Priority | Acceptance Criteria Reference | Status |
|---|---|---|---|---|---|
| REQ-001 | The system shall allow an authenticated student to submit a workflow request containing all information required for processing. | Students need a controlled and traceable way to initiate a workflow. | Must | AC-REQ-001-01, AC-REQ-001-02 | Proposed |
| REQ-002 | The system shall allow a requester to view the current status of each workflow request they submitted. | Requesters need visibility into workflow progress without relying on manual status inquiries. | Must | AC-REQ-002-01 | Proposed |

<!--
DELETE THE SAMPLE ROWS ABOVE after your team has replaced them with actual
project requirements.

Do not reuse a requirement ID for a different requirement after that ID has
been referenced elsewhere in the repository.
-->

## Requirement Quality

The primary stakeholders are the student requester(submits the request and monitor/view the request), the support reviewer(updates/resolves the status), the team administrator(optional in the 1st cycle), and the optional AI assistant(strictly advisory, and human-reviewed)
## Requirements and Design

<!--
The student will create a new support request that will contain only synthetic data
The system will store it and assign it with an unique number
The reviewer can view the request and change the status
The reviewer can also record the resolution 
The student can view the status and/or resolution 
The repository will show the traceability 

-->

## Requirements and Uncertainty

<!--
Do not invent an answer simply because a requirement is incomplete.

If an important fact is unknown, record it in:

/docs/requirements/assumptions-open-questions.md

A known uncertainty is stronger engineering evidence than an unsupported
assumption disguised as a requirement.
-->

## Requirements and Acceptance Criteria

<!--
A requirement establishes an obligation.

An acceptance criterion defines an observable condition demonstrating that
the obligation has been satisfied.

Example:

Requirement:
"The system shall allow a requester to view the current status of each
workflow request they submitted."

Acceptance criterion:
"Given an authenticated requester with an existing workflow request, when the
requester views their request list, then the current status of that request is
displayed."

Maintain traceability in both directions between requirements and acceptance
criteria.
-->

## Expectations

- Maintain unique requirement identifiers.
- Keep requirements current as understanding changes.
- Use professional engineering language.
- Record rationale rather than simply listing features.
- Assign meaningful priorities.
- Link requirements to acceptance criteria.
- Link related decisions, risks, tests, and other evidence when appropriate.
- Record unresolved uncertainty explicitly rather than inventing details.
- Preserve traceability when accepted requirements change, are deferred, or are removed.

<!--
This is a living requirements baseline, not a one-time document.

Before the applicable phase-gate submission:
1. Replace all sample data.
2. Review the document for accuracy and internal consistency.
3. Remove instructional HTML comments.
-->
