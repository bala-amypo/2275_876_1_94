package com.example.demo.service.impl;

import com.example.demo.model.Bin;
import com.example.demo.model.FillLevelRecord;
import com.example.demo.repository.FillLevelRecordRepository;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FillLevelRecordServiceImpl implements FillLevelRecordService {

    private final FillLevelRecordRepository recordRepository;

    public FillLevelRecordServiceImpl(FillLevelRecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public List<FillLevelRecord> getRecentRecords(Long binId, int n) {
        Bin bin = new Bin();
        bin.setId(binId); // minimal Bin object for query
        return recordRepository.findByBinOrderByCreatedAtDesc(bin, PageRequest.of(0, n));
    }

    @Override
    public FillLevelRecord createRecord(FillLevelRecord record) {
        record.setCreatedAt(java.time.LocalDateTime.now());
        return recordRepository.save(record);
    }
}
