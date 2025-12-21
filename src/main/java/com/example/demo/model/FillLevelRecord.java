package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class FillLevelRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int level;

    @ManyToOne
    @JoinColumn(name = "bin_id")
    private Bin bin;

    private LocalDateTime generatedAt; // timestamp for the record

    // Constructors
    public FillLevelRecord() {}

    public FillLevelRecord(int level, Bin bin, LocalDateTime generatedAt) {
        this.level = level;
        this.bin = bin;
        this.generatedAt = generatedAt;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    public Bin getBin() { return bin; }
    public void setBin(Bin bin) { this.bin = bin; }

    public LocalDateTime getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; }
}
