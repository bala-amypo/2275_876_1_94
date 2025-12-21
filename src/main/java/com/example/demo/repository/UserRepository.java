package com.example.demo.repository;

import com.example.demo.model.*;
import org.springframework.data.jpa.repository.*;
import java.util.*;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
