package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "zones", uniqueConstraints = @UniqueConstraint(columnNames = "zoneName"))
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String zoneName;

    private String description;

    @Column(nullable = false)
    private Boolean active = true;

    @OneToMany(mappedBy = "zone")
    private List<Bin> bins;

    public Zone() {}

    public Zone(String zoneName, String description, Boolean active) {
        this.zoneName = zoneName;
        this.description = description;
        this.active = active;
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
