# A4 Changed-File Index — refreshed after implementation merges

**A3 baseline:** `a3-architecture-review` (`255c335343c242eade44181c3ba581157ec78bfe`)  
**Reviewed main:** `4851dff34a2ced725e5ffee51d569e0ba4924cdc` (PR #75 merged)  
**Source:** [GitHub compare](https://github.com/victordodan/-comp330-f26-team-02/compare/a3-architecture-review...main)  
**Scope:** 40 changed paths in this baseline-to-main comparison, **34 added and 6 modified**. PR #78 and this new follow-up PR are excluded until merged.

## Changed paths

| Path | Change |
|---|---|
| `.github/workflows/a4-validation-tests.yml` | Added |
| `.gitignore` | Modified |
| `README.md` | Modified |
| `docs/ai/a4-pr-fixes.md` | Added |
| `docs/ai/ai-use-log.md` | Modified |
| `docs/planning/traceability.md` | Modified |
| `docs/reviews/a4-ai-code-review-template.md` | Added |
| `docs/reviews/a4-build-dependency-assessment.md` | Added |
| `docs/reviews/a4-build-dependency-tree.txt` | Added |
| `docs/reviews/a4-code-review-findings.md` | Added |
| `docs/reviews/a4-definition-of-done.md` | Added |
| `docs/reviews/a4-dependency-supply-chain-review.md` | Added |
| `docs/reviews/a4-quality-review-checklist.md` | Added |
| `docs/reviews/architecture-review.md` | Modified |
| `docs/reviews/assignment4-changed-files.md` | Added |
| `docs/reviews/assignment4-review-package.md` | Added |
| `docs/testing/a4-backend-verification.md` | Added |
| `docs/testing/a4-http-api.md` | Added |
| `docs/testing/a4-request-validation-evidence.md` | Added |
| `docs/testing/test-cases.md` | Modified |
| `pom.xml` | Added |
| `src/main/java/edu/loyola/campusconnect/CampusConnectApplication.java` | Added |
| `src/main/java/edu/loyola/campusconnect/controller/ApiExceptionHandler.java` | Added |
| `src/main/java/edu/loyola/campusconnect/controller/RequestController.java` | Added |
| `src/main/java/edu/loyola/campusconnect/model/CreateRequestDto.java` | Added |
| `src/main/java/edu/loyola/campusconnect/model/DemoIdentity.java` | Added |
| `src/main/java/edu/loyola/campusconnect/model/SupportRequest.java` | Added |
| `src/main/java/edu/loyola/campusconnect/model/UpdateNoteDto.java` | Added |
| `src/main/java/edu/loyola/campusconnect/model/UpdateStatusDto.java` | Added |
| `src/main/java/edu/loyola/campusconnect/repository/JdbcRequestRepository.java` | Added |
| `src/main/java/edu/loyola/campusconnect/repository/RequestRepository.java` | Added |
| `src/main/java/edu/loyola/campusconnect/service/WorkflowException.java` | Added |
| `src/main/java/edu/loyola/campusconnect/service/WorkflowService.java` | Added |
| `src/main/java/edu/loyola/campusconnect/validation/RequestValidator.java` | Added |
| `src/main/resources/application.properties` | Added |
| `src/main/resources/schema.sql` | Added |
| `src/test/java/edu/loyola/campusconnect/CampusConnectApplicationTests.java` | Added |
| `src/test/java/edu/loyola/campusconnect/controller/RequestControllerTest.java` | Added |
| `src/test/java/edu/loyola/campusconnect/service/WorkflowServiceTest.java` | Added |
| `src/test/java/edu/loyola/campusconnect/validation/RequestValidatorTest.java` | Added |

## Why these paths changed

- **Executable feature and integration:** Java model, controller, service, JDBC repository, schema, application configuration and Spring Boot entry point implement the six request-workflow requirements; see merged PRs #69, #71, #72, #73.
- **Build and verification:** `pom.xml`, A4 workflow, validator/controller/service/startup tests and backend verification document compile, packaged HTTP, persistence and role/ownership checks; see #67, #76, #77 and [successful integrated main CI](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/41000100733).
- **Process and quality:** review templates, architecture follow-up, AI disclosures, traceability, dependency assessment and evidence index document decisions and risks; see #61–65, #74–75.
- **README/.gitignore:** local execution guidance and H2 generated-data exclusions.

## Selected unchanged files and disposition

- `.github/workflows/ci.yml`: original starter workflow remains unchanged; dedicated A4 workflow now performs the Java build. Verify desired long-term CI policy separately.
- `docs/release/known-limitations.md`: unchanged in comparison; release limitations should be reconciled with [final quality verification](../testing/a4-final-quality-verification.md) before submission.
- `docs/decisions/ADR-002-cycle-1-technology-stack.md`: unchanged architectural decision; compare actual Spring Boot/H2 implementation to decision before release sign-off.
- `docs/planning/risk-register.md`, `docs/planning/schedule.md`, `docs/planning/task-plan.md`: unchanged **as of this main commit** because A4 updates are still proposed in [PR #78](https://github.com/victordodan/-comp330-f26-team-02/pull/78).

Unchanged-path explanations are reviewer observations, not claims of explicit team approval. Re-run the comparison after #78 or any subsequent merge and update the final Word submission accordingly.
