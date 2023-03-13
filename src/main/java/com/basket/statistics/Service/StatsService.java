package com.basket.statistics.Service;

import com.basket.statistics.dto.StatsDTO;

import java.util.List;

public interface StatsService {
    StatsDTO saveOrUpdate(StatsDTO e, long id);

    List<StatsDTO> findByJoueurId(long id);

    List<StatsDTO> getAll();

    void suppression(long id);

    StatsDTO findById(long id);

    StatsDTO pointsMarquev2(long joueurid, long matchId);

    StatsDTO tirRate(long id, long matchId);

    StatsDTO tirTroisPoints(long id, long matchId);

    StatsDTO ajoutContre(long id, long matchId);

    StatsDTO tirTroisPointsRate(long id, long matchId);

    StatsDTO ballonPerduAjout(long id, long matchId);

    StatsDTO ajoutFautes(long id, long matchId);

    StatsDTO ajoutPasse(long id, long matchId);

    StatsDTO ajoutInterceptions(long joueurid, long matchId);

    StatsDTO ajoutRebondOff(long id, long matchId);

    StatsDTO ajoutrebondDeff(long id, long matchId);

    StatsDTO ajoutLFMarque(long id, long matchId);

    StatsDTO ajoutLFRate(long id, long matchId);
}
