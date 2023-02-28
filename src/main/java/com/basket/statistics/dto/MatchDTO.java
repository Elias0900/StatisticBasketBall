package com.basket.statistics.dto;

import com.basket.statistics.entities.Equipe;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatchDTO  implements Serializable {

    private long id;

    private long domicileId;

    private long exterieurId;

    private int scoreDomicile;

    private long scoreExterieur;

    private Date date;
}
