package com.basket.statistics.Service;

import com.basket.statistics.MapperDto.DtoConvertisseur;
import com.basket.statistics.Repo.EquipeRepo;
import com.basket.statistics.Repo.JoueurRepo;
import com.basket.statistics.Repo.MatchRepo;
import com.basket.statistics.dto.MatchDTO;
import com.basket.statistics.entities.*;

import javax.transaction.Transactional;

import com.basket.statistics.exception.MatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MatchImpl implements MatchService{
    @Autowired
    private MatchRepo repo;



    @Autowired
    private StatsService statsService;
    @Autowired
    private EquipeRepo equipeRepo;

    @Override
    public List<MatchDTO> getAll() {
        List<Match> jList = repo.findAll();
        List<MatchDTO> MatchDTOList = new ArrayList<>();
        for (Match j : jList){
            MatchDTOList.add(DtoConvertisseur.convert(j, MatchDTO.class));
        }
        return MatchDTOList;
    }

    @Override
    public MatchDTO saveOrUpdate(MatchDTO j) throws MatchException {
        Equipe equipedom = equipeRepo.getReferenceById(j.getEquipeDomicileId());
        Equipe equipeext = equipeRepo.getReferenceById(j.getEquipeExterieurId());
        if (equipedom != equipeext) {
            Match match = DtoConvertisseur.convert(j, Match.class);
            match.setEquipeDomicileId(equipedom);
            match.setEquipeExterieurId(equipeext);
            repo.saveAndFlush(match);
            return DtoConvertisseur.convert(match, MatchDTO.class);
        }else {
            throw new MatchException("Vous ne pouvez pas choisir deux fois la meme equipe");
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
        for (Joueur joueur : joueurs) {
            if (joueur.getId() == joueurId) {
                List<Stats> statsList = joueur.getStats();
                for (Stats stats : statsList) {
                        statsService.pointsMarque(stats.getId());
                        return match.getScoreDomicile();
                }
            }
        }
        return match.getScoreExterieur(); // ou une autre valeur par défaut si le joueur ou le match n'est pas trouvé
    }

    @Override
    public int marquer2PointExt(long matchId, long joueurId) {
        Match match = repo.getReferenceById(matchId);
        Equipe equipe = match.getEquipeExterieurId();
        List<Joueur> joueurs = equipe.getJoueur();
        for (Joueur joueur : joueurs) {
            if (joueur.getId() == joueurId) {
                List<Stats> statsList = joueur.getStats();
                for (Stats stats : statsList) {
                    statsService.pointsMarque(stats.getId());
                    return match.getScoreExterieur();
                }
            }
        }
        return match.getScoreExterieur(); // ou une autre valeur par défaut si le joueur ou le match n'est pas trouvé
    }


    @Override
    public int marquer3Point(long matchId, long joueurId) {
        Match match = repo.getReferenceById(matchId);
        Equipe equipe = match.getEquipeDomicileId();
        List<Joueur> joueurs = equipe.getJoueur();
        for (Joueur joueur : joueurs) {
            if (joueur.getId() == joueurId) {
                List<Stats> statsList = joueur.getStats();
                for (Stats stats : statsList) {
                    statsService.tirTroisPoints(stats.getId());
                    return match.getScoreDomicile();
                }
            }
        }
        return match.getScoreDomicile();
    }

    @Override
    public int marquer3PointExt(long matchId, long joueurId) {
        Match match = repo.getReferenceById(matchId);
        Equipe equipe = match.getEquipeExterieurId();
        List<Joueur> joueurs = equipe.getJoueur();
        for (Joueur joueur : joueurs) {
            if (joueur.getId() == joueurId) {
                List<Stats> statsList = joueur.getStats();
                for (Stats stats : statsList) {
                    statsService.tirTroisPoints(stats.getId());
                    return match.getScoreExterieur();
                }
            }
        }
        return match.getScoreExterieur();
    }

    @Override
    public int marquer1Point(long matchId, long joueurId) {
        Match match = repo.getReferenceById(matchId);
        Equipe equipe = match.getEquipeDomicileId();
        List<Joueur> joueurs = equipe.getJoueur();
        for (Joueur joueur : joueurs) {
            if (joueur.getId() == joueurId) {
                List<Stats> statsList = joueur.getStats();
                for (Stats stats : statsList) {
                    statsService.ajoutLFMarque(stats.getId());
                    return match.getScoreDomicile();
                }
            }
        }
        return match.getScoreDomicile();
    }

    @Override
    public int marquer1PointExt(long matchId, long joueurId) {
        Match match = repo.getReferenceById(matchId);
        Equipe equipe = match.getEquipeExterieurId();
        List<Joueur> joueurs = equipe.getJoueur();
        for (Joueur joueur : joueurs) {
            if (joueur.getId() == joueurId) {
                List<Stats> statsList = joueur.getStats();
                for (Stats stats : statsList) {
                    statsService.ajoutLFMarque(stats.getId());
                    return match.getScoreExterieur();
                }
            }
        }
        return match.getScoreExterieur();
    }


}

