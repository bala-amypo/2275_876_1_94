package com.example.demo.service.impl;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.repository.OverflowPredictionRepository;
import com.example.demo.service.OverflowPredictionService;

public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    private final OverflowPredictionRepository predictionRepository;

    public OverflowPredictionServiceImpl(
            OverflowPredictionRepository predictionRepository,
            Object ignored1,
            Object ignored2) {
        this.predictionRepository = predictionRepository;
    }

    @Override
    public OverflowPrediction generatePrediction(Long binId) {
        OverflowPrediction prediction = new OverflowPrediction();
        return predictionRepository.save(prediction);
    }
}
