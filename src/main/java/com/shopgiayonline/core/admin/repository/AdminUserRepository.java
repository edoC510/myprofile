package com.shopgiayonline.core.admin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shopgiayonline.common.enums.UserRole;
import com.shopgiayonline.core.admin.model.response.AdminUserResponse;
import com.shopgiayonline.entity.User;
import com.shopgiayonline.repository.UserRepository;

@Repository
public interface AdminUserRepository extends UserRepository {
       User findByUsername(String username);

       Optional<User> findUsersByUsernameOrEmail(String username, String email);

       Optional<User> findByEmail(String email);

       List<User> findByRoleAndStatusOrderByCreatedAtDesc(UserRole role, Integer status);

       Optional<User> findByPhone(String sdt);

       List<User> getAllByStatus(Short status, Sort sort);

       @Query(value = """
                     SELECT ROW_NUMBER() OVER(ORDER BY u.id DESC) AS serial,
                            u.id as id, u.avatar_url as avatarUrl, u.email as email,
                            u.gender as gender, u.user_id as userId, u.dob as dob,
                            u.password as password, u.role as role, u.phone as phone,
                            u.name as name, u.status as status, u.username as username,
                            COUNT(hd.id) AS orderCount
                     FROM users u
                     LEFT JOIN orders hd ON u.id = hd.user_id
                     WHERE u.role =:roles
                     GROUP BY u.id, u.avatar_url, u.email, u.gender, u.user_id, u.dob, u.password, u.role, u.phone, u.name, u.status, u.username;
                     """, nativeQuery = true)
       List<AdminUserResponse> findUserByRole(@Param("roles") String roles);

       @Query(value = """
                      SELECT ROW_NUMBER() OVER(ORDER BY u.id DESC) AS serial,
                            u.id as id, u.avatar_url as avatarUrl, u.email as email,
                            u.gender as gender, u.user_id as userId, u.dob as dob,
                            u.password as password, u.role as role, u.phone as phone,
                            u.name as name, u.status as status, u.username as username,
                            COUNT(hd.id) AS orderCount
                     FROM users u
                     LEFT JOIN orders hd ON u.id = hd.user_id
                     GROUP BY u.id, u.avatar_url, u.email, u.gender, u.user_id, u.dob, u.password, u.role, u.phone, u.name, u.status, u.username;
                     """, nativeQuery = true)
       List<AdminUserResponse> getAllUser();

       @Query(value = """
                      SELECT ROW_NUMBER() OVER(ORDER BY u.id DESC) AS serial,
                            u.id as id, u.avatar_url as avatarUrl, u.email as email,
                            u.gender as gender, u.user_id as userId, u.dob as dob,
                            u.password as password, u.role as role, u.phone as phone,
                            u.name as name, u.status as status, u.username as username,
                            COUNT(hd.id) AS orderCount
                     FROM users u
                     LEFT JOIN orders hd ON u.id = hd.user_id
                     WHERE u.id =:id
                     GROUP BY u.id, u.avatar_url, u.email, u.gender, u.user_id, u.dob, u.password, u.role, u.phone, u.name, u.status, u.username;
                     """, nativeQuery = true)
       AdminUserResponse findUserById(@Param("id") Integer id);
}
