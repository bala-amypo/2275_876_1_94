package com.example.demo.service;

import com.example.demo.model.DemoUser;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserService() {
    }

    public DemoUser getUserByUsername(String username) {
        return new DemoUser(1L, username, "password", "ROLE_USER");
    }
}
