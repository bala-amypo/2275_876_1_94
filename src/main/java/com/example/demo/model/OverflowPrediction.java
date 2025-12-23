package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class OverflowPrediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer daysUntilFull;
    private LocalDate predictedFullDate;

    @ManyToOne
    private Bin bin;

    @ManyToOne
    private UsagePatternModel modelUsed;

    public Long getId() { return id; }
    public Integer getDaysUntilFull() { return daysUntilFull; }
    public void setDaysUntilFull(Integer d) { this.daysUntilFull = d; }
    public LocalDate getPredictedFullDate() { return predictedFullDate; }
    public void setPredictedFullDate(LocalDate d) { this.predictedFullDate = d; }
    public Bin getBin() { return bin; }
    public void setBin(Bin bin) { this.bin = bin; }
    public UsagePatternModel getModelUsed() { return modelUsed; }
    public void setModelUsed(UsagePatternModel modelUsed) { this.modelUsed = modelUsed; }
}
