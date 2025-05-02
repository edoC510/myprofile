package com.shopgiayonline.core.customer.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopgiayonline.core.customer.model.request.LoginRequest;
import com.shopgiayonline.core.customer.model.response.LoginResponse;
import com.shopgiayonline.core.customer.repository.CustomerUserRepository;
import com.shopgiayonline.core.customer.service.CustomerUserService;
import com.shopgiayonline.core.customer.service.LoginService;
import com.shopgiayonline.entity.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/khach-hang/user")
@CrossOrigin(origins = { "*" })
public class CustomerUserController {

    @Autowired
    CustomerUserRepository customerUserRepo;

    @Autowired
    private CustomerUserService customerUserService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LoginService loginService;

    @GetMapping()
    public User getUserByUsername(@RequestParam("username") String username) {
        User user = customerUserRepo.findAllByUsername(username);
        return user;
    }

    @GetMapping("/find-user-by-email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email, @RequestParam("username") String username,
            @RequestParam("image") String image) {
        User user = customerUserService.loginGoogle(email, username, image);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/createAccountGG/{email}")
    public ResponseEntity<?> createAccountGoogle(@PathVariable String email, @RequestParam("username") String username,
            @RequestParam("image") String image) {
        User user = customerUserService.createAccountGoogle(email, username, image);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/updateSDT/{id}")
    public ResponseEntity<?> updatePhone(@PathVariable(value = "id") Integer id, @RequestParam("phone") String phone) {
        User user = customerUserService.updatePhone(id, phone);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(),
                            loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = loginService.login(loginRequest);
            String usernameOrEmail = loginRequest.getUsernameOrEmail();

            LoginResponse jwtAuthResponse = new LoginResponse();
            jwtAuthResponse.setAccessToken(token);
            jwtAuthResponse.setUsernameOrEmail(usernameOrEmail);
            jwtAuthResponse.setUserId(customerUserService.findByToken(token).getId());
            return ResponseEntity.ok(jwtAuthResponse);
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex);
        }
    }

    @GetMapping("/check-validate")
    public ResponseEntity<?> checkValidate(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(customerUserService.checkValiDate(loginRequest));
    }

    @GetMapping("/find-by-token")
    public ResponseEntity<?> validate(@RequestParam("token") String token) {
        return ResponseEntity.ok(customerUserService.findByToken(token));
    }

    @GetMapping("/find-email")
    public ResponseEntity<?> findUserByEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok(customerUserService.findByEmail(email));
    }

    @PutMapping("/{id}/doi-mat-khau")
    public ResponseEntity<?> changePassword(@PathVariable("id") Integer userId,
            @RequestBody Map<String, String> reqBody) {
        return customerUserService.changePassword(userId, reqBody.get("oldPassword"), reqBody.get("newPassword"))
                ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().body("Mật khẩu cũ chưa chính xác");
    }
}
