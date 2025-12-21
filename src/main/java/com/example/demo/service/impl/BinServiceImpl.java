package com.example.demo.service.impl;

import com.example.demo.model.Bin;
import com.example.demo.service.BinService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BinServiceImpl implements BinService {

    @Override
    public List<Bin> getAllBins() {
        return new ArrayList<>();
    }

    @Override
    public Bin getBinById(Long id) {
        return new Bin();
    }

    @Override
    public Bin createBin(Bin bin) {
        return bin;
    }

    @Override
    public Bin updateBin(Long id, Bin bin) {
        return bin;
    }

    @Override
    public void deleteBin(Long id) {
        // TODO: Delete bin
    }

    @Override
    public void deactivateBin(Long id) {
        // TODO: Add deactivate logic
    }
}
