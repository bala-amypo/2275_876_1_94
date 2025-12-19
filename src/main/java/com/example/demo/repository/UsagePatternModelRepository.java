package com.yourpackage.repository;

import com.yourpackage.entity.UsagePatternModel;
import com.yourpackage.entity.Bin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface UsagePatternModelRepository
        extends JpaRepository<UsagePatternModel, Long> {

    List<UsagePatternModel> findByBin(Bin bin);

    Optional<UsagePatternModel> findTopByBinOrderByLastUpdatedDesc();
}
