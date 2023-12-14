package com.hector.TFGranjas.controller;


import com.hector.TFGranjas.domain.Cultivo;
import com.hector.TFGranjas.domain.Empleado;
import com.hector.TFGranjas.domain.Granja;
import com.hector.TFGranjas.exception.EmpleadoNotFoundException;
import com.hector.TFGranjas.exception.ErrorMessage;
import com.hector.TFGranjas.service.EmpleadoService;
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
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;

    private final Logger logger = LoggerFactory.getLogger(EmpleadoController.class);


    @PostMapping("/empleados")
    public ResponseEntity<Empleado> addEmpleado(@Valid @RequestBody Empleado empleado) {
        logger.debug("INICIO ADD Empleado");
        Empleado newEmpleado = empleadoService.addEmpleado(empleado);
        logger.debug("FIN ADD Empleado)");
        return new ResponseEntity<>(newEmpleado, HttpStatus.CREATED);
    }


    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable long id) throws EmpleadoNotFoundException {
        logger.debug("begin DeleteMapping Empleado");
        empleadoService.deleteEmpleado(id);
        logger.debug("end DeleteMapping Empleado");
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> modifyAnimal(@PathVariable long id, @RequestBody Empleado empleado) throws EmpleadoNotFoundException {
        logger.debug("begin PutMapping Animal");
        Empleado modifiedEmpleado = empleadoService.modifyEmpleado(id, empleado);
        logger.debug("end PutMapping Empleado");
        return ResponseEntity.status(HttpStatus.OK).body(modifiedEmpleado);
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> getEmpleado(@PathVariable long id) throws EmpleadoNotFoundException {
        logger.debug("begin GetMapping ID Empleado");
        Empleado empleado = empleadoService.findById(id);
        logger.debug("end GetMapping ID Empleado");
        return ResponseEntity.ok(empleado);
    }

    @GetMapping("/empleados")
    public ResponseEntity<List<Empleado>> getEmpleados() {
        logger.debug("get all Empleado");
        List<Empleado> empleados = empleadoService.findAll();
        return ResponseEntity.ok(empleados);
    }

    //Excepción 404: Card not found
    @ExceptionHandler(EmpleadoNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleEmpleadoNotFoundException(EmpleadoNotFoundException enfe) {
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
