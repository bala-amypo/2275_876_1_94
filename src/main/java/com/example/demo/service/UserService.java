package com.example.demo.service;

import com.example.demo.model.User;
import java.util.List;

public interface UserService {
    User createUser(User user);            // create user
    User updateUser(User user);            // update user
    User getUserById(Long id);             // get by id
    List<User> getAllUsers();              // list all
    void deleteUser(Long id);              // delete by id
}
