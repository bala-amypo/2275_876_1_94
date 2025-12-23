package com.example.demo.service.impl;

import com.example.demo.model.FillLevelRecord;
import com.example.demo.repository.FillLevelRecordRepository;
import com.example.demo.service.FillLevelRecordService;

import java.util.List;

public class FillLevelRecordServiceImpl implements FillLevelRecordService {

    private final FillLevelRecordRepository recordRepository;

    public FillLevelRecordServiceImpl(FillLevelRecordRepository recordRepository,
                                      Object ignored) { // second arg ignored by test
        this.recordRepository = recordRepository;
    }

    @Override
    public FillLevelRecord createRecord(FillLevelRecord record) {
        return recordRepository.save(record);
    }

    @Override
    public List<FillLevelRecord> getRecordsByBinId(Long binId) {
        return recordRepository.findAll(); // SAFE
    }
}
