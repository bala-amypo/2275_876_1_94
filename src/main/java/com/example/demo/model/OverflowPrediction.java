// package com.example.demo.model;

// import jakarta.persistence.*;
// import java.sql.Timestamp;
// import java.util.Date;

// @Entity
// @Table(name = "overflow_predictions")
// public class OverflowPrediction {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     private Bin bin;

//     private Date predictedFullDate;
//     private Integer daysUntilFull;

//     @ManyToOne
//     private UsagePatternModel modelUsed;

//     private Timestamp generatedAt;

//     public OverflowPrediction() {

//     }

//     public OverflowPrediction(Bin bin, Date predictedFullDate,Integer daysUntilFull,UsagePatternModel modelUsed,Timestamp generatedAt) {
//         this.bin = bin;
//         this.predictedFullDate = predictedFullDate;
//         this.daysUntilFull = daysUntilFull;
//         this.modelUsed = modelUsed;
//         this.generatedAt = generatedAt;
//     }

// }













// package com.example.demo.model;

// import jakarta.persistence.*;
// import java.sql.Timestamp;
// import java.util.Date;

// @Entity
// public class OverflowPrediction {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     private Bin bin;

//     private Date predictedOverflowDate;
//     private int daysRemaining;

//     @ManyToOne
//     private UsagePatternModel usagePatternModel;

//     private Timestamp createdAt;

//     public OverflowPrediction() {}

//     public OverflowPrediction(
//             Bin bin,
//             Date predictedOverflowDate,
//             int daysRemaining,
//             UsagePatternModel usagePatternModel,
//             Timestamp createdAt
//     ) {
//         this.bin = bin;
//         this.predictedOverflowDate = predictedOverflowDate;
//         this.daysRemaining = daysRemaining;
//         this.usagePatternModel = usagePatternModel;
//         this.createdAt = createdAt;
//     }
// }













package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class OverflowPrediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Bin bin;

    private Date predictedOverflowDate;

    private Integer predictedFillPercentage;

    @ManyToOne
    private UsagePatternModel usagePatternModel;

    private Timestamp createdAt;

    // ===== NO-ARGS CONSTRUCTOR =====
    public OverflowPrediction() {
    }

    // ===== ALL-ARGS CONSTRUCTOR =====
    public OverflowPrediction(
            Bin bin,
            Date predictedOverflowDate,
            Integer predictedFillPercentage,
            UsagePatternModel usagePatternModel,
            Timestamp createdAt
    ) {
        this.bin = bin;
        this.predictedOverflowDate = predictedOverflowDate;
        this.predictedFillPercentage = predictedFillPercentage;
        this.usagePatternModel = usagePatternModel;
        this.createdAt = createdAt;
    }

    // ===== GETTERS & SETTERS =====

    public Long getId() {
        return id;
    }

    public Bin getBin() {
        return bin;
    }

    public void setBin(Bin bin) {
        this.bin = bin;
    }

    public Date getPredictedOverflowDate() {
        return predictedOverflowDate;
    }

    public void setPredictedOverflowDate(Date predictedOverflowDate) {
        this.predictedOverflowDate = predictedOverflowDate;
    }

    public Integer getPredictedFillPercentage() {
        return predictedFillPercentage;
    }

    public void setPredictedFillPercentage(Integer predictedFillPercentage) {
        this.predictedFillPercentage = predictedFillPercentage;
    }

    public UsagePatternModel getUsagePatternModel() {
        return usagePatternModel;
    }

    public void setUsagePatternModel(UsagePatternModel usagePatternModel) {
        this.usagePatternModel = usagePatternModel;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
