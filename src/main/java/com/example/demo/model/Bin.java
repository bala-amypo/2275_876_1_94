package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "bins")
public class Bin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String binName;
    private double latitude;
    private double longitude;
    private String locationDescription;
    private int capacityLiters;   // Added to fix getCapacityLiters() error
    private boolean active;       // Added to fix setActive() error
    private Timestamp createdAt;  // Added to fix setCreatedAt()
    private Timestamp updatedAt;  // Added to fix setUpdatedAt()

    @ManyToOne
    private Zone zone;

    public Bin() {}

    // Getters & setters
    public Long getId() { return id; }

    public String getBinName() { return binName; }
    public void setBinName(String binName) { this.binName = binName; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public String getLocationDescription() { return locationDescription; }
    public void setLocationDescription(String locationDescription) { this.locationDescription = locationDescription; }

    public int getCapacityLiters() { return capacityLiters; }
    public void setCapacityLiters(int capacityLiters) { this.capacityLiters = capacityLiters; }

    public boolean getActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }

    public Zone getZone() { return zone; }
    public void setZone(Zone zone) { this.zone = zone; }
}
