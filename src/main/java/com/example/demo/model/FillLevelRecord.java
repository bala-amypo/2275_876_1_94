package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fill_level_records")
public class FillLevelRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bin_id")
    private Bin bin;

    private double fillPercentage;
    private boolean isWeekend;
    private LocalDateTime recordedAt;

    // Default constructor
    public FillLevelRecord() {}

    // Parameterized constructor
    public FillLevelRecord(Bin bin, double fillPercentage, boolean isWeekend, LocalDateTime recordedAt) {
        this.bin = bin;
        this.fillPercentage = fillPercentage;
        this.isWeekend = isWeekend;
        this.recordedAt = recordedAt;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Bin getBin() { return bin; }
    public void setBin(Bin bin) { this.bin = bin; }

    public double getFillPercentage() { return fillPercentage; }
    public void setFillPercentage(double fillPercentage) { this.fillPercentage = fillPercentage; }

    public boolean getIsWeekend() { return isWeekend; }
    public void setIsWeekend(boolean isWeekend) { this.isWeekend = isWeekend; }

    public LocalDateTime getRecordedAt() { return recordedAt; }
    public void setRecordedAt(LocalDateTime recordedAt) { this.recordedAt = recordedAt; }
}
