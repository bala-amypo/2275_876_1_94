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
    public List<Bin> getAllBins() {
        return binRepository.findAll();
    }

    @Override
    public Bin getBinById(Long id) {
        Optional<Bin> bin = binRepository.findById(id);
        return bin.orElse(null);
    }

    @Override
    public Bin createBin(Bin bin) {
        return binRepository.save(bin);
    }

    @Override
    public Bin updateBin(Long id, Bin binDetails) {
        return binRepository.findById(id).map(bin -> {
            bin.setName(binDetails.getName());
            bin.setLocation(binDetails.getLocation());
            bin.setZone(binDetails.getZone());
            return binRepository.save(bin);
        }).orElse(null);
    }

    @Override
    public boolean deactivateBin(Long id) {
        return binRepository.findById(id).map(bin -> {
            bin.setActive(false);
            binRepository.save(bin);
            return true;
        }).orElse(false);
    }
}
