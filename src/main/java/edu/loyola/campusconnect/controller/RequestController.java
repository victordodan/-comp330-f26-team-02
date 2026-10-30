package edu.loyola.campusconnect.controller;

import edu.loyola.campusconnect.model.CreateRequestDto;
import edu.loyola.campusconnect.model.DemoIdentity;
import edu.loyola.campusconnect.model.SupportRequest;
import edu.loyola.campusconnect.model.UpdateNoteDto;
import edu.loyola.campusconnect.model.UpdateStatusDto;
import edu.loyola.campusconnect.service.WorkflowException;
import edu.loyola.campusconnect.service.WorkflowService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** Local demonstration API; permissions are enforced again in the service. */
@RestController
public class RequestController {
    public static final String DEMO_IDENTITY = "campusconnect.demoIdentity";
    private final WorkflowService workflowService;

    public RequestController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    /** Intentionally permits switching fixed synthetic users; this is not authentication. */
    @PostMapping("/api/demo/identity")
    public Map<String, String> selectIdentity(@RequestBody DemoSelection selection, HttpSession session) {
        DemoIdentity identity = DemoIdentity.fromId(selection.identity());
        session.setAttribute(DEMO_IDENTITY, identity.id());
        return Map.of("identity", identity.id());
    }

    @PostMapping("/api/requests")
    public ResponseEntity<SupportRequest> createRequest(@RequestBody CreateRequestDto dto, HttpSession session) {
        SupportRequest created = workflowService.createRequest(actor(session), dto.title(), dto.category(), dto.description());
        return response(HttpStatus.CREATED, created);
    }

    @GetMapping("/api/requests")
    public List<SupportRequest> getOwnRequests(HttpSession session) {
        return workflowService.getOwnRequests(actor(session));
    }

    @GetMapping("/api/requests/{id}")
    public ResponseEntity<SupportRequest> getOwnRequest(@PathVariable String id, HttpSession session) {
        return response(HttpStatus.OK, workflowService.getOwnRequest(actor(session), id));
    }

    @GetMapping("/api/reviewer/requests")
    public List<SupportRequest> getReviewerRequests(HttpSession session) {
        return workflowService.getReviewerRequests(actor(session));
    }

    @GetMapping("/api/reviewer/requests/{id}")
    public ResponseEntity<SupportRequest> getReviewerRequest(@PathVariable String id, HttpSession session) {
        return response(HttpStatus.OK, workflowService.getReviewerRequest(actor(session), id));
    }

    @PatchMapping("/api/requests/{id}/status")
    public ResponseEntity<SupportRequest> updateStatus(@PathVariable String id,
            @RequestBody UpdateStatusDto dto,
            @RequestHeader(value = "If-Match", required = false) String ifMatch, HttpSession session) {
        DemoIdentity identity = reviewer(session);
        if (dto.resolutionNote() != null) {
            throw new WorkflowException("BAD_INPUT", "Save notes through the note endpoint before changing status.");
        }
        return response(HttpStatus.OK, workflowService.updateStatus(identity, id, dto.status(), version(ifMatch)));
    }

    @PatchMapping("/api/requests/{id}/note")
    public ResponseEntity<SupportRequest> updateNote(@PathVariable String id,
            @RequestBody UpdateNoteDto dto,
            @RequestHeader(value = "If-Match", required = false) String ifMatch, HttpSession session) {
        return response(HttpStatus.OK, workflowService.updateNote(reviewer(session), id, dto.resolutionNote(), version(ifMatch)));
    }

    private static DemoIdentity actor(HttpSession session) {
        Object selected = session.getAttribute(DEMO_IDENTITY);
        if (!(selected instanceof String id)) {
            throw new WorkflowException("DENIED", "Select a valid demo identity before using this operation.");
        }
        return DemoIdentity.fromId(id);
    }

    private static DemoIdentity reviewer(HttpSession session) {
        DemoIdentity identity = actor(session);
        if (identity != DemoIdentity.REVIEWER) {
            throw new WorkflowException("DENIED", "The Reviewer demo identity is required.");
        }
        return identity;
    }

    private static long version(String ifMatch) {
        if (ifMatch == null || ifMatch.isBlank()) {
            throw new WorkflowException("PRECONDITION_REQUIRED", "Supply the version from the request ETag in If-Match.");
        }
        String value = ifMatch.strip();
        if (!value.matches("\"[0-9]+\"")) {
            throw new WorkflowException("BAD_INPUT", "If-Match must contain one quoted numeric request version.");
        }
        try {
            return Long.parseLong(value.substring(1, value.length() - 1));
        } catch (NumberFormatException ex) {
            throw new WorkflowException("BAD_INPUT", "The request version is out of range.");
        }
    }

    private static ResponseEntity<SupportRequest> response(HttpStatus status, SupportRequest request) {
        return ResponseEntity.status(status).eTag("\"" + request.version() + "\"").body(request);
    }

    public record DemoSelection(String identity) {}
}
