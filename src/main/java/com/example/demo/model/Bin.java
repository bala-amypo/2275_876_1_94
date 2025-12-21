package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "bins")
public class Bin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String identifier;

    private String locationDescription;
    private Double latitude;
    private Double longitude;

    @ManyToOne
    private Zone zone;

    private Double capacityLiters;
    private Boolean active;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Bin() {}

    public Bin(String identifier, String locationDescription, Double latitude,
               Double longitude, Zone zone, Double capacityLiters,
               Boolean active, Timestamp createdAt, Timestamp updatedAt) {
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

    @PrePersist
    public void prePersist() {
        if (active == null) active = true;
        createdAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }

    // getters & setters (portal tests expect them)
    public Long getId() { return id; }
    public String getIdentifier() { return identifier; }
    public Double getCapacityLiters() { return capacityLiters; }
    public Boolean getActive() { return active; }
    public Zone getZone() { return zone; }

    public void setId(Long id) { this.id = id; }
    public void setIdentifier(String identifier) { this.identifier = identifier; }
    public void setCapacityLiters(Double capacityLiters) { this.capacityLiters = capacityLiters; }
    public void setActive(Boolean active) { this.active = active; }
    public void setZone(Zone zone) { this.zone = zone; }
}
