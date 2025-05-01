// package com.shopgiayonline.infrastructure.config;

// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import com.shopgiayonline.service.AuthService;

// import lombok.RequiredArgsConstructor;

// @Configuration
// @RequiredArgsConstructor
// public class ApplicationRunnerConfig {
// private final AuthService authService;

// @Bean
// public CommandLineRunner commandLineRunner() {
// return args -> authService.createAdminIfNotExists();
// }
// }
