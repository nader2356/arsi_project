package com.example.entity;


import com.example.util.enumData.EventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event")
@SQLDelete(sql = "UPDATE event SET deleted = true WHERE id=?")
@Where(clause = "deleted = false ")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime date;
    private String image;
    private Long numberOfParticipants;
    private String location;
    @Enumerated(EnumType.STRING)
    private EventType type;
    @ManyToOne(optional = true)
    private Partner partner;
    private boolean status;
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant UpdatedAt;
    private boolean deleted = Boolean.FALSE;


}