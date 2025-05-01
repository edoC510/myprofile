package com.shopgiayonline.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RegisterResponse {
    private String email;
    private String ten;
    private String message;
}
