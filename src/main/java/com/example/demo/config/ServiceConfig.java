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

    @Bean
    public UserService userService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return new UserServiceImpl(userRepository, passwordEncoder);
    }

    @Bean
    public BinService binService(BinRepository binRepository, ZoneRepository zoneRepository) {
        return new BinServiceImpl(binRepository, zoneRepository);
    }

    @Bean
    public ZoneService zoneService(ZoneRepository zoneRepository) {
        return new ZoneServiceImpl(zoneRepository);
    }

    @Bean
    public FillLevelRecordService fillLevelRecordService(FillLevelRecordRepository recordRepository, BinRepository binRepository) {
        return new FillLevelRecordServiceImpl(recordRepository, binRepository);
    }

    @Bean
    public UsagePatternModelService usagePatternModelService(UsagePatternModelRepository modelRepository, BinRepository binRepository) {
        return new UsagePatternModelServiceImpl(modelRepository, binRepository);
    }

    @Bean
    public OverflowPredictionService overflowPredictionService(BinRepository binRepository,
                                                               FillLevelRecordRepository recordRepository,
                                                               UsagePatternModelRepository modelRepository,
                                                               OverflowPredictionRepository predictionRepository,
                                                               ZoneRepository zoneRepository) {
        return new OverflowPredictionServiceImpl(binRepository, recordRepository, modelRepository, predictionRepository, zoneRepository);
    }

    @Bean
    public CustomUserDetailsService customUserDetailsService(UserRepository userRepository) {
        return new CustomUserDetailsService(userRepository);
    }
}
