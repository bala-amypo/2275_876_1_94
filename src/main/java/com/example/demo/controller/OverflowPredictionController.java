package com.example.demo.controller;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/predictions")
public class OverflowPredictionController {

    private final OverflowPredictionService service;

    public OverflowPredictionController(OverflowPredictionService service) {
        this.service = service;
    }

    @PostMapping
    public OverflowPrediction create(@RequestBody OverflowPrediction prediction) {
        return service.createPrediction(prediction);
    }

    @GetMapping("/{id}")
    public OverflowPrediction get(@PathVariable Long id) {
        return service.getPredictionById(id);
    }

    @GetMapping("/bin/{binId}")
    public List<OverflowPrediction> getForBin(@PathVariable Long binId) {
        return service.getPredictionsForBin(binId);
    }
}
