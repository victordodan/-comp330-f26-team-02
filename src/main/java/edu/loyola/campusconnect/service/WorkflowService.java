package edu.loyola.campusconnect.service;

import edu.loyola.campusconnect.model.DemoIdentity;
import edu.loyola.campusconnect.model.SupportRequest;
import edu.loyola.campusconnect.repository.RequestRepository;
import edu.loyola.campusconnect.validation.RequestValidator;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.TransactionTemplate;

/** Authoritative validation, permissions and lifecycle rules for the classroom workflow. */
@Service
public class WorkflowService {
    private final RequestRepository repository;
    private final TransactionTemplate transactions;

    public WorkflowService(RequestRepository repository, PlatformTransactionManager transactionManager) {
        this.repository = repository;
        this.transactions = new TransactionTemplate(transactionManager);
    }

    public SupportRequest createRequest(DemoIdentity actor, String title, String category, String description) {
        requireStudent(actor);
        var result = RequestValidator.validate(title, category, description);
        if (!result.valid()) {
            throw failure("BAD_INPUT", String.join(" ", result.errors()));
        }
        return transaction(() -> {
            Instant now = Instant.now();
            SupportRequest request = new SupportRequest(UUID.randomUUID().toString(), actor.id(), result.title(),
                    result.category(), result.description(), "Submitted", "", now, now, 0);
            repository.insert(request);
            return request;
        });
    }

    public List<SupportRequest> getOwnRequests(DemoIdentity actor) {
        requireStudent(actor);
        return transaction(() -> repository.findByStudentID(actor.id()));
    }

    public SupportRequest getOwnRequest(DemoIdentity actor, String id) {
        requireStudent(actor);
        return transaction(() -> {
            SupportRequest request = existing(id);
            if (!request.studentID().equals(actor.id())) {
                throw failure("NOT_FOUND", "Request not found.");
            }
            return request;
        });
    }

    public List<SupportRequest> getReviewerRequests(DemoIdentity actor) {
        requireReviewer(actor);
        return transaction(repository::findAll);
    }

    public SupportRequest getReviewerRequest(DemoIdentity actor, String id) {
        requireReviewer(actor);
        return transaction(() -> existing(id));
    }

    public SupportRequest updateStatus(DemoIdentity actor, String id, String newStatus, long expectedVersion) {
        requireReviewer(actor);
        return transaction(() -> {
            SupportRequest request = current(id, expectedVersion);
            boolean permitted = (request.status().equals("Submitted") && "In Progress".equals(newStatus))
                    || (request.status().equals("In Progress") && "Resolved".equals(newStatus))
                    || (request.status().equals("Resolved") && "In Progress".equals(newStatus));
            if (!permitted) {
                throw failure("BAD_INPUT", "Unsupported status transition.");
            }
            if ("Resolved".equals(newStatus) && request.resolutionNote().isBlank()) {
                throw failure("BAD_INPUT", "Save nonblank resolution text before resolving.");
            }
            return save(request, newStatus, request.resolutionNote(), expectedVersion);
        });
    }

    public SupportRequest updateNote(DemoIdentity actor, String id, String note, long expectedVersion) {
        requireReviewer(actor);
        String cleanNote = note == null ? "" : note.strip();
        if (cleanNote.length() > 2000) {
            throw failure("BAD_INPUT", "Resolution text must not exceed 2000 characters.");
        }
        return transaction(() -> {
            SupportRequest request = current(id, expectedVersion);
            if (request.status().equals("Resolved") && cleanNote.isEmpty()) {
                throw failure("BAD_INPUT", "Resolution text cannot be cleared while Resolved.");
            }
            return save(request, request.status(), cleanNote, expectedVersion);
        });
    }

    private SupportRequest save(SupportRequest request, String status, String note, long expectedVersion) {
        Instant now = Instant.now();
        Instant updatedAt = now.isAfter(request.updatedAt()) ? now : request.updatedAt().plusNanos(1);
        SupportRequest updated = new SupportRequest(request.id(), request.studentID(), request.title(), request.category(),
                request.description(), status, note, request.createdAt(),
                updatedAt, request.version() + 1);
        if (!repository.replace(updated, expectedVersion)) {
            throw failure("CONFLICT", "Request changed; reload it before saving.");
        }
        return updated;
    }

    private SupportRequest current(String id, long expectedVersion) {
        if (expectedVersion < 0) {
            throw failure("BAD_INPUT", "A nonnegative expected version is required.");
        }
        SupportRequest request = existing(id);
        if (request.version() != expectedVersion) {
            throw failure("CONFLICT", "Request changed; reload it before saving.");
        }
        return request;
    }

    private SupportRequest existing(String id) {
        return repository.findById(id).orElseThrow(() -> failure("NOT_FOUND", "Request not found."));
    }

    private void requireStudent(DemoIdentity actor) {
        if (actor == null || !actor.isStudent()) {
            throw failure("DENIED", "A valid student demo identity is required.");
        }
    }

    private void requireReviewer(DemoIdentity actor) {
        if (actor != DemoIdentity.REVIEWER) {
            throw failure("DENIED", "The Reviewer demo identity is required.");
        }
    }

    private <T> T transaction(Supplier<T> work) {
        try {
            return transactions.execute(status -> work.get());
        } catch (DataAccessException | TransactionException error) {
            throw failure("STORAGE_ERROR", "Storage operation failed; no success was confirmed.");
        }
    }

    private WorkflowException failure(String code, String message) {
        return new WorkflowException(code, message);
    }
}
