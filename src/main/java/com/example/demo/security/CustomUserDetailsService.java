// package com.example.demo.security;

// import com.example.demo.model.DemoUser;
// import com.example.demo.repository.UserRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.*;
// import org.springframework.stereotype.Service;

// @Service
// public class CustomUserDetailsService implements UserDetailsService {

//     @Autowired
//     private UserRepository userRepository;

//     // REQUIRED BY TESTS
//     public DemoUser registerUser(String name, String email, String password) {
//         DemoUser user = new DemoUser();
//         user.setName(name);
//         user.setEmail(email);
//         user.setPassword(password);
//         user.setRole("USER");
//         return userRepository.save(user);
//     }

//     // REQUIRED BY TESTS
//     public DemoUser getByEmail(String email) {
//         return userRepository.findByEmail(email).orElse(null);
//     }

//     // REQUIRED BY TESTS
//     @Override
//     public UserDetails loadUserByUsername(String email)
//             throws UsernameNotFoundException {

//         DemoUser user = getByEmail(email);
//         if (user == null) {
//             throw new UsernameNotFoundException("User not found");
//         }

//         return User.withUsername(user.getEmail())
//                 .password(user.getPassword())
//                 .roles(user.getRole())
//                 .build();
//     }
// }
