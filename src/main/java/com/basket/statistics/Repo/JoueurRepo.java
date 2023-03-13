package com.basket.statistics.Repo;

import com.basket.statistics.entities.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JoueurRepo extends JpaRepository<Joueur, Long> {
    List<Joueur> getJoueurByEquipeIdOrderByNumero(long id);

    Joueur getJoueurByNumero(int numero);

    Joueur getJoueurById(long id);

    Joueur getJoueurByNom(String nom);

    Joueur getJoueurByPrenom(String prenom);
}
