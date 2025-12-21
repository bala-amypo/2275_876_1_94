package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
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
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found with id " + binId));

        if (!bin.getActive()) {
            throw new BadRequestException("Cannot generate prediction for inactive bin");
        }

        FillLevelRecord latestRecord = recordRepository.findTop1ByBinOrderByRecordedAtDesc(bin)
                .orElseThrow(() -> new BadRequestException("No fill record found for bin"));

        UsagePatternModel latestModel = modelRepository.findTop1ByBinOrderByLastUpdatedDesc(bin)
                .orElseThrow(() -> new BadRequestException("No usage model found for bin"));

        double currentFill = latestRecord.getFillPercentage();
        double capacityLiters = bin.getCapacityLiters();
        double dailyIncrease = latestRecord.getIsWeekend() ? latestModel.getAvgDailyIncreaseWeekend()
                : latestModel.getAvgDailyIncreaseWeekday();

        if (dailyIncrease <= 0) {
            throw new BadRequestException("Daily increase must be greater than 0 to predict overflow");
        }

        // Calculate days until full
        double litersFilled = currentFill / 100 * capacityLiters;
        double litersRemaining = capacityLiters - litersFilled;
        int daysUntilFull = (int) Math.ceil(litersRemaining / dailyIncrease);

        LocalDate predictedDate = LocalDate.now().plusDays(daysUntilFull);
        Date predictedFullDate = Date.from(predictedDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBin(bin);
        prediction.setModelUsed(latestModel);
        prediction.setDaysUntilFull(daysUntilFull);
        prediction.setPredictedFullDate(predictedFullDate);
        prediction.setGeneratedAt(new Timestamp(System.currentTimeMillis()));

        return predictionRepository.save(prediction);
    }

    @Override
    public OverflowPrediction getPredictionById(Long id) {
        return predictionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prediction not found with id " + id));
    }

    @Override
    public List<OverflowPrediction> getPredictionsForBin(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found with id " + binId));
        return predictionRepository.findByBin(bin);
    }

    @Override
    public List<OverflowPrediction> getLatestPredictionsForZone(Long zoneId) {
        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found with id " + zoneId));
        return predictionRepository.findLatestPredictionsForZone(zone);
    }
}
