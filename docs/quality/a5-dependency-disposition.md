# A5 Dependency and Residual-Risk Disposition — Candidate

**Checkpoint:** `ac228d07bc950251faee006b7ab98857fde81f43`; Java17/Maven3.9.9 dependency resolution completed locally.
**Scope:** application transitive dependency graph, including compile/runtime/test scopes.
**Coordinator:** designated technical reviewer; **decision:** Victor (pending).

Fresh `mvn dependency:tree -DoutputType=tgf` succeeded. The
[resolved graph](../../test-evidence/a5-dependencies/resolved-tree.tgf) and
[declared-license inventory](../../test-evidence/a5-dependencies/declared-licenses.csv)
contain 68 dependency nodes. The initial extraction left H2 unresolved; the
namespace correction below resolves its declaration in the staged CSV.
License metadata was read from resolved POMs, following available parent POMs
when declarations were inherited. Declarations do not establish which license
option was selected, notice obligations, distribution compatibility or legal approval.
The graph does not cover a complete build-plugin supply chain or an SBOM.

An H2 query to `https://api.osv.dev/v1/query` was attempted and rejected by the
workspace outbound proxy (CONNECT HTTP403; no advisory response). Consequently
**no advisory scan or zero-vulnerability result exists**. This is a tooling/access
limitation, not proof that H2 or another dependency is safe or vulnerable.
No credentials or real user data were sent. Use an accessible authoritative
advisory source/scanner in an approved environment and preserve dated results,
or record an explicit bounded-demo deferral and its risk owner.

| Decision | Evidence / scope | Current disposition |
|---|---|---|
| Dependency inventory | Fresh resolved graph | Recorded; reviewer checks final candidate matches |
| License declarations | POM-derived per-node CSV | Recorded metadata; obligations and dual-license choices need human decision |
| CVE/advisory applicability | OSV blocked; subsequent primary-source Tomcat findings below | OPEN — concrete findings require treatment; no full-graph clearance |
| Maintenance/support | Spring primary-source statement below: 3.5.16 is final OSS-supported 3.5 release | OPEN — support risk identified; upgrade or explicit human exception needed |
| Synthetic role selection (LIM-002) | Local sessions intentionally permit Reviewer selection | Human bounded-demo risk acceptance pending |
| Local database recovery (LIM-001) | Same-store restart passed; backup restoration unverified | Human decision: restrict synthetic scope, add verification or defer explicitly |

[Existing security assessment](a5-security-dependency-review.md) and
[limitations](../release/known-limitations.md) remain authoritative context.
No production authentication, legal license signoff, support clearance or release
approval is asserted. Each actual human decision must name approver, date,
scope, evidence and remaining mitigation; no approval is manufactured here.

## Reproduction / provenance

Executed locally with Temurin JDK17.0.17+10 and Maven3.9.9:
`mvn -B dependency:tree -DoutputType=tgf -DoutputFile=resolved-tree.tgf`.
Workspace-local repository/proxy settings enabled resolution; no application or
POM edits were made. The run completed successfully at 2026-11-10T05:04:56Z.
The CSV is derived evidence, not a scanner output. The initial H2 extraction
issue and its subsequent source-backed correction are documented below;
license declaration is distinct from a complete license decision.

## Subsequent primary-source risk review

The prior H2 declaration extraction missed its unqualified XML namespace. The
local resolved POM and [upstream license](https://h2database.github.io/html/license.html)
confirm MPL2.0 or EPL1.0; the CSV now records this. This closes the metadata gap,
not the license-choice/notice review. Preserve upstream licenses/disclaimers
when distributing the jar; no blanket legal approval is asserted.

[Spring's 3.5.16 announcement](https://spring.io/blog/2026/06/25/spring-boot-3-5-16-available-now/)
identifies it as the final OSS-supported 3.5 release. No commercial entitlement
is established. Support risk is therefore identified, not merely unknown.

[Apache's Tomcat advisories](https://tomcat.apache.org/security-10.html) list
10.1.55 within affected ranges. For example CVE-2026-55956 concerns default-servlet
security constraints; CVE-2026-55276 concerns incomplete logged effective web.xml;
10.1.56 fixes those issues. This is not an exhaustive advisory scan, and moving
to 10.1.56 alone is not current all-advisory clearance. Apache rates the latter
Low while [GitHub](https://github.com/advisories/GHSA-mqg3-r7h5-24x4) displays
Critical; preserve that discrepancy rather than treating a generic score as
proven application exploitability. Source defaults expose local HTTP with
application/service checks, without custom web.xml constraints, HTTP2/TLS,
clustering or RewriteValve configuration observed. That limits some paths but
does not establish immunity or a version-wide exception.

### Proposed treatment — no approval recorded

- Prefer a controlled compatible dependency/support update with repeat verification;
  assess the whole managed stack before overriding a single version. No POM change
  was made during this evidence pass.
- If the team defers for the bounded classroom release, Victor must explicitly
  record the named findings, synthetic/loopback restriction, duration, owner and
  follow-up. Deferral is proposed, not accepted. No public/real-data deployment.
- Human reviewer still needs remaining-dependency advisories and license/support
  dispositions; the blocked OSV API has not been replaced by an exhaustive scan.
- H2 license declaration is resolved; actual license election, notices and the
  other transitive obligations remain review work. Backup recovery and deliberate
  Reviewer selection likewise retain explicit bounded-risk decisions.

These sources were accessed during this local pass. Findings supersede the
prior generic support/advisory unknowns for the components reviewed, while the
historical inventory/proxy failure remains part of the evidence record.
