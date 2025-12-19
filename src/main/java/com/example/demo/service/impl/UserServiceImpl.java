package com.example.demo.service;

import com.example.demo.model.DemoUser;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

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
    public DemoUser getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
