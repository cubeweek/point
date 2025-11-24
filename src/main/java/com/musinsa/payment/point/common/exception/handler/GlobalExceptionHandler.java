package com.musinsa.payment.point.common.exception.handler;

import com.musinsa.payment.point.common.exception.PointValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PointValidationException.class)
    public ResponseEntity<?> handlePointException(PointValidationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getErrorMsg());
    }
}
