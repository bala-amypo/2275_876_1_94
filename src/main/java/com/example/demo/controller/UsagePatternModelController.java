package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.service.UsagePatternModelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/models")
public class UsagePatternModelController {

    private final UsagePatternModelService modelService;

    public UsagePatternModelController(UsagePatternModelService modelService) {
        this.modelService = modelService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createModel(@RequestBody UsagePatternModel model) {
        UsagePatternModel created = modelService.createModel(model);
        return ResponseEntity.ok(new ApiResponse(true, "Model created", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateModel(@PathVariable Long id, @RequestBody UsagePatternModel model) {
        UsagePatternModel updated = modelService.updateModel(id, model);
        return ResponseEntity.ok(new ApiResponse(true, "Model updated", updated));
    }

    @GetMapping("/bin/{binId}")
    public ResponseEntity<ApiResponse> getModelForBin(@PathVariable Long binId) {
        UsagePatternModel model = modelService.getModelForBin(binId);
        return ResponseEntity.ok(new ApiResponse(true, "Model fetched", model));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllModels() {
        List<UsagePatternModel> models = modelService.getAllModels();
        return ResponseEntity.ok(new ApiResponse(true, "All models fetched", models));
    }
}
