// package com.example.demo.model;

// import com.example.demo.model.Zone;
// import jakarta.persistence.*;

// @Entity
// @Table(name = "zones")
// public class Zone {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(unique = true, nullable = false)
//     private String zoneName;

//     private String description;

//     private Boolean active;

//     public Zone() {

//     }

//     public Zone(String zoneName, String description, Boolean active) {
//         this.zoneName = zoneName;
//         this.description = description;
//         this.active = active;
//     }

//     // Getters and Setters
//     public Long getId() { 
//         return id; 
//     }
//     public String getZoneName() { 
//         return zoneName; 
//     }
//     public void setZoneName(String zoneName) { 
//         this.zoneName = zoneName; 
//     }
//     public String getDescription() { 
//         return description; 
//     }
//     public void setDescription(String description) { 
//         this.description = description; 
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

// @Entity
// public class Zone {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String name;

//     // ===== GETTERS & SETTERS =====

//     public Long getId() {
//         return id;
//     }

//     public String getName() {
//         return name;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }
// }










package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String zoneName;

    private String description;

    private Boolean active = true;

    // ===== GETTERS & SETTERS =====

    public Long getId() {
        return id;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
