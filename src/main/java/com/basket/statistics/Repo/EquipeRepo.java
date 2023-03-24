package com.basket.statistics.Repo;

import com.basket.statistics.entities.Equipe;
import com.basket.statistics.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.From;
import java.util.List;

public interface EquipeRepo extends JpaRepository<Equipe, Long> {

    @Query("from Equipe e where e.nomEquipe= :nom ")
    Equipe findByNomEquipe(@Param("nom") String name);

    @Query("from Equipe e where e.id= :id")
    List<Match> findAllByEquipeId(@Param("id") long id);




}
