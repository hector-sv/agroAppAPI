package com.hector.TFGranjas.repository;

import com.hector.TFGranjas.domain.Cultivo;
import com.hector.TFGranjas.domain.Granja;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface GranjaRepository extends CrudRepository<Granja, Long> {
    List<Granja> findAll();

}
