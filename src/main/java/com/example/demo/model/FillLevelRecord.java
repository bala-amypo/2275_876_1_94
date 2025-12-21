package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fill_level_records")
public class FillLevelRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int level;

    @ManyToOne
    @JoinColumn(name = "bin_id")
    private Bin bin;

    private LocalDateTime createdAt;

    public FillLevelRecord() {}

    public FillLevelRecord(int level, Bin bin, LocalDateTime createdAt) {
        this.level = level;
        this.bin = bin;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    public Bin getBin() { return bin; }
    public void setBin(Bin bin) { this.bin = bin; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
