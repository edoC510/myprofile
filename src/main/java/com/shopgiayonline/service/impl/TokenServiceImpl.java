package com.shopgiayonline.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopgiayonline.infrastructure.authentication.JwtTokenProvider;
import com.shopgiayonline.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Override
    public String genToken(String username) {
        String test = jwtTokenProvider.generateTokenByUser(username);
        return test;
    }

    @Override
    public String getUserNameByToken(String token) {
        String userName = jwtTokenProvider.getUsername(token);
        return userName;
    }

}
