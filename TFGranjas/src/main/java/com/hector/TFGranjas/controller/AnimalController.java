package com.hector.TFGranjas.controller;

import com.hector.TFGranjas.domain.Animal;
import com.hector.TFGranjas.exception.AnimalNotFoundException;
import com.hector.TFGranjas.service.AnimalService;
import com.hector.TFGranjas.exception.ErrorMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AnimalController {
    @Autowired
    AnimalService animalService;

    private final Logger logger = LoggerFactory.getLogger(AnimalController.class);

    @PostMapping("/animales")
    public ResponseEntity<Animal> addAnimal(@Valid @RequestBody Animal animal) {
        logger.debug("INICIO ADD animal");
        Animal newAnimal = animalService.addAnimal(animal);
        logger.debug("FIN ADD animal)");
        return new ResponseEntity<>(newAnimal, HttpStatus.CREATED);
    }

    @DeleteMapping("/animales/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable long id) throws AnimalNotFoundException {
        logger.debug("begin DeleteMapping Animal");
        animalService.deleteAnimal(id);
        logger.debug("end DeleteMapping Animal");
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/animales/{id}")
    public ResponseEntity<Animal> modifyAnimal(@PathVariable long id, @RequestBody Animal animal) throws AnimalNotFoundException {
        logger.debug("begin PutMapping Animal");
        Animal modifiedAnimal = animalService.modifyAnimal(id, animal);
        logger.debug("end PutMapping Animal");
        return ResponseEntity.status(HttpStatus.OK).body(modifiedAnimal);
    }

    @GetMapping("/animales/{id}")
    public ResponseEntity<Animal> getAnimal(@PathVariable long id) throws AnimalNotFoundException {
        logger.debug("begin GetMapping ID Animal");
        Animal animal = animalService.findById(id);
        logger.debug("end GetMapping ID Animal");
        return ResponseEntity.ok(animal);
    }

    @GetMapping("/animales")
    public ResponseEntity<List<Animal>> getAnimales() {
        logger.debug("get all animales");
        List<Animal> animales = animalService.findAll();
        return ResponseEntity.ok(animales);
    }



    //Excepción 404: Card not found
    @ExceptionHandler(AnimalNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleAnimalNotFoundException(AnimalNotFoundException anfe) {
        logger.error((anfe.getMessage()), anfe);
        ErrorMessage errorMessage = new ErrorMessage(404, anfe.getMessage());
        return new ResponseEntity(errorMessage, HttpStatus.NOT_FOUND);
    }

    //Excetion 400: Bad request
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException(MethodArgumentNotValidException manve) {
        logger.error((manve.getMessage()), manve);
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        ErrorMessage errorMessage = new ErrorMessage(400, "Bad Request");
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }

    //cualquier exception. 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception e) {
        logger.error((e.getMessage()), e);
        ErrorMessage errorMessage = new ErrorMessage(500, "Internal Server Error");
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
