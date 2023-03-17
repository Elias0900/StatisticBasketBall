package com.basket.statistics.Service;

import com.basket.statistics.dto.TotalDTO;
import com.basket.statistics.exception.TotalException;

public interface TotalService {

    TotalDTO saveOrUpdate(TotalDTO totalDTO, long id, long matchId);

    double totalPoint(long joueurId, long matchId) throws TotalException;

    double totalContre(long joueurId, long matchId) throws TotalException;

    double totalPasse(long joueurId, long matchId) throws TotalException;

    double totalInterc(long joueurId, long matchId) throws TotalException;

    double totalRebond(long joueurId, long matchId) throws TotalException;

    double pourcentageLF(long id, long matchId) throws TotalException;

    double pourcentage(long joueurId, long matchId) throws TotalException;

    double pourcentageDeuxPts(long joueurId, long matchId) throws TotalException;

    double pourcentageTroisPts(long joueurId, long matchId) throws TotalException;

    String generatePdf(long totalId) throws Exception;

    String generateTotalForJoueur(long joueurId) throws Exception;
}
