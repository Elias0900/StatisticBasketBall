package com.basket.statistics.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
