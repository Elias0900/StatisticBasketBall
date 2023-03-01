package com.basket.statistics.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

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
    private int rebondOff;
    private int rebondDeff;
    private int fautes;
    private int ballonPerdu;
    private int paniersProche;
    private int passeD;
    private int tirRateProche;
    private int contre;
    private int paniersLoins;
    private int tirRateLoin;

    private int lfRate;
    private int lfMarque;

    @ToString.Exclude
    @ManyToOne
    private Joueur joueur;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    private Total total;

}
