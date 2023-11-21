package com.example.repository;

import com.example.entity.UserCompetence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCompetenceRepository extends JpaRepository<UserCompetence,Long> {

    List<UserCompetence> findAllByUserId(Long id);
    List<UserCompetence> findAllByCompetenceId(Long id);

}