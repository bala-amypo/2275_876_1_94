package com.example.demo.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final Map<String, DemoUser> users = new HashMap<>();

    public CustomUserDetailsService() {
        // Add default admin user
        DemoUser admin = new DemoUser(1L, "Admin", "admin@city.com", "admin123", "ADMIN");
        users.put(admin.getEmail(), admin);
    }

    @Override
    public DemoUser loadUserByUsername(String email) {
        DemoUser user = users.get(email);
        if (user == null) throw new RuntimeException("User not found");
        return user;
    }

    public DemoUser getByEmail(String email) {
        return users.get(email);
    }

    public DemoUser registerUser(String name, String email, String password) {
        if (users.containsKey(email)) throw new RuntimeException("User with email already exists");
        DemoUser user = new DemoUser((long) (users.size() + 1), name, email, password, "USER");
        users.put(email, user);
        return user;
    }

    public static class DemoUser {
        private Long id;
        private String name;
        private String email;
        private String password;
        private String role;

        public DemoUser(Long id, String name, String email, String password, String role) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.password = password;
            this.role = role;
        }

        // Getters
        public Long getId() { return id; }
        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getPassword() { return password; }
        public String getRole() { return role; }
    }
}
