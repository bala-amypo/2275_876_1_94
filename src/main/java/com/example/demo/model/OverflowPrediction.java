package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "overflow_predictions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OverflowPrediction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Bin bin;

    private Date predictedFullDate;
    private Integer daysUntilFull;
    private Timestamp generatedAt;

    @ManyToOne
    private UsagePatternModel modelUsed;
}
