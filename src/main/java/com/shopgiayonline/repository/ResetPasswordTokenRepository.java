package com.shopgiayonline.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopgiayonline.entity.ResetPasswordToken;

@Repository
public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken, Integer> {
    Optional<ResetPasswordToken> findFirstByOrderByCreatedAtDesc();

    Optional<ResetPasswordToken> findByTokenAndUserId(String token, Integer userId);

    Optional<ResetPasswordToken> findByUserId(Integer userId);
}