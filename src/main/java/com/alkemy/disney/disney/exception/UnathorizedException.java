package com.alkemy.disney.disney.exception;

public class UnathorizedException extends RuntimeException {
    //Excepcion: no autorizado /no accedió con login
    private static final String DESCRIPTION = "Unathorized Access (401)";

    public UnathorizedException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
