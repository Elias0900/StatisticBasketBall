package com.basket.statistics.Service;

import com.basket.statistics.dto.EquipeDTO;

import java.util.List;

public interface EquipeService {

    List<EquipeDTO> findAll();

    EquipeDTO saveOrUpdate(EquipeDTO e);

    void suppression(long id);

    EquipeDTO findById(long id);
}
