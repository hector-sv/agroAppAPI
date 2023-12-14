package com.hector.TFGranjas.service;

import com.hector.TFGranjas.domain.Granja;
import com.hector.TFGranjas.exception.GranjaNotFoundException;

import java.util.List;

public interface GranjaService {
    Granja addGranja(Granja granja) ;

    void deleteGranja(long id) throws GranjaNotFoundException;

    Granja modifyGranja(long id, Granja granja) throws GranjaNotFoundException;

    List<Granja> findAll();


    Granja findById(long id) throws GranjaNotFoundException;

}
