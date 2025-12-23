package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.Bin;
import com.example.demo.model.FillLevelRecord;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.FillLevelRecordRepository;

import java.time.LocalDateTime;
import java.util.List;

public class FillLevelRecordServiceImpl {
    private final FillLevelRecordRepository recordRepository;
    private final BinRepository binRepository;

    public FillLevelRecordServiceImpl(FillLevelRecordRepository recordRepository, BinRepository binRepository) {
        this.recordRepository = recordRepository;
        this.binRepository = binRepository;
    }

    public FillLevelRecord createRecord(FillLevelRecord record) {
        if (record.getBin() == null || record.getBin().getId() == null) {
            throw new BadRequestException("Bin required");
        }
        Bin bin = binRepository.findById(record.getBin().getId()).orElseThrow(() -> new BadRequestException("Bin not found"));
        if (Boolean.FALSE.equals(bin.getActive())) {
            throw new BadRequestException("Inactive bin");
        }
        if (record.getRecordedAt() == null) record.setRecordedAt(LocalDateTime.now());
        if (record.getRecordedAt().isAfter(LocalDateTime.now())) {
            throw new BadRequestException("recordedAt cannot be in the future");
        }
        record.setBin(bin);
        return recordRepository.save(record);
    }

    public FillLevelRecord getRecordById(Long id) {
        return recordRepository.findById(id).orElseThrow(() -> new BadRequestException("Record not found"));
    }

    public List<FillLevelRecord> getRecentRecords(Long binId, int limit) {
        Bin bin = binRepository.findById(binId).orElseThrow(() -> new BadRequestException("Bin not found"));
        List<FillLevelRecord> all = recordRepository.findByBinOrderByRecordedAtDesc(bin);
        return all.subList(0, Math.min(limit, all.size()));
    }
}
