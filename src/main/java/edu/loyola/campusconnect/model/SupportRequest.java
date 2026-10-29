package edu.loyola.campusconnect.model;

import java.time.Instant;

// Domain entity representing a stored CampusConnect support request.
// Contains the full lifecycle state (ID, submission data, status, resolution notes, and timestamp).

// Create public record of Support Requests
  // Represents the complete, server-side record stored in memory given by student
// Creates id, student's ID, title, category, desc., status, resNote, and created at strings
public record SupportRequest(
    String id,
    String studentID,
    String title,
    String category,
    String description,
    String status,
    String resolutionNote,
    Instant createdAt
) {
    public SupportRequest(String id, String studentID, String title, String category, String description) {
        this(id, studentID, title, category, description, "Submitted", null, Instant.now());
    }
}
