package com.hector.TFGranjas.service;

import com.hector.TFGranjas.domain.Equipo;
import com.hector.TFGranjas.exception.EquipoNotFoundException;

import java.util.List;

public interface EquipoService {

    Equipo addEquipo(Equipo equipo);

    void deleteEquipo(long id) throws EquipoNotFoundException;

    Equipo modifyEquipo(long id, Equipo equipo) throws EquipoNotFoundException;

    List<Equipo> findAll();


    Equipo findById(long id) throws EquipoNotFoundException;
}


