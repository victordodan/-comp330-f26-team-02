# A4 Dependency and Supply-Chain Review

**Project:** CampusConnect | **Team:** Codex Ramblers — Team 2  
**Prepared by:** Giancarlo Herrera — Quality & Review Lead  
**Date:** October 23, 2026  
**Status:** Initial inventory and review procedure — **not a completed vulnerability scan or dependency approval**

## Scope and verified baseline

[ADR-002](../decisions/ADR-002-cycle-1-technology-stack.md) accepts Java, Spring Boot, Thymeleaf, Maven and file-backed H2 for the local Cycle 1 application. At this review, `src/` and `tests/` contain only README files; there is no application `pom.xml` or verified resolved dependency tree. The existing [CI evidence](../testing/ci-evidence.md) documents a deliberately failing starter guard, not a Maven build or dependency scan.

The root [LICENSE](../../LICENSE) grants educational use of the instructor's starter kit and states that student-created contributions are not automatically licensed by that starter-kit license. This review does not presume the team's application code has an open-source license.

## Planned technology inventory (not installed packages)

| Technology | Role in accepted architecture | Exact version | License evidence | Maintenance/security review | Status |
|---|---|---|---|---|---|
| Java / JDK | Language and runtime | Not selected in repository | Confirm selected JDK vendor and license | Check supported release and security update policy | Pending |
| Spring Boot | Web and application framework | Not pinned | Inspect selected artifacts and transitive licenses | Check supported version and published advisories | Pending |
| Thymeleaf | Server-side HTML templates | Not pinned | Inspect selected artifact license | Check maintained release and advisories | Pending |
| Maven | Build and dependency management | Not pinned | Confirm distribution and plugin licenses | Use supported Maven and audited plugins | Pending |
| H2 Database | Local file-backed storage | Not pinned | Inspect selected artifact license | Check advisories, file access, persistence/recovery limits | Pending |

**Important:** An accepted architecture is not proof that a package is installed or safe. Record exact artifact coordinates, versions and licenses only after a real Maven configuration and dependency resolution exist.

## Evidence required when implementation begins

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
| DEP-001 | Exact Java/framework/database versions and transitive packages are not yet available | Unsupported or vulnerable versions could be selected | Pin reviewed versions in first implementation PR and capture resolved dependency tree | Architecture/development lead — Open |
| DEP-002 | No dependency vulnerability or license scan evidence exists | Unknown vulnerabilities or licensing incompatibilities | Add and run an appropriate check; record results and limitations | Development/operations with Quality Lead review — Open |
| DEP-003 | Starter-kit license is not an automatic license for student-created code | Unclear redistribution rights | Victor/team to decide and document any separate project-code licensing before external distribution | Team Lead — Open |
| DEP-004 | CI is still a deliberate starter guard | No automated supply-chain or build visibility | Configure meaningful Maven build/tests and dependency checks, then link actual runs | Development/operations — Open |

## Gate criteria

- **Ready for implementation PR review:** Dependency additions and versions are declared, their source/license/maintenance risks documented, and review evidence is linked.
- **Ready for merge:** Independent reviewer examines dependency changes, real build/tests run, available vulnerability findings are triaged, and unresolved significant risks are explicitly escalated.
- **Not sufficient:** A proposed stack list, a clean-looking `pom.xml`, or this document alone. No dependency is marked approved, scanned, or vulnerability-free here.

Related: [Definition of Done](a4-definition-of-done.md) proposed in PR #63, [quality checklist](a4-quality-review-checklist.md) proposed in PR #61, and [traceability update](../planning/traceability.md) proposed in PR #62.
