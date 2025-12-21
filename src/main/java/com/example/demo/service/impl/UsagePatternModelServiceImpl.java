package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.UsagePatternModelRepository;
import com.example.demo.service.UsagePatternModelService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UsagePatternModelServiceImpl implements UsagePatternModelService {

    private final UsagePatternModelRepository modelRepository;
    private final BinRepository binRepository;

    public UsagePatternModelServiceImpl(UsagePatternModelRepository modelRepository, BinRepository binRepository) {
        this.modelRepository = modelRepository;
        this.binRepository = binRepository;
    }

    @Override
    public UsagePatternModel createModel(UsagePatternModel model) {
        Bin bin = binRepository.findById(model.getBin().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found with id " + model.getBin().getId()));

        if (model.getAvgDailyIncreaseWeekday() < 0 || model.getAvgDailyIncreaseWeekend() < 0) {
            throw new BadRequestException("Daily increase values must be non-negative");
        }

        model.setBin(bin);
        model.setLastUpdated(new Timestamp(System.currentTimeMillis()));

        return modelRepository.save(model);
    }

    @Override
    public UsagePatternModel updateModel(Long id, UsagePatternModel model) {
        UsagePatternModel existing = modelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usage model not found with id " + id));

        if (model.getAvgDailyIncreaseWeekday() != null && model.getAvgDailyIncreaseWeekday() < 0
            || model.getAvgDailyIncreaseWeekend() != null && model.getAvgDailyIncreaseWeekend() < 0) {
            throw new BadRequestException("Daily increase values must be non-negative");
        }

        existing.setAvgDailyIncreaseWeekday(model.getAvgDailyIncreaseWeekday() != null ?
                model.getAvgDailyIncreaseWeekday() : existing.getAvgDailyIncreaseWeekday());

        existing.setAvgDailyIncreaseWeekend(model.getAvgDailyIncreaseWeekend() != null ?
                model.getAvgDailyIncreaseWeekend() : existing.getAvgDailyIncreaseWeekend());

        existing.setLastUpdated(new Timestamp(System.currentTimeMillis()));

        return modelRepository.save(existing);
    }

    @Override
    public UsagePatternModel getModelForBin(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found with id " + binId));
        return modelRepository.findTop1ByBinOrderByLastUpdatedDesc(bin)
                .orElseThrow(() -> new ResourceNotFoundException("No usage model found for bin with id " + binId));
    }

    @Override
    public List<UsagePatternModel> getAllModels() {
        return modelRepository.findAll();
    }
}
