package com.basket.statistics.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class Stats implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double rebondOff;
    private double rebondDeff;
    private double fautes;
    private double ballonPerdu;
    private double paniersProche;
    private double passeD;
    private double tirRateProche;
    private double contre;
    private double interception;
    private double paniersLoins;
    private double tirRateLoin;
    private double tirTotal;

    private double lfRate;
    private double lfMarque;

    @ToString.Exclude
    @ManyToOne
    private Joueur joueur;
    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Total total;

    @ManyToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    private Match match;

    @ManyToOne
    private Equipe equipe;


}
