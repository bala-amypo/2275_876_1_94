package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.Zone;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.BinService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class BinServiceImpl implements BinService {

    private final BinRepository binRepository;
    private final ZoneRepository zoneRepository;

    public BinServiceImpl(BinRepository binRepository, ZoneRepository zoneRepository) {
        this.binRepository = binRepository;
        this.zoneRepository = zoneRepository;
    }

    @Override
    public Bin createBin(Bin bin) {

        if (bin.getIdentifier() == null || bin.getIdentifier().trim().isEmpty()) {
            throw new BadRequestException("identifier is required");
        }

        if (bin.getCapacityLiters() == null || bin.getCapacityLiters() <= 0) {
            throw new BadRequestException("capacity must be greater than 0");
        }

        binRepository.findByIdentifier(bin.getIdentifier())
                .ifPresent(b -> {
                    throw new BadRequestException("Bin identifier already exists");
                });

        if (bin.getZone() != null && bin.getZone().getId() != null) {
            Zone zone = zoneRepository.findById(bin.getZone().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
            bin.setZone(zone);
        }

        Timestamp now = new Timestamp(System.currentTimeMillis());
        bin.setCreatedAt(now);
        bin.setUpdatedAt(now);
        bin.setActive(true);

        return binRepository.save(bin);
    }

    @Override
    public Bin updateBin(Long id, Bin bin) {

        Bin existing = getBinById(id);

        if (bin.getCapacityLiters() != null && bin.getCapacityLiters() <= 0) {
            throw new BadRequestException("capacity must be greater than 0");
        }

        if (bin.getIdentifier() != null) {
            existing.setIdentifier(bin.getIdentifier());
        }

        if (bin.getLocationDescription() != null) {
            existing.setLocationDescription(bin.getLocationDescription());
        }

        if (bin.getLatitude() != null) {
            existing.setLatitude(bin.getLatitude());
        }

        if (bin.getLongitude() != null) {
            existing.setLongitude(bin.getLongitude());
        }

        if (bin.getCapacityLiters() != null) {
            existing.setCapacityLiters(bin.getCapacityLiters());
        }

        if (bin.getZone() != null && bin.getZone().getId() != null) {
            Zone zone = zoneRepository.findById(bin.getZone().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
            existing.setZone(zone);
        }

        existing.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return binRepository.save(existing);
    }

    @Override
    public Bin getBinById(Long id) {
        return binRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found with id " + id));
    }

    @Override
    public List<Bin> getAllBins() {
        return binRepository.findAll();
    }

    @Override
    public void deactivateBin(Long id) {
        Bin bin = getBinById(id);
        bin.setActive(false);
        bin.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        binRepository.save(bin);
    }
}
