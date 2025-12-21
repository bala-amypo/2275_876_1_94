package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.FillLevelRecord;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.FillLevelRecordRepository;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FillLevelRecordServiceImpl implements FillLevelRecordService {

    @Autowired
    private FillLevelRecordRepository recordRepository;

    @Autowired
    private BinRepository binRepository;

    @Override
    public List<FillLevelRecord> getAllRecords() {
        return recordRepository.findAll();
    }

    @Override
    public FillLevelRecord getRecordById(Long id) {
        return recordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found with ID: " + id));
    }

    @Override
    public FillLevelRecord createRecord(FillLevelRecord record) {
        if (record.getBin() == null || record.getLevel() == null) {
            throw new BadRequestException("Bin or level cannot be null");
        }
        Bin bin = binRepository.findById(record.getBin().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        record.setBin(bin);
        return recordRepository.save(record);
    }

    @Override
    public FillLevelRecord updateRecord(Long id, FillLevelRecord recordDetails) {
        FillLevelRecord record = getRecordById(id);
        record.setLevel(recordDetails.getLevel());
        return recordRepository.save(record);
    }

    @Override
    public void deleteRecord(Long id) {
        FillLevelRecord record = getRecordById(id);
        recordRepository.delete(record);
    }
}
