package com.example.repository;

import com.example.entity.Event;
import com.example.entity.User;
import com.example.entity.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserEventRepository extends JpaRepository<UserEvent,Long> {

	List<UserEvent> findAllByUserId(UUID id);
    List<UserEvent> findAllByEventId(Long id);
    
    @Query("SELECT CASE WHEN COUNT(ue) > 0 THEN true ELSE false END FROM UserEvent ue WHERE ue.user = ?1 AND ue.event = ?2")
    Boolean CheckJoinedEvent(User userId, Event eventId);

}