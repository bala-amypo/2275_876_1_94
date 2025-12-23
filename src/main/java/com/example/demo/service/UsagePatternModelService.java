package com.example.demo.service;

import com.example.demo.model.UsagePatternModel;
import java.util.List;

public interface UsagePatternModelService {

    UsagePatternModel createModel(UsagePatternModel model);

    UsagePatternModel updateModel(Long id, UsagePatternModel model);

    UsagePatternModel getModelById(Long id);

    List<UsagePatternModel> getAllModels();
}
