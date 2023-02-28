package com.basket.statistics.Service;

import com.basket.statistics.MapperDto.DtoConvertisseur;
import com.basket.statistics.Repo.StatsRepo;
import com.basket.statistics.Repo.TotalRepo;
import com.basket.statistics.dto.TotalDTO;
import com.basket.statistics.entities.Stats;
import com.basket.statistics.entities.Total;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class TotalImpl implements TotalService{
    @Autowired
    private TotalRepo repo;

    @Autowired
    private StatsRepo statsRepo;


    @Override
    public TotalDTO saveOrUpdate(TotalDTO totalDTO, long id) {
        Optional<Stats> stats = statsRepo.findById(id);
        Total t = DtoConvertisseur.convert(totalDTO, Total.class);
        if (stats.isPresent()) {
            t = repo.saveAndFlush(t);
            return DtoConvertisseur.convert(t, TotalDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public int totalPoint(long id) {
        Optional<Stats> recupStat = statsRepo.findById(id);
        if (recupStat.isPresent() && recupStat.get().getTotal().getId() != 0) {
            Stats stats = recupStat.get();
            Total total1 = stats.getTotal();
            int deuxPoints = stats.getPaniersProche(); // Récupération de la valeur actuelle du champ
            int troisPoints = stats.getPaniersLoins(); // Récupération de la valeur actuelle du champ
            int deuxPointsTotal = deuxPoints * 2; // Incrément de la valeur actuelle
            int troisPointsTotal = troisPoints * 3; // Incrément de la valeur actuelle
            total1.setTotalPoints(deuxPointsTotal + troisPointsTotal);
            repo.save(total1);
            TotalDTO dto = DtoConvertisseur.convert(total1, TotalDTO.class);
            return dto.getTotalPoints();
        } else {
            return 0;
        }
    }

    @Override
    public int totalPasse(long id) {
        Optional<Stats> recupStat = statsRepo.findById(id);
        if (recupStat.isPresent() && recupStat.get().getTotal().getId() != 0) {
            Stats stats = recupStat.get();
            Total total1 = stats.getTotal();
            total1.setTotalPasseD(stats.getPasseD());
            repo.save(total1);
            TotalDTO dto = DtoConvertisseur.convert(total1, TotalDTO.class);
            return dto.getTotalPasseD();
        } else {
            return 0;
        }
    }

    @Override
    public int totalRebond( long id) {
        Optional<Stats> recupStat = statsRepo.findById(id);
        if (recupStat.isPresent() && recupStat.get().getTotal().getId() != 0) {
            Stats stats = recupStat.get();
            Total total1 = stats.getTotal();
            total1.setTotalRebonds(stats.getRebondOff() + stats.getRebondDeff());
            repo.save(total1);
            TotalDTO dto = DtoConvertisseur.convert(total1, TotalDTO.class);
            return dto.getTotalRebonds();        }
        else {
            return 0;
        }
    }

    @Override
    public double pourcentageDeuxPts(long id) {
        Optional<Stats> recupStat = statsRepo.findById(id);
        if (recupStat.isPresent() && recupStat.get().getTotal().getId() != 0) {
            Stats stats = recupStat.get();
            Total total1 = stats.getTotal();
            total1.setPourcentageDeuxPts(((double) stats.getPaniersProche() / (double) stats.getTirRateProche()) *100 );
            repo.save(total1);
            TotalDTO dto = DtoConvertisseur.convert(total1, TotalDTO.class);
            return dto.getPourcentageDeuxPts();
        } else {
            return 5555;
        }
    }

    @Override
    public double pourcentageTroisPts( long id) {
        Optional<Stats> recupStat = statsRepo.findById(id);
        if (recupStat.isPresent() && recupStat.get().getTotal().getId() != 0) {
            Stats stats = recupStat.get();
            Total total1 = stats.getTotal();
            total1.setPourcentageTroisPts(( (double) stats.getPaniersLoins() / (double) stats.getTirRateLoin())*100);
            repo.save(total1);
            TotalDTO dto = DtoConvertisseur.convert(total1, TotalDTO.class);
            return dto.getPourcentageTroisPts();
        } else {
            return 0;
        }
    }

    @Override
    public double pourcentage(long id) {
        Optional<Stats> recupStat = statsRepo.findById(id);
        if (recupStat.isPresent() && recupStat.get().getTotal().getId() != 0) {
            Stats stats = recupStat.get();
            Total total1 = stats.getTotal();
            total1.setPourcentage(
                    (
                            ((double) stats.getPaniersProche() + (double) stats.getPaniersLoins())
                                    /
                                    ((double) stats.getTirRateProche() + (double) stats.getTirRateProche())
                    ) * 100
            );
            repo.save(total1);
            TotalDTO dto = DtoConvertisseur.convert(total1, TotalDTO.class);
            return dto.getPourcentage();
        } else {
            return 0;
        }
    }
}
