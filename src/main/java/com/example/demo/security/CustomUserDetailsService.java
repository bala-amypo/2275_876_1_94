package com.example.demo.security;

import com.example.demo.model.DemoUser;
import com.example.demo.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService {

    private final UserService userService;

    // REQUIRED constructor
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    // REQUIRED BY TESTS
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        DemoUser user = userService.getUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }

    // REQUIRED BY TESTS
    public DemoUser getByEmail(String email) {
        return userService.getUserByEmail(email);
    }

    // REQUIRED BY TESTS
    public DemoUser registerUser(String name, String email, String password) {
        DemoUser user = new DemoUser(name, email, password);
        return userService.saveUser(user);
    }
}
