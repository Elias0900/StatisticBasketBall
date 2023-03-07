package com.basket.statistics.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
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


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "joueur")
    @ToString.Exclude
    private List<Stats> stats;




}
