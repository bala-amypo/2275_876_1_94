package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.OverflowPrediction;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.model.Zone;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.FillLevelRecordRepository;
import com.example.demo.repository.OverflowPredictionRepository;
import com.example.demo.repository.UsagePatternModelRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
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
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found with id: " + binId));

        UsagePatternModel model = modelRepository.findTop1ByBinOrderByLastUpdatedDesc(bin)
                .orElseThrow(() -> new ResourceNotFoundException("Usage model not found for bin id: " + binId));

        double currentFill = recordRepository.findTop1ByBinOrderByRecordedAtDesc(bin)
                .map(r -> r.getFillPercentage())
                .orElse(0.0);

        if (currentFill >= 100) {
            throw new BadRequestException("Bin is already full");
        }

        // Simplified calculation: assume today is the start
        LocalDate today = LocalDate.now();
        double avgIncrease = (today.getDayOfWeek().getValue() >= 6) ? model.getAvgDailyIncreaseWeekend() : model.getAvgDailyIncreaseWeekday();
        if (avgIncrease <= 0) avgIncrease = 1; // prevent divide by zero
        int daysUntilFull = (int) Math.ceil((100 - currentFill) / avgIncrease);
        LocalDate predictedDate = today.plusDays(daysUntilFull);

        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBin(bin);
        prediction.setModelUsed(model);
        prediction.setDaysUntilFull(daysUntilFull);
        prediction.setPredictedFullDate(java.sql.Date.valueOf(predictedDate));
        prediction.setGeneratedAt(new Timestamp(System.currentTimeMillis()));

        return predictionRepository.save(prediction);
    }

    @Override
    public OverflowPrediction getPredictionById(Long id) {
        return predictionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prediction not found with id: " + id));
    }

    @Override
    public List<OverflowPrediction> getPredictionsForBin(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found with id: " + binId));
        return predictionRepository.findAll().stream()
                .filter(p -> p.getBin().getId().equals(binId))
                .toList();
    }

    @Override
    public List<OverflowPrediction> getLatestPredictionsForZone(Long zoneId) {
        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found with id: " + zoneId));
        return predictionRepository.findLatestPredictionsForZone(zone);
    }
}
