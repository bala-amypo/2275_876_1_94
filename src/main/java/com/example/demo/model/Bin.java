package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bins")
public class Bin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String binName;

    private double latitude;      // latitude field
    private double longitude;     // longitude field
    private String locationDescription;  // location description

    @ManyToOne
    private Zone zone;

    // Default constructor
    public Bin() {}

    // Getters & Setters
    public Long getId() { return id; }

    public String getBinName() { return binName; }
    public void setBinName(String binName) { this.binName = binName; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public String getLocationDescription() { return locationDescription; }
    public void setLocationDescription(String locationDescription) { this.locationDescription = locationDescription; }

    public Zone getZone() { return zone; }
    public void setZone(Zone zone) { this.zone = zone; }
}
