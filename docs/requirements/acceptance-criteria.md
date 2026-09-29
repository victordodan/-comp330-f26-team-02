# Acceptance Criteria

| ID | Requirement Reference | Acceptance Criterion | Verification Method | Status |
|---|---|---|---|---|
| AC-REQ-001-01 | REQ-001 User Authentication| Given a registered user, when valid login information are entered, then the system will allow access | Automated integration test | Proposed |
| AC-REQ-001-02 | REQ-001 User Authentication | Given invalid login information, when a user makes a login attempt, then the system will deny access and show an error message. | Automated integration test | Proposed |
| AC-REQ-002-01 | REQ-002 User Communication | Given an authenticated user, when a message is sent via platform, then the message is delivered to the recipient | Automated end-to-end test | Proposed |
| AC-REQ-002-02 | REQ-002 User Communication | Given that the network is interrupted or message delivery failure, when the user attempts to deliver a message, then the system will inform the user that the delivery was unable to process | Automated test / demonstration | Proposed |
| AC-REQ-003-01 | REQ-003 Team Coordination | Given an upcoming team meeting is scheduled, the assigned team members can view the date, time, and meeting details | Demonstration | Proposed |
| AC-REQ-003-02 | REQ-003 Team Coordination | Given an updated team meeting, meeting details are changed and modified, then all the members will receive the updated information | Automated test / demonstration | Proposed |
| AC-REQ-004-01 | REQ-004 Task Management | Given a team member assigned a task, when the member views the assigned task, then all the current & active tasks and due dates are shown | Automated test / demonstration | Proposed |
| AC-REQ-004-02 | REQ-004 Task Management | Given a completed task, when the user marks that the task is complete, then the status of the task will update frequently | Automated test | Proposed |
| AC-REQ-005-01 | REQ-005 Notifications | Given an important announcement, when a team-wide notification is developed, then all team members will receive the notification | Demonstration | Proposed |
| AC-REQ-005-02 | REQ-005 Notifications | Given a user who is logged into the platform, when they are presented with an assigned task, then the system will generate an assignment notification | Automated integration test | Proposed |
| AC-REQ-006-01 | REQ-006 Accountability Tracking | Given a project due date, when a team member submits work before the proposed deadline, then the submission will be recorded | Automated test | Proposed |
| AC-REQ-006-02 | REQ-006 Accountability Tracking | Given a missed deadline, when the due date has surpassed without submission, then the system will mark the task as overdue | Automated test | Proposed |
| AC-REQ-007-01 | REQ-007 AI Usage Documentation | Given AI-assisted work, when the user submits the project documentation, then the system will provide a method to record the details of AI usage | Inspection / demonstration | Proposed |
| AC-REQ-007-02 | REQ-007 AI Usage Documentation | Given an AI-use entry, the entry will save and then the information will become available for later review | Demonstration | Proposed |


## Writing Acceptance Criteria

<!--
Acceptance criteria should describe observable behavior or measurable outcomes.

When useful, use:

Given <starting condition>
When <action or event>
Then <observable result>

Given / When / Then is encouraged when it improves clarity, but it is not
mandatory. Another precise formulation is acceptable.

The important requirement is that the criterion be specific and verifiable.

Avoid criteria that merely repeat the requirement without defining how
satisfaction could be observed.
-->

## Positive and Negative Conditions

<!--
Do not consider only the successful path.

For relevant requirements, consider behavior involving:

- missing input;
- invalid input;
- unauthorized access;
- duplicate operations;
- unavailable dependencies;
- boundary conditions;
- failure conditions;
- recovery behavior; and
- other meaningful exceptions.

Not every requirement needs every type of condition. Use engineering judgment.
-->

## Verification Method

<!--
Identify how the team currently expects to demonstrate satisfaction of each
criterion.

Possible methods include:

- automated unit test;
- automated integration test;
- automated end-to-end test;
- manual demonstration;
- inspection;
- analysis;
- code review; or
- operational observation.

At an early phase gate, the method may be preliminary.

As implementation matures, replace general descriptions with concrete,
repository-visible evidence where practical.

Example early reference:
Automated test / demonstration

Example later reference:
tests/integration/test_request_submission.py
-->

## Status Guidance

<!--
Recommended status values:

Proposed
- Criterion has been identified but is not yet part of the accepted baseline.

Accepted
- Criterion is part of the current requirements baseline.

Implemented
- Intended system behavior exists.

Verified
- Evidence demonstrates that the criterion is satisfied.

Failed
- Verification demonstrates that the criterion is not currently satisfied.

Deferred
- Criterion has intentionally been postponed.

Removed
- Criterion no longer applies, but is retained when needed for traceability.

Do not mark a criterion Verified simply because implementation exists.
Verification requires evidence.
-->

## Acceptance Criteria and Implementation Tasks

<!--
Acceptance criteria describe acceptable SYSTEM BEHAVIOR.

They are not implementation tasks.

Acceptance criterion example:

"Given an invalid workflow request, when submission is attempted, then the
request is rejected and the missing required fields are identified."

Implementation-task example:

"Add validation logic to the request controller."

The first defines an observable outcome.
The second describes engineering work.
-->

## Traceability
| Requirement ID | Related Acceptance Criteria |
|---|---|
REQ-001 | AC-REQ-001-01, AC-REQ-001-02 | 
REQ-002 | AC-REQ-002-01, AC-REQ-002-02 |
REQ-003 | AC-REQ-003-01, AC-REQ-003-02 |
REQ-004 | AC-REQ-004-01, AC-REQ-004-02 | 
REQ-005 | AC-REQ-005-01, AC-REQ-005-02 | 
REQ-006 | AC-REQ-006-01, AC-REQ-006-02 | 
REQ-007 | AC-REQ-007-01, AC-REQ-007-02 |
<!--
Acceptance criteria should eventually connect requirements to verification
evidence.

A mature traceability path may look like:

REQ-001
  ->
AC-REQ-001-01
  ->
Automated integration test
  ->
Implementation
  ->
Phase-gate evidence

Maintain the relationships throughout the project rather than attempting to
reconstruct them at the end.
-->

## Unresolved Criteria

<!--
If the team cannot define a meaningful acceptance criterion because important
information is unknown, do not invent precision.

Record the unresolved matter in:

/docs/requirements/assumptions-open-questions.md

Refine the criterion when the required information becomes available.
-->

## Expectations

- Maintain unique acceptance-criteria identifiers.
- Reference valid requirement IDs.
- Keep requirement-to-criterion traceability current in both files.
- Make criteria observable and verifiable.
- Include important success and failure conditions.
- Identify an appropriate verification method.
- Strengthen verification references as implementation matures.
- Do not claim verification without evidence.
- Record unresolved questions rather than inventing missing behavior.
- Preserve meaningful traceability when criteria change.

<!--
Acceptance criteria are living engineering evidence and should mature alongside
requirements, implementation, and verification.

Before the applicable phase-gate submission:
1. Replace all sample data.
2. Confirm all requirement references are valid.
3. Review criteria for verifiability.
4. Remove instructional HTML comments.
-->
