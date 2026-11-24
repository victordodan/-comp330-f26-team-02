
/*
 * CampusConnect
 * COMP 330 - Assignment 6
 * Browser interface and REST API integration
 *
 * The frontend should handle presentation and user interaction.
 * Business rules, permissions, and persistence should remain
 * the responsibility of the existing Java backend.
 */

"use strict";

// Keeping the current identity and request list in memory.
// The server-side session is still the authority for permissions.
let activeIdentity = null;
let requests = [];
let operationPending = false;
let requestsLoaded = false;

// These values need to match DemoIdentity.java.
const identityNames = {
    "student-a": "Student A",
    "student-b": "Student B",
    "reviewer": "Support Reviewer"
};

// Keeping endpoint definitions together makes it easier to update
// the frontend if the API contracts change later.
const API = {
    identity: "/api/demo/identity",
    requests: "/api/requests",
    reviewerRequests: "/api/reviewer/requests"
};

const statusClasses = {
    "Submitted": "status-submitted",
    "In Progress": "status-in-progress",
    "Resolved": "status-resolved"
};

// Only display transitions supported by WorkflowService.java.
// The backend will still validate every requested transition.
const statusTransitions = {
    "Submitted": "In Progress",
    "In Progress": "Resolved",
    "Resolved": "In Progress"
};

const statusButtonLabels = {
    "Submitted": "Start reviewing",
    "In Progress": "Resolve request",
    "Resolved": "Reopen request"
};


// DOM references

// Store the elements once instead of repeatedly searching
// the document whenever a request is updated.
const elements = {
    identityScreen: document.getElementById("identity-screen"),
    dashboard: document.getElementById("dashboard"),
    sessionControls: document.getElementById("session-controls"),
    activeIdentity: document.getElementById("active-identity"),

    switchIdentity: document.getElementById("switch-identity"),
    refreshRequests: document.getElementById("refresh-requests"),

    dashboardLabel: document.getElementById("dashboard-label"),
    dashboardTitle: document.getElementById("dashboard-title"),
    dashboardDescription: document.getElementById(
        "dashboard-description"
    ),

    studentPanel: document.getElementById("student-panel"),

    requestForm: document.getElementById("request-form"),
    requestTitle: document.getElementById("request-title"),
    requestCategory: document.getElementById("request-category"),
    requestDescription: document.getElementById(
        "request-description"
    ),
    submitRequest: document.getElementById("submit-request"),

    requestList: document.getElementById("request-list"),
    requestListTitle: document.getElementById("request-list-title"),
    requestListDescription: document.getElementById(
        "request-list-description"
    ),

    statusFilter: document.getElementById("status-filter"),

    totalCount: document.getElementById("total-count"),
    submittedCount: document.getElementById("submitted-count"),
    progressCount: document.getElementById("progress-count"),
    resolvedCount: document.getElementById("resolved-count"),

    notification: document.getElementById("notification"),
    loadingState: document.getElementById("loading-state"),
    emptyState: document.getElementById("empty-state"),

    requestTemplate: document.getElementById(
        "request-card-template"
    )
};


// API communication

// Use one error type so HTTP failures can be distinguished
// from network failures or unexpected JavaScript errors.
class ApiError extends Error {
    constructor(status, code, message) {
        super(message);

        this.name = "ApiError";
        this.status = status;
        this.code = code;
    }
}

/*
 * All API requests go through this function.
 *
 * Since Spring Boot serves both the frontend and backend,
 * relative URLs should be enough. No separate server or
 * additional CORS configuration is needed.
 */
