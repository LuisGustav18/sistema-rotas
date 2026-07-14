package com.luis.sistema_rotas.exceptions;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public ObjectNotFoundException(String message){
        super(message);
    }
}
