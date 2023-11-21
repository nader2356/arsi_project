package com.example.repository;

import com.example.entity.Competence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetenceRepository extends JpaRepository<Competence,Long> {
  boolean existsByName(String name);
}