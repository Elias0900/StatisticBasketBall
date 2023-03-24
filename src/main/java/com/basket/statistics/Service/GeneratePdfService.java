package com.basket.statistics.Service;

public interface GeneratePdfService {

    String generatePdfTotal(Long joueurId, Long matchId) throws Exception;
}
