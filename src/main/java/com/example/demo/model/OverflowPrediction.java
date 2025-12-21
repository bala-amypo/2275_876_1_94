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
    private Bin bin;

    private Date predictedFullDate;
    private Integer daysUntilFull;

    @ManyToOne
    private UsagePatternModel modelUsed;

    private Timestamp generatedAt;

    public OverflowPrediction() {}

    public OverflowPrediction(Bin bin, Date predictedFullDate,
                              Integer daysUntilFull,
                              UsagePatternModel modelUsed,
                              Timestamp generatedAt) {
        this.bin = bin;
        this.predictedFullDate = predictedFullDate;
        this.daysUntilFull = daysUntilFull;
        this.modelUsed = modelUsed;
        this.generatedAt = generatedAt;
    }

    public Long getId() {
        return id;
    }

    public Bin getBin() {
        return bin;
    }

    public Date getPredictedFullDate() {
        return predictedFullDate;
    }

    public Integer getDaysUntilFull() {
        return daysUntilFull;
    }

    public UsagePatternModel getModelUsed() {
        return modelUsed;
    }

    public Timestamp getGeneratedAt() {
        return generatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBin(Bin bin) {
        this.bin = bin;
    }

    public void setPredictedFullDate(Date predictedFullDate) {
        this.predictedFullDate = predictedFullDate;
    }

    public void setDaysUntilFull(Integer daysUntilFull) {
        this.daysUntilFull = daysUntilFull;
    }

    public void setModelUsed(UsagePatternModel modelUsed) {
        this.modelUsed = modelUsed;
    }

    public void setGeneratedAt(Timestamp generatedAt) {
        this.generatedAt = generatedAt;
    }
}
