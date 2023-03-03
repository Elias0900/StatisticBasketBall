package com.basket.statistics.Repo;

import com.basket.statistics.entities.Equipe;
import com.basket.statistics.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepo extends JpaRepository<Match, Long> {

    Match getMatchByEquipeDomicileIdOrEquipeExterieurId(Equipe equipeDomicileId, Equipe equipeExterieurId);


}
