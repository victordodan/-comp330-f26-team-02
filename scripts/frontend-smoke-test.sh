#!/usr/bin/env bash

# CampusConnect - Frontend Smoke Test
# COMP 330 | Assignment 6 | Cycle 2 Final Release
#
# Purpose:
# Quickly verify that the running Spring Boot application
# serves the browser interface and preserves API permissions.
#
# This is a lightweight smoke test, not a replacement for
# automated regression tests or manual browser verification.
#
# Usage:
# bash scripts/frontend-smoke-test.sh
#
# The application must already be running on localhost:8080.

set -euo pipefail

BASE_URL="http://localhost:8080"

PASSED=0
FAILED=0
TOTAL=0

# Check dependencies before starting. Keeping this script
# lightweight so teammates do not need another testing framework.

if ! command -v curl >/dev/null 2>&1; then
    echo "ERROR: curl is required to run this script."
    exit 1
fi

# Separate cookie files represent independent browser sessions.
# Using one shared cookie file would invalidate identity tests.

TEMP_DIR=$(mktemp -d)

STUDENT_A_COOKIE="$TEMP_DIR/student-a.cookies"
STUDENT_B_COOKIE="$TEMP_DIR/student-b.cookies"
REVIEWER_COOKIE="$TEMP_DIR/reviewer.cookies"

RESPONSE_FILE="$TEMP_DIR/response.txt"

touch "$STUDENT_A_COOKIE"
touch "$STUDENT_B_COOKIE"
touch "$REVIEWER_COOKIE"

# Always remove temporary response files and session cookies,
# including when the script exits unexpectedly.

cleanup() {
    rm -rf "$TEMP_DIR"
}

trap cleanup EXIT

# Helper functions

section() {
    echo ""
    echo "=================================================="
    echo "$1"
    echo "=================================================="
}

pass() {
    PASSED=$((PASSED + 1))
    TOTAL=$((TOTAL + 1))

    printf "[PASS] %s\n" "$1"
}

fail() {
    FAILED=$((FAILED + 1))
    TOTAL=$((TOTAL + 1))

    printf "[FAIL] %s\n" "$1"
}

check_status() {

    local test_name="$1"
    local expected_status="$2"

    shift 2

    local actual_status

    # Do not use curl --fail here because some tests intentionally
    # expect HTTP 403 or 404 responses from the application.

    if ! actual_status=$(curl \
        --silent \
        --show-error \
        --connect-timeout 5 \
        --max-time 15 \
        --output "$RESPONSE_FILE" \
        --write-out "%{http_code}" \
        "$@"); then

        fail "$test_name - Connection failed"
        return 0
    fi

    if [[ "$actual_status" == "$expected_status" ]]; then

        pass "$test_name - HTTP $actual_status"

    else

        fail "$test_name - Expected HTTP $expected_status, received HTTP $actual_status"

        echo "Response body:"
        head -c 500 "$RESPONSE_FILE"
        echo ""

    fi
}

check_content() {

    local test_name="$1"
    local url="$2"
    local expected_content="$3"

    local actual_status

    if ! actual_status=$(curl \
        --silent \
        --show-error \
        --connect-timeout 5 \
        --max-time 15 \
        --output "$RESPONSE_FILE" \
        --write-out "%{http_code}" \
        "$url"); then

        fail "$test_name - Connection failed"
        return 0
    fi

    if [[ "$actual_status" != "200" ]]; then

        fail "$test_name - Expected HTTP 200, received HTTP $actual_status"

    elif grep -Fq -- "$expected_content" "$RESPONSE_FILE"; then

        pass "$test_name"

    else

        fail "$test_name - Expected content was not found"

    fi
}

select_identity() {

    local identity="$1"
    local cookie_file="$2"

    check_status \
        "Select identity: $identity" \
        "200" \
        --request POST \
        --cookie "$cookie_file" \
        --cookie-jar "$cookie_file" \
        --header "Content-Type: application/json" \
        --data "{\"identity\":\"$identity\"}" \
        "$BASE_URL/api/demo/identity"
}

# Application availability

section "1. Application Availability"

echo "Testing application at: $BASE_URL"
echo "Execution date: $(date -u '+%Y-%m-%d %H:%M:%S UTC')"

# Stop early if the application is not running.
# Otherwise every remaining test would fail for the same reason.

if ! curl \
    --silent \
    --show-error \
    --connect-timeout 5 \
    --max-time 10 \
    --output /dev/null \
    "$BASE_URL/"; then

    echo ""
    echo "ERROR: CampusConnect could not be reached."
    echo ""
    echo "Start the application using:"
    echo "mvn spring-boot:run"
    echo ""
    exit 1
fi

check_status \
    "Root URL is accessible" \
    "200" \
    "$BASE_URL/"

check_status \
    "Homepage is accessible directly" \
    "200" \
    "$BASE_URL/index.html"

# Static frontend resources

section "2. Frontend Resources"

check_status \
    "Stylesheet is accessible" \
    "200" \
    "$BASE_URL/styles.css"

check_status \
    "JavaScript file is accessible" \
    "200" \
    "$BASE_URL/app.js"

check_content \
    "Homepage contains CampusConnect branding" \
    "$BASE_URL/index.html" \
    "CampusConnect"

check_content \
    "Homepage references stylesheet" \
    "$BASE_URL/index.html" \
    'href="styles.css"'

check_content \
    "Homepage references JavaScript" \
    "$BASE_URL/index.html" \
    'src="app.js"'

check_content \
    "Stylesheet contains request card styling" \
    "$BASE_URL/styles.css" \
    ".request-card"

check_content \
    "JavaScript contains application initialization" \
    "$BASE_URL/app.js" \
    "initializeApplication"

