// package com.example.demo.model;

// import jakarta.persistence.*;
// import java.sql.Timestamp;

// @Entity
// @Table(name = "bins")
// public class Bin {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(unique = true)
//     private String identifier;

//     private String locationDescription;
//     private Double latitude;
//     private Double longitude;

//     @ManyToOne
//     private Zone zone;

//     private Double capacityLiters;
//     private Boolean active;

//     private Timestamp createdAt;
//     private Timestamp updatedAt;

//     public Bin() {

//     }

//     public Bin(String identifier, String locationDescription,Double latitude, Double longitude,Zone zone, Double capacityLiters,Boolean active, Timestamp createdAt, Timestamp updatedAt) {
//         this.identifier = identifier;
//         this.locationDescription = locationDescription;
//         this.latitude = latitude;
//         this.longitude = longitude;
//         this.zone = zone;
//         this.capacityLiters = capacityLiters;
//         this.active = active;
//         this.createdAt = createdAt;
//         this.updatedAt = updatedAt;
//     }

//     // Getters and Setters
//     public Long getId() { 
//         return id; 
//     }
//     public String getIdentifier() { 
//         return identifier; 
//     }
//     public void setIdentifier(String identifier) { 
//         this.identifier = identifier; 
//     }
//     public Zone getZone() { 
//         return zone; 
//     }
//     public void setZone(Zone zone) { 
//         this.zone = zone; 
//     }
//     public Double getCapacityLiters() { 
//         return capacityLiters;
//     }
//     public void setCapacityLiters(Double capacityLiters) { 
//         this.capacityLiters = capacityLiters; 
//     }
//     public Boolean getActive() { 
//         return active; 
//     }
//     public void setActive(Boolean active) { 
//         this.active = active; 
//     }

// }












package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
public class Bin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identifier;
    private Double capacityLiters;
    private boolean active = true;

    private String locationDescription;
    private Double latitude;
    private Double longitude;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    /* ---------- GETTERS ---------- */

    public Long getId() { return id; }
    public String getIdentifier() { return identifier; }
    public Double getCapacityLiters() { return capacityLiters; }
    public boolean getActive() { return active; }
    public String getLocationDescription() { return locationDescription; }
    public Double getLatitude() { return latitude; }
    public Double getLongitude() { return longitude; }
    public Timestamp getCreatedAt() { return createdAt; }
    public Timestamp getUpdatedAt() { return updatedAt; }

    /* ---------- SETTERS ---------- */

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setCapacityLiters(Double capacityLiters) {
        this.capacityLiters = capacityLiters;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
