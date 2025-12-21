package com.example.demo.repository;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface OverflowPredictionRepository extends JpaRepository<OverflowPrediction, Long> {

    @Query("SELECT op FROM OverflowPrediction op " +
           "JOIN op.bin b " +
           "WHERE b.zone = :zone " +
           "AND op.generatedAt = (" +
           "   SELECT MAX(op2.generatedAt) FROM OverflowPrediction op2 WHERE op2.bin = b" +
           ")")
    List<OverflowPrediction> findLatestPredictionsForZone(Zone zone);
}
