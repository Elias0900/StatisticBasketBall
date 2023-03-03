package com.basket.statistics.Service;

import com.basket.statistics.MapperDto.DtoConvertisseur;
import com.basket.statistics.Repo.JoueurRepo;
import com.basket.statistics.dto.JoueurDTO;
import com.basket.statistics.entities.Joueur;
import javax.transaction.Transactional;

import com.basket.statistics.exception.JoueurException;
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
    public JoueurDTO saveOrUpdate(JoueurDTO j) throws JoueurException {
        Joueur joueur = DtoConvertisseur.convert(j, Joueur.class);
        Joueur jExistant = jRepo.getJoueurByNumero(joueur.getNumero());
        Joueur jExistantNom = jRepo.getJoueurByNom(joueur.getNom());
        Joueur jExistantPrenom = jRepo.getJoueurByPrenom(joueur.getPrenom());
        if (jExistant != null ) {
            throw new JoueurException("Ce numéro est déjà utilisé");
        } else if (jExistantNom != null && jExistantPrenom != null ) {
            throw new JoueurException("Ce joueur existe déjà");
        } else {
            joueur = jRepo.saveAndFlush(joueur);
            return DtoConvertisseur.convert(joueur, JoueurDTO.class);
        }
    }

    @Override
    public JoueurDTO update(JoueurDTO j) throws JoueurException {
        Joueur joueur = DtoConvertisseur.convert(j, Joueur.class);
        Joueur jExistant = jRepo.getJoueurById(joueur.getId()); // Recherche du joueur existant par son identifiant
        if (jExistant == null) {
            throw new JoueurException("Le joueur n'existe pas");
        }

        // Mettre à jour les champs pertinents du joueur existant
        jExistant.setNom(joueur.getNom());
        jExistant.setPrenom(joueur.getPrenom());
        jExistant.setNumero(joueur.getNumero());
        jExistant.setEquipe(joueur.getEquipe());
        // Enregistrer les modifications dans la base de données
        joueur = jRepo.saveAndFlush(jExistant);
        return DtoConvertisseur.convert(joueur, JoueurDTO.class);
    }

    @Override
    public JoueurDTO findById(long id) throws JoueurException {
        Optional<Joueur> j = jRepo.findById(id);
        if (j.isPresent()) {
            return DtoConvertisseur.convert(j.get(), JoueurDTO.class);
        } else {
            throw new JoueurException("Ce joueur n'existe pas");
        }
    }

    @Override
    public JoueurDTO findById(String nom) throws JoueurException {
        Optional<Joueur> j = Optional.ofNullable(jRepo.getJoueurByNom(nom));
        if (j.isPresent()) {
            return DtoConvertisseur.convert(j.get(), JoueurDTO.class);
        } else {
            throw new JoueurException("Ce joueur n'existe pas");
        }
    }

    @Override
    public void suppressionJoueur(long id) {
        jRepo.deleteById(id);

    }

    @Override
    public List<JoueurDTO> getByTeamId(long id) {
        List<Joueur> joueurList = jRepo.getJoueurByEquipeIdOrderByNumero(id);
        List<JoueurDTO> joueurDTOList = new ArrayList<>();
        for (Joueur j : joueurList){
            joueurDTOList.add(DtoConvertisseur.convert(j, JoueurDTO.class));
        }
        return joueurDTOList;
    }


}
