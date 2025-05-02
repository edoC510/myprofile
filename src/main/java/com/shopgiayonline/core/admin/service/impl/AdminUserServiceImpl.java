package com.shopgiayonline.core.admin.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shopgiayonline.common.status.UserStatus;
import com.shopgiayonline.core.admin.model.request.AdminUserRequest;
import com.shopgiayonline.core.admin.model.response.AdminUserResponse;
import com.shopgiayonline.core.admin.repository.AdminUserRepository;
import com.shopgiayonline.core.admin.service.AdminUserService;
import com.shopgiayonline.entity.User;

@Service("adminUserService")
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    AdminUserRepository adminUserRepository;

    public List<AdminUserResponse> getCustomer() {
        return adminUserRepository.findUserByRole("CUSTOMER");
    }

    public List<AdminUserResponse> getEmployee() {
        return adminUserRepository.findUserByRole("EMPLOYEE");
    }

    public List<AdminUserResponse> getAdmin() {
        return adminUserRepository.findUserByRole("ADMIN");
    }

    @Override
    public List<User> getAllByStatus(Short status) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return adminUserRepository.getAllByStatus(status, sort);
    }

    @Override
    public AdminUserResponse delete(Integer id) {
        User user = adminUserRepository.findById(id).get();
        if (user != null) {
            user.setStatus(UserStatus.INACTIVE);
            User newUser = adminUserRepository.save(user);
            return adminUserRepository.findUserById(newUser.getId());
        }
        return null;
    }

    @Override
    public AdminUserResponse update(AdminUserRequest request, Integer id) {
        User user = adminUserRepository.findById(id).get();
        if (user != null) {
            user.setEmail(request.getEmail());
            user.setUpdatedAt(LocalDateTime.now());
            user.setName(request.getName());
            user.setDob(request.getDob());
            user.setPhone(request.getPhone());
            user.setGender(request.getGender());
            user.setAvatarUrl(request.getAvatarUrl());
            String newAddress = request.getAddress();
            if (newAddress != null) {
                // Cập nhật địa chỉ hiện tại của người dùng với địa chỉ mới
                user.getAddresses().forEach(address -> address.setAddressLine(newAddress));
            }
            // Lưu thông tin người dùng và trả về thông tin đã được cập nhật
            User updatedUser = adminUserRepository.save(user);
            return adminUserRepository.findUserById(updatedUser.getId());
        }
        return null;
    }

    @Override
    public AdminUserResponse disableUser(Integer id) {
        User user = adminUserRepository.findById(id).get();
        if (user != null) {
            user.setStatus(UserStatus.INACTIVE);
            User newUser = adminUserRepository.save(user);
            return adminUserRepository.findUserById(newUser.getId());
        }
        return null;
    }

    @Override
    public List<AdminUserResponse> getAllUserByRole(String role) {
        return adminUserRepository.findUserByRole(role);
    }

    @Override
    public List<AdminUserResponse> getAllUser() {
        return adminUserRepository.getAllUser();
    }
}
