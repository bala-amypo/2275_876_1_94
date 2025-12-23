package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OverflowPredictionServiceImpl {

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

    public OverflowPrediction generatePrediction(Long binId) {
        Bin bin = binRepository.findById(binId).orElseThrow();
        FillLevelRecord lastRecord = recordRepository.findTop1ByBinOrderByRecordedAtDesc(bin).orElse(null);
        UsagePatternModel model = modelRepository.findTop1ByBinOrderByLastUpdatedDesc(bin).orElse(null);

        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBin(bin);
        prediction.setModelUsed(model);

        if (lastRecord != null && model != null) {
            double remainingCapacity = bin.getCapacityLiters() * (1 - lastRecord.getFillPercentage() / 100.0);
            double avgIncrease = (model.getAvgDailyIncreaseWeekday() + model.getAvgDailyIncreaseWeekend()) / 2;
            int daysUntilFull = (int) Math.ceil(remainingCapacity / avgIncrease);
            prediction.setDaysUntilFull(daysUntilFull);
            prediction.setPredictedFullDate(LocalDate.now().plusDays(daysUntilFull));
        }

        return predictionRepository.save(prediction);
    }

    public List<OverflowPrediction> getLatestPredictionsForZone(Long zoneId) {
        Zone zone = zoneRepository.findById(zoneId).orElseThrow();
        return predictionRepository.findLatestPredictionsForZone(zone);
    }
}
