# CampusConnect

## Project Overview

CampusConnect is a student support request system being developed by Codex Ramblers for COMP 330. The project is intended to provide a simple workflow where a student can submit a support request and later view its status and resolution, while a support reviewer can view submitted requests, update their status, and record notes or resolution information.

CampusConnect focuses on one small end-to-end workflow rather than a complete university support platform. The project will use synthetic data and simulated user roles so that the system can be developed, reviewed, and tested without depending on real student information or live university systems.

## Project Status

**Current Phase Gate:** A1 — Project Launch  
**Release Cycle:** Cycle 1  
**Status:** Active Development

The team is currently establishing the initial engineering baseline for CampusConnect, with some documentation awaiting completion. Team processes, project scope, planning, risk evidence, AI-use governance, and other A1 artifacts are being completed before architecture and implementation begin.

Initial Requirements package and Initial Decision Record have not yet reached their final A1 state. Additional engineering evidence will be added as the project moves through later lifecycle stages.

## Team

The authoritative team roster, GitHub identities, specialized role ownership, backup responsibilities, and team acknowledgements are maintained in:

[`docs/team/roles.md`](docs/team/roles.md)

## Engineering Evidence

This repository is the authoritative engineering record for CampusConnect.

Engineering evidence is maintained throughout the repository:

- **AI Use and Verification** → [`docs/ai/`](docs/ai/)
- **Architecture** → [`docs/architecture/`](docs/architecture/)
- **Engineering Decisions** → [`docs/decisions/`](docs/decisions/)
- **Observability** → [`docs/observability/`](docs/observability/)
- **Operations** → [`docs/operations/`](docs/operations/)
- **Planning and Traceability** → [`docs/planning/`](docs/planning/)
- **Quality and Defects** → [`docs/quality/`](docs/quality/)
- **Release Evidence** → [`docs/release/`](docs/release/)
- **Requirements and Acceptance Criteria** → [`docs/requirements/`](docs/requirements/)
- **Engineering Reviews** → [`docs/reviews/`](docs/reviews/)
- **Security and Data Handling** → [`docs/security/`](docs/security/)
- **Team Evidence** → [`docs/team/`](docs/team/)
- **Testing and Verification** → [`docs/testing/`](docs/testing/)

Detailed evidence is maintained in its authoritative artifact rather than duplicated in this README.

## Repository Structure

The repository is organized to preserve both the CampusConnect software and the engineering evidence supporting it.

| Path | Purpose |
|---|---|
| `src/` | Application source code |
| `tests/` | Automated tests and supporting test code |
| `test-evidence/` | Preserved testing and verification evidence |
| `data/` | Synthetic sample, fixture, seed, or project data |
| `scripts/` | Development, verification, setup, or maintenance utilities |
| `docs/` | Lifecycle engineering evidence and project documentation |
| `.github/` | Issue templates, pull-request guidance, workflows, and repository automation |

## Build, Run, and Test

CampusConnect is currently in the A1 Project Launch phase. The technology stack and application architecture have not yet been finalized, so project-specific setup, build, run, and test commands are not yet available.

These instructions will be updated as architecture and implementation decisions are made. Commands will only be added once they represent the actual project environment.

### Prerequisites

Application-specific prerequisites have not yet been finalized.

Git and access to the project GitHub repository are currently required for repository-based engineering work.

### Setup

Application setup instructions are not yet available because implementation has not begun.

### Build

A project build procedure has not yet been established.

### Run

The CampusConnect application is not yet available to run during A1.

### Test

The automated test procedure will be documented as implementation and verification work begins.

Detailed testing evidence will be maintained under:

[`docs/testing/`](docs/testing/)

## Engineering Practices

CampusConnect uses repository-centered engineering practices, including:

- lifecycle-based engineering evidence;
- requirements and acceptance-criteria traceability;
- issue and pull-request workflows;
- documented architecture and engineering decisions;
- automated testing and verification as implementation develops;
- peer review;
- defect and quality management;
- security and data-handling evidence;
- responsible AI-assisted engineering;
- AI disclosure and human verification;
- release-readiness evidence; and
- continuous improvement throughout the semester.

Engineering evidence is to be created and maintained as work occurs rather than reconstructed immediately before a phase-gate submission.

## Engineering Evidence Model

Important engineering claims should be supported by traceable evidence.

A typical lifecycle relationship may develop as:

```text
Requirement
  ->
Acceptance Criterion
  ->
Architecture / Decision
  ->
Implementation
  ->
Test / Review
  ->
Verification Evidence
  ->
Release Evidence
```
## AI-Assisted Engineering

AI-assisted tools may be used during CampusConnect development, but the team remains responsible for understanding, reviewing, verifying, and defending all accepted work.

Significant AI-assisted work should follow the team's AI-Use Policy and be recorded when required. AI-generated output should not be accepted into project evidence or implementation without human review and verification.

Authoritative AI-use evidence is maintained under:

[`docs/ai/`](docs/ai/)

## Engineering Operating Model

COMP 330 uses three complementary environments:

- **Sakai** — the authoritative source for course assignments, deadlines, grading, and submission expectations.
- **ETIS** — a professional engineering reference used for broader engineering practices and guidance.
- **GitHub** — the authoritative engineering record for the team's project work, decisions, implementation, reviews, testing, and supporting evidence.

Sakai defines the course requirements, while GitHub preserves evidence of how Codex Ramblers carries out the engineering work.

## Professional Engineering Expectations

A reviewer examining this repository should be able to determine:

- what CampusConnect is intended to accomplish;
- what work is currently in and out of scope;
- who owns and contributes to project work;
- what requirements define expected behavior;
- what assumptions and risks remain;
- what engineering decisions were made and why;
- how planning and requirements connect to later implementation;
- what work was reviewed and tested;
- how AI-assisted work was disclosed and verified;
- what limitations remain; and
- how the project develops throughout the semester.

The project is intended to produce both a working system and repository-visible evidence showing how that system was planned, designed, built, reviewed, tested, and improved.

## Course and Professional Context

This repository was established from the **COMP 330/474 Fall 2026 Repository Starter Kit** for Software Engineering at Loyola University Chicago.

The Starter Kit provides the initial repository structure and engineering evidence model used throughout the course. Codex Ramblers team is responsible for replacing that initial scaffolding with project-specific CampusConnect evidence as development continues.

**Sakai remains authoritative for COMP 330 course requirements.**
