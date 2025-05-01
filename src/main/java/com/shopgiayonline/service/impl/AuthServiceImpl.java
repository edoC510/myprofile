package com.shopgiayonline.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shopgiayonline.common.enums.UserRole;
import com.shopgiayonline.common.exception.BadRequestException;
import com.shopgiayonline.common.exception.ResourceAlreadyExistsException;
import com.shopgiayonline.common.message.UserMessage;
import com.shopgiayonline.common.status.UserStatus;
import com.shopgiayonline.dto.RegisterRequest;
import com.shopgiayonline.dto.RegisterResponse;
import com.shopgiayonline.entity.User;
import com.shopgiayonline.repository.UserRepository;
import com.shopgiayonline.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(@Qualifier("userRepository") UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RegisterResponse register(RegisterRequest req) {
        if (!req.getPassword().equals(req.getConfirmPassword())) {
            throw new BadRequestException(UserMessage.PASSWORDS_DO_NOT_MATCH);
        }

        if (userRepo.existsByEmail(req.getEmail())) {
            throw new ResourceAlreadyExistsException(UserMessage.EMAIL_ALREADY_EXISTS);
        }

        if (userRepo.existsByPhone(req.getPhone())) {
            throw new ResourceAlreadyExistsException(UserMessage.PHONE_ALREADY_EXISTS);
        }

        String username = req.getEmail().split("@")[0];
        if (userRepo.existsByUsername(username)) {
            throw new ResourceAlreadyExistsException(UserMessage.USERNAME_ALREADY_EXISTS);
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setName(req.getName());
        user.setGender(req.getGender());
        user.setDob(java.sql.Date.valueOf(req.getDob()));
        user.setPhone(req.getPhone());
        user.setRole(UserRole.CUSTOMER);
        user.setStatus(UserStatus.ACTIVE);

        userRepo.save(user);

        RegisterResponse res = new RegisterResponse();
        res.setEmail(user.getEmail());
        res.setTen(user.getName());
        res.setMessage(UserMessage.REGISTER_SUCCESSFULLY);
        return res;
    }

    @Override
    public boolean checkEmailExists(String email) {
        return userRepo.existsByEmail(email);
    }

}
