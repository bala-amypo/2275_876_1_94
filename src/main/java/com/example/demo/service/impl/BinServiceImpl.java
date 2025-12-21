package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.Zone;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.BinService;
import java.sql.Timestamp;
import java.util.List;

public class BinServiceImpl implements BinService {

    private final BinRepository binRepository;
    private final ZoneRepository zoneRepository;

    public BinServiceImpl(BinRepository binRepository, ZoneRepository zoneRepository) {
        this.binRepository = binRepository;
        this.zoneRepository = zoneRepository;
    }

    @Override
    public Bin createBin(Bin bin) {
        if (bin.getCapacityLiters() == null || bin.getCapacityLiters() <= 0) {
            throw new BadRequestException("Bin capacity must be greater than 0");
        }

        Zone zone = zoneRepository.findById(bin.getZone().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

        bin.setZone(zone);
        bin.setActive(bin.getActive() != null ? bin.getActive() : true);
        bin.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        bin.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        return binRepository.save(bin);
    }

    @Override
    public Bin updateBin(Long id, Bin updatedBin) {
        Bin existing = binRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));

        if (updatedBin.getCapacityLiters() != null && updatedBin.getCapacityLiters() <= 0) {
            throw new BadRequestException("Bin capacity must be greater than 0");
        }

        if (updatedBin.getIdentifier() != null) existing.setIdentifier(updatedBin.getIdentifier());
        if (updatedBin.getLocationDescription() != null) existing.setLocationDescription(updatedBin.getLocationDescription());
        if (updatedBin.getLatitude() != null) existing.setLatitude(updatedBin.getLatitude());
        if (updatedBin.getLongitude() != null) existing.setLongitude(updatedBin.getLongitude());
        if (updatedBin.getCapacityLiters() != null) existing.setCapacityLiters(updatedBin.getCapacityLiters());
        existing.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        return binRepository.save(existing);
    }

    @Override
    public Bin getBinById(Long id) {
        return binRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
    }

    @Override
    public List<Bin> getAllBins() {
        return binRepository.findAll();
    }

    @Override
    public void deactivateBin(Long id) {
        Bin bin = binRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        bin.setActive(false);
        bin.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        binRepository.save(bin);
    }
}
