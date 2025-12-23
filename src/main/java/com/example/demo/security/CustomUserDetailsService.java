package com.example.demo.security;

import java.util.*;

public class CustomUserDetailsService {

    private final Map<String, DemoUser> users = new HashMap<>();

    public CustomUserDetailsService() {
        users.put("admin@city.com",
                new DemoUser("Admin", "admin@city.com", "ADMIN"));
    }

    public DemoUser getByEmail(String email) {
        return users.get(email);
    }

    public DemoUser registerUser(String name, String email, String pwd) {
        if (users.containsKey(email))
            throw new RuntimeException("User exists");
        DemoUser u = new DemoUser(name, email, "USER");
        users.put(email, u);
        return u;
    }

    public DemoUser loadUserByUsername(String email) {
        if (!users.containsKey(email))
            throw new RuntimeException("User not found");
        return users.get(email);
    }

    public static class DemoUser {
        private final String name;
        private final String email;
        private final String role;

        public DemoUser(String n, String e, String r) {
            name = n; email = e; role = r;
        }
        public String getEmail() { return email; }
        public String getRole() { return role; }
    }
}
