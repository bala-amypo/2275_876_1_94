package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
    User registerUser(User user);
    String authenticateUser(String email, String password);
    User getUserById(Long userId);
}
