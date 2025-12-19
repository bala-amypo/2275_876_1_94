package com.example.demo.repository;

import com.example.demo.model.DemoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<DemoUser, Long> {

    Optional<DemoUser> findByEmail(String email);
}
