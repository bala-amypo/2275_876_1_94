package com.example.demo.repository;

import com.example.demo.model.Bin;
import com.example.demo.model.FillLevelRecord;

import java.time.LocalDateTime;
import java.util.*;

public interface FillLevelRecordRepository {
    Optional<FillLevelRecord> findById(Long id);
    FillLevelRecord save(FillLevelRecord record);
    List<FillLevelRecord> findByBinOrderByRecordedAtDesc(Bin bin);
    Optional<FillLevelRecord> findTop1ByBinOrderByRecordedAtDesc(Bin bin);
    List<FillLevelRecord> findByBinAndRecordedAtBetween(Bin bin, LocalDateTime start, LocalDateTime end);
}
