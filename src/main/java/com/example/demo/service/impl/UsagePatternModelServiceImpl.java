package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.repository.UsagePatternModelRepository;
import com.example.demo.service.UsagePatternModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsagePatternModelServiceImpl implements UsagePatternModelService {

    @Autowired
    private UsagePatternModelRepository modelRepository;

    @Override
    public List<UsagePatternModel> getAllModels() {
        return modelRepository.findAll();
    }

    @Override
    public UsagePatternModel getModelById(Long id) {
        return modelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Model not found with ID: " + id));
    }

    @Override
    public UsagePatternModel createModel(UsagePatternModel model) {
        if (model.getName() == null) {
            throw new BadRequestException("Model name cannot be null");
        }
        return modelRepository.save(model);
    }

    @Override
    public UsagePatternModel updateModel(Long id, UsagePatternModel modelDetails) {
        UsagePatternModel model = getModelById(id);
        model.setName(modelDetails.getName());
        return modelRepository.save(model);
    }

    @Override
    public void deleteModel(Long id) {
        UsagePatternModel model = getModelById(id);
        modelRepository.delete(model);
    }
}
