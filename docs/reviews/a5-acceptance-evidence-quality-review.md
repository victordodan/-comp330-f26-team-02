# A5 Quality Review — TC-001 through TC-012 Evidence Reconciliation

**Project:** CampusConnect — COMP 330 Team 2 (Codex Ramblers)  
**Prepared for:** Quality & Review Lead (Giancarlo Herrera), for human review  
**Review basis:** Repository records inspected November 9, 2026 (local demo record spans November 9–10 Chicago/UTC)  
**Status:** Draft review; no new test execution, case acceptance, or human signoff claimed  
**Release baseline:** Not frozen; no `a5-cycle1-release` tag established at review.

## Evidence examined and interpretation

- [Mareddy's A5 acceptance execution report](../testing/a5-acceptance-test-evidence.md): 35/35 JUnit tests passed in historical PR #84 CI; mapped related test methods to 12 cases, but explicitly recorded no separate dated case-specific acceptance executions.
- [A5 acceptance matrix](../testing/a5-acceptance-evidence-matrix.md): detailed AC-to-TC and automated test mapping; all A5 dispositions pending.
- [Test-case catalog](../testing/test-cases.md): authoritative scenario steps and detailed boundary variants; historical `Not Run` refers to missing case-specific execution records, not absence of automated tests.
- [Shu's packaged demo procedure](../release/demo-script.md), [rehearsal summary](../testing/a5-demo-rehearsal.md), and [raw HTTP transcript](../../test-evidence/a5-http-demo/workflow.jsonl): automated tool rehearsal, not a student-observed or approved acceptance run. Recorded 36 initial HTTP checks, 5 packaged restart checks, 36 repeat checks, and an intentional stopped-server failure control.
- [Rehearsal evidence README](../../test-evidence/a5-http-demo/README.md): describes transcripts, hashes, and limitations.

**Important distinction:** HTTP check counts are not acceptance-case counts. Related passing JUnit methods and an automated demo can support a final acceptance disposition; they do not automatically constitute a separately executed complete TC or an approved result. No additional manual execution is presumed necessary if existing evidence genuinely satisfies all steps and a reviewer explicitly documents that judgment.

## Case-by-case comparison

| Case / AC | Catalog requirement | Observed packaged-demo support | Existing JUnit support (Mareddy report) | Remaining evidence / disposition needed |
|---|---|---|---|---|
| TC-001 / AC-REQ-001-01 | Student A submits valid request; Submitted and ID | A/B creates return 201, distinct UUID, Submitted, session owner and ETag | Request creation and default status | Link actual run, final SHA and reviewer disposition |
| TC-002 / AC-REQ-001-02 | Blank, trimmed/out-of-range, unsupported values rejected without persistence | Blank title 400; owner listing unchanged | Validator boundaries and invalid create tests | Demo does not run all title 4/5/100/101, description 9/10/2000/2001, category and whitespace variants; reconcile JUnit assertions and final SHA |
| TC-003 / AC-REQ-002-01 | Student sees current status after reviewer updates/reopens | Owner GET after lifecycle and reopen | Lifecycle/reopen tests | Case-specific evidence link, final SHA and review |
| TC-004 / AC-REQ-002-02 | Edited latest resolution note visible and preserved after reopen | Reviewer note replacement; owner reads; reopen retains note | Note and reopen tests | Case-specific evidence link and review |
| TC-005 / AC-REQ-002-03 | Student B cannot list/read A's record; unknown ID | A list restricted; B direct lookup A returns 404; unknown ID exercised | Ownership/isolation tests | Verify full listing and direct-path variants, link results and review |
| TC-006 / AC-REQ-003-01 | Two unique IDs, owner/timestamps; failed storage write not falsely successful | A/B UUIDs and metadata visible; restart generates unique ID | Unique-ID, rollback and unavailable-storage tests | Injected storage failure is not part of demo; cite actual JUnit method/run and determine full scenario disposition |
| TC-007 / AC-REQ-003-02 | Saved status/note survives application restart with same store | Full JVM restart; 5 HTTP checks retain record/ETag and create distinct new ID | File-store restart test | Verify field-by-field expectations from restart transcript; final-release-SHA execution and approval; backup recovery not tested |
| TC-008 / AC-REQ-004-01 | Reviewer lists/inspects A and B | Reviewer list and inspect both | Reviewer visibility tests | Case record and review |
| TC-009 / AC-REQ-004-02 | Student denied reviewer actions; missing session/forged role fields | Missing identity 403; forged headers 403; student status edit 403; denied writes checked | Missing/wrong role, spoofing, isolation tests | Reconcile full invalid-session and supplied-body role/owner variants; synthetic Reviewer selector is not real authentication |
| TC-010 / AC-REQ-005-01 | Submitted -> In Progress -> Resolved with note -> In Progress; preserve note/ID | Demo lifecycle, note save, resolve and reopen | Transition and note preservation tests | Case record and final SHA |
| TC-011 / AC-REQ-005-02 | Invalid transitions, missing resolution, unknown ID, failed status write preserve state | Direct resolve rejected, missing note rejected, unknown UUID 404, missing/stale ETag rejected, unchanged-state checks | Invalid transitions, optimistic checks, rollback tests | Injected write failure not in HTTP demo; link JUnit failure-path evidence and final disposition |
| TC-012 / AC-REQ-006-01 | Save/replace note; >2000 characters, clearing while Resolved and failed write | Note save/replace; >2000 rejected; resolved clear rejected; unchanged-state checks | Note boundaries and rollback tests | Exact 2000/2001 and blank/whitespace variants plus injected write failure not all exercised by demo; reconcile JUnit and disposition |

## Priority evidence gaps

1. **TC-002:** The demo verifies a blank-title negative case, not the entire required field-length, trimming and category boundary set. Mareddy's related validator tests passed, but the final reviewer must check their assertions against each catalog step.
2. **TC-006, TC-011 and TC-012:** Database failure injection and transactional rollback are reported by JUnit methods, not the packaged HTTP demo. Identify tested SHA and observed assertions, and document whether coverage is sufficient or a separate exercise/deferral is required.
3. **TC-005 and TC-009:** The demo includes meaningful ownership and spoofing negatives, but the catalog asks for additional direct/list/update and supplied-context variants. Distinguish a simulated selectable Reviewer identity from actual authentication.
4. **TC-007:** Packaged restart with the same H2 store was demonstrated. This is stronger evidence than the older service-level restart alone, but it is not backup/restore or all-status recovery verification.
5. **All 12:** Associate any final case decision with exact release SHA, date, operator, environment, steps/assertions, links, uncovered steps and reviewer approval. Do not silently convert the historical 0/12 case-record count into 12/12 Pass.
6. **Final release:** Repeat/verify relevant CI and demo evidence against the frozen A5 SHA; obtain human acceptance and release decision.

## Proposed owner coordination

| Work | Proposed coordinator | Review output |
|---|---|---|
| Attach per-case evidence and actual dispositions | Mareddy / testing owner | Twelve case entries or evidence-backed explicit deferrals |
| Confirm demo transcript mapping and reproduction | Shu / demo owner | Script, run log, source SHA and missing variants |
| Reconcile coverage and challenge unsupported Pass claims | Giancarlo / Quality & Review Lead | Reviewed matrix and documented findings |
| Approve criteria/remaining deviations | Shelby and Victor as applicable | Attributable decision |
| Final release decision | Victor / authorized lead | Recorded SHA, accepted risks, approval |

## Recommended case record fields

For each TC, record: acceptance criterion; exact SHA; execution date and operator; environment/synthetic data; steps and actual results; automated/manual/mixed evidence; link(s); uncovered steps; Pass/Fail/Blocked/Deferred/Not Run **as a documented human disposition**; defect/retest; independent reviewer and approval date. A passing mapped test method alone is not a separately documented TC execution.

## Quality review conclusion

**Evidence is substantial but final acceptance remains pending.** The repository records 35 passing automated Java tests and a successful 36/5/36 packaged HTTP rehearsal. All 12 planned acceptance cases have related automated coverage, with the largest unclosed scenario-evidence questions involving input boundaries and injected write failures. Preserve the historical execution records and add exact-revision, evidence-backed human dispositions before claiming release acceptance.

**AI-use disclosure:** ChatGPT compared repository test catalogs, acceptance mappings, rehearsal summary, procedure and transcript, and drafted this review. No new tests were run, no user observation or independent verification is claimed, and no acceptance or release approval was performed. Human review and AI-use log update remain required.
