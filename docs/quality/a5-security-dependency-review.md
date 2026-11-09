# A5 Security and Dependency Review — Evidence and Open Decisions

**Project:** CampusConnect | **Team:** Team 2 — Codex Ramblers  
**Prepared by:** Giancarlo Herrera, Quality & Review Lead  
**Date:** 2026-11-09  
**Status:** Draft documentation review; no vulnerability scan, fresh Maven build, penetration test, or release approval performed in this review.  
**Reviewed baseline:** main at A4 commit `70d3726b64c0deafef2d770870bb14ef8f28b79b`; repeat against exact A5 release SHA.

## Observed configuration and historical evidence

| ID | Area | Observed repository evidence | Status / limitation |
|---|---|---|---|
| SEC-01 | Java and dependency management | `pom.xml` uses Java 17, Spring Boot parent 3.5.16 and managed web/JDBC/test/H2 dependencies. | Configuration observed; not a current vulnerability clearance. |
| SEC-02 | HTTP exposure | `application.properties` binds `server.address=127.0.0.1`, port 8080. | Loopback configuration observed; verify effective settings at runtime and prohibit Internet/real-data deployment. |
| SEC-03 | H2 exposure and persistence | `application.properties` configures `jdbc:h2:file:./data/campusconnect` and `spring.h2.console.enabled=false`. | File-backed configuration observed; not proof of backup/restore, filesystem permissions or final runtime behavior. |
| SEC-04 | Synthetic demo identity | `docs/testing/a4-http-api.md` documents fixed student-a, student-b and reviewer identities in server sessions, with forged headers/body ownership ignored. | Intentional local demo selector is **not production authentication**; reconcile contradictory PR #83 claims with implementation and tests (PR #87). |
| SEC-05 | Authorization and data isolation | `RequestControllerTest` includes negative tests for role, ownership, forged headers and missing context; A4 final quality review reports successful main CI and 35-test local integration simulation. | Historical automated evidence, not a fresh A5 exact-SHA run or independent security assessment. |
| SEC-06 | Write integrity | A4 backend verification reports stale-version rejection, transactional rollback, concurrent-write checks and packaged restart behavior. | Historical evidence; reverify exact A5 release revision and record any remaining recovery limitations. |
| SEC-07 | Dependency inventory/licenses | `docs/reviews/a4-build-dependency-assessment.md` records Spring Framework 6.2.19, H2 2.3.232, JUnit 5.12.2 and transitive examples (Jackson, Tomcat, Logback, HikariCP); direct library-family licenses reviewed. | Historical resolved graph; full transitive license/legal review not documented. |
| SEC-08 | CVE/advisory scanning | A4 assessment explicitly says **no CVE scanner executed** and no advisory/support sign-off. | **Open**; do not claim zero vulnerabilities or supported status without dated authoritative evidence. |
| SEC-09 | Credential/data handling | Demo instructions require synthetic data; local datasource configuration has username `sa` and blank password. | Acceptable only within explicitly restricted synthetic local demo context; never present as a secure shared/production deployment. Confirm database file access and handling. |
| SEC-10 | Release governance | A4 quality gate remains conditional; A5 PRs #85–#88 address evidence, limitations and acceptance gaps. | No independent A5 security approval or final release decision established by this review. |

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
| Check findings against requirements, risks and PRs #85–#88 | Giancarlo / Quality & Review Lead | Updated traceability and findings |
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

**AI disclosure:** ChatGPT assisted with source comparison and document drafting. Human review and an accurate AI-use-log entry are required before team acceptance.
