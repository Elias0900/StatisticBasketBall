package com.basket.statistics.Service;

import com.basket.statistics.MapperDto.DtoConvertisseur;
import com.basket.statistics.Repo.StatsRepo;
import com.basket.statistics.Repo.TotalRepo;
import com.basket.statistics.dto.TotalDTO;
import com.basket.statistics.entities.Stats;
import com.basket.statistics.entities.Total;
import com.basket.statistics.exception.TotalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TotalImpl implements TotalService {
    @Autowired
    private TotalRepo repo;

    @Autowired
    private StatsRepo statsRepo;


    @Override
    public TotalDTO saveOrUpdate(TotalDTO totalDTO, long id, long matchId) {
        Total t = DtoConvertisseur.convert(totalDTO, Total.class);

        t = repo.saveAndFlush(t);
        return DtoConvertisseur.convert(t, TotalDTO.class);


    }

    @Override
    public double totalPoint(long joueurId, long matchId) throws TotalException {
        Stats stats = statsRepo.getStatsByJoueurIdAndMatchId(joueurId, matchId);
        if (stats.getPaniersLoins() > 0 || stats.getPaniersProche() > 0 || stats.getLfMarque() > 0) {
            Total total1 = stats.getTotal();
            double deuxPoints = stats.getPaniersProche();
            double troisPoints = stats.getPaniersLoins();
            double deuxPointsTotal = deuxPoints * 2;
            double troisPointsTotal = troisPoints * 3;
            total1.setTotalPoints(deuxPointsTotal + troisPointsTotal + stats.getLfMarque());
            repo.save(total1);
            TotalDTO dto = DtoConvertisseur.convert(total1, TotalDTO.class);
            return dto.getTotalPoints();
        } else {
            throw new TotalException(0 + " Points");
        }
    }

    @Override
    public double totalPasse(long joueurId, long matchId) throws TotalException {
        Stats stats = statsRepo.getStatsByJoueurIdAndMatchId(joueurId, matchId);
        if (stats.getPasseD() >= 0) {
            Total total1 = stats.getTotal();
            total1.setTotalPasseD(stats.getPasseD());
            repo.save(total1);
            TotalDTO dto = DtoConvertisseur.convert(total1, TotalDTO.class);
            return dto.getTotalPasseD();
        } else {
            throw new TotalException(0 + " Passe Décisive !");
        }
    }

    @Override
    public double totalContre(long joueurId, long matchId) throws TotalException {
        Stats stats = statsRepo.getStatsByJoueurIdAndMatchId(joueurId, matchId);
        if (stats.getPasseD() >= 0) {
            Total total1 = stats.getTotal();
            total1.setTotalContre(stats.getContre());
            repo.save(total1);
            TotalDTO dto = DtoConvertisseur.convert(total1, TotalDTO.class);
            return dto.getTotalPasseD();
        } else {
            throw new TotalException(0 + " Contres !");
        }
    }

    @Override
    public double totalInterc(long joueurId, long matchId) throws TotalException {
        Stats stats = statsRepo.getStatsByJoueurIdAndMatchId(joueurId, matchId);
        if (stats.getPasseD() >= 0) {
            Total total1 = stats.getTotal();
            total1.setTotalInterception(stats.getInterception());
            repo.save(total1);
            TotalDTO dto = DtoConvertisseur.convert(total1, TotalDTO.class);
            return dto.getTotalPasseD();
        } else {
            throw new TotalException(0 + " Interceptions !");
        }
    }

    @Override
    public double totalRebond(long joueurId, long matchId) throws TotalException {
        Stats stats = statsRepo.getStatsByJoueurIdAndMatchId(joueurId, matchId);
        if (stats.getRebondDeff() > 0 || stats.getRebondOff() > 0) {
            Total total1 = stats.getTotal();
            total1.setTotalRebonds(stats.getRebondOff() + stats.getRebondDeff());
            repo.save(total1);
            TotalDTO dto = DtoConvertisseur.convert(total1, TotalDTO.class);
            return dto.getTotalRebonds();
        } else {
            throw new TotalException("Aucun Rebonds pris !");
        }
    }

    @Override
    public double pourcentageDeuxPts(long joueurId, long matchId) throws TotalException {
        Stats stats = statsRepo.getStatsByJoueurIdAndMatchId(joueurId, matchId);
        Total total1 = stats.getTotal();
        if (stats.getTirRateProche() > 0 && stats.getPaniersProche() > 0) {
            total1.setPourcentageDeuxPts((stats.getPaniersProche() / (stats.getTirRateProche() + stats.getPaniersProche())) * 100);
            repo.save(total1);
            TotalDTO dto = DtoConvertisseur.convert(total1, TotalDTO.class);
            return dto.getPourcentageDeuxPts();
        } else if (stats.getTirRateProche() == 0 && stats.getPaniersProche() > 0) {
            total1.setPourcentageDeuxPts(100.0);
            repo.save(total1);
            TotalDTO dto2 = DtoConvertisseur.convert(total1, TotalDTO.class);
            return dto2.getPourcentageDeuxPts();
        } else if (stats.getPaniersProche() == 0 && stats.getTirRateProche() > 0) {
            total1.setPourcentageDeuxPts(0);
            repo.save(total1);
            TotalDTO dto3 = DtoConvertisseur.convert(total1, TotalDTO.class);
            return dto3.getPourcentageDeuxPts();
        } else {
            throw new TotalException("Aucun tir proche tenté !");
        }
    }

    @Override
    public double pourcentageTroisPts(long joueurId, long matchId) throws TotalException {
        Stats stats = statsRepo.getStatsByJoueurIdAndMatchId(joueurId, matchId);
        Total total1 = stats.getTotal();
        if (stats.getTirRateLoin() > 0 && stats.getPaniersLoins() > 0) {
            total1.setPourcentageTroisPts((stats.getPaniersLoins() / (stats.getTirRateLoin() + stats.getPaniersLoins())) * 100);
            repo.save(total1);
            TotalDTO dto = DtoConvertisseur.convert(total1, TotalDTO.class);
            return dto.getPourcentageTroisPts();
        } else if (stats.getTirRateLoin() == 0 && stats.getPaniersLoins() > 0) {
            total1.setPourcentageDeuxPts(100.0);
            repo.save(total1);
            TotalDTO dto2 = DtoConvertisseur.convert(total1, TotalDTO.class);
            return dto2.getPourcentageTroisPts();
        } else if (stats.getPaniersLoins() == 0 && stats.getTirRateLoin() > 0) {
            total1.setPourcentageDeuxPts(0);
            repo.save(total1);
            TotalDTO dto3 = DtoConvertisseur.convert(total1, TotalDTO.class);
            return dto3.getPourcentageTroisPts();
        } else {
            throw new TotalException("Aucun 3 points tenté !");
        }
    }

    @Override
    public double pourcentageLF(long joueurId, long matchId) throws TotalException {
        Stats stats = statsRepo.getStatsByJoueurIdAndMatchId(joueurId, matchId);
        Total total1 = stats.getTotal();
        if (stats.getLfMarque() > 0 && stats.getLfRate() > 0) {
            total1.setPourcentageLF((stats.getLfMarque() / (stats.getLfRate() + stats.getLfMarque())) * 100);
            repo.save(total1);
            TotalDTO dto = DtoConvertisseur.convert(total1, TotalDTO.class);
            return dto.getPourcentageLF();
        } else if (stats.getLfRate() == 0 && stats.getLfMarque() > 0) {
            total1.setPourcentageDeuxPts(100.0);
            repo.save(total1);
            TotalDTO dto2 = DtoConvertisseur.convert(total1, TotalDTO.class);
            return dto2.getPourcentageLF();
        } else if (stats.getLfMarque() == 0 && stats.getLfRate() > 0) {
            total1.setPourcentageDeuxPts(0);
            repo.save(total1);
            TotalDTO dto3 = DtoConvertisseur.convert(total1, TotalDTO.class);
            return dto3.getPourcentageLF();
        } else {
            throw new TotalException("Aucun Lancers-Franc proche tenté !");
        }
    }

    @Override
    public double pourcentage(long joueurId, long matchId) throws TotalException {
        Stats stats = statsRepo.getStatsByJoueurIdAndMatchId(joueurId, matchId);
        if (stats.getTirTotal() > 0) {
            Total total1 = stats.getTotal();
            total1.setPourcentage(
                    (
                            (stats.getPaniersProche() + stats.getPaniersLoins())
                                    /
                                    stats.getTirTotal()
                    ) * 100
            );
            repo.save(total1);
            TotalDTO dto = DtoConvertisseur.convert(total1, TotalDTO.class);
            return dto.getPourcentage();
        } else {
            throw new TotalException("Aucun tir tentés !");
        }
    }



}
