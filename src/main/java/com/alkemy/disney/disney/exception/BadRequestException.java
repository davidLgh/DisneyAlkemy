package com.alkemy.disney.disney.exception;

public class BadRequestException extends RuntimeException {
    //Excepcion: la petición está mal construida o faltan parametros
    private static final String DESCRIPTION = "Bad Request Exception (400)";

    public BadRequestException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
