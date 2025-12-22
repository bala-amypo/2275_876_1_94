package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return service.createUser(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return service.updateUser(id, user);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return service.getUserById(id);
    }

    @GetMapping
    public List<User> getAll() {
        return service.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteUser(id);
    }
}
