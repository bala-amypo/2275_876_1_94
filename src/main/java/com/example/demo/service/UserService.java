package com.example.demo.service;

import com.example.demo.model.DemoUser;

public interface UserService {
    DemoUser saveUser(DemoUser user);
    DemoUser getUserByEmail(String email);
}
