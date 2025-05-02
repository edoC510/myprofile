package com.shopgiayonline.core.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopgiayonline.core.admin.model.request.OTPResquest;
import com.shopgiayonline.core.admin.service.ClientService;

@RestController
@RequestMapping("/api/khach-hang/mail")
public class SendMailCustomerController {

    @Autowired
    private ClientService clientService;

    @PostMapping(value = "/sendOTP")
    public ResponseEntity<Boolean> sendMailOTP(@RequestBody OTPResquest otpResquest) {
        Boolean result = clientService.createOTP(otpResquest);
        return ResponseEntity.ok(result);
    }
}
