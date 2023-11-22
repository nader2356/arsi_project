package com.example.repository;

import com.example.entity.UserFormation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserFormationRepository extends JpaRepository<UserFormation,Long> {
    List<UserFormation> findAllByUserId(Long id);
    List<UserFormation> findAllByFormationId(Long id);

}