async function apiRequest(url, options = {}) {
    const headers = new Headers(options.headers || {});

    headers.set("Accept", "application/json");

    if (options.body !== undefined) {
        headers.set("Content-Type", "application/json");
    }

    const response = await fetch(url, {
        ...options,
        headers: headers,

        // Keep the existing Spring Boot session cookie.
        // Do not send custom identity or role headers.
        credentials: "same-origin",

        // Avoid using an outdated cached request representation.
        cache: "no-store"
    });

    let data = null;

    const contentType = response.headers.get("Content-Type") || "";

    if (contentType.includes("application/json")) {
        data = await response.json();
    }

    // fetch() does not automatically throw for HTTP 400, 403,
    // 404, etc. Need to explicitly check the response status.
    if (!response.ok) {
        const message = Array.isArray(data?.errors)
            ? data.errors.join(" ")
            : data?.message || `Request failed (${response.status}).`;

        throw new ApiError(
            response.status,
            data?.code || "UNKNOWN_ERROR",
            message
        );
    }

    return {
        data: data,
        etag: response.headers.get("ETag"),
        status: response.status
    };
}


// Notifications and error handling

function showNotification(message, type = "info") {
    elements.notification.textContent = message;

    elements.notification.className =
        `notification notification-${type}`;

    elements.notification.hidden = false;
}

function clearNotification() {
    elements.notification.textContent = "";
    elements.notification.hidden = true;
}

/*
 * Keep the backend's error message where possible.
 * Some errors need additional instructions so the user
 * knows what to do instead of just seeing an HTTP code.
 */
function handleError(error) {
    if (!(error instanceof ApiError)) {
        console.error(error);

        showNotification(
            "Unable to complete the operation. " +
            "Check that CampusConnect is running. " +
            "Refresh requests before retrying a modification.",
            "error"
        );

        return;
    }

    switch (error.status) {
        case 400:
            showNotification(
                `Invalid request: ${error.message}`,
                "error"
            );
            break;

        case 403:
            // Clear the displayed records because the current
            // browser view may no longer match the server session.
            showIdentityScreen();

            showNotification(
                "Access denied. Please select a demo identity again. " +
                error.message,
                "error"
            );
            break;

        case 404:
            showNotification(
                "The requested record is unavailable or inaccessible. " +
                "Refresh the request list.",
                "error"
            );
            break;

        case 412:
            // Do not automatically retry with a newer ETag.
            // The reviewer needs to inspect the latest record first.
            showNotification(
                "This request was changed by another operation. " +
                "Refresh the request list and review the latest " +
                "information before trying again.",
                "warning"
            );
            break;

        case 428:
            showNotification(
                "The required request version is missing. " +
                "Refresh the request list before making changes.",
                "warning"
            );
            break;

        case 503:
            showNotification(
                "The storage service is currently unavailable. " +
                "The operation could not be confirmed. " +
                "Refresh the request list before retrying.",
                "error"
            );
            break;

        default:
            showNotification(error.message, "error");
    }
}


// Pending operation management

/*
 * Prevent overlapping operations from this browser page.
 *
 * This helps reduce accidental double-clicks and identity changes
 * while a request is being processed. It is not a replacement
 * for backend transaction management or concurrency enforcement.
 */
function setOperationPending(pending) {
    operationPending = pending;

    elements.switchIdentity.disabled = pending;
    elements.refreshRequests.disabled = pending;
    elements.submitRequest.disabled = pending;
    elements.statusFilter.disabled = pending;

    document.querySelectorAll(".identity-button")
        .forEach(button => {
            button.disabled = pending;
        });

    document.querySelectorAll(".save-note, .change-status")
        .forEach(button => {
            button.disabled =
                pending || button.dataset.blocked === "true";
        });

    document.querySelectorAll(".reviewer-note")
        .forEach(textarea => {
            textarea.disabled = pending;
        });

    elements.requestTitle.disabled = pending;
    elements.requestCategory.disabled = pending;
    elements.requestDescription.disabled = pending;
}

async function runOperation(operation) {
    if (operationPending) {
        return;
    }

    setOperationPending(true);
    clearNotification();

    try {
        await operation();
    } catch (error) {
        handleError(error);
    } finally {
        setOperationPending(false);
    }
}


// Demo identity selection

