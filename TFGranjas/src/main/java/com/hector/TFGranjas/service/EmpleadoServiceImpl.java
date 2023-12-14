package com.hector.TFGranjas.service;

import com.hector.TFGranjas.domain.Empleado;
import com.hector.TFGranjas.exception.EmpleadoNotFoundException;
import com.hector.TFGranjas.repository.EmpleadoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{
    @Autowired
    EmpleadoRepository empleadoRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Empleado addEmpleado(Empleado empleado) {


        return empleadoRepository.save(empleado);

    }

    @Override
    public void deleteEmpleado(long id) throws EmpleadoNotFoundException {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(EmpleadoNotFoundException::new);
        empleadoRepository.delete(empleado);
    }
    @Override
    public Empleado modifyEmpleado(long id, Empleado newEmpleado) throws EmpleadoNotFoundException {
        Empleado existingEmpleado = empleadoRepository.findById(id)
                .orElseThrow(EmpleadoNotFoundException::new);
        newEmpleado.setId(id);
        modelMapper.map(newEmpleado, existingEmpleado);
        return empleadoRepository.save(existingEmpleado);
    }

    @Override
    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }


    @Override
    public Empleado findById(long id) throws EmpleadoNotFoundException {
        return empleadoRepository.findById(id)
                .orElseThrow(EmpleadoNotFoundException::new);
    }

}

