package com.example.demo.service;

import com.example.demo.model.Zone;
import java.util.List;

public interface ZoneService {
    List<Zone> getAllZones();
    Zone getZoneById(Long id);
    Zone createZone(Zone zone);
    Zone updateZone(Long id, Zone zone);
    void deleteZone(Long id);
}
