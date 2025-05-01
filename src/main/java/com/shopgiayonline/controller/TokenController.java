package com.shopgiayonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopgiayonline.infrastructure.authentication.JwtTokenProvider;
import com.shopgiayonline.service.TokenService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/")
public class TokenController {

    @Autowired
    TokenService tokenService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @PostMapping("/genToken")
    public ResponseEntity<String> login(@RequestParam("username") String username) {
        String data = tokenService.genToken(username);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/validate-token")
    public ResponseEntity<String> validateToken(@RequestParam("token") String token) {
        if (jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.ok("JWT token is valid");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("JWT token is not valid");
        }
    }

    @GetMapping("/getUseNameByToken")
    public String getUseNameByToken(@RequestParam("token") String token) {
        return jwtTokenProvider.getUsername(token);
    }

}
