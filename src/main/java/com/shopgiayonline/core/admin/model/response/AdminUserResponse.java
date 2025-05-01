package com.shopgiayonline.core.admin.model.response;

import org.springframework.beans.factory.annotation.Value;

public interface AdminUserResponse {
    Integer getSerial();

    @Value("#{target.id}")
    Integer getId();

    @Value("#{target.avatarUrl}")
    String getAvatarUrl();

    @Value("#{target.email}")
    String getEmail();

    @Value("#{target.dob}")
    String getDob();

    @Value("#{target.gender}")
    Short getGender();

    @Value("#{target.userId}")
    String getUserId();

    @Value("#{target.password}")
    String getPassword();

    @Value("#{target.role}")
    String getRole();

    @Value("#{target.phone}")
    String getPhone();

    @Value("#{target.name}")
    String getName();

    @Value("#{target.status}")
    Short getStatus();

    @Value("#{target.username}")
    String getUsername();

    @Value("#{target.orderCount}")
    Integer getOrderCount();
}
