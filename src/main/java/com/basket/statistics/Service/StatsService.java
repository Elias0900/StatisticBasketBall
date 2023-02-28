package com.basket.statistics.Service;

import com.basket.statistics.dto.EquipeDTO;
import com.basket.statistics.dto.StatsDTO;

import java.util.List;

public interface StatsService {
    StatsDTO saveOrUpdate(StatsDTO e, long id);

    List<StatsDTO> findByJoueurId(long id);
    StatsDTO pointsMarque(long id);
    List<StatsDTO> getAll();

    void suppression(long id);

    StatsDTO findById(long id);

    StatsDTO tirRate(long id);

    StatsDTO tirTroisPoints(long id);

    StatsDTO ajoutContre(long id);

    StatsDTO tirTroisPointsRate(long id);

    StatsDTO ballonPerduAjout(long id);

    StatsDTO ajoutFautes(long id);

    StatsDTO ajoutPasse(long id);

    StatsDTO ajoutRebondOff(long id);

    StatsDTO ajoutrebondDeff(long id);
}
