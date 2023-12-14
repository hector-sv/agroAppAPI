package com.hector.TFGranjas.service;

import com.hector.TFGranjas.domain.Animal;
import com.hector.TFGranjas.exception.AnimalNotFoundException;

import java.util.List;

public interface AnimalService {

    Animal addAnimal(Animal animal);

    void deleteAnimal(long id) throws AnimalNotFoundException;

    Animal modifyAnimal(long id, Animal animal) throws AnimalNotFoundException;

    List<Animal> findAll();


    Animal findById(long id) throws AnimalNotFoundException;
}
