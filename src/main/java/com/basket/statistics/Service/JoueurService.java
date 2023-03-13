package com.basket.statistics.Service;

import com.basket.statistics.dto.JoueurDTO;
import com.basket.statistics.exception.JoueurException;

import java.util.List;

public interface JoueurService {

    List<JoueurDTO> getAll();

    JoueurDTO saveOrUpdate(JoueurDTO j) throws JoueurException;

    JoueurDTO update(JoueurDTO j) throws JoueurException;

    JoueurDTO findById(long id) throws JoueurException;

    JoueurDTO findById(String nom) throws JoueurException;

    void suppressionJoueur(long id);

    List<JoueurDTO> getByTeamId(long id);


}
