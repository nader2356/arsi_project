package com.example.repository;

import com.example.entity.Club;
import com.example.entity.User;
import com.example.entity.UserClub;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserClubRepository extends JpaRepository<UserClub, Long> {

    List<UserClub> findByUserId(Long id);

    boolean existsByUserAndClub(User user, Club club);

    List<UserClub> findAllByClubId(Long clubId);

    List<UserClub> findByUser(User user);
}
