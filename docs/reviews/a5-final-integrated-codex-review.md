# A5 final integrated technical review — Codex

Reviewed candidate: `2349a14dd3be6ad0cff2d407239f096434675ce8` on
`a5-release-authorization`. Reviewer: Codex, acting under Victor Dodan's
explicit authorization to perform the review. This report is not attributed
to Mareddy, Giancarlo or any other teammate and is not an independent human
signoff. The later commit containing this report is an evidence-only delta.

## Result

No additional blocking implementation finding was identified in the inspected
workflow for the approved loopback-only, synthetic-data classroom scope.
This is a scoped technical assessment, not absence-of-defects certification,
security clearance or a production release recommendation.

| Area inspected | Evidence and finding | Disposition |
|---|---|---|
| Identity / ownership | RequestController takes the fixed identity from HttpSession; WorkflowService enforces role checks and owner filtering. Client ownership is not used to create requests. DemoIdentity validates the fixed selector. | Appropriate to disclosed demo scope. Deliberate Reviewer selection remains accepted LIM-002; real authentication is absent. |
| Creation / validation | RequestValidator strips fields, checks title 5–100, description 10–2000 and the category allowlist; WorkflowService creates UUID, owner, Submitted status and timestamps server-side. | Supports TC-001/002/006 within recorded cases. Exact HTTP error-message assertion remains an accepted coverage limitation. |
| Lifecycle / notes | Only Submitted→In Progress, In Progress→Resolved and Resolved→In Progress are permitted; resolution requires an already saved note. Notes are bounded at 2000; resolved notes cannot be cleared. Reopening preserves immutable fields and note text. | Supports documented lifecycle. No combined status/note save is claimed. |
| Concurrency / writes | Quoted numeric If-Match required; stale read versions fail; SQL replacement also predicates on id and version and checks affected-row count. Service uses TransactionTemplate for database operations. | Conditional updates and rollback assertions support the bounded concurrent-save behavior; not a capacity/load certification. |
| Storage / recovery | JdbcRequestRepository uses parameterized JDBC and file-backed H2. Existing tests exercise insertion/update failure rollback, unavailable storage and same-store reopening. | Same-store retention is supported. Backup restoration and real-data operational recovery remain deferred LIM-001/005. |
| API errors | ApiExceptionHandler maps domain errors to 400/403/404/412/428 and storage failures to 503, returning structured errors. | Review covers documented failure paths; no claim that every malformed-body or unexpected runtime failure has been enumerated. |
| Build / CI | pom.xml targets Java17; CI runs reusable clean verify on main pushes, all PR changes and manual dispatch with read-only contents permission. CI #61 passed at the reviewed PR head. | Passing CI is verified, not inferred from a different SHA. Exact-main CI remains required after merge. |
| Acceptance / risk evidence | Case table and accountability section record all twelve scoped PASS decisions under Victor's delegation; #108/#109 approvals remain documentary. Release decision names risk owner, time/scope boundaries and deferred work. | Decisions are attributable and do not invent additional teammate approvals. |
| Dependencies / support / licenses | Resolved inventory and primary-source findings are linked; release decision accepts/defer named risks for A5 only and selects H2 MPL2.0. | No full-graph advisory scan or legal clearance is claimed; broader use remains unauthorized. |
| Submission artifacts | Prepared Word/PPTX reopen successfully; six students and six slides were checked during preparation. | Content preparation is recorded; visual presentation rehearsal and submission are not established by these checks. |

## Verification basis and limits

- [CI #61](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42772322319)
  completed successfully at the reviewed head.
- [Fresh integrated build](../../test-evidence/a5-release-authorization/build.json)
  records 35 passing tests at `5fab39a6b6fd64bf82756d5766c617f34b778430`.
- Application source, POM and demo script have no diff between that checkpoint,
  the earlier `531d54c` HTTP/restart source and the reviewed candidate.
- Source inspection included controller, service, JDBC repository, validator,
  identity enum, exception handler, runtime configuration, POM and CI entry
  workflow, with relevant ownership, spoofing, rollback, concurrency and
  persistence test assertions. This is not exhaustive adversarial analysis.
- No additional test execution, vulnerability scan, human approval, or tag is
  claimed by this review. Original evidence SHAs remain unchanged.

## Remaining independent-review requirement

Codex also assisted the implementation/evidence and release records. Therefore
this report cannot establish independence or satisfy an instructor requirement
for final non-author human review. Victor may use the findings for his recorded
conditional release decision, but a teammate's review must be attributed to
that teammate only when actually performed. Final integrated human review and
exact-main CI remain the pre-tag prerequisites recorded in the release decision.
