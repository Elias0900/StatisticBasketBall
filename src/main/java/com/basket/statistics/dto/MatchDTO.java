package com.basket.statistics.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatchDTO  implements Serializable {

    private long id;
    private long equipeDomicileId;
    private long equipeExterieurId;
    private int scoreDomicile;
    private long scoreExterieur;
    private Date date;
    private long statsId;
}
