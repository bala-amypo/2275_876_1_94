package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class FillLevelRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float level;

    @ManyToOne
    @JoinColumn(name = "bin_id")
    private Bin bin;

    private LocalDateTime generatedAt;  // ⚡ Make sure this exists

    // Getters and Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public float getLevel() { return level; }

    public void setLevel(float level) { this.level = level; }

    public Bin getBin() { return bin; }

    public void setBin(Bin bin) { this.bin = bin; }

    public LocalDateTime getGeneratedAt() { return generatedAt; }

    public void setGeneratedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; }
}
