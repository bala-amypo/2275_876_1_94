package com.example.demo.service.impl;

import com.example.demo.model.Bin;
import com.example.demo.repository.BinRepository;
import com.example.demo.service.BinService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BinServiceImpl implements BinService {

    private final BinRepository binRepository;

    public BinServiceImpl(BinRepository binRepository) {
        this.binRepository = binRepository;
    }

    @Override
    public Bin createBin(Bin bin) {
        bin.setActive(true);
        return binRepository.save(bin);
    }

    @Override
    public Bin updateBin(Long id, Bin updatedBin) {
        Bin existing = getBinById(id);

        existing.setIdentifier(updatedBin.getIdentifier());
        existing.setLocationDescription(updatedBin.getLocationDescription());
        existing.setLatitude(updatedBin.getLatitude());
        existing.setLongitude(updatedBin.getLongitude());
        existing.setCapacityLiters(updatedBin.getCapacityLiters());

        return binRepository.save(existing);
    }

    @Override
    public Bin getBinById(Long id) {
        return binRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bin not found with id " + id));
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
