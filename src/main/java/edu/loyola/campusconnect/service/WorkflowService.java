package edu.loyola.campusconnect.service;

import edu.loyola.campusconnect.model.SupportRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// Service managing the lifecycle, in-memory persistence, and status updates for CampusConnect support requests
@Service
public class WorkflowService {
    // Thread-safe in-memory storage for support requests
    private final ConcurrentHashMap<String, SupportRequest> requestStore = new ConcurrentHashMap<>();

    // Counter for generating unique request IDs (i.e. REQ-1001)
    private final AtomicLong idSequence = new AtomicLong(1000);

    // Creates and stores a new support request with default initial state ("Submitted")
    public SupportRequest createRequest(String studentID, String title, String category, String description) {
        String id = "REQ-" + idSequence.incrementAndGet();

      // Create a new SupportRequest object using the 5-argument constructor
        // which fills in default status, resolution note, and timestamp
        SupportRequest request = new SupportRequest(id, studentID, title, category, description);
      // Store the new request in the map (in-memory "database")
        // Keys are the generated id
        // Values are the request object
        requestStore.put(id, request);
        return request;
    }

    // Retrieves support requests visible to a user, filtered by role
    public List<SupportRequest> getRequestsForUser(String userID, String userRole) {
        if ("REVIEWER".equalsIgnoreCase(userRole)) {
            return List.copyOf(requestStore.values());
        }
        return requestStore.values().stream()
            .filter(req -> req.studentID().equalsIgnoreCase(userID))
            .toList();
    }

    // Retrieves a single support request by its ID
    public Optional<SupportRequest> getRequestById(String id) {
        return Optional.ofNullable(requestStore.get(id));
    }

    // Updates the status and resolution note of an existing support request
    public Optional<SupportRequest> updateStatus(String id, String newStatus, String resolutionNote) {
    return Optional.ofNullable(requestStore.computeIfPresent(id, (reqId, existing) ->
        new SupportRequest(
            existing.id(),
            existing.studentID(),
            existing.title(), existing.category(), existing.description(),
            newStatus,
            resolutionNote,
            existing.createdAt()
        )
    ));
    }
}
