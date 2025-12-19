package com.example.demo.service;

import com.example.demo.model.DemoUser;

public interface UserService {

    DemoUser registerUser(String email, String password, String role);

    DemoUser getByEmail(String email);
}
