package com.basket.statistics.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor

public class Equipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String nomEquipe;
    @ToString.Exclude
    @OneToMany(mappedBy = "equipe", cascade = CascadeType.ALL)
    private List<Joueur> joueur;
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL)
    private List<Match> match;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL)
    private List<Stats>stats;
}
