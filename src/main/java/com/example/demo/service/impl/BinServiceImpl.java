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
        if (bin.getCapacityLiters() <= 0) {
            throw new BadRequestException("Bin capacity must be greater than 0");
        }
        if (binRepository.findByIdentifier(bin.getIdentifier()).isPresent()) {
            throw new BadRequestException("Bin identifier must be unique");
        }
        Zone zone = zoneRepository.findById(bin.getZone().getId())
                .orElseThrow(() -> new BadRequestException("Zone not found"));
        bin.setZone(zone);
        bin.setActive(true);
        bin.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        bin.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return binRepository.save(bin);
    }

    @Override
    public Bin updateBin(Long id, Bin bin) {
        Bin existing = getBinById(id);
        if (bin.getCapacityLiters() != null && bin.getCapacityLiters() <= 0) {
            throw new BadRequestException("Bin capacity must be greater than 0");
        }
        existing.setCapacityLiters(bin.getCapacityLiters() != null ? bin.getCapacityLiters() : existing.getCapacityLiters());
        existing.setLocationDescription(bin.getLocationDescription() != null ? bin.getLocationDescription() : existing.getLocationDescription());
        existing.setLatitude(bin.getLatitude() != null ? bin.getLatitude() : existing.getLatitude());
        existing.setLongitude(bin.getLongitude() != null ? bin.getLongitude() : existing.getLongitude());
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
