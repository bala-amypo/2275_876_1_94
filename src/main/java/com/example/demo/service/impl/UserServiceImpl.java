package com.example.demo.service.impl;

import com.example.demo.model.DemoUser;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public DemoUser saveUser(DemoUser user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<DemoUser> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
