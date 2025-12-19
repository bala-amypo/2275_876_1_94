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




package com.example.demo.security;

import com.example.demo.model.User;

import java.util.HashMap;
import java.util.Map;

public class CustomUserDetailsService {

    private final Map<String, User> users = new HashMap<>();
    private long idCounter = 1;

    public CustomUserDetailsService() {}

    public User registerUser(String fullName, String email, String password) {
        if (users.containsKey(email)) {
            throw new RuntimeException("User already exists");
        }
        User user = new User(fullName, email, password, "USER");
        user.setId(idCounter++);
        users.put(email, user);
        return user;
    }

    public User getByEmail(String email) {
        User user = users.get(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user;
    }

    public User loadUserByUsername(String email) {
        return getByEmail(email);
    }
}
