package com.hector.TFGranjas.exception;

public class EquipoNotFoundException extends Exception{
    public EquipoNotFoundException() {
        super("Equipo not found");
    }

    public EquipoNotFoundException(String message) {
        super(message);
    }
}


