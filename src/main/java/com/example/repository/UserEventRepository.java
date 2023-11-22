package com.example.repository;

import com.example.entity.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserEventRepository extends JpaRepository<UserEvent,Long> {

    List<UserEvent> findAllByUserId(Long id);
    List<UserEvent> findAllByEventId(Long id);

}