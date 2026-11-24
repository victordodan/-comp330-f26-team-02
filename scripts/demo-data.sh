
#!/usr/bin/env bash
# CampusConnect - Synthetic Demonstration Data
# COMP 330 | Assignment 6 | Cycle 2 Final Release
#
# Purpose:
# Prepare realistic support requests for the final presentation
# without manually entering every request through the website.
#
# All records are synthetic and use the existing REST API.
# No direct database access is needed.
#
# Usage:
# bash scripts/demo-data.sh
#
# Requirements:
# - Running CampusConnect application
# - Bash
# - curl
# - Python 3 for JSON handling

set -euo pipefail

BASE_URL="http://localhost:8080"

CREATED=0
SKIPPED=0
UPDATED=0

# Check dependencies before creating any records.

for dependency in curl python3; do

    if ! command -v "$dependency" >/dev/null 2>&1; then

        echo "ERROR: $dependency is required."
        exit 1

    fi

done

TEMP_DIR=$(mktemp -d)

STUDENT_A_COOKIE="$TEMP_DIR/student-a.cookies"
STUDENT_B_COOKIE="$TEMP_DIR/student-b.cookies"
REVIEWER_COOKIE="$TEMP_DIR/reviewer.cookies"

RESPONSE_FILE="$TEMP_DIR/response.json"
HEADER_FILE="$TEMP_DIR/headers.txt"

touch "$STUDENT_A_COOKIE"
touch "$STUDENT_B_COOKIE"
touch "$REVIEWER_COOKIE"

# Separate cookie files prevent the student and reviewer
# identities from accidentally sharing the same session.

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

abort() {

    echo ""
    echo "ERROR: $1"
    echo "Previously confirmed changes may still be saved."
    echo "Inspect the current requests before running again."

    exit 1

}

api_request() {

    local expected_status="$1"

    shift

    local actual_status

    # Capture response headers separately because the reviewer
    # update endpoints require the current ETag value.

    if ! actual_status=$(curl \
        --silent \
        --show-error \
        --connect-timeout 5 \
        --max-time 15 \
        --dump-header "$HEADER_FILE" \
        --output "$RESPONSE_FILE" \
        --write-out "%{http_code}" \
        "$@"); then

        abort "Unable to communicate with CampusConnect."

    fi

    if [[ "$actual_status" != "$expected_status" ]]; then

        echo "Expected HTTP $expected_status"
        echo "Received HTTP $actual_status"

        abort "API request failed."

    fi

}

json_field() {

    local field="$1"

    # Use a JSON parser rather than relying on the order
    # or formatting of fields in the API response.

    python3 - "$RESPONSE_FILE" "$field" <<'PYTHON'
import json
import sys

filename = sys.argv[1]
field = sys.argv[2]

with open(filename, encoding="utf-8") as file:
    data = json.load(file)

value = data[field]

if value is None:
    print("")
else:
    print(value)
PYTHON

}

get_etag() {

    # Preserve the quotation marks around the version number.
    # The backend expects If-Match values such as "0" or "1".

    awk '
        tolower($1) == "etag:" {
            sub(/\r$/, "", $2)
            print $2
        }
    ' "$HEADER_FILE" | tail -n 1

}

select_identity() {

    local identity="$1"
    local cookie_file="$2"

    api_request \
        "200" \
        --request POST \
        --cookie "$cookie_file" \
        --cookie-jar "$cookie_file" \
        --header "Content-Type: application/json" \
        --data "{\"identity\":\"$identity\"}" \
        "$BASE_URL/api/demo/identity"

    if [[ "$(json_field identity)" != "$identity" ]]; then

        abort "Unexpected identity selection response."

    fi

    echo "[OK] Selected identity: $identity"

}

# Request creation

find_existing_request() {

    local cookie_file="$1"
    local title="$2"
    local description="$3"
    local category="$4"

    api_request \
        "200" \
        --cookie "$cookie_file" \
        "$BASE_URL/api/requests"

    # Check the current student's records before creating
    # another copy of the same demonstration request.
    #
    # This is only a convenience for repeated demo preparation.
    # It is not a replacement for server-side idempotency.

    python3 - "$RESPONSE_FILE" "$title" "$description" "$category" <<'PYTHON'
import json
import sys

filename, title, description, category = sys.argv[1:]

with open(filename, encoding="utf-8") as file:
    requests = json.load(file)

matches = [
    request for request in requests
    if request.get("title") == title
    and request.get("description") == description
    and request.get("category") == category
]

if len(matches) > 1:
    print("Multiple matching demo requests found.", file=sys.stderr)
    sys.exit(1)

if matches:
    print(matches[0]["id"])
PYTHON

}

