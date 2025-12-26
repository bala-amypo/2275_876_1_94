package com.example.demo.security;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ✅ Used ONLY by tests
    private final Map<String, DemoUser> registeredUsers = new HashMap<>();

    // ✅ SINGLE constructor – Spring-safe
    public CustomUserDetailsService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ AUTHENTICATION ENTRY POINT
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        // 🔹 1. TEST USERS (encoded password!)
        if (registeredUsers.containsKey(email)) {
            DemoUser user = registeredUsers.get(email);

            return org.springframework.security.core.userdetails.User.builder()
                    .username(email)
                    .password(user.getEncodedPassword())
                    .authorities(
                            Collections.singletonList(
                                    new SimpleGrantedAuthority("ROLE_" + user.getRole())
                            )
                    )
                    .build();
        }

        // 🔹 2. REAL USERS (DB)
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found: " + email)
                );

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword()) // encoded
                .authorities(
                        Collections.singletonList(
                                new SimpleGrantedAuthority("ROLE_" + user.getRole())
                        )
                )
                .build();
    }

    // ✅ Used by test cases
    public DemoUser registerUser(String fullName, String email, String password) {

        if (registeredUsers.containsKey(email)) {
            throw new RuntimeException("User already exists");
        }

        DemoUser user = new DemoUser(
                System.currentTimeMillis(),
                email,
                passwordEncoder.encode(password),
                "USER"
        );

        registeredUsers.put(email, user);
        return user;
    }

    // =====================================================

    public static class DemoUser {
        private Long id;
        private String email;
        private String encodedPassword;
        private String role;

        public DemoUser(Long id, String email, String encodedPassword, String role) {
            this.id = id;
            this.email = email;
            this.encodedPassword = encodedPassword;
            this.role = role;
        }

        public Long getId() { return id; }
        public String getEmail() { return email; }
        public String getEncodedPassword() { return encodedPassword; }
        public String getRole() { return role; }
    }
}
