package com.example.demo.security;

import com.example.demo.model.DemoUser;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    public DemoUser registerUser(String name, String email, String password) {
        DemoUser user = new DemoUser(name, email, password);
        return userService.saveUser(user);
    }

    public DemoUser getByEmail(String email) {
        return userService.getUserByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
