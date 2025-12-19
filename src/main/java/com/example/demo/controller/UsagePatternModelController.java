package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.service.UsagePatternModelService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/models")
public class UsagePatternModelController {

    private final UsagePatternModelService modelService;

    public UsagePatternModelController(UsagePatternModelService modelService) {
        this.modelService = modelService;
    }

    @PostMapping
    public ApiResponse createModel(@RequestBody UsagePatternModel model) {
        return new ApiResponse(
                true,
                "Model created",
                modelService.createModel(model)
        );
    }

    @PutMapping("/{id}")
    public ApiResponse updateModel(@PathVariable Long id,@RequestBody UsagePatternModel model) {
        return new ApiResponse(
                true,
                "Model updated",
                modelService.updateModel(id, model)
        );
    }

    @GetMapping("/bin/{binId}")
    public ApiResponse getModelForBin(@PathVariable Long binId) {
        return new ApiResponse(
                true,
                "Model fetched",
                modelService.getModelForBin(binId)
        );
    }

    @GetMapping
    public ApiResponse getAllModels() {
        return new ApiResponse(
                true,
                "Models fetched",
                modelService.getAllModels()
        );
    }
}