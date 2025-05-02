package com.shopgiayonline.infrastructure.authentication;

import java.util.Collections;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.shopgiayonline.core.admin.repository.AdminUserRepository;
import com.shopgiayonline.entity.User;

@Component
public class Authentication implements AuthenticationProvider {

    private final AdminUserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public Authentication(AdminUserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public org.springframework.security.core.Authentication authenticate(
            org.springframework.security.core.Authentication authentication) throws AuthenticationException {
        String usernameOrEmail = authentication.getName();
        String password = authentication.getCredentials().toString();

        try {
            User user = userRepo.findByUsername(usernameOrEmail);

            if (user == null) {

                user = userRepo.findByEmail(usernameOrEmail)
                        .orElseThrow(() -> new UsernameNotFoundException("Đăng nhập không thành công") {
                        });

            }

            boolean matches = passwordEncoder.matches(password, user.getPassword());

            if (matches == false) {
                throw new BadCredentialsException("Đăng nhập không thành công");
            }

        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("Đăng nhập không thành công");
        }

        return new UsernamePasswordAuthenticationToken(usernameOrEmail, password, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
