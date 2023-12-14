package com.hector.TFGranjas.exception;

public class CultivoNotFoundException extends Exception{

    public CultivoNotFoundException() {
        super("Cultivo not found");
    }

    public CultivoNotFoundException(String message) {
        super(message);
    }
}


