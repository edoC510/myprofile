package com.shopgiayonline.service.impl;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.shopgiayonline.common.exception.BadRequestException;
import com.shopgiayonline.entity.OtpToken;
import com.shopgiayonline.repository.OtpTokenRepository;
import com.shopgiayonline.service.EmailService;
import com.shopgiayonline.service.OtpService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {

    private final OtpTokenRepository otpRepo;
    private final EmailService emailService;

    @Value("${app.otp.expiration.minutes:5}")
    private long expirationMinutes;

    @Override
    public void sendOtp(String email, String name) {
        String code = String.format("%04d", new Random().nextInt(10000));
        LocalDateTime now = LocalDateTime.now();
        OtpToken otp = new OtpToken();
        otp.setEmail(email);
        otp.setCode(code);
        otp.setExpiresAt(now.plusMinutes(expirationMinutes));
        otp.setValid(true);
        otpRepo.save(otp);

        // Gửi mail qua Thymeleaf template “otp.html”
        emailService.sendOTPMail(email, name, code);
    }

    @Override
    public boolean verifyOtp(String email, String code) {
        OtpToken otp = otpRepo.findFirstByEmailAndValidOrderByExpiresAtDesc(email, true)
                .orElseThrow(() -> new BadRequestException("OTP không hợp lệ hoặc đã hết hạn"));
        if (LocalDateTime.now().isAfter(otp.getExpiresAt()))
            throw new BadRequestException("OTP đã hết hạn");
        if (!otp.getCode().equals(code))
            throw new BadRequestException("OTP không đúng");
        otp.setValid(false);
        otpRepo.save(otp);
        return true;
    }
}
