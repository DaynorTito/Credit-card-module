package org.company.com.controllers.exceptions;

public class RequestNullException extends ControllerException {

    public static final String msg = "Request is null";

    public RequestNullException() {
        super(msg);
    }
}
