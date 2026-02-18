package com.retailsco.loyalty.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for REST APIs.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles RewardsException.
     *
     * @param e the exception
     * @return bad request response with error message
     */
    @ExceptionHandler(RewardsException.class)
    public ResponseEntity<String> handleRewardsException(RewardsException e) {
        log.error("RewardsException:{}", e.getMessage(), e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    /**
     * Handles DataAccessException.
     *
     * @param e the exception
     * @return internal server error response with error message
     */
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDataAccessException(DataAccessException e) {
        log.error("DataAccessException:{}", e.getMessage(), e);
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

    /**
     * Handles unexpected exceptions.
     *
     * @param e the exception
     * @return internal server error response with error message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        log.error("Unexpected error:{}", e.getMessage(), e);
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

}
