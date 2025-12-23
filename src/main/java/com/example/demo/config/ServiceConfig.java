package com.example.demo.config;

import com.example.demo.repository.*;
import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.service.*;
import com.example.demo.service.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ServiceConfig {

    // ---------- BIN ----------
    @Bean
    public BinService binService(
            BinRepository binRepository,
            ZoneRepository zoneRepository) {
        return new BinServiceImpl(binRepository, zoneRepository);
    }

    // ---------- ZONE ----------
    @Bean
    public ZoneService zoneService(
            ZoneRepository zoneRepository) {
        return new ZoneServiceImpl(zoneRepository);
    }

    // ---------- FILL LEVEL RECORD ----------
    @Bean
    public FillLevelRecordService fillLevelRecordService(
            FillLevelRecordRepository recordRepository,
            BinRepository binRepository) {
        return new FillLevelRecordServiceImpl(
                recordRepository, binRepository);
    }

    // ---------- USAGE PATTERN MODEL ----------
    @Bean
    public UsagePatternModelService usagePatternModelService(
            UsagePatternModelRepository modelRepository,
            BinRepository binRepository) {
        return new UsagePatternModelServiceImpl(
                modelRepository, binRepository);
    }

    // ---------- OVERFLOW PREDICTION ----------
    @Bean
    public OverflowPredictionService overflowPredictionService(
            BinRepository binRepository,
            FillLevelRecordRepository recordRepository,
            UsagePatternModelRepository modelRepository,
            OverflowPredictionRepository predictionRepository,
            ZoneRepository zoneRepository) {

        return new OverflowPredictionServiceImpl(
                binRepository,
                recordRepository,
                modelRepository,
                predictionRepository,
                zoneRepository);
    }

    // ---------- USER ----------
    @Bean
    public UserService userService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        return new UserServiceImpl(
                userRepository, passwordEncoder);
    }

    // ---------- SECURITY ----------
    @Bean
    public CustomUserDetailsService customUserDetailsService(
            UserRepository userRepository) {
        return new CustomUserDetailsService(userRepository);
    }
}
