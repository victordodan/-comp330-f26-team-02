# Classroom permission boundaries

CampusConnect listens on loopback by default and uses synthetic data only.
The browser selects `student-a`, `student-b`, or `reviewer` through
`POST /api/demo/identity`. The controller stores the selected demo identity in
the server-side HTTP session. Any person who can access the local demo can
select Reviewer. This is the accepted classroom boundary.
[Application configuration](../../src/main/resources/application.properties) ·
[Request controller](../../src/main/java/edu/loyola/campusconnect/controller/RequestController.java)

| Capability | Student A or B | Reviewer |
|---|---|---|
| View portal and select an identity | Available locally | Available locally |
| Submit a request | Allowed. The selected student becomes the owner. | Denied |
| List or view student requests | Own records only | Uses reviewer endpoints |
| List or inspect the reviewer queue | Denied | Allowed across both students |
| Change request status or resolution note | Denied | Allowed subject to lifecycle, validation, and version checks |

The controller reads the identity from the session. The service enforces the role
and owner rules, and queries the repository by student for student lists.
Missing or disallowed identity produces HTTP 403. A student lookup of another
student's request produces HTTP 404, the same result as an unknown ID. Reviewer
updates require a current `If-Match` value. Missing, malformed, and stale values
produce 428, 400, and 412 respectively.
[Workflow service](../../src/main/java/edu/loyola/campusconnect/service/WorkflowService.java) ·
[Error mapping](../../src/main/java/edu/loyola/campusconnect/controller/ApiExceptionHandler.java) ·
[Controller tests](../../src/test/java/edu/loyola/campusconnect/controller/RequestControllerTest.java)

Use separate browser profiles when comparing simultaneous identities.
Selecting another identity changes the session used by tabs in the same
profile. Keep real credentials and student data outside the demo.
[Data handling notes](data-handling-notes.md) ·
[Known limitations](../release/known-limitations.md)
