package com.basket.statistics.dto;

import com.basket.statistics.entities.Joueur;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatsDTO implements Serializable {
    private long id;
    private int fautes;
    private int passeD;
    private int ballonPerdu;
    private int paniersProche;
    private int tirRateProche;
    private int contre;
    private int paniersLoins;
    private int tirRateLoin;
    private int rebondOff;
    private int rebondDeff;
    private long joueurId;
    private long totalId;


}
