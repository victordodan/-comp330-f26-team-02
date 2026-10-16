# AI Use Log

## AI Use

This log records significant AI-assisted work that influenced the Codex Ramblers' project. AI use is documented when it contributes to project planning, documentation, development, testing, or other engineering evidence.
| **Date**   | **Tool** | **Purpose**                                           | **Artifact / Activity**                       | **Human Verification**                                                                                                                                                                                                 | **Result / Action**                                                                          |
| ---------- | -------- | ----------------------------------------------------- | --------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------- |
| 2026-10-15 | Codex | Help draft and simplify the A3 behavior baseline | `docs/requirements/requirements.md`; `acceptance-criteria.md`; `assumptions-open-questions.md` | The project submitter selected Java, server-rendered pages, local demonstration, and reopening; delegated remaining behavior choices; and requested preservation of the template and fewer criteria. Review of the resulting draft remains pending. | Recorded the behavior decisions using the existing document structure, retained the original seven acceptance IDs, and added five focused criteria. No implementation or executed tests are claimed. |
| 2026-09-17 | ChatGPT  | Help review and customize the team's AI use rules     | `/docs/ai/ai-policy.md`                       | Giancarlo Herrera reviewed the suggestions against the starter policy and Assignment 1 requirements, then selected and modified the wording before adding it to the repository                                         | Accepted after modification and used to customize the AI Use Policy                          |
| 2026-10-15 | Codex | Help align the PR #48 architecture overview and diagram | `docs/architecture/architecture.md` | The project submitter authorized corrections to PR #48 after integrating #47/#49. Human review of the final written overview remains pending. | Aligned the local Java/server-rendered design with merged #43, #44, #46, and #47; replaced the conflicting attachment with a versioned Mermaid diagram and corrected status, identity, API, AI, and baseline claims. No application tests, stack approval, or final A3 approval are claimed. |
| 2026-10-15 | Codex | Help finish data/context corrections in PR #49 | `docs/architecture/data-context.md` | The project submitter authorized these corrections and integration of PR #49 into PR #47. Independent human review of the final written revision remains pending. | Aligned the complete document with merged PR #43 and #46, corrected identity/API and role boundaries, and separated proposed operational logs from development AI disclosure. No application tests or final architecture approval are claimed. |
| 2026-09-17 | ChatGPT  | Help organize the team's initial AI use documentation | `/docs/ai/ai-use-log.md`                      | Giancarlo Herrera reviewed the suggested entries and confirmed that they described AI assistance that actually occurred during Assignment 1 work                                                                       | Accepted after modification and added to the AI Use Log                                      |
| 2026-09-28 | ChatGPT  | Help review A1 feedback and organize A2 planning work | Assignment 2 planning                         | Giancarlo Herrera compared the AI-assisted planning suggestions with the Assignment 2 instructions, A1 instructor feedback, and current repository evidence before using them to organize A2 work                       | Used as planning assistance; final responsibilities and repository changes remain team-owned |
| 2026-09-28 | ChatGPT  | Help update requirement traceability for A2 planning  | `/docs/planning/traceability.md`               | Giancarlo Herrera checked the suggested traceability changes against the current `requirements.md` and `acceptance-criteria.md`, preserved their Proposed status, and did not add task or estimate IDs that do not yet exist | Modified and used for the A2 traceability update                                             |
| 2026-09-29 | ChatGPT | Help document the Cycle 1 re-estimation process | `/docs/planning/re-estimation.md` | Giancarlo Herrera compared the suggested wording with the current three-point estimates and A2 planning evidence, confirmed that no material re-estimation had occurred, and revised the document after pull-request review requested clarification | Modified after human and peer review, then merged into the A2 planning evidence |
| 2026-09-29 | ChatGPT | Help evaluate and document an A2 engineering decision | `/docs/decisions/ADR-001-defer-production-technology-stack.md` | Giancarlo Herrera reviewed the proposed decision against the current project state and reviewer feedback. An earlier synthetic-data proposal was not used as the ADR because it represented an existing constraint rather than a genuine engineering choice. The final ADR instead documents the decision to defer the production technology stack | Initial approach rejected after review; revised ADR-001 was reviewed and merged |
| 2026-09-29 | ChatGPT | Help align the Cycle 1 scope with current A2 evidence | `/docs/planning/scope.md` | Giancarlo Herrera compared the proposed scope changes with REQ-001 through REQ-006, current planning evidence, and ADR-001 before updating the existing scope document | Modified, reviewed through the repository workflow, and merged |
| 2026-09-29 | ChatGPT | Help perform the final A2 planning quality review | A2 planning repository evidence | Giancarlo Herrera compared AI-assisted review suggestions against the Assignment 2 instructions, current repository artifacts, team work, and pull-request status before deciding which changes were necessary. Minor non-blocking suggestions were left unchanged rather than modifying teammates' work unnecessarily | Used as review assistance to identify required corrections and distinguish them from optional cleanup |
| 2026-10-14 | ChatGPT | Help develop the Assignment 3 testing approach and documentation | `/docs/testing/test-strategy.md` | Giancarlo Herrera reviewed the proposed test coverage against CampusConnect requirements REQ-001 through REQ-006, the proposed API contracts, and the Assignment 3 testing expectations. The document distinguishes planned tests from completed testing. | Used to draft the Cycle 1 Test Strategy; no tests were claimed as executed. |
| 2026-10-14 | ChatGPT | Help organize architecture review findings and follow-up actions | `/docs/reviews/architecture-review.md`; PR #43 | Giancarlo Herrera reviewed Maddy's component responsibilities and API contracts in PR #43 and submitted a GitHub review comment. AI helped organize the review into findings, requirements alignment, and follow-up actions. The overall architecture was not marked approved. | Used to document the initial architecture review; final cross-document review remains pending. |
| 2026-10-14 | ChatGPT | Help document significant AI assistance during Assignment 3 | `/docs/ai/ai-use-log.md` | Giancarlo Herrera compared the proposed entries with the AI-assisted work performed on the Test Strategy and Architecture Review and checked that the entries did not claim implementation or testing that had not occurred. | Used to update the A3 AI Use Log while preserving earlier entries. |
| 2026-10-15 | ChatGPT | Help compare proposed A3 requirements, API contracts, data/context design, and security checklist across open PRs | PRs #43, #45, #46, #47; `/docs/testing/test-strategy.md` | Giancarlo Herrera reviewed the source files and proposed acceptance checks in GitHub, then used the comparison to identify inconsistent status terminology, authentication claims, and test-status claims. | Updated proposed test coverage and documented cross-PR concerns; no test execution claimed. |
| 2026-10-15 | ChatGPT | Help organize and document cross-PR architecture review findings and follow-up actions | `/docs/reviews/architecture-review.md`; PR #44 | Giancarlo Herrera directed the review, checked the referenced PR evidence, and retained an In Progress outcome because the architecture overview, diagram, and team corrections were incomplete. | Added ARF-005–008 and ARA-004–007; final architecture approval remains pending. |
| 2026-10-15 | ChatGPT | Help extend requirement traceability to proposed A3 components, contracts, and planned verification | `/docs/planning/traceability.md`; PR #44 | Giancarlo Herrera used the team's open PRs and existing test strategy as the comparison basis, retaining proposed statuses and separating planned tests from executed evidence. | Updated A3 traceability with provisional API links, acceptance checks, and outstanding gaps. |

