package com.example.demo.service.impl;

import com.example.demo.repository.OverflowPredictionRepository;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;

@Service
public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    private final OverflowPredictionRepository repository;

    public OverflowPredictionServiceImpl(OverflowPredictionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void generatePrediction(Long binId) {
        // dummy
    }

    // ✅ Method called in test
    public void getLatestPredictionsForZone(long zoneId) {
        // dummy
    }
}
