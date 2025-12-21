package com.example.demo.service;

import com.example.demo.model.Zone;

import java.util.List;
import java.util.Optional;

public interface ZoneService {

    List<Zone> getAllZones();

    Optional<Zone> getZoneById(Long id);

    Zone createZone(Zone zone);

    Zone updateZone(Long id, Zone zone);

    boolean deleteZone(Long id); // ✅ return boolean instead of void
}
