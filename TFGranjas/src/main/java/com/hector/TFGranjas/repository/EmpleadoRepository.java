package com.hector.TFGranjas.repository;

import com.hector.TFGranjas.domain.Cultivo;
import com.hector.TFGranjas.domain.Empleado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends CrudRepository<Empleado, Long>{
    List<Empleado> findAll();
}
