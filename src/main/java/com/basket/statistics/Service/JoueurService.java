package com.basket.statistics.Service;

import com.basket.statistics.dto.JoueurDTO;

import java.util.List;

public interface JoueurService {

    List <JoueurDTO> getAll();

    JoueurDTO saveOrUpdate(JoueurDTO j);

    JoueurDTO findById(long id);

    void suppressionJoueur(long id);




}
