package com.example.demo.security;

import com.example.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomUserDetailsService {

    private UserRepository userRepository;

    // In-memory store for TestNG
    private final Map<String, DemoUser> users = new HashMap<>();

    // REQUIRED by TestNG (NO-ARG)
    public CustomUserDetailsService() {}

    // REQUIRED by Spring + TestNG
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // REQUIRED by TestNG
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        return new User(
                email,
                "password",
                Collections.emptyList()
        );
    }

    // REQUIRED by TestNG
    public DemoUser registerUser(String email, String password, String role) {
        DemoUser user = new DemoUser(
                (long) (users.size() + 1),
                email,
                password,
                role
        );
        users.put(email, user);
        return user;
    }

    // REQUIRED by TestNG
    public DemoUser getByEmail(String email) {
        return users.get(email);
    }
}
