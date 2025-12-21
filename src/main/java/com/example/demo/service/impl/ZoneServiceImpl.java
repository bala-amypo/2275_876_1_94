package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
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
        // Ensure zone name is unique
        zoneRepository.findByZoneName(zone.getZoneName()).ifPresent(existing -> {
            throw new BadRequestException("Zone name must be unique");
        });

        if (zone.getActive() == null) {
            zone.setActive(true); // Default active
        }

        return zoneRepository.save(zone);
    }

    @Override
    public Zone updateZone(Long id, Zone zone) {
        Zone existingZone = zoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found with id " + id));

        if (!existingZone.getZoneName().equals(zone.getZoneName())) {
            // Check unique name on update
            zoneRepository.findByZoneName(zone.getZoneName()).ifPresent(other -> {
                throw new BadRequestException("Zone name must be unique");
            });
        }

        existingZone.setZoneName(zone.getZoneName());
        existingZone.setDescription(zone.getDescription());
        if (zone.getActive() != null) {
            existingZone.setActive(zone.getActive());
        }

        return zoneRepository.save(existingZone);
    }

    @Override
    public Zone getZoneById(Long id) {
        return zoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found with id " + id));
    }

    @Override
    public List<Zone> getAllZones() {
        return zoneRepository.findAll();
    }

    @Override
    public void deactivateZone(Long id) {
        Zone zone = zoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found with id " + id));
        zone.setActive(false);
        zoneRepository.save(zone);
    }
}
