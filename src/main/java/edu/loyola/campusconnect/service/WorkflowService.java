package edu.loyola.campusconnect.service;

import edu.loyola.campusconnect.model.SupportRequest;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// Service managing the lifecycle, in-memory persistence, and status updates for CampusConnect support requests
@Service
public class WorkflowService {
    // Thread-safe in-memory storage for support requests
    private final Map<String, SupportRequest> requestStore = new ConcurrentHashMap<>();
    
    // Counter for generating unique request IDs (i.e. REQ-1001)
    private final AtomicLong idCounter = new AtomicLong(1000);

    // Creates and stores a new support request with default initial state ("New")
    public SupportRequest createRequest(String title, String category, String description) {
        String requestId = "REQ-" + idCounter.incrementAndGet();

      // Create a new SupportRequest object using the constructor which takes 7 arguments
        SupportRequest request = new SupportRequest(
          // Top 4 provided by caller
            requestId,
            title,
            category,
            description,
          // Default lifecycle status
            "New",
          // Default empty resolution note
            "",
          // Creation timestamp
            Instant.now()
        );
      // Store the new request in the map (in-memory "database")
        // Keys are requestId
        // Values are the request object
        requestStore.put(requestId, request);
        return request;
    }

    // Retrieves all support requests currently stored in memory
    public List<SupportRequest> getAllRequests() {
        return new ArrayList<>(requestStore.values());
    }

    // Retrieves a single support request by its ID
    public Optional<SupportRequest> getRequestById(String id) {
        return Optional.ofNullable(requestStore.get(id));
    }

    // Updates the status and resolution note of an existing support request
    public Optional<SupportRequest> updateStatus(String id, String newStatus, String resolutionNote) {
      // Look up the existing request in the store by its ID
        // If no request with that ID exists, 'existing' will be null
      SupportRequest existing = requestStore.get(id);
        if (existing == null) {
            return Optional.empty();
        }
      // Build a NEW SupportRequest object (immutable update pattern)
        // Copy fields and replace only the ones that are being changed (status + resolution)
        SupportRequest updated = new SupportRequest(
            existing.id(),
            existing.title(),
            existing.category(),
            existing.description(),
            newStatus,
            resolutionNote != null ? resolutionNote.strip() : "",
            existing.createdAt()
        );
        requestStore.put(id, updated);
        return Optional.of(updated);
    }
}
