package com.alkemy.disney.disney.exception;

public class NotFoundException extends RuntimeException{
    //Excepcion: no se encontro el archivo/recurso
    private static final String DESCRIPTION = "The resource doesn't exist";

    public NotFoundException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
