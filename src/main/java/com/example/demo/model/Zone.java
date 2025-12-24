package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "zones")
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "zone")
    private List<Bin> bins;

    public Zone() {}

    public Zone(String name) {
        this.name = name;
    }

    // getters & setters
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Bin> getBins() { return bins; }
    public void setBins(List<Bin> bins) { this.bins = bins; }
}
