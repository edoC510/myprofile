package com.shopgiayonline.core.customer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shopgiayonline.core.customer.model.response.CustomerUserResponse;
import com.shopgiayonline.entity.User;
import com.shopgiayonline.repository.UserRepository;

@Repository
public interface CustomerUserRepository extends UserRepository {

    User findAllByUsername(String username);

    User findUserByEmail(String email);

    Optional<User> findAllById(Integer id);

    Optional<User> findAllByEmail(String email);

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
    CustomerUserResponse findUserById(@Param("id") Integer id);

    boolean existsByEmail(String email);
}
