package com.shopgiayonline.service;

import com.shopgiayonline.dto.RegisterRequest;
import com.shopgiayonline.dto.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest req);

    boolean checkEmailExists(String email);

}
