package com.hector.TFGranjas.exception;

public class AnimalNotFoundException extends Exception{

    public AnimalNotFoundException() {
        super("Animal not found");
    }

    public AnimalNotFoundException(String message) {
        super(message);
    }
}
