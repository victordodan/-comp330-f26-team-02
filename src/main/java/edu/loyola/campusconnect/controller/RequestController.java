package edu.loyola.campusconnect.controller;

import edu.loyola.campusconnect.model.CreateRequestDto;
import edu.loyola.campusconnect.model.SupportRequest;
import edu.loyola.campusconnect.model.UpdateStatusDto;
import edu.loyola.campusconnect.service.WorkflowService;
import edu.loyola.campusconnect.validation.RequestValidator;
import edu.loyola.campusconnect.validation.RequestValidator.ValidationResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

// REST Controller exposing HTTP API endpoints for CampusConnect support requests
  // Connects incoming HTTP requests to RequestValidator and WorkflowService

@RestController
@RequestMapping("/api/requests")
public class RequestController {

  // Whitelist of allowed status values, anything else is rejected
    private static final Set<String> VALID_STATUSES = Set.of(
        "Submitted", "In Review", "Needs Information", "Resolved", "Closed", "Reopened"
    );
  // Service layer, holdes the business logic
    private final WorkflowService workflowService;

  // Constructor injection
    public RequestController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

  // POST /api/requests
    // Student submits a new support request
      // Integrates RequestValidator to enforce API-001 field boundaries

    @PostMapping
    public ResponseEntity<?> createRequest(@RequestBody CreateRequestDto dto) {
      // Turn the incoming JSON body into a CreateRequestsDTO
        // Validator checks these
        ValidationResult validation = RequestValidator.validate(
            dto.title(),
            dto.category(),
            dto.description()
        );
      // If validation fails, return HTTP 400 Bad Request
        // With list of error messages as JSON
        if (!validation.valid()) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("errors", validation.errors()));
        }
      // Validation passes, creates request vi service
        // Passes validation not DTO because input may be trimmed/ cleaned up
        SupportRequest created = workflowService.createRequest(
            dto.studentID() != null ? dto.studentID() : "student-123",
            validation.title(),
            validation.category(),
            validation.description()
        );
      // Return HTTP 201
        // "A resource was created"
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // GET /api/requests
      // Retrieves all support requests for review.
    @GetMapping
    public ResponseEntity<List<SupportRequest>> getAllRequests() {
      // Delegates entirely to the servise, the wrap the list
        // If 200 OK, spring serialized the list to JSON
        return ResponseEntity.ok(workflowService.getAllRequests());
    }
    // PATCH /api/requests/{id}/status
      // Reviewer updates ticket status and attaches resolution notes.
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(
        // ID from URL path
            @PathVariable String id,
      // JSON body with status + note
            @RequestBody UpdateStatusDto dto) {
      // Guard-rail for status
        // Present and values allowed, otherwise 400 and message
        if (dto.status() == null || !VALID_STATUSES.contains(dto.status())) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", "Status must be Submitted, In Review, Needs Information, Resolved, or Closed."));
        }
      // Delegates to service, returns present or empty
        // If present, wrap updated request in 200 OK
        // If empty, return 404 Not Found and empty body
        return workflowService.updateStatus(id, dto.status(), dto.resolutionNote())
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
