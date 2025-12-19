// package com.example.demo.model;

// import jakarta.persistence.*;
// import java.time.LocalDateTime;

// @Entity
// @Table(name = "users")
// public class DemoUser {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String name;
//     private String email;
//     private String password;
//     private String role;

//     private LocalDateTime createdAt;

//     public DemoUser() {
//         this.createdAt = LocalDateTime.now();
//     }

//     public DemoUser(Long id, String email, String password, String role) {
//         this.id = id;
//         this.email = email;
//         this.password = password;
//         this.role = role;
//         this.createdAt = LocalDateTime.now();
//     }

//     // ===== GETTERS =====
//     public Long getId() { return id; }
//     public String getName() { return name; }
//     public String getEmail() { return email; }
//     public String getPassword() { return password; }
//     public String getRole() { return role; }
//     public LocalDateTime getCreatedAt() { return createdAt; }

//     // ===== SETTERS =====
//     public void setName(String name) { this.name = name; }
//     public void setEmail(String email) { this.email = email; }
//     public void setPassword(String password) { this.password = password; }
//     public void setRole(String role) { this.role = role; }
// }
