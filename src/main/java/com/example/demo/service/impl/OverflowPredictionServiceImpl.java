package com.example.demo.service.impl;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    @Override
    public OverflowPrediction createPrediction(OverflowPrediction prediction) {
        return prediction;
    }

    @Override
    public List<OverflowPrediction> getLatestPredictionsForZone(Long zoneId) {
        return new ArrayList<>();
    }
}
