package com.example.repository;

import com.example.entity.Event;
import com.example.util.enumData.EventType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {

	   @Query(value = "SELECT E from Event E where E.isActivity=false and  E.type= :type ")
	    List<Event> findAllEvent(@Param("type") EventType type);
	    @Query(value = "SELECT E from Event E where E.isActivity=false ")
	    List<Event> findAllEvent();

	    @Query(value = "SELECT E from Event E where E.isActivity=true ")
	    List<Event> findAllActivity();

}