package com.shopgiayonline.core.admin.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopgiayonline.common.util.DataUltil;
import com.shopgiayonline.core.admin.model.request.AdminUserRequest;
import com.shopgiayonline.core.admin.model.response.AdminUserResponse;
import com.shopgiayonline.core.admin.service.AdminUserService;
import com.shopgiayonline.entity.User;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/user")
public class AdminUserController {
    @Autowired
    private final AdminUserService adminUserService;

    public AdminUserController(@Qualifier("adminUserService") AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @GetMapping("/getAllUser")
    public List<AdminUserResponse> getAllUser() {
        return adminUserService.getAllUser();
    }

    @GetMapping("/getAllUserByRole")
    public List<AdminUserResponse> getAllUserByRole(@RequestParam("role") String role) {
        return adminUserService.getAllUserByRole(role);
    }

    @GetMapping("/trang-thai")
    public ResponseEntity<?> getAllByStatus(@RequestParam("status") Short status) {
        List<User> listUsers = adminUserService.getAllByStatus(status);
        HashMap<String, Object> map = DataUltil.setData("ok", listUsers);
        return ResponseEntity.ok(map);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@RequestBody AdminUserRequest request, @PathVariable Integer id) {
        return ResponseEntity.ok(adminUserService.update(request, id));
    }

    @PutMapping("{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(adminUserService.delete(id));
    }

    @PutMapping("{id}/vo-hieu-hoa")
    public ResponseEntity<?> voHieuHoaUser(@PathVariable Integer id) {
        return ResponseEntity.ok(adminUserService.disableUser(id));
    }
}
