package com.example.demo.repository;

import com.yourpackage.entity.OverflowPrediction;
import com.yourpackage.entity.Bin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OverflowPredictionRepository
        extends JpaRepository<OverflowPrediction, Long> {

    List<OverflowPrediction> findByBin(Bin bin);

    List<OverflowPrediction> findByBinId(Long binId);
}
