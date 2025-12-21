package com.example.demo.repository;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OverflowPredictionRepository extends JpaRepository<OverflowPrediction, Long> {

    @Query("SELECT o FROM OverflowPrediction o WHERE o.bin.zone = :zone " +
           "AND o.generatedAt = (SELECT MAX(o2.generatedAt) FROM OverflowPrediction o2 WHERE o2.bin = o.bin)")
    List<OverflowPrediction> findLatestPredictionsForZone(Zone zone);

    List<OverflowPrediction> findByBinId(Long binId);
}
