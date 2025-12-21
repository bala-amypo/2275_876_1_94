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
        if (bin.getCapacityLiters() == null || bin.getCapacityLiters() <= 0) {
            throw new BadRequestException("Bin capacity must be greater than 0");
        }

        Zone zone = zoneRepository.findById(bin.getZone().getId())
                .orElseThrow(() -> new BadRequestException("Zone not found"));

        bin.setZone(zone);
        bin.setActive(true);
        bin.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return binRepository.save(bin);
    }

    @Override
    public Bin updateBin(Long id, Bin binDetails) {
        Bin bin = getBinById(id);

        bin.setIdentifier(binDetails.getIdentifier());
        bin.setLocationDescription(binDetails.getLocationDescription());
        bin.setLatitude(binDetails.getLatitude());
        bin.setLongitude(binDetails.getLongitude());
        if (binDetails.getCapacityLiters() != null && binDetails.getCapacityLiters() > 0) {
            bin.setCapacityLiters(binDetails.getCapacityLiters());
        } else {
            throw new BadRequestException("Bin capacity must be greater than 0");
        }
        bin.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return binRepository.save(bin);
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
        binRepository.save(bin);
    }
}
