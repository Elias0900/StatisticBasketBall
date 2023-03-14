package com.basket.statistics.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatsDTO implements Serializable {
    private long id;
    private double fautes;
    private double passeD;
    private double ballonPerdu;
    private double paniersProche;
    private double tirRateProche;
    private double paniersLoins;
    private double tirRateLoin;
    private double lfRate;
    private double lfMarque;
    private double tirTotal;
    private double rebondOff;
    private double rebondDeff;
    private double contre;
    private double interception;
    private long matchId;
    private long joueurId;
    private long totalId;

    private long equipeDomicileId;

    private long equipeExterieurId;


}
