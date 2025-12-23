package com.example.demo.service.impl;

import com.example.demo.exception.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;

import java.time.LocalDateTime;

public class UsagePatternModelServiceImpl {

    private final UsagePatternModelRepository modelRepository;
    private final BinRepository binRepository;

    public UsagePatternModelServiceImpl(UsagePatternModelRepository modelRepository,
                                        BinRepository binRepository) {
        this.modelRepository = modelRepository;
        this.binRepository = binRepository;
    }

    public UsagePatternModel createModel(UsagePatternModel model) {
        if (model.getAvgDailyIncreaseWeekday() < 0 || model.getAvgDailyIncreaseWeekend() < 0)
            throw new BadRequestException("Negative increase");

        Bin bin = binRepository.findById(model.getBin().getId())
                .orElseThrow(() -> new BadRequestException("Bin not found"));

        model.setBin(bin);
        model.setLastUpdated(LocalDateTime.now());
        return modelRepository.save(model);
    }

    public UsagePatternModel updateModel(Long id, UsagePatternModel update) {
        UsagePatternModel model = modelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Model not found"));

        if (update.getAvgDailyIncreaseWeekend() != null)
            model.setAvgDailyIncreaseWeekend(update.getAvgDailyIncreaseWeekend());

        model.setLastUpdated(LocalDateTime.now());
        return model;
    }

    public UsagePatternModel getModelForBin(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));

        return modelRepository.findTop1ByBinOrderByLastUpdatedDesc(bin)
                .orElseThrow(() -> new ResourceNotFoundException("Model not found"));
    }
}
