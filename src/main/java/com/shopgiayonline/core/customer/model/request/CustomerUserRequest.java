package com.shopgiayonline.core.customer.model.request;

import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.shopgiayonline.common.enums.UserRole;
import com.shopgiayonline.entity.User;
import com.shopgiayonline.infrastructure.adapter.DtoToEntity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerUserRequest implements DtoToEntity<User> {
    private String name;

    private Short status;

    private String username;

    private LocalDateTime createdAt;

    private String password;

    private java.sql.Date dob;

    private String avatarUrl;

    private String email;

    private String phone;

    private Short gender;

    private String address;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private PasswordEncoder passwordEncoder;

    public CustomerUserRequest() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public User dtoToEntity(User user) {
        user.setName(this.getName());
        user.setUsername(this.getUsername());
        user.setStatus(this.getStatus());
        user.setCreatedAt(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(this.getPassword()));
        user.setDob(this.getDob());
        user.setAvatarUrl(this.getAvatarUrl());
        user.setEmail(this.getEmail());
        user.setPhone(this.getPhone());
        user.setGender(this.getGender());
        user.setRole(this.getRole());
        return user;
    }
}
