package com.basket.statistics.Repo;

import com.basket.statistics.entities.Equipe;
import com.basket.statistics.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepo extends JpaRepository<Match, Long> {

    Match getMatchByEquipeDomicileIdOrEquipeExterieurId(Equipe equipeDomicileId, Equipe equipeExterieurId);



    List<Match> findByEquipeDomicileIdId(long id);
    List<Match> findByEquipeExterieurIdId(long id);

}
