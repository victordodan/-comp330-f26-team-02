package edu.loyola.campusconnect.service;

import static edu.loyola.campusconnect.model.DemoIdentity.*;
import static org.junit.jupiter.api.Assertions.*;

import edu.loyola.campusconnect.model.DemoIdentity;
import edu.loyola.campusconnect.model.SupportRequest;
import edu.loyola.campusconnect.repository.JdbcRequestRepository;
import edu.loyola.campusconnect.repository.RequestRepository;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

class WorkflowServiceTest {
    private JdbcDataSource source;
    private JdbcTemplate jdbc;
    private RequestRepository repository;
    private WorkflowService service;

    @BeforeEach
    void setup() {
        source = dataSource("jdbc:h2:mem:service-" + UUID.randomUUID() + ";DB_CLOSE_DELAY=-1");
        initialize(source);
        jdbc = new JdbcTemplate(source);
        repository = new JdbcRequestRepository(jdbc);
        service = new WorkflowService(repository, new DataSourceTransactionManager(source));
    }

    @AfterEach
    void shutdown() {
        jdbc.execute("SHUTDOWN");
    }

    @Test
    void createsTrimmedOwnedRequestsWithDistinctIdsAndDefaults() {
        SupportRequest a = create(STUDENT_A);
        SupportRequest b = create(STUDENT_B);
        assertNotEquals(a.id(), b.id());
        assertEquals("Valid title", a.title());
        assertEquals("IT", a.category());
        assertEquals("Valid description", a.description());
        assertEquals("student-a", a.studentID());
        assertEquals("Submitted", a.status());
        assertEquals("", a.resolutionNote());
        assertEquals(0, a.version());
        assertEquals(a.createdAt(), a.updatedAt());
        assertEquals(a, service.getOwnRequest(STUDENT_A, a.id()));
    }

    @Test
    void rejectsInvalidInputWithoutStoringAnything() {
        expect("BAD_INPUT", () -> service.createRequest(STUDENT_A, "short", "Unknown", "Too short"));
        expect("BAD_INPUT", () -> service.createRequest(STUDENT_A, null, "IT", "Valid description"));
        assertTrue(service.getReviewerRequests(REVIEWER).isEmpty());
    }

    @Test
    void enforcesStudentOwnershipAndSeparateReviewerOperations() {
        SupportRequest a = create(STUDENT_A);
        SupportRequest b = create(STUDENT_B);
        assertEquals(List.of(a), service.getOwnRequests(STUDENT_A));
        assertEquals(List.of(b), service.getOwnRequests(STUDENT_B));
        expect("NOT_FOUND", () -> service.getOwnRequest(STUDENT_B, a.id()));
        expect("NOT_FOUND", () -> service.getOwnRequest(STUDENT_A, "unknown"));
        expect("DENIED", () -> service.getOwnRequest(REVIEWER, a.id()));
        expect("DENIED", () -> service.getOwnRequests(REVIEWER));
        expect("DENIED", () -> service.getReviewerRequests(STUDENT_A));
        expect("DENIED", () -> service.getReviewerRequest(STUDENT_A, a.id()));
        assertEquals(2, service.getReviewerRequests(REVIEWER).size());
        assertEquals(a, service.getReviewerRequest(REVIEWER, a.id()));
    }

    @Test
    void rejectsMissingInvalidOrWrongRoleContextBeforeAction() {
        expect("DENIED", () -> DemoIdentity.fromId(null));
        expect("DENIED", () -> DemoIdentity.fromId(""));
        expect("DENIED", () -> DemoIdentity.fromId("administrator"));
        expect("DENIED", () -> service.createRequest(null, "Valid title", "IT", "Valid description"));
        expect("DENIED", () -> service.createRequest(REVIEWER, "Valid title", "IT", "Valid description"));
        expect("DENIED", () -> service.getOwnRequests(null));
        expect("DENIED", () -> service.getReviewerRequests(null));
        SupportRequest request = create(STUDENT_A);
        expect("DENIED", () -> service.updateStatus(STUDENT_A, request.id(), "In Progress", 0));
        expect("DENIED", () -> service.updateStatus(null, request.id(), "In Progress", 0));
        expect("DENIED", () -> service.updateNote(STUDENT_B, request.id(), "forged", 0));
        assertEquals(request, service.getOwnRequest(STUDENT_A, request.id()));
    }

