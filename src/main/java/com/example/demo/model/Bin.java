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












// package com.example.demo.model;

// import jakarta.persistence.*;
// import java.sql.Timestamp;

// @Entity
// public class Bin {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String identifier;
//     private Double capacityLiters;
//     private boolean active = true;

//     private String locationDescription;
//     private Double latitude;
//     private Double longitude;

//     private Timestamp createdAt;
//     private Timestamp updatedAt;

//     /* ---------- GETTERS ---------- */

//     public Long getId() { return id; }
//     public String getIdentifier() { return identifier; }
//     public Double getCapacityLiters() { return capacityLiters; }
//     public boolean getActive() { return active; }
//     public String getLocationDescription() { return locationDescription; }
//     public Double getLatitude() { return latitude; }
//     public Double getLongitude() { return longitude; }
//     public Timestamp getCreatedAt() { return createdAt; }
//     public Timestamp getUpdatedAt() { return updatedAt; }

//     /* ---------- SETTERS ---------- */

//     public void setIdentifier(String identifier) {
//         this.identifier = identifier;
//     }

//     public void setCapacityLiters(Double capacityLiters) {
//         this.capacityLiters = capacityLiters;
//     }

//     public void setActive(boolean active) {
//         this.active = active;
//     }

//     public void setLocationDescription(String locationDescription) {
//         this.locationDescription = locationDescription;
//     }

//     public void setLatitude(Double latitude) {
//         this.latitude = latitude;
//     }

//     public void setLongitude(Double longitude) {
//         this.longitude = longitude;
//     }

//     public void setCreatedAt(Timestamp createdAt) {
//         this.createdAt = createdAt;
//     }

//     public void setUpdatedAt(Timestamp updatedAt) {
//         this.updatedAt = updatedAt;
//     }
// }









// package com.example.demo.model;

// import jakarta.persistence.*;

// @Entity
// public class Bin {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String identifier;

//     private String locationDescription;

//     private Double latitude;

//     private Double longitude;

//     private Double capacityLiters;

//     private Boolean active = true;

//     @ManyToOne
//     private Zone zone;

//     // ===== GETTERS & SETTERS =====

//     public Long getId() {
//         return id;
//     }

//     public String getIdentifier() {
//         return identifier;
//     }

//     public void setIdentifier(String identifier) {
//         this.identifier = identifier;
//     }

//     public String getLocationDescription() {
//         return locationDescription;
//     }

//     public void setLocationDescription(String locationDescription) {
//         this.locationDescription = locationDescription;
//     }

//     public Double getLatitude() {
//         return latitude;
//     }

//     public void setLatitude(Double latitude) {
//         this.latitude = latitude;
//     }

//     public Double getLongitude() {
//         return longitude;
//     }

//     public void setLongitude(Double longitude) {
//         this.longitude = longitude;
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

//     public Zone getZone() {
//         return zone;
//     }

//     public void setZone(Zone zone) {
//         this.zone = zone;
//     }
// }





// package com.example.demo.model;

// import jakarta.persistence.*;
// import java.sql.Timestamp;

// @Entity
// public class Bin {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String identifier;

//     private String locationDescription;

//     private Double latitude;

//     private Double longitude;

//     private Double capacityLiters;

//     private Boolean active = true;

//     private Timestamp createdAt;

//     private Timestamp updatedAt;

//     @ManyToOne
//     private Zone zone;

//     // ===== GETTERS & SETTERS =====

//     public Long getId() {
//         return id;
//     }

//     public String getIdentifier() {
//         return identifier;
//     }

//     public void setIdentifier(String identifier) {
//         this.identifier = identifier;
//     }

//     public String getLocationDescription() {
//         return locationDescription;
//     }

//     public void setLocationDescription(String locationDescription) {
//         this.locationDescription = locationDescription;
//     }

//     public Double getLatitude() {
//         return latitude;
//     }

//     public void setLatitude(Double latitude) {
//         this.latitude = latitude;
//     }

//     public Double getLongitude() {
//         return longitude;
//     }

//     public void setLongitude(Double longitude) {
//         this.longitude = longitude;
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

//     public Zone getZone() {
//         return zone;
//     }

//     public void setZone(Zone zone) {
//         this.zone = zone;
//     }

//     public Timestamp getCreatedAt() {
//         return createdAt;
//     }

//     public void setCreatedAt(Timestamp createdAt) {
//         this.createdAt = createdAt;
//     }

//     public Timestamp getUpdatedAt() {
//         return updatedAt;
//     }

//     public void setUpdatedAt(Timestamp updatedAt) {
//         this.updatedAt = updatedAt;
//     }
// }








// package com.example.demo.model;

// import jakarta.persistence.*;
// import java.sql.Timestamp;

// @Entity
// @Table(name = "bins")
// public class Bin {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(nullable = false)
//     private String name;

//     private Integer capacity;

//     @Column(name = "created_at")
//     private Timestamp createdAt;

//     @Column(name = "updated_at")
//     private Timestamp updatedAt;

//     // ✅ NO-ARG CONSTRUCTOR (REQUIRED BY JPA)
//     public Bin() {
//     }

//     // ✅ GETTERS AND SETTERS (VERY IMPORTANT)

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public String getName() {          // 🔥 REQUIRED
//         return name;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public Integer getCapacity() {      // 🔥 REQUIRED
//         return capacity;
//     }

//     public void setCapacity(Integer capacity) {
//         this.capacity = capacity;
//     }

//     public Timestamp getCreatedAt() {
//         return createdAt;
//     }

//     public void setCreatedAt(Timestamp createdAt) {
//         this.createdAt = createdAt;
//     }

//     public Timestamp getUpdatedAt() {
//         return updatedAt;
//     }

//     public void setUpdatedAt(Timestamp updatedAt) {
//         this.updatedAt = updatedAt;
//     }
// }












package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "bins")
public class Bin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Integer capacity;

    private Boolean active;          // 🔥 NEW FIELD

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    // ✅ NO-ARG CONSTRUCTOR
    public Bin() {}

    // ✅ GETTERS AND SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Boolean getActive() {      // 🔥 REQUIRED
        return active;
    }

    public void setActive(Boolean active) {  // 🔥 REQUIRED
        this.active = active;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
