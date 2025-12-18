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
    private int daysRemaining;

    @ManyToOne
    private UsagePatternModel usagePatternModel;

    private Timestamp createdAt;

    public OverflowPrediction() {}

    public OverflowPrediction(
            Bin bin,
            Date predictedOverflowDate,
            int daysRemaining,
            UsagePatternModel usagePatternModel,
            Timestamp createdAt
    ) {
        this.bin = bin;
        this.predictedOverflowDate = predictedOverflowDate;
        this.daysRemaining = daysRemaining;
        this.usagePatternModel = usagePatternModel;
        this.createdAt = createdAt;
    }
}
