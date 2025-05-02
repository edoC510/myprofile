package com.shopgiayonline.core.customer.service.impl;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shopgiayonline.common.enums.UserRole;
import com.shopgiayonline.core.customer.model.request.RegisterRequest;
import com.shopgiayonline.core.customer.repository.CustomerUserRepository;
import com.shopgiayonline.core.customer.service.RegisterService;
import com.shopgiayonline.entity.User;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    CustomerUserRepository customerUserRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(RegisterRequest payload) {

        Random random = new Random();
        int randomNumber = random.nextInt(9000) + 1000;

        if (customerUserRepo.existsByEmail(payload.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");
        }

        User user = new User();
        user.setUserCode("U" + randomNumber);
        user.setEmail(payload.getEmail());
        user.setName(payload.getName());
        user.setPhone(payload.getPhone());
        user.setRole(UserRole.CUSTOMER);
        user.setCreatedAt(LocalDateTime.now());
        user.setUsername(payload.getEmail());
        user.setDob(payload.getDob());
        user.setGender(payload.getGender());
        user.setPassword(passwordEncoder.encode(payload.getPassword()));
        return customerUserRepo.save(user);
    }
}
