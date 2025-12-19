package com.example.demo.security;

import com.example.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService {

    private final UserRepository userRepository;

    // REQUIRED by TestNG
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // REQUIRED by TestNG
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        return new User(
                username,
                "password",
                Collections.emptyList()
        );
    }
}
