package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class UsagePatternModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double avgDailyIncreaseWeekday;
    private Double avgDailyIncreaseWeekend;
    private LocalDateTime lastUpdated = LocalDateTime.now();

    @ManyToOne
    private Bin bin;

    public UsagePatternModel() {}

    public UsagePatternModel(Bin bin, Double avgDailyIncreaseWeekday, Double avgDailyIncreaseWeekend) {
        this.bin = bin;
        this.avgDailyIncreaseWeekday = avgDailyIncreaseWeekday;
        this.avgDailyIncreaseWeekend = avgDailyIncreaseWeekend;
    }

    public Long getId() { return id; }
    public Double getAvgDailyIncreaseWeekday() { return avgDailyIncreaseWeekday; }
    public void setAvgDailyIncreaseWeekday(Double avgDailyIncreaseWeekday) { this.avgDailyIncreaseWeekday = avgDailyIncreaseWeekday; }
    public Double getAvgDailyIncreaseWeekend() { return avgDailyIncreaseWeekend; }
    public void setAvgDailyIncreaseWeekend(Double avgDailyIncreaseWeekend) { this.avgDailyIncreaseWeekend = avgDailyIncreaseWeekend; }
    public Bin getBin() { return bin; }
    public void setBin(Bin bin) { this.bin = bin; }
    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
}
