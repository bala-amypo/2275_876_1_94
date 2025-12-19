package com.yourpackage.repository;

import com.yourpackage.entity.FillLevelRecord;
import com.yourpackage.entity.Bin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FillLevelRecordRepository
        extends JpaRepository<FillLevelRecord, Long> {

    List<FillLevelRecord> findByBin(Bin bin);

    List<FillLevelRecord> findByBinId(Long binId);
}