async function selectIdentity(identity) {
    if (!Object.hasOwn(identityNames, identity)) {
        showNotification("Invalid demo identity.", "error");
        return;
    }

    await runOperation(async () => {
        const result = await apiRequest(API.identity, {
            method: "POST",
            body: JSON.stringify({
                identity: identity
            })
        });

        // Do not display the dashboard until the server confirms
        // the requested identity.
        if (result.data?.identity !== identity) {
            throw new Error("Unexpected identity response.");
        }

        activeIdentity = result.data.identity;

        requests = [];
        requestsLoaded = false;

        elements.requestList.replaceChildren();
        elements.requestForm.reset();
        elements.statusFilter.value = "all";

        showDashboard();

        await loadRequests();
    });
}

function showIdentityScreen() {
    activeIdentity = null;

    // Remove previously displayed request data before allowing
    // another identity to be selected.
    requests = [];
    requestsLoaded = false;

    elements.requestList.replaceChildren();
    elements.requestForm.reset();
    elements.statusFilter.value = "all";

    elements.activeIdentity.textContent = "";

    elements.dashboard.hidden = true;
    elements.studentPanel.hidden = true;
    elements.sessionControls.hidden = true;

    elements.loadingState.hidden = true;
    elements.emptyState.hidden = true;

    elements.identityScreen.hidden = false;

    updateStatistics();
    clearNotification();

    // This returns to the identity selection screen.
    // It is not a server-side logout operation because the
    // current API does not provide a logout endpoint.
}

function showDashboard() {
    const isReviewer = activeIdentity === "reviewer";

    elements.identityScreen.hidden = true;
    elements.dashboard.hidden = false;
    elements.sessionControls.hidden = false;

    elements.activeIdentity.textContent =
        identityNames[activeIdentity];

    elements.studentPanel.hidden = isReviewer;

    if (isReviewer) {
        elements.dashboardLabel.textContent = "REVIEWER PORTAL";

        elements.dashboardTitle.textContent =
            "Support reviewer dashboard";

        elements.dashboardDescription.textContent =
            "Review incoming requests and manage their progress.";

        elements.requestListTitle.textContent =
            "All support requests";

        elements.requestListDescription.textContent =
            "Review requests submitted by both student identities.";
    } else {
        elements.dashboardLabel.textContent = "STUDENT PORTAL";

        elements.dashboardTitle.textContent =
            "How can we help you?";

        elements.dashboardDescription.textContent =
            "Submit a support request and track its progress.";

        elements.requestListTitle.textContent =
            "My support requests";

        elements.requestListDescription.textContent =
            "View and track your submitted requests.";
    }

    updateStatistics();
}


// Retrieve requests

async function loadRequests() {
    if (!activeIdentity) {
        return;
    }

    elements.loadingState.hidden = false;
    elements.emptyState.hidden = true;

    // Clear the old list while loading. If the API fails,
    // we should not present outdated records as current.
    elements.requestList.replaceChildren();

    requests = [];
    requestsLoaded = false;

    updateStatistics();

    try {
        // Let the backend decide which records are accessible.
        // Do not download every student's records and then
        // attempt to enforce ownership through browser filtering.
        const endpoint = activeIdentity === "reviewer"
            ? API.reviewerRequests
            : API.requests;

        const result = await apiRequest(endpoint);

        if (!Array.isArray(result.data)) {
            throw new Error("Unexpected request list response.");
        }

        requests = result.data;
        requestsLoaded = true;

        updateStatistics();
        renderRequests();

    } finally {
        elements.loadingState.hidden = true;
    }
}


// Dashboard statistics

