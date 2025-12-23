package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.Zone;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.ZoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BinServiceImpl {

    private final BinRepository binRepository;
    private final ZoneRepository zoneRepository;

    public BinServiceImpl(BinRepository binRepository, ZoneRepository zoneRepository) {
        this.binRepository = binRepository;
        this.zoneRepository = zoneRepository;
    }

    public Bin createBin(Bin bin) {
        if (bin.getCapacityLiters() <= 0) {
            throw new BadRequestException("Capacity must be greater than 0");
        }

        Zone zone = bin.getZone();
        if (zone == null || zone.getId() == null) {
            throw new BadRequestException("Bin must belong to a zone");
        }

        Zone foundZone = zoneRepository.findById(zone.getId())
                .orElseThrow(() -> new BadRequestException("Zone not found"));

        if (!foundZone.getActive()) {
            throw new BadRequestException("Cannot assign bin to inactive zone");
        }

        bin.setZone(foundZone);
        if (bin.getActive() == null) bin.setActive(true);

        return binRepository.save(bin);
    }

    public Bin getBinById(Long id) {
        return binRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
    }

    public List<Bin> getAllBins() {
        return binRepository.findAll();
    }

    public Bin updateBin(Long id, Bin updated) {
        Bin bin = getBinById(id);
        if (updated.getLatitude() != null) bin.setLatitude(updated.getLatitude());
        if (updated.getLongitude() != null) bin.setLongitude(updated.getLongitude());
        if (updated.getLocationDescription() != null) bin.setLocationDescription(updated.getLocationDescription());

        if (updated.getZone() != null && updated.getZone().getId() != null) {
            Zone zone = zoneRepository.findById(updated.getZone().getId())
                    .orElseThrow(() -> new BadRequestException("Zone not found"));
            bin.setZone(zone);
        }

        return binRepository.save(bin);
    }

    public void deactivateBin(Long id) {
        Bin bin = getBinById(id);
        bin.setActive(false);
        binRepository.save(bin);
    }
}
