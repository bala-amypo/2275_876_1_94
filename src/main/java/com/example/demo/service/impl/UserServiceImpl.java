package com.example.demo.service.impl;

import com.example.demo.model.DemoUser;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public DemoUser registerUser(String email, String password, String role) {
        DemoUser user = new DemoUser(email, password, role);
        return userRepository.save(user);
    }

    @Override
    public DemoUser getByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
