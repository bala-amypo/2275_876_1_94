package com.example.demo.repository;

import com.example.demo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.*;

public interface OverflowPredictionRepository extends JpaRepository<OverflowPrediction, Long> {
    List<OverflowPrediction> findLatestPredictionsForZone(Zone zone);
}