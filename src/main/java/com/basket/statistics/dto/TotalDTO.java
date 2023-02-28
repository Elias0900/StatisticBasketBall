package com.basket.statistics.dto;

import com.basket.statistics.entities.Stats;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TotalDTO {
    private long id;
    private int totalPoints;
    private int totalRebonds;
    private int totalPasseD;

    private double pourcentageDeuxPts;
    private double pourcentageTroisPts;

    private double pourcentage;
    private long statsId;

}
