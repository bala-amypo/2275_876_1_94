package com.example.demo.controller;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/overflow-predictions")
public class OverflowPredictionController {

    @Autowired
    private OverflowPredictionService predictionService;

    @GetMapping("/zone/{zoneId}")
    public List<OverflowPrediction> getLatestPredictions(@PathVariable Long zoneId) {
        return predictionService.getLatestPredictionsForZone(zoneId);
    }

    @PostMapping
    public OverflowPrediction createPrediction(@RequestBody OverflowPrediction prediction) {
        return predictionService.createPrediction(prediction);
    }
}
