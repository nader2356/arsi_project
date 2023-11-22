package com.example.repository;

import com.example.entity.Event;
import com.example.entity.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {

    @Query(value = "SELECT E from Event E where E.status=true  ")
    List<Event> findAllEvent();
    @Query(value = "SELECT E from Event E where E.status=false ")
    List<Event> findAllSuggestEvent();

}