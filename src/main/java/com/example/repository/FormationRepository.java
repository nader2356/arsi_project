package com.example.repository;

import com.example.entity.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FormationRepository extends JpaRepository<Formation,Long> {
    @Query(value = "SELECT F from Formation F where F.status=true  ")
    List<Formation> findAllFormation();
    @Query(value = "SELECT F from Formation F where F.status=false ")
    List<Formation> findAllSuggestFormation();
}