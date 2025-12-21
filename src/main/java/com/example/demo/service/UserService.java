package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
    User authenticate(String username, String password);
}
