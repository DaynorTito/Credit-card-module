package org.company.com.controllers.exceptions;

public class FieldsNotValidException extends ControllerException {
    public FieldsNotValidException(String field) {
        super("Field <<" + field + ">> is not valid.");
    }
}
