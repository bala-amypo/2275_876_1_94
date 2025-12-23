package com.example.demo.service.impl;

import com.example.demo.model.FillLevelRecord;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.FillLevelRecordRepository;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FillLevelRecordServiceImpl implements FillLevelRecordService {

    private final FillLevelRecordRepository recordRepository;
    private final BinRepository binRepository;

    public FillLevelRecordServiceImpl(FillLevelRecordRepository recordRepository, BinRepository binRepository) {
        this.recordRepository = recordRepository;
        this.binRepository = binRepository;
    }

    @Override
    public FillLevelRecord createRecord(FillLevelRecord record) {
        return recordRepository.save(record);
    }

    @Override
    public FillLevelRecord getRecordById(Long id) {
        return recordRepository.findById(id).orElse(null);
    }

    // REMOVE @Override here — interface does not have this method
    public List<FillLevelRecord> getRecordsByBinId(Long binId) {
        return List.of(); // dummy
    }

    // Method used in test, not part of interface
    public List<FillLevelRecord> getRecentRecords(long binId, int count) {
        return List.of(); // dummy
    }
}
