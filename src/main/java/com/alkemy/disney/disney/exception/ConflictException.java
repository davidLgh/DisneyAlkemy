package com.alkemy.disney.disney.exception;

public class ConflictException extends RuntimeException{
    //Excepcion: se quiere crear objeto que ya existe en DB
    private static final String DESCRIPTION = "Conflict Exception (409)";

    public ConflictException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
