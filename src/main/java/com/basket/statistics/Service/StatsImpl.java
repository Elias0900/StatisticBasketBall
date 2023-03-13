package com.basket.statistics.Service;

import com.basket.statistics.MapperDto.DtoConvertisseur;
import com.basket.statistics.Repo.JoueurRepo;
import com.basket.statistics.Repo.MatchRepo;
import com.basket.statistics.Repo.StatsRepo;
import com.basket.statistics.Repo.TotalRepo;
import com.basket.statistics.dto.StatsDTO;
import com.basket.statistics.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StatsImpl implements StatsService {
    @Autowired
    private StatsRepo repo;

    @Autowired
    private JoueurRepo jRepo;

    @Autowired
    private MatchRepo mRepo;

    @Autowired
    private TotalRepo tRepo;

    @Override
    public StatsDTO saveOrUpdate(StatsDTO statsDTO, long id) {
        Optional<Joueur> joueur = jRepo.findById(id);
        Stats s = DtoConvertisseur.convert(statsDTO, Stats.class);
        if (joueur.isPresent()) {
            s.setJoueur(joueur.get());
            Total total = tRepo.save(new Total());
            s.setTotal(total);
            s = repo.saveAndFlush(s);

            return DtoConvertisseur.convert(s, StatsDTO.class);
        } else {
            return null;
        }


    }

    @Override
    public List<StatsDTO> findByJoueurId(long id) {
        List<Stats> stats = repo.findByJoueurId(id);
        List<StatsDTO> statsDTOS = new ArrayList<>();
        for (Stats s : stats) {
            statsDTOS.add(DtoConvertisseur.convert(s, StatsDTO.class));
        }
        return statsDTOS;
    }

    @Override
    public StatsDTO findById(long id) {
        Optional<Stats> stat = repo.findById(id);
        if (stat.isPresent()) {
            return DtoConvertisseur.convert(stat.get(), StatsDTO.class);
        }
        return null;
    }


    @Override
    public List<StatsDTO> getAll() {
        List<Stats> statsList = repo.findAll();
        List<StatsDTO> statsDTOS = new ArrayList<>();
        for (Stats s : statsList) {
            statsDTOS.add(DtoConvertisseur.convert(s, StatsDTO.class));
        }
        return statsDTOS;
    }

    @Override
    public void suppression(long id) {
        repo.deleteById(id);
    }


    @Override
    public StatsDTO pointsMarquev2(long joueurid, long matchId) {
        Joueur joueur = jRepo.getJoueurById(joueurid);
        Equipe equipe = joueur.getEquipe();
        Stats stats = repo.getStatsByJoueurIdAndMatchId(joueur.getId(), matchId);
        Match match = mRepo.getReferenceById(matchId);
        if (equipe == match.getEquipeDomicileId()) {
            int scoreDom = match.getScoreDomicile();
            int newScoreDom = scoreDom + 2;
            match.setScoreDomicile(newScoreDom);
            mRepo.save(match);
            double currentValue = stats.getPaniersProche(); // Récupération de la valeur actuelle du champ
            double newValue = currentValue + 1; // Incrément de la valeur actuelle
            stats.setPaniersProche(newValue); // Mise à jour de la valeur du champ avec la nouvelle valeur incrémentée
            double tt = stats.getTirTotal();
            double newTT = tt + 1;
            stats.setTirTotal(newTT);
            repo.save(stats);
            return DtoConvertisseur.convert(stats, StatsDTO.class);
        } else {
            int scoreExt = match.getScoreExterieur();
            int newScoreExt = scoreExt + 2;
            match.setScoreExterieur(newScoreExt);
            mRepo.save(match);
            double currentValue = stats.getPaniersProche(); // Récupération de la valeur actuelle du champ
            double newValue = currentValue + 1; // Incrément de la valeur actuelle
            stats.setPaniersProche(newValue); // Mise à jour de la valeur du champ avec la nouvelle valeur incrémentée
            double tt = stats.getTirTotal();
            double newTT = tt + 1;
            stats.setTirTotal(newTT);
            repo.save(stats);
            return DtoConvertisseur.convert(stats, StatsDTO.class);
        }
    }


    @Override
    public StatsDTO tirRate(long joueurid, long matchId) {
        Joueur joueur = jRepo.getJoueurById(joueurid);
        Stats stats = repo.getStatsByJoueurIdAndMatchId(joueur.getId(), matchId);
        double currentValue = stats.getTirRateProche(); // Récupération de la valeur actuelle du champ
        double newValue = currentValue + 1; // Incrément de la valeur actuelle
        stats.setTirRateProche(newValue); // Mise à jour de la valeur du champ avec la nouvelle valeur incrémentée
        double tt = stats.getTirTotal();
        double newTT = tt + 1;
        stats.setTirTotal(newTT);
        repo.save(stats);
        return DtoConvertisseur.convert(stats, StatsDTO.class);
    }


    @Override
    public StatsDTO tirTroisPoints(long joueurid, long matchId) {
        Joueur joueur = jRepo.getJoueurById(joueurid);
        Equipe equipe = joueur.getEquipe();
        Stats stats = repo.getStatsByJoueurIdAndMatchId(joueur.getId(), matchId);
        Match match = mRepo.getReferenceById(matchId);
        if (equipe == match.getEquipeDomicileId()) {
            int scoreDom = match.getScoreDomicile();
            int newScoreDom = scoreDom + 3;
            match.setScoreDomicile(newScoreDom);
            mRepo.save(match);
            double currentValue = stats.getPaniersLoins(); // Récupération de la valeur actuelle du champ
            double newValue = currentValue + 1; // Incrément de la valeur actuelle
            stats.setPaniersLoins(newValue); // Mise à jour de la valeur du champ avec la nouvelle valeur incrémentée
            double tt = stats.getTirTotal();
            double newTT = tt + 1;
            stats.setTirTotal(newTT);
            repo.save(stats);
            return DtoConvertisseur.convert(stats, StatsDTO.class);
        } else {
            int scoreExt = match.getScoreExterieur();
            int newScoreExt = scoreExt + 3;
            match.setScoreExterieur(newScoreExt);
            mRepo.save(match);
            double currentValue = stats.getPaniersLoins(); // Récupération de la valeur actuelle du champ
            double newValue = currentValue + 1; // Incrément de la valeur actuelle
            stats.setPaniersLoins(newValue); // Mise à jour de la valeur du champ avec la nouvelle valeur incrémentée
            double tt = stats.getTirTotal();
            double newTT = tt + 1;
            stats.setTirTotal(newTT);
            repo.save(stats);
            return DtoConvertisseur.convert(stats, StatsDTO.class);
        }

    }


    @Override
    public StatsDTO tirTroisPointsRate(long joueurid, long matchId) {
        Joueur joueur = jRepo.getJoueurById(joueurid);
        Stats stats = repo.getStatsByJoueurIdAndMatchId(joueur.getId(), matchId);
        double currentValue = stats.getTirRateLoin(); // Récupération de la valeur actuelle du champ
        double newValue = currentValue + 1; // Incrément de la valeur actuelle
        stats.setTirRateLoin(newValue); // Mise à jour de la valeur du champ avec la nouvelle valeur incrémentée
        double tt = stats.getTirTotal();
        double newTT = tt + 1;
        stats.setTirTotal(newTT);
        repo.save(stats);
        return DtoConvertisseur.convert(stats, StatsDTO.class);
    }


    @Override
    public StatsDTO ballonPerduAjout(long joueurid, long matchId) {
        Joueur joueur = jRepo.getJoueurById(joueurid);
        Stats stats = repo.getStatsByJoueurIdAndMatchId(joueur.getId(), matchId);
        double currentValue = stats.getBallonPerdu(); // Récupération de la valeur actuelle du champ
        double newValue = currentValue + 1; // Incrément de la valeur actuelle
        stats.setBallonPerdu(newValue); // Mise à jour de la valeur du champ avec la nouvelle valeur incrémentée
        repo.save(stats);
        return DtoConvertisseur.convert(stats, StatsDTO.class);
    }


    @Override
    public StatsDTO ajoutContre(long joueurid, long matchId) {
        Joueur joueur = jRepo.getJoueurById(joueurid);
        Stats stats = repo.getStatsByJoueurIdAndMatchId(joueur.getId(), matchId);
        double currentValue = stats.getContre(); // Récupération de la valeur actuelle du champ
        double newValue = currentValue + 1; // Incrément de la valeur actuelle
        stats.setContre(newValue); // Mise à jour de la valeur du champ avec la nouvelle valeur incrémentée
        repo.save(stats);
        return DtoConvertisseur.convert(stats, StatsDTO.class);
    }


    @Override
    public StatsDTO ajoutFautes(long joueurid, long matchId) {
        Joueur joueur = jRepo.getJoueurById(joueurid);
        Stats stats = repo.getStatsByJoueurIdAndMatchId(joueur.getId(), matchId);
        double currentValue = stats.getFautes(); // Récupération de la valeur actuelle du champ
        double newValue = currentValue + 1; // Incrément de la valeur actuelle
        stats.setFautes(newValue); // Mise à jour de la valeur du champ avec la nouvelle valeur incrémentée
        repo.save(stats);
        return DtoConvertisseur.convert(stats, StatsDTO.class);
    }


    @Override
    public StatsDTO ajoutPasse(long joueurid, long matchId) {
        Joueur joueur = jRepo.getJoueurById(joueurid);
        Stats stats = repo.getStatsByJoueurIdAndMatchId(joueur.getId(), matchId);
        double currentValue = stats.getPasseD(); // Récupération de la valeur actuelle du champ
        double newValue = currentValue + 1; // Incrément de la valeur actuelle
        stats.setPasseD(newValue); // Mise à jour de la valeur du champ avec la nouvelle valeur incrémentée
        repo.save(stats);
        return DtoConvertisseur.convert(stats, StatsDTO.class);
    }

    @Override
    public StatsDTO ajoutInterceptions(long joueurid, long matchId) {
        Joueur joueur = jRepo.getJoueurById(joueurid);
        Stats stats = repo.getStatsByJoueurIdAndMatchId(joueur.getId(), matchId);
        double currentValue = stats.getInterception(); // Récupération de la valeur actuelle du champ
        double newValue = currentValue + 1; // Incrément de la valeur actuelle
        stats.setInterception(newValue); // Mise à jour de la valeur du champ avec la nouvelle valeur incrémentée
        repo.save(stats);
        return DtoConvertisseur.convert(stats, StatsDTO.class);
    }


    @Override
    public StatsDTO ajoutRebondOff(long joueurid, long matchId) {
        Joueur joueur = jRepo.getJoueurById(joueurid);
        Stats stats = repo.getStatsByJoueurIdAndMatchId(joueur.getId(), matchId);
        double currentValue = stats.getRebondOff(); // Récupération de la valeur actuelle du champ
        double newValue = currentValue + 1; // Incrément de la valeur actuelle
        stats.setRebondOff(newValue); // Mise à jour de la valeur du champ avec la nouvelle valeur incrémentée
        repo.save(stats);
        return DtoConvertisseur.convert(stats, StatsDTO.class);
    }


    @Override
    public StatsDTO ajoutrebondDeff(long joueurid, long matchId) {
        Joueur joueur = jRepo.getJoueurById(joueurid);
        Stats stats = repo.getStatsByJoueurIdAndMatchId(joueur.getId(), matchId);
        double currentValue = stats.getRebondDeff(); // Récupération de la valeur actuelle du champ
        double newValue = currentValue + 1; // Incrément de la valeur actuelle
        stats.setRebondDeff(newValue); // Mise à jour de la valeur du champ avec la nouvelle valeur incrémentée
        repo.save(stats);
        return DtoConvertisseur.convert(stats, StatsDTO.class);
    }

    @Override
    public StatsDTO ajoutLFMarque(long joueurid, long matchId) {
        Joueur joueur = jRepo.getJoueurById(joueurid);
        Equipe equipe = joueur.getEquipe();
        Stats stats = repo.getStatsByJoueurIdAndMatchId(joueur.getId(), matchId);
        Match match = mRepo.getReferenceById(matchId);
        if (equipe == match.getEquipeDomicileId()) {
            int scoreDom = match.getScoreDomicile();
            int newScoreDom = scoreDom + 1;
            match.setScoreDomicile(newScoreDom);
            mRepo.save(match);
            double currentValue = stats.getLfMarque(); // Récupération de la valeur actuelle du champ
            double newValue = currentValue + 1; // Incrément de la valeur actuelle
            stats.setLfMarque(newValue); // Mise à jour de la valeur du champ avec la nouvelle valeur incrémentée
            repo.save(stats);
            return DtoConvertisseur.convert(stats, StatsDTO.class);
        } else {
            int scoreExt = match.getScoreExterieur();
            int newScoreExt = scoreExt + 1;
            match.setScoreExterieur(newScoreExt);
            mRepo.save(match);
            double currentValue = stats.getLfMarque(); // Récupération de la valeur actuelle du champ
            double newValue = currentValue + 1; // Incrément de la valeur actuelle
            stats.setLfMarque(newValue); // Mise à jour de la valeur du champ avec la nouvelle valeur incrémentée
            repo.save(stats);
            return DtoConvertisseur.convert(stats, StatsDTO.class);
        }

    }


    @Override
    public StatsDTO ajoutLFRate(long joueurid, long matchId) {
        Joueur joueur = jRepo.getJoueurById(joueurid);
        Stats stats = repo.getStatsByJoueurIdAndMatchId(joueur.getId(), matchId);
        double currentValue = stats.getLfRate(); // Récupération de la valeur actuelle du champ
        double newValue = currentValue + 1; // Incrément de la valeur actuelle
        stats.setLfRate(newValue); // Mise à jour de la valeur du champ avec la nouvelle valeur incrémentée
        repo.save(stats);
        return DtoConvertisseur.convert(stats, StatsDTO.class);
    }
}


