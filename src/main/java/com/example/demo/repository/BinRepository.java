package com.example.demo.repository;

import com.yourpackage.entity.Bin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BinRepository extends JpaRepository<Bin, Long> {

    Optional<Bin> findByIdentifier(String identifier);

    boolean existsByIdentifier(String identifier);
}
