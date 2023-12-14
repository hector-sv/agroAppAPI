package com.hector.TFGranjas.service;

import com.hector.TFGranjas.domain.Animal;
import com.hector.TFGranjas.exception.AnimalNotFoundException;
import com.hector.TFGranjas.repository.AnimalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    AnimalRepository animalRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Animal addAnimal(Animal animal) {
        return animalRepository.save(animal);

    }

    @Override
    public void deleteAnimal(long id) throws AnimalNotFoundException {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(AnimalNotFoundException::new);
        animalRepository.delete(animal);
    }

    @Override
    public Animal modifyAnimal(long id, Animal newAnimal) throws AnimalNotFoundException {
        Animal existingAnimal = animalRepository.findById(id)
                .orElseThrow(AnimalNotFoundException::new);
        newAnimal.setId(id);
        modelMapper.map(newAnimal, existingAnimal);
        return animalRepository.save(existingAnimal);
    }

    @Override
    public List<Animal> findAll() {
        return animalRepository.findAll();
    }


    @Override
    public Animal findById(long id) throws AnimalNotFoundException {
        return animalRepository.findById(id)
                .orElseThrow(AnimalNotFoundException::new);
    }

}
