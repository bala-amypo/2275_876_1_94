// package com.example.demo.controller;

// import com.example.demo.dto.AuthRequest;
// import com.example.demo.dto.AuthResponse;
// import com.example.demo.model.User;
// import com.example.demo.security.JwtTokenProvider;
// import com.example.demo.service.UserService;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/auth")
// public class AuthController {

//     private final UserService userService;
//     private final JwtTokenProvider tokenProvider;

//     public AuthController(UserService userService, JwtTokenProvider tokenProvider) {
//         this.userService = userService;
//         this.tokenProvider = tokenProvider;
//     }

//     @PostMapping("/register")
//     public ResponseEntity<AuthResponse> register(@RequestBody User user) {
//         User savedUser = userService.registerUser(
//                 user.getFullName(),
//                 user.getEmail(),
//                 user.getPassword(),
//                 user.getRole()
//         );
//         String token = tokenProvider.generateToken(savedUser.getId(), savedUser.getEmail(), savedUser.getRole());
//         return ResponseEntity.ok(new AuthResponse(token, savedUser.getId(), savedUser.getEmail(), savedUser.getRole()));
//     }

//     @PostMapping("/login")
//     public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
//         User user = userService.getByEmail(request.getEmail());
//         // Password verification skipped; assume done via SecurityConfig authentication manager
//         String token = tokenProvider.generateToken(user.getId(), user.getEmail(), user.getRole());
//         return ResponseEntity.ok(new AuthResponse(token, user.getId(), user.getEmail(), user.getRole()));
//     }
// }
