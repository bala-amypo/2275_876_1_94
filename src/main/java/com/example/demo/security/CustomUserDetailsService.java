package com.example.demo.security;

import com.example.demo.model.DemoUser;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService {

    @Autowired
    private UserService userService;

    public DemoUser registerUser(String name, String email, String password) {
        DemoUser user = new DemoUser(name, email, password);
        return userService.saveUser(user);
    }

    public DemoUser getByEmail(String email) {
        return userService.getUserByEmail(email);
    }
}
