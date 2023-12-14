package com.hector.TFGranjas.service;

import com.hector.TFGranjas.domain.Granja;
import com.hector.TFGranjas.exception.GranjaNotFoundException;
import com.hector.TFGranjas.repository.GranjaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GranjaServiceImpl implements GranjaService {
    @Autowired
    GranjaRepository granjaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Granja addGranja(Granja granja)  {

        return granjaRepository.save(granja);

    }
    @Override
    public void deleteGranja(long id) throws GranjaNotFoundException {
        Granja granja = granjaRepository.findById(id)
                .orElseThrow(GranjaNotFoundException::new);
        granjaRepository.delete(granja);
    }

    @Override
    public Granja modifyGranja(long id, Granja newGranja) throws GranjaNotFoundException {
        Granja existingGranja = granjaRepository.findById(id)
                .orElseThrow(GranjaNotFoundException::new);
        newGranja.setId(id);
        modelMapper.map(newGranja, existingGranja);
        return granjaRepository.save(existingGranja);
    }

    @Override
    public List<Granja> findAll() {
        return granjaRepository.findAll();
    }


    @Override
    public Granja findById(long id) throws GranjaNotFoundException {
        return granjaRepository.findById(id)
                .orElseThrow(GranjaNotFoundException::new);
    }

}


