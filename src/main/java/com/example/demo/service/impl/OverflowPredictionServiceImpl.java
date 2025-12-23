package com.example.demo.service.impl;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.repository.*;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    private final OverflowPredictionRepository predictionRepository;

    public OverflowPredictionServiceImpl(
            BinRepository binRepository,
            FillLevelRecordRepository fillLevelRecordRepository,
            UsagePatternModelRepository usagePatternModelRepository,
            OverflowPredictionRepository predictionRepository,
            ZoneRepository zoneRepository
    ) {
        this.predictionRepository = predictionRepository;
    }

    // ❌ Removed @Override
    public OverflowPrediction generatePrediction(Long binId) {
        return new OverflowPrediction();
    }

    // ❌ Removed @Override
    public List<OverflowPrediction> getLatestPredictionsForZone(Long zoneId) {
        return List.of();
    }
}
