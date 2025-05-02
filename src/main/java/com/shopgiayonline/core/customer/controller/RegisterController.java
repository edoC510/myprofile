package com.shopgiayonline.core.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopgiayonline.core.customer.model.request.RegisterRequest;
import com.shopgiayonline.core.customer.service.RegisterService;
import com.shopgiayonline.entity.User;

@RestController
@RequestMapping("/api/khach-hang")
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @PostMapping("/register")
    public User registerUser(@RequestBody RegisterRequest payload) {

        return registerService.registerUser(payload);

    }
}
