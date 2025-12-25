// package com.example.demo.config;

// import com.example.demo.security.JwtTokenProvider;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.web.filter.OncePerRequestFilter;
// import java.io.IOException;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {
    
//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
    
//     @Bean
//     public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//         return config.getAuthenticationManager();
//     }
    
//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http, JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) throws Exception {
//         http.csrf(csrf -> csrf.disable())
//             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
//                 .requestMatchers("/api/**").authenticated()
//                 .anyRequest().permitAll()
//             )
//             .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, userDetailsService), 
//                            UsernamePasswordAuthenticationFilter.class);
        
//         return http.build();
//     }
    
//     private static class JwtAuthenticationFilter extends OncePerRequestFilter {
//         private final JwtTokenProvider jwtTokenProvider;
//         private final UserDetailsService userDetailsService;
        
//         public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
//             this.jwtTokenProvider = jwtTokenProvider;
//             this.userDetailsService = userDetailsService;
//         }
        
//         @Override
//         protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
//                                       FilterChain filterChain) throws ServletException, IOException {
//             String token = getTokenFromRequest(request);
            
//             if (token != null && jwtTokenProvider.validateToken(token)) {
//                 String email = jwtTokenProvider.getEmailFromToken(token);
//                 UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                
//                 UsernamePasswordAuthenticationToken authentication = 
//                     new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                 SecurityContextHolder.getContext().setAuthentication(authentication);
//             }
            
//             filterChain.doFilter(request, response);
//         }
        
//         private String getTokenFromRequest(HttpServletRequest request) {
//             String bearerToken = request.getHeader("Authorization");
//             if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
//                 return bearerToken.substring(7);
//             }
//             return null;
//         }
//     }
// }








// package com.example.demo.config;

// import com.example.demo.security.JwtTokenProvider;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.web.filter.OncePerRequestFilter;
// import java.io.IOException;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {
    
//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
    
//     @Bean
//     public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//         return config.getAuthenticationManager();
//     }
    
//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http, JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) throws Exception {
//         http.csrf(csrf -> csrf.disable())
//             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/auth/**", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html", "/h2-console/**").permitAll()
//                 .requestMatchers("/api/**").authenticated()
//                 .anyRequest().permitAll()
//             )
//             .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, userDetailsService), 
//                            UsernamePasswordAuthenticationFilter.class);
        
//         return http.build();
//     }
    
//     private static class JwtAuthenticationFilter extends OncePerRequestFilter {
//         private final JwtTokenProvider jwtTokenProvider;
//         private final UserDetailsService userDetailsService;
        
//         public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
//             this.jwtTokenProvider = jwtTokenProvider;
//             this.userDetailsService = userDetailsService;
//         }
        
//         @Override
//         protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
//                                       FilterChain filterChain) throws ServletException, IOException {
//             String token = getTokenFromRequest(request);
            
//             if (token != null && jwtTokenProvider.validateToken(token)) {
//                 String email = jwtTokenProvider.getEmailFromToken(token);
//                 UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                
//                 UsernamePasswordAuthenticationToken authentication = 
//                     new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                 SecurityContextHolder.getContext().setAuthentication(authentication);
//             }
            
//             filterChain.doFilter(request, response);
//         }
        
//         private String getTokenFromRequest(HttpServletRequest request) {
//             String bearerToken = request.getHeader("Authorization");
//             if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
//                 return bearerToken.substring(7);
//             }
//             return null;
//         }
//     }
// }




// package com.example.demo.config;

// import com.example.demo.security.JwtTokenProvider;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.web.filter.OncePerRequestFilter;
// import java.io.IOException;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {
    
//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
    
//     @Bean
//     public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//         return config.getAuthenticationManager();
//     }
    
//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http, JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) throws Exception {
//         http.csrf(csrf -> csrf.disable())
//             .headers(headers -> headers.frameOptions().sameOrigin())
//             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/auth/**").permitAll()
//                 .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/swagger-resources/**", "/v3/api-docs/**", "/webjars/**").permitAll()
//                 .requestMatchers("/h2-console/**").permitAll()
//                 .requestMatchers("/api/**").authenticated()
//                 .anyRequest().permitAll()
//             )
//             .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, userDetailsService), 
//                            UsernamePasswordAuthenticationFilter.class);
        
//         return http.build();
//     }
    
//     private static class JwtAuthenticationFilter extends OncePerRequestFilter {
//         private final JwtTokenProvider jwtTokenProvider;
//         private final UserDetailsService userDetailsService;
        
//         public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
//             this.jwtTokenProvider = jwtTokenProvider;
//             this.userDetailsService = userDetailsService;
//         }
        
//         @Override
//         protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
//                                       FilterChain filterChain) throws ServletException, IOException {
//             String token = getTokenFromRequest(request);
            
//             if (token != null && jwtTokenProvider.validateToken(token)) {
//                 String email = jwtTokenProvider.getEmailFromToken(token);
//                 UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                
//                 UsernamePasswordAuthenticationToken authentication = 
//                     new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                 SecurityContextHolder.getContext().setAuthentication(authentication);
//             }
            
//             filterChain.doFilter(request, response);
//         }
        
//         private String getTokenFromRequest(HttpServletRequest request) {
//             String bearerToken = request.getHeader("Authorization");
//             if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
//                 return bearerToken.substring(7);
//             }
//             return null;
//         }
//     }
// }




package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/auth/**",
                        "/swagger-ui/**",
                        "/v3/api-docs/**"
                ).permitAll()
                .anyRequest().authenticated()
            );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
