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

| Artifact | Purpose | Primary Owner | Backup Owner | Current Status | Authoritative Location |
|---|---|---|---|---|---|
| Cycle 1 Scope | Defines the intended Cycle 1 vertical slice, project boundaries, constraints, dependencies, and deferred work. | Malec Tarabein | Shu Perez | Updated for A2 | [`scope.md`](scope.md) |
| Requirements Traceability | Connects Cycle 1 requirements and acceptance criteria to scope, tasks, owners, estimates, risks, and later verification evidence. | Giancarlo Herrera | Venkata Aravind Mareddy | Updated for A2 | [`traceability.md`](traceability.md) |
| Task Plan | Records major planned work, ownership, dependencies, targets, estimates, and status. | Shu Perez | Giancarlo Herrera | Updated for A2 | [`task-plan.md`](task-plan.md) |
| Effort Estimates | Records low, likely, and high estimates together with assumptions, confidence, dependencies, and re-estimation triggers. | Venkata Aravind Mareddy | Giancarlo Herrera | Updated for A2 | [`estimates.md`](estimates.md) |
| Risk Register | Records identified project risks, triggers, likelihood, impact, mitigation, contingency response, ownership, and status. | Shelby Sierah | Victor Dodan | Updated for A2 | [`risk-register.md`](risk-register.md) |
| Schedule and Milestones | Records major Cycle 1 milestones, dependencies, integration checkpoints, review windows, buffers, and schedule triggers. | Venkata Aravind Mareddy | Giancarlo Herrera | Updated for A2 | [`schedule.md`](schedule.md) |
| Planning Dependencies | Consolidates task, milestone, technical, risk, and schedule dependencies across the Cycle 1 plan. | Shu Perez | Shelby Sierah | Added for A2 | [`dependencies.md`](dependencies.md) |
| Team Commitments | Records who owns planned work, expected completion targets, and the evidence that will demonstrate completion. | Shu Perez | Victor Dodan | Added for A2 | [`team-commitments.md`](team-commitments.md) |
| Re-estimation Notes | Defines when estimates and commitments must be reconsidered and records material changes when new evidence requires them. | Malec Tarabein | Shu Perez | Added for A2 | [`re-estimation.md`](re-estimation.md) |

## Related Engineering Evidence

The planning package depends on and links to engineering evidence maintained elsewhere in the repository.

### Requirements

Current Cycle 1 requirements, acceptance criteria, assumptions, and open questions are maintained under:

[`../requirements/`](../requirements/)

The current requirements remain proposed planning inputs while team review and requirement-level uncertainty continue to be resolved.

### Engineering Decisions

Tradeoff decisions, deferred-scope rationale, and major engineering decisions are maintained under:

[`../decisions/`](../decisions/)

The current decision evidence includes the accepted deferral of the final production technology stack until the A3 architecture phase. Planning artifacts should not present an architecture or implementation choice as committed until the team has actually established that decision

### AI-Assisted Engineering

AI-use policy, significant AI-assisted work, and human verification evidence are maintained under:

[`../ai/`](../ai/)

Significant AI-assisted planning work must be reviewed and verified by team members before it is accepted as project evidence.

### GitHub Workflow Evidence

GitHub Issues, branches, pull requests, reviews, and repository-visible status updates provide workflow evidence for planned engineering work.

Issues should identify meaningful work, ownership, applicable requirement or planning relationships, and completion evidence where appropriate.

### Repository Workflow Links

- [GitHub Issues](https://github.com/victordodan/-comp330-f26-team-02/issues)
- [Project Board](link)

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

- establish the final `a2-planning-estimate` repository tag.

## A2 Planning Baseline

The A2 planning baseline is considered reviewable when the planning artifacts in this directory are mutually consistent, repository evidence is current, required ownership and dependency relationships are documented, and the final A2 repository tag is established.

The planning package remains living engineering evidence and should continue to be updated when later requirements, decisions, implementation evidence, risks, or re-estimation triggers materially change the plan.