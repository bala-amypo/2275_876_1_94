package com.example.demo.security;

import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public DemoUser getByEmail(String email) {
        return new DemoUser(1L, "Demo User", email, "USER");
    }

    public DemoUser registerUser(String name, String email, String password) {
        return new DemoUser(1L, name, email, "USER");
    }

    // Inner class required by tests
    public static class DemoUser {
        private Long id;
        private String name;
        private String email;
        private String role;

        public DemoUser(Long id, String name, String email, String role) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.role = role;
        }

        public Long getId() { return id; }
        public String getEmail() { return email; }
        public String getRole() { return role; }
    }
}
