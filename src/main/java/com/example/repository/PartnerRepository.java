package com.example.repository;

import com.example.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<Partner,Long> {

    boolean existsByName(String name);
}