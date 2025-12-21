package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "overflow_predictions")
public class OverflowPrediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bin_id", nullable = false)
    private Bin bin;

    @Column(name = "predicted_full_date", nullable = false)
    private Date predictedFullDate;

    @Column(name = "days_until_full", nullable = false)
    private Integer daysUntilFull;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private UsagePatternModel modelUsed;

    @Column(name = "generated_at", nullable = false)
    private Timestamp generatedAt;

    public OverflowPrediction() {}

    public OverflowPrediction(Bin bin, Date predictedFullDate, Integer daysUntilFull,
                              UsagePatternModel modelUsed, Timestamp generatedAt) {
        this.bin = bin;
        this.predictedFullDate = predictedFullDate;
        this.daysUntilFull = daysUntilFull;
        this.modelUsed = modelUsed;
        this.generatedAt = generatedAt;
    }

    // Getters and Setters
    // ...
}
