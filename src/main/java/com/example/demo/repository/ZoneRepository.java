package com.example.demo.repository;

import com.yourpackage.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ZoneRepository extends JpaRepository<Zone, Long> {

    Optional<Zone> findByZoneName(String zoneName);

    boolean existsByZoneName(String zoneName);
}
