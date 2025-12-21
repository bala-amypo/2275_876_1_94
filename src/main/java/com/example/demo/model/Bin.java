package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "bins")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String identifier;

    private String locationDescription;

    private Double latitude;
    private Double longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id")
    private Zone zone;

    @Column(nullable = false)
    private Double capacityLiters;

    @Column(nullable = false)
    private Boolean active = true;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "bin", cascade = CascadeType.ALL)
    private List<FillLevelRecord> fillLevelRecords;

    @OneToMany(mappedBy = "bin", cascade = CascadeType.ALL)
    private List<OverflowPrediction> overflowPredictions;

    // Convenience constructor without id
    public Bin(String identifier, String locationDescription, Double latitude, Double longitude,
               Zone zone, Double capacityLiters, Boolean active, Timestamp createdAt, Timestamp updatedAt) {
        this.identifier = identifier;
        this.locationDescription = locationDescription;
        this.latitude = latitude;
        this.longitude = longitude;
        this.zone = zone;
        this.capacityLiters = capacityLiters;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
