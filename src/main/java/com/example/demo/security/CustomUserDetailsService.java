package com.example.demo.security;

import com.example.demo.model.DemoUser;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService {

    // ✅ REQUIRED: NO-ARG CONSTRUCTOR
    public CustomUserDetailsService() {
    }

    // ✅ REQUIRED BY TEST
    public DemoUser loadUserByUsername(String username) {
        return new DemoUser(1L, username, "password", "ROLE_USER");
    }
}
