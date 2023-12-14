package com.hector.TFGranjas.service;

import com.hector.TFGranjas.domain.Cultivo;
import com.hector.TFGranjas.exception.CultivoNotFoundException;
import com.hector.TFGranjas.repository.CultivoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CultivoServiceImpl implements CultivoService {

    @Autowired
    CultivoRepository cultivoRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Cultivo addCultivo(Cultivo cultivo) {


        return cultivoRepository.save(cultivo);

    }
    @Override
    public void deleteCultivo(long id) throws CultivoNotFoundException {
        Cultivo cultivo = cultivoRepository.findById(id)
                .orElseThrow(CultivoNotFoundException::new);
        cultivoRepository.delete(cultivo);
    }

    @Override
    public Cultivo modifyCultivo(long id, Cultivo newCultivo) throws CultivoNotFoundException {
        Cultivo existingCultivo = cultivoRepository.findById(id)
                .orElseThrow(CultivoNotFoundException::new);
        newCultivo.setId(id);
        modelMapper.map(newCultivo, existingCultivo);
        return cultivoRepository.save(existingCultivo);
    }

    @Override
    public List<Cultivo> findAll() {
        return cultivoRepository.findAll();
    }


    @Override
    public Cultivo findById(long id) throws CultivoNotFoundException {
        return cultivoRepository.findById(id)
                .orElseThrow(CultivoNotFoundException::new);
    }

}