create_request() {

    local cookie_file="$1"
    local expected_owner="$2"
    local title="$3"
    local category="$4"
    local description="$5"

    local existing_id
    local payload

    existing_id=$(find_existing_request \
        "$cookie_file" \
        "$title" \
        "$description" \
        "$category") || abort "Unable to check existing requests."

    if [[ -n "$existing_id" ]]; then

        SKIPPED=$((SKIPPED + 1))

        REQUEST_ID="$existing_id"
        REQUEST_IS_NEW="false"

        echo "[SKIP] Existing request: $title"
        return 0

    fi

    # Build valid JSON without manually escaping user-facing
    # strings. This also makes future demo records easier to add.

    payload=$(python3 - "$title" "$category" "$description" <<'PYTHON'
import json
import sys

print(json.dumps({
    "title": sys.argv[1],
    "category": sys.argv[2],
    "description": sys.argv[3]
}))
PYTHON
)

    api_request \
        "201" \
        --request POST \
        --cookie "$cookie_file" \
        --header "Content-Type: application/json" \
        --data "$payload" \
        "$BASE_URL/api/requests"

    REQUEST_ID=$(json_field id)
    REQUEST_IS_NEW="true"

    # The server should assign ownership from the selected
    # session, not from a studentID supplied in the request body.

    if [[ "$(json_field studentID)" != "$expected_owner" ]]; then

        abort "Unexpected request ownership."

    fi

    if [[ "$(json_field status)" != "Submitted" ]]; then

        abort "Unexpected initial request status."

    fi

    if [[ -z "$REQUEST_ID" ]]; then

        abort "Created request did not contain an identifier."

    fi

    CREATED=$((CREATED + 1))

    echo "[CREATED] $title"
    echo "          Request ID: $REQUEST_ID"

}
# Reviewer operations


load_reviewer_request() {

    local request_id="$1"

    api_request \
        "200" \
        --cookie "$REVIEWER_COOKIE" \
        "$BASE_URL/api/reviewer/requests/$request_id"

    CURRENT_STATUS=$(json_field status)
    CURRENT_NOTE=$(json_field resolutionNote)
    CURRENT_ETAG=$(get_etag)

    if [[ -z "$CURRENT_ETAG" ]]; then

        abort "Request response did not contain an ETag."

    fi

}

update_status() {

    local request_id="$1"
    local next_status="$2"

    local payload
    local previous_etag="$CURRENT_ETAG"

    payload=$(python3 - "$next_status" <<'PYTHON'
import json
import sys

print(json.dumps({
    "status": sys.argv[1]
}))
PYTHON
)

    # Use the version returned by the previous API response.
    # If another reviewer changes the request in the meantime,
    # the backend should reject this update rather than overwrite it.

    api_request \
        "200" \
        --request PATCH \
        --cookie "$REVIEWER_COOKIE" \
        --header "Content-Type: application/json" \
        --header "If-Match: $previous_etag" \
        --data "$payload" \
        "$BASE_URL/api/requests/$request_id/status"

    CURRENT_STATUS=$(json_field status)
    CURRENT_NOTE=$(json_field resolutionNote)
    CURRENT_ETAG=$(get_etag)

    if [[ "$CURRENT_STATUS" != "$next_status" ]]; then

        abort "Status update returned an unexpected result."

    fi

    if [[ -z "$CURRENT_ETAG" ]]; then

        abort "Status update did not return an ETag."

    fi

    UPDATED=$((UPDATED + 1))

    echo "[UPDATED] Status: $CURRENT_STATUS"

}

save_note() {

    local request_id="$1"
    local note="$2"

    local payload
    local previous_etag="$CURRENT_ETAG"

    payload=$(python3 - "$note" <<'PYTHON'
import json
import sys

print(json.dumps({
    "resolutionNote": sys.argv[1]
}))
PYTHON
)

    # Notes are saved separately from status changes.
    # A request cannot be resolved until a nonblank
    # resolution note has already been saved.

    api_request \
        "200" \
        --request PATCH \
        --cookie "$REVIEWER_COOKIE" \
        --header "Content-Type: application/json" \
        --header "If-Match: $previous_etag" \
        --data "$payload" \
        "$BASE_URL/api/requests/$request_id/note"

    CURRENT_STATUS=$(json_field status)
    CURRENT_NOTE=$(json_field resolutionNote)
    CURRENT_ETAG=$(get_etag)

    if [[ "$CURRENT_NOTE" != "$note" ]]; then

        abort "Resolution note was not confirmed."

    fi

    if [[ -z "$CURRENT_ETAG" ]]; then

        abort "Note update did not return an ETag."

    fi

    UPDATED=$((UPDATED + 1))

    echo "[UPDATED] Resolution note saved."

}

prepare_request() {

    local cookie_file="$1"
    local owner="$2"
    local title="$3"
    local category="$4"
    local description="$5"
    local target_status="$6"
    local note="$7"

    create_request \
        "$cookie_file" \
        "$owner" \
        "$title" \
        "$category" \
        "$description"

    local request_id="$REQUEST_ID"

    # Do not overwrite an existing request just to restore
    # its original demonstration status. A teammate may have
    # already interacted with that record through the website.

    if [[ "$REQUEST_IS_NEW" == "false" ]]; then

        return 0

    fi

    if [[ "$target_status" == "Submitted" ]]; then

        return 0

    fi

    load_reviewer_request "$request_id"

    if [[ "$CURRENT_STATUS" != "Submitted" || "$CURRENT_ETAG" != '"0"' ]]; then

        abort "New request changed before demonstration setup."

    fi

    # Follow the supported lifecycle rather than attempting
    # to move directly from Submitted to Resolved.

    update_status "$request_id" "In Progress"

    if [[ -n "$note" ]]; then

        save_note "$request_id" "$note"

    fi

    if [[ "$target_status" == "Resolved" ]]; then

        update_status "$request_id" "Resolved"

    fi

}

