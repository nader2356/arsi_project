package com.example.repository;

import com.example.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ContactRepository extends JpaRepository<Contact,Long> {

	List<Contact> findAllByUserId(UUID id);

}
