package com.example.demo.service;

import com.example.demo.model.*;
import java.util.List;
public interface OverflowPredictionService {
    List<OverflowPrediction> getLatestPredictionsForZone(Long zoneId);
}