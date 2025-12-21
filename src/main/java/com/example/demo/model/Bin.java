package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bins")
public class Bin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identifier;
    private double capacityLiters;
    private boolean active;
    private String zone;

    private double latitude;
    private double longitude;
    private String locationDescription;

    // Default constructor
    public Bin() {}

    // Parameterized constructor
    public Bin(String identifier, double capacityLiters, boolean active, String zone) {
        this.identifier = identifier;
        this.capacityLiters = capacityLiters;
        this.active = active;
        this.zone = zone;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIdentifier() { return identifier; }
    public void setIdentifier(String identifier) { this.identifier = identifier; }

    public double getCapacityLiters() { return capacityLiters; }
    public void setCapacityLiters(double capacityLiters) { this.capacityLiters = capacityLiters; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public String getZone() { return zone; }
    public void setZone(String zone) { this.zone = zone; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public String getLocationDescription() { return locationDescription; }
    public void setLocationDescription(String locationDescription) { this.locationDescription = locationDescription; }
}
