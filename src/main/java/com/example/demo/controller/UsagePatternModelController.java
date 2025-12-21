package com.example.demo.controller;

import com.example.demo.model.UsagePatternModel;
import com.example.demo.service.UsagePatternModelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
@Tag(name = "Usage Pattern Models")
public class UsagePatternModelController {

    private final UsagePatternModelService modelService;

    public UsagePatternModelController(UsagePatternModelService modelService) {
        this.modelService = modelService;
    }

    @PostMapping
    public UsagePatternModel createModel(@RequestBody UsagePatternModel model) {
        return modelService.createModel(model);
    }

    @PutMapping("/{id}")
    public UsagePatternModel updateModel(
            @PathVariable Long id,
            @RequestBody UsagePatternModel model) {

        return modelService.updateModel(id, model);
    }

    @GetMapping("/bin/{binId}")
    public UsagePatternModel getModelForBin(@PathVariable Long binId) {
        return modelService.getModelForBin(binId);
    }

    @GetMapping
    public List<UsagePatternModel> getAllModels() {
        return modelService.getAllModels();
    }
}
