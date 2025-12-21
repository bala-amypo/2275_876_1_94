package com.example.demo.service.impl;

import com.example.demo.model.Bin;
import com.example.demo.repository.BinRepository;
import com.example.demo.service.BinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BinServiceImpl implements BinService {

    @Autowired
    private BinRepository binRepository;

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
    public Bin updateBin(Long id, Bin bin) {
        Bin existing = binRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(bin.getName());
            existing.setActive(bin.isActive());
            return binRepository.save(existing);
        }
        return null;
    }

    @Override
    public void deactivateBin(Long id) {
        Bin bin = binRepository.findById(id).orElse(null);
        if (bin != null) {
            bin.setActive(false);
            binRepository.save(bin);
        }
    }
}
