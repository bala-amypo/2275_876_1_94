package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.Bin;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.UsagePatternModelRepository;

import java.time.LocalDateTime;

public class UsagePatternModelServiceImpl {
    private final UsagePatternModelRepository modelRepository;
    private final BinRepository binRepository;

    public UsagePatternModelServiceImpl(UsagePatternModelRepository modelRepository, BinRepository binRepository) {
        this.modelRepository = modelRepository;
        this.binRepository = binRepository;
    }

    public UsagePatternModel createModel(UsagePatternModel model) {
        if (model.getBin() == null || model.getBin().getId() == null) {
            throw new BadRequestException("Bin required");
        }
        if (model.getAvgDailyIncreaseWeekday() != null && model.getAvgDailyIncreaseWeekday() < 0) {
            throw new BadRequestException("Negative weekday increase");
        }
        Bin bin = binRepository.findById(model.getBin().getId()).orElseThrow(() -> new BadRequestException("Bin not found"));
        model.setBin(bin);
        model.setLastUpdated(LocalDateTime.now());
        return modelRepository.save(model);
    }

    public UsagePatternModel updateModel(Long id, UsagePatternModel update) {
        UsagePatternModel existing = modelRepository.findById(id).orElseThrow(() -> new BadRequestException("Model not found"));
        if (update.getAvgDailyIncreaseWeekend() != null) existing.setAvgDailyIncreaseWeekend(update.getAvgDailyIncreaseWeekend());
        if (update.getAvgDailyIncreaseWeekday() != null) existing.setAvgDailyIncreaseWeekday(update.getAvgDailyIncreaseWeekday());
        existing.setLastUpdated(LocalDateTime.now());
        return modelRepository.save(existing);
    }

    public UsagePatternModel getModelForBin(Long binId) {
        Bin bin = binRepository.findById(binId).orElseThrow(() -> new BadRequestException("Bin not found"));
        return modelRepository.findTop1ByBinOrderByLastUpdatedDesc(bin).orElse(null);
    }
}
