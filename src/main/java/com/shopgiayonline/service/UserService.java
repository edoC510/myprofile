package com.shopgiayonline.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.shopgiayonline.entity.User;

public interface UserService {
    User findByEmailOrCreateGoogle(String email, String username, String avatarUrl);

    User createGoogleAccount(String email, String username, String avatarUrl);

    User findByToken(String token);

    UserDetails loadByUsername(String username);
}