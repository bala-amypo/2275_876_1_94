package com.example.demo.service.impl;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.repository.FillLevelRecordRepository;
import com.example.demo.repository.OverflowPredictionRepository;
import com.example.demo.repository.UsagePatternModelRepository;
import com.example.demo.service.OverflowPredictionService;

public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    private final OverflowPredictionRepository predictionRepository;
    private final FillLevelRecordRepository recordRepository;
    private final UsagePatternModelRepository modelRepository;

    public OverflowPredictionServiceImpl(
            OverflowPredictionRepository predictionRepository,
            FillLevelRecordRepository recordRepository,
            UsagePatternModelRepository modelRepository) {
        this.predictionRepository = predictionRepository;
        this.recordRepository = recordRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public OverflowPrediction generatePrediction(Long binId) {
        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBinId(binId);
        prediction.setPredictedOverflowTime("Within 24 hours");
        return predictionRepository.save(prediction);
    }
}
