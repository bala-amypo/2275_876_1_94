package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.FillLevelRecord;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.FillLevelRecordRepository;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class FillLevelRecordServiceImpl implements FillLevelRecordService {

    @Autowired
    private BinRepository binRepository;

    @Autowired
    private FillLevelRecordRepository recordRepository;

    @Override
    public List<FillLevelRecord> getRecentRecords(Long binId, int count) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        return recordRepository.findTopNByBinOrderByGeneratedAtDesc(bin, count);
    }
}
