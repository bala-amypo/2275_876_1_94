package com.example.demo.repository;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OverflowPredictionRepository extends JpaRepository<OverflowPrediction, Long> {

    @Query("SELECT p FROM OverflowPrediction p JOIN p.bin b WHERE b.zone = :zone AND p.generatedAt = (SELECT MAX(p2.generatedAt) FROM OverflowPrediction p2 WHERE p2.bin = b)")
    List<OverflowPrediction> findLatestPredictionsForZone(Zone zone);
}
