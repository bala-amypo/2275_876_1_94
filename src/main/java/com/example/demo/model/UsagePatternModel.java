package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usage_pattern_model")
public class UsagePatternModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String modelName;

    @ManyToOne
    @JoinColumn(name = "bin_id", nullable = false)
    private Bin bin;

    public UsagePatternModel() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getModelName() { return modelName; }
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Bin getBin() { return bin; }
    public void setBin(Bin bin) { this.bin = bin; }
}
