package com.basket.statistics.Repo;

import com.basket.statistics.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepo extends JpaRepository<Match, Long> {
}
