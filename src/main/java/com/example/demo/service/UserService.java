package com.example.demo.service;

import com.example.demo.model.DemoUser;

public interface UserService {

    DemoUser getUserByUsername(String username);

    DemoUser saveUser(DemoUser user);
}
