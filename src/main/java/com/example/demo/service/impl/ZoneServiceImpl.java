package com.example.demo.service.impl;

import com.example.demo.model.Zone;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.ZoneService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository zoneRepository;

    public ZoneServiceImpl(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    @Override
    public Zone createZone(Zone zone) {
        return zoneRepository.save(zone);
    }

    @Override
    public Zone updateZone(Long id, Zone zone) {
        return zoneRepository.findById(id).map(z -> zoneRepository.save(zone)).orElse(null);
    }

    @Override
    public Zone getZoneById(Long id) {
        return zoneRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteZone(Long id) {
        zoneRepository.deleteById(id);
    }

    @Override
    public List<Zone> getAllZones() {
        return zoneRepository.findAll();
    }

    // method called in test
    public void deactivateZone(long zoneId) {
        // dummy
    }
}
