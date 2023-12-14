package com.hector.TFGranjas.controller;

import com.hector.TFGranjas.domain.Cultivo;
import com.hector.TFGranjas.domain.Empleado;
import com.hector.TFGranjas.domain.Equipo;
import com.hector.TFGranjas.domain.Granja;
import com.hector.TFGranjas.exception.EquipoNotFoundException;
import com.hector.TFGranjas.exception.ErrorMessage;
import com.hector.TFGranjas.service.EquipoService;
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
public class EquipoController {
    @Autowired
    EquipoService equipoService;

    private final Logger logger = LoggerFactory.getLogger(EquipoController.class);


    @PostMapping("/equipos")
    public ResponseEntity<Equipo> addEquipo(@Valid @RequestBody Equipo equipo) {
        logger.debug("INICIO ADD Equipo");
        Equipo newEquipo = equipoService.addEquipo(equipo);
        logger.debug("FIN ADD Equipo)");
        return new ResponseEntity<>(newEquipo, HttpStatus.CREATED);
    }


    @DeleteMapping("/equipos/{id}")
    public ResponseEntity<Void> deleteEquipo(@PathVariable long id) throws EquipoNotFoundException {
        logger.debug("begin DeleteMapping Animal");
        equipoService.deleteEquipo(id);
        logger.debug("end DeleteMapping Animal");
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/equipos/{id}")
    public ResponseEntity<Equipo> modifyEquipo(@PathVariable long id, @RequestBody Equipo equipo) throws EquipoNotFoundException {
        logger.debug("begin PutMapping Equipo");
        Equipo modifiedEquipo = equipoService.modifyEquipo(id, equipo);
        logger.debug("end PutMapping Equipo");
        return ResponseEntity.status(HttpStatus.OK).body(modifiedEquipo);
    }

    @GetMapping("/equipos/{id}")
    public ResponseEntity<Equipo> getEquipo(@PathVariable long id) throws EquipoNotFoundException {
        logger.debug("begin GetMapping ID Equipo");
        Equipo equipo = equipoService.findById(id);
        logger.debug("end GetMapping ID Equipo");
        return ResponseEntity.ok(equipo);
    }

    @GetMapping("/equipos")
    public ResponseEntity<List<Equipo>> getEquipos() {
        logger.debug("get all equipos");
        List<Equipo> equipos = equipoService.findAll();
        return ResponseEntity.ok(equipos);
    }

    //Excepción 404: Card not found
    @ExceptionHandler(EquipoNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleAnimalNotFoundException(EquipoNotFoundException enfe) {
        logger.error((enfe.getMessage()), enfe);
        ErrorMessage errorMessage = new ErrorMessage(404, enfe.getMessage());
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
