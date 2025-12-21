package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Table(name = "usage_pattern_models")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsagePatternModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bin_id", nullable = false)
    private Bin bin;

    @Column(nullable = false)
    private Double avgDailyIncreaseWeekday;

    @Column(nullable = false)
    private Double avgDailyIncreaseWeekend;

    @Column(nullable = false)
    private Timestamp lastUpdated;

    public UsagePatternModel(Bin bin, Double avgDailyIncreaseWeekday, Double avgDailyIncreaseWeekend, Timestamp lastUpdated) {
        this.bin = bin;
        this.avgDailyIncreaseWeekday = avgDailyIncreaseWeekday;
        this.avgDailyIncreaseWeekend = avgDailyIncreaseWeekend;
        this.lastUpdated = lastUpdated;
    }
}
