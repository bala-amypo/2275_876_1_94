package com.yourpackage.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "overflow_predictions")
public class OverflowPrediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bin_id")
    private Bin bin;

    @Column(nullable = false)
    private LocalDate predictedFullDate;

    @Column(nullable = false)
    private Integer daysUntilFull;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private UsagePatternModel modelUsed;

    private Instant generatedAt;

    // Default Constructor
    public OverflowPrediction() {}

    // Parameterized Constructor
    public OverflowPrediction(Bin bin, LocalDate predictedFullDate,
                              Integer daysUntilFull,
                              UsagePatternModel modelUsed) {
        this.bin = bin;
        this.predictedFullDate = predictedFullDate;
        this.daysUntilFull = daysUntilFull;
        this.modelUsed = modelUsed;
    }

    @PrePersist
    private void validate() {
        if (daysUntilFull < 0)
            throw new IllegalArgumentException("daysUntilFull must be >= 0");
        if (predictedFullDate.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Predicted date must be today or future");
        generatedAt = Instant.now();
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Bin getBin() { return bin; }
    public void setBin(Bin bin) { this.bin = bin; }

    public LocalDate getPredictedFullDate() {
        return predictedFullDate;
    }
    public void setPredictedFullDate(LocalDate predictedFullDate) {
        this.predictedFullDate = predictedFullDate;
    }

    public Integer getDaysUntilFull() { return daysUntilFull; }
    public void setDaysUntilFull(Integer daysUntilFull) {
        this.daysUntilFull = daysUntilFull;
    }

    public UsagePatternModel getModelUsed() { return modelUsed; }
    public void setModelUsed(UsagePatternModel modelUsed) {
        this.modelUsed = modelUsed;
    }

    public Instant getGeneratedAt() { return generatedAt; }
}
