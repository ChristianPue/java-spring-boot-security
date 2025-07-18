package com.backend.api.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Map<String, String>> handleResourceNotFoundException(ResourceNotFoundException ex) {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put("error", "ResourceNotFoundException");
    errorResponse.put("message", ex.getMessage());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<Map<String, String>> handleBadRequestException(BadRequestException ex) {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put("error", "BadRequestException");
    errorResponse.put("message", ex.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }
}
