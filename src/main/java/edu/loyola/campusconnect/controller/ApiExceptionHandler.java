package edu.loyola.campusconnect.controller;

import edu.loyola.campusconnect.service.WorkflowException;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(WorkflowException.class)
    public ResponseEntity<Map<String, Object>> workflowError(WorkflowException ex) {
        int status = switch (ex.getCode()) {
            case "BAD_INPUT" -> 400;
            case "DENIED" -> 403;
            case "NOT_FOUND" -> 404;
            case "CONFLICT" -> 412;
            case "PRECONDITION_REQUIRED" -> 428;
            default -> 503;
        };
        return error(status, ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> unreadableBody() {
        return error(400, "BAD_INPUT", "Supply a valid JSON request body.");
    }

    @ExceptionHandler({DataAccessException.class, TransactionException.class})
    public ResponseEntity<Map<String, Object>> storageError() {
        return error(503, "STORAGE_ERROR", "Storage is unavailable; no success is confirmed.");
    }

    private static ResponseEntity<Map<String, Object>> error(int status, String code, String message) {
        return ResponseEntity.status(status).body(Map.of("code", code, "errors", List.of(message)));
    }
}
