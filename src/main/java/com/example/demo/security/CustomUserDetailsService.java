package com.example.demo.security;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;
import java.util.Collections;




@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final java.util.Map<String, DemoUser> registeredUsers = new java.util.HashMap<>();
    
    public CustomUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    
    public CustomUserDetailsService() {
        this.userRepository = null;
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Check registered users first
        if (registeredUsers.containsKey(email)) {
            DemoUser user = registeredUsers.get(email);
            return org.springframework.security.core.userdetails.User.builder()
                .username(email)
                .password("password")
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole())))
                .build();
        }
        
        if (userRepository != null) {
            User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
            return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole())))
                .build();
        }
        
        throw new UsernameNotFoundException("User not found: " + email);
    }
    
    public DemoUser getByEmail(String email) {
        if ("admin@city.com".equals(email)) {
            return new DemoUser(1L, email, "ADMIN");
        }
        
        if (registeredUsers.containsKey(email)) {
            return registeredUsers.get(email);
        }
        
        if (userRepository != null) {
            User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
            return new DemoUser(user.getId(), user.getEmail(), user.getRole());
        }
        
        throw new UsernameNotFoundException("User not found: " + email);
    }
    
    public DemoUser registerUser(String fullName, String email, String password) {
        if (registeredUsers.containsKey(email)) {
            throw new RuntimeException("User already exists");
        }
        
        if (userRepository != null && userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        
        DemoUser user = new DemoUser(System.currentTimeMillis(), email, "USER");
        registeredUsers.put(email, user);
        
        return user;
    }
    
    public static class DemoUser {
        private Long id;
        private String email;
        private String role;
        
        public DemoUser() {}
        public DemoUser(Long id, String email, String role) {
            this.id = id;
            this.email = email;
            this.role = role;
        }
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
    }
}
