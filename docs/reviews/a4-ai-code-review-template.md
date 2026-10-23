# A4 AI-Assisted Code Review Template

Owner: Giancarlo Herrera — Quality & Review Lead
Purpose: Complete this for each substantial AI-assisted implementation PR. Record real evidence only.

## Identification
- PR, issue, branch, commit and changed files:
- Author and independent reviewer:
- AI tool/model and contributor disclosure:
- Generated or transformed material:
- Accepted AI output and human edits:
- Rejected AI output and reason:
- AI-use log entry:

## Review findings
| Review lens | Questions and evidence | Finding, severity, required fix |
|---|---|---|
| Correctness | Which REQ/AC is addressed? Are validation, transitions and failure paths correct? | |
| Security | Are session roles, student ownership, input validation and output encoding enforced? | |
| Architecture | Does code match Spring Boot, Thymeleaf, H2 and accepted ADR boundaries? | |
| Maintainability | Is code understandable, modular, and documented? | |
| Testing | Are positive, negative, boundary, restart and regression cases covered? | |
| Dependencies | Are package versions, licenses, maintenance and security risks checked? | |
| CI evidence | Is the exact commit checked by a real build/test workflow with linked results? | |
| Stewardship | Are rollback, limitations, owner and deferred risks clear? | |

## Resolution
- Reviewer comments and author responses:
- Correction commits and retest evidence:
- Remaining risks / follow-up issues:
- Decision: Approve, request changes, block, or defer
- Reviewer / date / evidence link:

Do not mark an AI-assisted change safe merely because it looks plausible. No implementation or passing test is asserted by this template.
