package com.example.entity;

import com.example.util.enumData.OpportunityType;
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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "opportunity")
@SQLDelete(sql = "UPDATE opportunity SET deleted = true WHERE id=?")
@Where(clause = "deleted = false ")
public class Opportunity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @Column(length = 1000)
    private String description;
    @Enumerated(EnumType.STRING)
    private OpportunityType type;
    private String company;
    private String image;
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;
    private boolean deleted = Boolean.FALSE;
}