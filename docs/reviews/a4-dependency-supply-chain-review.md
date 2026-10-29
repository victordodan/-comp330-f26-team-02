# A4 Dependency and Supply-Chain Review

**Project:** CampusConnect | **Team:** Codex Ramblers — Team 2  
**Prepared by:** Giancarlo Herrera — Quality & Review Lead  
**Original review:** October 23, 2026  
**Evidence refresh:** October 29, 2026  
**Status:** Initial inventory and review procedure — **not a completed vulnerability scan or dependency approval**

## Scope and verified baseline

[ADR-002](../decisions/ADR-002-cycle-1-technology-stack.md) accepts Java, Spring Boot, Thymeleaf, Maven and file-backed H2 for the local Cycle 1 application. The October 23 baseline had no `pom.xml` or executable code. **As of October 29**, merged [PR #67](https://github.com/victordodan/-comp330-f26-team-02/pull/67) adds `pom.xml`, a Java request validator, six JUnit unit tests and a dedicated [GitHub Actions workflow](../../.github/workflows/a4-validation-tests.yml). The original [CI evidence](../testing/ci-evidence.md) still describes the separate starter guard; [A4 validation evidence](../testing/a4-request-validation-evidence.md) reports a successful focused test run. No resolved dependency tree, license audit or vulnerability scan was found in the reviewed evidence.

The root [LICENSE](../../LICENSE) grants educational use of the instructor's starter kit and states that student-created contributions are not automatically licensed by that starter-kit license. This review does not presume the team's application code has an open-source license.

## Declared and planned technology inventory (October 29 snapshot)

| Technology | Role in accepted architecture | Exact version | License evidence | Maintenance/security review | Status |
|---|---|---|---|---|---|
| Java / JDK | Compiler target and CI runtime | Java 17 (`maven.compiler.release=17`); CI Temurin 17 | Vendor/runtime license not independently audited | Verify installed JDK patch version and security policy | Declared; audit pending |
| Spring Boot | Web and application framework | Not pinned | Inspect selected artifacts and transitive licenses | Check supported version and published advisories | Pending |
| Thymeleaf | Server-side HTML templates | Not pinned | Inspect selected artifact license | Check maintained release and advisories | Pending |
| Maven | Build and dependency management | Maven executable version not pinned; `maven-compiler-plugin:3.13.0`, `maven-surefire-plugin:3.5.2` declared | Plugin and transitive licenses not independently audited | Check Maven/plugin support and advisories | Plugins declared; audit pending |
| H2 Database | Local file-backed storage | Not pinned | Inspect selected artifact license | Check advisories, file access, persistence/recovery limits | Pending |

**Verified from `pom.xml`:** `org.junit.jupiter:junit-jupiter:5.11.4` is the only declared direct dependency (test scope). Spring Boot, Thymeleaf and H2 remain planned but are **not declared in the current main-branch POM**. The two Maven plugins above are declared. No transitive dependency inventory, license review, vulnerability result or approved dependency risk disposition is claimed. An accepted architecture is not proof that a package is installed or safe.

## Evidence required for dependency approval

1. **Direct inventory:** Record each Maven `groupId:artifactId:version`, purpose, maintainer/source, license, and whether it is direct or transitive.
2. **Version reproducibility:** Commit the reviewed `pom.xml` and Maven wrapper or documented compatible Maven/JDK versions. Avoid unbounded version ranges and undocumented snapshots.
3. **Resolved tree:** Run `mvn dependency:tree` on the reviewed commit; save output or link a CI artifact. This command is **planned, not yet run**.
4. **Security review:** Use an approved dependency-vulnerability tool or advisory source; capture tool name/version, scan date, exact commit, findings, and remediation/defer decisions. Do not treat an unexecuted scan as clean.
5. **License review:** Inspect direct and relevant transitive licenses; document compatibility with intended educational distribution and distinguish starter-kit restrictions from student-authored code.
6. **Maintenance review:** Check support status, recent releases, security-fix availability and whether a dependency is abandoned.
7. **Supply-chain controls:** Review build plugins, transitive dependencies and external repositories; prefer trusted sources, least-privilege CI, pinned action revisions where feasible, and no committed credentials.
8. **Change control:** For every dependency change, link issue → branch → PR → reviewer → CI/test/scan results; record new risk and approval or rejection.

## Per-change review record

| Field | Evidence to enter |
|---|---|
| PR / issue / commit SHA | |
| Dependency / plugin / action and purpose | |
| Previous → proposed exact version | |
| Direct or transitive, origin and maintainer | |
| License and source link | |
| Maintenance/support status | |
| Advisory/scan source, date and result | |
| Dependency tree / lock or resolution evidence | |
| Build and test run URL, result | |
| Risk severity and mitigation / accepted exception | |
| Reviewer, review comment and decision | |

## Initial findings and risk register candidates

| ID | Finding | Risk | Recommended action | Owner / state |
|---|---|---|---|---|
| DEP-001 | Java 17 target, JUnit 5.11.4 and two Maven plugin versions are declared; Spring Boot, Thymeleaf, H2, Maven runtime version and transitive dependency tree remain unverified | Unsupported or vulnerable versions could be selected | Coordinate approved framework/database versions with Shu; capture resolved dependency tree on integrated build | Architecture/development lead — Open |
| DEP-002 | No dependency vulnerability or license scan evidence exists | Unknown vulnerabilities or licensing incompatibilities | Add and run an appropriate check; record results and limitations | Development/operations with Quality Lead review — Open |
| DEP-003 | Starter-kit license is not an automatic license for student-created code | Unclear redistribution rights | Victor/team to decide and document any separate project-code licensing before external distribution | Team Lead — Open |
| DEP-004 | Dedicated PR #67 Maven/JUnit CI passed, but the separate starter guard and full integrated application/dependency scan are not verified | Partial CI does not prove application integration or supply-chain safety | Reconcile CI workflows, run integrated build and dependency checks, and link exact results | Development/operations — Open |

## Gate criteria

- **Ready for implementation PR review:** Dependency additions and versions are declared, their source/license/maintenance risks documented, and review evidence is linked.
- **Ready for merge:** Independent reviewer examines dependency changes, real build/tests run, available vulnerability findings are triaged, and unresolved significant risks are explicitly escalated.
- **Not sufficient:** A proposed stack list, a clean-looking `pom.xml`, or this document alone. No dependency is marked approved, scanned, or vulnerability-free here.

Related: [Definition of Done](a4-definition-of-done.md) merged via PR #63, [quality checklist](a4-quality-review-checklist.md) merged via PR #61, and [traceability update](../planning/traceability.md) merged via PR #62. **Review disposition:** inventory updated from actual POM; licenses, dependency tree, advisories and full application integration remain pending. No dependency approval is asserted.
