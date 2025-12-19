package com.example.demo.controller;

import com.example.demo.model.DemoUser;
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

    @PostMapping("/register")
    public ResponseEntity<DemoUser> register(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password
    ) {
        DemoUser user = new DemoUser();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        return ResponseEntity.ok(userService.saveUser(user));
    }

    @GetMapping("/user")
    public ResponseEntity<DemoUser> getUser(@RequestParam String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }
}