    @Test
    void permitsLifecycleAndPreservesResolutionOnReopen() {
        SupportRequest initial = create(STUDENT_A);
        SupportRequest progress = service.updateStatus(REVIEWER, initial.id(), "In Progress", 0);
        SupportRequest noted = service.updateNote(REVIEWER, initial.id(), "  Fixed WiFi  ", 1);
        SupportRequest resolved = service.updateStatus(REVIEWER, initial.id(), "Resolved", 2);
        SupportRequest reopened = service.updateStatus(REVIEWER, initial.id(), "In Progress", 3);
        assertEquals("In Progress", reopened.status());
        assertEquals("Fixed WiFi", reopened.resolutionNote());
        assertEquals(initial.id(), reopened.id());
        assertEquals(initial.studentID(), reopened.studentID());
        assertEquals(initial.createdAt(), reopened.createdAt());
        assertEquals(4, reopened.version());
        assertTrue(progress.updatedAt().isAfter(initial.updatedAt()));
        assertTrue(noted.updatedAt().isAfter(progress.updatedAt()));
        assertTrue(resolved.updatedAt().isAfter(noted.updatedAt()));
        assertEquals(reopened, service.getOwnRequest(STUDENT_A, initial.id()));
    }

    @Test
    void rejectsUnsupportedSameStateAndUnresolvedTransitionsWithoutChanges() {
        SupportRequest initial = create(STUDENT_A);
        for (String status : new String[] {null, "New", "Closed", "Submitted", "Resolved"}) {
            expect("BAD_INPUT", () -> service.updateStatus(REVIEWER, initial.id(), status, 0));
            assertEquals(initial, service.getOwnRequest(STUDENT_A, initial.id()));
        }
        SupportRequest progress = service.updateStatus(REVIEWER, initial.id(), "In Progress", 0);
        expect("BAD_INPUT", () -> service.updateStatus(REVIEWER, initial.id(), "Resolved", 1));
        expect("BAD_INPUT", () -> service.updateStatus(REVIEWER, initial.id(), "Submitted", 1));
        assertEquals(progress, service.getOwnRequest(STUDENT_A, initial.id()));
    }

    @Test
    void enforcesTrimmedNoteBoundariesAndResolvedClearingRules() {
        SupportRequest initial = create(STUDENT_A);
        SupportRequest maximum = service.updateNote(REVIEWER, initial.id(), " " + "a".repeat(2000) + " ", 0);
        assertEquals(2000, maximum.resolutionNote().length());
        expect("BAD_INPUT", () -> service.updateNote(REVIEWER, initial.id(), "a".repeat(2001), 1));
        assertEquals(maximum, service.getOwnRequest(STUDENT_A, initial.id()));
        SupportRequest cleared = service.updateNote(REVIEWER, initial.id(), null, 1);
        assertEquals("", cleared.resolutionNote());
        SupportRequest saved = service.updateNote(REVIEWER, initial.id(), "Saved resolution", 2);
        service.updateStatus(REVIEWER, initial.id(), "In Progress", saved.version());
        SupportRequest resolved = service.updateStatus(REVIEWER, initial.id(), "Resolved", 4);
        expect("BAD_INPUT", () -> service.updateNote(REVIEWER, initial.id(), "   ", 5));
        assertEquals(resolved, service.getOwnRequest(STUDENT_A, initial.id()));
        SupportRequest edited = service.updateNote(REVIEWER, initial.id(), "  Revised resolution  ", 5);
        assertEquals("Resolved", edited.status());
        assertEquals("Revised resolution", edited.resolutionNote());
    }

