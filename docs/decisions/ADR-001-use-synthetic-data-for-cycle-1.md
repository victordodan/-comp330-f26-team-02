# ADR-001: Use Synthetic Data for Cycle 1

**Status:** Accepted  
**Team:** Codex Ramblers  
**Project:** CampusConnect  
**Phase:** A2 — Planning & Estimation  
**Release Cycle:** Cycle 1  

## Context

CampusConnect is being developed as a minimal end-to-end support request workflow during Cycle 1.

The current Cycle 1 scope includes the ability to submit a support request, store the request, allow an authorized reviewer to access it, update its status, record reviewer notes, and allow the student to view the current status.

The project is currently intended for development and demonstration rather than production use. Using real student or other private information during this stage would introduce privacy, security, access-control, and data-handling concerns that are not necessary to demonstrate the Cycle 1 workflow.

The existing Cycle 1 scope already identifies fake data and simulated users as part of the planned implementation and excludes real student or private data from the current scope.

## Decision

Codex Ramblers will use synthetic or fake data and simulated users for CampusConnect during Cycle 1.

Real student records, private student information, credentials, or other sensitive production data will not be required to develop, demonstrate, or verify the Cycle 1 workflow.

Test and demonstration data should contain only fictional information created for CampusConnect development and verification.

## Alternatives Considered

### Alternative 1: Use Real Student Data

The team could use real student information to make demonstrations more representative of an actual university environment.

This alternative was not selected for Cycle 1 because real data would introduce privacy, security, access-control, and data-handling responsibilities that are unnecessary for validating the current workflow.

### Alternative 2: Use De-Identified Real Data

The team could use real records after removing identifying information.

This alternative was not selected for Cycle 1 because obtaining, preparing, reviewing, and safely handling real records would add complexity without being necessary to demonstrate the planned support-request workflow.

### Alternative 3: Use Synthetic Data and Simulated Users

The team can create fictional student, request, status, and reviewer information specifically for development, testing, and demonstration.

This alternative was selected because it supports the current Cycle 1 requirements while avoiding unnecessary use of real or private information.

## Rationale

Using synthetic data keeps Cycle 1 aligned with the project's current scope and allows the team to focus on demonstrating the planned support-request workflow.

This decision also reduces unnecessary privacy and data-handling risk during development and makes it easier for team members to create repeatable test scenarios without depending on access to real university information.

The decision does not mean that privacy, authorization, or security requirements are unimportant. Instead, it limits the type of data used during the current development cycle while those concerns continue to be represented in the project's requirements, risks, and future engineering work.

## Tradeoffs

### Benefits

- Avoids unnecessary use of real student or private information.
- Supports repeatable development and testing scenarios.
- Reduces dependencies on access to university or production data.
- Keeps Cycle 1 focused on the minimum end-to-end workflow.
- Allows repository evidence and demonstrations to be created without exposing sensitive information.

### Costs and Limitations

- Synthetic data may not represent every condition found in real university data.
- Some production-level privacy and integration concerns cannot be fully validated using simulated users and fictional records.
- Additional review will be necessary before any future phase considers real or production data.

## Consequences

For Cycle 1:

- development and testing should use fictional records;
- demonstrations should not depend on real student information;
- test evidence should avoid private or sensitive production data;
- tasks and requirements should not assume access to Loyola production systems or records; and
- any future proposal to use real data must be reviewed separately before that data is introduced.

If future requirements require production or real student data, the team should revisit this decision and evaluate the additional privacy, security, authorization, and data-handling requirements before changing the current approach.

## Related Engineering Evidence

This decision is related to:

- `docs/planning/scope.md`
- `docs/requirements/requirements.md`
- `docs/requirements/acceptance-criteria.md`
- `docs/planning/risk-register.md`
- `docs/planning/task-plan.md`
- `docs/planning/traceability.md`

The team should update related planning or traceability evidence if this decision materially changes during a later phase.

## Review and Reconsideration

This decision should be reconsidered if:

- Cycle 1 scope changes to require real university data;
- a requirement cannot be verified using synthetic data;
- integration with a real university system becomes part of the approved scope;
- security or privacy requirements require a different testing approach; or
- the team receives new project evidence that materially changes the assumptions behind this decision.

Until one of these conditions occurs, synthetic data and simulated users remain the accepted approach for Cycle 1.
