package com.example.demo.repository;

import com.example.demo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BinRepository extends JpaRepository<Bin, Long> {
    List<Bin> findByZoneId(Long zoneId);
}