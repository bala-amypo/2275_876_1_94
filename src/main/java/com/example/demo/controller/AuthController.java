// package com.example.demo.controller;

// import com.example.demo.dto.AuthRequest;
// import com.example.demo.dto.AuthResponse;
// import com.example.demo.model.User;
// import com.example.demo.security.JwtTokenProvider;
// import com.example.demo.service.UserService;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.AuthenticationException;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/auth")
// public class AuthController {
//     private final UserService userService;
//     private final JwtTokenProvider jwtTokenProvider;
//     private final AuthenticationManager authenticationManager;
    
//     public AuthController(UserService userService, JwtTokenProvider jwtTokenProvider, 
//                          AuthenticationManager authenticationManager) {
//         this.userService = userService;
//         this.jwtTokenProvider = jwtTokenProvider;
//         this.authenticationManager = authenticationManager;
//     }
    
//     @PostMapping("/register")
//     public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
//         User user = userService.registerUser("User", request.getEmail(), request.getPassword());
//         String token = jwtTokenProvider.generateToken(user.getId(), user.getEmail(), user.getRole());
//         return ResponseEntity.ok(new AuthResponse(token, user.getId(), user.getEmail(), user.getRole()));
//     }
    
//     @PostMapping("/login")
//     public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
//         try {
//             // This validates user exists AND password matches
//             authenticationManager.authenticate(
//                 new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
//             );
            
//             // Only reaches here if authentication succeeded
//             User user = userService.getByEmail(request.getEmail());
//             String token = jwtTokenProvider.generateToken(user.getId(), user.getEmail(), user.getRole());
//             return ResponseEntity.ok(new AuthResponse(token, user.getId(), user.getEmail(), user.getRole()));
            
//         } catch (AuthenticationException e) {
//             // Wrong credentials - return 401
//              return ResponseEntity
//             .status(401)
//             .body(null);
//         }
//     }
// }







package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.ApiResponse;
import com.example.demo.model.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody User user) {
        User registeredUser = userService.registerUser(user.getFullName(), user.getEmail(), user.getPassword());
        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully", registeredUser));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        User user = userService.getByEmail(request.getEmail());
        
        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            String token = jwtTokenProvider.generateToken(user.getId(), user.getEmail(), user.getRole());
            return ResponseEntity.ok(new AuthResponse(token, user.getId(), user.getEmail(), user.getRole()));
        }
        
        return ResponseEntity.badRequest().build();
    }
}