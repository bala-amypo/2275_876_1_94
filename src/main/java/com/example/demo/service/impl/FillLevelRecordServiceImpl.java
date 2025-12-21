package com.example.demo.service.impl;

import com.example.demo.model.FillLevelRecord;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class FillLevelRecordServiceImpl implements FillLevelRecordService {

    @Override
    public FillLevelRecord createRecord(FillLevelRecord record) {
        return record;
    }

    @Override
    public List<FillLevelRecord> getRecentRecords(Long binId, int count) {
        return new ArrayList<>();
    }
}
