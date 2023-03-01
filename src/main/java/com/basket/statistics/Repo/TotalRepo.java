package com.basket.statistics.Repo;

import com.basket.statistics.entities.Total;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TotalRepo extends JpaRepository<Total, Long> {




}
