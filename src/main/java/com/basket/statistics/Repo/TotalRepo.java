package com.basket.statistics.Repo;

import com.basket.statistics.entities.Total;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TotalRepo extends JpaRepository<Total, Long> {
    @Query("From Total t where t.stats.paniersProche= :panierProche and t.stats.paniersLoins= :panierLoin")
    int totalPoint(@Param("panierProche") int panierProche, @Param("panierLoin") int panierLoin);

}
