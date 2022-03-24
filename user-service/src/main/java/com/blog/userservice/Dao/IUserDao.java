package com.blog.userservice.Dao;

import com.blog.userservice.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
