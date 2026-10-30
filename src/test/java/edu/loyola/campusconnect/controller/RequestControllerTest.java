package edu.loyola.campusconnect.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jayway.jsonpath.JsonPath;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:controller-contract-tests;DB_CLOSE_DELAY=-1",
        "spring.sql.init.mode=always"
})
class RequestControllerTest {
    @Autowired WebApplicationContext context;
    @Autowired JdbcTemplate jdbc;
    private MockMvc mvc;

    @BeforeEach
    void resetDatabaseAndMvc() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        jdbc.update("DELETE FROM support_request");
    }

    @Test
    void createsTrimmedStudentOwnedRequestAndReturnsCurrentEtag() throws Exception {
        MockHttpSession student = identity("student-a");
        MvcResult created = mvc.perform(post("/api/requests").session(student)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"  WiFi issue  \",\"category\":\" IT \","
                        + "\"description\":\"  Campus WiFi is not connecting.  \"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("ETag", "\"0\""))
                .andExpect(jsonPath("$.studentID").value("student-a"))
                .andExpect(jsonPath("$.title").value("WiFi issue"))
                .andExpect(jsonPath("$.category").value("IT"))
                .andExpect(jsonPath("$.description").value("Campus WiFi is not connecting."))
                .andExpect(jsonPath("$.status").value("Submitted"))
                .andExpect(jsonPath("$.resolutionNote").value(""))
                .andExpect(jsonPath("$.version").value(0)).andReturn();
        Map<String, Object> request = body(created);
        assertNotNull(request.get("createdAt"));
        assertEquals(request.get("createdAt"), request.get("updatedAt"));
        mvc.perform(get("/api/requests/{id}", request.get("id")).session(student))
                .andExpect(status().isOk()).andExpect(header().string("ETag", "\"0\""));
        assertEquals(request, ownRequest(student, id(created)));
    }

    @Test
    void rejectsInvalidCreationWithoutStoringAnything() throws Exception {
        MockHttpSession student = identity("student-a");
        for (String invalid : List.of("{}", "{\"title\":\"bad\",\"category\":\"IT\","
                + "\"description\":\"Valid description\"}",
                "{\"title\":\"Valid title\",\"category\":\"Sports\",\"description\":\"Valid description\"}",
                "{\"title\":\"Valid title\",\"category\":\"IT\",\"description\":\"   \"}")) {
            mvc.perform(post("/api/requests").session(student).contentType(MediaType.APPLICATION_JSON)
                    .content(invalid)).andExpect(status().isBadRequest());
        }
        assertEquals(0, count());
    }

    @Test
    void suppliedServerFieldsCannotForgeOwnerOrGrantReviewerRole() throws Exception {
        MockHttpSession student = identity("student-a");
        MvcResult result = mvc.perform(post("/api/requests").session(student)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Valid title\",\"category\":\"IT\",\"description\":\"Valid description\","
                        + "\"studentID\":\"student-b\",\"role\":\"reviewer\",\"status\":\"Resolved\",\"id\":\"forged\"}"))
                .andReturn();
        // Both rejecting unknown fields and ignoring them are safe DTO-binding policies.
        int responseStatus = result.getResponse().getStatus();
        assertTrue(responseStatus == 400 || responseStatus == 201);
        if (responseStatus == 201) {
            assertEquals("student-a", body(result).get("studentID"));
            assertEquals("Submitted", body(result).get("status"));
            assertNotEquals("forged", id(result));
        } else {
            assertEquals(0, count());
        }
        assertEquals("student-a", student.getAttribute(RequestController.DEMO_IDENTITY));
        mvc.perform(get("/api/reviewer/requests").session(student)).andExpect(status().isForbidden());
        mvc.perform(get("/api/requests").session(identity("student-b")))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void legacyClientStudentIdIsIgnoredWhenAssigningTheSessionOwner() throws Exception {
        MockHttpSession a = identity("student-a");
        mvc.perform(post("/api/requests").session(a).contentType(MediaType.APPLICATION_JSON)
                .content("{\"studentID\":\"student-b\",\"title\":\"Valid title\","
                        + "\"category\":\"IT\",\"description\":\"Valid description\"}"))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.studentID").value("student-a"));
        mvc.perform(get("/api/requests").session(identity("student-b")))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(0)));
        assertEquals(1, count());
    }

    @Test
    void missingInvalidAndWrongRoleContextsAreDenied() throws Exception {
        mvc.perform(get("/api/requests")).andExpect(status().isForbidden());
        mvc.perform(get("/api/reviewer/requests")).andExpect(status().isForbidden());
        mvc.perform(post("/api/requests").contentType(MediaType.APPLICATION_JSON).content(validRequest()))
                .andExpect(status().isForbidden());
        for (Object invalid : List.of("administrator", 42)) {
            MockHttpSession session = new MockHttpSession();
            session.setAttribute(RequestController.DEMO_IDENTITY, invalid);
            mvc.perform(get("/api/requests").session(session)).andExpect(status().isForbidden());
            mvc.perform(get("/api/reviewer/requests").session(session)).andExpect(status().isForbidden());
        }
        mvc.perform(post("/api/demo/identity").contentType(MediaType.APPLICATION_JSON)
                .content("{\"identity\":\"administrator\"}")).andExpect(status().isForbidden());
        MockHttpSession reviewer = identity("reviewer");
        mvc.perform(post("/api/requests").session(reviewer).contentType(MediaType.APPLICATION_JSON)
                .content(validRequest())).andExpect(status().isForbidden());
        mvc.perform(get("/api/requests").session(reviewer)).andExpect(status().isForbidden());
        assertEquals(0, count());
    }

    @Test
    void studentsSeeOnlyOwnedRequestsAndCannotReadOrChangeReviewerData() throws Exception {
        MockHttpSession a = identity("student-a"), b = identity("student-b");
        MvcResult createdA = create(a), createdB = create(b);
        String idA = id(createdA), idB = id(createdB);
        mvc.perform(get("/api/requests").session(a)).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].id").value(idA));
        mvc.perform(get("/api/requests").session(b)).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].id").value(idB));
        MvcResult denied = mvc.perform(get("/api/requests/{id}", idA).session(b))
                .andExpect(status().isNotFound()).andReturn();
        assertFalse(denied.getResponse().getContentAsString().contains("Valid title"));
        for (MockHttpSession student : List.of(a, b)) {
            mvc.perform(get("/api/reviewer/requests").session(student)).andExpect(status().isForbidden());
            mvc.perform(get("/api/reviewer/requests/{id}", idA).session(student))
                    .andExpect(status().isForbidden());
            statusChange(student, idA, "\"0\"", "In Progress", 403);
            noteChange(student, idA, "\"0\"", "Attempted student note", 403);
        }
        assertEquals(body(createdA), ownRequest(a, idA));
        assertEquals(body(createdB), ownRequest(b, idB));
    }

    @Test
    void reviewerCanInspectBothStudentsButCannotUseStudentOnlyRetrieval() throws Exception {
        MockHttpSession a = identity("student-a"), b = identity("student-b"), reviewer = identity("reviewer");
        String idA = id(create(a)), idB = id(create(b));
        mvc.perform(get("/api/reviewer/requests").session(reviewer)).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
        for (String requestId : List.of(idA, idB)) {
            mvc.perform(get("/api/reviewer/requests/{id}", requestId).session(reviewer))
                    .andExpect(status().isOk()).andExpect(header().string("ETag", "\"0\""));
            mvc.perform(get("/api/requests/{id}", requestId).session(reviewer))
                    .andExpect(status().isForbidden());
        }
    }

    @Test
    void resolvingRequiresSavedNoteAndReopeningPreservesImmutableFieldsAndText() throws Exception {
        MockHttpSession student = identity("student-a"), reviewer = identity("reviewer");
        MvcResult created = create(student);
        String id = id(created);
        statusChange(reviewer, id, "\"0\"", "Resolved", 400);
        assertEquals(body(created), ownRequest(student, id));
        MvcResult inProgress = statusChange(reviewer, id, "\"0\"", "In Progress", 200);
        statusChange(reviewer, id, "\"1\"", "Resolved", 400);
        mvc.perform(patch("/api/requests/{id}/status", id).session(reviewer).header("If-Match", "\"1\"")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"status\":\"Resolved\",\"resolutionNote\":\"Inline resolution must not bypass saved-note rule\"}"))
                .andExpect(status().isBadRequest());
        assertEquals(body(inProgress), ownRequest(student, id));
        MvcResult note = noteChange(reviewer, id, "\"1\"", "  Saved resolution  ", 200);
        assertEquals("Saved resolution", body(note).get("resolutionNote"));
        MvcResult resolved = statusChange(reviewer, id, "\"2\"", "Resolved", 200);
        noteChange(reviewer, id, "\"3\"", "   ", 400);
        assertEquals(body(resolved), ownRequest(student, id));
        MvcResult reopened = statusChange(reviewer, id, "\"3\"", "In Progress", 200);
        Map<String, Object> after = body(reopened), original = body(created);
        assertEquals("In Progress", after.get("status"));
        assertEquals("Saved resolution", after.get("resolutionNote"));
        assertEquals(4, after.get("version"));
        for (String field : List.of("id", "studentID", "title", "category", "description", "createdAt")) {
            assertEquals(original.get(field), after.get(field), field + " must be immutable");
        }
        assertNotEquals(original.get("updatedAt"), after.get("updatedAt"));
        assertEquals(after, ownRequest(student, id));
    }

    @Test
    void notesRespectTrimmedBoundaryAndCanBeClearedOnlyBeforeResolution() throws Exception {
        MockHttpSession student = identity("student-a"), reviewer = identity("reviewer");
        String id = id(create(student));
        MvcResult maximum = noteChange(reviewer, id, "\"0\"", "  " + "n".repeat(2000) + "  ", 200);
        assertEquals("n".repeat(2000), body(maximum).get("resolutionNote"));
        noteChange(reviewer, id, "\"1\"", "n".repeat(2001), 400);
        assertEquals(body(maximum), ownRequest(student, id));
        MvcResult cleared = noteChange(reviewer, id, "\"1\"", "  ", 200);
        assertEquals("", body(cleared).get("resolutionNote"));
        statusChange(reviewer, id, "\"2\"", "In Progress", 200);
        noteChange(reviewer, id, "\"3\"", "", 200);
        noteChange(reviewer, id, "\"4\"", "Saved before resolve", 200);
        statusChange(reviewer, id, "\"5\"", "Resolved", 200);
        MvcResult replaced = noteChange(reviewer, id, "\"6\"", "Replacement while resolved", 200);
        assertEquals("Resolved", body(replaced).get("status"));
        assertEquals("Replacement while resolved", ownRequest(student, id).get("resolutionNote"));
        noteChange(reviewer, id, "\"7\"", "", 400);
        assertEquals(body(replaced), ownRequest(student, id));
    }

    @Test
    void updatePreconditionsRejectMissingMalformedAndStaleVersionsWithoutChanges() throws Exception {
        MockHttpSession student = identity("student-a"), reviewer = identity("reviewer");
        MvcResult created = create(student);
        String id = id(created);
        mvc.perform(patch("/api/requests/{id}/status", id).session(reviewer)
                .contentType(MediaType.APPLICATION_JSON).content("{\"status\":\"In Progress\"}"))
                .andExpect(status().is(428));
        for (String invalid : List.of("0", "\"-1\"", "W/\"0\"", "*", "\"0\", \"1\"", "\"999999999999999999999999999\"")) {
            statusChange(reviewer, id, invalid, "In Progress", 400);
        }
        assertEquals(body(created), ownRequest(student, id));
        MvcResult updated = statusChange(reviewer, id, "\"0\"", "In Progress", 200);
        assertEquals("\"1\"", updated.getResponse().getHeader("ETag"));
        noteChange(reviewer, id, "\"0\"", "Stale note must not overwrite", 412);
        statusChange(reviewer, id, "\"0\"", "Resolved", 412);
        assertEquals(body(updated), ownRequest(student, id));
    }

    @Test
    void unknownIdsMalformedJsonAndUnsupportedTransitionsProduceClearErrors() throws Exception {
        MockHttpSession student = identity("student-a"), reviewer = identity("reviewer");
        mvc.perform(get("/api/requests/unknown").session(student)).andExpect(status().isNotFound());
        mvc.perform(get("/api/reviewer/requests/unknown").session(reviewer)).andExpect(status().isNotFound());
        statusChange(reviewer, "unknown", "\"0\"", "In Progress", 404);
        noteChange(reviewer, "unknown", "\"0\"", "Unknown request", 404);
        mvc.perform(post("/api/requests").session(student).contentType(MediaType.APPLICATION_JSON).content("{"))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.code").value("BAD_INPUT"));
        assertEquals(0, count());
        MvcResult created = create(student);
        String id = id(created);
        for (String invalid : List.of("New", "Closed", "In Review", "Submitted")) {
            statusChange(reviewer, id, "\"0\"", invalid, 400);
        }
        mvc.perform(patch("/api/requests/{id}/status", id).session(reviewer).header("If-Match", "\"0\"")
                .contentType(MediaType.APPLICATION_JSON).content("{"))
                .andExpect(status().isBadRequest());
        assertEquals(body(created), ownRequest(student, id));
    }

    @Test
    void spoofedIdentityHeadersCannotGrantPermissionsOrOverrideTheSession() throws Exception {
        mvc.perform(get("/api/reviewer/requests")
                .header("X-User-Role", "REVIEWER").header("X-User-ID", "reviewer"))
                .andExpect(status().isForbidden());
        mvc.perform(post("/api/requests").header("X-User-Role", "STUDENT")
                .header("X-User-ID", "student-a").contentType(MediaType.APPLICATION_JSON)
                .content(validRequest())).andExpect(status().isForbidden());
        MockHttpSession a = identity("student-a"), b = identity("student-b");
        MvcResult createdA = create(a);
        create(b);
        mvc.perform(get("/api/requests").session(a).header("X-User-ID", "student-b")
                .header("X-User-Role", "REVIEWER"))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(id(createdA)));
        mvc.perform(patch("/api/requests/{id}/status", id(createdA)).session(a)
                .header("X-User-Role", "REVIEWER").header("If-Match", "\"0\"")
                .contentType(MediaType.APPLICATION_JSON).content("{\"status\":\"In Progress\"}"))
                .andExpect(status().isForbidden());
        assertEquals(body(createdA), ownRequest(a, id(createdA)));
    }

    @Test
    void unavailableStorageReturnsFailureWithoutFalseSuccessOrRecordChanges() throws Exception {
        MockHttpSession a = identity("student-a");
        MvcResult created = create(a);
        jdbc.execute("ALTER TABLE support_request RENAME TO unavailable_support_request");
        try {
            mvc.perform(post("/api/requests").session(a).contentType(MediaType.APPLICATION_JSON)
                    .content(validRequest())).andExpect(status().isServiceUnavailable())
                    .andExpect(jsonPath("$.code").value("STORAGE_ERROR"));
            mvc.perform(get("/api/requests").session(a)).andExpect(status().isServiceUnavailable())
                    .andExpect(jsonPath("$.code").value("STORAGE_ERROR"));
        } finally {
            jdbc.execute("ALTER TABLE unavailable_support_request RENAME TO support_request");
        }
        assertEquals(1, count());
        assertEquals(body(created), ownRequest(a, id(created)));
    }

    @Test
    void twoReviewersCannotSaveChangesBasedOnTheSameLoadedVersion() throws Exception {
        MockHttpSession student = identity("student-a"), first = identity("reviewer"), second = identity("reviewer");
        String id = id(create(student));
        for (MockHttpSession reviewer : List.of(first, second)) {
            mvc.perform(get("/api/reviewer/requests/{id}", id).session(reviewer))
                    .andExpect(status().isOk()).andExpect(header().string("ETag", "\"0\""));
        }
        MvcResult saved = statusChange(first, id, "\"0\"", "In Progress", 200);
        noteChange(second, id, "\"0\"", "Second reviewer stale edit", 412);
        assertEquals(body(saved), ownRequest(student, id));
    }

    private MockHttpSession identity(String identity) throws Exception {
        MockHttpSession session = new MockHttpSession();
        mvc.perform(post("/api/demo/identity").session(session).contentType(MediaType.APPLICATION_JSON)
                .content("{\"identity\":\"" + identity + "\"}"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.identity").value(identity));
        return session;
    }

    private MvcResult create(MockHttpSession student) throws Exception {
        return mvc.perform(post("/api/requests").session(student).contentType(MediaType.APPLICATION_JSON)
                .content(validRequest())).andExpect(status().isCreated()).andReturn();
    }

    private MvcResult statusChange(MockHttpSession reviewer, String id, String version,
            String status, int expected) throws Exception {
        return mvc.perform(patch("/api/requests/{id}/status", id).session(reviewer).header("If-Match", version)
                .contentType(MediaType.APPLICATION_JSON).content("{\"status\":\"" + status + "\"}"))
                .andExpect(status().is(expected)).andReturn();
    }

    private MvcResult noteChange(MockHttpSession reviewer, String id, String version,
            String note, int expected) throws Exception {
        return mvc.perform(patch("/api/requests/{id}/note", id).session(reviewer).header("If-Match", version)
                .contentType(MediaType.APPLICATION_JSON).content("{\"resolutionNote\":\"" + note + "\"}"))
                .andExpect(status().is(expected)).andReturn();
    }

    private Map<String, Object> ownRequest(MockHttpSession student, String id) throws Exception {
        return body(mvc.perform(get("/api/requests/{id}", id).session(student)).andExpect(status().isOk()).andReturn());
    }

    private static Map<String, Object> body(MvcResult result) throws Exception {
        return JsonPath.parse(result.getResponse().getContentAsString()).read("$");
    }

    private static String id(MvcResult result) throws Exception { return (String) body(result).get("id"); }
    private int count() { return jdbc.queryForObject("SELECT COUNT(*) FROM support_request", Integer.class); }
    private static String validRequest() {
        return "{\"title\":\"Valid title\",\"category\":\"IT\",\"description\":\"Valid description\"}";
    }
}
