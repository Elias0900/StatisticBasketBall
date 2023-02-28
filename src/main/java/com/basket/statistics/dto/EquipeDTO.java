package com.basket.statistics.dto;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipeDTO implements Serializable {

        private long id;

        private String nomEquipe;


        private long joueurId;

        private long matchId;


}
