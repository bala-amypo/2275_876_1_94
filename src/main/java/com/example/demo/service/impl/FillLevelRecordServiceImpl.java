package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.FillLevelRecordService;
import com.example.demo.exception.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

public class FillLevelRecordServiceImpl implements FillLevelRecordService {

    private final FillLevelRecordRepository recordRepository;
    private final BinRepository binRepository;

    public FillLevelRecordServiceImpl(FillLevelRecordRepository recordRepository,
                                      BinRepository binRepository) {
        this.recordRepository = recordRepository;
        this.binRepository = binRepository;
    }

    public FillLevelRecord createRecord(FillLevelRecord record) {
        Bin bin = binRepository.findById(record.getBin().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));

        if (!bin.getActive())
            throw new BadRequestException("Bin inactive");

        if (record.getFillPercentage() < 0 || record.getFillPercentage() > 100)
            throw new BadRequestException("fillPercentage");

        if (record.getRecordedAt().after(new Timestamp(System.currentTimeMillis())))
            throw new BadRequestException("future timestamp");

        record.setBin(bin);
        return recordRepository.save(record);
    }

    public FillLevelRecord getRecordById(Long id) {
        return recordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));
    }

    public List<FillLevelRecord> getRecordsForBin(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        return recordRepository.findByBinOrderByRecordedAtDesc(bin);
    }

    public List<FillLevelRecord> getRecentRecords(Long binId, int limit) {
        List<FillLevelRecord> list = getRecordsForBin(binId);
        return list.subList(0, Math.min(limit, list.size()));
    }
}
