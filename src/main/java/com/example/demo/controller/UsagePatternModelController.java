package com.example.demo.controller;

import com.example.demo.model.UsagePatternModel;
import com.example.demo.service.UsagePatternModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usage-patterns")
public class UsagePatternModelController {

    @Autowired
    private UsagePatternModelService usageService;

    @GetMapping("/{binId}")
    public UsagePatternModel getModelForBin(@PathVariable Long binId) {
        return usageService.getModelForBin(binId);
    }

    @PostMapping
    public UsagePatternModel createModel(@RequestBody UsagePatternModel model) {
        return usageService.createModel(model);
    }
}
