package com.shopgiayonline.infrastructure.authentication;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.shopgiayonline.common.enums.UserRole;
import com.shopgiayonline.core.admin.repository.AdminUserRepository;
import com.shopgiayonline.entity.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private AdminUserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws RuntimeException {
        User user = userRepo.findUsersByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new RuntimeException("Đăng nhập không thành công "));
        UserRole role = user.getRole();
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name())));
    }
}
