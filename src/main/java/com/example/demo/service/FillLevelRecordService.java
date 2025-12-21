package com.example.demo.service;

import com.example.demo.model.*;
import java.util.List;
public interface FillLevelRecordService {
    List<FillLevelRecord> getRecentRecords(Long binId, int count);
}