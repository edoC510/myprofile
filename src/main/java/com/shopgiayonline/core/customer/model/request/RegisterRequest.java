package com.shopgiayonline.core.customer.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String name;

    private String email;

    private String code;

    private String phone;

    private String password;

    private Short gender;

    private java.sql.Date dob;
}
