package com.example.demo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Simple in-memory user service for testing/demo purposes.
 * Registers users and returns UserDetails for Spring Security.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final Map<String, DemoUser> users = new HashMap<>();

    public CustomUserDetailsService() {
        // Default admin user
        DemoUser admin = new DemoUser(1L, "Admin", "admin@city.com", "admin123", "ADMIN");
        users.put(admin.getEmail(), admin);
    }

    public DemoUser registerUser(String name, String email, String password) {
        if (users.containsKey(email)) {
            throw new RuntimeException("User with email " + email + " already exists");
        }
        DemoUser user = new DemoUser((long) (users.size() + 1), name, email, password, "USER");
        users.put(email, user);
        return user;
    }

    public DemoUser getByEmail(String email) {
        DemoUser user = users.get(email);
        if (user == null) throw new UsernameNotFoundException("User not found");
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DemoUser user = users.get(username);
        if (user == null) throw new UsernameNotFoundException("User not found");
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }

    // -------------------------------------
    // Inner class representing a simple user
    // -------------------------------------
    public static class DemoUser {
        private Long id;
        private String name;
        private String email;
        private String password;
        private String role;

        public DemoUser() { }

        public DemoUser(Long id, String name, String email, String password, String role) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.password = password;
            this.role = role;
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
    }
}
