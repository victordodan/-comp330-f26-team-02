package edu.loyola.campusconnect.model;

import java.time.Instant;

/** Immutable request data; workflow rules and persistence belong to the service and repository. */
public record SupportRequest(
    String id,
    String studentID,
    String title,
    String category,
    String description,
    String status,
    String resolutionNote,
    Instant createdAt,
    Instant updatedAt,
    long version
) {
    public SupportRequest(String id, String studentID, String title, String category, String description) {
        this(id, studentID, title, category, description, Instant.now());
    }

    private SupportRequest(String id, String studentID, String title, String category,
            String description, Instant now) {
        this(id, studentID, title, category, description, "Submitted", "", now, now, 0L);
    }
}
