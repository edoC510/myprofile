package com.shopgiayonline.core.admin.service;

import com.shopgiayonline.core.admin.model.request.AdminUserRequest;
import com.shopgiayonline.core.admin.model.request.OTPResquest;

public interface ClientService {
    Boolean create(AdminUserRequest adminUserRequest);

    Boolean createOTP(OTPResquest otpRequest);
}
