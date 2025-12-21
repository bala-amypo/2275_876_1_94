package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "zones")
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "zone_name", unique = true, nullable = false)
    private String zoneName;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "zone")
    private List<Bin> bins;

    public Zone() {
        this.active = true; // default active
    }

    public Zone(String zoneName, String description, Boolean active) {
        this.zoneName = zoneName;
        this.description = description;
        this.active = active != null ? active : true;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public String getZoneName() { return zoneName; }
    public void setZoneName(String zoneName) { this.zoneName = zoneName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    public List<Bin> getBins() { return bins; }
    public void setBins(List<Bin> bins) { this.bins = bins; }
}
