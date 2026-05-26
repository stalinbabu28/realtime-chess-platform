package com.stalinbabu.backend.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            EmailAlreadyExistsException.class
    )
    public ResponseEntity<?> handleEmailExists(
            EmailAlreadyExistsException ex
    ) {

        return ResponseEntity
                .status(
                        HttpStatus.CONFLICT
                )
                .body(
                        Map.of(
                                "message",
                                ex.getMessage()
                        )
                );

    }

}