# A5 Security and Dependency Review — Evidence and Open Decisions

**Project:** CampusConnect | **Team:** Team 2 — Codex Ramblers
**Original preparer:** Giancarlo Herrera, Quality & Review Lead
**Status:** Draft documentation review; no vulnerability scan, fresh Maven build, penetration test, or release approval performed in this review.
**Reviewed checkpoint:** `4ff2f0f12e636f177ec25202b8029498e613afde`. Final A5 SHA/tag and risk decisions remain pending.

## Observed configuration and historical evidence

| ID | Area | Observed repository evidence | Status / limitation |
|---|---|---|---|
| SEC-01 | Java and dependency management | `pom.xml` uses Java 17, Spring Boot parent 3.5.16 and managed web/JDBC/test/H2 dependencies. | Configuration observed; not a current vulnerability clearance. |
| SEC-02 | HTTP exposure | `application.properties` binds `server.address=127.0.0.1`, port 8080. | Loopback configuration observed; verify effective settings at runtime and prohibit Internet/real-data deployment. |
| SEC-03 | H2 exposure and persistence | `application.properties` configures `jdbc:h2:file:./data/campusconnect` and `spring.h2.console.enabled=false`. | File-backed configuration observed; not proof of backup/restore, filesystem permissions or final runtime behavior. |
| SEC-04 | Synthetic demo identity | `docs/testing/a4-http-api.md` documents fixed student-a, student-b and reviewer identities in server sessions, with forged headers/body ownership ignored. | Merged #83/#94/#95 correct the descriptions; anyone reaching the selector can intentionally choose Reviewer. Header-spoofing denial is not protection against deliberate selector use. |
| SEC-05 | Authorization and data isolation | `RequestControllerTest` includes negative tests for role, ownership, forged headers and missing context; A4 final quality review reports successful main CI and 35-test local integration simulation. | E1/E2 below report passing automation. This is not independent penetration testing, final acceptance or production authentication. |
| SEC-06 | Write integrity | A4 backend verification reports stale-version rejection, transactional rollback, concurrent-write checks and packaged restart behavior. | Historical evidence; reverify exact A5 release revision and record any remaining recovery limitations. |
| SEC-07 | Dependency inventory/licenses | `docs/reviews/a4-build-dependency-assessment.md` records Spring Framework 6.2.19, H2 2.3.232, JUnit 5.12.2 and transitive examples (Jackson, Tomcat, Logback, HikariCP); direct library-family licenses reviewed. | Historical resolved graph; full transitive license/legal review not documented. |
| SEC-08 | CVE/advisory scanning | A4 assessment explicitly says **no CVE scanner executed** and no advisory/support sign-off. | **Open**; do not claim zero vulnerabilities or supported status without dated authoritative evidence. |
| SEC-09 | Credential/data handling | Demo instructions require synthetic data; local datasource configuration has username `sa` and blank password. | Use only within the restricted synthetic local demo scope; human risk acceptance is not asserted. Confirm effective runtime settings and database-file access; do not present as secure shared/production deployment. |
| SEC-10 | Release governance | Merged #87/#91 reconcile quality/traceability and #92 supplies execution evidence. #85/#86 remain candidate gate/release-note work. | No independent A5 security approval or final release decision established by this review. |

## Executed CI evidence and revision boundaries

- **E1 — PR #84 historical verification:** [run #41](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42613709038), `mvn -B clean verify`, 35 tests, zero failures/errors/skips. Tested merge `0fcc79c29dfcdc89403d3c230f47438cb00c358c`; head `00c034a74f22ce616f707848168909289b2db001`; base `70d3726b64c0deafef2d770870bb14ef8f28b79b`. Merged #92 records this evidence.
- **E2 — PR #95 integration verification:** [run #4](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42746026082) / [job logs](https://github.com/victordodan/-comp330-f26-team-02/actions/runs/42746026082/job/129598203601), `mvn -B clean verify`, 35 tests, zero failures/errors/skips and successful packaging. Tested merge `5d791a7b657209a6c688251149db92828b313606`; head `e724e84af257a69237d51f8b6dab3d1927bd8cfb`; base `6c6584162deb4c396f162670b39e4d48fdd548da`.

E2 used the consolidated CI configuration from merged #93. #95 subsequently
merged as `4ff2f0f12e636f177ec25202b8029498e613afde`. The tested synthetic
merge and final main commit are distinct revisions; no final A5 tag or release
approval is inferred. No new build was run to prepare this correction.

## Configuration boundaries

The POM at this checkpoint still specifies Java 17 and Spring Boot parent
3.5.16 with managed web/JDBC/test/H2 dependencies. Application defaults bind to
loopback and disable the H2 console, but environment/command-line settings can
override defaults. This review does not prove effective runtime configuration
or filesystem permissions. JDBC statements are parameterized; transactions and
version checks support consistency, without proving tested backup restoration.

## Dependency review inventory

The following are **reported by the historical A4 resolved dependency tree**, not independently re-resolved today:

