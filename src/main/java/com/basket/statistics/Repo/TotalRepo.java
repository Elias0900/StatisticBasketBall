package com.basket.statistics.Repo;

import com.basket.statistics.entities.Total;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TotalRepo extends JpaRepository<Total, Long> {

    //moyenne de points
    @Query("SELECT AVG(t.totalPoints) FROM Total t JOIN t.stats s JOIN s.joueur joueur WHERE joueur.id= :joueurId")
    double getAvgPointsJoueur(@Param("joueurId") long joueurId);

    @Query("SELECT AVG(t.totalPasseD) FROM Total t JOIN t.stats s JOIN s.joueur joueur WHERE joueur.id= :joueurId")
    double getAvgPasseDJoueur(@Param("joueurId") long joueurId);

    @Query("SELECT AVG(t.totalRebonds) FROM Total t JOIN t.stats s JOIN s.joueur joueur WHERE joueur.id= :joueurId")
    double getAvgRebondJoueur(@Param("joueurId") long joueurId);

    @Query("SELECT AVG(t.pourcentageDeuxPts) FROM Total t JOIN t.stats s JOIN s.joueur joueur WHERE joueur.id= :joueurId")
    double getAvgPourcentage2Pts(@Param("joueurId") long joueurId);

    @Query("SELECT AVG(t.pourcentageTroisPts) FROM Total t JOIN t.stats s JOIN s.joueur joueur WHERE joueur.id= :joueurId")
    double getAvgPourcentage3Pts(@Param("joueurId") long joueurId);

    @Query("SELECT AVG(t.pourcentageLF) FROM Total t JOIN t.stats s JOIN s.joueur joueur WHERE joueur.id= :joueurId")
    double getAvgPourcentageLF(@Param("joueurId") long joueurId);

    @Query("SELECT AVG(t.pourcentage) FROM Total t JOIN t.stats s JOIN s.joueur joueur WHERE joueur.id= :joueurId")
    double getAvgPourcentageShoot(@Param("joueurId") long joueurId);

    @Query("SELECT AVG(t.totalContre) FROM Total t JOIN t.stats s JOIN s.joueur joueur WHERE joueur.id= :joueurId")
    double getAvgContre(@Param("joueurId") long joueurId);

    @Query("SELECT AVG(t.totalInterception) FROM Total t JOIN t.stats s JOIN s.joueur joueur WHERE joueur.id= :joueurId")
    double getAvgInter(@Param("joueurId") long joueurId);

    @Query("from Total t JOIN t.stats s JOIN s.joueur joueur WHERE joueur.id= :joueurId")
    Total getTotalByJoueurId(@Param("joueurId")long joueurId);
}
