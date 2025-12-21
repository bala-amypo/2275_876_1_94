package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Table(name = "fill_level_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FillLevelRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bin_id", nullable = false)
    private Bin bin;

    @Column(nullable = false)
    private Double fillPercentage;

    @Column(nullable = false)
    private Timestamp recordedAt;

    @Column(nullable = false)
    private Boolean isWeekend;

    public FillLevelRecord(Bin bin, Double fillPercentage, Timestamp recordedAt, Boolean isWeekend) {
        this.bin = bin;
        this.fillPercentage = fillPercentage;
        this.recordedAt = recordedAt;
        this.isWeekend = isWeekend;
    }
}
