package com.hector.TFGranjas.controller;

import com.hector.TFGranjas.domain.Animal;
import com.hector.TFGranjas.domain.Cultivo;
import com.hector.TFGranjas.domain.Granja;
import com.hector.TFGranjas.exception.CultivoNotFoundException;
import com.hector.TFGranjas.exception.ErrorMessage;
import com.hector.TFGranjas.service.CultivoService;

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
public class CultivoController {
    @Autowired
    CultivoService cultivoService;

    private final Logger logger = LoggerFactory.getLogger(CultivoController.class);

    @PostMapping("/cultivos")
    public ResponseEntity<Cultivo> addCultivo(@Valid @RequestBody Cultivo cultivo) {
        logger.debug("INICIO ADD Cultivo");
        Cultivo newCultivo = cultivoService.addCultivo(cultivo);
        logger.debug("FIN ADD Cultivo)");
        return new ResponseEntity<>(newCultivo, HttpStatus.CREATED);
    }


    @DeleteMapping("/cultivos/{id}")
    public ResponseEntity<Void> deleteCultivo(@PathVariable long id) throws CultivoNotFoundException {
        logger.debug("begin DeleteMapping Cultivo");
        cultivoService.deleteCultivo(id);
        logger.debug("end DeleteMapping Cultivo");
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/cultivos/{id}")
    public ResponseEntity<Cultivo> modifyCultivo(@PathVariable long id, @RequestBody Cultivo cultivo) throws CultivoNotFoundException {
        logger.debug("begin PutMapping Cultivo");
        Cultivo modifiedCultivo = cultivoService.modifyCultivo(id, cultivo);
        logger.debug("end PutMapping Cultivo");
        return ResponseEntity.status(HttpStatus.OK).body(modifiedCultivo);
    }

    @GetMapping("/cultivos/{id}")
    public ResponseEntity<Cultivo> getCultivo(@PathVariable long id) throws CultivoNotFoundException {
        logger.debug("begin GetMapping ID Cultivo");
        Cultivo cultivo = cultivoService.findById(id);
        logger.debug("end GetMapping ID Cultivo");
        return ResponseEntity.ok(cultivo);
    }

    @GetMapping("/cultivos")
    public ResponseEntity<List<Cultivo>> getCultivos() {
        logger.debug("get all Cultivo");
        List<Cultivo> cultivos = cultivoService.findAll();
        return ResponseEntity.ok(cultivos);
    }


    @ExceptionHandler(CultivoNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleCultivoNotFoundException(CultivoNotFoundException cnfe) {
        logger.error((cnfe.getMessage()), cnfe);
        ErrorMessage errorMessage = new ErrorMessage(404, cnfe.getMessage());
        return new ResponseEntity(errorMessage, HttpStatus.NOT_FOUND);
    }

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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception e) {
        logger.error((e.getMessage()), e);
        ErrorMessage errorMessage = new ErrorMessage(500, "Internal Server Error");
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}