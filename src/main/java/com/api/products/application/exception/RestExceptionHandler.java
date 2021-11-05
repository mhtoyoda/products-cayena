package com.api.products.application.exception;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

  private ResponseEntity<Map<String, Object>> error(HttpStatus status, Map<String, Object> body) {
    body.put("timestamp", new Date());
    body.put("status", status.value());
    body.put("httpStatus", status.getReasonPhrase());
    return ResponseEntity.status(status).body(body);
  }

  @ExceptionHandler({SuppliersNotFoundException.class, ProductNotFoundException.class})
  public ResponseEntity<Map<String, Object>> handleNotFoundException(RuntimeException exception) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("message", exception.getMessage());
    return error(HttpStatus.NOT_FOUND, body);
  }

  @ExceptionHandler({SuppliersBadRequestException.class})
  public ResponseEntity<Map<String, Object>> handleBadRequestException(RuntimeException exception) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("message", exception.getMessage());
    return error(HttpStatus.BAD_REQUEST, body);
  }
}
