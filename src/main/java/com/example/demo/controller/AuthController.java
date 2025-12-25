// package com.example.demo.controller;

// import com.example.demo.dto.AuthRequest;
// import com.example.demo.dto.AuthResponse;
// import com.example.demo.model.User;
// import com.example.demo.security.JwtTokenProvider;
// import com.example.demo.service.UserService;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
//         authenticationManager.authenticate(
//             new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
//         );
        
//         User user = userService.getByEmail(request.getEmail());
//         String token = jwtTokenProvider.generateToken(user.getId(), user.getEmail(), user.getRole());
//         return ResponseEntity.ok(new AuthResponse(token, user.getId(), user.getEmail(), user.getRole()));
//     }
// }








package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        return ResponseEntity.ok(
                new ApiResponse(true, "Login successful", authentication.getName())
        );
    }

    // ======================
    // INNER CLASSES (NO LOMBOK)
    // ======================

    public static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class ApiResponse {
        private boolean success;
        private String message;
        private Object data;

        public ApiResponse(boolean success, String message, Object data) {
            this.success = success;
            this.message = message;
            this.data = data;
        }

        public boolean isSuccess() {
            return success;
        }
        public String getMessage() {
            return message;
        }
        public Object getData() {
            return data;
        }
    }
}