function updateStatistics() {
    // A failed or unfinished request should not appear
    // as a confirmed count of zero records.
    if (!requestsLoaded) {
        elements.totalCount.textContent = "—";
        elements.submittedCount.textContent = "—";
        elements.progressCount.textContent = "—";
        elements.resolvedCount.textContent = "—";

        return;
    }

    elements.totalCount.textContent = requests.length;

    elements.submittedCount.textContent = requests.filter(
        request => request.status === "Submitted"
    ).length;

    elements.progressCount.textContent = requests.filter(
        request => request.status === "In Progress"
    ).length;

    elements.resolvedCount.textContent = requests.filter(
        request => request.status === "Resolved"
    ).length;
}


// Student request submission

async function submitRequest(event) {
    event.preventDefault();

    if (!activeIdentity || activeIdentity === "reviewer") {
        return;
    }

    const title = elements.requestTitle.value.trim();

    const category = elements.requestCategory.value;

    const description =
        elements.requestDescription.value.trim();

    // Match RequestValidator.java after trimming whitespace.
    // HTML minlength alone would count leading and trailing spaces.
    if (title.length < 5 || title.length > 100) {
        showNotification(
            "The request title must contain 5–100 characters.",
            "error"
        );
        return;
    }

    if (description.length < 10 || description.length > 2000) {
        showNotification(
            "The description must contain 10–2,000 characters.",
            "error"
        );
        return;
    }

    if (!["Academic", "IT", "Campus Services", "Other"]
        .includes(category)) {

        showNotification(
            "Please select a valid category.",
            "error"
        );

        return;
    }

    await runOperation(async () => {
        const result = await apiRequest(API.requests, {
            method: "POST",

            // Intentionally omit studentID, status, UUID,
            // timestamps, and version. These are assigned
            // or controlled by the backend.
            body: JSON.stringify({
                title: title,
                category: category,
                description: description
            })
        });

        if (result.status !== 201) {
            throw new Error("Unexpected creation response.");
        }

        // Only clear the form after the server confirms creation.
        elements.requestForm.reset();
        elements.statusFilter.value = "all";

        await refreshAfterMutation(
            "Your support request was submitted successfully."
        );
    });
}

/*
 * A successful mutation and a failed list refresh are two
 * different outcomes. Do not tell the user that creation
 * failed if the server already confirmed it.
 */
async function refreshAfterMutation(successMessage) {
    try {
        await loadRequests();

        showNotification(successMessage, "success");

    } catch (error) {
        if (error instanceof ApiError && error.status === 403) {
            showIdentityScreen();
        }

        console.error(error);

        showNotification(
            successMessage +
            " However, the request list could not be refreshed. " +
            "Refresh the page's request list before making more changes.",
            "warning"
        );
    }
}


// Render request cards

function renderRequests() {
    elements.requestList.replaceChildren();

    if (!requestsLoaded) {
        elements.emptyState.hidden = true;
        return;
    }

    const selectedStatus = elements.statusFilter.value;

    // Filtering is only applied to records the server has
    // already authorized this identity to retrieve.
    const visibleRequests = selectedStatus === "all"
        ? requests
        : requests.filter(
            request => request.status === selectedStatus
        );

    elements.emptyState.hidden = visibleRequests.length !== 0;

    const fragment = document.createDocumentFragment();

    visibleRequests.forEach(request => {
        fragment.appendChild(createRequestCard(request));
    });

    elements.requestList.appendChild(fragment);

    // Newly generated buttons also need to respect the
    // current pending operation state.
    setOperationPending(operationPending);
}

function formatDate(value) {
    if (!value) {
        return "Unavailable";
    }

    const date = new Date(value);

    if (Number.isNaN(date.getTime())) {
        return "Unavailable";
    }

    return date.toLocaleString();
}

