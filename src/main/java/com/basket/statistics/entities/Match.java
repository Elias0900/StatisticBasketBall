package com.basket.statistics.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
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
    private Equipe equipeDomicileId;

    @OneToOne
    private Equipe equipeExterieurId;

    private int scoreDomicile;

    private int scoreExterieur;

    private Date date;


}
