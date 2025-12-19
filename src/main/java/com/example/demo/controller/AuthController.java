// package com.example.demo.controller;

// import com.example.demo.security.JwtTokenProvider;
// import com.example.demo.service.UserService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/auth")
// public class AuthController {

//     @Autowired
//     private UserService userService;

//     @Autowired
//     private JwtTokenProvider jwtTokenProvider;

//     @GetMapping("/test")
//     public String test() {
//         return "Auth OK";
//     }
// }





package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.security.JwtTokenProvider;

public class AuthController {

    private final CustomUserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(CustomUserDetailsService uds, JwtTokenProvider jwt) {
        this.userDetailsService = uds;
        this.jwtTokenProvider = jwt;
    }

    public String register(String name, String email, String password) {
        User user = userDetailsService.registerUser(name, email, password);
        return jwtTokenProvider.generateToken(user.getId(), user.getEmail(), user.getRole());
    }
}