# Required interface elements

section "3. Required Interface Elements"

# These values need to stay consistent with app.js.
# A renamed HTML element could otherwise break the interface
# even when the application still returns HTTP 200.

check_content \
    "Student A identity option exists" \
    "$BASE_URL/index.html" \
    'data-identity="student-a"'

check_content \
    "Student B identity option exists" \
    "$BASE_URL/index.html" \
    'data-identity="student-b"'

check_content \
    "Reviewer identity option exists" \
    "$BASE_URL/index.html" \
    'data-identity="reviewer"'

check_content \
    "Request submission form exists" \
    "$BASE_URL/index.html" \
    'id="request-form"'

check_content \
    "Dashboard container exists" \
    "$BASE_URL/index.html" \
    'id="dashboard"'

check_content \
    "Request list container exists" \
    "$BASE_URL/index.html" \
    'id="request-list"'

check_content \
    "Request card template exists" \
    "$BASE_URL/index.html" \
    'id="request-card-template"'

check_content \
    "Notification container exists" \
    "$BASE_URL/index.html" \
    'id="notification"'

check_content \
    "Loading state exists" \
    "$BASE_URL/index.html" \
    'id="loading-state"'

check_content \
    "Empty state exists" \
    "$BASE_URL/index.html" \
    'id="empty-state"'

check_content \
    "Classroom demonstration warning exists" \
    "$BASE_URL/index.html" \
    "Classroom demonstration environment"
    
# Unauthorized access

section "4. Unauthorized API Access"

# Serving the website publicly within the local application
# must not automatically grant access to protected API data.

check_status \
    "Student API rejects missing identity" \
    "403" \
    "$BASE_URL/api/requests"

check_status \
    "Reviewer API rejects missing identity" \
    "403" \
    "$BASE_URL/api/reviewer/requests"

check_status \
    "Forged reviewer header does not grant access" \
    "403" \
    --header "X-User-Role: REVIEWER" \
    "$BASE_URL/api/reviewer/requests"

check_status \
    "Unsupported identity is rejected" \
    "403" \
    --request POST \
    --header "Content-Type: application/json" \
    --data '{"identity":"administrator"}' \
    "$BASE_URL/api/demo/identity"

# Student A session

section "5. Student A Session"

select_identity "student-a" "$STUDENT_A_COOKIE"

check_status \
    "Student A can retrieve personal requests" \
    "200" \
    --cookie "$STUDENT_A_COOKIE" \
    "$BASE_URL/api/requests"

check_status \
    "Student A cannot retrieve reviewer queue" \
    "403" \
    --cookie "$STUDENT_A_COOKIE" \
    "$BASE_URL/api/reviewer/requests"

# Student B session

section "6. Student B Session"

select_identity "student-b" "$STUDENT_B_COOKIE"

check_status \
    "Student B can retrieve personal requests" \
    "200" \
    --cookie "$STUDENT_B_COOKIE" \
    "$BASE_URL/api/requests"

check_status \
    "Student B cannot retrieve reviewer queue" \
    "403" \
    --cookie "$STUDENT_B_COOKIE" \
    "$BASE_URL/api/reviewer/requests"

# Reviewer session


section "7. Reviewer Session"

select_identity "reviewer" "$REVIEWER_COOKIE"

check_status \
    "Reviewer can retrieve support queue" \
    "200" \
    --cookie "$REVIEWER_COOKIE" \
    "$BASE_URL/api/reviewer/requests"

check_status \
    "Reviewer cannot use student-only endpoint" \
    "403" \
    --cookie "$REVIEWER_COOKIE" \
    "$BASE_URL/api/requests"

# Identity switching

section "8. Identity Switching"

# Reuse the reviewer cookie to simulate the website's
# Switch Identity feature. Permissions should follow the
# newly selected identity rather than the previous role.

select_identity "student-b" "$REVIEWER_COOKIE"

check_status \
    "Switched identity can access student endpoint" \
    "200" \
    --cookie "$REVIEWER_COOKIE" \
    "$BASE_URL/api/requests"

check_status \
    "Previous reviewer permissions are no longer available" \
    "403" \
    --cookie "$REVIEWER_COOKIE" \
    "$BASE_URL/api/reviewer/requests"

# Confirm switching one session does not change another.

check_status \
    "Independent Student A session remains accessible" \
    "200" \
    --cookie "$STUDENT_A_COOKIE" \
    "$BASE_URL/api/requests"

# Missing resource handling

section "9. Missing Resource Handling"

check_status \
    "Missing frontend resource returns HTTP 404" \
    "404" \
    "$BASE_URL/missing-frontend-resource.js"

# Final results

section "10. Smoke Test Results"

echo "Application: CampusConnect"
echo "Release phase: Assignment 6"
echo "Target: $BASE_URL"
echo ""
echo "Total checks: $TOTAL"
echo "Passed:       $PASSED"
echo "Failed:       $FAILED"

# Include the current Git revision when available so the
# results can be associated with the correct release candidate.
# This identifies the checkout, not necessarily the running build.

if command -v git >/dev/null 2>&1; then

    if COMMIT_SHA=$(git rev-parse HEAD 2>/dev/null); then

        echo "Checkout commit: $COMMIT_SHA"

    fi
fi

echo ""

if [[ "$FAILED" -eq 0 ]]; then

    echo "RESULT: PASS"
    echo "All frontend smoke checks passed."
    echo ""
    echo "Manual browser verification is still required."

    exit 0

else

    echo "RESULT: FAIL"
    echo "One or more frontend smoke checks failed."
    echo ""
    echo "Review the failed checks before approving the release."

    exit 1

fi
