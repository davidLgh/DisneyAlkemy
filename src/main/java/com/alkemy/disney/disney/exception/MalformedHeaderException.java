package com.alkemy.disney.disney.exception;

public class MalformedHeaderException extends BadRequestException{

    private static final String DESCRIPTION = "Token with a wrong format";

    public MalformedHeaderException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
