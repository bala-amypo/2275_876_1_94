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
import java.util.Optional;

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
        if (bin.getCapacityLiters() == null || bin.getCapacityLiters() <= 0) {
            throw new BadRequestException("Bin capacity must be greater than 0");
        }

        if (bin.getIdentifier() == null || bin.getIdentifier().isEmpty()) {
            throw new BadRequestException("Bin identifier is required");
        }

        if (binRepository.findByIdentifier(bin.getIdentifier()).isPresent()) {
            throw new BadRequestException("Bin identifier must be unique");
        }

        // Validate Zone
        Zone zone = zoneRepository.findById(bin.getZone().getId())
                .orElseThrow(() -> new BadRequestException("Zone not found"));

        bin.setZone(zone);
        bin.setActive(bin.getActive() != null ? bin.getActive() : true);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        bin.setCreatedAt(now);
        bin.setUpdatedAt(now);

        return binRepository.save(bin);
    }

    @Override
    public Bin updateBin(Long id, Bin binDetails) {
        Bin bin = getBinById(id);

        if (binDetails.getCapacityLiters() != null && binDetails.getCapacityLiters() <= 0) {
            throw new BadRequestException("Bin capacity must be greater than 0");
        }

        if (binDetails.getCapacityLiters() != null) bin.setCapacityLiters(binDetails.getCapacityLiters());
        if (binDetails.getLocationDescription() != null) bin.setLocationDescription(binDetails.getLocationDescription());
        if (binDetails.getLatitude() != null) bin.setLatitude(binDetails.getLatitude());
        if (binDetails.getLongitude() != null) bin.setLongitude(binDetails.getLongitude());
        if (binDetails.getZone() != null) {
            Zone zone = zoneRepository.findById(binDetails.getZone().getId())
                    .orElseThrow(() -> new BadRequestException("Zone not found"));
            bin.setZone(zone);
        }

        bin.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        return binRepository.save(bin);
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
        Bin bin = getBinById(id);
        bin.setActive(false);
        bin.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        binRepository.save(bin);
    }
}
