package com.example.demo.service;

import com.example.demo.model.OverflowPrediction;

public interface OverflowPredictionService {
    OverflowPrediction generatePrediction(Long binId);
}
