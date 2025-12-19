package com.example.demo.security;

import com.example.demo.model.DemoUser;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService {

    public CustomUserDetailsService() {}

    public DemoUser loadUserByUsername(String username) {
        return new DemoUser(
                1L,
                "Test User",
                "test@gmail.com",
                username,
                "password",
                "ROLE_USER"
        );
    }

    public DemoUser registerUser(String name, String email, String password) {
        return new DemoUser(
                1L,
                name,
                email,
                email,
                password,
                "ROLE_USER"
        );
    }
}
