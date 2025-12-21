package com.example.demo.service.impl;

import com.example.demo.model.Bin;
import com.example.demo.model.FillLevelRecord;
import com.example.demo.repository.FillLevelRecordRepository;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FillLevelRecordServiceImpl implements FillLevelRecordService {

    private final FillLevelRecordRepository recordRepository;

    public FillLevelRecordServiceImpl(FillLevelRecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public FillLevelRecord save(FillLevelRecord record) {
        return recordRepository.save(record);
    }

    @Override
    public List<FillLevelRecord> getRecordsByBin(Bin bin) {
        return recordRepository.findByBinOrderByRecordedAtDesc(bin);
    }

    @Override
    public List<FillLevelRecord> getRecordsBetween(Bin bin, LocalDateTime start, LocalDateTime end) {
        return recordRepository.findByBinAndRecordedAtBetween(bin, start, end);
    }

    @Override
    public FillLevelRecord getLatestRecord(Bin bin) {
        return recordRepository.findTop1ByBinOrderByRecordedAtDesc(bin);
    }
}
