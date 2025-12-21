package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.FillLevelRecord;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.FillLevelRecordRepository;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class FillLevelRecordServiceImpl implements FillLevelRecordService {

    private final FillLevelRecordRepository recordRepository;
    private final BinRepository binRepository;

    public FillLevelRecordServiceImpl(FillLevelRecordRepository recordRepository,
                                      BinRepository binRepository) {
        this.recordRepository = recordRepository;
        this.binRepository = binRepository;
    }

    @Override
    public FillLevelRecord createRecord(FillLevelRecord record) {

        if (record.getBin() == null || record.getBin().getId() == null) {
            throw new BadRequestException("Bin id is required");
        }

        Bin bin = binRepository.findById(record.getBin().getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Bin not found with id: " + record.getBin().getId()));

        if (!Boolean.TRUE.equals(bin.getActive())) {
            throw new BadRequestException("Cannot add record to inactive bin");
        }

        if (record.getFillPercentage() == null ||
                record.getFillPercentage() < 0 ||
                record.getFillPercentage() > 100) {
            throw new BadRequestException("fillPercentage must be between 0 and 100");
        }

        // Auto set recordedAt if not provided
        if (record.getRecordedAt() == null) {
            record.setRecordedAt(new Timestamp(System.currentTimeMillis()));
        }

        // Prevent future timestamps
        if (record.getRecordedAt().after(new Timestamp(System.currentTimeMillis()))) {
            throw new BadRequestException("recordedAt cannot be in the future");
        }

        // Auto set isWeekend if missing
        if (record.getIsWeekend() == null) {
            record.setIsWeekend(false);
        }

        record.setBin(bin);
        return recordRepository.save(record);
    }

    @Override
    public FillLevelRecord getRecordById(Long id) {
        return recordRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("FillLevelRecord not found with id: " + id));
    }

    @Override
    public List<FillLevelRecord> getRecordsForBin(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Bin not found with id: " + binId));
        return recordRepository.findByBinOrderByRecordedAtDesc(bin);
    }

    @Override
    public List<FillLevelRecord> getRecentRecords(Long binId, int limit) {
        List<FillLevelRecord> records = getRecordsForBin(binId);
        return records.subList(0, Math.min(records.size(), limit));
    }
}
