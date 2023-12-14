package com.hector.TFGranjas.service;

import com.hector.TFGranjas.domain.Empleado;
import com.hector.TFGranjas.exception.EmpleadoNotFoundException;

import java.util.List;

public interface EmpleadoService {

    Empleado addEmpleado(Empleado empleado);

    void deleteEmpleado(long id) throws EmpleadoNotFoundException;

    Empleado modifyEmpleado(long id, Empleado empleado) throws EmpleadoNotFoundException;

    List<Empleado> findAll();


    Empleado findById(long id) throws EmpleadoNotFoundException;
}