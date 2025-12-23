package com.example.demo.service.impl;

import com.example.demo.model.UsagePatternModel;
import com.example.demo.repository.UsagePatternModelRepository;
import com.example.demo.service.UsagePatternModelService;
import org.springframework.stereotype.Service;

@Service
public class UsagePatternModelServiceImpl implements UsagePatternModelService {

    private final UsagePatternModelRepository repository;

    public UsagePatternModelServiceImpl(UsagePatternModelRepository repository) {
        this.repository = repository;
    }

    // ❌ Removed @Override
    public UsagePatternModel createModel(UsagePatternModel model) {
        return repository.save(model);
    }

    // ❌ Removed @Override
    public UsagePatternModel updateModel(Long id, UsagePatternModel model) {
        return model;
    }

    // ❌ Removed @Override
    public UsagePatternModel getModelByBinId(Long binId) {
        return null;
    }
}
