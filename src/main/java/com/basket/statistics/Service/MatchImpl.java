package com.basket.statistics.Service;

import com.basket.statistics.MapperDto.DtoConvertisseur;
import com.basket.statistics.Repo.EquipeRepo;
import com.basket.statistics.Repo.MatchRepo;
import com.basket.statistics.Repo.StatsRepo;
import com.basket.statistics.Repo.TotalRepo;
import com.basket.statistics.dto.JoueurDTO;
import com.basket.statistics.dto.MatchDTO;
import com.basket.statistics.entities.*;
import com.basket.statistics.exception.MatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
    public MatchDTO findById(long id) throws MatchException {
        Optional<Match> m = repo.findById(id);
        if (m.isPresent()) {
            return DtoConvertisseur.convert(m.get(), MatchDTO.class);
        } else {
            throw new MatchException("Ce match n'existe pas");
        }
    }

    @Override
    public MatchDTO saveOrUpdate(MatchDTO j) throws MatchException {
        Equipe equipeDom = equipeRepo.getReferenceById(j.getEquipeDomicileId());
        Equipe equipeExt = equipeRepo.getReferenceById(j.getEquipeExterieurId());
        if (equipeDom != equipeExt) {
            Match match = DtoConvertisseur.convert(j, Match.class);
            match.setEquipeDomicileId(equipeDom);
            match.setEquipeExterieurId(equipeExt);

            repo.saveAndFlush(match);

            for (Joueur joueur : equipeDom.getJoueur()) {
                Stats stats = new Stats();
                Total total = new Total();
                // Créer une nouvelle instance de Total et l'associer à la Stats
                stats.setTotal(total);
                total.setStats(stats);
                // Assigner les autres valeurs à Stats
                stats.setMatchId(match.getId());
                stats.setEquipeDomicile(equipeDom);
                stats.setJoueur(joueur);
                // Enregistrer l'entité
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
                stats.setMatchId(match.getId());
                stats.setEquipeExterieur(equipeExt);
                stats.setJoueur(joueur);
                // Enregistrer l'entité
                sRepo.save(stats);
                tRepo.save(total);
            }

           // sRepo.saveAll(statsList);


            return DtoConvertisseur.convert(match, MatchDTO.class);
        } else {
            throw new MatchException("Vous ne pouvez pas choisir deux fois la même équipe");
        }
    }



    @Override
    public void suppressionMatch(long id) {
        Match match = repo.getReferenceById(id);
        Equipe equipe = match.getEquipeDomicileId();
        Equipe equipeExt = match.getEquipeExterieurId();

        List<Joueur> joueurs = equipe.getJoueur();
        for (Joueur joueur : joueurs) {
            List<Stats> stats = sRepo.findByJoueurId(joueur.getId());
            for (Stats stats1 : stats) {
                if (stats1.getMatchId() == match.getId() && stats1.getJoueur().getId() == joueur.getId()) {
                    sRepo.deleteById(stats1.getId());
                }
            }
            List<Joueur> joueursExt = equipeExt.getJoueur();
            for (Joueur joueurExt : joueursExt) {
                List<Stats> statsExt = sRepo.findByJoueurId(joueurExt.getId());
                for (Stats stats2 : statsExt) {
                    if (stats2.getMatchId() == match.getId() && stats2.getJoueur().getId() == joueurExt.getId()) {
                        sRepo.deleteById(stats2.getId());
                    }
                }
            }
            repo.deleteById(id);

        }
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
                    if (stats1.getMatchId() == match.getId() && stats1.getJoueur().getId() == joueur.getId()) {
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
                if (stats1.getMatchId() == match.getId() && stats1.getJoueur().getId() == joueur.getId()) {
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
                if (stats1.getMatchId() == match.getId() && stats1.getJoueur().getId() == joueur.getId()) {
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

    @Override
    public List<JoueurDTO> getJoueurDomByMatch(long matchId) throws MatchException {
        Optional<Match> match = repo.findById(matchId);
        if (match.isPresent()){
            Equipe domicile = match.get().getEquipeDomicileId();
            return getJoueurDTOS(domicile);
        } else {
            throw new MatchException("Le match n'existe pas !");
        }
    }

    @Override
    public List<JoueurDTO> getJoueurExtByMatch(long matchId) throws MatchException {
        Optional<Match> match = repo.findById(matchId);
        if (match.isPresent()){
            Equipe exterieur = match.get().getEquipeExterieurId();
            return getJoueurDTOS(exterieur);
        } else {
            throw new MatchException("Le match n'existe pas !");
        }
    }

    private List<JoueurDTO> getJoueurDTOS(Equipe equipe) {
        List<Joueur> joueurs = equipe.getJoueur();
        List<JoueurDTO> joueurDTOS = new ArrayList<>();
        for (Joueur j : joueurs){
            joueurDTOS.add(DtoConvertisseur.convert(j, JoueurDTO.class));
            return joueurDTOS;
        }
        return null;
    }


}

