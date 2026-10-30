package edu.loyola.campusconnect.service;

/** Safe failures exclude stored content and database internals. */
public class WorkflowException extends RuntimeException {
    private final String code;
    public WorkflowException(String code, String message) {
        super(message);
        this.code = code;
    }
    public String getCode() { return code; }
}
