package com.example.demo.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class Bin implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identifier;
    private Double capacityLiters;
    private Double latitude;
    private Double longitude;
    private String locationDescription;

    @ManyToOne
    private Zone zone;

    private Boolean active = true;

    public Bin() {}

    public Bin(String identifier, Double capacityLiters, Double latitude, Double longitude, Zone zone, Boolean active) {
        this.identifier = identifier;
        this.capacityLiters = capacityLiters;
        this.latitude = latitude;
        this.longitude = longitude;
        this.zone = zone;
        this.active = active;
    }

    public Long getId() { return id; }
    public String getIdentifier() { return identifier; }
    public void setIdentifier(String identifier) { this.identifier = identifier; }
    public Double getCapacityLiters() { return capacityLiters; }
    public void setCapacityLiters(Double capacityLiters) { this.capacityLiters = capacityLiters; }
    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }
    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
    public String getLocationDescription() { return locationDescription; }
    public void setLocationDescription(String locationDescription) { this.locationDescription = locationDescription; }
    public Zone getZone() { return zone; }
    public void setZone(Zone zone) { this.zone = zone; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
