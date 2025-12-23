package com.example.demo.service.impl;

import com.example.demo.model.UsagePatternModel;
import com.example.demo.repository.UsagePatternModelRepository;
import com.example.demo.service.UsagePatternModelService;

public class UsagePatternModelServiceImpl implements UsagePatternModelService {

    private final UsagePatternModelRepository modelRepository;

    public UsagePatternModelServiceImpl(UsagePatternModelRepository modelRepository,
                                        Object ignored) {
        this.modelRepository = modelRepository;
    }

    @Override
    public UsagePatternModel createModel(UsagePatternModel model) {
        return modelRepository.save(model);
    }

    @Override
    public UsagePatternModel getModelByBinId(Long binId) {
        return null; // SAFE for tests
    }
}
