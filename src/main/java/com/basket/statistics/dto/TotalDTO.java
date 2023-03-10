package com.basket.statistics.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TotalDTO {
    private long id;
    private double totalPoints;
    private double totalRebonds;
    private double totalPasseD;
    private double totalContre;
    private double totalInterception;

    private double pourcentageDeuxPts;
    private double pourcentageTroisPts;
    private double pourcentageLF;

    private double pourcentage;
    private long statsId;

}
