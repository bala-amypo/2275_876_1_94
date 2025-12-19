package com.example.demo.security;

import com.example.demo.model.DemoUser;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // REQUIRED constructor (tests expect this)
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // REQUIRED BY TEST
    public DemoUser registerUser(String name, String email, String password) {
        DemoUser user = new DemoUser(name, email, password);
        return userRepository.save(user);
    }

    // REQUIRED BY TEST
    public DemoUser getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    // REQUIRED BY SPRING + TEST
    @Override
    public UserDetails loadUserByUsername(String email) {
        DemoUser user = getByEmail(email);
        return new User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
