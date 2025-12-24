package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.FillLevelRecord;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.FillLevelRecordRepository;
import com.example.demo.service.FillLevelRecordService;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
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
        if (record.getBin() != null && record.getBin().getId() != null) {
            Bin bin = binRepository.findById(record.getBin().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
            
            if (!bin.getActive()) {
                throw new BadRequestException("Cannot create records for inactive bins");
            }
            record.setBin(bin);
        }
        
        if (record.getFillPercentage() == null || record.getFillPercentage() < 0 || record.getFillPercentage() > 100) {
            throw new BadRequestException("Fill percentage must be between 0 and 100");
        }
        
        if (record.getRecordedAt() != null && record.getRecordedAt().isAfter(LocalDateTime.now())) {
            throw new BadRequestException("Recorded time cannot be in the future");
        }
        
        return recordRepository.save(record);
    }
    
    @Override
    public List<FillLevelRecord> getRecordsForBin(Long binId) {
        Bin bin = binRepository.findById(binId)
            .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        return recordRepository.findByBinOrderByRecordedAtDesc(bin);
    }
    
    @Override
    public FillLevelRecord getRecordById(Long id) {
        return recordRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Fill level record not found"));
    }
    
    @Override
    public List<FillLevelRecord> getRecentRecords(Long binId, int limit) {
        Bin bin = binRepository.findById(binId)
            .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        return recordRepository.findByBinOrderByRecordedAtDesc(bin)
            .stream().limit(limit).toList();
    }
}