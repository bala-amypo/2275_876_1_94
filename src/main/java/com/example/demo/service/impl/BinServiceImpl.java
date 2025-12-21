package com.example.demo.service.impl;

import com.example.demo.model.Bin;
import com.example.demo.repository.BinRepository;
import com.example.demo.service.BinService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BinServiceImpl implements BinService {

    private final BinRepository binRepository;

    public BinServiceImpl(BinRepository binRepository) {
        this.binRepository = binRepository;
    }

    @Override
    public List<Bin> findAll() {
        return binRepository.findAll();
    }

    @Override
    public Bin save(Bin bin) {
        return binRepository.save(bin);
    }

    @Override
    public Bin updateBin(Long id, Bin updated) {
        Optional<Bin> optionalBin = binRepository.findById(id);
        if (optionalBin.isPresent()) {
            Bin bin = optionalBin.get();
            bin.setIdentifier(updated.getIdentifier());
            bin.setCapacityLiters(updated.getCapacityLiters());
            bin.setZone(updated.getZone());
            bin.setActive(updated.isActive());
            bin.setLatitude(updated.getLatitude());
            bin.setLongitude(updated.getLongitude());
            bin.setLocationDescription(updated.getLocationDescription());
            return binRepository.save(bin);
        }
        return null;
    }

    @Override
    public Optional<Bin> findById(Long id) {
        return binRepository.findById(id);
    }
}
