package com.basket.statistics.Service;

import com.basket.statistics.MapperDto.DtoConvertisseur;
import com.basket.statistics.Repo.EquipeRepo;
import com.basket.statistics.dto.EquipeDTO;
import com.basket.statistics.entities.Equipe;
import javax.transaction.Transactional;

import com.basket.statistics.exception.EquipeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EquipeImpl implements EquipeService {

    @Autowired
    private EquipeRepo equipeRepo;

    @Override
    public List<EquipeDTO> findAll() {
        List<Equipe> equipeList = equipeRepo.findAll();
        List<EquipeDTO> equipeDTOS = new ArrayList<>();
        for (Equipe e : equipeList) {
            equipeDTOS.add(DtoConvertisseur.convert(e, EquipeDTO.class));
        }
        return equipeDTOS;
    }

    @Override
    public EquipeDTO saveOrUpdate(EquipeDTO eDto) throws EquipeException {
        Equipe equipe = DtoConvertisseur.convert(eDto, Equipe.class);
        Equipe equipeExistant = equipeRepo.findByNomEquipe(equipe.getNomEquipe());
        if (equipeExistant != null) {
            throw new EquipeException("Cette équipe existe déjà");
        } else {
            equipe = equipeRepo.saveAndFlush(equipe);
            return DtoConvertisseur.convert(equipe, EquipeDTO.class);
        }

    }

    @Override
    public void suppression(long id) {
        equipeRepo.deleteById(id);
    }

    @Override
    public EquipeDTO findById(long id) throws EquipeException {
        Optional<Equipe> equipe = equipeRepo.findById(id);
        if (equipe.isPresent()) {
            return DtoConvertisseur.convert(equipe.get(), EquipeDTO.class);
        } else {
            throw new EquipeException("Cette équipe n'existe pas");
        }
    }



}
