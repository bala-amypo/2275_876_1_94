package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.repository.UsagePatternModelRepository;
import com.example.demo.service.UsagePatternModelService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsagePatternModelServiceImpl implements UsagePatternModelService {

    private final UsagePatternModelRepository repository;

    public UsagePatternModelServiceImpl(UsagePatternModelRepository repository) {
        this.repository = repository;
    }

    @Override
    public UsagePatternModel createModel(UsagePatternModel model) {
        model.setLastUpdated(LocalDateTime.now());
        return repository.save(model);
    }

    @Override
    public UsagePatternModel updateModel(Long id, UsagePatternModel updated) {
        UsagePatternModel existing = getModelById(id);

        existing.setAvgDailyIncreaseWeekday(updated.getAvgDailyIncreaseWeekday());
        existing.setAvgDailyIncreaseWeekend(updated.getAvgDailyIncreaseWeekend());
        existing.setLastUpdated(LocalDateTime.now());

        return repository.save(existing);
    }

    @Override
    public UsagePatternModel getModelById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usage model not found"));
    }

    @Override
    public List<UsagePatternModel> getAllModels() {
        return repository.findAll();
    }
}
