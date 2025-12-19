package com.example.demo.controller;

import com.example.demo.model.DemoUser;
import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final CustomUserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(CustomUserDetailsService userDetailsService, JwtTokenProvider jwtTokenProvider) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public String register(@RequestParam String name,
                       @RequestParam String email,
                       @RequestParam String password) {

        DemoUser user = userDetailsService.registerUser(name, email, password);
        return jwtTokenProvider.generateToken(user.getId(), user.getName(), user.getEmail());
    }

}