// package com.example.demo.model;

// import jakarta.persistence.*;
// import java.sql.Timestamp;

// @Entity
// @Table(name = "fill_level_records")
// public class FillLevelRecord {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     private Bin bin;

//     private Double fillPercentage;
//     private Timestamp recordedAt;
//     private Boolean isWeekend;

//     public FillLevelRecord() {

//     }

//     public FillLevelRecord(Bin bin, Double fillPercentage,Timestamp recordedAt, Boolean isWeekend) {
//         this.bin = bin;
//         this.fillPercentage = fillPercentage;
//         this.recordedAt = recordedAt;
//         this.isWeekend = isWeekend;
//     }

//     // Getters & Setters
//     public Bin getBin() { 
//         return bin; 
//     }
//     public Double getFillPercentage() { 
//         return fillPercentage; 
//     }
//     public Timestamp getRecordedAt() { 
//         return recordedAt; 
    
//     }
// }












package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
public class FillLevelRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double fillPercentage;
    private boolean weekend;
    private Timestamp recordedAt;

    @ManyToOne
    private Bin bin;

    public Double getFillPercentage() { return fillPercentage; }
    public boolean isWeekend() { return weekend; }
    public Timestamp getRecordedAt() { return recordedAt; }
    public Bin getBin() { return bin; }

    public void setBin(Bin bin) { this.bin = bin; }
}
