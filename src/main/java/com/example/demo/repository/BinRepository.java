package com.example.demo.repository;

import com.example.demo.model.Bin;
import com.example.demo.model.Zone;

import java.util.*;

public interface BinRepository {
    Optional<Bin> findById(Long id);
    Bin save(Bin bin);
    List<Bin> findAll();
    Optional<Bin> findByIdentifier(String identifier);
    List<Bin> findByZoneAndActiveTrue(Zone zone);
}
