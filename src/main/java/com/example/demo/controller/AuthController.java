package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Register user
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        userService.registerUser(user.getUsername(), user.getEmail(), user.getPassword(), user.getRole());
        return ResponseEntity.ok("User registered successfully");
    }

    // Authenticate user
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        boolean success = userService.authenticateUser(user.getEmail(), user.getPassword());
        if (success) return ResponseEntity.ok("Login successful");
        else return ResponseEntity.status(401).body("Invalid credentials");
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) return ResponseEntity.ok(user);
        else return ResponseEntity.notFound().build();
    }
}
