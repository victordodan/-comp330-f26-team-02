
package edu.loyola.campusconnect.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/*
 * CampusConnect
 * COMP 330 - Assignment 6
 * Frontend integration testing
 *
 * These tests focus on the connection between the new browser
 * interface and the existing Spring Boot application.
 *
 * Keeping these separate from RequestControllerTest because
 * the existing controller tests already cover the main
 * backend workflow and its negative paths.
 */

@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:frontend-integration-tests;DB_CLOSE_DELAY=-1",
        "spring.sql.init.mode=always"
})
class FrontendIntegrationTest {

    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    void setUp() {

        // Use the full application context so Spring Boot's
        // static resource configuration is included in the tests.
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    void servesCampusConnectHomepage() throws Exception {

        // MockMvc records the welcome-page forward without dispatching it.
        // The direct index.html test verifies the forwarded resource body.
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("index.html"));
    }

    @Test
    void servesIndexHtmlDirectly() throws Exception {

        mvc.perform(get("/index.html"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(
                        MediaType.TEXT_HTML))
                .andExpect(content().string(
                        containsString("Welcome to CampusConnect")));
    }

    @Test
    void homepageReferencesRequiredFrontendAssets() throws Exception {

        // The page could load successfully even if its stylesheet
        // or JavaScript reference is missing. Check both references
        // so that an incomplete interface does not go unnoticed.
        mvc.perform(get("/index.html"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("href=\"styles.css\"")))
                .andExpect(content().string(
                        containsString("src=\"app.js\"")));
    }

    @Test
    void servesStylesheet() throws Exception {

        mvc.perform(get("/styles.css"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(
                        MediaType.parseMediaType("text/css")))
                .andExpect(content().string(
                        containsString(".identity-card")))
                .andExpect(content().string(
                        containsString(".request-card")));
    }

    @Test
    void servesJavaScriptFile() throws Exception {

        // Check that the actual application script is being served.
        // This does not execute JavaScript or prove that every
        // browser interaction works correctly.
        mvc.perform(get("/app.js"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("initializeApplication")))
                .andExpect(content().string(
                        containsString("apiRequest")));
    }

    @Test
    void homepageContainsSupportedDemoIdentities() throws Exception {

        // These identity values must stay consistent with
        // DemoIdentity.java and the identity selection endpoint.
        mvc.perform(get("/index.html"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("data-identity=\"student-a\"")))
                .andExpect(content().string(
                        containsString("data-identity=\"student-b\"")))
                .andExpect(content().string(
                        containsString("data-identity=\"reviewer\"")));
    }

    @Test
    void homepageContainsRequiredDashboardElements() throws Exception {

        // app.js relies on these IDs to populate the dashboard.
        // If an element is renamed later, the JavaScript may stop
        // working even though the HTML still loads successfully.
        mvc.perform(get("/index.html"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("id=\"identity-screen\"")))
                .andExpect(content().string(
                        containsString("id=\"dashboard\"")))
                .andExpect(content().string(
                        containsString("id=\"student-panel\"")))
                .andExpect(content().string(
                        containsString("id=\"request-form\"")))
                .andExpect(content().string(
                        containsString("id=\"request-list\"")))
                .andExpect(content().string(
                        containsString("id=\"request-card-template\"")));
    }

    @Test
    void homepageContainsFeedbackAndLoadingElements() throws Exception {

        // Need separate loading, empty, and notification states.
        // Otherwise an unavailable API could look like an empty
        // request list instead of an actual system failure.
        mvc.perform(get("/index.html"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("id=\"notification\"")))
                .andExpect(content().string(
                        containsString("id=\"loading-state\"")))
                .andExpect(content().string(
                        containsString("id=\"empty-state\"")));
    }

    @Test
    void homepageDisplaysClassroomReleaseWarning() throws Exception {

        // The frontend is publicly accessible within the running
        // application, but this is still a synthetic classroom demo.
        // Make sure the warning remains visible in the page source.
        mvc.perform(get("/index.html"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString(
                                "Classroom demonstration environment")))
                .andExpect(content().string(
                        containsString(
                                "This is not a production authentication system.")));
    }

    @Test
    void loadingHomepageDoesNotGrantApiAccess() throws Exception {

        MockHttpSession session = new MockHttpSession();

        // Accessing a public HTML page should not automatically
        // grant a student or reviewer identity.
        mvc.perform(get("/index.html").session(session))
                .andExpect(status().isOk());

        mvc.perform(get("/api/requests").session(session))
                .andExpect(status().isForbidden());

        mvc.perform(get("/api/reviewer/requests").session(session))
                .andExpect(status().isForbidden());
    }

    @Test
    void loadingJavaScriptDoesNotGrantReviewerAccess() throws Exception {

        MockHttpSession session = new MockHttpSession();

        // The JavaScript contains reviewer functionality, but
        // downloading it must not grant reviewer permissions.
        // Authorization still belongs to the Java backend.
        mvc.perform(get("/app.js").session(session))
                .andExpect(status().isOk());

        mvc.perform(get("/api/reviewer/requests").session(session))
                .andExpect(status().isForbidden());
    }

    @Test
    void studentIdentityCanAccessStudentApiButNotReviewerApi()
            throws Exception {

        MockHttpSession session = new MockHttpSession();

        mvc.perform(get("/index.html").session(session))
                .andExpect(status().isOk());

        // Simulate the identity selection request made by app.js.
        mvc.perform(post("/api/demo/identity")
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"identity\":\"student-a\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.identity")
                        .value("student-a"));

        mvc.perform(get("/api/requests").session(session))
                .andExpect(status().isOk());

        mvc.perform(get("/api/reviewer/requests").session(session))
                .andExpect(status().isForbidden());
    }

    @Test
    void reviewerIdentityCanAccessReviewerApiButNotStudentApi()
            throws Exception {

        MockHttpSession session = new MockHttpSession();

        mvc.perform(get("/index.html").session(session))
                .andExpect(status().isOk());

        mvc.perform(post("/api/demo/identity")
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"identity\":\"reviewer\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.identity")
                        .value("reviewer"));

        mvc.perform(get("/api/reviewer/requests").session(session))
                .andExpect(status().isOk());

        mvc.perform(get("/api/requests").session(session))
                .andExpect(status().isForbidden());
    }

