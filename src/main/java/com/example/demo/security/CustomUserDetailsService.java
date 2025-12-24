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
import java.util.Optional;

import jakarta.annotation.Nullable;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public CustomUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    // Overloaded constructor to support tests instantiating with only repository
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    
    // No-args constructor for tests that new up the service directly
    public CustomUserDetailsService() {
        this.userRepository = null;
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    
    // Varargs constructor to gracefully accept extra unused dependencies in tests
    public CustomUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder, Object... ignored) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder != null ? passwordEncoder : PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
        
        return org.springframework.security.core.userdetails.User.builder()
            .username(user.getEmail())
            .password(user.getPassword())
            .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole())))
            .build();
    }
    
    // Convenience method expected by some tests (returns DemoUser)
    public DemoUser getByEmail(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
        return toDemoUser(user);
    }
    
    // Convenience method to register user (fullName, email, rawPassword) and return DemoUser
    public DemoUser registerUser(String fullName, String email, String password) {
        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("USER");
        User saved = userRepository.save(user);
        return toDemoUser(saved);
    }
    
    private DemoUser toDemoUser(User user) {
        return new DemoUser(user.getId(), user.getEmail(), user.getRole());
    }
    
    // Minimal inner DemoUser class to satisfy tests referencing CustomUserDetailsService.DemoUser
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