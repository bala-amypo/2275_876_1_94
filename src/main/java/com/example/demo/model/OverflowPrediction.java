package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "overflow_predictions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OverflowPrediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bin_id", nullable = false)
    private Bin bin;

    @Column(nullable = false)
    private Date predictedFullDate;

    @Column(nullable = false)
    private Integer daysUntilFull;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private UsagePatternModel modelUsed;

    @Column(nullable = false)
    private Timestamp generatedAt;

    public OverflowPrediction(Bin bin, Date predictedFullDate, Integer daysUntilFull, UsagePatternModel modelUsed, Timestamp generatedAt) {
        this.bin = bin;
        this.predictedFullDate = predictedFullDate;
        this.daysUntilFull = daysUntilFull;
        this.modelUsed = modelUsed;
        this.generatedAt = generatedAt;
    }
}
