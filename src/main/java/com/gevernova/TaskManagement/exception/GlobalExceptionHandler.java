package com.gevernova.TaskManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice // Handles exceptions globally for all controllers
public class GlobalExceptionHandler {

    // Handles UserNotFoundException and returns 404 response
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex) {
        return buildError(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Handles TaskNotFoundException and returns 404 response
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<Object> handleTaskNotFound(TaskNotFoundException ex) {
        return buildError(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Handles any general exception and returns 500 response
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneral(Exception ex) {
        return buildError(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Common method to build structured error response
    private ResponseEntity<Object> buildError(String message, HttpStatus status) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("timestamp", LocalDateTime.now()); // Current time
        map.put("status", status.value()); // HTTP status code
        map.put("error", status.getReasonPhrase()); // Reason phrase (example: NOT_FOUND)
        map.put("message", message); // Actual exception message

        return new ResponseEntity<>(map, status);
    }
}

