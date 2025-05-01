package com.shopgiayonline.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopgiayonline.entity.OtpToken;

public interface OtpTokenRepository extends JpaRepository<OtpToken, Long> {
    Optional<OtpToken> findFirstByEmailAndValidOrderByExpiresAtDesc(String email, Boolean valid);
}
