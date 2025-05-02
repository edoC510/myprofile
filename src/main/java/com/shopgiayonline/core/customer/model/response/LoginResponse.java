package com.shopgiayonline.core.customer.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private String usernameOrEmail;
    private Integer userId;

    public LoginResponse(String accessToken, String usernameOrEmail) {
        this.accessToken = accessToken;
        this.usernameOrEmail = usernameOrEmail;
    }
}
