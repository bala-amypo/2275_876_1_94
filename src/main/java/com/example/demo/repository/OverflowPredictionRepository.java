package com.example.demo.repository;

import com.example.demo.model.*;
import org.springframework.data.jpa.repository.*;
import java.util.*;

public interface OverflowPredictionRepository extends JpaRepository<OverflowPrediction, Long> {}
