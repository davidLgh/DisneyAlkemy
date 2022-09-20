package com.alkemy.disney.disney.exception;

public class MailException extends RuntimeException{
    private static final String DESCRIPTION = "Email with a wrong format";

    public MailException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
