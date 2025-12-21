package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class FillLevelRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Bin bin;

    private LocalDateTime recordedAt;
    private double fillLevel;

    public FillLevelRecord() {}

    public FillLevelRecord(Bin bin, LocalDateTime recordedAt, double fillLevel) {
        this.bin = bin;
        this.recordedAt = recordedAt;
        this.fillLevel = fillLevel;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Bin getBin() { return bin; }
    public void setBin(Bin bin) { this.bin = bin; }

    public LocalDateTime getRecordedAt() { return recordedAt; }
    public void setRecordedAt(LocalDateTime recordedAt) { this.recordedAt = recordedAt; }

    public double getFillLevel() { return fillLevel; }
    public void setFillLevel(double fillLevel) { this.fillLevel = fillLevel; }
}
