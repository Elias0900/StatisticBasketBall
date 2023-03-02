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
    private JoueurRepo jRepo;

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
                        match.setScoreDomicile(match.getScoreDomicile() + 2);
                        return match.getScoreDomicile();
                }
            }
        }
        return 0; // ou une autre valeur par défaut si le joueur ou le match n'est pas trouvé
    }


    @Override
    public int marquer3Point(long matchId, long joueurId) {
        Match match = repo.getReferenceById(matchId);
        Equipe equipe = match.getEquipeDomicileId();
        List<Joueur> joueur1 = equipe.getJoueur();
        for (Joueur j : joueur1){
            if (j.getId() == joueurId) {
                Stats stats = (Stats) j.getStats();
                statsService.tirTroisPoints(stats.getId());
                match.setScoreDomicile(+3);
            }
        }
        return match.getScoreDomicile();
    }

    @Override
    public int marquer1Point(long matchId, long joueurId) {
        Match match = repo.getReferenceById(matchId);
        Equipe equipe = match.getEquipeDomicileId();
        List<Joueur> joueur1 = equipe.getJoueur();
        for (Joueur j : joueur1){
            if (j.getId() == joueurId) {
                Stats stats = (Stats) j.getStats();
                statsService.ajoutLFMarque(stats.getId());
                match.setScoreDomicile(+1);
            }
        }
        return match.getScoreDomicile();
    }


}

