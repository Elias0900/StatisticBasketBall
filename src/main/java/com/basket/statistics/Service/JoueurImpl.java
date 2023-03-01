package com.basket.statistics.Service;

import com.basket.statistics.MapperDto.DtoConvertisseur;
import com.basket.statistics.Repo.JoueurRepo;
import com.basket.statistics.dto.JoueurDTO;
import com.basket.statistics.entities.Joueur;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class JoueurImpl implements JoueurService {

    @Autowired
    private JoueurRepo jRepo;

    @Override
    public List<JoueurDTO> getAll() {
        List<Joueur> jList = jRepo.findAll();
        List<JoueurDTO> joueurDTOList = new ArrayList<>();
        for (Joueur j : jList){
            joueurDTOList.add(DtoConvertisseur.convert(j, JoueurDTO.class));
        }
        return joueurDTOList;
    }

    @Override
    public JoueurDTO saveOrUpdate(JoueurDTO j) {
        Joueur joueur = DtoConvertisseur.convert(j, Joueur.class);
        joueur = jRepo.saveAndFlush(joueur);
        return DtoConvertisseur.convert(joueur, JoueurDTO.class);
    }

    @Override
    public JoueurDTO findById(long id) {
        Optional<Joueur> j = jRepo.findById(id);
        if (j.isPresent()) {
            return DtoConvertisseur.convert(j.get(), JoueurDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public void suppressionJoueur(long id) {
        jRepo.deleteById(id);

    }
}
