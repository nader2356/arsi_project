package com.example.entity;



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
@Table(name = "formation")
@SQLDelete(sql = "UPDATE formation SET deleted = true WHERE id=?")
@Where(clause = "deleted = false ")
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime date;
    private String image;
    private Long maxOfParticipants;
    private Long numberOfParticipants;
    private String location;
    private String formateur;
    private Long price;
    private boolean status;
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant UpdatedAt;
    private boolean deleted = Boolean.FALSE;

}