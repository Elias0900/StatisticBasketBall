package com.basket.statistics.Repo;

import com.basket.statistics.entities.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EquipeRepo extends JpaRepository<Equipe, Long> {

    @Query("from Equipe e where e.nomEquipe= :nom ")
    Equipe findByNomEquipe(@Param("nom") String name);





}
