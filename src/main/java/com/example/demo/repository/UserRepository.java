package com.example.demo.repository;

import com.example.demo.model.DemoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<DemoUser, Long> {

    Optional<DemoUser> findByEmail(String email);
}