function createRequestCard(request) {
    const card = elements.requestTemplate.content.cloneNode(true);

    /*
     * Use textContent for all request data.
     *
     * Descriptions and notes originate from user input,
     * so inserting them with innerHTML would introduce
     * unnecessary markup injection risks.
     */
    card.querySelector(".request-title").textContent =
        request.title;

    card.querySelector(".request-meta").textContent =
        request.category;

    card.querySelector(".request-description").textContent =
        request.description;

    card.querySelector(".request-id").textContent =
        request.id;

    card.querySelector(".request-owner").textContent =
        request.studentID;

    card.querySelector(".request-created").textContent =
        formatDate(request.createdAt);

    card.querySelector(".request-updated").textContent =
        formatDate(request.updatedAt);

    const statusBadge = card.querySelector(".status-badge");

    statusBadge.textContent = request.status;

    if (Object.hasOwn(statusClasses, request.status)) {
        statusBadge.classList.add(
            statusClasses[request.status]
        );
    }

    if (activeIdentity === "reviewer") {
        configureReviewerControls(card, request);
    } else {
        configureStudentControls(card, request);
    }

    return card;
}

function configureStudentControls(card, request) {
    const noteSection = card.querySelector(".student-note");

    const note = request.resolutionNote || "";

    noteSection.hidden = note.trim().length === 0;

    card.querySelector(".saved-note").textContent = note;

    // Student cards should not expose editing controls.
    card.querySelector(".reviewer-controls").hidden = true;
    card.querySelector(".owner-field").hidden = true;
}


// Reviewer controls

function configureReviewerControls(card, request) {
    card.querySelector(".reviewer-controls").hidden = false;
    card.querySelector(".owner-field").hidden = false;

    const noteInput = card.querySelector(".reviewer-note");

    const noteLabel = card.querySelector(".note-label");

    const saveNoteButton = card.querySelector(".save-note");

    const statusButton = card.querySelector(".change-status");

    const savedNote = request.resolutionNote || "";

    noteInput.value = savedNote;

    // Each generated textarea needs a unique ID so its
    // label points to the correct request.
    noteInput.id = `note-${request.id}`;
    noteLabel.htmlFor = noteInput.id;

    const nextStatus = Object.hasOwn(
        statusTransitions,
        request.status
    )
        ? statusTransitions[request.status]
        : null;

    statusButton.textContent =
        statusButtonLabels[request.status] || "Unavailable";

    // Resolving a request requires a note that has already
    // been saved on the server, not just typed into the form.
    const missingResolutionNote =
        nextStatus === "Resolved" &&
        savedNote.trim().length === 0;

    statusButton.dataset.blocked = String(
        !nextStatus || missingResolutionNote
    );

    statusButton.disabled =
        statusButton.dataset.blocked === "true";

    if (missingResolutionNote) {
        statusButton.title =
            "Save a resolution note before resolving this request.";
    }

    saveNoteButton.addEventListener("click", () => {
        saveResolutionNote(request, noteInput.value);
    });

    statusButton.addEventListener("click", () => {
        // Avoid losing an unsaved note when the request list
        // is rebuilt after a successful status update.
        if (noteInput.value.trim() !== savedNote.trim()) {
            showNotification(
                "Save your note before changing the request status.",
                "warning"
            );
            return;
        }

        updateRequestStatus(request, nextStatus);
    });
}


// Optimistic concurrency

/*
 * The list endpoint returns request records with version values,
 * while the individual request endpoint returns an HTTP ETag.
 *
 * Retrieve the detail representation and confirm it still matches
 * the version displayed to the reviewer before making a change.
 *
 * Important: do not silently use a newer ETag to overwrite a
 * request that has changed since the reviewer loaded the list.
 */
async function getMatchingEtag(request) {
    const requestId = encodeURIComponent(request.id);

    const result = await apiRequest(
        `${API.reviewerRequests}/${requestId}`
    );

    if (!result.data || !result.etag) {
        throw new ApiError(
            428,
            "PRECONDITION_REQUIRED",
            "The request version could not be retrieved."
        );
    }

    // Compare against the version the reviewer actually saw.
    // The backend will perform the final atomic version check.
    if (
        result.data.version !== request.version ||
        result.etag !== `"${request.version}"`
    ) {
        throw new ApiError(
            412,
            "CONFLICT",
            "The displayed request is no longer current."
        );
    }

    return result.etag;
}


