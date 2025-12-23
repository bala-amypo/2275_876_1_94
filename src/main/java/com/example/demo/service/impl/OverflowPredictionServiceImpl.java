package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.*;
import com.example.demo.repository.*;

import java.time.LocalDate;
import java.util.List;

public class OverflowPredictionServiceImpl {
    private final BinRepository binRepository;
    private final UsagePatternModelRepository modelRepository;
    private final OverflowPredictionRepository predictionRepository;
    private final ZoneRepository zoneRepository;

    public OverflowPredictionServiceImpl(BinRepository binRepository,
                                         FillLevelRecordRepository recordRepository,
                                         UsagePatternModelRepository modelRepository,
                                         OverflowPredictionRepository predictionRepository,
                                         ZoneRepository zoneRepository) {
        this.binRepository = binRepository;
        this.modelRepository = modelRepository;
        this.predictionRepository = predictionRepository;
        this.zoneRepository = zoneRepository;
    }

    public OverflowPrediction generatePrediction(Long binId) {
        Bin bin = binRepository.findById(binId).orElseThrow(() -> new BadRequestException("Bin not found"));
        UsagePatternModel model = modelRepository.findTop1ByBinOrderByLastUpdatedDesc(bin).orElse(null);
        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBin(bin);
        prediction.setModelUsed(model);
        prediction.setDaysUntilFull(3);
        prediction.setPredictedFullDate(LocalDate.now().plusDays(3));
        return predictionRepository.save(prediction);
    }

    public List<OverflowPrediction> getLatestPredictionsForZone(Long zoneId) {
        Zone zone = zoneRepository.findById(zoneId).orElseThrow(() -> new BadRequestException("Zone not found"));
        return predictionRepository.findLatestPredictionsForZone(zone);
    }
}
