package com.basket.statistics.entities;

import javax.persistence.*;
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
    private double totalPoints;
    private double totalRebonds;
    private double totalPasseD;
    private double pourcentageDeuxPts;
    private double pourcentageTroisPts;
    private double pourcentageLF;
    private double pourcentage;
    private double totalContre;
    private double totalInterception;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    private Stats stats;
}
