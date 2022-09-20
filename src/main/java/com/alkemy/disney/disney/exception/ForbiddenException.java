package com.alkemy.disney.disney.exception;

public class ForbiddenException extends RuntimeException{
    //Excepcion: accion no permitida para el rol
    private static final String DESCRIPTION = "Forbidden Exception (403)";

    public ForbiddenException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
