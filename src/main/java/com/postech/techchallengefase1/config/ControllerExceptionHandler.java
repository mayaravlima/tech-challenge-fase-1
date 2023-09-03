package com.postech.techchallengefase1.config;


import com.postech.techchallengefase1.domain.exception.ApiException;
import com.postech.techchallengefase1.domain.exception.ValidationErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        ValidationErrorResponse errorResponse = new ValidationErrorResponse(errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleJsonErrors(HttpMessageNotReadableException error) {
        Map<String, String> errorResponse = Map.of("error", "Invalid JSON");
        if (error.getMessage().contains("Relationship")) {
            errorResponse = Map.of("error", "Relationship must be one of the values accepted: [" +
                    "PARENT," +
                    "SPOUSE, " +
                    "SIBLING, " +
                    "PARTNER, " +
                    "RELATIVE]");
        } else if (error.getMessage().contains("Gender")) {
            errorResponse = Map.of("error", "Gender must be one of the values accepted: [FEMALE, MALE]");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Map<String, String>> handleDataError(ApiException error) {
        return ResponseEntity.status(error.getStatus()).body(Map.of("error", error.getMessage()));

    }
}
