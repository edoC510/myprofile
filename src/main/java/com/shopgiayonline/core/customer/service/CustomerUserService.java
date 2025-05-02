package com.shopgiayonline.core.customer.service;

import java.util.Optional;

import com.shopgiayonline.core.customer.model.request.LoginRequest;
import com.shopgiayonline.entity.User;

public interface CustomerUserService {
    User loginGoogle(String email, String name, String image);

    User findByToken(String token);

    Optional<User> findByEmail(String email);

    String checkValiDate(LoginRequest loginRequest);

    User createAccountGoogle(String email, String name, String image);

    User updatePhone(Integer id, String phone);

    boolean changePassword(Integer userId, String oldPassword, String newPassword);
}
