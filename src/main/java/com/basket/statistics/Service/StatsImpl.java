package com.basket.statistics.Service;

import com.basket.statistics.MapperDto.DtoConvertisseur;
import com.basket.statistics.Repo.JoueurRepo;
import com.basket.statistics.Repo.StatsRepo;
import com.basket.statistics.dto.StatsDTO;
import com.basket.statistics.entities.Joueur;
import com.basket.statistics.entities.Stats;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StatsImpl implements StatsService{
    @Autowired
    private StatsRepo repo;

    @Autowired
    private JoueurRepo jRepo;


    @Override
    public StatsDTO saveOrUpdate(StatsDTO statsDTO, long id) {
        Optional<Joueur> joueur = jRepo.findById(id);
        Stats s = DtoConvertisseur.convert(statsDTO, Stats.class);
        if (joueur.isPresent()){
            s.setJoueur(joueur.get());
            s = repo.saveAndFlush(s);
            return DtoConvertisseur.convert(s, StatsDTO.class);
        }else {
            return null;
        }


    }

    @Override
    public List<StatsDTO> findByJoueurId(long id) {
        List<Stats> stats = repo.findByJoueurId(id);
        List<StatsDTO> statsDTOS = new ArrayList<>();
        for (Stats s : stats){
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
        for (Stats s : statsList){
            statsDTOS.add(DtoConvertisseur.convert(s, StatsDTO.class));
        }
        return statsDTOS;
    }

    @Override
    public void suppression(long id) {
        repo.deleteById(id);
    }

    @Override
    public StatsDTO pointsMarque(long id){
        Optional<Stats> recupStat = repo.findById(id);
        if (recupStat.isPresent()){
            Stats stats = recupStat.get();
            int currentValue = stats.getPaniersProche(); // Récupération de la valeur actuelle du champ
            int newValue = currentValue + 1; // Incrément de la valeur actuelle
            stats.setPaniersProche(newValue); // Mise à jour de la valeur du champ avec la nouvelle valeur incrémentée
            repo.save(stats);
            return DtoConvertisseur.convert(stats, StatsDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public StatsDTO tirRate(long id) {
        Optional<Stats> recupStat = repo.findById(id);
        if (recupStat.isPresent()) {
            Stats stats = recupStat.get();
            int currentValue = stats.getTirRateProche(); // Récupération de la valeur actuelle du champ
            int newValue = currentValue + 1; // Incrément de la valeur actuelle
            stats.setTirRateProche(newValue); // Mise à jour de la valeur du champ avec la nouvelle valeur incrémentée
            repo.save(stats);
            return DtoConvertisseur.convert(stats, StatsDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public StatsDTO tirTroisPoints(long id) {
        Optional<Stats> recupStat = repo.findById(id);
        if (recupStat.isPresent()) {
            Stats stats = recupStat.get();
            int currentValue = stats.getPaniersLoins(); // Récupération de la valeur actuelle du champ
            int newValue = currentValue + 1; // Incrément de la valeur actuelle
            stats.setPaniersLoins(newValue); // Mise à jour de la valeur du champ avec la nouvelle valeur incrémentée
            repo.save(stats);
            return DtoConvertisseur.convert(stats, StatsDTO.class);
        } else {
            return null;
        }
    }
    @Override
    public StatsDTO tirTroisPointsRate(long id) {
        Optional<Stats> recupStat = repo.findById(id);
        if (recupStat.isPresent()) {
            Stats stats = recupStat.get();
            int currentValue = stats.getTirRateLoin(); // Récupération de la valeur actuelle du champ
            int newValue = currentValue + 1; // Incrément de la valeur actuelle
            stats.setTirRateLoin(newValue); // Mise à jour de la valeur du champ avec la nouvelle valeur incrémentée
            repo.save(stats);
            return DtoConvertisseur.convert(stats, StatsDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public StatsDTO ballonPerduAjout(long id) {
        Optional<Stats> recupStat = repo.findById(id);
        if (recupStat.isPresent()) {
            Stats stats = recupStat.get();
            int currentValue = stats.getBallonPerdu(); // Récupération de la valeur actuelle du champ
            int newValue = currentValue + 1; // Incrément de la valeur actuelle
            stats.setBallonPerdu(newValue); // Mise à jour de la valeur du champ avec la nouvelle valeur incrémentée
            repo.save(stats);
            return DtoConvertisseur.convert(stats, StatsDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public StatsDTO ajoutContre(long id) {
        Optional<Stats> recupStat = repo.findById(id);
        if (recupStat.isPresent()) {
            Stats stats = recupStat.get();
            int currentValue = stats.getContre(); // Récupération de la valeur actuelle du champ
            int newValue = currentValue + 1; // Incrément de la valeur actuelle
            stats.setContre(newValue); // Mise à jour de la valeur du champ avec la nouvelle valeur incrémentée
            repo.save(stats);
            return DtoConvertisseur.convert(stats, StatsDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public StatsDTO ajoutFautes(long id) {
        Optional<Stats> recupStat = repo.findById(id);
        if (recupStat.isPresent()) {
            Stats stats = recupStat.get();
            int currentValue = stats.getFautes(); // Récupération de la valeur actuelle du champ
            int newValue = currentValue + 1; // Incrément de la valeur actuelle
            stats.setTirRateLoin(newValue); // Mise à jour de la valeur du champ avec la nouvelle valeur incrémentée
            repo.save(stats);
            return DtoConvertisseur.convert(stats, StatsDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public StatsDTO ajoutPasse(long id) {
        Optional<Stats> recupStat = repo.findById(id);
        if (recupStat.isPresent()) {
            Stats stats = recupStat.get();
            int currentValue = stats.getPasseD(); // Récupération de la valeur actuelle du champ
            int newValue = currentValue + 1; // Incrément de la valeur actuelle
            stats.setPasseD(newValue); // Mise à jour de la valeur du champ avec la nouvelle valeur incrémentée
            repo.save(stats);
            return DtoConvertisseur.convert(stats, StatsDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public StatsDTO ajoutRebondOff(long id) {
        Optional<Stats> recupStat = repo.findById(id);
        if (recupStat.isPresent()) {
            Stats stats = recupStat.get();
            int currentValue = stats.getRebondOff(); // Récupération de la valeur actuelle du champ
            int newValue = currentValue + 1; // Incrément de la valeur actuelle
            stats.setRebondOff(newValue); // Mise à jour de la valeur du champ avec la nouvelle valeur incrémentée
            repo.save(stats);
            return DtoConvertisseur.convert(stats, StatsDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public StatsDTO ajoutrebondDeff(long id) {
        Optional<Stats> recupStat = repo.findById(id);
        if (recupStat.isPresent()) {
            Stats stats = recupStat.get();
            int currentValue = stats.getRebondDeff(); // Récupération de la valeur actuelle du champ
            int newValue = currentValue + 1; // Incrément de la valeur actuelle
            stats.setRebondDeff(newValue); // Mise à jour de la valeur du champ avec la nouvelle valeur incrémentée
            repo.save(stats);
            return DtoConvertisseur.convert(stats, StatsDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public StatsDTO ajoutLFMarque(long id) {
        Optional<Stats> recupStat = repo.findById(id);
        if (recupStat.isPresent()) {
            Stats stats = recupStat.get();
            int currentValue = stats.getLfMarque(); // Récupération de la valeur actuelle du champ
            int newValue = currentValue + 1; // Incrément de la valeur actuelle
            stats.setLfMarque(newValue); // Mise à jour de la valeur du champ avec la nouvelle valeur incrémentée
            repo.save(stats);
            return DtoConvertisseur.convert(stats, StatsDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public StatsDTO ajoutLFRate(long id) {
        Optional<Stats> recupStat = repo.findById(id);
        if (recupStat.isPresent()) {
            Stats stats = recupStat.get();
            int currentValue = stats.getLfRate(); // Récupération de la valeur actuelle du champ
            int newValue = currentValue + 1; // Incrément de la valeur actuelle
            stats.setLfRate(newValue); // Mise à jour de la valeur du champ avec la nouvelle valeur incrémentée
            repo.save(stats);
            return DtoConvertisseur.convert(stats, StatsDTO.class);
        } else {
            return null;
        }
    }
}
