package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.Zone;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.BinService;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

public class BinServiceImpl implements BinService {

    private final BinRepository binRepository;
    private final ZoneRepository zoneRepository;

    // Constructor injection
    public BinServiceImpl(BinRepository binRepository, ZoneRepository zoneRepository) {
        this.binRepository = binRepository;
        this.zoneRepository = zoneRepository;
    }

    @Override
    public Bin createBin(Bin bin) {
        if (bin.getCapacityLiters() == null || bin.getCapacityLiters() <= 0) {
            throw new BadRequestException("Bin capacity must be greater than 0");
        }

        if (bin.getIdentifier() == null || bin.getIdentifier().isEmpty()) {
            throw new BadRequestException("Bin identifier is required");
        }

        // Check uniqueness
        Optional<Bin> existing = binRepository.findByIdentifier(bin.getIdentifier());
        if (existing.isPresent()) {
            throw new BadRequestException("Bin identifier must be unique");
        }

        // Validate zone
        if (bin.getZone() != null) {
            Zone zone = zoneRepository.findById(bin.getZone().getId())
                    .orElseThrow(() -> new BadRequestException("Zone not found"));
            bin.setZone(zone);
        }

        bin.setActive(bin.getActive() != null ? bin.getActive() : true);
        Timestamp now = Timestamp.from(Instant.now());
        bin.setCreatedAt(now);
        bin.setUpdatedAt(now);

        return binRepository.save(bin);
    }

    @Override
    public Bin updateBin(Long id, Bin bin) {
        Bin existing = getBinById(id);

        if (bin.getCapacityLiters() != null && bin.getCapacityLiters() <= 0) {
            throw new BadRequestException("Bin capacity must be greater than 0");
        }

        if (bin.getIdentifier() != null && !bin.getIdentifier().equals(existing.getIdentifier())) {
            binRepository.findByIdentifier(bin.getIdentifier()).ifPresent(b -> {
                throw new BadRequestException("Bin identifier must be unique");
            });
            existing.setIdentifier(bin.getIdentifier());
        }

        if (bin.getLocationDescription() != null) existing.setLocationDescription(bin.getLocationDescription());
        if (bin.getLatitude() != null) existing.setLatitude(bin.getLatitude());
        if (bin.getLongitude() != null) existing.setLongitude(bin.getLongitude());
        if (bin.getCapacityLiters() != null) existing.setCapacityLiters(bin.getCapacityLiters());

        if (bin.getZone() != null) {
            Zone zone = zoneRepository.findById(bin.getZone().getId())
                    .orElseThrow(() -> new BadRequestException("Zone not found"));
            existing.setZone(zone);
        }

        existing.setUpdatedAt(Timestamp.from(Instant.now()));

        return binRepository.save(existing);
    }

    @Override
    public Bin getBinById(Long id) {
        return binRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found with id: " + id));
    }

    @Override
    public List<Bin> getAllBins() {
        return binRepository.findAll();
    }

    @Override
    public void deactivateBin(Long id) {
        Bin existing = getBinById(id);
        existing.setActive(false);
        existing.setUpdatedAt(Timestamp.from(Instant.now()));
        binRepository.save(existing);
    }
}
