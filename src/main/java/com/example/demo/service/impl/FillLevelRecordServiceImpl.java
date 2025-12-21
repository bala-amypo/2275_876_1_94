package com.example.demo.service.impl;

import com.example.demo.model.Bin;
import com.example.demo.model.FillLevelRecord;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.FillLevelRecordRepository;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FillLevelRecordServiceImpl implements FillLevelRecordService {

    @Autowired
    private FillLevelRecordRepository fillLevelRecordRepository;

    @Autowired
    private BinRepository binRepository;

    @Override
    public FillLevelRecord createRecord(FillLevelRecord record) {
        record.setGeneratedAt(java.time.LocalDateTime.now());  // set timestamp automatically
        return fillLevelRecordRepository.save(record);
    }

    @Override
    public List<FillLevelRecord> getRecentRecords(Long binId, int n) {
        Bin bin = binRepository.findById(binId).orElse(null);
        if (bin == null) return List.of(); // Return empty if bin not found
        return fillLevelRecordRepository.findByBinOrderByGeneratedAtDesc(bin, PageRequest.of(0, n));
    }
}
