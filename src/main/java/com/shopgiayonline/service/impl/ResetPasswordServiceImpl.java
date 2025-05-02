package com.shopgiayonline.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.shopgiayonline.entity.ResetPasswordToken;
import com.shopgiayonline.entity.User;
import com.shopgiayonline.repository.ResetPasswordTokenRepository;
import com.shopgiayonline.repository.UserRepository;
import com.shopgiayonline.service.ResetPasswordService;
import com.shopgiayonline.service.SendMailService;

@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {
    private final String BASE_FRONTEND_ENDPOINT;
    private final UserRepository userRepository;
    private final ResetPasswordTokenRepository resetPasswordTokenRepository;
    private final SendMailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final SpringTemplateEngine springTemplateEngine;

    public ResetPasswordServiceImpl(
            @Qualifier("userRepository") UserRepository userRepository,
            ResetPasswordTokenRepository resetPasswordTokenRepository,
            SendMailService emailService,
            SpringTemplateEngine springTemplateEngine,
            PasswordEncoder passwordEncoder,
            @Value("${frontend.base-endpoint}") String BASE_FRONTEND_ENDPOINT) {
        this.userRepository = userRepository;
        this.resetPasswordTokenRepository = resetPasswordTokenRepository;
        this.emailService = emailService;
        this.springTemplateEngine = springTemplateEngine;
        this.passwordEncoder = passwordEncoder;
        this.BASE_FRONTEND_ENDPOINT = BASE_FRONTEND_ENDPOINT;
    }

    @Override
    @Transactional
    public boolean handleForgotPassword(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null)
            return false;
        resetPasswordTokenRepository.findFirstByOrderByCreatedAtDesc().ifPresent(oldToken -> {
            oldToken.setIsValid(false);
            resetPasswordTokenRepository.save(oldToken);
        });
        ResetPasswordToken resetPasswordToken = new ResetPasswordToken();
        resetPasswordToken.setUser(user);
        resetPasswordToken.setToken(System.currentTimeMillis() + "-" + UUID.randomUUID());
        resetPasswordToken.setCreatedAt(LocalDateTime.now());
        resetPasswordToken.setExpiresAt(LocalDateTime.now().plusMinutes(15));
        resetPasswordToken.setIsValid(true);
        this.sendResetPasswordMail(user.getEmail(), user.getName(), resetPasswordToken.getToken());
        resetPasswordTokenRepository.save(resetPasswordToken);
        return true;
    }

    @Override
    public void sendResetPasswordMail(String email, String name, String token) {
        Map<String, Object> templateProps = new HashMap<>();
        templateProps.put("ten", name);
        templateProps.put("url", BASE_FRONTEND_ENDPOINT + "/reset-password?email=" + email + "&token=" + token);
        Context context = new Context();
        context.setVariables(templateProps);
        String mailContent = springTemplateEngine.process("reset-password", context);
        emailService.sendSimpleEmail(email, "[Shop Giày Online] Yêu cầu đặt lại mật khẩu", mailContent);
    }

    @Override
    @Transactional
    public boolean resetPassword(String token, String email, @Nullable String password) {
        User user = userRepository.findByEmail(email).orElse(null);
        ResetPasswordToken resetPasswordToken = user != null
                ? resetPasswordTokenRepository.findByTokenAndUserId(token, user.getId()).orElse(null)
                : null;
        if (resetPasswordToken == null)
            return false;
        if (!resetPasswordToken.getIsValid())
            return false;
        if (resetPasswordToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            resetPasswordToken.setIsValid(false);
            resetPasswordTokenRepository.save(resetPasswordToken);
            return false;
        }
        if (password != null) {
            user.setPassword(passwordEncoder.encode(password));
            resetPasswordToken.setIsValid(false);
            userRepository.save(user);
            resetPasswordTokenRepository.save(resetPasswordToken);
            return true;
        }
        return true;
    }

}
