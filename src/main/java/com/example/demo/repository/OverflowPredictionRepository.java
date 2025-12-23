package com.example.demo.repository;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.model.Zone;

import java.util.*;

public interface OverflowPredictionRepository {
    OverflowPrediction save(OverflowPrediction prediction);
    List<OverflowPrediction> findLatestPredictionsForZone(Zone zone);
}
