package com.example.demo.service.impl;

import com.example.demo.exception.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.OverflowPredictionService;

import java.sql.Timestamp;
import java.util.*;

public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    private final BinRepository binRepository;
    private final FillLevelRecordRepository recordRepository;
    private final UsagePatternModelRepository modelRepository;
    private final OverflowPredictionRepository predictionRepository;
    private final ZoneRepository zoneRepository;

    public OverflowPredictionServiceImpl(BinRepository binRepository,
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

    @Override
    public OverflowPrediction generatePrediction(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));

        FillLevelRecord latestRecord = recordRepository
                .findTop1ByBinOrderByRecordedAtDesc(bin)
                .orElseThrow(() -> new ResourceNotFoundException("No fill record"));

        UsagePatternModel model = modelRepository
                .findTop1ByBinOrderByLastUpdatedDesc(bin)
                .orElseThrow(() -> new ResourceNotFoundException("Model not found"));

        double remaining = 100 - latestRecord.getFillPercentage();
        double dailyIncrease = latestRecord.getIsWeekend()
                ? model.getAvgDailyIncreaseWeekend()
                : model.getAvgDailyIncreaseWeekday();

        int daysUntilFull = dailyIncrease == 0 ? 0 : (int) Math.ceil(remaining / dailyIncrease);

        if (daysUntilFull < 0) {
            throw new BadRequestException("daysUntilFull invalid");
        }

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, daysUntilFull);

        OverflowPrediction prediction = new OverflowPrediction(
                bin,
                cal.getTime(),
                daysUntilFull,
                model,
                new Timestamp(System.currentTimeMillis())
        );

        return predictionRepository.save(prediction);
    }

    @Override
    public OverflowPrediction getPredictionById(Long id) {
        return predictionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prediction not found"));
    }

    @Override
    public List<OverflowPrediction> getPredictionsForBin(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        return predictionRepository.findAll()
                .stream()
                .filter(p -> p.getBin().getId().equals(bin.getId()))
                .toList();
    }

    @Override
    public List<OverflowPrediction> getLatestPredictionsForZone(Long zoneId) {
        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
        return predictionRepository.findLatestPredictionsForZone(zone);
    }
}
