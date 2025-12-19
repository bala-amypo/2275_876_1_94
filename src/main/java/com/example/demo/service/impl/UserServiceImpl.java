package com.example.demo.service.impl;

import com.example.demo.model.DemoUser;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public DemoUser getUserByUsername(String username) {
        return new DemoUser(
                1L,
                "Test User",
                "test@gmail.com",
                username,
                "password",
                "ROLE_USER"
        );
    }

    @Override
    public DemoUser saveUser(DemoUser user) {
        return user;
    }
}
