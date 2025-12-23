package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.UsagePatternModelRepository;
import org.springframework.stereotype.Service;

@Service
public class UsagePatternModelServiceImpl {

    private final UsagePatternModelRepository modelRepository;
    private final BinRepository binRepository;

    public UsagePatternModelServiceImpl(UsagePatternModelRepository modelRepository, BinRepository binRepository) {
        this.modelRepository = modelRepository;
        this.binRepository = binRepository;
    }

    public UsagePatternModel createModel(UsagePatternModel model) {
        Bin bin = binRepository.findById(model.getBin().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));

        if (model.getAvgDailyIncreaseWeekday() < 0 || model.getAvgDailyIncreaseWeekend() < 0)
            throw new BadRequestException("Average daily increase cannot be negative");

        model.setBin(bin);
        return modelRepository.save(model);
    }

    public UsagePatternModel updateModel(Long id, UsagePatternModel update) {
        UsagePatternModel existing = modelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usage model not found"));

        if (update.getAvgDailyIncreaseWeekday() != null) existing.setAvgDailyIncreaseWeekday(update.getAvgDailyIncreaseWeekday());
        if (update.getAvgDailyIncreaseWeekend() != null) existing.setAvgDailyIncreaseWeekend(update.getAvgDailyIncreaseWeekend());

        return modelRepository.save(existing);
    }

    public UsagePatternModel getModelForBin(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        return modelRepository.findTop1ByBinOrderByLastUpdatedDesc(bin)
                .orElse(null);
    }
}
