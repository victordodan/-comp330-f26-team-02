package edu.loyola.campusconnect.repository;

import edu.loyola.campusconnect.model.SupportRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcRequestRepository implements RequestRepository {
    private final JdbcTemplate jdbc;
    public JdbcRequestRepository(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    @Override
    public void insert(SupportRequest request) {
        jdbc.update("""
                INSERT INTO support_request
                (id, title, category, description, student_id, status, resolution_note,
                 created_at, updated_at, version)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """, request.id(), request.title(), request.category(), request.description(),
                request.studentID(), request.status(), request.resolutionNote(),
                request.createdAt().atOffset(ZoneOffset.UTC),
                request.updatedAt().atOffset(ZoneOffset.UTC), request.version());
    }

    @Override
    public Optional<SupportRequest> findById(String id) {
        return jdbc.query("SELECT * FROM support_request WHERE id = ?", this::map, id)
                .stream().findFirst();
    }

    @Override
    public List<SupportRequest> findByStudentID(String studentID) {
        return jdbc.query("SELECT * FROM support_request WHERE student_id = ? ORDER BY created_at, id",
                this::map, studentID);
    }

    @Override
    public List<SupportRequest> findAll() {
        return jdbc.query("SELECT * FROM support_request ORDER BY created_at, id", this::map);
    }

    @Override
    public boolean replace(SupportRequest request, long expectedVersion) {
        return jdbc.update("""
                UPDATE support_request
                SET status = ?, resolution_note = ?, updated_at = ?, version = ?
                WHERE id = ? AND version = ?
                """, request.status(), request.resolutionNote(),
                request.updatedAt().atOffset(ZoneOffset.UTC), request.version(),
                request.id(), expectedVersion) == 1;
    }

    private SupportRequest map(ResultSet rs, int row) throws SQLException {
        return new SupportRequest(rs.getString("id"), rs.getString("student_id"), rs.getString("title"),
                rs.getString("category"), rs.getString("description"), rs.getString("status"),
                rs.getString("resolution_note"),
                rs.getObject("created_at", OffsetDateTime.class).toInstant(),
                rs.getObject("updated_at", OffsetDateTime.class).toInstant(), rs.getLong("version"));
    }
}
