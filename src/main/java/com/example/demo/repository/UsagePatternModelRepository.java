package com.example.demo.repository;

import com.example.demo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsagePatternModelRepository
        extends JpaRepository<UsagePatternModel, Long> {

    UsagePatternModel findTop1ByBinOrderByLastUpdatedDesc(Bin bin);
}
