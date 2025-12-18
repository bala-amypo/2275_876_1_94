package com.yourpackage.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
// @Table(name = "usage_pattern_models")
public class UsagePatternModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bin_id")
    private Bin bin;

    @Column(nullable = false)
    private Double avgDailyIncreaseWeekday;

    @Column(nullable = false)
    private Double avgDailyIncreaseWeekend;

    private Instant lastUpdated;

    // Default Constructor
    public UsagePatternModel() {}

    // Parameterized Constructor
    public UsagePatternModel(Bin bin, Double weekday, Double weekend) {
        this.bin = bin;
        this.avgDailyIncreaseWeekday = weekday;
        this.avgDailyIncreaseWeekend = weekend;
    }

    @PrePersist
    @PreUpdate
    private void validate() {
        if (avgDailyIncreaseWeekday < 0 || avgDailyIncreaseWeekend < 0)
            throw new IllegalArgumentException("Daily increase must be >= 0");
        lastUpdated = Instant.now();
    }

    // Getters & Setters
    public Long getId() { 
        return id; 
    }
    public void setId(Long id) { 
        this.id = id; 
    }

    public Bin getBin() { 
        return bin; 
    }
    public void setBin(Bin bin) { 
        this.bin = bin; 
    }

    public Double getAvgDailyIncreaseWeekday() {
        return avgDailyIncreaseWeekday;
    }
    public void setAvgDailyIncreaseWeekday(Double value) {
        this.avgDailyIncreaseWeekday = value;
    }

    public Double getAvgDailyIncreaseWeekend() {
        return avgDailyIncreaseWeekend;
    }
    public void setAvgDailyIncreaseWeekend(Double value) {
        this.avgDailyIncreaseWeekend = value;
    }

    public Instant getLastUpdated() { 
        return lastUpdated; 
    }

}