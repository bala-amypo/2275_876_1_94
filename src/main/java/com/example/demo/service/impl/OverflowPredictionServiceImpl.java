package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.FillLevelRecord;
import com.example.demo.model.OverflowPrediction;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.repository.*;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    private final BinRepository binRepository;
    private final FillLevelRecordRepository recordRepository;
    private final UsagePatternModelRepository modelRepository;
    private final OverflowPredictionRepository predictionRepository;

    public OverflowPredictionServiceImpl(
            BinRepository binRepository,
            FillLevelRecordRepository recordRepository,
            UsagePatternModelRepository modelRepository,
            OverflowPredictionRepository predictionRepository) {
        this.binRepository = binRepository;
        this.recordRepository = recordRepository;
        this.modelRepository = modelRepository;
        this.predictionRepository = predictionRepository;
    }

    @Override
    public OverflowPrediction generatePrediction(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));

        FillLevelRecord latestRecord =
                recordRepository.findTop1ByBinOrderByRecordedAtDesc(bin)
                        .orElseThrow(() -> new ResourceNotFoundException("No fill record"));

        UsagePatternModel model =
                modelRepository.findTop1ByBinOrderByLastUpdatedDesc(bin)
                        .orElseThrow(() -> new ResourceNotFoundException("No usage model"));

        double remaining = 100 - latestRecord.getFillPercentage();
        double dailyIncrease = model.getAvgDailyIncreaseWeekday();

        int days = dailyIncrease <= 0 ? 0 : (int) Math.ceil(remaining / dailyIncrease);

        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBin(bin);
        prediction.setModelUsed(model);
        prediction.setDaysUntilFull(days);
        prediction.setPredictedFullDate(LocalDate.now().plusDays(days));

        return predictionRepository.save(prediction);
    }
}
