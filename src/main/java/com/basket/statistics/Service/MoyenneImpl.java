package com.basket.statistics.Service;

import com.basket.statistics.Repo.TotalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MoyenneImpl implements MoyenneService{

    @Autowired
    private TotalRepo repo;
    @Override
    public double avgPoint(long joueurId) {
        return (repo.getAvgPointsJoueur(joueurId));
    }

    @Override
    public double avgPasse(long joueurId) {
        return (repo.getAvgPasseDJoueur(joueurId));
    }
    @Override
    public double avgRebond(long joueurId) {
        return (repo.getAvgRebondJoueur(joueurId));
    }

    @Override
    public double avgPourcentage2Pts(long joueurId) {
        return (repo.getAvgPourcentage2Pts(joueurId));
    }

    @Override
    public double avgPourcentage3Pts(long joueurId) {
        return (repo.getAvgPourcentage3Pts(joueurId));
    }

    @Override
    public double avgPourcentageLF(long joueurId) {
        return (repo.getAvgPourcentageLF(joueurId));
    }

    @Override
    public double shoot(long joueurId) {
        return (repo.getAvgPourcentageShoot(joueurId));
    }

    @Override
    public double contre(long joueurId) {
        return (repo.getAvgContre(joueurId));
    }

    @Override
    public double inter(long joueurId) {
        return (repo.getAvgInter(joueurId));
    }



}