    @Test
    void rejectsUnknownIdsNegativeVersionsAndStaleStatusAndNoteWrites() {
        expect("NOT_FOUND", () -> service.updateStatus(REVIEWER, "missing", "In Progress", 0));
        expect("NOT_FOUND", () -> service.updateNote(REVIEWER, "missing", "note", 0));
        SupportRequest initial = create(STUDENT_A);
        expect("BAD_INPUT", () -> service.updateStatus(REVIEWER, initial.id(), "In Progress", -1));
        SupportRequest updated = service.updateNote(REVIEWER, initial.id(), "Saved note", 0);
        expect("CONFLICT", () -> service.updateStatus(REVIEWER, initial.id(), "In Progress", 0));
        expect("CONFLICT", () -> service.updateNote(REVIEWER, initial.id(), "Lost update", 0));
        assertEquals(updated, service.getOwnRequest(STUDENT_A, initial.id()));
    }

    @Test
    void atomicallyAllowsOnlyOneConcurrentStatusOrNoteSave() throws Exception {
        SupportRequest initial = create(STUDENT_A);
        CyclicBarrier loaded = new CyclicBarrier(2);
        RequestRepository interleaving = new ForwardingRepository(repository) {
            @Override
            public Optional<SupportRequest> findById(String id) {
                Optional<SupportRequest> result = super.findById(id);
                try { loaded.await(10, TimeUnit.SECONDS); }
                catch (Exception error) { throw new IllegalStateException(error); }
                return result;
            }
        };
        WorkflowService concurrent = new WorkflowService(interleaving, new DataSourceTransactionManager(source));
        var executor = Executors.newFixedThreadPool(2);
        try {
            var status = executor.submit(() -> outcome(() -> concurrent.updateStatus(REVIEWER, initial.id(), "In Progress", 0)));
            var note = executor.submit(() -> outcome(() -> concurrent.updateNote(REVIEWER, initial.id(), "Concurrent note", 0)));
            List<String> results = List.of(status.get(15, TimeUnit.SECONDS), note.get(15, TimeUnit.SECONDS));
            assertEquals(1, results.stream().filter("OK"::equals).count());
            assertEquals(1, results.stream().filter("CONFLICT"::equals).count());
            SupportRequest stored = service.getOwnRequest(STUDENT_A, initial.id());
            assertEquals(1, stored.version());
            assertTrue((stored.status().equals("In Progress") && stored.resolutionNote().isEmpty())
                    || (stored.status().equals("Submitted") && stored.resolutionNote().equals("Concurrent note")));
        } finally {
            executor.shutdownNow();
        }
    }

    @Test
    void rollsBackInsertWhenStorageFailsBeforeConfirmation() {
        WorkflowService failing = with(new ForwardingRepository(repository) {
            @Override public void insert(SupportRequest request) {
                super.insert(request);
                throw new DataAccessResourceFailureException("injected after insert");
            }
        });
        expect("STORAGE_ERROR", () -> failing.createRequest(STUDENT_A, "Valid title", "IT", "Valid description"));
        assertTrue(service.getReviewerRequests(REVIEWER).isEmpty());
    }

    @Test
    void rollsBackStatusAndNoteWhenStorageFailsAfterWrite() {
        SupportRequest initial = create(STUDENT_A);
        WorkflowService failing = with(new ForwardingRepository(repository) {
            @Override public boolean replace(SupportRequest request, long expectedVersion) {
                super.replace(request, expectedVersion);
                throw new DataAccessResourceFailureException("injected after update");
            }
        });
        expect("STORAGE_ERROR", () -> failing.updateStatus(REVIEWER, initial.id(), "In Progress", 0));
        assertEquals(initial, service.getOwnRequest(STUDENT_A, initial.id()));
        expect("STORAGE_ERROR", () -> failing.updateNote(REVIEWER, initial.id(), "Should roll back", 0));
        assertEquals(initial, service.getOwnRequest(STUDENT_A, initial.id()));
    }

