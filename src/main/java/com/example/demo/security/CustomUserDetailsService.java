package com.example.demo.security;

import org.springframework.security.core.userdetails.*;
import java.util.*;

public class CustomUserDetailsService implements UserDetailsService {

    private final Map<String, DemoUser> users = new HashMap<>();

    public CustomUserDetailsService() {
        users.put("admin@city.com",
                new DemoUser("Admin", "admin@city.com", "ADMIN"));
    }

    public DemoUser getByEmail(String email) {
        return users.get(email);
    }

    public DemoUser registerUser(String name, String email, String password) {
        if (users.containsKey(email))
            throw new RuntimeException("User already exists");
        DemoUser user = new DemoUser(name, email, "USER");
        users.put(email, user);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        if (!users.containsKey(username))
            throw new UsernameNotFoundException("User not found");
        return users.get(username);
    }

    public static class DemoUser implements UserDetails {
        private final String name;
        private final String email;
        private final String role;

        public DemoUser(String name, String email, String role) {
            this.name = name;
            this.email = email;
            this.role = role;
        }

        public String getEmail() { return email; }
        public String getRole() { return role; }

        @Override public Collection getAuthorities() { return List.of(); }
        @Override public String getPassword() { return ""; }
        @Override public String getUsername() { return email; }
        @Override public boolean isAccountNonExpired() { return true; }
        @Override public boolean isAccountNonLocked() { return true; }
        @Override public boolean isCredentialsNonExpired() { return true; }
        @Override public boolean isEnabled() { return true; }
    }
}
