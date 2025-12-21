package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.model.OverflowPrediction;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/predictions")
public class OverflowPredictionController {

    private final OverflowPredictionService predictionService;

    public OverflowPredictionController(OverflowPredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @PostMapping("/generate/{binId}")
    public ResponseEntity<ApiResponse> generatePrediction(@PathVariable Long binId) {
        OverflowPrediction prediction = predictionService.generatePrediction(binId);
        return ResponseEntity.ok(new ApiResponse(true, "Prediction generated", prediction));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getPredictionById(@PathVariable Long id) {
        OverflowPrediction prediction = predictionService.getPredictionById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Prediction fetched", prediction));
    }

    @GetMapping("/bin/{binId}")
    public ResponseEntity<ApiResponse> getPredictionsForBin(@PathVariable Long binId) {
        List<OverflowPrediction> predictions = predictionService.getPredictionsForBin(binId);
        return ResponseEntity.ok(new ApiResponse(true, "Predictions for bin fetched", predictions));
    }

    @GetMapping("/zone/{zoneId}/latest")
    public ResponseEntity<ApiResponse> getLatestPredictionsForZone(@PathVariable Long zoneId) {
        List<OverflowPrediction> predictions = predictionService.getLatestPredictionsForZone(zoneId);
        return ResponseEntity.ok(new ApiResponse(true, "Latest predictions for zone fetched", predictions));
    }
}
