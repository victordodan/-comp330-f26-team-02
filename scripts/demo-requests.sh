#!/bin/bash
# CampusConnect — Cycle 1 Live HTTP Demo Script
# Run this script while 'mvn spring-boot:run' is active on http://localhost:8080

BASE_URL="http://localhost:8080/api/requests"

echo "─────────────────── ⋆⋅☆⋅⋆ ───────────────────"
echo "  CampusConnect Cycle 1 REST Demo Execution"
echo "─────────────────── ⋆⋅☆⋅⋆ ───────────────────"
echo ""

echo "[1/6] Step 1: POST /api/requests — Student Submits Support Request"
curl -i -X POST "$BASE_URL" \
  -H "Content-Type: application/json" \
  -d '{
    "studentID": "student-101",
    "title": "Need help with course registration",
    "category": "Academic",
    "description": "Unable to register for COMP 330 due to prerequisite override requirement."
  }'
echo -e "\n\n──────────────────────────────────────────────────\n"

echo "[2/6] Step 2: GET /api/requests — Student-101 Retrieves Isolated Tickets (REQ-002)"
curl -i -X GET "$BASE_URL" \
  -H "X-User-ID: student-101" \
  -H "X-User-Role: STUDENT"
echo -e "\n\n──────────────────────────────────────────────────\n"

echo "[3/6] Step 3: PATCH /api/requests/REQ-1001/status — Reviewer Triages Ticket"
curl -i -X PATCH "$BASE_URL/REQ-1001/status" \
  -H "X-User-ID: reviewer-1" \
  -H "X-User-Role: REVIEWER" \
  -H "Content-Type: application/json" \
  -d '{
    "status": "Resolved",
    "resolutionNote": "Prerequisite override granted in registration system."
  }'
echo -e "\n\n──────────────────────────────────────────────────\n"

echo "[4/6] Boundary Case 1: Student Status Tampering (Expects 403 Forbidden)"
curl -i -X PATCH "$BASE_URL/REQ-1001/status" \
  -H "X-User-ID: student-101" \
  -H "X-User-Role: STUDENT" \
  -H "Content-Type: application/json" \
  -d '{
    "status": "Resolved",
    "resolutionNote": "Self-resolving"
  }'
echo -e "\n\n──────────────────────────────────────────────────\n"

echo "[5/6] Boundary Case 2: Blank Resolution Note on Resolve (Expects 400 Bad Request)"
curl -i -X PATCH "$BASE_URL/REQ-1001/status" \
  -H "X-User-ID: reviewer-1" \
  -H "X-User-Role: REVIEWER" \
  -H "Content-Type: application/json" \
  -d '{
    "status": "Resolved",
    "resolutionNote": ""
  }'
echo -e "\n\n──────────────────────────────────────────────────\n"

echo "[6/6] Boundary Case 3: Non-Existent Ticket ID (Expects 404 Not Found)"
curl -i -X PATCH "$BASE_URL/REQ-9999/status" \
  -H "X-User-ID: reviewer-1" \
  -H "X-User-Role: REVIEWER" \
  -H "Content-Type: application/json" \
  -d '{
    "status": "Resolved",
    "resolutionNote": "Testing non-existent ticket"
  }'
echo -e "\n\n──────────────────────────────────────────────────\n"

echo "───────────────── ⋆⋅☆⋅⋆ ─────────────────"
echo "        Demo Execution Complete!"
echo "───────────────── ⋆⋅☆⋅⋆ ─────────────────"