| Component | Historical version | License evidence reported | A5 action |
|---|---|---|---|
| Spring Boot web/JDBC/test starters | 3.5.16 | Apache-2.0 upstream family | Verify support status and security advisories |
| Spring Framework | 6.2.19 | Apache-2.0 | Check applicable advisories |
| H2 | 2.3.232 | MPL-2.0 or EPL-1.0 | Confirm applicable license and advisories |
| JUnit Jupiter | 5.12.2 | EPL-2.0 | Confirm test dependency scope |
| Tomcat | 10.1.55 | Listed in A4 transitive tree | Verify current resolved version and advisories |
| Jackson | 2.21.4 | Listed in A4 transitive tree | Verify current resolved version and advisories |
| Logback | 1.5.34 | Listed in A4 transitive tree | Verify current resolved version and advisories |
| HikariCP | 6.3.3 | Listed in A4 transitive tree | Verify current resolved version and advisories |

## Outstanding security and supply-chain dispositions

| Item | Current disposition | Evidence needed / coordinator |
|---|---|---|
| Resolved dependency inventory | Historical A4 tree retained; not freshly resolved by this correction | Technical reviewer captures a tree for the selected release revision, including runtime/test/build scopes |
| Advisories/CVEs | Not assessed by a new scan here; no zero-CVE finding | Dated authoritative advisory check or scanner results with database/tool scope, applicable versions, triage and limitations |
| Transitive licenses | Partial direct-family review exists; complete signoff pending | Reviewer records complete transitive scope, applicable dual-license choices and unresolved obligations |
| Maintenance/support | Publication/build success does not establish support | Check authoritative support/advisory sources; record source date and exceptions |
| Demo identity/local database | Known synthetic-selector and local-store limits remain | Victor records bounded-demo residual-risk disposition; no real-data or public-service approval implied |
| Final release | Open | Named non-author review, exact revision, accepted limitations and release decision |

This is a classroom Cycle 1 evidence assessment. Do not expand it into an
unrequested production authentication system or legal certification. Missing
assurance needs actual evidence or an explicit human deferral/risk decision,
not a claim that passing Maven tests closes it.

## Required A5 security evidence

1. **Freeze revision:** Victor identifies the exact release SHA and scope; run checks against that revision, not merely historical A4 code.
2. **Build and dependency tree:** designated technical reviewer records `java -version`, `mvn -version`, `mvn -B clean verify`, and `mvn dependency:tree` output and CI URLs.
3. **Advisory scan:** run a supported dependency vulnerability scanner with dated output, note its advisory database and limitations, and triage findings by affected dependency, severity, reachability/context and fix/defer decision. **No scanner result is asserted here.**
4. **License review:** examine the complete resolved transitive dependency list, license notices and any unresolved/dual-license items; record the human reviewer and disposition.
5. **Access-control negatives:** rerun Student A/B isolation, forged `X-User-ID` / `X-User-Role`, missing session and student-to-reviewer access tests on the final revision; attach results.
6. **Local environment boundaries:** confirm loopback binding, disabled H2 console, synthetic-only data, and restricted local database file handling. Do not imply institutional login or production security.
7. **Integrity/recovery:** rerun persistence and concurrency tests and explicitly distinguish restart persistence from unverified backup restoration.
8. **Risk acceptance:** record any open advisories, unsupported components, missing scans, mitigations, named approver and Go/Conditional Go/No-Go rationale; update release notes and limitations.

## Suggested review ownership and disposition

| Action | Proposed owner | Approval evidence |
|---|---|---|
| Produce Maven, dependency and scan output | Shu / implementation lead or designated developer | Logs with exact SHA and date |
| Verify negative authorization and acceptance cases | Mareddy / test owner | Test/CI URLs and case records |
| Reconcile findings with merged #87/#91/#92/#95 and candidate #85/#86/#88 | Giancarlo / Quality & Review Lead | Updated traceability and findings |
| Approve exceptions, release scope and residual risk | Victor / team lead | Recorded human decision |

**Quality recommendation:** Security/dependency documentation is prepared, but final clearance remains **pending**. This review does not establish a vulnerability-free system or authorize use with real student information.

## Sources within repository

- [Build configuration](../../pom.xml)
- [Runtime configuration](../../src/main/resources/application.properties)
- [A4 dependency assessment](../reviews/a4-build-dependency-assessment.md)
- [A4 final quality verification](../testing/a4-final-quality-verification.md)
- [A4 backend verification](../testing/a4-backend-verification.md)
- [HTTP API and demo identity rules](../testing/a4-http-api.md)
- [Controller security test source](../../src/test/java/edu/loyola/campusconnect/controller/RequestControllerTest.java)
- [A5 defect/limitations review PR #87](https://github.com/victordodan/-comp330-f26-team-02/pull/87)

**AI disclosure:** Codex assisted this correction with source/configuration and repository-evidence comparison. No new vulnerability scan, dependency resolution, license acceptance or human security approval was performed. Original Giancarlo attribution does not imply he verified these corrections. See [AI-use log](../ai/ai-use-log.md).
