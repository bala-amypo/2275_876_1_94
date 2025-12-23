package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.Zone;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.ZoneRepository;

import java.util.List;

public class BinServiceImpl {
    private final BinRepository binRepository;
    private final ZoneRepository zoneRepository;

    public BinServiceImpl(BinRepository binRepository, ZoneRepository zoneRepository) {
        this.binRepository = binRepository;
        this.zoneRepository = zoneRepository;
    }

    public Bin createBin(Bin bin) {
        if (bin.getCapacityLiters() == null || bin.getCapacityLiters() <= 0) {
            throw new BadRequestException("Capacity must be positive");
        }
        if (bin.getZone() == null || bin.getZone().getId() == null) {
            throw new BadRequestException("Zone is required");
        }
        Zone zone = zoneRepository.findById(bin.getZone().getId())
                .orElseThrow(() -> new BadRequestException("Zone not found"));
        if (Boolean.FALSE.equals(zone.getActive())) {
            throw new BadRequestException("Zone is inactive");
        }
        bin.setZone(zone);
        return binRepository.save(bin);
    }

    public Bin getBinById(Long id) {
        return binRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
    }

    public Bin updateBin(Long id, Bin update) {
        Bin existing = getBinById(id);
        if (update.getLocationDescription() != null) {
            existing.setLocationDescription(update.getLocationDescription());
        }
        if (update.getZone() != null && update.getZone().getId() != null) {
            Zone zone = zoneRepository.findById(update.getZone().getId())
                    .orElseThrow(() -> new BadRequestException("Zone not found"));
            existing.setZone(zone);
        }
        return binRepository.save(existing);
    }

    public void deactivateBin(Long id) {
        Bin existing = getBinById(id);
        existing.setActive(false);
        binRepository.save(existing);
    }

    public List<Bin> getAllBins() {
        return binRepository.findAll();
    }
}
