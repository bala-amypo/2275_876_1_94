package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.Bin;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.UsagePatternModelRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsagePatternModelServiceImpl {

    private final UsagePatternModelRepository modelRepository;
    private final BinRepository binRepository;

    public UsagePatternModelServiceImpl(UsagePatternModelRepository modelRepository, BinRepository binRepository) {
        this.modelRepository = modelRepository;
        this.binRepository = binRepository;
    }

    public UsagePatternModel createModel(UsagePatternModel model) {
        Bin bin = model.getBin();
        if (bin == null || bin.getId() == null) {
            throw new BadRequestException("Bin required");
        }

        if (model.getAvgDailyIncreaseWeekday() < 0 || model.getAvgDailyIncreaseWeekend() < 0) {
            throw new BadRequestException("Increase must be positive");
        }

        Bin fetchedBin = binRepository.findById(bin.getId())
                .orElseThrow(() -> new BadRequestException("Bin not found"));

        model.setBin(fetchedBin);
        model.setLastUpdated(LocalDateTime.now());
        return modelRepository.save(model);
    }

    public UsagePatternModel updateModel(Long id, UsagePatternModel update) {
        UsagePatternModel existing = modelRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Model not found"));
        if (update.getAvgDailyIncreaseWeekday() != null) existing.setAvgDailyIncreaseWeekday(update.getAvgDailyIncreaseWeekday());
        if (update.getAvgDailyIncreaseWeekend() != null) existing.setAvgDailyIncreaseWeekend(update.getAvgDailyIncreaseWeekend());
        existing.setLastUpdated(LocalDateTime.now());
        return modelRepository.save(existing);
    }

    public UsagePatternModel getModelForBin(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new BadRequestException("Bin not found"));
        return modelRepository.findTop1ByBinOrderByLastUpdatedDesc(bin)
                .orElse(null);
    }
}
