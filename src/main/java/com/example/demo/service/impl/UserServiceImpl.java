package com.example.demo.service.impl;

import com.example.demo.model.DemoUser;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final Map<String, DemoUser> users = new HashMap<>();

    @Override
    public DemoUser saveUser(DemoUser user) {
        users.put(user.getEmail(), user);
        return user;
    }

    @Override
    public DemoUser getUserByEmail(String email) {
        return users.get(email);
    }
}
