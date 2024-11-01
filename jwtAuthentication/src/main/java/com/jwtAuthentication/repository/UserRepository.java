package com.jwtAuthentication.repository;


import com.jwtAuthentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<org.springframework.security.core.userdetails.User> findByName(String username);
}
