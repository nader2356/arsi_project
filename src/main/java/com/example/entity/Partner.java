package com.example.entity;

import com.example.util.enumData.PartnerType;
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
@Table(name = "partner")
@SQLDelete(sql = "UPDATE partner SET deleted = true WHERE id=?")
@Where(clause = "deleted = false ")
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(length = 1000)
    private String description;
    private String address;
    private String contact;
    private PartnerType type;
    private String image;
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;
    private boolean deleted = Boolean.FALSE;
}