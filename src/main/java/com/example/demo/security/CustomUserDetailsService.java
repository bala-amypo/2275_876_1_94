package com.example.demo.security;

import com.example.demo.model.DemoUser;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // Must match test signature
    public DemoUser registerUser(String name, String email, String password) {
        DemoUser user = new DemoUser(name, email, password);
        return userRepository.save(user);
    }

    public DemoUser getByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
