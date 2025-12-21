package com.example.demo.service.impl;

import com.example.demo.model.UsagePatternModel;
import com.example.demo.service.UsagePatternModelService;
import org.springframework.stereotype.Service;

@Service
public class UsagePatternModelServiceImpl implements UsagePatternModelService {

    @Override
    public UsagePatternModel createModel(UsagePatternModel model) {
        return model;
    }

    @Override
    public UsagePatternModel getModelForBin(Long binId) {
        return new UsagePatternModel();
    }
}
