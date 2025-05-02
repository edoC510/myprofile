package com.shopgiayonline.core.customer.service;

import com.shopgiayonline.core.customer.model.request.LoginRequest;

public interface LoginService {
    String login(LoginRequest loginRequest);
}
