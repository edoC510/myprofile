package com.shopgiayonline.core.customer.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopgiayonline.common.enums.UserRole;
import com.shopgiayonline.core.customer.model.request.LoginRequest;
import com.shopgiayonline.core.customer.repository.CustomerUserRepository;
import com.shopgiayonline.core.customer.service.CustomerUserService;
import com.shopgiayonline.entity.User;
import com.shopgiayonline.service.TokenService;

@Service
public class CustomerUserServiceImpl implements CustomerUserService {

    @Autowired
    private CustomerUserRepository customerUserRepo;

    @Autowired
    TokenService tokenService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User loginGoogle(String email, String name, String image) {
        User user = customerUserRepo.findUserByEmail(email);
        return user;
    }

    public User createAccountGoogle(String email, String name, String image) {
        User addUser = User.builder().email(email).username(email).name(name).build();
        addUser.setRole(UserRole.CUSTOMER);
        addUser.setCreatedAt(LocalDateTime.now());
        addUser.setPassword("$2a$12$Xcp214DEIsQr61KrINMt5egl.2Tqfcjwhu32Y9Y5TCEFzH5yiEOlS");
        addUser.setAvatarUrl(image);
        addUser.setStatus((short) 1);
        User user1 = customerUserRepo.save(addUser);
        customerUserRepo.save(user1);
        return user1;
    }

    @Override
    public User updatePhone(Integer id, String phone) {

        User user = customerUserRepo.findAllById(id).orElseThrow();

        user.setPhone(phone);

        customerUserRepo.save(user);
        return user;
    }

    @Override
    @Transactional
    public boolean changePassword(Integer userId, String oldPassword, String newPassword) {
        User user = customerUserRepo.findById(userId).orElse(null);
        if (user != null) {
            if (passwordEncoder.matches(oldPassword, user.getPassword())) {
                user.setPassword(passwordEncoder.encode(newPassword));
                customerUserRepo.save(user);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public User findByToken(String token) {
        if (tokenService.getUserNameByToken(token) == null) {
            return null;
        }
        String username = tokenService.getUserNameByToken(token);
        User user = customerUserRepo.findAllByUsername(username);
        return user;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        if (email == null) {
            return Optional.empty();
        }
        return customerUserRepo.findAllByEmail(email);
    }

    @Override
    public String checkValiDate(LoginRequest loginRequest) {
        User u = customerUserRepo.findUserByEmail(loginRequest.getUsernameOrEmail());
        if (u == null || u.getPassword() != loginRequest.getPassword()) {
            return "Sai tài khoản hoặc mật khẩu";
        } else {
            return "Ok";
        }

    }
}
