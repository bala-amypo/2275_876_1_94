package com.example.demo.repository;

import com.example.demo.model.Bin;
import com.example.demo.model.FillLevelRecord;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FillLevelRecordRepository extends JpaRepository<FillLevelRecord, Long> {

    // Fetch recent records for a bin with limit
    List<FillLevelRecord> findByBinOrderByCreatedAtDesc(Bin bin, Pageable pageable);
}
