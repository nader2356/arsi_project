package com.example.repository;

import com.example.entity.Newsletter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsletterRepository  extends JpaRepository<Newsletter, Integer> {
    @Query("SELECT n FROM Newsletter n")
    List<Newsletter> findAll();
}