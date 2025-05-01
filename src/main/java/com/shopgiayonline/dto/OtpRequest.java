package com.shopgiayonline.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OtpRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank(message = "Tên người dùng bắt buộc để gửi mail")
    private String name;
}
