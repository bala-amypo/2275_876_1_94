package com.example.demo.service;

import com.example.demo.model.DemoUser;
import java.util.Optional;

public interface UserService {
    DemoUser saveUser(DemoUser user);
    Optional<DemoUser> getUserByEmail(String email);
}
