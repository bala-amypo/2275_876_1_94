package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.FillLevelRecord;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.FillLevelRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FillLevelRecordServiceImpl {

    private final FillLevelRecordRepository recordRepository;
    private final BinRepository binRepository;

    public FillLevelRecordServiceImpl(FillLevelRecordRepository recordRepository, BinRepository binRepository) {
        this.recordRepository = recordRepository;
        this.binRepository = binRepository;
    }

    public FillLevelRecord createRecord(FillLevelRecord record) {
        Bin bin = record.getBin();
        if (bin == null || bin.getId() == null) {
            throw new BadRequestException("Bin required");
        }

        Bin fetchedBin = binRepository.findById(bin.getId())
                .orElseThrow(() -> new BadRequestException("Bin not found"));

        if (!fetchedBin.getActive()) {
            throw new BadRequestException("Bin inactive");
        }

        if (record.getRecordedAt() != null && record.getRecordedAt().isAfter(LocalDateTime.now())) {
            throw new BadRequestException("RecordedAt cannot be in the future");
        }

        record.setBin(fetchedBin);
        return recordRepository.save(record);
    }

    public FillLevelRecord getRecordById(Long id) {
        return recordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fill record not found with id " + id));
    }

    public List<FillLevelRecord> getRecentRecords(Long binId, int limit) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        List<FillLevelRecord> allRecords = recordRepository.findByBinOrderByRecordedAtDesc(bin);
        return allRecords.subList(0, Math.min(limit, allRecords.size()));
    }
}
