package com.example.demo.service;

import com.example.demo.model.Zone;

import java.util.List;

@Service
public interface ZoneService {

    Zone createZone(Zone zone);

    Zone updateZone(Long id, Zone zone);

    Zone getZoneById(Long id);

    List<Zone> getAllZones();

    void deactivateZone(Long id);
}
