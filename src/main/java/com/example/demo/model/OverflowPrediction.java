package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "overflow_prediction")
public class OverflowPrediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bin_id", nullable = false)
    private Bin bin;

    @Column(name = "predicted_level", nullable = false)
    private Integer predictedLevel;

    @Column(name = "predicted_at", nullable = false)
    private LocalDateTime predictedAt = LocalDateTime.now();

    // ✅ REQUIRED BY REPOSITORY
    @Column(name = "generated_at", nullable = false)
    private LocalDateTime generatedAt = LocalDateTime.now();

    public OverflowPrediction() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Bin getBin() { return bin; }
    public void setBin(Bin bin) { this.bin = bin; }

    public Integer getPredictedLevel() { return predictedLevel; }
    public void setPredictedLevel(Integer predictedLevel) {
        this.predictedLevel = predictedLevel;
    }

    public LocalDateTime getPredictedAt() { return predictedAt; }
    public void setPredictedAt(LocalDateTime predictedAt) {
        this.predictedAt = predictedAt;
    }

    // ✅ REQUIRED BY findByBinOrderByGeneratedAtDesc
    public LocalDateTime getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }
}
