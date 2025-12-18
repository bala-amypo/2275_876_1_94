// package com.example.demo.model;

// import jakarta.persistence.*;
// import java.sql.Timestamp;

// @Entity
// @Table(name = "usage_pattern_models")
// public class UsagePatternModel {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     private Bin bin;

//     private Double avgDailyIncreaseWeekday;
//     private Double avgDailyIncreaseWeekend;
//     private Timestamp lastUpdated;

//     public UsagePatternModel() {

//     }

//     public UsagePatternModel(Bin bin, Double weekday,Double weekend, Timestamp lastUpdated) {
//         this.bin = bin;
//         this.avgDailyIncreaseWeekday = weekday;
//         this.avgDailyIncreaseWeekend = weekend;
//         this.lastUpdated = lastUpdated;
//     }

//     // Getters
//     public Bin getBin() { 
//         return bin; 
//     }
//     public Double getAvgDailyIncreaseWeekday() { 
//         return avgDailyIncreaseWeekday; 
//     }
//     public Double getAvgDailyIncreaseWeekend() { 
//         return avgDailyIncreaseWeekend; 
//     }

// }













@Entity
public class UsagePatternModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double avgDailyIncreaseWeekday;
    private Double avgDailyIncreaseWeekend;

    @ManyToOne
    private Bin bin;

    private Timestamp lastUpdated;

    public Double getAvgDailyIncreaseWeekday() {
        return avgDailyIncreaseWeekday;
    }

    public Double getAvgDailyIncreaseWeekend() {
        return avgDailyIncreaseWeekend;
    }

    public Bin getBin() { return bin; }

    public void setBin(Bin bin) { this.bin = bin; }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
