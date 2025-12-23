package com.example.demo.service.impl;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;

@Service
public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    @Override
    public OverflowPrediction generatePrediction(Long binId) {
        return null; // dummy for test
    }
}
