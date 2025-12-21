package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.OverflowPrediction;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.OverflowPredictionRepository;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;

@Service
public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    @Autowired
    private BinRepository binRepository;

    @Autowired
    private OverflowPredictionRepository predictionRepository;

    @Override
    public List<OverflowPrediction> getLatestPredictionsForZone(Long zoneId) {
        List<Bin> bins = binRepository.findByZoneId(zoneId);
        List<OverflowPrediction> predictions = new ArrayList<>();
        for (Bin bin : bins) {
            predictions.addAll(predictionRepository.findByBinOrderByGeneratedAtDesc(bin));
        }
        return predictions;
    }
}
