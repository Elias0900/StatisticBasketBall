package com.basket.statistics.Service;

import com.basket.statistics.dto.MatchDTO;
import com.basket.statistics.exception.MatchException;

import java.util.List;

public interface MatchService {
    List<MatchDTO> getAll();


    MatchDTO findById(long id);

    void suppressionMatch(long id);


    int marquer2Point(long matchId, long joueurId);

    int marquer3Point(long matchId, long joueurId);

    int marquer1Point(long matchId, long joueurId);

    MatchDTO saveOrUpdate(MatchDTO j) throws MatchException;
}
