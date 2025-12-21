package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "zones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String zoneName;

    private String description;

    @Column(nullable = false)
    private Boolean active = true;

    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL)
    private List<Bin> bins;

    public Zone(String zoneName, String description, Boolean active) {
        this.zoneName = zoneName;
        this.description = description;
        this.active = active;
    }
}
