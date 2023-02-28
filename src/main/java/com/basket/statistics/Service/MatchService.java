package com.basket.statistics.Service;

import com.basket.statistics.dto.MatchDTO;

import java.util.List;

public interface MatchService {
    List<MatchDTO> getAll();

    MatchDTO saveOrUpdate(MatchDTO j);

    MatchDTO findById(long id);

    void suppressionMatch(long id);
}
