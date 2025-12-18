package com.yourpackage.entity;

import jakarta.persistence.*;

@Entity
@Table(
    // name = "zones",
    uniqueConstraints = @UniqueConstraint(columnNames = "zoneName")
)
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String zoneName;

    private String description;

    @Column(nullable = false)
    private Boolean active = true;

    // Default Constructor
    public Zone() {}

    // Parameterized Constructor
    public Zone(String zoneName, String description) {
        this.zoneName = zoneName;
        this.description = description;
        this.active = true;
    }

    // Getters & Setters
    public Long getId() { 
        return id; 
    }
    public void setId(Long id) { 
        this.id = id; 
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