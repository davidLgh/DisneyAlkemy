package com.alkemy.disney.disney.exception;

public class FieldInvalidException extends BadRequestException{
    private static final String DESCRIPTION = "Invalid field.";

    public FieldInvalidException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
