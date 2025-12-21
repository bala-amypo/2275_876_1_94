package com.example.demo.repository;

import com.example.demo.model.Bin;
import com.example.demo.model.FillLevelRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FillLevelRecordRepository extends JpaRepository<FillLevelRecord, Long> {

    // Custom query to fetch top N records for a bin, ordered by generatedAt descending
    @Query("SELECT r FROM FillLevelRecord r WHERE r.bin = :bin ORDER BY r.generatedAt DESC")
    List<FillLevelRecord> findTopNByBinOrderByGeneratedAtDesc(@Param("bin") Bin bin, Pageable pageable);

}
