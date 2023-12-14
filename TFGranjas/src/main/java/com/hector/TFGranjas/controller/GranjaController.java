package com.hector.TFGranjas.controller;


import com.hector.TFGranjas.domain.Granja;
import com.hector.TFGranjas.exception.ErrorMessage;
import com.hector.TFGranjas.exception.GranjaNotFoundException;
import com.hector.TFGranjas.service.GranjaService;
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
public class GranjaController {
    @Autowired
    GranjaService granjaService;

    private final Logger logger = LoggerFactory.getLogger(GranjaController.class);

    @PostMapping("/granjas")
    public ResponseEntity<Granja> addGranja(@Valid @RequestBody Granja granja) {
        logger.debug("INICIO ADD Granja");
        Granja newGranja = granjaService.addGranja(granja);
        logger.debug("FIN ADD Granja)");
        return new ResponseEntity<>(newGranja, HttpStatus.CREATED);
    }


    @DeleteMapping("/granjas/{id}")
    public ResponseEntity<Void> deleteGranja(@PathVariable long id) throws GranjaNotFoundException {
        logger.debug("begin DeleteMapping Granja");
        granjaService.deleteGranja(id);
        logger.debug("end DeleteMapping Granja");
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/granjas/{id}")
    public ResponseEntity<Granja> modifyGranja(@PathVariable long id, @RequestBody Granja granja) throws GranjaNotFoundException {
        logger.debug("begin PutMapping Granja");
        Granja modifiedGranja = granjaService.modifyGranja(id, granja);
        logger.debug("end PutMapping Granja");
        return ResponseEntity.status(HttpStatus.OK).body(modifiedGranja);
    }

    @GetMapping("/granjas/{id}")
    public ResponseEntity<Granja> getGranja(@PathVariable long id) throws GranjaNotFoundException {
        logger.debug("begin GetMapping ID Granja");
        Granja granja = granjaService.findById(id);
        logger.debug("end GetMapping ID Granja");
        return ResponseEntity.ok(granja);
    }

    @GetMapping("/granjas")
    public ResponseEntity<List<Granja>> getGranjas() {
        logger.debug("get all granjas");
        List<Granja> granjas = granjaService.findAll();
        return ResponseEntity.ok(granjas);
    }


    @ExceptionHandler(GranjaNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleGranjaNotFoundException(GranjaNotFoundException gnfe) {
        logger.error((gnfe.getMessage()), gnfe);
        ErrorMessage errorMessage = new ErrorMessage(404, gnfe.getMessage());
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

