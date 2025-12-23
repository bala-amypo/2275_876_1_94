package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Zone;
import com.example.demo.repository.ZoneRepository;

public class ZoneServiceImpl {
    private final ZoneRepository zoneRepository;

    public ZoneServiceImpl(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    public Zone createZone(Zone zone) {
        if (zone.getActive() == null) zone.setActive(true);
        return zoneRepository.save(zone);
    }

    public Zone getZoneById(Long id) {
        return zoneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
    }

    public Zone updateZone(Long id, Zone update) {
        Zone existing = getZoneById(id);
        if (update.getDescription() != null) existing.setDescription(update.getDescription());
        if (update.getZoneName() != null) existing.setZoneName(update.getZoneName());
        return zoneRepository.save(existing);
    }

    public void deactivateZone(Long id) {
        Zone existing = getZoneById(id);
        existing.setActive(false);
        zoneRepository.save(existing);
    }
}
