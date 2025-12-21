package com.example.demo.repository;

import com.example.demo.model.*;
import org.springframework.data.jpa.repository.*;
import java.util.*;

public interface FillLevelRecordRepository extends JpaRepository<FillLevelRecord, Long> {
    List<FillLevelRecord> findByBinOrderByRecordedAtDesc(Bin bin);
    Optional<FillLevelRecord> findTop1ByBinOrderByRecordedAtDesc(Bin bin);
}
