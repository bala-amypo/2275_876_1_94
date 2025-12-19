package com.example.demo.service.impl;

import com.example.demo.model.DemoUser;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public DemoUser saveUser(DemoUser user) {
        return userRepository.save(user);
    }

    @Override
    public DemoUser getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
