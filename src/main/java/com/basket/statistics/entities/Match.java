package com.basket.statistics.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
@Table(name = "t_match")
public class Match implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Equipe equipeDomicileIdequipeDomicileId;

    @OneToOne
    private Equipe equipeExterieurId;

    private int scoreDomicile;

    private int scoreExterieur;

    private Date date;
    @ToString.Exclude
    @ManyToOne
    private Stats stats;

}
