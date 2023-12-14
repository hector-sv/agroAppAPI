package com.hector.TFGranjas.repository;


import com.hector.TFGranjas.domain.Animal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository  extends CrudRepository<Animal, Long> {

    List<Animal> findAll();
}
