package com.shopgiayonline.service;

public interface OtpService {
    void sendOtp(String email, String name);

    boolean verifyOtp(String email, String code);
}
