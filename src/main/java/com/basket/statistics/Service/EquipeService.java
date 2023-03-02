package com.basket.statistics.Service;

import com.basket.statistics.dto.EquipeDTO;
import com.basket.statistics.exception.EquipeException;

import java.util.List;

public interface EquipeService {

    List<EquipeDTO> findAll();

    EquipeDTO saveOrUpdate(EquipeDTO e) throws EquipeException;

    void suppression(long id);

    EquipeDTO findById(long id) throws EquipeException;

}
