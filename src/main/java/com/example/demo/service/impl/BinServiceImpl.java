package com.example.demo.service.impl;

import com.example.demo.exception.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;

import java.util.List;

public class BinServiceImpl {

    private final BinRepository binRepository;
    private final ZoneRepository zoneRepository;

    public BinServiceImpl(BinRepository binRepository, ZoneRepository zoneRepository) {
        this.binRepository = binRepository;
        this.zoneRepository = zoneRepository;
    }

    public Bin createBin(Bin bin) {
        if (bin.getCapacityLiters() == null || bin.getCapacityLiters() <= 0)
            throw new BadRequestException("Invalid capacity");

        Zone zone = zoneRepository.findById(bin.getZone().getId())
                .orElseThrow(() -> new BadRequestException("Zone not found"));

        if (!zone.getActive())
            throw new BadRequestException("Zone inactive");

        if (bin.getActive() == null) bin.setActive(true);
        bin.setZone(zone);
        return binRepository.save(bin);
    }

    public Bin getBinById(Long id) {
        return binRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
    }

    public List<Bin> getAllBins() {
        return binRepository.findAll();
    }

    public Bin updateBin(Long id, Bin update) {
        Bin bin = getBinById(id);
        if (update.getLocationDescription() != null)
            bin.setLocationDescription(update.getLocationDescription());
        return binRepository.save(bin);
    }

    public void deactivateBin(Long id) {
        Bin bin = getBinById(id);
        bin.setActive(false);
        binRepository.save(bin);
    }
}
