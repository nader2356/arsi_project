package com.example.repository;

import com.example.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {
    @Query("SELECT d FROM Document d")
    List<Document> findAllDocument();
}
