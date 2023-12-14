package com.hector.TFGranjas.service;

;
import com.hector.TFGranjas.domain.Equipo;
import com.hector.TFGranjas.exception.EquipoNotFoundException;
import com.hector.TFGranjas.repository.EquipoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoServiceImpl implements EquipoService {
    @Autowired
    EquipoRepository equipoRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Equipo addEquipo(Equipo equipo){


        return equipoRepository.save(equipo);

    }

    @Override
    public void deleteEquipo(long id) throws EquipoNotFoundException {
        Equipo equipo = equipoRepository.findById(id)
                .orElseThrow(EquipoNotFoundException::new);
        equipoRepository.delete(equipo);
    }

    @Override
    public Equipo modifyEquipo(long id, Equipo newEquipo) throws EquipoNotFoundException {
        Equipo existingEquipo = equipoRepository.findById(id)
                .orElseThrow(EquipoNotFoundException::new);
        newEquipo.setId(id);
        modelMapper.map(newEquipo, existingEquipo);
        return equipoRepository.save(existingEquipo);
    }

    @Override
    public List<Equipo> findAll() {
        return equipoRepository.findAll();
    }


    @Override
    public Equipo findById(long id) throws EquipoNotFoundException {
        return equipoRepository.findById(id)
                .orElseThrow(EquipoNotFoundException::new);
    }


}
