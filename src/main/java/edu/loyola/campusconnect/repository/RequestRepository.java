package edu.loyola.campusconnect.repository;

import edu.loyola.campusconnect.model.SupportRequest;
import java.util.List;
import java.util.Optional;

public interface RequestRepository {
    void insert(SupportRequest request);
    Optional<SupportRequest> findById(String id);
    List<SupportRequest> findByStudentID(String studentID);
    List<SupportRequest> findAll();
    boolean replace(SupportRequest request, long expectedVersion);
}
