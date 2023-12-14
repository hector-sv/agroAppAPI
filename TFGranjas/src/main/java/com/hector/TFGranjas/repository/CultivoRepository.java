package com.hector.TFGranjas.repository;

import com.hector.TFGranjas.domain.Cultivo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CultivoRepository extends CrudRepository<Cultivo, Long>{

    List<Cultivo> findAll();
}
