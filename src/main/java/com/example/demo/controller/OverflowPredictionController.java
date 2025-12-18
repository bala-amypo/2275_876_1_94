package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/predictions")
public class OverflowPredictionController {

    private final OverflowPredictionService predictionService;

    public OverflowPredictionController(
            OverflowPredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @PostMapping("/generate/{binId}")
    public ApiResponse generatePrediction(
            @PathVariable Long binId) {
        return new ApiResponse(
                true,
                "Prediction generated",
                predictionService.generatePrediction(binId)
        );
    }

    @GetMapping("/{id}")
    public ApiResponse getPrediction(
            @PathVariable Long id) {
        return new ApiResponse(
                true,
                "Prediction fetched",
                predictionService.getPredictionById(id)
        );
    }

    @GetMapping("/bin/{binId}")
    public ApiResponse getPredictionsForBin(
            @PathVariable Long binId) {
        return new ApiResponse(
                true,
                "Predictions fetched",
                predictionService.getPredictionsForBin(binId)
        );
    }

    @GetMapping("/zone/{zoneId}/latest")
    public ApiResponse getLatestPredictionsForZone(
            @PathVariable Long zoneId) {
        return new ApiResponse(
                true,
                "Zone predictions fetched",
                predictionService.getLatestPredictionsForZone(zoneId)
        );
    }
}
