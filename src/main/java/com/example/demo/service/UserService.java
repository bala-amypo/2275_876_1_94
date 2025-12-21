package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {

    User registerUser(String username, String email, String password, String role);

    User authenticateUser(String email, String password);

    User getUserById(Long id);
}
