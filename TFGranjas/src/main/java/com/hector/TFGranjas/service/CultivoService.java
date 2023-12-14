package com.hector.TFGranjas.service;

import com.hector.TFGranjas.domain.Cultivo;
import com.hector.TFGranjas.exception.CultivoNotFoundException;

import java.util.List;

public interface CultivoService {


    Cultivo addCultivo(Cultivo cultivo) ;

    void deleteCultivo(long id) throws CultivoNotFoundException;

    Cultivo modifyCultivo(long id, Cultivo cultivo) throws CultivoNotFoundException;

    List<Cultivo> findAll();


    Cultivo findById(long id) throws CultivoNotFoundException;
}

