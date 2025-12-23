package com.example.demo.repository;

import com.example.demo.model.Bin;
import com.example.demo.model.UsagePatternModel;

import java.util.*;

public interface UsagePatternModelRepository {
    Optional<UsagePatternModel> findById(Long id);
    UsagePatternModel save(UsagePatternModel model);
    Optional<UsagePatternModel> findTop1ByBinOrderByLastUpdatedDesc(Bin bin);
}
