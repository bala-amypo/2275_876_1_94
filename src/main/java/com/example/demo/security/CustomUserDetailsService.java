package com.example.demo.security;

import com.example.demo.model.DemoUser;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // 🔥 REQUIRED BY TESTS
    public DemoUser getByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
