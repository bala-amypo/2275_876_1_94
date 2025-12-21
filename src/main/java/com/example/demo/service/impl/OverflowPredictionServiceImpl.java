package com.example.demo.service.impl;

import com.example.demo.model.Bin;
import com.example.demo.model.FillLevelRecord;
import com.example.demo.repository.FillLevelRecordRepository;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;

@Service
public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    private final FillLevelRecordRepository recordRepository;

    public OverflowPredictionServiceImpl(FillLevelRecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public boolean isOverflowExpected(Bin bin) {
        FillLevelRecord latestRecord = recordRepository.findTop1ByBinOrderByRecordedAtDesc(bin);
        if (latestRecord != null) {
            return latestRecord.getFillPercentage() >= bin.getCapacityLiters();
        }
        return false;
    }
}
