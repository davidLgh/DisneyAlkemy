package com.alkemy.disney.disney.exception;

public class FieldAlreadyExistException extends ConflictException {
    private static final String DESCRIPTION = "Field already exist.";

    public FieldAlreadyExistException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
