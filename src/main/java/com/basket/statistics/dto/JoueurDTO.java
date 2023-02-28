package com.basket.statistics.dto;

import com.basket.statistics.entities.Equipe;
import com.basket.statistics.entities.Stats;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JoueurDTO implements Serializable {
    private long id;
    private String nom;
    private String prenom;
    private int numero;
    private long equipeId;

    private long statId;
}
