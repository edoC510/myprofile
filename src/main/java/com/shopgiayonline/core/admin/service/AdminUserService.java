package com.shopgiayonline.core.admin.service;

import java.util.List;

import com.shopgiayonline.core.admin.model.request.AdminUserRequest;
import com.shopgiayonline.core.admin.model.response.AdminUserResponse;
import com.shopgiayonline.entity.User;

public interface AdminUserService {

    AdminUserResponse delete(Integer id);

    AdminUserResponse update(AdminUserRequest user, Integer id);

    AdminUserResponse disableUser(Integer id);

    List<AdminUserResponse> getAllUserByRole(String role);

    List<AdminUserResponse> getAllUser();

    List<AdminUserResponse> getAdmin();

    List<AdminUserResponse> getCustomer();

    List<AdminUserResponse> getEmployee();

    List<User> getAllByStatus(Short status);

}
