// package com.example.demo.model;

// import jakarta.persistence.*;
// import java.sql.Date;
// import java.sql.Timestamp;
// import java.time.LocalDate;

// @Entity
// @Table(name = "overflow_predictions")
// public class OverflowPrediction {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
    
//     @ManyToOne
//     @JoinColumn(name = "bin_id")
//     private Bin bin;
    
//     private Date predictedFullDate;
//     private Integer daysUntilFull;
    
//     @ManyToOne
//     @JoinColumn(name = "model_id")
//     private UsagePatternModel modelUsed;
    
//     private Timestamp generatedAt;
    
//     public OverflowPrediction() {}
    
//     public OverflowPrediction(Bin bin, Date predictedFullDate, Integer daysUntilFull, 
//                              UsagePatternModel modelUsed, Timestamp generatedAt) {
//         this.bin = bin;
//         this.predictedFullDate = predictedFullDate;
//         this.daysUntilFull = daysUntilFull;
//         this.modelUsed = modelUsed;
//         this.generatedAt = generatedAt;
//     }
    
//     // Convenience constructor if tests pass LocalDate
//     public OverflowPrediction(Bin bin, LocalDate predictedFullLocalDate, Integer daysUntilFull,
//                               UsagePatternModel modelUsed, Timestamp generatedAt) {
//         this(bin, predictedFullLocalDate != null ? Date.valueOf(predictedFullLocalDate) : null,
//              daysUntilFull, modelUsed, generatedAt);
//     }
    
//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }
    
//     public Bin getBin() { return bin; }
//     public void setBin(Bin bin) { this.bin = bin; }
    
//     public Date getPredictedFullDate() { return predictedFullDate; }
//     public void setPredictedFullDate(Date predictedFullDate) { this.predictedFullDate = predictedFullDate; }
    
//     // Overloaded setter to accept LocalDate in tests
//     public void setPredictedFullDate(LocalDate predictedFullLocalDate) {
//         this.predictedFullDate = predictedFullLocalDate != null ? Date.valueOf(predictedFullLocalDate) : null;
//     }
    
//     public Integer getDaysUntilFull() { return daysUntilFull; }
//     public void setDaysUntilFull(Integer daysUntilFull) { this.daysUntilFull = daysUntilFull; }
    
//     public UsagePatternModel getModelUsed() { return modelUsed; }
//     public void setModelUsed(UsagePatternModel modelUsed) { this.modelUsed = modelUsed; }
    
//     public Timestamp getGeneratedAt() { return generatedAt; }
//     public void setGeneratedAt(Timestamp generatedAt) { this.generatedAt = generatedAt; }
// }






package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "overflow_predictions")
public class OverflowPrediction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "bin_id")
    private Bin bin;
    
    private Date predictedFullDate;
    private Integer daysUntilFull;
    
    @ManyToOne
    @JoinColumn(name = "model_id")
    private UsagePatternModel modelUsed;
    
    private Timestamp generatedAt;
    
    public OverflowPrediction() {}
    
    public OverflowPrediction(Bin bin, Date predictedFullDate, Integer daysUntilFull, 
                             UsagePatternModel modelUsed, Timestamp generatedAt) {
        this.bin = bin;
        this.predictedFullDate = predictedFullDate;
        this.daysUntilFull = daysUntilFull;
        this.modelUsed = modelUsed;
        this.generatedAt = generatedAt;
    }
    
    // Convenience constructor if tests pass LocalDate
    public OverflowPrediction(Bin bin, LocalDate predictedFullLocalDate, Integer daysUntilFull,
                              UsagePatternModel modelUsed, Timestamp generatedAt) {
        this(bin, predictedFullLocalDate != null ? Date.valueOf(predictedFullLocalDate) : null,
             daysUntilFull, modelUsed, generatedAt);
    }
    
    // Comprehensive constructor for tests using both LocalDate and LocalDateTime
    public OverflowPrediction(Bin bin, LocalDate predictedFullLocalDate, Integer daysUntilFull,
                              UsagePatternModel modelUsed, LocalDateTime generatedAt) {
        this.bin = bin;
        this.predictedFullDate = predictedFullLocalDate != null ? Date.valueOf(predictedFullLocalDate) : null;
        this.daysUntilFull = daysUntilFull;
        this.modelUsed = modelUsed;
        this.generatedAt = generatedAt != null ? Timestamp.valueOf(generatedAt) : null;
    }
    
    // Constructor for tests using Date and LocalDateTime
    public OverflowPrediction(Bin bin, Date predictedFullDate, Integer daysUntilFull,
                              UsagePatternModel modelUsed, LocalDateTime generatedAt) {
        this.bin = bin;
        this.predictedFullDate = predictedFullDate;
        this.daysUntilFull = daysUntilFull;
        this.modelUsed = modelUsed;
        this.generatedAt = generatedAt != null ? Timestamp.valueOf(generatedAt) : null;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Bin getBin() { return bin; }
    public void setBin(Bin bin) { this.bin = bin; }
    
    public Date getPredictedFullDate() { return predictedFullDate; }
    public void setPredictedFullDate(Date predictedFullDate) { this.predictedFullDate = predictedFullDate; }
    
    // Overloaded setter to accept LocalDate in tests
    public void setPredictedFullDate(LocalDate predictedFullLocalDate) {
        this.predictedFullDate = predictedFullLocalDate != null ? Date.valueOf(predictedFullLocalDate) : null;
    }
    
    public Integer getDaysUntilFull() { return daysUntilFull; }
    public void setDaysUntilFull(Integer daysUntilFull) { this.daysUntilFull = daysUntilFull; }
    
    public UsagePatternModel getModelUsed() { return modelUsed; }
    public void setModelUsed(UsagePatternModel modelUsed) { this.modelUsed = modelUsed; }
    
    public Timestamp getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(Timestamp generatedAt) { this.generatedAt = generatedAt; }
    
    // Convenience setter for tests using LocalDateTime
    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt != null ? Timestamp.valueOf(generatedAt) : null;
    }
}