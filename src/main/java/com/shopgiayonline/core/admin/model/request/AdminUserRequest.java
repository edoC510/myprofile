package com.shopgiayonline.core.admin.model.request;

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
public class AdminUserRequest implements DtoToEntity<User> {
    private String avatarUrl;

    private String name;

    private Short gender;

    private java.sql.Date dob;

    private String phone;

    private String email;

    private String password;

    private String username;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private Short status;

    private String address;

    private LocalDateTime createdAt;

    private PasswordEncoder passwordEncoder;

    public AdminUserRequest() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public User dtoToEntity(User user) {
        user.setName(this.getName());
        user.setUsername(this.getUsername());
        user.setStatus(this.getStatus());
        user.setCreatedAt(this.getCreatedAt());
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
