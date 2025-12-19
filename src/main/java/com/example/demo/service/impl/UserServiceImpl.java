package com.example.demo.service.impl;

import com.example.demo.model.DemoUser;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    public DemoUser createUser(Long id, String email, String password, String role) {
        DemoUser user = new DemoUser(id, email, password, role);
        return userRepository.save(user);
    }
}
