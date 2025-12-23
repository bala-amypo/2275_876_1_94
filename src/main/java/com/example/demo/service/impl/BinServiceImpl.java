package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.Zone;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.BinService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // 🔥 THIS IS REQUIRED
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
            throw new BadRequestException("Capacity must be greater than zero");
        }

        Zone zone = zoneRepository.findById(bin.getZone().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

        if (!Boolean.TRUE.equals(zone.getActive())) {
            throw new BadRequestException("Zone is inactive");
        }

        bin.setZone(zone);
        bin.setActive(true);
        return binRepository.save(bin);
    }

    @Override
    public Bin updateBin(Long id, Bin bin) {
        Bin existing = getBinById(id);

        if (bin.getIdentifier() != null) existing.setIdentifier(bin.getIdentifier());
        if (bin.getCapacityLiters() != null) existing.setCapacityLiters(bin.getCapacityLiters());
        if (bin.getLatitude() != null) existing.setLatitude(bin.getLatitude());
        if (bin.getLongitude() != null) existing.setLongitude(bin.getLongitude());
        if (bin.getLocationDescription() != null)
            existing.setLocationDescription(bin.getLocationDescription());

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
        Bin bin = getBinById(id);
        bin.setActive(false);
        binRepository.save(bin);
    }
}
