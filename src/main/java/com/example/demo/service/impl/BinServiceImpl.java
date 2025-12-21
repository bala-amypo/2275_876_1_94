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
    public List<Bin> getAllBins() {
        return binRepository.findAll();
    }

    @Override
    public Bin getBinById(Long id) {
        return binRepository.findById(id).orElse(null);
    }

    @Override
    public Bin createBin(Bin bin) {
        return binRepository.save(bin);
    }

    @Override
    public Bin updateBin(Long id, Bin binDetails) {
        Bin bin = binRepository.findById(id).orElseThrow(() -> new RuntimeException("Bin not found"));
        bin.setName(binDetails.getName());
        bin.setLocation(binDetails.getLocation());
        return binRepository.save(bin);
    }

    @Override
    public void deactivateBin(Long id) {
        Bin bin = binRepository.findById(id).orElseThrow(() -> new RuntimeException("Bin not found"));
        bin.setActive(false);
        binRepository.save(bin);
    }
}
