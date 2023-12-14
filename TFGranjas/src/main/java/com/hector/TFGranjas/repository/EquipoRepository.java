package com.hector.TFGranjas.repository;

import com.hector.TFGranjas.domain.Cultivo;
import com.hector.TFGranjas.domain.Equipo;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Repository

public interface EquipoRepository extends CrudRepository<Equipo, Long>{

    List<Equipo> findAll();
}
