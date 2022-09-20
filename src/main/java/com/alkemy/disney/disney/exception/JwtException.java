package com.alkemy.disney.disney.exception;

public class JwtException extends MalformedHeaderException{
    private static final String DESCRIPTION = "Invalid JWT token";

    public JwtException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
