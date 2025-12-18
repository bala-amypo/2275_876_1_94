package com.yourpackage.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
// @Table(name = "fill_level_records")
public class FillLevelRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bin_id")
    private Bin bin;

    @Column(nullable = false)
    private Double fillPercentage;

    @Column(nullable = false)
    private Instant recordedAt;

    private Boolean isWeekend;

    // Default Constructor
    public FillLevelRecord() {

    }

    // Parameterized Constructor
    public FillLevelRecord(Bin bin, Double fillPercentage,Instant recordedAt, Boolean isWeekend) {
        this.bin = bin;
        this.fillPercentage = fillPercentage;
        this.recordedAt = recordedAt;
        this.isWeekend = isWeekend;
    }

    @PrePersist
    private void validate() {
        if (fillPercentage < 0 || fillPercentage > 100)
            throw new IllegalArgumentException("Fill percentage must be between 0 and 100");
        if (recordedAt.isAfter(Instant.now()))
            throw new IllegalArgumentException("Recorded date cannot be future");
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

    public Double getFillPercentage() { 
        return fillPercentage; 
    }
    public void setFillPercentage(Double fillPercentage) {
        this.fillPercentage = fillPercentage;
    }

    public Instant getRecordedAt() { 
        return recordedAt; 
    }
    public void setRecordedAt(Instant recordedAt) {
        this.recordedAt = recordedAt;
    }

    public Boolean getIsWeekend() { 
        return isWeekend; 
    }
    public void setIsWeekend(Boolean isWeekend) {
        this.isWeekend = isWeekend;
    }

}