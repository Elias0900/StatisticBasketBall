package com.basket.statistics.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class Total {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int totalPoints;
    private int totalRebonds;
    private int totalPasseD;
    private double pourcentageDeuxPts;
    private double pourcentageTroisPts;
    private double pourcentageLF;
    private double pourcentage;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    private Stats stats;
}
