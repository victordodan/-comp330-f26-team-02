package edu.loyola.campusconnect.model;

// Data Transfer Object (DTO) representing the JSON payload sent by a reviewer
// when updating a support request status via PATCH /api/requests/{id}/status

public record UpdateStatusDto(
  // Status of the ticket ("Submitted", "In Progress", "Resolved")
    String status,
  // Notes for explaining the resolution, change, or overall details of student's issue
    String resolutionNote
) {}
