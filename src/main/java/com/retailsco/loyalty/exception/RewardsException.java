package com.retailsco.loyalty.exception;

/**
 * Custom exception thrown when reward-related errors occur.
 */
public class RewardsException extends RuntimeException {
    /**
     * Creates a new RewardsException with the given message.
     *
     * @param message error message
     */
    public RewardsException(String message) {
        super(message);
    }
}
