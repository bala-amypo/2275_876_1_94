package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

        FillLevelRecord latestRecord = recordRepository.findTop1ByBinOrderByRecordedAtDesc(bin)
                .orElseThrow(() -> new BadRequestException("No fill records found for bin " + binId));

        UsagePatternModel model = modelRepository.findTop1ByBinOrderByLastUpdatedDesc(bin)
                .orElseThrow(() -> new BadRequestException("No usage model found for bin " + binId));

        double remainingCapacity = 100 - latestRecord.getFillPercentage();
        double dailyIncrease = LocalDate.now().getDayOfWeek().getValue() >= 6 ? model.getAvgDailyIncreaseWeekend() : model.getAvgDailyIncreaseWeekday();

        if (dailyIncrease <= 0) {
            throw new BadRequestException("Daily increase must be positive to generate prediction");
        }

        int daysUntilFull = (int) Math.ceil(remainingCapacity / dailyIncrease);
        LocalDate predictedDate = LocalDate.now().plusDays(daysUntilFull);

        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBin(bin);
        prediction.setDaysUntilFull(daysUntilFull);
        prediction.setPredictedFullDate(java.sql.Date.valueOf(predictedDate));
        prediction.setModelUsed(model);
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
        return predictionRepository.findByBinIdOrderByGeneratedAtDesc(binId);
    }

    @Override
    public List<OverflowPrediction> getLatestPredictionsForZone(Long zoneId) {
        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found with id " + zoneId));

        return predictionRepository.findLatestPredictionsForZone(zone);
    }
}
