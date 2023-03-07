package com.basket.statistics.Service;


public interface MoyenneService {

    double avgPoint(long joueurId);

    double avgPasse(long joueurId);

    double avgRebond(long joueurId);

    double avgPourcentage2Pts(long joueurId);

    double avgPourcentage3Pts(long joueurId);

    double avgPourcentageLF(long joueurId);

    double shoot(long joueurId);

    double contre(long joueurId);

    double inter(long joueurId);
}
