package com.hector.TFGranjas.exception;

public class GranjaNotFoundException extends Exception{

    public GranjaNotFoundException() {
        super("Granja not found");
    }

    public GranjaNotFoundException(String message) {
        super(message);
    }
}

