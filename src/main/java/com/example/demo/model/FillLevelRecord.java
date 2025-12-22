package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "fill_level_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FillLevelRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Bin bin;

    private Double fillPercentage;
    private Timestamp recordedAt;
    private Boolean isWeekend;
}
