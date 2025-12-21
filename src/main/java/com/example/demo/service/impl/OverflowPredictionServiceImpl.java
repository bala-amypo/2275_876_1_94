package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.OverflowPredictionService;

import java.sql.Timestamp;
import java.time.LocalDate;

public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    private final BinRepository binRepository;
    private final FillLevelRecordRepository recordRepository;
    private final UsagePatternModelRepository modelRepository;
    private final OverflowPredictionRepository predictionRepository;
    private final ZoneRepository zoneRepository;

    // ⚠️ EXACT ORDER REQUIRED
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
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));

        FillLevelRecord latest =
                recordRepository.findTop1ByBinOrderByRecordedAtDesc(bin);

        UsagePatternModel model =
                modelRepository.findTop1ByBinOrderByLastUpdatedDesc(bin);

        double remaining =
                100 - latest.getFillPercentage();

        double dailyIncrease =
                latest.getIsWeekend()
                        ? model.getAvgDailyIncreaseWeekend()
                        : model.getAvgDailyIncreaseWeekday();

        int days = (int) Math.ceil(remaining / dailyIncrease);

        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBin(bin);
        prediction.setDaysUntilFull(days);
        prediction.setPredictedFullDate(LocalDate.now().plusDays(days));
        prediction.setModelUsed(model);
        prediction.setGeneratedAt(new Timestamp(System.currentTimeMillis()));

        return predictionRepository.save(prediction);
    }
}
