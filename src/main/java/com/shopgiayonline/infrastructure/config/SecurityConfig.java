package com.shopgiayonline.infrastructure.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.shopgiayonline.infrastructure.authentication.Authentication;
import com.shopgiayonline.infrastructure.authentication.JwtAuthenticationEntryPoint;
import com.shopgiayonline.infrastructure.authentication.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;
    private final JwtAuthenticationFilter authenticationFilter;
    private final Authentication authenticationProvider;

    public SecurityConfig(
            JwtAuthenticationEntryPoint authenticationEntryPoint,
            JwtAuthenticationFilter authenticationFilter,
            Authentication authenticationProvider) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.authenticationFilter = authenticationFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/api/khach-hang/**", "/api/payment-vnpay", "/api/payment-callback")
                        .permitAll()
                        .requestMatchers("/api/admin/thong-bao/**").permitAll()
                        .requestMatchers("/api/khach-hang/user/**").permitAll()
                        .requestMatchers("/api/khach-hang/hoa-don-chi-tiet/**").permitAll()
                        .requestMatchers("/api/khach-hang/checkout").permitAll()
                        .requestMatchers("/api/getUseNameByToken/**").permitAll()
                        .requestMatchers("/api/khach-hang/user-voucher/**").permitAll()
                        .requestMatchers("/api/genToken/**").permitAll()
                        .requestMatchers("/ws/**").permitAll()
                        .requestMatchers("/api/admin/hoaDon/**").hasAnyRole("ADMIN", "EMPLOYEE")
                        .requestMatchers("/api/admin/hoa-don-chi-tiet/**").hasAnyRole("ADMIN", "EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/admin/**").hasAnyRole("ADMIN", "EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/admin/**").hasAnyRole("ADMIN", "EMPLOYEE")
                        .requestMatchers(HttpMethod.PUT, "/api/admin/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/admin/**").hasAnyRole("ADMIN")
                        .anyRequest().permitAll())
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(authenticationEntryPoint))
                .authenticationProvider(authenticationProvider)
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(headers -> headers
                        .frameOptions().disable())
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class); // Đảm bảo filter
                                                                                                    // không bị null

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}