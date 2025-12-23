package com.example.demo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;

public class CustomUserDetailsService {

    public static class DemoUser {
        private Long id;
        private String name;
        private String email;
        private String password;
        private String role;

        public DemoUser(Long id, String name, String email, String password, String role) {
            this.id = id; this.name = name; this.email = email; this.password = password; this.role = role;
        }
        public Long getId() { return id; }
        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getPassword() { return password; }
        public String getRole() { return role; }
    }

    private final Map<String, DemoUser> users = new HashMap<>();
    private long seq = 1L;

    public CustomUserDetailsService() {
        // default admin
        DemoUser admin = new DemoUser(seq++, "Admin", "admin@city.com", "admin123", "ADMIN");
        users.put(admin.getEmail(), admin);
    }

    public DemoUser getByEmail(String email) {
        return users.get(email);
    }

    public DemoUser registerUser(String name, String email, String password) {
        if (users.containsKey(email)) {
            throw new RuntimeException("User already exists");
        }
        DemoUser user = new DemoUser(seq++, name, email, password, "USER");
        users.put(email, user);
        return user;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        DemoUser user = users.get(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Collection<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
        return new User(user.getEmail(), user.getPassword(), authorities);
    }
}
