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

@Entity
public class OverflowPrediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Bin bin;

    public Bin getBin() {
        return bin;
    }
}
