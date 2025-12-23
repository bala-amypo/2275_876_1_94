package com.example.demo.service.impl;

import com.example.demo.model.FillLevelRecord;
import com.example.demo.repository.FillLevelRecordRepository;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FillLevelRecordServiceImpl implements FillLevelRecordService {

    private final FillLevelRecordRepository repository;

    public FillLevelRecordServiceImpl(FillLevelRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public FillLevelRecord createRecord(FillLevelRecord record) {
        return repository.save(record);
    }

    @Override
    public Optional<FillLevelRecord> getRecordById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<FillLevelRecord> getRecordsByBinId(Long binId) {
        return List.of();
    }
}
