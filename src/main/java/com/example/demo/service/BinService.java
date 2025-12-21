package com.example.demo.service;

import com.example.demo.model.Bin;
import java.util.List;

public interface BinService {
    List<Bin> getAllBins();
    Bin getBinById(Long id);
    Bin createBin(Bin bin);
    Bin updateBin(Long id, Bin bin);
    void deleteBin(Long id);
}
