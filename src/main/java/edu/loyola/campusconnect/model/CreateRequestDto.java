package edu.loyola.campusconnect.model;

// Data Transfer Object (DTO) representing the JSON payload sent by a student
// when creating a new support request via POST /api/requests

// Create public record called Create Requests DTO
// Using record because its immutable and automatically converts HTTP JSON requests
// Create strings for title, category, and description
public record CreateRequestDto(
  String title,
  String category,
  String description
) {}