    @Test
    void reportsFailedListingInsteadOfReturningAnEmptySuccess() {
        WorkflowService failing = with(new ForwardingRepository(repository) {
            @Override public List<SupportRequest> findAll() {
                throw new DataAccessResourceFailureException("injected read failure");
            }
            @Override public List<SupportRequest> findByStudentID(String id) {
                throw new DataAccessResourceFailureException("injected read failure");
            }
        });
        expect("STORAGE_ERROR", () -> failing.getReviewerRequests(REVIEWER));
        expect("STORAGE_ERROR", () -> failing.getOwnRequests(STUDENT_A));
    }

    @Test
    void preservesOwnershipStatusNotesAndUniqueIdsAcrossFileStoreRestart(@TempDir Path directory) {
        String url = "jdbc:h2:file:" + directory.resolve("requests").toAbsolutePath() + ";DB_CLOSE_ON_EXIT=FALSE";
        JdbcDataSource first = dataSource(url);
        initialize(first);
        WorkflowService before = withSource(first);
        SupportRequest created = before.createRequest(STUDENT_A, "Persisted title", "Academic", "Persisted description");
        SupportRequest note = before.updateNote(REVIEWER, created.id(), "Keep this note", 0);
        SupportRequest progress = before.updateStatus(REVIEWER, created.id(), "In Progress", note.version());
        new JdbcTemplate(first).execute("SHUTDOWN");
        JdbcDataSource reopened = dataSource(url);
        initialize(reopened);
        WorkflowService after = withSource(reopened);
        assertEquals(progress, after.getOwnRequest(DemoIdentity.fromId("student-a"), created.id()));
        assertTrue(after.getOwnRequests(DemoIdentity.fromId("student-b")).isEmpty());
        SupportRequest another = after.createRequest(STUDENT_A, "Another title", "Other", "Another description");
        assertNotEquals(created.id(), another.id());
        assertEquals(2, after.getReviewerRequests(REVIEWER).size());
        new JdbcTemplate(reopened).execute("SHUTDOWN");
    }

    private SupportRequest create(DemoIdentity actor) {
        return service.createRequest(actor, "  Valid title  ", " IT ", "  Valid description  ");
    }

    private static JdbcDataSource dataSource(String url) {
        JdbcDataSource source = new JdbcDataSource();
        source.setURL(url);
        source.setUser("sa");
        source.setPassword("");
        return source;
    }

    private static void initialize(JdbcDataSource source) {
        new ResourceDatabasePopulator(new ClassPathResource("schema.sql")).execute(source);
    }

    private WorkflowService with(RequestRepository replacement) {
        return new WorkflowService(replacement, new DataSourceTransactionManager(source));
    }

    private static WorkflowService withSource(JdbcDataSource source) {
        return new WorkflowService(new JdbcRequestRepository(new JdbcTemplate(source)), new DataSourceTransactionManager(source));
    }

    private static void expect(String code, Runnable action) {
        assertEquals(code, assertThrows(WorkflowException.class, action::run).getCode());
    }

    private static String outcome(Runnable action) {
        try { action.run(); return "OK"; }
        catch (WorkflowException failure) { return failure.getCode(); }
    }

    private static class ForwardingRepository implements RequestRepository {
        private final RequestRepository delegate;
        ForwardingRepository(RequestRepository delegate) { this.delegate = delegate; }
        @Override public void insert(SupportRequest request) { delegate.insert(request); }
        @Override public Optional<SupportRequest> findById(String id) { return delegate.findById(id); }
        @Override public List<SupportRequest> findByStudentID(String id) { return delegate.findByStudentID(id); }
        @Override public List<SupportRequest> findAll() { return delegate.findAll(); }
        @Override public boolean replace(SupportRequest request, long version) { return delegate.replace(request, version); }
    }
}
