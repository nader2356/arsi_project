package com.example.repository;

import com.example.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MediaRepository extends JpaRepository<Media,Long> {
}
