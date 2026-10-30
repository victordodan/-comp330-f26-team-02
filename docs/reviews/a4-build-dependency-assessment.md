# A4 build dependency assessment

Checkpoint: 2026-10-30 UTC. Scope: the dependency foundation proposed in PR #76 and reused by #73/#77/#71/#72. These are branch results, not a claim that the PRs are merged. Human dependency acceptance remains part of review.

## Selection and reproducibility

Spring Boot **3.5.16** replaces the proposed 3.2.5 baseline. Official Maven Central metadata listed it as the latest available stable 3.x patch during this check. This retains Java 17 and the Spring 3.x API line. Publication alone does not prove current community/commercial support or absence of vulnerabilities; support/advisory acceptance remains explicit below. No automatic 4.x migration is being claimed.

The Boot parent manages compatible versions; no independent Spring, Jackson, Tomcat, or H2 overrides are applied. Maven's source-activated `executable-app` profile runs Boot repackaging when `CampusConnectApplication.java` is present. The foundation branch without that entry point still produces an ordinary jar. The same POM is reused across dependent branches, avoiding conflicting prerequisite copies.

## Observed inventory and license evidence

| Dependency | Resolved version/scope | License/source |
|---|---|---|
| Boot web/JDBC starters | 3.5.16, compile | Apache-2.0; [Boot POM](https://repo.maven.apache.org/maven2/org/springframework/boot/spring-boot/3.5.16/spring-boot-3.5.16.pom) |
| Boot test starter | 3.5.16, test | Apache-2.0, same upstream project |
| Spring Framework | 6.2.19, compile | Apache-2.0; [Spring POM](https://repo.maven.apache.org/maven2/org/springframework/spring-core/6.2.19/spring-core-6.2.19.pom) |
| H2 | 2.3.232, runtime | MPL-2.0 or EPL-1.0; [H2 POM](https://repo.maven.apache.org/maven2/com/h2database/h2/2.3.232/h2-2.3.232.pom) |
| JUnit Jupiter | 5.12.2, test | EPL-2.0; [JUnit POM](https://repo.maven.apache.org/maven2/org/junit/jupiter/junit-jupiter/5.12.2/junit-jupiter-5.12.2.pom) |
| Maven Compiler / Surefire | 3.14.1 / 3.5.6, build | Apache Maven projects, Apache-2.0; versions managed by Boot parent |

The preserved [dependency tree](a4-build-dependency-tree.txt) records transitive libraries, including Jackson 2.21.4, Tomcat 10.1.55, Logback 1.5.34, and HikariCP 6.3.3. It was generated with `mvn dependency:tree` using Maven 3.9.9 and Java 17.0.21. Upstream license declarations were inspected for the direct library families above; this is not a complete legal review of every transitive component.

## Maintenance and security assessment

- Pinning the BOM makes review reproducible and avoids independently mismatching framework components. The selected patch is newer than the original 3.2.5 proposal; that change does not establish a clean vulnerability scan.
- HTTP parsing, JSON binding, logging and H2 remain dependency attack surfaces. The dependent application binds to loopback, disables the H2 console, accepts synthetic data only and uses parameterized JDBC. Fixed demo identities deliberately do not provide production authentication. No real student data or Internet deployment is supported by this review.
- Source-activated packaging, Java 17 compilation, and the complete dependency graph were exercised by actual Maven builds. Dependency publication metadata and build success cannot certify current upstream support. The reviewer must consider [Spring support information](https://spring.io/projects/spring-boot#support) and [Spring security advisories](https://spring.io/security) before accepting a longer-lived/deployed release. Those pages were not independently verified in this automated review.
- No CVE scanner was executed, no blanket 'zero vulnerabilities' claim is made, and maintenance/advisory acceptance is **pending human review**. This limitation must remain in the final A4 evidence or be replaced by the team's actual assessment; it is not closed by a green CI check.

## Verification and AI disclosure

The foundation passed Java 17 `mvn -B clean verify` with six validator tests and a normal jar. Dependent branches verify executable packaging and application behavior separately. CI links for each pushed revision belong in its PR description.

OpenAI Codex (GPT-6 family; exact deployment identifier unavailable) assisted the POM, assessment, and verification. Retained: managed dependencies and a build that succeeds before/after bootstrap. Rejected: treating a test-only green check as runnable-jar proof, a forced 4.x migration, and unsupported security/support claims. Human acceptance has not been fabricated.
