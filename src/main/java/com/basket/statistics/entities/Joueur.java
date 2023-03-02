package com.basket.statistics.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class Joueur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String prenom;
    @Column(unique = true)
    private int numero;
    @ToString.Exclude
    @ManyToOne
    private Equipe equipe;
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL)
    private List<Stats> stats;

    @PostPersist
    private void addStats() {
        Stats stats = new Stats();
        stats.setJoueur(this);
        this.stats = Collections.singletonList(stats);
    }




}
