# Planning Package

**Team:** Codex Ramblers  
**Project:** CampusConnect  
**Current Phase:** A2 — Planning and Estimation  
**Release Cycle:** Cycle 1  

## Purpose

This directory contains the authoritative Cycle 1 planning evidence for CampusConnect.

The A2 planning package converts the current CampusConnect requirements into visible and manageable engineering work by documenting project scope, task relationships, estimates, risks, schedule dependencies, traceability, team commitments, and re-estimation practices.

Planning evidence is maintained as the project develops rather than reconstructed at the end of a phase. When requirements, estimates, risks, tasks, or decisions change materially, the affected planning artifacts should be reviewed and updated.

## Planning Evidence Index

| Artifact | Purpose | Current Status | Authoritative Location |
|---|---|---|---|
| Cycle 1 Scope | Defines the intended Cycle 1 vertical slice, project boundaries, constraints, dependencies, and deferred work. | Existing — A2 refinement required | [`scope.md`](scope.md) |
| Requirements Traceability | Connects Cycle 1 requirements and acceptance criteria to scope, tasks, owners, estimates, risks, and later verification evidence. | Updated for A2 | [`traceability.md`](traceability.md) |
| Task Plan | Records major planned work, ownership, dependencies, targets, estimates, and status. | Updated for A2 | [`task-plan.md`](task-plan.md) |
| Effort Estimates | Records low, likely, and high estimates together with assumptions, confidence, dependencies, and re-estimation triggers. | Updated for A2 | [`estimates.md`](estimates.md) |
| Risk Register | Records identified project risks, triggers, likelihood, impact, mitigation, contingency response, ownership, and status. | Existing — A2 refinement required | [`risk-register.md`](risk-register.md) |
| Schedule and Milestones | Records major Cycle 1 milestones, dependencies, integration checkpoints, review windows, buffers, and schedule triggers. | Updated for A2 | [`schedule.md`](schedule.md) |
| Team Commitments | Records who owns planned work, expected completion targets, and the evidence that will demonstrate completion. | Pending A2 completion | [`team-commitments.md`](team-commitments.md) |
| Re-estimation Notes | Records changes to estimates or commitments, the evidence that triggered those changes, and resulting scope or schedule decisions. | Pending A2 completion | [`re-estimation.md`](re-estimation.md) |

## Related Engineering Evidence

The planning package depends on and links to engineering evidence maintained elsewhere in the repository.

### Requirements

Current Cycle 1 requirements, acceptance criteria, assumptions, and open questions are maintained under:

[`../requirements/`](../requirements/)

The current requirements remain proposed planning inputs while team review and requirement-level uncertainty continue to be resolved.

### Engineering Decisions

Tradeoff decisions, deferred-scope rationale, and major engineering decisions are maintained under:

[`../decisions/`](../decisions/)

Decision evidence should only be added when the team has actually made or explicitly deferred a decision. Planning artifacts should not infer architecture or implementation choices that have not yet been established.

### AI-Assisted Engineering

AI-use policy, significant AI-assisted work, and human verification evidence are maintained under:

[`../ai/`](../ai/)

Significant AI-assisted planning work must be reviewed and verified by team members before it is accepted as project evidence.

### GitHub Workflow Evidence

GitHub Issues, branches, pull requests, reviews, and repository-visible status updates provide workflow evidence for planned engineering work.

Issues should identify meaningful work, ownership, applicable requirement or planning relationships, and completion evidence where appropriate.

## Current Planning Baseline

The current Cycle 1 planning baseline is centered on one bounded end-to-end CampusConnect support-request workflow:

1. A student requester submits a support request using synthetic data.
2. The system stores the request and assigns a unique identifier.
3. A support reviewer can view submitted requests.
4. The reviewer can change the current request status.
5. The reviewer can record a short note or resolution.
6. The student requester can view the current status and available resolution information.

The planning package intentionally avoids expanding Cycle 1 into a full university support platform.

Architecture, implementation, testing, and release evidence will be added only when those lifecycle activities actually occur.

## Planning Maintenance

The planning package should be reviewed when:

- a requirement or acceptance criterion changes;
- an open question or assumption is resolved;
- a task is added, split, blocked, reassigned, deferred, or completed;
- an estimate moves outside its expected range;
- a tracked risk materializes or changes significantly;
- a project decision affects scope, architecture, or implementation;
- integration or testing reveals unexpected work;
- team availability changes;
- or the team prepares for a phase-gate review.

When new evidence changes what the team knows, the affected plan should be updated rather than leaving outdated commitments in place.

## A2 Completion Items

Before establishing the final A2 planning baseline, the team still needs to:

- complete `team-commitments.md`;
- complete `re-estimation.md`;
- reconcile the A2 scope and risk evidence with the current requirements;
- record required tradeoff or deferred-scope decision evidence;
- confirm A2 workflow evidence through GitHub Issues and related planning records;
- verify planning references are internally consistent;
- and establish the final `a2-planning-estimate` repository tag.