    @Test
    void switchingDemoIdentityUpdatesApiPermissions()
            throws Exception {

        MockHttpSession session = new MockHttpSession();

        // The frontend allows users to switch identities without
        // restarting the application. Verify that permissions
        // follow the currently selected server-side identity.
        mvc.perform(post("/api/demo/identity")
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"identity\":\"reviewer\"}"))
                .andExpect(status().isOk());

        mvc.perform(get("/api/reviewer/requests").session(session))
                .andExpect(status().isOk());

        mvc.perform(post("/api/demo/identity")
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"identity\":\"student-b\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.identity")
                        .value("student-b"));

        mvc.perform(get("/api/requests").session(session))
                .andExpect(status().isOk());

        mvc.perform(get("/api/reviewer/requests").session(session))
                .andExpect(status().isForbidden());
    }

    @Test
    void missingFrontendResourceReturnsNotFound() throws Exception {

        // A missing frontend file should return a real error
        // rather than silently returning the homepage.
        mvc.perform(get("/missing-frontend-resource.js"))
                .andExpect(status().isNotFound());
    }

    /*
     * Remaining manual verification:
     *
     * MockMvc verifies HTTP responses and application integration,
     * but it does not behave like a real web browser.
     *
     * Still need to verify:
     *
     * - JavaScript execution and event listeners
     * - Actual form submission through the browser
     * - Dynamic request card rendering
     * - Status filtering and dashboard statistics
     * - Reviewer note and status updates
     * - Stale ETag conflict notifications
     * - Responsive layouts and keyboard navigation
     * - Persistence after restarting the application
     *
     * These results should be recorded separately in the
     * final A6 testing evidence once the frontend is integrated.
     */
}
