package com.example.demo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final Map<String, DemoUser> userStore = new HashMap<>();

    public CustomUserDetailsService() {
        // default admin user
        DemoUser admin = new DemoUser(1L, "Admin", "admin@city.com", "admin123", "ADMIN");
        userStore.put(admin.getEmail(), admin);
    }

    public DemoUser registerUser(String name, String email, String password) {
        if (userStore.containsKey(email)) {
            throw new RuntimeException("User with email already exists");
        }
        DemoUser user = new DemoUser(System.currentTimeMillis(), name, email, password, "USER");
        userStore.put(email, user);
        return user;
    }

    public DemoUser getByEmail(String email) {
        return userStore.get(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DemoUser user = userStore.get(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }

    // Inner class to hold demo user info
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

        // getters
        public Long getId() { return id; }
        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getPassword() { return password; }
        public String getRole() { return role; }
    }
}
