package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.User;

public interface UserRepository   extends JpaRepository<User,Long> {
	@Query("SELECT u from User u where u.role='ADMIN' OR u.expiresAt > CURRENT_TIMESTAMP ")
    List<User> findAllMember();
    Optional<User> findByUserName (String userName);
    Optional<User> findByEmail (String email);
     boolean existsByEmail(String email);
    boolean existsByUserName(String userName);
}