// Save reviewer resolution note

async function saveResolutionNote(request, value) {
    if (activeIdentity !== "reviewer") {
        return;
    }

    const note = value.trim();

    if (note.length > 2000) {
        showNotification(
            "Resolution notes cannot exceed 2,000 characters.",
            "error"
        );
        return;
    }

    // A resolved request must retain a nonempty resolution note.
    // The same rule is enforced by WorkflowService.java.
    if (request.status === "Resolved" && note.length === 0) {
        showNotification(
            "A resolved request must retain a resolution note.",
            "error"
        );
        return;
    }

    await runOperation(async () => {
        const etag = await getMatchingEtag(request);

        const requestId = encodeURIComponent(request.id);

        await apiRequest(
            `${API.requests}/${requestId}/note`,
            {
                method: "PATCH",

                headers: {
                    "If-Match": etag
                },

                body: JSON.stringify({
                    resolutionNote: note
                })
            }
        );

        // Saving a note increments the stored version.
        // Reload the records before allowing another update.
        await refreshAfterMutation(
            "Resolution note saved successfully."
        );
    });
}


// Update request status

async function updateRequestStatus(request, nextStatus) {
    if (activeIdentity !== "reviewer") {
        return;
    }

    if (
        !nextStatus ||
        statusTransitions[request.status] !== nextStatus
    ) {
        showNotification(
            "This status transition is not supported.",
            "error"
        );
        return;
    }

    if (
        nextStatus === "Resolved" &&
        !(request.resolutionNote || "").trim()
    ) {
        showNotification(
            "Save a resolution note before resolving the request.",
            "warning"
        );
        return;
    }

    await runOperation(async () => {
        const etag = await getMatchingEtag(request);

        const requestId = encodeURIComponent(request.id);

        await apiRequest(
            `${API.requests}/${requestId}/status`,
            {
                method: "PATCH",

                headers: {
                    "If-Match": etag
                },

                // The status endpoint should only receive
                // the new status. Notes are saved separately.
                body: JSON.stringify({
                    status: nextStatus
                })
            }
        );

        await refreshAfterMutation(
            `Request status updated to ${nextStatus}.`
        );
    });
}


// Event listeners

function initializeEventListeners() {
    document.querySelectorAll(".identity-button")
        .forEach(button => {
            button.addEventListener("click", () => {
                selectIdentity(button.dataset.identity);
            });
        });

    elements.switchIdentity.addEventListener("click", () => {
        if (!operationPending) {
            showIdentityScreen();
        }
    });

    elements.refreshRequests.addEventListener("click", () => {
        runOperation(async () => {
            await loadRequests();

            showNotification(
                "Request list refreshed.",
                "success"
            );
        });
    });

    elements.requestForm.addEventListener(
        "submit",
        submitRequest
    );

    elements.statusFilter.addEventListener("change", () => {
        if (!operationPending) {
            renderRequests();
        }
    });
}


// Application initialization

function initializeApplication() {
    initializeEventListeners();

    // Start with explicit identity selection on each page load.
    // Avoid storing synthetic identities or request data in
    // localStorage because the server session owns that context.
    showIdentityScreen();
}

/*
 * Remaining verification before the A6 release:
 *
 * 1. Create a request as Student A.
 * 2. Confirm Student B cannot see Student A's request.
 * 3. Confirm Reviewer can see both students' requests.
 * 4. Move a request to In Progress.
 * 5. Save a resolution note and resolve the request.
 * 6. Confirm the student can see the updated status and note.
 * 7. Test stale updates using independent browser sessions.
 * 8. Restart Spring Boot and verify stored requests persist.
 * 9. Verify error messages and responsive layouts.
 * 10. Run the final Maven build and integrated CI checks.
 *
 * These checks need to be performed against the running
 * application before claiming successful verification.
 */

initializeApplication();
