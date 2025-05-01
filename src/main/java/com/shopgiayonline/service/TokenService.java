package com.shopgiayonline.service;

public interface TokenService {
    String genToken(String username);

    String getUserNameByToken(String token);
}
