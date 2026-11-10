# A5 Dependency and Residual-Risk Disposition — Candidate

**Checkpoint:** `ac228d07bc950251faee006b7ab98857fde81f43`; Java17/Maven3.9.9 dependency resolution completed locally.
**Scope:** application transitive dependency graph, including compile/runtime/test scopes.
**Coordinator:** designated technical reviewer; **decision:** Victor (pending).

Fresh `mvn dependency:tree -DoutputType=tgf` succeeded. The
[resolved graph](../../test-evidence/a5-dependencies/resolved-tree.tgf) and
[declared-license inventory](../../test-evidence/a5-dependencies/declared-licenses.csv)
contain 68 dependency nodes; 1 have unresolved declarations.
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
| CVE/advisory applicability | API access blocked | OPEN — no clearance; designated reviewer must assess or explicitly defer |
| Maintenance/support | Dependency versions and [prior assessment](../reviews/a4-build-dependency-assessment.md) | OPEN — verify dated authoritative support information; publication/build success is insufficient |
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
The CSV is derived evidence, not a scanner output. H2's POM declaration is
unresolved in this extraction; its historical dual-license assessment is not
silently substituted for a fresh complete license decision.
