package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "fill_level_records")
public class FillLevelRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Bin bin;

    private Double fillPercentage;
    private Timestamp recordedAt;
    private Boolean isWeekend;

    public FillLevelRecord() {}

    public FillLevelRecord(Bin bin, Double fillPercentage,
                           Timestamp recordedAt, Boolean isWeekend) {
        this.bin = bin;
        this.fillPercentage = fillPercentage;
        this.recordedAt = recordedAt;
        this.isWeekend = isWeekend;
    }

    public Long getId() { return id; }
    public Bin getBin() { return bin; }
    public Double getFillPercentage() { return fillPercentage; }
    public Timestamp getRecordedAt() { return recordedAt; }

    public void setId(Long id) { this.id = id; }
    public void setBin(Bin bin) { this.bin = bin; }
    public void setFillPercentage(Double fillPercentage) { this.fillPercentage = fillPercentage; }
    public void setRecordedAt(Timestamp recordedAt) { this.recordedAt = recordedAt; }
}
