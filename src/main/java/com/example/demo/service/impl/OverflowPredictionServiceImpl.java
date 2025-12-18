package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.OverflowPredictionService;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class OverflowPredictionServiceImpl
        implements OverflowPredictionService {

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

    @Override
    public OverflowPrediction generatePrediction(Long binId) {

        Bin bin = binRepository.findById(binId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Bin not found"));

        FillLevelRecord latestRecord =
                recordRepository.findTop1ByBinOrderByRecordedAtDesc(bin)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("No records"));

        UsagePatternModel model =
                modelRepository
                        .findTop1ByBinOrderByLastUpdatedDesc(bin)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Model not found"));

        double remaining =
                100 - latestRecord.getFillPercentage();

        double dailyIncrease =
                latestRecord.getIsWeekend()
                        ? model.getAvgDailyIncreaseWeekend()
                        : model.getAvgDailyIncreaseWeekday();

        if (dailyIncrease <= 0) {
            throw new BadRequestException("Invalid daily increase");
        }

        int daysUntilFull =
                (int) Math.ceil(remaining / dailyIncrease);

        LocalDate predictedDate =
                LocalDate.now().plusDays(daysUntilFull);

        OverflowPrediction prediction =
                new OverflowPrediction(
                        bin,
                        Date.from(predictedDate
                                .atStartOfDay()
                                .atZone(
                                        java.time.ZoneId.systemDefault())
                                .toInstant()),
                        daysUntilFull,
                        model,
                        Timestamp.from(Instant.now())
                );

        return predictionRepository.save(prediction);
    }

    @Override
    public OverflowPrediction getPredictionById(Long id) {
        return predictionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Prediction not found"));
    }

    @Override
    public List<OverflowPrediction> getPredictionsForBin(Long binId) {

        Bin bin = binRepository.findById(binId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Bin not found"));

        return predictionRepository.findAll()
                .stream()
                .filter(p -> p.getBin().getId().equals(bin.getId()))
                .toList();
    }

    @Override
    public List<OverflowPrediction> getLatestPredictionsForZone(Long zoneId) {

        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Zone not found"));

        return predictionRepository.findLatestPredictionsForZone(zone);
    }
}
