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

    public Long getId() { return id; }
    public Double getAvgDailyIncreaseWeekday() { return avgDailyIncreaseWeekday; }
    public void setAvgDailyIncreaseWeekday(Double v) { this.avgDailyIncreaseWeekday = v; }
    public Double getAvgDailyIncreaseWeekend() { return avgDailyIncreaseWeekend; }
    public void setAvgDailyIncreaseWeekend(Double v) { this.avgDailyIncreaseWeekend = v; }
    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public Bin getBin() { return bin; }
    public void setBin(Bin bin) { this.bin = bin; }
}