# Application availability

section "CampusConnect Demonstration Setup"

echo "Application: $BASE_URL"
echo "Execution date: $(date -u '+%Y-%m-%d %H:%M:%S UTC')"

if ! curl \
    --silent \
    --show-error \
    --connect-timeout 5 \
    --max-time 10 \
    --output /dev/null \
    "$BASE_URL/"; then

    abort "Start CampusConnect before running this script."

fi

echo ""
echo "This script creates persistent synthetic support requests."
echo "Existing matching records will not be overwritten."
echo ""

read -r -p "Continue with demonstration setup? [y/N]: " CONFIRM

case "$CONFIRM" in

    y|Y|yes|YES)
        echo "Preparing demonstration data..."
        ;;

    *)
        echo "Demonstration setup cancelled."
        exit 0
        ;;

esac

# Establish independent sessions

section "1. Select Synthetic Identities"

select_identity "student-a" "$STUDENT_A_COOKIE"
select_identity "student-b" "$STUDENT_B_COOKIE"
select_identity "reviewer" "$REVIEWER_COOKIE"

# Student A demonstration requests

section "2. Prepare Student A Requests"

prepare_request \
    "$STUDENT_A_COOKIE" \
    "student-a" \
    "Unable to access Sakai" \
    "IT" \
    "[DEMO] Unable to access the course materials through Sakai." \
    "Submitted" \
    ""

prepare_request \
    "$STUDENT_A_COOKIE" \
    "student-a" \
    "Course registration issue" \
    "Academic" \
    "[DEMO] A required course is not appearing during registration." \
    "In Progress" \
    "Registration details are being reviewed by the academic support team."

prepare_request \
    "$STUDENT_A_COOKIE" \
    "student-a" \
    "Library account assistance" \
    "Campus Services" \
    "[DEMO] Unable to access online library resources using the demo account." \
    "Resolved" \
    "Library account access was restored and resource availability was confirmed."

# Student B demonstration requests

section "3. Prepare Student B Requests"

prepare_request \
    "$STUDENT_B_COOKIE" \
    "student-b" \
    "Campus Wi-Fi connection issue" \
    "IT" \
    "[DEMO] The campus wireless connection repeatedly disconnects." \
    "Resolved" \
    "Network settings were reviewed and the connection issue was resolved."

prepare_request \
    "$STUDENT_B_COOKIE" \
    "student-b" \
    "Academic advising appointment" \
    "Academic" \
    "[DEMO] Requesting assistance scheduling an academic advising appointment." \
    "Submitted" \
    ""

prepare_request \
    "$STUDENT_B_COOKIE" \
    "student-b" \
    "Student organization inquiry" \
    "Other" \
    "[DEMO] Requesting information about registering a student organization." \
    "In Progress" \
    "The request is being reviewed for the appropriate campus organization resources."

# Verify accessible request records

section "4. Verify Demonstration Records"

print_requests() {

    python3 - "$RESPONSE_FILE" <<'PYTHON'
import json
import sys

with open(sys.argv[1], encoding="utf-8") as file:
    requests = json.load(file)

demo_requests = [
    request for request in requests
    if request.get("description", "").startswith("[DEMO]")
]

print(f"Accessible demonstration requests: {len(demo_requests)}")

for request in demo_requests:
    print(
        f"  {request['studentID']} | "
        f"{request['status']} | "
        f"{request['title']}"
    )
PYTHON

}

echo ""
echo "Student A dashboard:"

api_request \
    "200" \
    --cookie "$STUDENT_A_COOKIE" \
    "$BASE_URL/api/requests"

print_requests

echo ""
echo "Student B dashboard:"

api_request \
    "200" \
    --cookie "$STUDENT_B_COOKIE" \
    "$BASE_URL/api/requests"

print_requests

echo ""
echo "Reviewer dashboard:"

api_request \
    "200" \
    --cookie "$REVIEWER_COOKIE" \
    "$BASE_URL/api/reviewer/requests"

print_requests

# Final summary

section "5. Demonstration Setup Results"

echo "New requests created:       $CREATED"
echo "Existing requests skipped:  $SKIPPED"
echo "Confirmed reviewer updates: $UPDATED"

echo ""
echo "Demonstration setup completed."
echo ""
echo "Open CampusConnect at:"
echo "$BASE_URL"
echo ""
echo "Select Student A, Student B, or Reviewer"
echo "to inspect the prepared support requests."

# These records intentionally remain in the application's
# file-backed H2 database after the script exits.
#
# Do not automatically delete demonstration data here.
# Teammates may need the records for presentation preparation
# or additional browser verification.
#
# Existing records are preserved on repeated execution,
# including any changes made manually through the website.

exit 0
