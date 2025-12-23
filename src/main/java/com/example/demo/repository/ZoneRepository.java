package com.example.demo.repository;

import com.example.demo.model.Zone;

import java.util.*;

public interface ZoneRepository {
    Optional<Zone> findById(Long id);
    Zone save(Zone zone);
    Optional<Zone> findByZoneName(String name);
}
