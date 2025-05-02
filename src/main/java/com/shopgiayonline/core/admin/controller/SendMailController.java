package com.shopgiayonline.core.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopgiayonline.core.admin.model.request.AdminUserRequest;
import com.shopgiayonline.core.admin.model.request.OTPResquest;
import com.shopgiayonline.core.admin.service.ClientService;

@RestController
@RequestMapping("/api/admin/mail")
public class SendMailController {
    @Autowired
    private ClientService clientService;

    @PostMapping(value = "send")
    public ResponseEntity<Boolean> create(@RequestBody AdminUserRequest adminUserRequest) {
        Boolean result = clientService.create(adminUserRequest);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "sendOTP")
    public ResponseEntity<Boolean> sendMailOTP(@RequestBody OTPResquest otpResquest) {
        Boolean result = clientService.createOTP(otpResquest);
        return ResponseEntity.ok(result);
    }
}
