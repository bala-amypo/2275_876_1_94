// package com.example.demo.model;

// import jakarta.persistence.*;
// import java.time.LocalDate;
// import java.time.LocalDateTime;

// @Entity
// @Table(name = "fill_level_records")
// public class FillLevelRecord {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
    
//     @ManyToOne
//     @JoinColumn(name = "bin_id")
//     private Bin bin;
    
//     private Double fillPercentage;
//     private LocalDateTime recordedAt;
//     private Boolean isWeekend;
    
//     public FillLevelRecord() {}
    
//     public FillLevelRecord(Bin bin, Double fillPercentage, LocalDateTime recordedAt, Boolean isWeekend) {
//         this.bin = bin;
//         this.fillPercentage = fillPercentage;
//         this.recordedAt = recordedAt;
//         this.isWeekend = isWeekend;
//     }
    
//     // Convenience constructor if tests pass a LocalDate; default time set to start of day
//     public FillLevelRecord(Bin bin, Double fillPercentage, LocalDate recordedDate, Boolean isWeekend) {
//         this(bin, fillPercentage, recordedDate != null ? recordedDate.atStartOfDay() : null, isWeekend);
//     }
    
//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }
    
//     public Bin getBin() { return bin; }
//     public void setBin(Bin bin) { this.bin = bin; }
    
//     public Double getFillPercentage() { return fillPercentage; }
//     public void setFillPercentage(Double fillPercentage) { this.fillPercentage = fillPercentage; }
    
//     public LocalDateTime getRecordedAt() { return recordedAt; }
//     public void setRecordedAt(LocalDateTime recordedAt) { this.recordedAt = recordedAt; }
    
//     public Boolean getIsWeekend() { return isWeekend; }
//     public void setIsWeekend(Boolean isWeekend) { this.isWeekend = isWeekend; }
// }






package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
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
    
    private Double fillPercentage;
    private Timestamp recordedAt;
    private Boolean isWeekend;
    
    public FillLevelRecord() {}
    
    public FillLevelRecord(Bin bin, Double fillPercentage, Timestamp recordedAt, Boolean isWeekend) {
        this.bin = bin;
        this.fillPercentage = fillPercentage;
        this.recordedAt = recordedAt;
        this.isWeekend = isWeekend;
    }
    
    // Convenience constructor for tests using LocalDateTime
    public FillLevelRecord(Bin bin, Double fillPercentage, LocalDateTime recordedAt, Boolean isWeekend) {
        this.bin = bin;
        this.fillPercentage = fillPercentage;
        this.recordedAt = recordedAt != null ? Timestamp.valueOf(recordedAt) : null;
        this.isWeekend = isWeekend;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Bin getBin() { return bin; }
    public void setBin(Bin bin) { this.bin = bin; }
    
    public Double getFillPercentage() { return fillPercentage; }
    public void setFillPercentage(Double fillPercentage) { this.fillPercentage = fillPercentage; }
    
    public Timestamp getRecordedAt() { return recordedAt; }
    public void setRecordedAt(Timestamp recordedAt) { this.recordedAt = recordedAt; }
    
    // Convenience setter for tests using LocalDateTime
    public void setRecordedAt(LocalDateTime recordedAt) {
        this.recordedAt = recordedAt != null ? Timestamp.valueOf(recordedAt) : null;
    }
    
    public Boolean getIsWeekend() { return isWeekend; }
    public void setIsWeekend(Boolean isWeekend) { this.isWeekend = isWeekend; }
}