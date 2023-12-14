package com.hector.TFGranjas.exception;

public class EmpleadoNotFoundException extends Exception{

    public EmpleadoNotFoundException() {
        super("Empleado not found");
    }

    public EmpleadoNotFoundException(String message) {
        super(message);
    }
}
