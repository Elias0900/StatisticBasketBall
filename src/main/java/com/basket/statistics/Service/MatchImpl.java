package com.basket.statistics.Service;

import com.basket.statistics.MapperDto.DtoConvertisseur;
import com.basket.statistics.Repo.EquipeRepo;
import com.basket.statistics.Repo.MatchRepo;
import com.basket.statistics.Repo.StatsRepo;
import com.basket.statistics.Repo.TotalRepo;
import com.basket.statistics.dto.MatchDTO;
import com.basket.statistics.entities.*;
import com.basket.statistics.exception.MatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MatchImpl implements MatchService {
    @Autowired
    private MatchRepo repo;

    @Autowired
    private StatsRepo sRepo;

    @Autowired
    private TotalRepo tRepo;
    @Autowired
    private EquipeRepo equipeRepo;

    @Override
    public List<MatchDTO> getAll() {
        List<Match> jList = repo.findAll();
        List<MatchDTO> MatchDTOList = new ArrayList<>();
        for (Match j : jList) {
            MatchDTOList.add(DtoConvertisseur.convert(j, MatchDTO.class));
        }
        return MatchDTOList;
    }

    @Override
    public MatchDTO saveOrUpdate(MatchDTO j) throws MatchException {
        Equipe equipeDom = equipeRepo.getReferenceById(j.getEquipeDomicileId());
        Equipe equipeExt = equipeRepo.getReferenceById(j.getEquipeExterieurId());
        if (equipeDom != equipeExt) {
            Match match = DtoConvertisseur.convert(j, Match.class);
            match.setEquipeDomicileId(equipeDom);
            match.setEquipeExterieurId(equipeExt);

            List<Stats> statsList = new ArrayList<>();


            for (Joueur joueur : equipeDom.getJoueur()) {
                Stats stats = new Stats();
                stats.setMatch(match);
                stats.setEquipe(equipeDom);
                stats.setJoueur(joueur);
                statsList.add(stats);

                Total total = new Total();
                total.setStats(stats);

                joueur.setStats(Collections.singletonList(stats));
                sRepo.save(stats);
                tRepo.save(total);
            }

            // Ajouter les statistiques pour chaque joueur de l'équipe extérieure
            for (Joueur joueur : equipeExt.getJoueur()) {
                Stats stats = new Stats();
                Total total = new Total();
                // Créer une nouvelle instance de Total et l'associer à la Stats
                stats.setTotal(total);
                total.setStats(stats);
                // Assigner les autres valeurs à Stats
                stats.setMatch(match);
                stats.setEquipe(equipeExt);
                stats.setJoueur(joueur);
                // Enregistrer l'entité
                sRepo.save(stats);
                tRepo.save(total);
            }

            sRepo.saveAll(statsList);
            repo.saveAndFlush(match);

            return DtoConvertisseur.convert(match, MatchDTO.class);
        } else {
            throw new MatchException("Vous ne pouvez pas choisir deux fois la même équipe");
        }
    }


    @Override
    public MatchDTO findById(long id) {
        Optional<Match> j = repo.findById(id);
        if (j.isPresent()) {
            return DtoConvertisseur.convert(j, MatchDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public void suppressionMatch(long id) {
        repo.deleteById(id);

    }

    @Override
    public int marquer2Point(long matchId, long joueurId) {
        Match match = repo.getReferenceById(matchId);
        Equipe equipe = match.getEquipeDomicileId();
        List<Joueur> joueurs = equipe.getJoueur();
        DeuxPoints(joueurId, match, joueurs);
        return match.getScoreDomicile(); // ou une autre valeur par défaut si le joueur ou le match n'est pas trouvé
    }

    @Override
    public int marquer2PointExt(long matchId, long joueurId) {
        Match match = repo.getReferenceById(matchId);
        Equipe equipe = match.getEquipeExterieurId();
        List<Joueur> joueurs = equipe.getJoueur();
        DeuxPoints(joueurId, match, joueurs);
        return match.getScoreExterieur(); // ou une autre valeur par défaut si le joueur ou le match n'est pas trouvé
    }

    private void DeuxPoints(long joueurId, Match match, List<Joueur> joueurs) {
        for (Joueur joueur : joueurs) {
            if (joueur.getId() == joueurId) {
                List<Stats> stats = sRepo.findByJoueurId(joueurId);
                for (Stats stats1 : stats) {
                    if (stats1.getMatch().getId() == match.getId() && stats1.getJoueur().getId() == joueur.getId()) {
                        if (joueur.getEquipe() == match.getEquipeExterieurId()) {
                            int scoreExt = match.getScoreExterieur();
                            int newScoreExt = scoreExt + 2;
                            match.setScoreExterieur(newScoreExt);
                            repo.save(match);
                        } else {
                            int scoreDom = match.getScoreDomicile();
                            int newScoreDom = scoreDom + 2;
                            match.setScoreDomicile(newScoreDom);
                            repo.save(match);
                        }
                        double currentValue = stats1.getPaniersProche(); // Récupération de la valeur actuelle du champ
                        double newValue = currentValue + 1; // Incrément de la valeur actuelle
                        stats1.setPaniersProche(newValue); // Mise à jour de la valeur du champ avec la nouvelle valeur incrémentée
                        double tt = stats1.getTirTotal();
                        double newTT = tt + 1;
                        stats1.setTirTotal(newTT);
                        sRepo.save(stats1);
                    }
                }
            }
        }
    }


    @Override
    public int marquer3Point(long matchId, long joueurId) {
        Match match = repo.getReferenceById(matchId);
        Equipe equipe = match.getEquipeDomicileId();
        List<Joueur> joueurs = equipe.getJoueur();
        TroisPtsMarque(joueurId, match, joueurs);
        return match.getScoreDomicile();
    }

    @Override
    public int marquer3PointExt(long matchId, long joueurId) {
        Match match = repo.getReferenceById(matchId);
        Equipe equipe = match.getEquipeExterieurId();
        List<Joueur> joueurs = equipe.getJoueur();
        TroisPtsMarque(joueurId, match, joueurs);
        return match.getScoreExterieur();
    }

    private void TroisPtsMarque(long joueurId, Match match, List<Joueur> joueurs) {
        for (Joueur joueur : joueurs) {
            List<Stats> stats = sRepo.findByJoueurId(joueurId);
            for (Stats stats1 : stats) {
                if (stats1.getMatch().getId() == match.getId() && stats1.getJoueur().getId() == joueur.getId()) {
                    if (joueur.getEquipe() == match.getEquipeExterieurId()) {
                        int scoreExt = match.getScoreExterieur();
                        int newScoreExt = scoreExt + 3;
                        match.setScoreExterieur(newScoreExt);
                        repo.save(match);
                    } else {
                        int scoreDom = match.getScoreDomicile();
                        int newScoreDom = scoreDom + 3;
                        match.setScoreDomicile(newScoreDom);
                        repo.save(match);
                    }
                    double currentValue = stats1.getPaniersLoins(); // Récupération de la valeur actuelle du champ
                    double newValue = currentValue + 1; // Incrément de la valeur actuelle
                    stats1.setPaniersLoins(newValue); // Mise à jour de la valeur du champ avec la nouvelle valeur incrémentée
                    double tt = stats1.getTirTotal();
                    double newTT = tt + 1;
                    stats1.setTirTotal(newTT);
                    sRepo.save(stats1);
                }
            }
        }
    }

    @Override
    public int marquer1Point(long matchId, long joueurId) {
        Match match = repo.getReferenceById(matchId);
        Equipe equipe = match.getEquipeDomicileId();
        List<Joueur> joueurs = equipe.getJoueur();
        LFMarque(joueurId, match, joueurs);
        return match.getScoreDomicile();
    }

    @Override
    public int marquer1PointExt(long matchId, long joueurId) {
        Match match = repo.getReferenceById(matchId);
        Equipe equipe = match.getEquipeExterieurId();
        List<Joueur> joueurs = equipe.getJoueur();
        LFMarque(joueurId, match, joueurs);
        return match.getScoreExterieur();
    }

    private void LFMarque(long joueurId, Match match, List<Joueur> joueurs) {
        for (Joueur joueur : joueurs) {
            List<Stats> stats = sRepo.findByJoueurId(joueurId);
            for (Stats stats1 : stats) {
                if (stats1.getMatch().getId() == match.getId() && stats1.getJoueur().getId() == joueur.getId()) {
                    if (joueur.getEquipe() == match.getEquipeExterieurId()) {
                        int scoreExt = match.getScoreExterieur();
                        int newScoreExt = scoreExt + 1;
                        match.setScoreExterieur(newScoreExt);
                        repo.save(match);
                    } else {
                        int scoreDom = match.getScoreDomicile();
                        int newScoreDom = scoreDom + 1;
                        match.setScoreDomicile(newScoreDom);
                        repo.save(match);
                    }
                    double currentValue = stats1.getLfMarque(); // Récupération de la valeur actuelle du champ
                    double newValue = currentValue + 1; // Incrément de la valeur actuelle
                    stats1.setLfMarque(newValue); // Mise à jour de la valeur du champ avec la nouvelle valeur incrémentée
                    double tt = stats1.getTirTotal();
                    double newTT = tt + 1;
                    stats1.setTirTotal(newTT);
                    sRepo.save(stats1);
                }
            }
        }
    }


}

