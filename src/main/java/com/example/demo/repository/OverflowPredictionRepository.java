package com.example.demo.repository;

import com.example.demo.model.*;
import org.springframework.data.jpa.repository.*;
import java.util.List;

public interface OverflowPredictionRepository
        extends JpaRepository<OverflowPrediction, Long> {

    List<OverflowPrediction> findByBin(Bin bin);

    @Query("SELECT o FROM OverflowPrediction o WHERE o.bin.zone = :zone ORDER BY o.generatedAt DESC")
    List<OverflowPrediction> findLatestPredictionsForZone(Zone zone);
}
