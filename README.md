# CampusConnect

## Project Overview

CampusConnect is a student support request system built by Codex Ramblers for COMP 330. A student can submit a support request and later view its status and resolution, while a support reviewer can view submitted requests, update their status, and record notes or resolution information.

CampusConnect focuses on one small end-to-end workflow rather than a complete university support platform. It uses synthetic data and simulated user roles, so it can be developed, reviewed, and tested without real student information or live university systems.

## Project Status

**Current Phase Gate:** A5 — Cycle 1 Release and Presentation
**Release Cycle:** Cycle 1  
**Status:** Release candidate — **not yet approved or tagged**. The intended tag is `a5-cycle1-release`; the last tagged baseline is [`a4-construction-integration`](https://github.com/victordodan/-comp330-f26-team-02/releases/tag/a4-construction-integration).

Cycle 1 delivers a local HTTP API for the student-to-reviewer support-request workflow (REQ-001 to REQ-006), with server-session demo identities, ownership and role checks, validation, file-backed H2 persistence and optimistic version checks. Application source and the POM are unchanged since the A4 tag; A5 work is release evidence, CI, the demo script and documentation corrections (see [changes since A4](docs/release/changed-files-since-a4.md)).

**Not delivered in Cycle 1:** a browser user interface (the API is the interface), real authentication, notifications, verified backup restoration, and production deployment. These are recorded as limitations, not hidden. See [known limitations](docs/release/known-limitations.md).

### Cycle 1 release evidence — start here

| Question | Where to look |
|---|---|
| What is in the release package and what is still pending? | [A5 release package](docs/release/assignment5-release-package.md) |
| What shipped, what did not, and what risks remain? | [Release notes](docs/release/release-notes.md), [known limitations](docs/release/known-limitations.md) |
| Is it ready, and what blocks approval? | [Release-readiness review](docs/reviews/release-readiness-review.md), [quality gate](docs/quality/a5-final-release-quality-gate.md) |
| What was tested, and how? | [Final test evidence](docs/testing/final-test-evidence.md), [CI evidence](docs/testing/ci-evidence.md), [demo rehearsal](docs/testing/a5-demo-rehearsal.md), [`test-evidence/`](test-evidence/) |
| How do requirements trace to code, tests and release claims? | [Release traceability summary](docs/release/traceability-summary.md), [planning traceability](docs/planning/traceability.md) |
| What defects and risks are known? | [Defect log](docs/quality/defect-log.md), [risk register](docs/planning/risk-register.md) |
| What changed since A4? | [Changed-file index](docs/release/changed-files-since-a4.md) |
| How was AI used and verified? | [AI-use log](docs/ai/ai-use-log.md) |
| How is the demo run? | [Demo script](docs/release/demo-script.md) |

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
| `src/test/java/` | Executed Java unit and integration tests |
| `tests/` | Starter test scaffolding (unused; executed tests are in `src/test/java/`) |
| `test-evidence/` | Preserved testing and verification evidence |
| `data/` | Synthetic sample, fixture, seed, or project data |
| `scripts/` | Development, verification, setup, or maintenance utilities |
| `docs/` | Lifecycle engineering evidence and project documentation |
| `.github/` | Issue templates, pull-request guidance, workflows, and repository automation |

## Build, Run, and Test

### Prerequisites

- JDK 17 (`java -version`).
- Maven 3.6.3 or newer (`mvn -version`); local verification uses Maven 3.9.9.
- Maven Central access for initial dependency resolution.

Spring Boot 3.5.16 is pinned in the parent POM, which manages compatible framework, test-library, and build-plugin versions. The selected dependency versions and review limits are recorded in [the build dependency assessment](docs/reviews/a4-build-dependency-assessment.md).

### Verify and package

```sh
mvn -B clean verify
```

Maven runs the JUnit suite (35 tests at the recorded CI checkpoints; see [CI evidence](docs/testing/ci-evidence.md)) and packages the application. The same command runs in GitHub Actions through `.github/workflows/ci.yml`.

### Run locally

```sh
mvn spring-boot:run
```

Alternatively, after verification:

```sh
java -jar target/campusconnect-0.1.0-SNAPSHOT.jar
```

The application binds to `http://127.0.0.1:8080`. The root URL has no page, so HTTP 404 at `/` is expected; the workflow is exercised through the HTTP API described in the [HTTP API guide](docs/testing/a4-http-api.md). Stop it with Ctrl+C.

To run the scripted end-to-end demo (student and reviewer workflow plus negative cases), follow the [demo script](docs/release/demo-script.md), which uses `scripts/demo-requests.sh` (`bash scripts/demo-requests.sh --help`).

Records are stored in a file-backed H2 database under `./data/` relative to the working directory, so restarting from the same directory keeps them. It uses no institutional authentication, external university integration, or real student data; use synthetic data only and keep it on the local machine.

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

The Starter Kit provides the initial repository structure and engineering evidence model used throughout the course. Codex Ramblers has replaced most of that scaffolding with project-specific CampusConnect evidence. Some starter templates remain in `docs/operations/`, `docs/observability/` and `docs/security/`; they are not Cycle 1 release evidence, and completing them is planned A6 work (TASK-018 in the [task plan](docs/planning/task-plan.md)).

**Sakai remains authoritative for COMP 330 course requirements.**
