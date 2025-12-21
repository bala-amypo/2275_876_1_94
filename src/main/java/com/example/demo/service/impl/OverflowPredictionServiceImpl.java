package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.OverflowPredictionService;
import com.example.demo.exception.*;
import java.sql.Timestamp;
import java.util.*;

public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    private final BinRepository binRepository;
    private final FillLevelRecordRepository recordRepository;
    private final UsagePatternModelRepository modelRepository;
    private final OverflowPredictionRepository predictionRepository;
    private final ZoneRepository zoneRepository;

    public OverflowPredictionServiceImpl(
            BinRepository binRepository,
            FillLevelRecordRepository recordRepository,
            UsagePatternModelRepository modelRepository,
            OverflowPredictionRepository predictionRepository,
            ZoneRepository zoneRepository) {

        this.binRepository = binRepository;
        this.recordRepository = recordRepository;
        this.modelRepository = modelRepository;
        this.predictionRepository = predictionRepository;
        this.zoneRepository = zoneRepository;
    }

    public OverflowPrediction generatePrediction(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));

        FillLevelRecord record = recordRepository.findTop1ByBinOrderByRecordedAtDesc(bin)
                .orElseThrow(() -> new ResourceNotFoundException("No records"));

        UsagePatternModel model = modelRepository.findTop1ByBinOrderByLastUpdatedDesc(bin)
                .orElseThrow(() -> new ResourceNotFoundException("No model"));

        int days = Math.max(0,
                (int)((100 - record.getFillPercentage()) /
                (record.getIsWeekend() ? model.getAvgDailyIncreaseWeekend()
                                       : model.getAvgDailyIncreaseWeekday())));

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, days);

        OverflowPrediction prediction = new OverflowPrediction(
                bin, cal.getTime(), days, model,
                new Timestamp(System.currentTimeMillis()));

        return predictionRepository.save(prediction);
    }

    public OverflowPrediction getPredictionById(Long id) {
        return predictionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prediction not found"));
    }

    public List<OverflowPrediction> getPredictionsForBin(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        return predictionRepository.findAll()
                .stream().filter(p -> p.getBin().equals(bin)).toList();
    }

    public List<OverflowPrediction> getLatestPredictionsForZone(Long zoneId) {
        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
        return predictionRepository.findLatestPredictionsForZone(zone);
    }
}
