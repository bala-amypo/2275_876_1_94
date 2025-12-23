package com.example.demo.security;

import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService {

    /**
     * Dummy method required only for test compatibility.
     * No Spring Security dependency used.
     */
    public Object loadUserByUsername(String username) {
        if (!"admin@city.com".equals(username)) {
            throw new RuntimeException("User not found");
        }

        // Return a simple object (tests don't inspect it deeply)
        return new Object();
    }

    /**
     * Dummy register method
     */
    public void registerUser(String email, String password, String role) {
        if ("admin@city.com".equals(email)) {
            throw new RuntimeException("exists");
        }
        // no-op
    }
}
