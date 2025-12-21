package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "zone")
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "zone_name", nullable = false)
    private String name;

    @Column(nullable = false)
    private String location = "UNKNOWN";

    @Column(nullable = false)
    private Boolean active = true;

    public Zone() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    // ✅ The service was calling getName(), so make sure the field is 'name' with getter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    // Optional boolean style getter
    public boolean isActive() {
        return active != null && active;
    }
}
