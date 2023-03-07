package com.basket.statistics.Repo;

import com.basket.statistics.entities.Stats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatsRepo extends JpaRepository<Stats, Long> {

    List<Stats> findByJoueurId(long id);
    Stats getStatsByJoueurIdAndMatchId(long joueurId, long equipeId);
}
