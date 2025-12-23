package com.example.demo.service;

import com.example.demo.model.OverflowPrediction;
import java.util.List;

public interface OverflowPredictionService {

    OverflowPrediction createPrediction(OverflowPrediction prediction);

    OverflowPrediction getPredictionById(Long id);

    List<OverflowPrediction> getPredictionsForBin(Long binId);
}
