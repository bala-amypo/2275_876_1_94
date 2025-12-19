package com.example.demo.controller;

import com.example.demo.model.DemoUser;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public DemoUser registerUser(@RequestParam String name,
                                 @RequestParam String email,
                                 @RequestParam String password) {
        DemoUser user = new DemoUser();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        return userService.saveUser(user);
    }

    @GetMapping("/get")
    public DemoUser getUser(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }
}
