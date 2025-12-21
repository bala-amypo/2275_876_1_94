package com.example.demo.service.impl;

import com.example.demo.model.Zone;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZoneServiceImpl implements ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;

    @Override
    public List<Zone> getAllZones() {
        return zoneRepository.findAll();
    }

    @Override
    public Optional<Zone> getZoneById(Long id) {
        return zoneRepository.findById(id);
    }

    @Override
    public Zone createZone(Zone zone) {
        return zoneRepository.save(zone);
    }

    @Override
    public Zone updateZone(Long id, Zone zone) {
        Optional<Zone> existingZone = zoneRepository.findById(id);
        if (existingZone.isPresent()) {
            Zone z = existingZone.get();
            z.setName(zone.getName());
            z.setLocation(zone.getLocation());
            return zoneRepository.save(z);
        }
        return null;
    }

    @Override
    public boolean deleteZone(Long id) {
        if (zoneRepository.existsById(id)) {
            zoneRepository.deleteById(id);
            return true; // ✅ return true if deleted
        }
        return false; // ✅ return false if not found
    }
}
