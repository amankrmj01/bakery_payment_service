package com.shah_s.bakery_payment_service.exception;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(PaymentServiceException.class)
    public ResponseEntity<ErrorResponse> handlePaymentServiceException(PaymentServiceException ex, WebRequest request) {
        logger.error("Payment service error: {}", ex.getMessage());

        ErrorResponse error = new ErrorResponse(
            "PAYMENT_SERVICE_ERROR",
            ex.getMessage(),
            LocalDateTime.now(),
            request.getDescription(false)
        );

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponse> handleFeignException(FeignException ex, WebRequest request) {
        logger.error("External service error: {}", ex.getMessage());

        String message = "External service unavailable";
        HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;

        if (ex.status() == 404) {
            message = "Requested resource not found";
            status = HttpStatus.NOT_FOUND;
        } else if (ex.status() == 400) {
            message = "Invalid request to external service";
            status = HttpStatus.BAD_REQUEST;
        }

        ErrorResponse error = new ErrorResponse(
            "EXTERNAL_SERVICE_ERROR",
            message,
            LocalDateTime.now(),
            request.getDescription(false)
        );

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {
        logger.error("Validation error: {}", ex.getMessage());

        Map<String, String> validationErrors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            validationErrors.put(fieldName, errorMessage);
        });

        ErrorResponse error = new ErrorResponse(
            "VALIDATION_ERROR",
            "Invalid input data",
            LocalDateTime.now(),
            request.getDescription(false)
        );
        error.setValidationErrors(validationErrors);

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
        logger.error("Unexpected error: {}", ex.getMessage(), ex);

        ErrorResponse error = new ErrorResponse(
            "INTERNAL_ERROR",
            "An unexpected error occurred",
            LocalDateTime.now(),
            request.getDescription(false)
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    // Error Response Class
    public static class ErrorResponse {
        private String code;
        private String message;
        private LocalDateTime timestamp;
        private String path;
        private Map<String, String> validationErrors;

        public ErrorResponse(String code, String message, LocalDateTime timestamp, String path) {
            this.code = code;
            this.message = message;
            this.timestamp = timestamp;
            this.path = path;
        }

        // Getters and Setters
        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }

        public LocalDateTime getTimestamp() { return timestamp; }
        public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }

        public Map<String, String> getValidationErrors() { return validationErrors; }
        public void setValidationErrors(Map<String, String> validationErrors) { this.validationErrors = validationErrors; }
    }
}
