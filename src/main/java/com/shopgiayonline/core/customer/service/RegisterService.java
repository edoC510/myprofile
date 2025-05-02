package com.shopgiayonline.core.customer.service;

import com.shopgiayonline.core.customer.model.request.RegisterRequest;
import com.shopgiayonline.entity.User;

public interface RegisterService {

    User registerUser(RegisterRequest payload);
}
