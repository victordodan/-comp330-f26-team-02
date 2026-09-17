# AI Verification Notes
# AI Verification Notes

## Verification Notes

The Codex Ramblers will use this file to document detailed verification of
significant AI-assisted engineering work when the verification cannot be
adequately described in the AI Use Log.

Not every use of AI requires a separate verification note. Verification notes
will be created when AI materially contributes to work with greater technical
risk, complexity, or impact on the project.

When a verification note is required, the team will use a unique identifier
in the format `AVN-###`.

## When to Create a Verification Note

A verification note may be created when AI significantly contributes to:

- security-related implementation;
- authentication or authorization;
- data integrity or database logic;
- important architecture decisions;
- complex implementation;
- significant requirements or acceptance criteria;
- important test design;
- debugging of significant defects;
- deployment or operational decisions; or
- other engineering work where incorrect AI output could significantly affect
  the project.

Routine or low-risk AI assistance normally does not require a separate
verification note.

## Verification Independence

AI-generated work should be verified using human judgment and appropriate
engineering evidence.

The team should not rely only on asking an AI system whether its own previous
answer was correct.

Depending on the work, verification may include code review, testing,
comparison with requirements, comparison with official technical
documentation, peer review, or observation of actual system behavior.

## Verification Depth

The amount of verification should match the potential impact of an incorrect
AI-generated result.

Low-risk assistance may only require human review or proofreading. More
important technical work may require code review, testing, documentation
comparison, or additional independent verification.

## Verification Against Requirements

When AI-assisted work affects system behavior, the team should connect the
verification to applicable requirements and acceptance criteria when
appropriate.

Important AI-assisted work should remain traceable to related project
documentation, implementation, testing, or review evidence.

## Failed Verification Is Valuable Evidence

AI recommendations that fail verification should not be hidden.

If verification identifies an incorrect assumption, defect, or unsuitable
recommendation, the team should record the result when it meaningfully affects
the project.

Rejected AI recommendations can provide evidence that the team independently
evaluated AI output rather than automatically accepting it.

## Expectations

- Create verification notes when significant AI-assisted work requires detailed
  verification evidence.
- Use unique `AVN-###` identifiers for verification notes.
- Connect verification notes to the related AI Use Log entry when applicable.
- Clearly describe what AI contributed.
- Clearly describe how a team member independently verified the work.
- Record whether the AI contribution was accepted, modified, or rejected.
- Identify remaining concerns or risks when applicable.
- Do not treat an AI system checking its own output as sufficient verification.
- Keep verification evidence concise and relevant.idence that AI was used.
-->
