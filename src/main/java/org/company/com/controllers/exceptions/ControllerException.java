package org.company.com.controllers.exceptions;

/**
 * Base Exception for the controllers.
 * Only the exceptions that extends from this class can be catch in the controllers.
 */
public class ControllerException extends Exception {
    public ControllerException(String message) {
        super(message);
    }
}
