package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.repository.UsagePatternModelRepository;
import com.example.demo.service.UsagePatternModelService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UsagePatternModelServiceImpl implements UsagePatternModelService {

    @Autowired
    private UsagePatternModelRepository modelRepository;

    @Override
    public UsagePatternModel getModelForBin(Long binId) {
        return modelRepository.findByBinId(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Model not found for Bin ID: " + binId));
    }
}
