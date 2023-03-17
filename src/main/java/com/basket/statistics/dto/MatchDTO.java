package com.basket.statistics.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatchDTO implements Serializable {

    private long id;
    private long equipeDomicileId;

    private String equipeDomicileIdNomEquipe;
    private long equipeExterieurId;
    private String equipeExterieurIdNomEquipe;
    private int scoreDomicile;
    private long scoreExterieur;
    private Date date;
}
