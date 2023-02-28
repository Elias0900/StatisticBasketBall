package com.basket.statistics.Repo;

import com.basket.statistics.entities.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoueurRepo extends JpaRepository<Joueur, Long> {
}
