package edu.loyola.campusconnect.model;

import edu.loyola.campusconnect.service.WorkflowException;

/** Fixed classroom identities; selection demonstrates roles rather than real authentication. */
public enum DemoIdentity {
    STUDENT_A("student-a"), STUDENT_B("student-b"), REVIEWER("reviewer");

    private final String id;
    DemoIdentity(String id) { this.id = id; }
    public String id() { return id; }
    public boolean isStudent() { return this != REVIEWER; }

    public static DemoIdentity fromId(String id) {
        for (DemoIdentity identity : values()) {
            if (identity.id.equals(id)) { return identity; }
        }
        throw new WorkflowException("DENIED", "Select a valid demo identity.");
    }
}
