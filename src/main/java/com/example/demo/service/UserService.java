package com.example.demo.service;

import com.example.demo.model.User;
import java.util.List;

public interface UserService {
    User saveUser(User user);
    User getUserById(Long id);  // returns User, not Optional<User>
    List<User> getAllUsers();
    void deleteUser(Long id);
    User updateUser(User user);
}