## What Should Be Logged?

The team will log AI use when it has a meaningful effect on project work. This can include AI assistance with requirements, project planning, documentation, engineering decisions, implementation, testing, debugging, risk analysis, or other important project evidence.

Small uses such as spelling corrections, formatting help, or simple syntax reminders generally do not need their own entry unless they meaningfully affect project work.

## Human Verification

AI-assisted work must be reviewed by a team member before it is accepted into the project. The verification should explain what the team member actually checked rather than simply stating that the work was reviewed.

Depending on the type of work, verification may include comparing the output with project requirements, reviewing code, running tests, checking technical documentation, or independently confirming the information.

## Result / Action

Each AI Use Log entry should explain what happened to the AI output after human review.

Possible results include accepting the output, modifying it before use, partially using it, rejecting it, using it only for brainstorming or analysis, or implementing and verifying the suggestion.

AI output is not automatically accepted into the project.

## Relationship to Verification Notes

For significant AI-assisted work that requires more detailed verification evidence, the team will reference the appropriate entry in:

`/docs/ai/ai-verification-notes.md`

Not every AI-assisted activity requires a separate verification note. Verification notes will be used when preserving additional verification evidence is important.

## Expectations

- Significant AI-assisted project work should be documented.
- Entries should identify the project artifact or activity affected by AI.
- Human verification should explain what was actually checked.
- Entries should state what happened to the AI-generated suggestion or output.
- Detailed verification evidence should be referenced when appropriate.
- AI activity should never be invented simply to make the log appear more complete.
- Passwords, credentials, confidential information, and other protected information should not be included.
- Entries should be brief while providing enough information to understand how AI affected the work.
