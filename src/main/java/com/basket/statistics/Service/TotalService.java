package com.basket.statistics.Service;

import com.basket.statistics.dto.TotalDTO;

public interface TotalService {

    TotalDTO saveOrUpdate(TotalDTO totalDTO, long id);

    int totalPoint(long id);

    int totalPasse( long id);

    int totalRebond( long id);

    double pourcentageLF(long id);

    double pourcentage(long id);

    double pourcentageDeuxPts(long id);

    double pourcentageTroisPts( long id);
}
