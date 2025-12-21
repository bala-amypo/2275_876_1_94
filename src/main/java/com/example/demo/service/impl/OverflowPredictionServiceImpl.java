package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.OverflowPrediction;
import com.example.demo.repository.OverflowPredictionRepository;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    @Autowired
    private OverflowPredictionRepository predictionRepository;

    @Override
    public OverflowPrediction getLatestPredictionByBin(Bin bin) {
        List<OverflowPrediction> predictions = predictionRepository.findByBinOrderByGeneratedAtDesc(bin);

        if (predictions.isEmpty()) {
            throw new ResourceNotFoundException("No predictions found for Bin ID: " + bin.getId());
        }

        return predictions.get(0); // Return the latest prediction
    }
}
