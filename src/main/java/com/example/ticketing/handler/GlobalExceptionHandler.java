package com.example.ticketing.handler;

import com.example.ticketing.handler.exception.ErrorResponse;
import com.example.ticketing.handler.exception.NotExistTicketException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        return ResponseEntity.status(500).body(ErrorResponse.of("500", "에러가 발생했습니다: " + e));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ErrorResponse.of("400", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        if (ex.getValue() == null) {
            throw new IllegalArgumentException("Parameter cannot be null");
        }
        return ResponseEntity.badRequest().body(ErrorResponse.of("400", ex.getMessage()));
    }

    @ExceptionHandler(NotExistTicketException.class)
    public ResponseEntity<ErrorResponse> handleNotExistTicketException(NotExistTicketException ex) {
        return ResponseEntity.badRequest().body(ErrorResponse.of("400", ex.getMessage()));
    }
}
