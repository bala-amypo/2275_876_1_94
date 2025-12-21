package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.UsagePatternModelRepository;
import com.example.demo.service.UsagePatternModelService;

import java.sql.Timestamp;
import java.util.List;

public class UsagePatternModelServiceImpl implements UsagePatternModelService {

    private final UsagePatternModelRepository modelRepository;
    private final BinRepository binRepository;

    public UsagePatternModelServiceImpl(UsagePatternModelRepository modelRepository, BinRepository binRepository) {
        this.modelRepository = modelRepository;
        this.binRepository = binRepository;
    }

    @Override
    public UsagePatternModel createModel(UsagePatternModel model) {
        if (model.getAvgDailyIncreaseWeekday() < 0 || model.getAvgDailyIncreaseWeekend() < 0) {
            throw new BadRequestException("Daily increase must be >= 0");
        }

        Bin bin = binRepository.findById(model.getBin().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));

        model.setBin(bin);
        model.setLastUpdated(new Timestamp(System.currentTimeMillis()));
        return modelRepository.save(model);
    }

    @Override
    public UsagePatternModel updateModel(Long id, UsagePatternModel updatedModel) {
        UsagePatternModel existing = modelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UsagePatternModel not found"));

        if (updatedModel.getAvgDailyIncreaseWeekday() != null && updatedModel.getAvgDailyIncreaseWeekday() < 0)
            throw new BadRequestException("Weekday increase must be >= 0");

        if (updatedModel.getAvgDailyIncreaseWeekend() != null && updatedModel.getAvgDailyIncreaseWeekend() < 0)
            throw new BadRequestException("Weekend increase must be >= 0");

        if (updatedModel.getAvgDailyIncreaseWeekday() != null)
            existing.setAvgDailyIncreaseWeekday(updatedModel.getAvgDailyIncreaseWeekday());
        if (updatedModel.getAvgDailyIncreaseWeekend() != null)
            existing.setAvgDailyIncreaseWeekend(updatedModel.getAvgDailyIncreaseWeekend());

        existing.setLastUpdated(new Timestamp(System.currentTimeMillis()));
        return modelRepository.save(existing);
    }

    @Override
    public UsagePatternModel getModelForBin(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));

        return modelRepository.findTop1ByBinOrderByLastUpdatedDesc(bin)
                .orElseThrow(() -> new ResourceNotFoundException("No model found for bin"));
    }

    @Override
    public List<UsagePatternModel> getAllModels() {
        return modelRepository.findAll();
    }
}
